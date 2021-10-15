package com.jt.service;

import com.jt.vo.ImageVO;
import jdk.internal.org.objectweb.asm.tree.analysis.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@Service
public class FileServiceImpl implements FileService{

    private String localDir = "E:/images";
    private String urlPath = "http://image.jt.com";

    /**
     * 考虑的问题:
     *      1. 校验图片类型    xx.jpg
     *      2. 校验是否为恶意程序 xx.exe.jpg
     *      3. 将文件分目录存储.
     *      4. 为了保证图片唯一性 ,UUID
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

            //第四步: 使用uuid实现文件名称 uuid.jpg
            //4.1 生成UUID
            String uuid = UUID.randomUUID()
                              .toString().replace("-","");
            //截取文件类型
            int index = fileName.lastIndexOf(".");
            String fileType = fileName.substring(index);
            //生成新文件名称
            String newFile = uuid + fileType;

            //第五步:实现文件上传  全路径 再上传
            // E:/images/2021/10/15/uuid.jpg
            String path = dirPath + newFile;
            file.transferTo(new File(path));
            System.out.println("文件上传成功!!!!");

            //第六步: 返回ImageVO数据
            //6.1 虚拟路径只写动态变化的数据  /2021/11/11/uuid.jpg
            String virtualPath = dateDir + newFile;
            //6.2 真实图片名称
            String fileNameVO = newFile;
            //6.3 网络地址 http://image.jt.com/xx/uuid.jpg
            String url =  urlPath + virtualPath;
            System.out.println(url);
            return new ImageVO(virtualPath,url,fileNameVO);

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 1.准备全文件路径
     * 2.实现文件删除
     * @param virtualPath
     */
    @Override
    public void deleteFile(String virtualPath) {
        String path = localDir + virtualPath;
        File file = new File(path);
        if(file.exists()){
            //如果文件存在,则删除文件
            file.delete();
        }
    }
}
