package com.easou.novelpush.thread;

import com.alibaba.fastjson.JSON;
import com.easou.novelpush.constants.BaiduAPPKey;
import com.easou.novelpush.constants.MessageType;
import com.easou.novelpush.vo.AndroidMessageVo;
import com.easou.novelpush.vo.PushMessageVo;

/**
 * 小说更新推送通知
 * 
 * @author xiaodong
 * @date 2014年8月7日 上午11:16:43
 */
public class AndroidPushMessageThreadTest {

	/**
	 * 帐号1: userId=734792561357859579 channelId=4012246822552375661  udid=2D7A3FB2E2DE6DC5585D3E71FE80CB59 
	 * 帐号2: userId=712186047497800071  channelId=4003210711501183366 udid=605639784C8E05DF375BB577EC113BF6
	 */
	public static void main(String[] args) {
		//小说推送
//		MessageVo mvo = new MessageVo(MessageType.NOVEL_UPDATE.getType(), "6326585");
		//个人消息
		AndroidMessageVo mvo = new AndroidMessageVo(MessageType.USER_MESSAGE.getType(), "您有一条消息");
		PushMessageVo vo = new PushMessageVo();
		vo.setApiKey("z7p4M4mGq4dEvGaPIMF3oM9I");
		vo.setSecretKey("Wvmq3jz7OVItaey8XyrjMQ3og7ejAck9");
		vo.setChannelId(4369957451118072359L);
		vo.setBaidu_userId("912198034294663091");
		vo.setMessage(JSON.toJSONString(mvo));
		AndroidPushMessageThread runnable = new AndroidPushMessageThread();
		runnable.setPushMessageVo(vo);
		Thread thread = new Thread(runnable);
		thread.start();
	}
	
}
