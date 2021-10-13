package com.jt.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jt.pojo.ItemCat;

public interface ItemCatMapper extends BaseMapper<ItemCat> {
    //CURD操作如果没有特殊需求可以省略
    //如果没有sql的需求,则xml映射文件可以简化
}
