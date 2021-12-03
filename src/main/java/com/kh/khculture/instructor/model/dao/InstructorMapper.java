package com.kh.khculture.instructor.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.instructor.model.vo.Instructor;

@Mapper
public interface InstructorMapper {
	
	int instructorCount();
	
	List<Instructor> getList(Map<String, Object> map);
	
	Instructor selectInstructor(int instructor_no);
	
	int instructorRegist(Instructor instructor);
	
	int instructorUpdate(Instructor updateInstructor);
	
	int instructorDelete(Instructor deleteInstructor);

}
