package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        return null;
    }

    @Override
    public List<ItemCat> findItemCatList(Integer level) {
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
        return getThreeList(map);
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
