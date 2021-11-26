package com.kh.khculture.questionnaire.model.vo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class QuestionnaireAnswer {
	
	private int answer_no;
	private String enroll_answer;
	private String a_enroll_date;
	private String a_status;
	private int questionnaire_no;
	private int m_no;

}
