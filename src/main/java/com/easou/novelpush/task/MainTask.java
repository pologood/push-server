package com.easou.novelpush.task;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.alibaba.fastjson.JSON;
import com.easou.novel.basedata.dto.GNovelDto;
import com.easou.novel.readpath.data.ReadingDto;
import com.easou.novelpush.cache.BookLocalCache;
import com.easou.novelpush.constants.BaiduAPPKey;
import com.easou.novelpush.constants.MessageType;
import com.easou.novelpush.constants.RedisKeyConstants;
import com.easou.novelpush.constants.VersionConstants;
import com.easou.novelpush.log.NovelUpdateLogUtil;
import com.easou.novelpush.service.PushNovelUpdateService;
import com.easou.novelpush.thread.AndroidPushMessageThread;
import com.easou.novelpush.thread.IosPushNotificationThread;
import com.easou.novelpush.util.EnviromentUtil;
import com.easou.novelpush.vo.AndroidMessageVo;
import com.easou.novelpush.vo.IosAps;
import com.easou.novelpush.vo.IosMessageVo;
import com.easou.novelpush.vo.PushMessageVo;
import com.easou.novelpush.vo.Pushparam;
import com.google.common.base.Strings;

/**
 * 
 * @author xiaodong
 * @date 2014年10月31日 下午3:39:03
 */
public class MainTask {
	public static final int threshold = 1000;
	@Autowired
	private PushNovelUpdateService pushNovelUpdateService;
	@Autowired
	private ThreadPoolTaskExecutor pushNovelUpdateExecutor;

	public void executeTask() {

		try {
			while (true) {
				isBlock(threshold);
				String baiduInfoStr = pushNovelUpdateService.rpop(RedisKeyConstants.BAIDUINFO2REDIS_KEY);
				if (Strings.isNullOrEmpty(baiduInfoStr)) {
					try {
						NovelUpdateLogUtil.infoLog("小说更新: baiduinfo redis队列中无数据，等待5s");
						TimeUnit.SECONDS.sleep(5);
						continue;
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
					
				}
				Pushparam pushparam = null;
				try {
					pushparam = JSON.parseObject(baiduInfoStr, Pushparam.class);
				} catch (Exception e1) {
					NovelUpdateLogUtil.errorLog("小说更新： 将baiduInfoStr json字符串转换为  Pushparam 出错. baiduInfoStr==="
							+ baiduInfoStr, e1);
				}
				if (pushparam != null) {
					long gid = pushparam.getGid();
					String gidStr = String.valueOf(gid);

					PushMessageVo vo = new PushMessageVo();
					vo.setChannelId(Long.parseLong(pushparam.getChannelId()));
					vo.setBaidu_userId(pushparam.getBaiduUserId());
					vo.setType(MessageType.NOVEL_UPDATE);
					// android小说更新
					if (pushparam.getOs().equals(VersionConstants.APP_OS_ANDROID)) {
						// 小说推送
						AndroidMessageVo mvo = new AndroidMessageVo(MessageType.NOVEL_UPDATE.getType(), gidStr);

						// 测试key
						BaiduAPPKey baiduAPPKey = BaiduAPPKey.ANDROID_TEST;
						if (EnviromentUtil.isOnline()) {
							// 线上key
							baiduAPPKey = BaiduAPPKey.ANDROID_ONLINE;
						}

						vo.setApiKey(baiduAPPKey.getApiKey());
						vo.setSecretKey(baiduAPPKey.getSecretKey());
						vo.setMessage(mvo.toJsonString());
						AndroidPushMessageThread runnable = new AndroidPushMessageThread();
						runnable.setPushMessageVo(vo);
						pushNovelUpdateExecutor.execute(runnable);

					}
					// IOS消息推送
					else {
						String novelName = null;
						String lastChapterName = null;
						String novel_key = BookLocalCache.assemblyNovelkey(gid);
						GNovelDto novel = BookLocalCache.novel_cache.get(novel_key);
						// bookBaseDataService.getNovelDtoByNid(gid);
						if (novel != null) {
							novelName = novel.getName();
							String last_chapter_key = BookLocalCache.assemblyLastChapterkey(gid);
							ReadingDto readingDto = BookLocalCache.best_lastchapter_cache.get(last_chapter_key);
							// bestReadPathService.getLastReading(gid);
							if (readingDto != null) {
								lastChapterName = readingDto.getCname();
							}
						} else {
							continue;
						}
						// IOS免费版 消息推送
						BaiduAPPKey baiduAPPKey = BaiduAPPKey.IOS_FREE;
						// IOS付费版 消息推送
						if (pushparam.getOs().equals((VersionConstants.APP_OS_IOS_PAID))) {
							baiduAPPKey = BaiduAPPKey.IOS_PAID;
						}

						// 小说更新推送
						IosMessageVo mvo = new IosMessageVo(new IosAps().assmblyAlert(novelName, lastChapterName),
								MessageType.NOVEL_UPDATE.getType(), gidStr);
						vo.setApiKey(baiduAPPKey.getApiKey());
						vo.setSecretKey(baiduAPPKey.getSecretKey());
						vo.setMessage(mvo.toJsonString());
						IosPushNotificationThread runnable = new IosPushNotificationThread();
						runnable.setPushMessageVo(vo);
						pushNovelUpdateExecutor.execute(runnable);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void isBlock(int threshold) {
		while (pushNovelUpdateExecutor.getThreadPoolExecutor().getQueue().size() > threshold) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
