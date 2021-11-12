package com.kh.khculture.book.model.vo;

import lombok.Data;

// Lombok
// VO/DTO 클래스의 constructor, getter/setter, toString 등을 어노테이션을 통해 작성해주는 라이브러리
// 필드 수정 후 해당 코드에 대한 수정 작업이 필요 없음
// [의존성 추가 후 라이브러리 설치]
// 해당 jar 파일이 위치해있는 폴더에서 cmd 창으로 java -jar lombok-버젼.jar 실행
// sts.exe 선택해서 install and update 후 sts 재부팅
// 구조와 무관하게 남용 유의

//@NoArgsConstructor //기본생성자
//@AllArgsConstructor //매개변수 생성자
//@Getter
//@Setter
//@ToString
//@EqualsAndHashCode
@Data //매개변수 생성자 제외하고 생성
public class Book {
	private int id;
	private String title;
	private String author;
	private String publisher;
	private String category;	
}
