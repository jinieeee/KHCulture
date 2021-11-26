package com.kh.khculture.management.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.lecture.model.vo.Instructor;
import com.kh.khculture.lecture.model.vo.Lecture;
import com.kh.khculture.management.model.dao.ManagementMapper;
import com.kh.khculture.management.model.vo.Lecture2;
import com.kh.khculture.management.model.vo.LectureOpen2;
import com.kh.khculture.management.model.vo.LectureOpen3;
import com.kh.khculture.management.model.vo.SearchInstructor;

@Service
public class ManagementServiceImpl implements ManagementService {
	
	private ManagementMapper managementMapper;
	
	@Autowired
	public ManagementServiceImpl(ManagementMapper managementMapper) {
		this.managementMapper = managementMapper;
	}
	
	@Override
	public int registLecture(Lecture lecture) {
		
		return managementMapper.registLecture(lecture);
	}

	@Override
	public int getListCount() {
		
		return managementMapper.getListCount();
	}

	@Override
	public List<Lecture2> selectLectureList(int startRow, int endRow) {
		
		return managementMapper.selectLectureList(startRow, endRow);
	}

	@Override
	public int deleteLecture(int lNo) {
		
		return managementMapper.deleteLecture(lNo);
	}

	@Override
	public Lecture selectLecture(int lNo) {
		
		return managementMapper.selectLecture(lNo);
	}

	@Override
	public int updateLecture(Lecture lecture) {
		
		return managementMapper.updateLecture(lecture);
	}

	@Override
	public List<Lecture> selectAllLectureList() {
		
		return managementMapper.selectAllLectureList();
	}

	@Override
	public List<Instructor> selectInstructor(SearchInstructor searchInstructor) {
		return managementMapper.selectInstructor(searchInstructor);
	}

	@Override
	public int registLectureOpen(LectureOpen2 lectureOpen) {

		return managementMapper.registLectureOpen(lectureOpen);
	}

	@Override
	public int getOpenListCount() {
		
		return managementMapper.getOpenListCount();
	}

	@Override
	public List<LectureOpen3> selectOpenList(int startRow, int endRow) {
		
		return managementMapper.selectOpenList(startRow, endRow);
	}

	@Override
	public LectureOpen2 selectOneOpen(int lrNo) {
		
		return managementMapper.selectOneOpen(lrNo);
	}

	@Override
	public int updateOpen(LectureOpen2 lectureOpen) {
		
		return managementMapper.updateOpen(lectureOpen);
	}

	@Override
	public int deleteProcedure(int lNo) {
		
		return managementMapper.deleteProcedure(lNo);
	}

	@Override
	public int deleteOpenProcedure(int lrNo) {
		
		return managementMapper.deleteOpenProcedure(lrNo);
	}

	@Override
	public int deleteOpen(int lrNo) {
		
		return managementMapper.deleteOpen(lrNo);
	}

	
}
