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
public class Questionnaire {
	private int questionnaire_no;
	private String questionnaire_title;
	private String questionnaire_content;
	private String q_enroll_date;
	private String q_status;
	private int m_no;

}
