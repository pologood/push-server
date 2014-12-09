package com.easou.novelpush.service.readpath.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.easou.novel.readpath.data.ReadingDto;
import com.easou.novel.readpath.exception.ReadPathClientInitException;
import com.easou.novel.readpath.factory.ReadPathServiceException;
import com.easou.novel.readpath.service.ReadPathService;
import com.easou.novel.readpath.service.impl.ReadPathServiceImpl;
import com.easou.novelpush.service.readpath.BestReadPathService;

/**
 * 最佳路径
 * 
 * @author xiaodong
 * @date 2013-09-26 11:30
 */
@Service("bestReadPathService")
public class BestReadPathServiceImpl implements BestReadPathService {

	private static Logger logger = LoggerFactory.getLogger(BestReadPathServiceImpl.class);
	private static final String PATH = "conf/readpath/readpath-redis.properties";
	private static ReadPathService readPathService;

	static {
		try {
			readPathService = new ReadPathServiceImpl(PATH);
		} catch (ReadPathClientInitException e) {
			logger.info("ReadPathFacade create ReadPathServiceImpl Object is falied! resoure file path : " + PATH, e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.easou.novelweb.service.readpath.BestReadPathService#getLastReading
	 * (long)
	 */
	@Override
	public ReadingDto getLastReading(long gid) {
		try {
			return readPathService.getLastReading(gid);
		} catch (ReadPathServiceException e) {
			logger.info("ReadPathFacade ReadPathServiceImpl getLastReading is falied!  paramters: gid=" + gid, e);
		}
		return null;
	}

}
