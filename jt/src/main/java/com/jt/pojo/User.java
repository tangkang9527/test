package com.jt.pojo;

import lombok.Data;
import lombok.experimental.Accessors;
@Data
@Accessors(chain = true)
public class User extends BasePojo{
    private Integer id;
    private String username;
    private String password;
    private String phone;
    private String email;
    private Boolean status;
}
