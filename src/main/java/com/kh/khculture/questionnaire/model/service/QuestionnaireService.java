package com.kh.khculture.questionnaire.model.service;

import java.util.List;

import com.kh.khculture.questionnaire.model.vo.Questionnaire;
import com.kh.khculture.questionnaire.model.vo.QuestionnaireAnswer;

public interface QuestionnaireService {
	
	List<Questionnaire> getList();
	
	Questionnaire questionnaireDetail(int questionnaire_no);
	
	int questionnaireInsert(Questionnaire questionnaire);
	
	int deleteQuestion(Questionnaire deleteQuestion);
	
	/* ========== 관리자 기능 ========== */
	
	int answerInsert(QuestionnaireAnswer answer);
	
	int answerDelete(QuestionnaireAnswer deleteAnswer);

}
