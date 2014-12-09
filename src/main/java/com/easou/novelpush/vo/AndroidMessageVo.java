/**
 * 
 */
package com.easou.novelpush.vo;

import com.alibaba.fastjson.JSON;

/**
 * @author xiaodong
 * @date 2014年8月7日 上午10:57:15
 */
public class AndroidMessageVo {
	/** 推送消息类别  1:小说更新, 2: 用户消息, 3: 系统消息*/
	private int type;
	/**消息内容*/
	private String message;

	public AndroidMessageVo() {
	}

	public AndroidMessageVo(int type, String message) {
		this.type = type;
		this.message = message;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String toJsonString(){
		return  JSON.toJSONString(this);
	}
	@Override
	public String toString() {
		return "MessageVo [type=" + type + ", message=" + message + "]";
	}
	
	

}
