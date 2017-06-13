package com.gs.service;

import com.gs.bean.MessageSend;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:08:49
*@des 短信发送记录表Service
*/
public interface MessageSendService extends BaseService<String, MessageSend>{

    public int insertRemind(MessageSend messageSend);
}