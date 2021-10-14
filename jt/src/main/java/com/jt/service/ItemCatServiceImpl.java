package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * 优化手段:
     *      思路:获取所有的数据库记录,之后按照父子级关系进行封装
     *      数据结构: Map<k,v>
     *               Map<parentId,List当前父级的子级信息(不嵌套)>
     *      例子:     Map<0,List[{id=1,name="xx",children=null}.....]>
     *
     * 封装数据规则:
     *      1.遍历所有的数据.
     *      2.获取parentId
     *      3.判断parentId是否存在,之后实现数据封装
     */

    public Map<Integer,List<ItemCat>> getMap(){
        Map<Integer,List<ItemCat>> map = new HashMap<>();
        //查询所有的数据库记录
        List<ItemCat> list = itemCatMapper.selectList(null);
        //1.遍历数据
        for(ItemCat itemCat:list){
            //获取parentId
            int parentId = itemCat.getParentId();
            if(map.containsKey(parentId)){  //判断集合中是否有key
                //表示数据存在,将自己追加
                map.get(parentId).add(itemCat);
            }else{
                //key不存在, 定义list集合,将自己作为第一个元素追加
                List<ItemCat> childrenList = new ArrayList<>();
                childrenList.add(itemCat);
                //将数据保存到map集合中
                map.put(parentId,childrenList);
            }
        }
        return map;
    }

    //该方法获取1-2级数据信息
    public List<ItemCat> getTwoList(Map<Integer,List<ItemCat>> map){
        //1.先查询一级菜单数据
        List<ItemCat> oneList = map.get(0);
        //2.遍历每个一级菜单去封装二级数据
        for(ItemCat oneItemCat : oneList){
            //parent_id = 一级ID
            int parentId = oneItemCat.getId();
            //查询二级数据
            List<ItemCat> twoList = map.get(parentId);
            //将数据进行封装
            oneItemCat.setChildren(twoList);
        }
        //返回一级数据
        return oneList;
    }

    /**
     * 实现思路:
     *      1. 获取二级分类列表信息
     *      2. 遍历一级菜单,获取二级数据
     *      3. 根据二级菜单查询三级数据   防止二级数据为null的现象
     *      4. 将三级数据封装到二级中
     * @param map
     * @return
     */
    public List<ItemCat> getThreeList(Map<Integer,List<ItemCat>> map){
        //1.获取1-2数据信息  包含了2级的children
        List<ItemCat> oneList = getTwoList(map);
        //2.编辑一级数据,获取二级数据
        for(ItemCat oneItemCat : oneList){
            List<ItemCat> twoList = oneItemCat.getChildren();
            if(twoList == null || twoList.size()==0){
                //跳过本地循环,进入下一次循环
                continue;
            }
            //3.遍历二级数据,查询三级信息
            for (ItemCat twoItemCat : twoList){
                //查询三级  parentId = 二级ID
                int parentId = twoItemCat.getId();
                List<ItemCat> threeList = map.get(parentId);
                //将三级封装到二级中
                twoItemCat.setChildren(threeList);
            }
        }
        return oneList;
    }

    /**
     * 如果使用常规的写法 耗时: 900毫秒
     *                 经过优化: 20毫秒
     *  CURD: 好代码!!!
     * @param level
     * @return
     */
    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        long startTime = System.currentTimeMillis();
        //获取所有集合数据
        Map<Integer,List<ItemCat>> map = getMap();
        if(level == 1){
            //1.一级商品分类信息
            return map.get(0);
        }
        //获取一级菜单和二级菜单
        if(level == 2){
            return getTwoList(map);
        }

        //获取三级菜单数据 1-2-3
        List<ItemCat> allList = getThreeList(map);
        long endTime = System.currentTimeMillis();
        System.out.println("耗时:"+(endTime-startTime)+"毫秒");
        return allList;
    }

    //优化:自动生成时间
    @Override
    @Transactional
    public void saveItemCat(ItemCat itemCat) {
        //Date date = new Date();
        itemCat.setStatus(true);
                //.setCreated(date)
                //.setUpdated(date);
        itemCatMapper.insert(itemCat);
    }

    /**
     * 业务: 如果是父级 则应该删除子级和自己.
     * 思路:
     *      1. 判断是否为3级标签  直接删除
     *      2. 判断是否为2级标签  先删除三级再删除二级
     *      3. 判断是否为1级标签  先查询二级,再删除三级和二级再删除一级
     * @param itemCat
     */
    @Override
    @Transactional
    public void deleteItemCat(ItemCat itemCat) {
        if(itemCat.getLevel() == 3){
            int id = itemCat.getId();
            itemCatMapper.deleteById(id);
            return;
        }
        if(itemCat.getLevel() == 2){ //id=二级id
            //Sql: xxx where parent_id = 二级ID
            int id = itemCat.getId();
            QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("parent_id",id);
            //删除的是三级数据
            itemCatMapper.delete(queryWrapper);
            //再删除自己
            itemCatMapper.deleteById(id);
            return;
        }

        /**
         * 删除一级菜单
         */
        //1.查询二级数据  Sql: where parent_id=id
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper();
        int id = itemCat.getId();
        queryWrapper.eq("parent_id",id);
        //由于是删除的业务,只需要获取id即可 所以使用objs
        List idList = itemCatMapper.selectObjs(queryWrapper);

        //判断是否有二级数据.如果有直接删除.如果没有直接删除一级
        if(idList.size()>0){
            //根据二级Id删除三级数据 Sql where parent_id in (1,2,3)
            queryWrapper.clear();
            queryWrapper.in("parent_id",idList);
            itemCatMapper.delete(queryWrapper);
            //将所有的1-2级的ID,封装到一个集合中
            idList.add(id);
            //将所有1级2级全部删除  Sql where id in (xx,xx,xx,xx)
            itemCatMapper.deleteBatchIds(idList);
        }else{
            itemCatMapper.deleteById(id);
        }
    }

    /**
     *   id/status
     *   Sql: update item_cat set status=xx,updated=xx
     *        where id=xx
     *   解释: updateById ID当做唯一where条件,其它不为null的属性
     *        当做set条件
     */
    @Override
    @Transactional
    public void updateStatus(ItemCat itemCat) {

        itemCatMapper.updateById(itemCat);
    }

    /**
     * itemCat对象,不为null的数据执行业务
     * 只改了name属性  set name=xxx where id=xx
     * @param itemCat
     */
    @Override
    @Transactional
    public void updateItemCat(ItemCat itemCat) {
        ItemCat temp = new ItemCat();
        temp.setId(itemCat.getId())
                .setName(itemCat.getName());
        itemCatMapper.updateById(temp);
    }


    /**
     * 弊端: 由于多次循环遍历 查询数据库,导致数据库查询次数太多效率极低.
     * 思路:
     *      1.刚才的业务逻辑梳理
     *      2.如何优化?????   提高效率
     * 优化策略:
     *       降低用户查询数据库的次数.
     * @param level
     * @return
     */
    /*@Override
    public List<ItemCat> findItemCatList(Integer level) {
        //查询一级商品分类信息
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
        //查询二级商品分类信息
        for(ItemCat oneItemCat: oneList){
            //1.为了复用条件构造器 将之前的数据清空
            queryWrapper.clear();
            //查询二级数据 parent_id = 一级ID
            queryWrapper.eq("parent_id",oneItemCat.getId());
            List<ItemCat> twoList = itemCatMapper.selectList(queryWrapper);
                    //遍历二级列表 查询三级数据,封装数据返回
            //将二级封装到一级
            oneItemCat.setChildren(twoList);
        }
        return oneList;
    }*/
}
