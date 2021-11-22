package com.kh.khculture.management.model.service;

import java.util.List;

import com.kh.khculture.lecture.model.vo.Instructor;
import com.kh.khculture.lecture.model.vo.Lecture;
import com.kh.khculture.management.model.vo.Lecture2;
import com.kh.khculture.management.model.vo.LectureOpen2;
import com.kh.khculture.management.model.vo.LectureOpen3;
import com.kh.khculture.management.model.vo.SearchInstructor;

public interface ManagementService {
	
	int registLecture(Lecture lecture);

	int getListCount();

	List<Lecture2> selectLectureList(int startRow, int endRow);

	int deleteLecture(int lNo);

	Lecture selectLecture(int lNo);

	int updateLecture(Lecture lecture);

	List<Lecture> selectAllLectureList();

	List<Instructor> selectInstructor(SearchInstructor searchInstructor);

	int registLectureOpen(LectureOpen2 lectureOpen);

	int getOpenListCount();

	List<LectureOpen3> selectOpenList(int startRow, int endRow);
}
