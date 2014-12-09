package com.easou.novelpush.vo;

import com.alibaba.fastjson.JSON;

/**
 * IOS推送服务类
 * 
 * @author xiaodong
 * @date 2014年8月7日 上午10:57:15
 */
public class IosMessageVo {
	/** 展示内容 */
	private IosAps aps;
	/** 推送消息类别 1:小说更新, 2: 用户消息, 3: 系统消息 */
	private int type;

	private String gid;

	public IosMessageVo() {
	}

	public IosMessageVo(IosAps aps, int type, String gid) {
		this.aps = aps;
		this.type = type;
		this.gid = gid;
	}

	public String getGid() {
		return gid;
	}

	public void setGid(String gid) {
		this.gid = gid;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public IosAps getAps() {
		return aps;
	}

	public void setAps(IosAps aps) {
		this.aps = aps;
	}

	public String toJsonString() {
		return JSON.toJSONString(this);
	}

	@Override
	public String toString() {
		return "IOSMessageVo [aps=" + aps + "]";
	}

}
