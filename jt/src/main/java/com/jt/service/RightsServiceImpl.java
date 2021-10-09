package com.jt.service;

import com.jt.mapper.RightsMapper;
import com.jt.pojo.Rights;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RightsServiceImpl implements RightsService{

    @Autowired
    private RightsMapper rightsMapper;


    @Override
    public List<Rights> getRightsList() {
        //利用左连接的方式获取数据
        //return rightsMapper.getRightsList();

        //利用子查询的方式获取数据
        return rightsMapper.getRightsList2();
    }
}
