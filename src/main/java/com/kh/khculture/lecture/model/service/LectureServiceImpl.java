package com.kh.khculture.lecture.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.common.PageInfo;
import com.kh.khculture.lecture.model.dao.LectureMapper;
import com.kh.khculture.lecture.model.vo.LectureOpen;
import com.kh.khculture.lecture.model.vo.Search;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LectureServiceImpl implements LectureService{

	private LectureMapper lectureMapper;
	
	@Autowired
	public LectureServiceImpl(LectureMapper lectureMapper) {
		this.lectureMapper = lectureMapper;
	}
	
	@Override
	public Map<String, Object> selectLectureList(Search search) {
		Map<String, Object> returnMap = new HashMap<>();
		PageInfo pi = null;
		if(search.getPage() != 0) {
			int listCount = lectureMapper.getListCount(search);
			pi = new PageInfo(search.getPage(), listCount, 10, 10);
			search.setStartRow((pi.getPage() - 1) * pi.getBoardLimit() + 1);
			search.setEndRow(search.getStartRow() + pi.getBoardLimit() - 1);
		}
		
		List<LectureOpen> lecturelist = lectureMapper.selectLectureList(search);
		if(search.getArrayStand().equals("time")) {
			List<LectureOpen> newlectureList = new ArrayList<>();
			for(LectureOpen lc : lecturelist) {
				if(lc.getLrStartDate().compareTo(lc.getLrEndDate()) == 0) {
					lc.setLrParDate(lc.getLrStartDate());
					newlectureList.add(lc);
				} else {
					Calendar cStartdt = Calendar.getInstance();
					Calendar cEnddt = Calendar.getInstance();
					cEnddt.setTime(lc.getLrEndDate());
					cStartdt.setTime(lc.getLrStartDate());
					while(cStartdt.getTime().compareTo(cEnddt.getTime()) <= 0) {
						int indexNum = lc.getLrStartTime().indexOf(':');
						int hour = Integer.parseInt(lc.getLrStartTime().substring(0, indexNum));
						int mi = Integer.parseInt(lc.getLrStartTime().substring(indexNum + 1));
						cStartdt.set(Calendar.HOUR_OF_DAY, hour);
						cStartdt.set(Calendar.MINUTE, mi);
						
						Date partDay = cStartdt.getTime();
						LectureOpen lt = new LectureOpen();
						lt.setLrNo(lc.getLrNo());
						lt.setLrCapacity(lc.getLrCapacity());
						lt.setLrCount(lc.getLrCount());
						lt.setLrFee(lc.getLrFee());
						lt.setLrStartDate(lc.getLrStartDate());
						lt.setLrEndDate(lc.getLrEndDate());
						lt.setLrParDate(partDay);
						lt.setLrNumber(lc.getLrNumber());
						lt.setLrDay(lc.getLrDay());
						lt.setLrStartTime(lc.getLrStartTime());
						lt.setLrEndTime(lc.getLrEndTime());
						lt.setLecture(lc.getLecture());
						lt.setInstructor(lc.getInstructor());
						newlectureList.add(lt);
						cStartdt.set(Calendar.HOUR_OF_DAY, 0);
						cStartdt.set(Calendar.MINUTE, 0);
						cStartdt.add(Calendar.DATE, 7);
					}
				} 
			}
		
			Collections.sort(newlectureList);
			returnMap.put("newlectureList", newlectureList);
			return returnMap;
		} else {
			returnMap.put("pi", pi);
			returnMap.put("lecturelist", lecturelist);
			return returnMap;
		}
	}

	@Override
	public List<Integer> findYear() {
		return lectureMapper.findYear();
	}

	
	


}
