package com.kh.khculture.lecture.model.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.lecture.model.dao.LectureMapper;
import com.kh.khculture.lecture.model.vo.LectureOpen;

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
	public List<LectureOpen> selectLectureList() {
		List<LectureOpen> lecturelist = lectureMapper.selectLectureList();
		List<LectureOpen> newlectureList = new ArrayList<>();
		for(LectureOpen lc : lecturelist) {
			if(lc.getLrStartDate() == lc.getLrEndDate()) {
				lc.setLrParDate(lc.getLrStartDate());
				newlectureList.add(lc);
			} else {
				Calendar cStartdt = Calendar.getInstance();
				Calendar cEnddt = Calendar.getInstance();
				cEnddt.setTime(lc.getLrEndDate());
				cStartdt.setTime(lc.getLrStartDate());
				while(cStartdt.getTime().compareTo(cEnddt.getTime()) <= 0) {
					Date partDay = cStartdt.getTime();
					log.info("partDay{}", partDay);
					lc.setLrParDate(partDay);
					LectureOpen lt = new LectureOpen();
					lt.setLrNo(lc.getLrNo());
					lt.setLrCapacity(lc.getLrCapacity());
					lt.setLrCount(lc.getLrCount());
					lt.setLrFee(lc.getLrFee());
					lt.setLrStartDate(lc.getLrStartDate());
					lt.setLrEndDate(lc.getLrEndDate());
					lt.setLrParDate(lc.getLrParDate());
					lt.setLrNumber(lc.getLrNumber());
					lt.setLrDay(lc.getLrDay());
					lt.setLrStartTime(lc.getLrStartTime());
					lt.setLrEndTime(lc.getLrEndTime());
					lt.setLecture(lc.getLecture());
					lt.setInstructor(lc.getInstructor());
					newlectureList.add(lt);
					cStartdt.add(Calendar.DATE, 7);
				}
			}
		}
		return newlectureList;
	}
	


}
