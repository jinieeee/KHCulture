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
import com.kh.khculture.management.model.vo.SearchBoard;
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
	
	
	// ================== 강의목록 요청처리 ================== //
	@GetMapping("/lecturelist")
	public String lectureList(Model model,
			@RequestParam(value="page" , defaultValue="1") int page,
			@RequestParam(required=false, defaultValue="title") String searchCondition,
			@RequestParam(required=false, defaultValue="") String searchValue) {
		
		
		SearchBoard search = new SearchBoard();
		search.setSearchCondition(searchCondition);
		search.setSearchValue(searchValue);
		
		log.info("searchBoard : {}", search);
		
		// 게시글 총 개수
		int listCount = managementService.getListCount(search);
		
		log.info("listCount : {}", listCount);
		
		PageInfo pi = new PageInfo(page , listCount, 5, 20);
		
		int startRow = (pi.getPage() -1)* pi.getBoardLimit() +1;
		int endRow = startRow +pi.getBoardLimit() -1;
		
		log.info("startRow : {}", startRow);
		log.info("endRow : {}", endRow);
		
		
		search.setStartRow(startRow);
		search.setEndRow(endRow);
		
		List<Lecture2> lectureList = managementService.selectLectureList(search);
		
		
		model.addAttribute("lectureList", lectureList);
		model.addAttribute("pi", pi);
		model.addAttribute("searchBoard", search);
		
		return "management/lecturelist";
	}
	
	
	// ================== 강의 등록 요청처리 ================== //
	@GetMapping("/lectureregist")
	public String lectureRegist() {
		return "management/lectureregist";
	}
	
	// ================== 강의 등록 응답 ================== //
	@PostMapping("/registserver")
	public String lectureRegistServer(Lecture lecture, @RequestParam MultipartFile singleFile, 
			HttpServletRequest request, RedirectAttributes rttr) {
		
		
		String msg = "";

		String projectPath = new File("").getAbsolutePath();
//		String filePath = projectPath + "\\src\\main\\resources\\static\\images\\upload";
		String filePath = projectPath + "\\src\\main\\resources\\uploadFiles\\upload";
		
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
	
	// ================== 강의 업데이트 요청처리 ================== //	
	@GetMapping("lectureupdate/{lNo}")
	public String lectureUpdate(@PathVariable int lNo, Model model) {
		
		Lecture lecture = managementService.selectLecture(lNo);
		
		if(lecture == null) {
			lecture = new Lecture();
		}
		
		model.addAttribute("lecture", lecture);
		
		return "management/lectureupdate";
	}
	
	// ================== 강의 업데이트 응답 ================== //	
	@PostMapping("/updateserver")
	public String lectureUpdateServer(Lecture lecture, RedirectAttributes rttr) {
		
		String msg = "";
		
		
		int result = managementService.updateLecture(lecture);
		
		if(result > 0) {
			msg = "강의 수정 성공!";
		} else {
			msg = "강의 수정 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/lecturelist";
	}
	
	// ================== 강의 삭제 응답 ================== //
	@GetMapping("/lecturedelete/{lNo}")
	public String lectureDelete(@PathVariable int lNo, RedirectAttributes rttr) {
		
		
		String msg = "";
		
		int lrCount = managementService.deleteProcedure(lNo);
		
		if(lrCount > 0) {
			msg = "오픈된 강의 입니다. 강의 삭제 실패!";
			rttr.addFlashAttribute("msg", msg);
			return "redirect:/lecturelist";
		}
		
		int result = managementService.deleteLecture(lNo);
		
		if(result > 0) {
			msg = "강의 삭제 성공!";
		} else {
			msg = "강의 삭제 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/lecturelist";
	}
	
	// ================== 강의오픈 목록 요청처리 ================== //
	@GetMapping("/openlist")
	public String openList(Model model,
			@RequestParam(value="page" , defaultValue="1") int page,
			@RequestParam(required=false, defaultValue="title") String searchCondition,
			@RequestParam(required=false, defaultValue="") String searchValue) {
		
		
		SearchBoard search = new SearchBoard();
		search.setSearchCondition(searchCondition);
		search.setSearchValue(searchValue);
		
		log.info("searchBoard : {}", search);
		
		// 게시글 총 개수
		int listCount = managementService.getOpenListCount(search);
		
		log.info("listCount : {}", listCount);
		
		PageInfo pi = new PageInfo(page , listCount, 5, 20);
		
		int startRow = (pi.getPage() -1)* pi.getBoardLimit() +1;
		int endRow = startRow +pi.getBoardLimit() -1;
		
		log.info("startRow : {}", startRow);
		log.info("endRow : {}", endRow);
		
		
		search.setStartRow(startRow);
		search.setEndRow(endRow);		
		
		
		List<LectureOpen3> openList = managementService.selectOpenList(search);
		
		log.info("openList : {}", openList);
		
		model.addAttribute("openList", openList);
		model.addAttribute("pi", pi);
		model.addAttribute("searchBoard", search);
		
		return "management/openlist";
	}
	
	
	// ================== 강의오픈 등록 요청처리 ================== //
	@GetMapping("/openregist")
	public String openRegist() {
		return "management/openregist";
	}
	
	// ================== 강의오픈 등록 응답 ================== //
	@PostMapping("/openserver")
	public String openRegistServer(LectureOpen2 lectureOpen, RedirectAttributes rttr) {
		
		String msg = "";
		
		
		int result = managementService.registLectureOpen(lectureOpen);
		
		if(result > 0) {
			msg = "강의 오픈 성공!";
		} else {
			msg = "강의 오픈 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/openlist";
	}
	
	// ================== 강의오픈 업데이트 요청처리 ================== //
	@GetMapping("openupdate/{lrNo}")
	public String openUpdate(@PathVariable int lrNo, Model model, RedirectAttributes rttr) {
		
		
		LectureOpen2 open = managementService.selectOneOpen(lrNo);
		
		log.info("open 확인 : {}", open);
		
		if(open.getLrCount() != 0) {
			
			rttr.addFlashAttribute("msg", "접수인원이 있습니다. 수정하실 수 없습니다.");
			
			return "redirect:/openlist";
		}
		
		model.addAttribute("open", open);
		
		return "management/openupdate";
	}
	
	
	// ================== 강의오픈 업데이트 응답 ================== //
	@PostMapping("/updateOpenserver")
	public String updateOpenServer(LectureOpen2 lectureOpen, RedirectAttributes rttr) {
		
		String msg = "";
		
		log.info("lectureOpen : {}", lectureOpen);
		
		int result = managementService.updateOpen(lectureOpen);
		
		if(result > 0) {
			msg = "강의오픈 수정 성공!";
		} else {
			msg = "강의오픈 수정 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/openlist";
	}
	
	
	// ================== 강의오픈 삭제 응답 ================== //
	@GetMapping("/opendelete/{lrNo}")
	public String deleteOpen(@PathVariable int lrNo, RedirectAttributes rttr) {
		
		String msg = "";
		
		int result1 = managementService.deleteOpenProcedure(lrNo);
		
		if(result1 > 0) {
			msg = "접수인원이 있거나 수강종료일이 지나지 않은 강의는 삭제할 수 없습니다.";
			rttr.addFlashAttribute("msg", msg);
			
			return "redirect:/openlist";
		}
		
		int result2 = managementService.deleteOpen(lrNo);
		
		if(result2 > 0) {
			
			msg = "강의오픈 삭제 성공!";
		} else {
			msg = "강의오픈 삭제 실패!";
		}
		
		rttr.addFlashAttribute("msg", msg);
		
		return "redirect:/openlist";
	}
	
	
	// ================== 모달창 : 강의 목록 ================== //
	@GetMapping(value="/modal/lecture", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Lecture> modalLecture() {
				
		return managementService.selectAllLectureList();
	}

	// ================== 모달창 : 강사 목록 ================== //	
	@PostMapping(value="/modal/instructor", produces="application/json; charset=UTF-8")
	@ResponseBody
	public List<Instructor> modalInstructor(@RequestBody SearchInstructor searchInstructor) {
				
		return managementService.selectInstructor(searchInstructor);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
