package com.kh.khculture.notice.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.notice.model.vo.PageInfo;
import com.kh.khculture.notice.model.dao.NoticeMapper;
import com.kh.khculture.notice.model.vo.Notice;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	 
	private NoticeMapper noticeMapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper=noticeMapper;
	}

	@Override
	public Map<String, Object> noticeList(int page, String searchValue) { 
		Map<String,Object> returnMap = new HashMap<>();
		int listCount = noticeMapper.getListCount(searchValue);
		log.info("impl : {} ",listCount+"");
		
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		int startRow = (pi.getPage() - 1)*pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("searchValue", searchValue);
		log.info("noticeList :  {} ",map);
		List<Notice> noticeList = noticeMapper.selectList(map);
		
		
		
		returnMap.put("pi", pi);
		returnMap.put("noticeList", noticeList);
		return returnMap;
	}
	
	@Override
	public Notice selectNotice(int n_no) {
		return noticeMapper.selectNotice(n_no);
	}

	@Override
	public int noticeInsert(Notice notice) {
		return noticeMapper.noticeInsert(notice);
	}


	@Override
	public int noticeUpdate(Notice uptNotice) {
		return noticeMapper.noticeUpdate(uptNotice);
		
	}


	@Override
	public int deleteNotice(Notice deleteNotice) {
		return noticeMapper.noticeDelete(deleteNotice);
	}
	

	
	
}
