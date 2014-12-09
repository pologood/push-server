/**
 * 
 */
package com.easou.novelpush.vo;

/**
 * @author xiaodong
 * @date 2014年8月7日 下午3:53:34
 */
public class IosAps {
	private String alert;

	public IosAps() {
	};

	public IosAps(String alert) {
		this.alert = alert;
	}

	public IosAps assmblyAlert(String novelName, String lastChpaterName) {
		this.setAlert("《" + novelName + "》 有更新了  " + lastChpaterName);
		return this;
	}

	public String getAlert() {
		return alert;
	}

	public void setAlert(String alert) {
		this.alert = alert;
	}
}
