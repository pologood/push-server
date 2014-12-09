/**
 * 
 */
package com.easou.novelpush.constants;

/**
 * 推送消息类别
 * 
 * @author xiaodong
 * @date 2014年8月7日 上午11:00:20
 */
public enum MessageType {

	NOVEL_UPDATE(1), // 小说更新
	USER_MESSAGE(2), // 个人消息
	SYSTEM_MESSAGE(3);// 系统消息
	private int type;

	private MessageType(int type) {
		this.type = type;
	}

	public String toString() {
		return String.valueOf(type);
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
