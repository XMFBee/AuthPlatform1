package com.gs.dao;

import com.gs.bean.MessageSend;
import com.gs.bean.User;
import com.gs.common.bean.Pager;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
*由CSWangBin技术支持
*
*@author CSWangBin
*@since 2017-04-17 16:08:48
*@des 短信发送记录表dao
*/
@Repository
public interface MessageSendDAO extends BaseDAO<String, MessageSend>{

    public int insertRemind(MessageSend messageSend);

    public List<MessageSend> blurredQuery(@Param("pager") Pager pager, @Param("messageSend") MessageSend messageSend);

    public int countByBlurred(@Param("messageSend") MessageSend messageSend, @Param("user") User user);
}