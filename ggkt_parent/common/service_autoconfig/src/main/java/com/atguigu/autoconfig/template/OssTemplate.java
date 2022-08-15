package com.atguigu.autoconfig.template;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.atguigu.autoconfig.properties.OssProperties;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: leah_ana
 * @Date: 2022/4/9 17:13
 * @Desc: 阿里云Oss文件上传
 */

@Slf4j
public class OssTemplate {

private OSS ossClient;

    private  OssProperties properties;

    private String  bucketName;

    public OssTemplate(OssProperties properties) {

        try {
            this.properties = properties;
            // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
            String endpoint = properties.getEndpoint();
            // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
            String accessKeyId = properties.getAccessId();
            String accessKeySecret = properties.getAccessSecret();
            // 填写Bucket名称，例如examplebucket。
            this.bucketName = properties.getBucketName();
            // 创建OSSClient实例。
            ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        } catch (Exception e) {
            e.printStackTrace();
            log.error("OssTemplate初始化失败");
        }
    }

    /**
     * 文件上传
     * @param fileName    文件名
     * @param inputStream 输入流
     */
    public String upload(String fileName, InputStream inputStream) {
        log.info("fileName:{}",fileName);
        String url= "";
        String filepath="";

        try {
             filepath = new SimpleDateFormat("yyyy/MM/dd").format(new Date())
                    + "/" + UUID.randomUUID() + fileName.substring(fileName.lastIndexOf("."));
            // 创建PutObject请求。
            ossClient.putObject(bucketName, filepath, inputStream);
        } catch (Exception oe) {
            log.error("Error Message: " + oe.getMessage());
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
                url = properties.getUrl() + filepath;
            }
        }
        return url;
    }
}


