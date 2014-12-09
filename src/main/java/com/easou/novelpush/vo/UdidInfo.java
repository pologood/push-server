/**
 * 
 */
package com.easou.novelpush.vo;

/**
 * @author xiaodong
 * @date 2014年8月11日 上午11:49:24
 */
public class UdidInfo {

	private String id;
	private String info;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	@Override
	public String toString() {
		return "UdidInfo [id=" + id + ", info=" + info + "]";
	}

}
