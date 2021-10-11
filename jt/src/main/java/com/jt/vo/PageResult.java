package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class PageResult {
    private String query;       //查询数据
    private Integer pageNum;    //页数
    private Integer pageSize;   //条数
    private Long total;         //总数
    private Object rows;        //查询的结果
}
