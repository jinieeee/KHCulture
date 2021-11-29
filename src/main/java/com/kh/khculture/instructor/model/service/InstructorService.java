package com.kh.khculture.instructor.model.service;

import java.util.List;

import com.kh.khculture.instructor.model.vo.Instructor;

public interface InstructorService {
	
	int getListCount();
	
	List<Instructor> getList();
	
	Instructor selectInstructor(int instructor_no);
	
	int instructorRegist(Instructor instructor);
	
	int insturctorUpdate(Instructor updateInstructor);
	
	int deleteInstructor(Instructor deleteInstructor);

}
