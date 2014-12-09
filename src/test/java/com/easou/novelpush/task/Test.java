/**
 * 
 */
package com.easou.novelpush.task;

import java.util.concurrent.ExecutionException;

import com.easou.novelpush.cache.BookLocalCache;

/**
 * @author ES-BJ-PC-142
 * @date 2014年11月3日 下午4:01:50
 */
public class Test {

	/**
	 * @param args
	 * @throws ExecutionException
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub

		// String key = "novel_1122";
		// System.out.println(Long.valueOf(key.substring(key.indexOf("_") +
		// 1)));
		long gid = 5655930L;
		String key = BookLocalCache.assemblyNovelkey(gid);
		String key2 = BookLocalCache.assemblyLastChapterkey(gid);
		BookLocalCache.novel_cache.get(key);

		BookLocalCache.best_lastchapter_cache.get(key2);

		Thread.sleep(2000);

		BookLocalCache.novel_cache.get(key);

		BookLocalCache.best_lastchapter_cache.get(key2);
	}

}
