package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileServiceImpl implements FileService{
    /**
     * 考虑的问题:
     *      1. 校验图片类型    xx.jpg
     *      2. 校验是否为恶意程序 xx.exe.jpg
     *      3. 将文件分目录存储.
     *      4. 为了保证图片唯一性 ,自定义文件名称
     * @param file
     * @return
     */
    @Override
    public ImageVO upload(MultipartFile file) {
        //xxxxxx.jpg|png|gif 防止大小写问题,将所有字母转化为小写
        String fileName = file.getOriginalFilename().toLowerCase();
        //利用正则判断是否为图片
        if(!fileName.matches("^.+\\.(jpg|png|gif)$")){
            //如果校验不通过,则终止程序
            return null;
        }
        System.out.println("图片类型正确的!!!!!!");
        return null;
    }
}
