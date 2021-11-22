package com.kh.khculture.management.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kh.khculture.lecture.model.vo.Instructor;
import com.kh.khculture.lecture.model.vo.Lecture;
import com.kh.khculture.management.model.service.ManagementService;
import com.kh.khculture.management.model.vo.Lecture2;
import com.kh.khculture.management.model.vo.LectureOpen2;
import com.kh.khculture.management.model.vo.LectureOpen3;
import com.kh.khculture.management.model.vo.SearchInstructor;
import com.kh.khculture.notice.model.vo.PageInfo;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ManagementController {
	
	private ManagementService managementService;
	
	@Autowired
	public ManagementController(ManagementService managementService) {
		this.managementService = managementService;
	}
	
	
	@GetMapping("/openlist")
	public String openList(Model model, @RequestParam(value="page" , defaultValue="1") int page) {
		
		// 게시글 총 개수
		int listCount = managementService.getOpenListCount();
		
		PageInfo pi = new PageInfo( page , listCount, 10, 10);
		
		int startRow = (pi.getPage() -1)* pi.getBoardLimit() +1;
		int endRow = startRow +pi.getBoardLimit() -1;
		
		List<LectureOpen3> openList = managementService.selectOpenList(startRow, endRow);
		
		log.info("check123 : {}", openList);
		
		model.addAttribute("openList", openList);
		model.addAttribute("pi", pi);
		
		return "management/openlist";
	}
	
	
	@GetMapping("/lecturelist")
	public String lectureList(Model model, @RequestParam(value="page" , defaultValue="1") int page) {
		
		// 게시글 총 개수
		int listCount = managementService.getListCount();
		log.info("listCount : {}", listCount);
		
		PageInfo pi = new PageInfo( page , listCount, 10, 10);
		
		int startRow = (pi.getPage() -1)* pi.getBoardLimit() +1;
		int endRow = startRow +pi.getBoardLimit() -1;
		
		List<Lecture2> lectureList = managementService.selectLectureList(startRow, endRow);
		
		model.addAttribute("lectureList", lectureList);
		
		log.info("lectureList : {}", lectureList);
		log.info("pi : {}", pi);
		
		model.addAttribute("pi", pi);
		
		return "management/lecturelist";
	}
	
	@GetMapping("lectureupdate/{lNo}")
	public String lectureUpdate(@PathVariable int lNo, Model model) {
		
		Lecture lecture = managementService.selectLecture(lNo);
		
		if(lecture == null) {
			lecture = new Lecture();
		}
		
		model.addAttribute("lecture", lecture);
		
		return "management/lectureupdate";
	}
	
	@GetMapping("/lecturedelete/{lNo}")
	public String lectureDelete(@PathVariable int lNo, RedirectAttributes rttr) {
		
		log.info("lNo : {}", lNo);
		
		String msg = "";
		
		int result = managementService.deleteLecture(lNo);
		
		if(result > 0) {
			msg = "강의 삭제 성공!";
		} else {
			msg = "강의 삭제 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/lecturelist";
	}
	
	@GetMapping(value="/modal/lecture", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Lecture> modalLecture() {
				
		return managementService.selectAllLectureList();
	}
	
	@PostMapping(value="/modal/instructor", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Instructor> modalInstructor(@RequestBody SearchInstructor searchInstructor) {
				
		log.info("searchCondition : {}", searchInstructor);
		
		return managementService.selectInstructor(searchInstructor);
	}
	
	
	@GetMapping("/openregist")
	public String openRegist() {
		return "management/openregist";
	}
	
	
	@GetMapping("/lectureregist")
	public String lectureRegist() {
		return "management/lectureregist";
	}
	
	
	
	
	@PostMapping("/updateserver")
	public String lectureUpdateServer(Lecture lecture, RedirectAttributes rttr) {
		
		String msg = "";
		
		log.info("lecture : {}", lecture);
		
		int result = managementService.updateLecture(lecture);
		
		if(result > 0) {
			msg = "강의 수정 성공!";
		} else {
			msg = "강의 수정 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/lecturelist";
	}
	
	@PostMapping("/openserver")
	public String openRegistServer(LectureOpen2 lectureOpen, RedirectAttributes rttr) {
		
		String msg = "";
		
		log.info("lectureInfo : {}", lectureOpen);
		
		int result = managementService.registLectureOpen(lectureOpen);
		
		if(result > 0) {
			msg = "강의 오픈 성공!";
		} else {
			msg = "강의 오픈 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/openlist";
	}
	
	
	
	
	
	
	
	@PostMapping("/registserver")
	public String lectureRegistServer(Lecture lecture, @RequestParam MultipartFile singleFile, 
			HttpServletRequest request, RedirectAttributes rttr) {
		
		
		String msg = "";

		String projectPath = new File("").getAbsolutePath();
		String filePath = projectPath + "\\src\\main\\resources\\static\\images\\upload";
		
		File mkdir = new File(filePath);
		
		if(!mkdir.exists()) {
			// 파일 경로가 존재하지 않을경우 디렉토리 생성
			mkdir.mkdirs();
		}
		
		String originFileName = singleFile.getOriginalFilename();
		String ext = originFileName.substring(originFileName.lastIndexOf("."));
		String savedName = UUID.randomUUID().toString().replace("-", "") + ext;
		
		try {
			
			singleFile.transferTo(new File(filePath + "\\" + savedName));
			lecture.setLThumbnail(savedName);
			
		} catch (IllegalStateException | IOException e) {
			
			msg = "사진 업로드 실패!";
			rttr.addFlashAttribute("msg", msg);
			return "redirect:/lecturelist";
//			e.printStackTrace();
			

		}
		
		int result = managementService.registLecture(lecture);
		
		if(result > 0 ) {
			
			msg = "강의 등록 성공!";
			rttr.addFlashAttribute("msg", msg);
			
			return "redirect:/lecturelist";
		} else {
			
			new File(filePath + "\\" + savedName).delete();
			
		}
		msg = "강의 등록 실패!";
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/lecturelist";
	}
	
	
	
	
}
