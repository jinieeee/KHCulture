package com.kh.khculture.board.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.board.model.dao.BoardMapper;
import com.kh.khculture.board.model.dao.UserLectureListMapper;
import com.kh.khculture.board.model.vo.Board;
import com.kh.khculture.board.model.vo.Lovit;
import com.kh.khculture.board.model.vo.PageInfo;
import com.kh.khculture.board.model.vo.Reply;
import com.kh.khculture.board.model.vo.Search;
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

	//페이징할 게시글 수
	@Override
	public List<Board> selectList(PageInfo pi) {
		int startRow = (pi.getPage() - 1) * pi.getBoardLimit() +1;
		int endRow = startRow + pi.getBoardLimit() -1;
//		System.out.println("starRow : "+startRow);
//		Map<String, Integer> map = new HashMap<>();
//		map.put("startRow", startRow);
//		map.put("endRow", endRow);
		
		return boardMapper.selectList(startRow, endRow);
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
	public Board selectBoard(int b_no) {
		return boardMapper.selectBoard(b_no);
	}
	
	// 후기 좋아요를 눌렀는지 확인
	@Override
	public int selectLovit(int b_no, int mno) {
		
		return boardMapper.selectLovit(b_no, mno);
	}
	
	// 후기 Lovit테이블에 추가
	@Override
	public int likeInsert(Lovit lovit) {
		return boardMapper.insertlovit(lovit);
	}
	

	// 후기 Lovit테이블에 삭제
	@Override
	public int likedelete(Lovit lovit) {
		return boardMapper.likedelete(lovit);
	}
	
	// 랭킹 정보 가지고 오기
	@Override
	public List<Board> ranktList() {
		return boardMapper.rankList();
	}
	
	//후기 수정페이지에 기본적으로 넣어주는 값 select
	@Override
	public Board updateBoardView(int b_no) {
		return boardMapper.updateBoardView(b_no);
	}
	
	//후기  수정
	@Override
	public int boardUpdate(Board uptBoard) {
		return boardMapper.boardUpdate(uptBoard);
	}

	//후기 지우기 
	@Override
	public int boardDelete(Board deleteBoard) {
		return boardMapper.boardDelete(deleteBoard);
	}

	//댓글 쓰기
	@Override
	public int replyInsert(Reply r) {
		return boardMapper.replyInsert(r);
	}
	
	//댓글 조회
	@Override
	public List<Reply> selectReplyList(int b_no) {
		return boardMapper.selectReplyList(b_no);
	}
	
	//댓글 삭제
	@Override
	public int replyDelete(int rno) {
		System.out.println("serviceImplt = " + rno );
		return boardMapper.replyDelete(rno);
	}

/*	//********************

	@Override
	public Map<String, Object> searchBoardList(Search search) {
		Map<String, Object> returnMap = new HashMap<>();
		PageInfo pi = null;
		if(search.getSearchCondition() != null && search.getSearchValue() != null) {
			if(search.getSearchCondition().equals("title")) {
				return boardMapper.getListCount2(search.getSearchValue());
				
			}else if(search.getSearchCondition().equals("content")){
				return boardMapper.getListCount2(search.getSearchValue());
			}
			
			
			
			int listCount = boardMapper.getListCount2(pi , search);
			pi = new PageInfo(search.getPage(),listCount,10,10);
			search.setStartRow((pi.getPage() - 1) * pi.getBoardLimit() + 1);
			search.setEndRow(search.getStartRow() + pi.getBoardLimit() - 1);
			
		}
		
		
		List<Board> boardList = boardMapper.selectBoardList(search);
		
		returnMap.put("pi",pi);
		returnMap.put("boardList", boardList);
		
		
		return returnMap;
	}

	
	*/
	
	
	
	


}
