package com.kh.khculture.questionnaire.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kh.khculture.questionnaire.model.dao.QuestionnaireMapper;
import com.kh.khculture.questionnaire.model.vo.Questionnaire;
import com.kh.khculture.questionnaire.model.vo.QuestionnaireAnswer;

@Service("questionnaireService")
public class QuestionnaireServiceImpl implements QuestionnaireService {
	
	private QuestionnaireMapper questionnaireMapper;
	
	@Autowired
	public QuestionnaireServiceImpl(QuestionnaireMapper questionnaireMapper) {
		this.questionnaireMapper = questionnaireMapper;
	}

	@Override
	public List<Questionnaire> getList() {
		// TODO Auto-generated method stub
		return questionnaireMapper.getList();
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
