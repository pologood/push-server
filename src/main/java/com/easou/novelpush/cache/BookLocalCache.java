/**
 * 
 */
package com.easou.novelpush.cache;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easou.novel.basedata.dto.GNovelDto;
import com.easou.novel.readpath.data.ReadingDto;
import com.easou.novelpush.service.basedata.BookBaseDataService;
import com.easou.novelpush.service.basedata.impl.BookBaseDataServiceImpl;
import com.easou.novelpush.service.readpath.BestReadPathService;
import com.easou.novelpush.service.readpath.impl.BestReadPathServiceImpl;
import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;

/**
 * @author xiaodong
 * @date 2014年11月3日 下午3:47:23
 */
public class BookLocalCache {
	private final static Logger logger = LoggerFactory.getLogger(BookLocalCache.class);
	// @Autowired
	private static BookBaseDataService bookBaseDataService;
	// @Autowired
	private static BestReadPathService bestReadPathService;

	static {
		if (bookBaseDataService == null) {
			bookBaseDataService = new BookBaseDataServiceImpl();
		}

		if (bestReadPathService == null) {
			bestReadPathService = new BestReadPathServiceImpl();
		}
	}

	/**
	 * 本地缓存
	 * 
	 * @param cacheLoader
	 * @return
	 */
	public static <K, V> LoadingCache<K, V> cacheMaker(long duration, TimeUnit unit, CacheLoader<K, V> cacheLoader) {
		LoadingCache<K, V> cache = CacheBuilder.newBuilder().weakKeys().softValues()
				.refreshAfterWrite(24, TimeUnit.HOURS).expireAfterWrite(duration, unit).build(cacheLoader);
		return cache;
	}

	/**
	 * 小说基本信息
	 * 
	 */
	public static LoadingCache<String, GNovelDto> novel_cache = cacheMaker(1, TimeUnit.HOURS,
			new CacheLoader<String, GNovelDto>() {
				@Override
				public GNovelDto load(String key) throws Exception {
					GNovelDto novel = null;
					try {
						long gid = Long.valueOf(key.substring(key.indexOf("_") + 1));
						novel = bookBaseDataService.getNovelDtoByNid(gid);
					} catch (Exception e) {
						logger.error("缓存：  获取小说基本信息失败--> .  key:" + key);
					}
					return novel;
				}
			});

	/**
	 * 小说最后章节信息
	 * 
	 */
	public static LoadingCache<String, ReadingDto> best_lastchapter_cache = cacheMaker(5, TimeUnit.MINUTES,
			new CacheLoader<String, ReadingDto>() {
				@Override
				public ReadingDto load(String key) throws Exception {
					ReadingDto readingDto = null;
					try {
						long gid = Long.valueOf(key.substring(key.indexOf("_") + 1));
						readingDto = bestReadPathService.getLastReading(gid);
					} catch (Exception e) {
						logger.error("缓存：  获取小说最后章节信息失败--> .  key:" + key);
					}
					return readingDto;
				}
			});

	/**
	 * 组拼小说基本信息key
	 * 
	 * @param gid
	 * @return
	 */
	public static String assemblyNovelkey(long gid) {
		return "novel_" + gid;
	}

	/**
	 * 组拼小说最后章节信息key
	 * 
	 * @param gid
	 * @return
	 */
	public static String assemblyLastChapterkey(long gid) {
		return "lastchapter_" + gid;
	}
}
