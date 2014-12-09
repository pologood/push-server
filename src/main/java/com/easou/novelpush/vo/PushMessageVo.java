/**
 * 
 */
package com.easou.novelpush.vo;

import com.easou.novelpush.constants.MessageType;

/**
 * 推送消息参数类
 * 
 * @author xiaodong
 * @date 2014年8月6日 下午5:18:09
 */
public class PushMessageVo {
	/** 百度apikey */
	private String apiKey;
	/** 百度secretKey */
	private String secretKey;
	/** 百度channelId */
	private long channelId;
	/** 百度userId */
	private String baidu_userId;
	/** 推送消息：IOS与android数据格式不一致 */
	private String message;
	/** 1小说更新, 2个人消息,3 系统消息 */
	private MessageType type;

	public MessageType getType() {
		return type;
	}

	public void setType(MessageType type) {
		this.type = type;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public long getChannelId() {
		return channelId;
	}

	public void setChannelId(long channelId) {
		this.channelId = channelId;
	}

	public String getBaidu_userId() {
		return baidu_userId;
	}

	public void setBaidu_userId(String baidu_userId) {
		this.baidu_userId = baidu_userId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	@Override
	public String toString() {
		return "PushMessageVo [apiKey=" + apiKey + ", secretKey=" + secretKey + ", channelId=" + channelId
				+ ", baidu_userId=" + baidu_userId + ", message=" + message + "]";
	}

}
