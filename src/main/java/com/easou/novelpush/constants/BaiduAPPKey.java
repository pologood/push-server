/**
 * 
 */
package com.easou.novelpush.constants;

/**
 * 所有小说APP在百度平台的apikey和secretKey
 * 
 * @author xiaodong
 * @date 2014年8月7日 上午11:00:20
 */
public enum BaiduAPPKey {


	private String apiKey;
	private String secretKey;

	public String getApiKey() {
		return apiKey;
	}

	private BaiduAPPKey(String apiKey, String secretKey) {
		this.apiKey = apiKey;
		this.secretKey = secretKey;
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

}
