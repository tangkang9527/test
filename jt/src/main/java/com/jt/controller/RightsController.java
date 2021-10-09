package com.jt.controller;

import com.jt.pojo.Rights;
import com.jt.service.RightsService;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/rights")
public class RightsController {

    @Autowired
    private RightsService rightsService;

    /**
     * 业务说明:  完成菜单列表查询 1-2级
     * URL: /rights/getRightsList
     * 参数: 没有参数
     * 返回值: SysResult(List)
     */
    @GetMapping("getRightsList")
    public SysResult getRightsList(){

        List<Rights> list = rightsService.getRightsList();
        return SysResult.success(list);
    }


}
