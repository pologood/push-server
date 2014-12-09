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

	ANDROID_TEST("z7p4M4mGq4dEvGaPIMF3oM9I", "Wvmq3jz7OVItaey8XyrjMQ3og7ejAck9"), // android测试
	ANDROID_ONLINE("SOniIoTofV3ezn5B1Vmwvgcd", "DQOu0usGCNlqHssjfyobFeSUYCWqlVof"), // //
																					// android线上
	// 注意: ios区分线上和测试是根据开发证书和发布证书来控制的
	IOS_FREE("ScYDgNGNXDAG7i94ZEYvNC6n", "clTt9uAEki7UIocKgQCRh3iIbGhCqnp5"), // Ios免费版本测试和线上
	IOS_PAID("lGs7pmUPcUXNNR6ahjuaiXgL", "65vF8SN34tkGiImtnT5aUtrGY2HXnaSB");// Ios收费版本测试和线上

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
