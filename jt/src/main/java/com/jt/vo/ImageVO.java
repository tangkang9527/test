package com.jt.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
public class ImageVO {
    private String virtualPath; //图片实际目录
    private String urlPath;     //请求路径
    private String fileName;    //图片名称
}
