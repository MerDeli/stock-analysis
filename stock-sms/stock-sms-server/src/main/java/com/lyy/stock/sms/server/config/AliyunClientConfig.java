package com.lyy.stock.sms.server.config;

import com.aliyun.auth.credentials.Credential;
import com.aliyun.auth.credentials.provider.StaticCredentialProvider;
import com.aliyun.sdk.service.dysmsapi20170525.AsyncClient;
import com.aliyun.teaopenapi.models.Config;
import darabonba.core.client.ClientOverrideConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @Author:
 * @createTime: 2023/04/26 17:11:40
 * @version:
 * @Description:
 */
@Configuration
public class AliyunClientConfig {
    @Resource
    private AliyunSendNoteConfig aliyunSendNoteConfig;
    /**
     * 使用AK&SK初始化账号Client 同步
     * 发送短信后立马知道结果
     * @return Client
     * @throws Exception
     */
    @Bean
    public  com.aliyun.dysmsapi20170525.Client createClient() throws Exception {
        Config config = new Config()
                // 您的 AccessKey ID
                .setAccessKeyId(aliyunSendNoteConfig.getAccessKeyId())
                // 您的 AccessKey Secret
                .setAccessKeySecret(aliyunSendNoteConfig.getAccessKeySecret());
        // 访问的域名
        config.endpoint = aliyunSendNoteConfig.getEndpoint();
        return new com.aliyun.dysmsapi20170525.Client(config);
    }
    /**
     * 使用AK&SK初始化账号Client 异步
     * 发送短信后，不需要知道结果，当然也可以通过Futur.get来获取结果,可设置超时时间。
     * @return Client
     * @throws Exception
     */
    @Bean
    public AsyncClient createAsyncClient() throws Exception {
        StaticCredentialProvider provider = StaticCredentialProvider.create(Credential.builder()
                .accessKeyId(aliyunSendNoteConfig.getAccessKeyId())
                .accessKeySecret(aliyunSendNoteConfig.getAccessKeySecret())
                //.securityToken("<your-token>") // use STS token
                .build());
        return   AsyncClient.builder()
                .region(aliyunSendNoteConfig.getRegion())
                .credentialsProvider(provider)
                //.serviceConfiguration(Configuration.create()) // Service-level configuration
                // Client-level configuration rewrite, can set Endpoint, Http request parameters, etc.
                .overrideConfiguration(
                        ClientOverrideConfiguration.create()
                                .setEndpointOverride(aliyunSendNoteConfig.getEndpoint())
                        //.setReadTimeout(Duration.ofSeconds(30))
                )
                .build();
    }

}
