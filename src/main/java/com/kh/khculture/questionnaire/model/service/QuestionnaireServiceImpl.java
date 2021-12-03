package com.kh.khculture.questionnaire.model.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.common.PageInfo;
import com.kh.khculture.questionnaire.model.dao.QuestionnaireMapper;
import com.kh.khculture.questionnaire.model.vo.Questionnaire;
import com.kh.khculture.questionnaire.model.vo.QuestionnaireAnswer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {
	
	private QuestionnaireMapper questionnaireMapper;
	
	@Autowired
	public QuestionnaireServiceImpl(QuestionnaireMapper questionnaireMapper) {
		this.questionnaireMapper = questionnaireMapper;
	}

	@Override
	public Map<String, Object> getList(int page, String searchValue) {
		// TODO Auto-generated method stub
		Map<String, Object> returnMap = new HashMap<>();
		int listCount = questionnaireMapper.getListCount(searchValue);
		log.info("impl : {} ", listCount + "");
		
		PageInfo pi = new PageInfo(page, listCount, 10, 10);
		int startRow = (pi.getPage() - 1)*pi.getBoardLimit() + 1;
		int endRow = startRow + pi.getBoardLimit() - 1;
		
		Map<String, Object> map = new HashMap<>();
		map.put("startRow", startRow);
		map.put("endRow", endRow);
		map.put("searchValue", searchValue);
		log.info("getList : {}", map);
		List<Questionnaire> getList = questionnaireMapper.getList(map);
		
		returnMap.put("pi", pi);
		returnMap.put("getList", getList);
		return returnMap;
	}
	@Override
	public Questionnaire questionnaireDetail(int questionnaire_no) {
		// TODO Auto-generated method stub
		return questionnaireMapper.questionnaireDetail(questionnaire_no);
	}

	
	@Override
	public int questionnaireInsert(Questionnaire questionnaire) {
		// TODO Auto-generated method stub
		return questionnaireMapper.questionnaireInsert(questionnaire);
	}

	@Override
	public int deleteQuestion (Questionnaire deleteQuestion) {
		// TODO Auto-generated method stub
		return questionnaireMapper.questionnaireDelete(deleteQuestion);
	}
	
	
	/* ========== 관리자 기능 ========== */
	@Override
	public QuestionnaireAnswer answerDetail(int answer_no) {
		// TODO Auto-generated method stub
		return questionnaireMapper.answerDetail(answer_no);
	}

	@Override
	public int answerInsert(Questionnaire answer) {
		// TODO Auto-generated method stub
		return questionnaireMapper.answerInsert(answer);
	}

	@Override
	public int answerDelete(Questionnaire deleteAnswer) {
		// TODO Auto-generated method stub
		return questionnaireMapper.answerDelete(deleteAnswer);
	}
	
	

	
}
