package com.kh.khculture.board.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.Reply;
import com.kh.khculture.board.model.vo.Search;

@Mapper
public interface BoardMapper {
	/*
	//총 게시물 수
	int getListCount();
	
	//페이징할 게시글 수
	List<Board> selectList(@Param("startRow") int startRow, @Param("endRow") int endRow);
	*/	

	// 후기 작성
	int boardInsert(Board newBoard);
	
	// 후기 상세보기
	Board selectBoard(int b_no);

	// 후기 좋아요를 눌렀는지 확인
	int selectLovit(@Param("b_no") int b_no, @Param("mno") int mno);

	// 후기 Lovit테이블에 추가
	int insertlovit(Lovit lovit);

	// 후기 Lovit테이블에 추가
//	int insertlovit(int mno, int bno);

	// 후기 Lovit테이블에 삭제
	int likedelete(Lovit lovit);
	
	// 랭킹 정보 가지고 오기
//	List<Board> rankList();

	//후기 수정페이지에 기본적으로 넣어주는 값 select
	Board updateBoardView(int b_no);
	
	//후기 수정
	int boardUpdate(Board uptBoard);
	
	//후기 지우기
	int boardDelete(Board deleteBoard);

	//댓글쓰기
	int replyInsert(Reply r);

	//댓글조회
	List<Reply> selectReplyList(int b_no);
	
	//댓글 삭제
	int replyDelete(int rno);


	//****************************

/*
	List<Board> selectBoardList(Search search);

	Map<String, Object> getListCount2(String searchValue);

	
*/


	int getListCount(Search search);

	List<Board> selectList(Map<String, Object> map);

	List<Board> rankList();


	


	
	
	



}

