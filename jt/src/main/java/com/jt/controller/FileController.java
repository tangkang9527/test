package com.jt.controller;

import com.jt.vo.SysResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    /**
     * 需求: 实现文件上传
     * URL地址: "http://localhost:8091/file/upload",
     * 参数: file=xxxx
     * 类型: POST请求
     * 返回值: SysResult(ImageVO)
     * 基础知识:
     *      inputStream,outputStream
     * 高级API: SpringMVC MultipartFile
     * 需求: 接收用户信息,保存到本地磁盘中
     * 控制图片大小: 默认大小1M
     */
    @PostMapping("/upload")
    public SysResult upload(MultipartFile file) throws IOException {
        //1.获取图片的名称
        String fileName = file.getOriginalFilename();
        //2.封装文件上传目录
        String fileDir = "E:/images";
        //3.检查目录是否存在
        File dir = new File(fileDir);
        if(!dir.exists()){//如果目录不存在
            //如果目录不存在,则创建目录
            dir.mkdirs();
        }
        //4.封装文件的全路径
        String path = fileDir + "/" +fileName;
        //5.上传文件
        file.transferTo(new File(path));
        return SysResult.success();
    }
}
