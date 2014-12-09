package com.easou.novelpush.service.basedata.impl;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.easou.novel.basedata.BaseDataService;
import com.easou.novel.basedata.dto.GNovelDto;
import com.easou.novel.basedata.exception.BaseDataServiceException;
import com.easou.novel.basedata.impl.BaseDataServiceImpl;
import com.easou.novelpush.service.basedata.BookBaseDataService;

@Service("bookBaseDataService")
public class BookBaseDataServiceImpl implements BookBaseDataService {
	private static Logger logger = Logger.getLogger(BookBaseDataServiceImpl.class);

	private static BaseDataService baseDataService = null;
	static {
		try {
			baseDataService = new BaseDataServiceImpl("conf/basedata/basedata-redis.properties");
		} catch (BaseDataServiceException e) {
			e.printStackTrace();
			logger.info("加载basedata redis 配置文件失败，conf/basedata/basedata-redis.properties ", e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.easou.novelweb.service.basedata.BookBaseDataService#getNovelDtoByNid
	 * (long)
	 */
	public GNovelDto getNovelDtoByNid(long gid) {
		GNovelDto dto = null;
		try {
			dto =  baseDataService.getGNovelDto(gid);
		} catch (BaseDataServiceException e) {
			logger.info("获取小说信息失败， nid:" + gid, e);
		}
		return dto;
	}

}
