package com.atguigu.autoconfig.template;

import com.atguigu.autoconfig.properties.VodProperties;
import com.atguigu.ggkt.exceptionHandler.GgktException;
import com.atguigu.ggkt.utils.Signature;
import com.qcloud.vod.VodUploadClient;
import com.qcloud.vod.model.VodUploadRequest;
import com.qcloud.vod.model.VodUploadResponse;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.vod.v20180717.VodClient;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaRequest;
import com.tencentcloudapi.vod.v20180717.models.DeleteMediaResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.InputStream;
import java.util.Random;

/**
 * @Author: LeahAna
 * @Date: 2022/8/11 10:59
 * @Desc: 腾讯云点播
 */
@Slf4j
public class VodTemplate {

    private VodProperties properties;

    private VodUploadClient client;

    public VodTemplate(VodProperties properties) {
        this.properties = properties;
        try {
            client = new VodUploadClient(properties.getSecretId(), properties.getSecretKey());
        }catch (Exception e){
            e.printStackTrace();
            log.error("VodTemplate初始化失败");
        }
    }

    public String uploadVideo(InputStream inputStream, String originalFilename) {

        try {
            VodUploadClient client =
                    new VodUploadClient(properties.getSecretId(), properties.getSecretKey());
            VodUploadRequest request = new VodUploadRequest();

            //视频本地地址
            log.info("originalFilename={}", originalFilename);
            // todo： 腾讯云vod上传参数只能用本地文件名的方式？？？？？
            request.setMediaFilePath("/Users/anshengyo/Downloads/连锁攻击！！.MOV");
            //指定任务流
            request.setProcedure("LongVideoPreset");
            //调用上传方法，传入接入点地域及上传请求。
            VodUploadResponse response = client.upload(properties.getRegion(), request);
            //返回文件id保存到业务表，用于控制视频播放
            String fileId = response.getFileId();
            log.info("Upload FileId = {}" + response.getFileId());
            return fileId;
        } catch (Exception e) {
            log.info("Upload err={}", e.toString());
            throw new GgktException(20001, "上传视频失败");
        }
    }

    public void removeVideo(String videoSourceId) {
        try {
            // 实例化一个认证对象，入参需要传入腾讯云账户secretId，secretKey,此处还需注意密钥对的保密
            Credential cred =
                    new Credential(properties.getSecretId(), properties.getSecretKey());
            // 实例化要请求产品的client对象,clientProfile是可选的
            VodClient client = new VodClient(cred, "");
            // 实例化一个请求对象,每个接口都会对应一个request对象
            DeleteMediaRequest req = new DeleteMediaRequest();
            req.setFileId(videoSourceId);
            // 返回的resp是一个DeleteMediaResponse的实例，与请求对象对应
            DeleteMediaResponse resp = client.DeleteMedia(req);
            // 输出json格式的字符串回包
            log.info(DeleteMediaResponse.toJsonString(resp));
        } catch (TencentCloudSDKException e) {
            log.info("removeVideoErr={}", e.toString());
            throw new GgktException(20001, "删除视频失败");
        }
    }

    public String getSign() {
        Signature sign = new Signature();
        // 设置 App 的云 API 密钥
        sign.setSecretId(properties.getSecretId());
        sign.setSecretKey(properties.getSecretKey());
        sign.setCurrentTime(System.currentTimeMillis() / 1000);
        sign.setRandom(new Random().nextInt(java.lang.Integer.MAX_VALUE));
        sign.setSignValidDuration(3600 * 24 * 2); // 签名有效期：2天
        sign.setProcedure(properties.getProcedure());
        try {
            String signature = sign.getUploadSignature();
            log.info("signature : " + signature);
            return signature;
        } catch (Exception e) {
            log.error("获取签名失败");
            e.printStackTrace();
            throw new GgktException(20001, "获取签名失败");
        }
    }
}
