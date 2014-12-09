package com.easou.novelpush.service.basedata;

import com.easou.novel.basedata.dto.GNovelDto;

public interface BookBaseDataService {

	/**
	 * 根据gid获取小说信息
	 * 
	 * @param gid
	 * @return
	 */
	public GNovelDto getNovelDtoByNid(long gid);

}