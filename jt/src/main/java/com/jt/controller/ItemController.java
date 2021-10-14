package com.jt.controller;

import com.jt.service.ItemService;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    /**
     * 业务: 实现商品列表分页展现
     * 类型: get
     * url: /item/getItemList?query=&pageNum=1&pageSize=10
     * 参数: pageResult
     * 返回值: SysResult(pageResult)
     */
    @GetMapping("/getItemList")
    public SysResult getItemList(PageResult pageResult){//3

        pageResult = itemService.getItemList(pageResult);
        return SysResult.success(pageResult);
    }


}
