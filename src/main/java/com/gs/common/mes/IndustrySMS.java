package com.gs.common.mes;

import com.gs.common.mes.Config;
import com.gs.common.mes.HttpUtil;

/**
 * 验证码通知短信接口
 * 
 * @ClassName: IndustrySMS
 * @Description: 验证码通知短信接口
 *
 */
public class IndustrySMS
{
	private static String operation = "/industrySMS/sendSMS";

	private static String accountSid = Config.ACCOUNT_SID;
	private String to;
	private String smsContent;

	public IndustrySMS(String to, String smsContent) {
		this.to = to;
		this.smsContent = smsContent;
	}
	/**
	 * 验证码通知短信
	 */
	public void execute()
	{
		String url = Config.BASE_URL + operation;
		String body = "accountSid=" + accountSid + "&to=" + to + "&smsContent=" + smsContent
				+ HttpUtil.createCommonParam();

		// 提交请求
		String result = HttpUtil.post(url, body);
		System.out.println("result:" + System.lineSeparator() + result);
	}
}
