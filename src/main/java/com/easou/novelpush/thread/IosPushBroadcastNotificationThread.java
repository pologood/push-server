package com.easou.novelpush.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushBroadcastMessageRequest;
import com.baidu.yun.channel.model.PushBroadcastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.easou.novelpush.vo.PushMessageVo;

/**
 * IOS推送广播消息<br />
 * 
 * 针对所有用户
 * 
 * @author xiaodong
 * @date 2014年8月6日 下午4:49:51
 */
public class IosPushBroadcastNotificationThread implements Runnable {

	public static Logger logger = LoggerFactory.getLogger(IosPushBroadcastNotificationThread.class);

	private PushMessageVo pushMessageVo;

	@Override
	public void run() {
		/**
		 * @brief 推送广播消息(IOS APNS) message_type = 1 (默认为0)
		 */

		// 1. 设置developer平台的ApiKey/SecretKey
		ChannelKeyPair pair = new ChannelKeyPair(pushMessageVo.getApiKey(), pushMessageVo.getSecretKey());

		// 2. 创建BaiduChannelClient对象实例
		BaiduChannelClient channelClient = new BaiduChannelClient(pair);

		// 3. 若要了解交互细节，请注册YunLogHandler类
		channelClient.setChannelLogHandler(new YunLogHandler() {
			@Override
			public void onHandle(YunLogEvent event) {
				System.out.println(event.getMessage());
			}
		});

		try {

			// 4. 创建请求类对象
			PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
			request.setDeviceType(4); // device_type => 1: web 2: pc 3:android
										// 4:ios 5:wp
			request.setMessageType(1);
			// TODO 注意测试和线上参数修改
			request.setDeployStatus(2); // DeployStatus => 1: Developer 2:
										// Production
			request.setMessage(pushMessageVo.getMessage());

			// 5. 调用pushMessage接口
			PushBroadcastMessageResponse response = channelClient.pushBroadcastMessage(request);

			int amount = response.getSuccessAmount();
			// 6. 认证推送成功
			System.out.println("push amount : " + amount);
			if (amount == 1) {
				logger.info("IOS推送消息成功. pushMessageVo===" + pushMessageVo);
			} else {
				logger.info("IOS推送消息失败. pushMessageVo===" + pushMessageVo);
			}
		} catch (ChannelClientException e) {
			// 处理客户端错误异常
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 处理服务端错误异常
			System.out.println(String.format("request_id: %d, error_code: %d, error_message: %s", e.getRequestId(),
					e.getErrorCode(), e.getErrorMsg()));
		}

	}

	public PushMessageVo getPushMessageVo() {
		return pushMessageVo;
	}

	public void setPushMessageVo(PushMessageVo pushMessageVo) {
		this.pushMessageVo = pushMessageVo;
	}

}
