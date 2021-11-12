package com.kh.khculture.book.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.kh.khculture.book.model.service.BookService;
import com.kh.khculture.book.model.vo.Book;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j  //lombok제공
//@RestController란
//@Controller + @ResponseBody의 의미로 모든 메소드에 @ResponseBody가 적용되므로 명시할 필요 없음
//RestController의 용도는 JSON/XML 형식으로 데이터를 반환하는 것
//@CrossOrigin("http://localhost:8001") //클래스 하위의 모든 요청에 대한 Origin 허용 처리
public class BookController {
	
	private BookService bookService;
	
	//@Slf4j : Lombok에서 제공하는 어노테이션으로 log라는 이름으로 필드 선언 제공함
//	private final Logger logger = LoggerFactory.getLogger(this.getClass()); //logger 객체 생성해야 하나 lombok 어노테이션 사용
	//this.getClass() : 클래스 설정 정보 읽어오기
	
	@Autowired
	public BookController(BookService bookService) {
		this.bookService = bookService;
	}
	
	//@RequestMapping(GET, POST, PUT, DELETE)의 값으로 {템플릿변수} 사용하여
	//@PathVariable 어노테이션이 적용 된 동일한 이름을 갖는 파라미터에 매핑
	//특정 메소드 범위 설정
	@CrossOrigin("http://localhost:8001")
	@GetMapping("/book/category/{category}")
	public List<Book> selectBookByCategory(@PathVariable String category) {
		
//		System.out.println("조회할 카테고리 : " + category); //리소스 많이 씀 
		
		//log level
		//1. error : 요청을 처리하는 중 오류가 발생
		//2. warn : 처리 가능한 문제, 향후 시스템 에러의 원인이 될 수 있는 경고성 메세지
		//3. info : 상태 변경과 같은 정보성 로그
		//4. debug : 프로그램을 디버깅하기 위한 정보
		//5. trace : 추적 레벨의 debug보다 훨씬 상세한 정보
		log.error("조회 요청 category : {}", category); 
		log.warn("조회 요청 category : {}", category); 
		log.info("조회 요청 category : {}", category); 
		log.debug("조회 요청 category : {}", category); 
		log.trace("조회 요청 category : {}", category);
		
		return bookService.selectBookByCategory(category);
	}
	
	@PostMapping("/book")
	public Map<String, String> insertBook(@RequestBody Book book){
		log.info("입력 요청 book : {}", book);
		
		String msg = bookService.insertBook(book) > 0 ? "도서 입력 성공" : "도서 입력 실패";
		Map<String, String> map = new HashMap<>();
		map.put("msg", msg);
		return map;
	}
	
	@GetMapping("/book/{id}")
	public Book selectBookByid(@PathVariable int id) {
		
		log.info("조회 요청 id : {}", id);
		
		Book book = bookService.selectBookByid(id);
		if (book == null) {
			book = new Book();
		}
		return book;
	}
	
	@PutMapping("/book/{id}")
	public Map<String, String> updateBook(@PathVariable int id, @RequestBody Book book) {
		
		log.info("수정 요청 id : {}", id);
		log.info("수정 요청 book : {}", book);
		
		book.setId(id);
		
		String msg = bookService.updateBook(book) > 0 ? "도서 정보 수정 성공" : "도서 정보 수정 실패";
		
		Map<String, String> map = new HashMap<>();
		map.put("msg", msg);
		
		return map;
	}
	
	@DeleteMapping("/book/{id}")
	public Map<String, String> deleteBook(@PathVariable int id) {
		log.info("삭제 요청 아이디 : ", id);
		
		String msg = bookService.deleteBook(id) > 0 ? "도서 삭제 성공" : "도서 삭제 실패";
		//로그로 삭제 요청 아이디 출력 후 DB에서 해당 행 삭제
		//도서 삭제 성공 or 도서 삭제 실패 msg Map 타입으로 리턴
		Map<String, String> map = new HashMap<>();
		map.put("msg", msg);
		
		return map;
	}
}
