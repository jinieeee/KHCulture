package com.kh.khculture.instructor.model.vo;

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
public class Instructor {
	private int instructor_no;
	private String instructor_name;
	private String profile_photo;
	private String birth_date;
	private String instructor_phone;
	private String career;

}
