package com.easou.novelpush.thread;

import com.baidu.yun.channel.auth.ChannelKeyPair;
import com.baidu.yun.channel.client.BaiduChannelClient;
import com.baidu.yun.channel.exception.ChannelClientException;
import com.baidu.yun.channel.exception.ChannelServerException;
import com.baidu.yun.channel.model.PushUnicastMessageRequest;
import com.baidu.yun.channel.model.PushUnicastMessageResponse;
import com.baidu.yun.core.log.YunLogEvent;
import com.baidu.yun.core.log.YunLogHandler;
import com.easou.novelpush.constants.MessageType;
import com.easou.novelpush.log.NovelUpdateLogUtil;
import com.easou.novelpush.log.UserMessageLogUtil;
import com.easou.novelpush.vo.PushMessageVo;

/**
 * android消息推送模式<br />
 * 
 * 针对单个用户： 小说更新和用户消息都是以消息模式推送
 * 
 * @author xiaodong
 * @date 2014年8月6日 下午4:49:51
 */
public class AndroidPushMessageThread implements Runnable {

	private PushMessageVo pushMessageVo;

	@Override
	public void run() {
		try {
			/*
			 * @brief 推送单播消息(消息类型为透传，由开发方应用自己来解析消息内容) message_type = 0 (默认为0)
			 */
			ChannelKeyPair pair = new ChannelKeyPair(pushMessageVo.getApiKey(), pushMessageVo.getSecretKey());
			// 2. 创建BaiduChannelClient对象实例
			BaiduChannelClient channelClient = new BaiduChannelClient(pair);
			// 3. 若要了解交互细节，请注册YunLogHandler类
			channelClient.setChannelLogHandler(new YunLogHandler() {
				@Override
				public void onHandle(YunLogEvent event) {
					// 打印到不同的日志文件中
					printLog(pushMessageVo.getType(), event.getMessage());
				}
			});
			// 4. 创建请求类对象
			// 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
			PushUnicastMessageRequest request = new PushUnicastMessageRequest();
			request.setDeviceType(3); // device_type => 1: web, 2: pc ,3:android
										// ,4:ios 5:wp
			request.setChannelId(pushMessageVo.getChannelId());
			request.setUserId(pushMessageVo.getBaidu_userId());
			// 若要通知，
			request.setMessageType(0);
			request.setMessage(pushMessageVo.getMessage());

			// 5. 调用pushMessage接口
			PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);
			int amount = response.getSuccessAmount();
			// 6. 认证推送成功
			// System.out.println("push amount : " + amount);
			if (amount == 1) {
				// 打印到不同的日志文件中
				printLog(pushMessageVo.getType(), "android推送消息成功. pushMessageVo===" + pushMessageVo);

			} else {
				// 打印到不同的日志文件中
				printLog(pushMessageVo.getType(), "android推送消息失败. pushMessageVo===" + pushMessageVo);
			}

		} catch (ChannelClientException e) {
			// 处理客户端错误异常
			e.printStackTrace();
		} catch (ChannelServerException e) {
			// 打印到不同的日志文件中
			if (pushMessageVo.getType() == MessageType.NOVEL_UPDATE) {
				NovelUpdateLogUtil.errorLog("android推送消息 出现异常, PushMessageVo :" + pushMessageVo);
				NovelUpdateLogUtil.errorLog(
						String.format("request_id: %d, error_code: %d, error_message: %s", e.getRequestId(),
								e.getErrorCode(), e.getErrorMsg()), e);
			} else if (pushMessageVo.getType() == MessageType.USER_MESSAGE) {
				UserMessageLogUtil.errorLog("android推送消息 出现异常, PushMessageVo :" + pushMessageVo);
				UserMessageLogUtil.errorLog(
						String.format("request_id: %d, error_code: %d, error_message: %s", e.getRequestId(),
								e.getErrorCode(), e.getErrorMsg()), e);
			}

			return;
		}
	}

	/**
	 * 打印到不同的日志文件中
	 * 
	 * @param type
	 * @param msg
	 */
	public void printLog(MessageType type, String msg) {
		// 打印到不同的日志文件中
		if (type == MessageType.NOVEL_UPDATE) {
			NovelUpdateLogUtil.infoLog(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "   "
					+ msg);
		} else if (type == MessageType.USER_MESSAGE) {
			UserMessageLogUtil.infoLog(Thread.currentThread().getName() + ":" + Thread.currentThread().getId() + "   "
					+ msg);
		}
	}

	public PushMessageVo getPushMessageVo() {
		return pushMessageVo;
	}

	public void setPushMessageVo(PushMessageVo pushMessageVo) {
		this.pushMessageVo = pushMessageVo;
	}

}
