/**
 * 
 */
package com.easou.novelpush.log;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author xiaodong
 * @date 2014年8月11日 下午2:57:36
 */
public class UserMessageLogUtil {
	private static final Logger log = LoggerFactory.getLogger(UserMessageLogUtil.class);

	/**
	 * 输出日志
	 * 
	 * @param msg
	 */
	public static void infoLog(String msg) {
		log.info("{} ", msg);
	}

	/**
	 * 输出日志
	 * 
	 * @param msg
	 */
	public static void errorLog(String msg, Throwable e) {
		log.error(msg, e);
	}

	/**
	 * 输出日志
	 * 
	 * @param msg
	 */
	public static void errorLog(String msg) {
		log.error(msg);
	}
}
