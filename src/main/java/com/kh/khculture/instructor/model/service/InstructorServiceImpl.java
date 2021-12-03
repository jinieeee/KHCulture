package com.kh.khculture.instructor.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.common.PageInfo;
import com.kh.khculture.instructor.model.dao.InstructorMapper;
import com.kh.khculture.instructor.model.vo.Instructor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("instructorService")
public class InstructorServiceImpl implements InstructorService{
	
	private InstructorMapper instructorMapper;
	
	@Autowired
	public InstructorServiceImpl(InstructorMapper instructorMapper) {
		this.instructorMapper = instructorMapper;
	}

	@Override
	public Map<String, Object> getList(int page) {
		Map<String, Object> returnMap = new HashMap<>();
		int listCount = instructorMapper.instructorCount();
		log.info("impl : {} ", listCount + "");
		
		PageInfo pi = new PageInfo(page, listCount, 10, 6);
		int startRow = (pi.getPage() - 1)*pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		log.info("instructor : {} ", map);
		List<Instructor> instructorList = instructorMapper.getList(map);
		
		
		returnMap.put("pi", pi);
		returnMap.put("instructorList", instructorList);
		return returnMap;
	}

	@Override
	public Instructor selectInstructor(int instructor_no) {
		// TODO Auto-generated method stub
		return instructorMapper.selectInstructor(instructor_no);
	}

	@Override
	public int instructorRegist(Instructor instructor) {
		// TODO Auto-generated method stub
		return instructorMapper.instructorRegist(instructor);
	}

	@Override
	public int insturctorUpdate(Instructor updateInstructor) {
		// TODO Auto-generated method stub
		return instructorMapper.instructorUpdate(updateInstructor);
	}

	@Override
	public int deleteInstructor(Instructor deleteInstructor) {
		// TODO Auto-generated method stub
		return instructorMapper.instructorDelete(deleteInstructor);
	}

	

}
