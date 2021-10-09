package com.jt.mapper;

import com.jt.pojo.Rights;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface RightsMapper {

    List<Rights> getRightsList();

    List<Rights> getRightsList2();
}
