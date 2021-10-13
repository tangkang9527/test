package com.jt.controller;

import com.jt.pojo.ItemCat;
import com.jt.service.ItemCatService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/itemCat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    /**
     * 需求: 查询3级分类数据信息
     * 类型： get
     * URL: /itemCat/findItemCatList/{level}
     * 参数: level
     * 返回值: SysResult(list)
     */
    @GetMapping("/findItemCatList/{level}")
    public SysResult findItemCatList(@PathVariable Integer level){

        List<ItemCat> list = itemCatService.findItemCatList(level);
        return SysResult.success(list);
    }

    /**
     * 实现商品分类新增操作
     * URL: /itemCat/saveItemCat
     * 参数:  this.itemCatForm  JSON
     * 返回值: SysResult对象
     */
    @PostMapping("/saveItemCat")
    public SysResult saveItemCat(@RequestBody ItemCat itemCat){

        itemCatService.saveItemCat(itemCat);
        return SysResult.success();
    }

    /**
     * 需求: 实现商品分类删除操作
     * 类型: delete
     * URL: /itemCat/deleteItemCat?id=xx&level=xx
     * 参数:  id/level
     * 返回值: SysResult
     */
    @DeleteMapping("/deleteItemCat")
    public SysResult deleteItemCat(ItemCat itemCat){

        itemCatService.deleteItemCat(itemCat);
        return SysResult.success();
    }
}
