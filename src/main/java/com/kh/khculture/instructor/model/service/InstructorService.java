package com.kh.khculture.instructor.model.service;

import java.util.List;

import com.kh.khculture.instructor.model.vo.Instructor;

public interface InstructorService {
	
	int getListCount();
	
	List<Instructor> getList();

}
