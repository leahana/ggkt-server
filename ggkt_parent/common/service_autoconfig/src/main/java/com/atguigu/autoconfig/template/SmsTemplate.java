package com.atguigu.autoconfig.template;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.ObjectUtil;
import com.atguigu.autoconfig.properties.SmsProperties;
import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.models.*;
import com.aliyun.sdk.service.dysmsapi20170525.*;
import darabonba.core.client.ClientOverrideConfiguration;
import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.CompletableFuture;

/**
 * @Author: leah_ana
 * @Date: 2022/4/8 23:11
 * @Desc: 阿里云短信发送
 */

@Slf4j
public class SmsTemplate {

    private  SmsProperties properties;

    private AsyncClient client ;

    public SmsTemplate(SmsProperties properties) {
        try {
            this.properties = properties;
            // Configure Credentials authentication information, including ak, secret, token
            StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                    .accessKeyId(this.properties.getAccessId())
                    .accessKeySecret(properties.getAccessSecret())
                    //.securityToken(" <your-token>") // use STS token
                    .build());
            // Configure the Client
            log.info("【 StaticCredentialProvider.create successfully 】:{}", DateUtil.now());
            client = AsyncClient.builder()
                    .region(properties.getRegion()) // Region ID
                    //.httpClient(httpClient) // Use the configured HttpClient, otherwise use the default HttpClient (Apache HttpClient)
                    .credentialsProvider(provider)
                    //.serviceConfiguration(Configuration.create()) // Service-level configuration
                    // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                    .overrideConfiguration(
                            ClientOverrideConfiguration.create()
                                    .setEndpointOverride("dysmsapi.aliyuncs.com")
                            //.setReadTimeout(Duration.ofSeconds(30))
                    )
                    .build();
            log.info("【 AsyncClient.builder successfully 】:{}", DateUtil.now());

        } catch (Exception e) {
            e.printStackTrace();
            log.error("SmsTemplate初始化失败");
        }
    }

    public void sendSms(String phoneNumbers) {
        try {
            // Parameter settings for API request
            SendSmsRequest sendSmsRequest = SendSmsRequest.builder()
                    .signName("阿里云短信测试")
                    .templateCode("SMS_154950909")
                    .phoneNumbers(phoneNumbers)
                    .templateParam("{\"code\":\"1234\"}")
                    // Request-level configuration rewrite, can set Http request parameters, etc.
                    // .requestConfiguration(RequestConfiguration.create().setHttpHeaders(new HttpHeaders()))
                    .build();
            log.info("【 sendSmsRequest create successfully 】:{},【phone】:{}", DateUtil.now(), sendSmsRequest.getPhoneNumbers());
            // Asynchronously get the return value of the API request
            CompletableFuture<SendSmsResponse> response = client.sendSms(sendSmsRequest);
            // Synchronously get the return value of the API request
            SendSmsResponse resp = response.get();
            log.info("【 SendSmsResponse get successfully 】:{}", DateUtil.now());
            // Finally, close the client
        } catch (Exception ignored) {
            ignored.printStackTrace();
        } finally {
            if (ObjectUtil.isNotNull(client)) {
                client.close();
            }
        }
    }

}
