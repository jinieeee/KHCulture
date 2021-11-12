package com.kh.khculture.book.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.book.model.vo.Book;

@Mapper
public interface BookMapper {
	//전달 받은 카테고리에 해당하는 책 목록 리턴
		List<Book> selectBookByCategory(String category);
		
		//전달 된 book 정보 도서 입력
		int insertBook(Book book);
		
		//전달 받은 id에 해당하는 도서 정보 리턴
		Book selectBookByid(int id);
			
		//전달 받은 book 정보로 도서 수정
		int updateBook(Book book);
		
		//전달 받은 id에 해당하는 도서 정보 삭제
		int deleteBook(int id);
}
