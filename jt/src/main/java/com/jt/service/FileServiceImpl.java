package com.jt.service;

import com.jt.vo.ImageVO;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class FileServiceImpl implements FileService{

    private String localDir = "E:/images";

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
        //第二步 防止恶意程序 判断图片是否有宽度和高度
        try {
            BufferedImage bufferedImage =
                    ImageIO.read(file.getInputStream());
            int width = bufferedImage.getWidth();
            int height = bufferedImage.getHeight();
            if(width == 0 || height == 0){
                return null;
            }
            System.out.println("用户上传的是图片");

            //第三步: 目录如何划分 yyyy/MM/dd
            String dateDir = new SimpleDateFormat("/yyyy/MM/dd/")
                            .format(new Date());
            // E:/images + /2022/11/11/  拼接目录
            String dirPath = localDir + dateDir;
            File dirFile = new File(dirPath);
            if(!dirFile.exists()){
                //如果目录不存在时, 创建目录
                dirFile.mkdirs();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }


        return null;
    }
}
