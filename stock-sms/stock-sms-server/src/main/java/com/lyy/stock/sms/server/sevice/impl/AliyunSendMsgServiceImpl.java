package com.lyy.stock.sms.server.sevice.impl;

import com.alibaba.fastjson2.JSONObject;
import com.aliyun.dysmsapi20170525.models.SendSmsRequest;
import com.aliyun.dysmsapi20170525.models.SendSmsResponse;
import com.aliyun.tea.TeaException;
import com.aliyun.teautil.models.RuntimeOptions;
import com.lyy.stock.sms.server.sevice.AliyunSendMsgBaseService;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.HashMap;

/**
 * @Author:
 * @createTime: 2023/04/26 17:16:09
 * @version:
 * @Description:
 */
@Service
public class AliyunSendMsgServiceImpl implements AliyunSendMsgBaseService {

    @Resource
    private com.aliyun.dysmsapi20170525.Client client;

    @Resource
    private com.aliyun.sdk.service.dysmsapi20170525.AsyncClient asyncClient;

    @Override
    public boolean syncSendMsg(String phoneNumber,String signName,String templateCode, String code,String outId) {
        HashMap<String, Object> templateMaps = new HashMap<>();
        templateMaps.put("code",code);

        SendSmsRequest sendSmsRequest = new SendSmsRequest()
                .setSignName(signName)
                .setTemplateCode(templateCode)
                .setPhoneNumbers(phoneNumber)
                .setTemplateParam(JSONObject.toJSONString(templateMaps));
        if(StringUtils.hasLength(outId))sendSmsRequest.setOutId(outId);
        RuntimeOptions runtime = new RuntimeOptions();
        try {
            // 复制代码运行请自行打印 API 的返回值
            SendSmsResponse sendSmsResponse = client.sendSmsWithOptions(sendSmsRequest, runtime);
            if(sendSmsResponse != null) {
                return "500".equals(sendSmsResponse.getBody().code);
            }
        } catch (TeaException error) {
            // 如有需要，请打印 error
            error.printStackTrace();
            com.aliyun.teautil.Common.assertAsString(error.message);
        } catch (Exception _error) {
            // 如有需要，请打印 error
            _error.printStackTrace();
            TeaException error = new TeaException(_error.getMessage(), _error);
            // 如有需要，请打印 error
            com.aliyun.teautil.Common.assertAsString(error.message);
        }
        return false;
    }

    @Override
    public void asyncSendMsg(String phoneNumber, String signName, String templateCode, String code, String outId) {
        HashMap<String, Object> templateMaps = new HashMap<>();
        templateMaps.put("code",code);
        com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest.Builder builder = com.aliyun.sdk.service.dysmsapi20170525.models.SendSmsRequest.builder()
                .signName(signName)
                .templateCode(templateCode)
                .phoneNumbers(phoneNumber)
                .templateParam(JSONObject.toJSONString(templateMaps));
        if(StringUtils.hasLength(outId))builder.outId(outId);
        // Asynchronously get the return value of the API request
        asyncClient.sendSms(builder.build());
    }


}

