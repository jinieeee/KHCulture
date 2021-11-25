package com.kh.khculture.board.model.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;

@Mapper
public interface BoardMapper {
	
	//총 게시물 수
	int getListCount();

	//페이징할 게시글 수
	List<Board> selectList(int startRow, int endRow);

	// 후기 작성
	int boardInsert(Board newBoard);
	
	// 후기 상세보기
	Board selectBoard(int b_no , int m_no);

	// 후기 좋아요를 눌렀는지 확인
	Lovit selectLovit(int b_no, int mno);

	// ??
	int insertlovit(Lovit lovit);

	// 후기 Lovit테이블에 추가
	int insertlovit(int mno, int bno);

	// 후기 Lovit테이블에 삭제
	int likedelete(int mno, int bno);
	
	// 랭킹 정보 가지고 오기
	List<Board> rankList();
	
	//후기 후정하기
	List<Board> updateBoardView(int b_no, int mno);


}
