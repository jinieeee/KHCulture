package com.kh.khculture.instructor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.kh.khculture.instructor.model.service.InstructorService;
import com.kh.khculture.instructor.model.vo.Instructor;

@Controller
@RequestMapping("/instructor")
public class InstructorController {
	
	private InstructorService instructorService;
	
	@Autowired
	public InstructorController(InstructorService instructorService) {
		this.instructorService = instructorService;
	}
	
	@GetMapping("/list")
	public String getList(Model model) {
		
		List<Instructor> instructorList = instructorService.getList();
		
		model.addAttribute("instructorList", instructorList);
		
		return "instructor/list";
	}
	
	@GetMapping("detail.do")
	public ModelAndView instructorDetail(ModelAndView mav, @RequestParam int instructor_no) {
		
		Instructor i = instructorService.selectInstructor(instructor_no);
		System.out.println("강사 정보 : " + i);
		mav.addObject("instructor", i);
		mav.setViewName("instructor/detail");
		
		return mav;
	}
	
	/*
	@GetMapping("detail.do")
	public String detailList() {
		return "instructor/detail";
	}*/
	
	@GetMapping("regist")
	public String registPage(Model model) {
		return "instructor/regist";
	}
	@PostMapping("regist")
	public String instructorRegist(Instructor newInsturctor) {
		instructorService.instructorRegist(newInsturctor);
		return "redirect:/instructor/list";
	}
	
	
	// 강사 정보 수정
	@GetMapping("updatePage")
	public String instructorUpdatePage(Model model, @RequestParam("instructor_no") int insturctor_no) {
		
		Instructor i = instructorService.selectInstructor(insturctor_no);
		model.addAttribute("instructor", i);
		
		return "instructor/update";
	}
	@PostMapping("update")
	public String instructorUpdate(Instructor updateInstructor) {
		instructorService.insturctorUpdate(updateInstructor);
		
		return "redirect:/instructor/detail.do?instructor_no=" + updateInstructor.getInstructor_no();
	}
	
	
	@PostMapping("delete")
	public String instructorDelete(Instructor deleteInstructor, @RequestParam("instructor_no") int instructor_no) {
		instructorService.deleteInstructor(deleteInstructor);
		return "redirect:/instructor/list";
	}
	
}
