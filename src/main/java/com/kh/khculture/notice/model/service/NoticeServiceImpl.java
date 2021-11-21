package com.kh.khculture.notice.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.notice.model.vo.PageInfo;
import com.kh.khculture.notice.model.dao.NoticeMapper;
import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.Search;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	private NoticeMapper noticeMapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper=noticeMapper;
	}
	@Override
	public int getListCount(String searchValue) {
		if(searchValue != null) {
			return noticeMapper.getcountentListCount(searchValue);
		}
		return noticeMapper.getListCount();
	}
	
	
	@Override
	public List<Notice> selectList(PageInfo pi,String searchValue) {
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		
		if(searchValue != null) {
			return noticeMapper.selectContentList(searchValue,startRow, endRow);
		}
		return noticeMapper.selectList(startRow, endRow );
		
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
