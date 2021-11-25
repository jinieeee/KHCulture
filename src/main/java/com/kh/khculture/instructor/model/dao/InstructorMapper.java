package com.kh.khculture.instructor.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.instructor.model.vo.Instructor;

@Mapper
public interface InstructorMapper {
	
	int instructorCount();
	
	List<Instructor> getList();

}
