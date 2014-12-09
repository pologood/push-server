/**
 * 
 */
package com.easou.novelpush.vo;

/**
 * @author xiaodongs
 * @date 2014年8月11日 上午11:52:38
 */
public class BaiduInfo {
	private String os;
	private String channelId;
	private String baiduUserId;

	public String getOs() {
		return os;
	}

	public void setOs(String os) {
		this.os = os;
	}

	public String getChannelId() {
		return channelId;
	}

	public void setChannelId(String channelId) {
		this.channelId = channelId;
	}

	public String getBaiduUserId() {
		return baiduUserId;
	}

	public void setBaiduUserId(String baiduUserId) {
		this.baiduUserId = baiduUserId;
	}

	@Override
	public String toString() {
		return "BaiduInfo [os=" + os + ", channelId=" + channelId + ", baiduUserId=" + baiduUserId + "]";
	}

}
