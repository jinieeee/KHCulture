package com.kh.khculture.instructor.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.instructor.model.vo.Instructor;

@Mapper
public interface InstructorMapper {
	
	int instructorCount();
	
	List<Instructor> getList();
	
	Instructor selectInstructor(int instructor_no);
	
	int instructorRegist(Instructor instructor);
	
	int instructorUpdate(Instructor updateInstructor);
	
	int instructorDelete(Instructor deleteInstructor);

}
