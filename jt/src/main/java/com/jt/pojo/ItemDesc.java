package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author 刘昱江
 * 时间 2021/4/7
 */
@Data
@Accessors(chain = true)
public class ItemDesc extends BasePojo{

    private Integer id;
    private String itemDesc;

}
