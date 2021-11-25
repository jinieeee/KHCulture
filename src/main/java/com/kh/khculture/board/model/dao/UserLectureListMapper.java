package com.kh.khculture.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.lecture.model.vo.LectureOpen;


@Mapper

//후기 작성할때 user가 듣고 있는 lecture의 List
public interface UserLectureListMapper {

	List<LectureOpen> userLectureList(int mno);
}
