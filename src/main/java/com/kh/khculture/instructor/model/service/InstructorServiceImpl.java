package com.kh.khculture.instructor.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.instructor.model.dao.InstructorMapper;
import com.kh.khculture.instructor.model.vo.Instructor;

@Service("instructorService")
public class InstructorServiceImpl implements InstructorService{
	
	private InstructorMapper instructorMapper;
	
	@Autowired
	public InstructorServiceImpl(InstructorMapper instructorMapper) {
		this.instructorMapper = instructorMapper;
	}

	@Override
	public int getListCount() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Instructor> getList() {
		// TODO Auto-generated method stub
		return instructorMapper.getList();
	}

	

}
