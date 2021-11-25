package com.kh.khculture.board.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.board.model.dao.BoardMapper;
import com.kh.khculture.board.model.dao.UserLectureListMapper;
import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.lecture.model.vo.LectureOpen;

@Service("boardService")
public class BoardServiceImpl implements BoardService {
	
	private BoardMapper boardMapper;
	private UserLectureListMapper userLectureListMapper;
	
	@Autowired
	public BoardServiceImpl(BoardMapper boardMapper , UserLectureListMapper userLectureListMapper) {
		this.boardMapper = boardMapper;
		this.userLectureListMapper = userLectureListMapper;
	}
	
	//총 게시물 수
	@Override
	public int getListCount() {
		return boardMapper.getListCount();
	}

	@Override
	public int getContentListCount() {
		return 0;
	}
	
	//페이징할 게시글 수
	@Override
	public List<Board> selectList(PageInfo pi) {
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
		return boardMapper.selectList(startRow,endRow);
	}
	
	//후기 작성할때 user가 듣고 있는 lecture의 List
	@Override
	public List<LectureOpen> userLectureList(int mno) {
		return userLectureListMapper.userLectureList(mno);
	}
	
	// 후기 작성
	@Override
	public int boardInsert(Board newBoard) {
		return boardMapper.boardInsert(newBoard);
		
	}
	
	// 후기 상세보기
	@Override
	public Board selectBoard(int b_no,int mno) {
		return boardMapper.selectBoard(b_no,mno);
	}
	
	// 후기 좋아요를 눌렀는지 확인
	@Override
	public Lovit selectLovit(int b_no, int mno) {
		return boardMapper.selectLovit(b_no,mno);
	}
	
	// ??
	@Override
	public int likeInsert(Lovit lovit) {
		return boardMapper.insertlovit(lovit);
	}
	
	// 후기 Lovit테이블에 추가
	@Override
	public int likeInsert(int mno, int bno) {
		return boardMapper.insertlovit(mno,bno);
	}
	
	// 후기 Lovit테이블에 삭제
	@Override
	public int likedelete(int mno, int bno) {
		return boardMapper.likedelete(mno,bno);
	}
	
	// 랭킹 정보 가지고 오기
	@Override
	public List<Board> ranktList() {
		return boardMapper.rankList();
	}
	
	//후기 수정하기
	@Override
	public List<Board> boardLectureList(int b_no, int mno) {
		return boardMapper.updateBoardView(b_no,mno);
	}

}
