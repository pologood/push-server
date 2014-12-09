/**
 * 
 */
package com.easou.novelpush.thread;

import com.easou.novelpush.constants.BaiduAPPKey;
import com.easou.novelpush.constants.MessageType;
import com.easou.novelpush.vo.IosAps;
import com.easou.novelpush.vo.IosMessageVo;
import com.easou.novelpush.vo.PushMessageVo;

/**
 * @author ES-BJ-PC-142
 * @date 2014年8月7日 下午3:41:07
 */
public class IosPushNotificationThreadTest {

	/**
	 * 帐号1: userId=734792561357859579 channelId=4012246822552375661  udid=2D7A3FB2E2DE6DC5585D3E71FE80CB59 
	 * 帐号2: userId=712186047497800071  channelId=4003210711501183366 udid=605639784C8E05DF375BB577EC113BF6
	 */
	public static void main(String[] args) {
		//小说更新推送
		IosMessageVo mvo = new IosMessageVo(new IosAps().assmblyAlert("莽荒纪", "第三十二卷 易波界 第四章 逃"), MessageType.NOVEL_UPDATE.getType(), "6326585");
//		IosMessageVo mvo = new IosMessageVo(new IosAps("您的书单审核通过了"), MessageType.USER_MESSAGE.getType(), "");
		PushMessageVo vo = new PushMessageVo();
		vo.setApiKey(BaiduAPPKey.IOS_PAID.getApiKey());
		vo.setSecretKey(BaiduAPPKey.IOS_PAID.getSecretKey());
		vo.setChannelId(5213245445503993424L);
		vo.setBaidu_userId("1061298486329514505");
		vo.setMessage(mvo.toJsonString());
		System.out.println(mvo.toJsonString());
		IosPushNotificationThread runnable = new IosPushNotificationThread();
		runnable.setPushMessageVo(vo);
		Thread thread = new Thread(runnable);
		thread.start();
	}

}
