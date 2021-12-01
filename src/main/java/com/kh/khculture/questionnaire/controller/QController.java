package com.kh.khculture.questionnaire.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.khculture.questionnaire.model.service.QuestionnaireService;
import com.kh.khculture.questionnaire.model.vo.Questionnaire;

@Controller
@RequestMapping("/questionnaire")
public class QController {
	
	private QuestionnaireService questionnaireService;
	
	@Autowired
	public QController(QuestionnaireService questionnaireService) {
		this.questionnaireService = questionnaireService;
	}
	
	@GetMapping("list")
	public String getList(Model model) {
		
		List<Questionnaire> questionnaireList = questionnaireService.getList();
		System.out.println("questionnaireList : " + questionnaireList);
		model.addAttribute("questionnaireList", questionnaireList);
		
		return "questionnaire/list";
	}
	
	@GetMapping("detail.do")
	public ModelAndView questionnaireDetail(ModelAndView mav, @RequestParam int questionnaire_no) {
		
		Questionnaire q = questionnaireService.questionnaireDetail(questionnaire_no);
		System.out.println("q : " + q);
		mav.addObject("questionnaire", q);
		mav.setViewName("questionnaire/detail");
		
		return mav;
	}
	
	@GetMapping("insert")
	public String questionnaireInsertForm(Model model) {
		return "questionnaire/insert";
	}
	@PostMapping("insert")
	public String questionnaireInsert(Questionnaire question) {
		System.out.println("question : " +  question);
		questionnaireService.questionnaireInsert(question);
		return "redirect:/questionnaire/list";
	}
	
	@PostMapping("delete")
	public String questionnaireDelete(Questionnaire deleteQuestionnaire, @RequestParam("questionnaire_no") int questionnaire_no) {
		
		questionnaireService.deleteQuestion(deleteQuestionnaire);
		
		return "redirect:/questionnaire/list";
	}
	
	/* ========== 관리자 기능 ========== */
	@GetMapping("answerPage")
	public String answerPage(Model model, @RequestParam("questionnaire_no") int questionnaire_no) {
		System.out.println("questionnaire_no : " + questionnaire_no);
		Questionnaire q = questionnaireService.questionnaireDetail(questionnaire_no);
		model.addAttribute("questionnaire", q);
		return "questionnaire/answer";
	}
	@PostMapping("answer")
	public String answer(Questionnaire answer) {
		questionnaireService.answerInsert(answer);
		System.out.println("answer : " + answer);
		return "redirect:/questionnaire/detail.do?questionnaire_no="+ answer.getQuestionnaire_no();
	}
	
	@PostMapping("answerDelete")
	public String answerDelete(Questionnaire deleteAnswer, @RequestParam("questionnaire_no") int questionnaire_no) {
		questionnaireService.answerDelete(deleteAnswer);
		return "redirect:/questionnaire/detail.do?questionnaire_no="+ deleteAnswer.getQuestionnaire_no();
	}
	
	

}
