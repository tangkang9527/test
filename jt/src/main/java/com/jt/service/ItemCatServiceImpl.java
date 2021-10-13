package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jt.mapper.ItemCatMapper;
import com.jt.pojo.ItemCat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemCatServiceImpl implements ItemCatService{

    @Autowired
    private ItemCatMapper itemCatMapper;

    /**
     * 弊端: 由于多次循环遍历 查询数据库,导致数据库查询次数太多效率极低.
     * 思路:
     *      1.刚才的业务逻辑梳理
     *      2.如何优化?????   提高效率
     * @param level
     * @return
     */
    @Override
    public List<ItemCat> findItemCatList(Integer level) {
        //查询一级商品分类信息
        QueryWrapper<ItemCat> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("parent_id",0);
        //100
        List<ItemCat> oneList = itemCatMapper.selectList(queryWrapper);
        //查询二级商品分类信息
        for(ItemCat oneItemCat: oneList){
            //1.为了复用条件构造器 将之前的数据清空
            queryWrapper.clear();
            //查询二级数据 parent_id = 一级ID
            queryWrapper.eq("parent_id",oneItemCat.getId());
            List<ItemCat> twoList = itemCatMapper.selectList(queryWrapper);
            //遍历二级列表 查询三级数据,封装数据返回
            oneItemCat.setChildren(twoList);
        }
        return oneList;
    }
}
