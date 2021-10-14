package com.jt.controller;

import com.jt.service.ItemService;
import com.jt.vo.ItemVO;
import com.jt.vo.PageResult;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    /**
     * 需求: 实现商品新增
     * URL: http://localhost:8091/item/saveItem
     * 参数: {item:this.addItemForm,itemDesc:this.itemDesc }
     * 接收数据: ItemVO
     * 返回值: SysResult对象
     */
    @PostMapping("/saveItem")
    public SysResult saveItem(@RequestBody ItemVO itemVO){
        itemService.saveItem(itemVO);
        return SysResult.success();
    }
}
