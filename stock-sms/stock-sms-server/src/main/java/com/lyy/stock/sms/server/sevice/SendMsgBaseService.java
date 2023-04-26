package com.lyy.stock.sms.server.sevice;

public interface SendMsgBaseService {
    /**
     *
     * 同步
     * @param phoneNumber 接收短信的手机号码
     * @param signName 短信签名名称
     * @param templateCode 短信模板CODE
     * @param code 短信模板变量对应的实际值
     * @param outId 外部流水扩展字段
     * @return
     */
    boolean syncSendMsg(String phoneNumber,String signName,String templateCode,
                        String code,String outId);

    /**
     *
     * 异步
     * @param phoneNumber 接收短信的手机号码
     * @param signName 短信签名名称
     * @param templateCode 短信模板CODE
     * @param code 短信模板变量对应的实际值
     * @param outId 外部流水扩展字段
     * @return
     */
    void asyncSendMsg(String phoneNumber,String signName,String templateCode,
                      String code,String outId);


}
