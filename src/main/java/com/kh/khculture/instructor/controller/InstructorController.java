package com.kh.khculture.instructor.controller;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	public String getList(Model model, @RequestParam(value="page", defaultValue="1") int page) {
		
		Map<String, Object> returnMap = instructorService.getList(page);
		model.addAttribute("pi", returnMap.get("pi"));
		model.addAttribute("instructorList", returnMap.get("instructorList"));
		
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
	public String instructorRegist(Instructor instructor, @RequestParam MultipartFile singleFile,
			                       HttpServletRequest request, RedirectAttributes rttr) {
		
		String msg = "";
		
		String projectPath = new File("").getAbsolutePath();
		String filePath = projectPath + "\\src\\main\\resources\\uploadFiles\\upload";
		
		File mkdir = new File(filePath);
		
		if(!mkdir.exists()) {
			mkdir.mkdirs();
		}
		
		String originFileName = singleFile.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		try {
			singleFile.transferTo(new File(filePath + "\\" + savedName));
			instructor.setProfile_photo(savedName);
			
		} catch (IllegalStateException | IOException e) {
			
			msg = "이미지 업로드에 실패하셨습니다.";
			rttr.addFlashAttribute("msg", msg);
			return "redirect:/instructor/list";
			// TODO Auto-generated catch block
			// e.printStackTrace();
		}
		int result = instructorService.instructorRegist(instructor);
		
		if(result > 0) {
			msg = "강사 정보가 등록되었습니다.";
			rttr.addFlashAttribute("msg", msg);
			
			return "redirect:/instructor/list";
		} else {
			new File(filePath + "\\" + savedName).delete();
		}
		msg = "강사 정보가 등록되었습니다.";
		rttr.addFlashAttribute("msg", msg);
		
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
