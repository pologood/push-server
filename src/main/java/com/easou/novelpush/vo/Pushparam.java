/**
 * 
 */
package com.easou.novelpush.vo;

/**
 * @author xiaodong
 * @date 2014年10月30日 下午4:47:23
 */
public class Pushparam {
	private long gid;
	private String os;
	private String channelId;
	private String baiduUserId;

	public Pushparam() {
	};

	public Pushparam(long gid, String os, String channelId, String baiduUserId) {
		this.gid = gid;
		this.os = os;
		this.channelId = channelId;
		this.baiduUserId = baiduUserId;
	}

	public long getGid() {
		return gid;
	}

	public void setGid(long gid) {
		this.gid = gid;
	}

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
		return "Pushparam [gid=" + gid + ", os=" + os + ", channelId=" + channelId + ", baiduUserId=" + baiduUserId
				+ "]";
	}

}
