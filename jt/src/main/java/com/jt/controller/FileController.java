package com.jt.controller;

import com.jt.service.FileService;
import com.jt.vo.ImageVO;
import com.jt.vo.SysResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@CrossOrigin
@RequestMapping("/file")
public class FileController {

    @Autowired
    private FileService fileService;

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
    public SysResult upload2(MultipartFile file){

        ImageVO imageVO = fileService.upload(file);
        if(imageVO == null){
            return SysResult.fail();
        }
        return SysResult.success(imageVO);
    }

    /**
     * 需求: 删除图片信息
     * url: http://localhost:8091/file/deleteFile
     * 类型: delete
     * 参数: virtualPath
     * 返回值: SysResult对象
     */
    @DeleteMapping("/deleteFile")
    public SysResult deleteFile(String virtualPath){

        fileService.deleteFile(virtualPath);
        return SysResult.success();
    }









    //@PostMapping("/upload")
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
