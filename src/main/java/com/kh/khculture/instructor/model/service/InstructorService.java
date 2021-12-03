package com.kh.khculture.instructor.model.service;

import java.util.Map;

import com.kh.khculture.instructor.model.vo.Instructor;

public interface InstructorService {
	
	Map<String, Object> getList(int page);
	
	Instructor selectInstructor(int instructor_no);
	
	int instructorRegist(Instructor instructor);
	
	int insturctorUpdate(Instructor updateInstructor);
	
	int deleteInstructor(Instructor deleteInstructor);

}
