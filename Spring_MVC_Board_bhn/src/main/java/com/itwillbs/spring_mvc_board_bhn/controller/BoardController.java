package com.itwillbs.spring_mvc_board_bhn.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.spring_mvc_board_bhn.service.BoardService;
import com.itwillbs.spring_mvc_board_bhn.vo.BoardVO;


@Controller
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	// 글쓰기 폼에 세션아이디가 존재하지 않으면 "MemberLoginfForm.me"저장 후 success 으로 이동!
	@GetMapping(value = "/BoardWriteForm.bo")
	public String writeForm(HttpSession session, Model model) {
		
		String id = (String) session.getAttribute("sId");
		
		if(id == null) {
			model.addAttribute("msg", "로그인 필수!");
			model.addAttribute("target", "MemberLoginForm.me");
			return "success";
		}
		
		return "board/board_write_form";
	}
	
	
	// 방법1) 파일 업로드 기능이 포함된(enctype="multipart/form-data") 폼 파라미터 처리할 경우
//	@PostMapping("/BoardWritePro.bo")
//	public String writePro(String board_name, String board_subject, String board_content, MultipartFile file) {
//		System.out.println(board_name + ", " + board_subject + ", " + board_content);
//		System.out.println("업로드 파일명 : " + file.getOriginalFilename());
//		return "";
//	}
	
	//방법2) @RequestParam 어노테이션 필수!
//	@PostMapping("/BoardWritePro.bo")
//	public String writePro(@RequestParam Map<String, String> map, MultipartFile file) {
//		System.out.println(map.get("board_name") + ", " + map.get("board_subject") + ", " + map.get("board_content"));
//		System.out.println("업로드 파일명 : " + file.getOriginalFilename());
//		return "";
//	}
	
	//방법3) MultipartFile 타입 멤버변수를 포함하는 VO타입으로 모든 파라미터를 한꺼번에 처리
	@PostMapping("/BoardWritePro.bo")
	public String writePro(BoardVO board, HttpSession session, Model model) { // request, session 둘다 가능한데 보통은 reqeust는 안씀
		String uploadDir = "/resources/upload";
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		System.out.println("실제 업로드 경로 : " + saveDir);
		
		
		try {
			//----------------------- 업로드 디렉토리를 날짜별 디렉토리로 분류하기 (java.util.Date) ----------
			Date date = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
			board.setBoard_file_path("/" + sdf.format(date));
			saveDir = saveDir + board.getBoard_file_path();
			//------------------------------------------------------------------------------------------------
			
			Path path = Paths.get(saveDir);
			Files.createDirectories(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		MultipartFile mFile = board.getFile(); //=> 단일파일
	//	MultipartFile[] mFiles = board.getFiles(); //=> 복수파일일 경우
		
		String originalFileName = mFile.getOriginalFilename();
		System.out.println("원본파일명 : " + originalFileName);
		
		String uuid = UUID.randomUUID().toString();
		
//		originalFileName = UUID.randomUUID().toString() + "_" + originalFileName;
		
		board.setBoard_file(uuid.substring(0, 8) + "_" + originalFileName);
		
		// 파일처리 끝 ------------------------------------------------------------------------
		// 게시물 등록 작업 요청 시작 ---------------------------------------------------------
		int insertCount = boardService.registBoard(board);
		
		if(insertCount > 0) {

			try {
				mFile.transferTo(new File(saveDir, board.getBoard_file()));
			} catch (IllegalStateException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			return "redirect:/BoardList.bo";
		} else {
			model.addAttribute("msg", "글 쓰기 실패!");
			return "fail_back";
		}
		
		
	}
	
	
// 글쓰기폼에 입력된 내용 가져오기	
//	@PostMapping("/BoardWritePro.bo")
//	public String writePro(BoardVO board) {
//		System.out.println(board);
//		System.out.println("업로드 파일명 : " + board.getFile().getOriginalFilename());
//		return "";
//	}
	
}
