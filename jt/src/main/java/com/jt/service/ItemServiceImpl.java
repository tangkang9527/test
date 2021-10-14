package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;


    /**
     * 语法:selectPage语法说明
     *      1.page: MP内部指定的分页对象
     *      2.queryWrapper 条件构造器
     *      Sql: where titile like "%xxx%"
     * @param pageResult
     * @return
     */
    @Override
    public PageResult getItemList(PageResult pageResult) {
        //判断用户的数据是否有值
        boolean flag = StringUtils.hasLength(pageResult.getQuery());
        QueryWrapper<Item> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(flag,"title",pageResult.getQuery());
        //编辑MP的分页对象
        IPage<Item> page = new Page<>();
        itemMapper.selectPage(page,queryWrapper);

        return null;
    }
}
