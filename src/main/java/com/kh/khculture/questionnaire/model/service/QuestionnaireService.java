package com.kh.khculture.questionnaire.model.service;

import java.util.Map;

import com.kh.khculture.questionnaire.model.vo.Questionnaire;
import com.kh.khculture.questionnaire.model.vo.QuestionnaireAnswer;

public interface QuestionnaireService {
	
	Map<String, Object> getList(int page, String searchValue);
	
	Questionnaire questionnaireDetail(int questionnaire_no);
	
	int questionnaireInsert(Questionnaire questionnaire);
	
	int deleteQuestion(Questionnaire deleteQuestion);
	
	/* ========== 관리자 기능 ========== */
	QuestionnaireAnswer answerDetail(int answer_no);
	
	int answerInsert(Questionnaire answer);
	
	int answerDelete(Questionnaire deleteAnswer);

}
