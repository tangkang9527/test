package com.jt.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.jt.mapper.ItemDescMapper;
import com.jt.mapper.ItemMapper;
import com.jt.pojo.Item;
import com.jt.pojo.ItemDesc;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private ItemMapper itemMapper;
    @Autowired
    private ItemDescMapper itemDescMapper;


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

        //编辑MP的分页对象 四个属性有用(页数/条数/总数/记录)  传递=页数/条数
        IPage<Item> page = new Page<>(pageResult.getPageNum(),pageResult.getPageSize());
        page = itemMapper.selectPage(page,queryWrapper);
        //获取总数
        long total = page.getTotal();
        //获取记录数
        List<Item> rows = page.getRecords();
        //将数据封装
        return pageResult.setTotal(total).setRows(rows);
    }

    @Override
    @Transactional
    public void saveItem(ItemVO itemVO) {
        Item item = itemVO.getItem(); //id=null
        item.setStatus(true);
        //要求item入库之后,动态返回Id!!!!
        //MP原则: 入库之后动态回显数据!!
        itemMapper.insert(item);
        //实现itemDesc对象入库
        ItemDesc itemDesc = itemVO.getItemDesc();
        itemDesc.setId(item.getId());
        itemDescMapper.insert(itemDesc);
    }
}
