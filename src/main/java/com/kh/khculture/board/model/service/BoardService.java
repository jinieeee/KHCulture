package com.kh.khculture.board.model.service;

import java.util.List;

import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.Receipt;
import com.kh.khculture.lecture.model.vo.LectureOpen;

public interface BoardService {

	//총 게시물 수
	int getListCount();
	// ?
	int getContentListCount();
	//페이징할 게시글 수
	List<Board> selectList(PageInfo pi);
	//후기 작성할때 user가 듣고 있는 lecture의 List
	List<LectureOpen> userLectureList(int mno);
	// 후기 작성
	int boardInsert(Board newBoard);
	// 후기 상세보기
	Board selectBoard(int b_no, int mno);
	// 후기 좋아요를 눌렀는지 확인
	Lovit selectLovit(int b_no, int mno); //// 좋아요 눌렸는가?
	// ???
	int likeInsert(Lovit lovit);
	// 후기 Lovit테이블에 추가
	int likeInsert(int mno, int bno);
	// 후기 Lovit테이블에 삭제
	int likedelete(int mno, int bno);
	// 랭킹 정보 가지고 오기
	List<Board> ranktList();
	// 후기 수정하기 
	List<Board> boardLectureList(int b_no, int mno);
	
	
	
	

}
