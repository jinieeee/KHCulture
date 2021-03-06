package com.kh.khculture.questionnaire.model.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.kh.khculture.questionnaire.model.vo.Questionnaire;
import com.kh.khculture.questionnaire.model.vo.QuestionnaireAnswer;

@Mapper
public interface QuestionnaireMapper {
	
	int getListCount(String searchValue);
	
	List<Questionnaire> getList(Map<String, Object> map);  // 1:1문의 리스트
	
	Questionnaire questionnaireDetail(int questionnaire_no);  // 1:1 문의 세부내용
	
	int questionnaireInsert(Questionnaire questionnaire);  // 문의 작성
	
	int questionnaireDelete(Questionnaire deleteQuestion);  // 문의 삭제
	
	/* ========== 관리자 기능 ========== */
	QuestionnaireAnswer answerDetail(int answer_no);
	
	int answerInsert(Questionnaire answer);   // 답변 작성
	
	int answerDelete(Questionnaire deleteAnswer);

}
