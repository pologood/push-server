/**
 * 
 */
package com.easou.novelpush.util;

import java.io.IOException;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

/**
 * @author xiaodong
 * @date 2014年8月25日 下午5:10:31
 */
public class EnviromentUtil {
	private static Logger logger = LoggerFactory.getLogger(EnviromentUtil.class);
	public static String PRODUCT_STATUS = "";

	private final static String ENVIROMENT_PATH = "enviroment.properties";

	static {
		load();
	}

	public static void load() {
		try {
			if (Strings.isNullOrEmpty(PRODUCT_STATUS)) {
				Properties resourceAsProperties = ResourcesUtils.getResourceAsProperties(ENVIROMENT_PATH);
				if (resourceAsProperties != null) {
					String proStr = resourceAsProperties.getProperty("product", "");
					if (!Strings.isNullOrEmpty(proStr)) {
						PRODUCT_STATUS = proStr;
					}
				}
			}

		} catch (IOException e) {
			logger.error("EnviromentUtil init Properties falied! ENVIROMENT_PATH=" + ENVIROMENT_PATH);
		}
	}

	public static boolean isOnline() {
		return PRODUCT_STATUS.equals("online");
	}

	public static void main(String[] args) throws InterruptedException {
		System.out.println(EnviromentUtil.isOnline());
		Thread.sleep(5000);
		System.out.println(EnviromentUtil.isOnline());
	}
}
