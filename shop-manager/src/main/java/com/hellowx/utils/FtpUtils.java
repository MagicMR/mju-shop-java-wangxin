package com.hellowx.utils;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;

import java.io.FileInputStream;

/**
 * Created by IntelliJ IDEA.
 *
 * @author MagicMushroom
 * @date 2021/5/24
 */
public class FtpUtils {
    /**
     * ftp服务器IP
     */
    public static final String FTP_IP ="39.103.187.89";
    /**
     * 图片默认存储路径
     */
    public static final String BASE_IMG_PATH ="http://39.103.187.89:8080/images/";

    public static void uploadImage(FileInputStream inputStream,String pathName) throws Exception{
        //创建一个FtpClient对象
        FTPClient ftpClient = new FTPClient();
        //创建ftp连接，默认是：21端口
        ftpClient.connect(FTP_IP,21);
        //登录ftp服务器，使用用户名 和密码
        ftpClient.login("ftpuser", "root");
        //上传文件

        //读取本地文件
//        FileInputStream inputStream = new FileInputStream(
//                new File("C:\\Users\\27250\\Desktop\\02.png"));
        //修改上传文件的格式
        ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
        //第一个参数：服务器端文档名
        //第二个参数：上传文档的inputStream
        ftpClient.storeFile(pathName, inputStream);
        //关闭连接
        ftpClient.logout();

    }
}

