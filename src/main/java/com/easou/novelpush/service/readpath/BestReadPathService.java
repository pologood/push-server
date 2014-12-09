package com.easou.novelpush.service.readpath;

import com.easou.novel.readpath.data.ReadingDto;

public interface BestReadPathService {

	/**
	 * 获取该书的最后章节信息，返回novelid,sort,cname,type,gSort
	 * 
	 * @param gid
	 * @return
	 */
	public abstract ReadingDto getLastReading(long gid);

}