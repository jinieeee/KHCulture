package com.kh.khculture.board.model.service;

import java.util.List;
import java.util.Map;

import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
import com.kh.khculture.board.model.vo.Reply;
import com.kh.khculture.board.model.vo.Search;
import com.kh.khculture.lecture.model.vo.LectureOpen;

public interface BoardService {
	
	Map<String, Object> boardList(int page, Search search);

	
	//후기 작성할때 user가 듣고 있는 lecture의 List
	List<LectureOpen> userLectureList(int mno);
	// 후기 작성
	int boardInsert(Board newBoard);
	// 후기 상세보기
	Board selectBoard(int b_no);
	// 후기 좋아요를 눌렀는지 확인
	int selectLovit(int b_no, int mno); //// 좋아요 눌렸는가?
	// 후기 Lovit테이블에 추가
	int likeInsert(Lovit lovit);
	// 후기 Lovit테이블에 삭제
	int likedelete(Lovit lovit);
	// 랭킹 정보 가지고 오기
	//후기 수정페이지에 기본적으로 넣어주는 값 select
	Board updateBoardView(int b_no);
	//후기 수정
	int boardUpdate(Board uptBoard);
	//후기 지우기
	int boardDelete(Board deleteBoard);
	// 댓글쓰기
	int replyInsert(Reply r);
	// 댓글 조회
	List<Reply> selectReplyList(int b_no);
	// 댓글 삭제
	int replyDelete(int rno);
	
	//*************************************************************
	







	
	
	
	
	
	

}
