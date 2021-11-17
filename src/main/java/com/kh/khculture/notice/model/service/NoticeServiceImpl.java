package com.kh.khculture.notice.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.notice.model.dao.NoticeMapper;
import com.kh.khculture.notice.model.vo.Notice;
import com.kh.khculture.notice.model.vo.PageInfo;

@Service("noticeService")
public class NoticeServiceImpl implements NoticeService {
	
	private NoticeMapper noticeMapper;

	@Autowired
	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		this.noticeMapper=noticeMapper;
	}
	/* No.1
	@Override
	public List<Notice> findAllNotice() {
		return noticeMapper.findAllNotice();
	}
	*/

	@Override
	public int getListCount() {
		return noticeMapper.getListCount();
	}

	@Override
	public List<Notice> selectList(int startRow, int endRow) {
		//System.out.println("service단 : "+noticeMapper.selectList(startRow, endRow));
		return noticeMapper.selectList(startRow, endRow);
	}

	@Override
	public Notice selectNotice(int n_no) {
		
		return noticeMapper.selectNotice(n_no);
	}

	@Override
	public Notice insertNotice() {
		return noticeMapper.insertNotice();
	}

	
}
