package com.jt.service;

import com.jt.mapper.RightsMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RightsServiceImpl implements RightsService{

    @Autowired
    private RightsMapper rightsMapper;



}
