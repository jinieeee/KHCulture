package com.kh.khculture.book.model.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.khculture.book.model.dao.BookMapper;
import com.kh.khculture.book.model.vo.Book;

@Service
public class BookServiceImpl implements BookService{
	private BookMapper bookMapper;
	
	public BookServiceImpl(BookMapper bookMapper) {
		this.bookMapper = bookMapper;
	}
	@Override
	public List<Book> selectBookByCategory(String category) {
		return bookMapper.selectBookByCategory(category);
	}

	@Override
	public int insertBook(Book book) {
		return bookMapper.insertBook(book);
	}

	@Override
	public Book selectBookByid(int id) {
		return bookMapper.selectBookByid(id);
	}

	@Override
	public int updateBook(Book book) {
		return bookMapper.updateBook(book);
	}

	@Override
	public int deleteBook(int id) {
		return bookMapper.deleteBook(id);
	}

}
