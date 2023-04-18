package com.itwillbs.spring_mvc_board_bhn.controller;

import java.util.Spliterator;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.itwillbs.spring_mvc_board_bhn.service.MemberService;
import com.itwillbs.spring_mvc_board_bhn.vo.MemberVO;

@Controller
public class MemberController {

	@Autowired
	private MemberService service;
	
	
	@GetMapping(value = "/MemberJoinForm.me")
	public String joinForm() {
		return "member/member_join_form";
	}
	
	
	@PostMapping(value = "/MemberJoinPro.me")
	public String joinPro(Model model, MemberVO member) {
		
		// ------------ BCryptPasswordEncoder 객체 활용한 패스워드 암호화 (해싱) ------------------------------
		// 입력받은 패스워드는 암호화(해싱)이 필요함 => 해싱 후 MemberVO객체 패스워드에 덮어쓰기
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String securePasswd = passwordEncoder.encode(member.getPasswd());
//		System.out.println("평문 : " + member.getPasswd()); //=> 평문 암호(raw password) 출력
//		System.out.println("암호문 : " + securePasswd); //=> 해싱된 암호 출력
		member.setPasswd(securePasswd);
		
		
		
		//------------- 회원가입작업 --------------------------------------------------------------------- 
//		member.setEmail(email1 + "@" + email2);  //=> 여기서 안하고 xml 가서 하는 방법으로 
		
		int insertCount = service.registMember(member);
		
		if(insertCount < 0) {
			model.addAttribute("msg", "회원가입 실패!");
			return "fail_back"; //디스패치
		} else {
			System.out.println("회원가입 성공!");
			return "redirect:/";
		}
	}
	
	
	
	// 로그인폼으로 이동
	@GetMapping(value = "/MemberLoginForm.me")
	public String loginForm() {
		return "member/member_login_form";
	}
	
	
	// 로그인 작업
	@PostMapping(value = "/MemberLoginPro.me")
	public String loginPro(Model model, HttpSession session, MemberVO member) {
//		System.out.println(member);
	
		// ------------ 가장 기본적인 로그인 ------------------------------------------------------------------
//		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//		System.out.println(passwordEncoder.encode(member.getPasswd()));
		
		//----------- BCryptPasswordEncoder 객체를 활용한 로그인(해싱된 암호 비교) -----------------------------
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwd = service.getpasswd(member.getId());
		System.out.println(passwd);
		
		//-------- DB로부터 조회된 기존 패스워드(암호문)을 받은 패스워드(평문)과 비교하여 로그인 성공 여부 판별
		if(passwd == null || !passwordEncoder.matches(member.getPasswd(), passwd)) { //=> passwordEncoder.matches(평문패스워드, 암호문패스워드)
			model.addAttribute("msg","로그인실패");
			return "fail_back";
		}else { 
			System.out.println("로그인성공!");
			session.setAttribute("sId", member.getId());
			return "redirect:/"; // 작업완료 후 메인페이지로 리다이렉트
		}
		
	}
	
	
	//로그아웃 작업
	@GetMapping(value = "/MemberLogout.me")
	public String logoutPro(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	
	
	// 회원정보를 수정할 수 있는 회원정보 페이지인 MemberInfo.jsp로 포워딩
	@GetMapping(value = "/MemberInfo.me")
	public String memberInfo(HttpSession session, Model model) {
		String id = (String) session.getAttribute("sId"); //=> 세션아이디를 가져와서 아이디로 활용할것
		System.out.println("아이디test : " + id);
		if(id == null) {
			model.addAttribute("msg", "잘못된 접근입니다!");
			return "fail_back";
		} 
		
		MemberVO member = service.getMemberInfo(id);
		
		// 단, email의 경우 split을 통해 email1, email2에 나누어 저장하는 작업 추가
//		member.setEmail1(member.getEmail1().split("@")[0]);
//		member.setEmail2(member.getEmail2().split("@")[1]);
		System.out.println("이메일테스트" + member.getEmail());
		
		
		model.addAttribute("member", member);
		
			return "member/member_info";
		
		
	}

	
	
	// Member_info.jsp 에서 정보수정 버튼을 클릭하면 UPDATE를 하는 Pro 실행
	@PostMapping(value = "/MemberUpdate.me")
	public String UpdatePro(MemberVO member, @RequestParam String newPasswd, Model model, HttpSession session) {
		
		//------------------------
		String id = (String) session.getAttribute("sId");
		
		if(id == null) {
			model.addAttribute("msg", "잘못된 접근입니다!");
			return "fail_back";
		}
		
		//-------------------------
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String passwd = service.getpasswd(id);
		
		if(passwd == null || !passwordEncoder.matches(member.getPasswd(), passwd)) { //=> passwordEncoder.matches(평문패스워드, 암호문패스워드)
			model.addAttribute("msg","수정 권한 없음!");
			return "fail_back";
		}
		
		System.out.println(newPasswd);
		// newPasswd가 존재할 경우 암호화(해싱) 수행하여 덮어쓰기
		if(!newPasswd.equals("")) { 
			newPasswd = passwordEncoder.encode(newPasswd);
		}
		
		
		//----------------------
		int updateCount = service.updateMemberInfo(member, newPasswd);
		
		if(updateCount > 0) {
			model.addAttribute("msg", "회원 정보 수정 성공!");
			model.addAttribute("target", "MemberInfo.me");
			
			return "success";
		} else {
			model.addAttribute("msg","회원 정보 수정 실패!");
			return "fail_back";
		}
	
		
	}
	
	
	
	
	// 회원탈퇴 뷰페이지로 포워딩
	@GetMapping("/MemberQuitForm.me")
	public String quitForm() {
		return "member/member_quit_form";
	}
	
	@PostMapping("/MemberQuitPro.me")
	public String quitPro(@RequestParam String passwd, HttpSession session, Model model) {
		// 세션 아이디 꺼내서 저장
		String id = (String)session.getAttribute("sId");
		
		// 해당 아이디와 일치하는 레코드의 패스워드 조회
		// => MemberService - getPasswd() 재사용
		String dbPasswd = service.getpasswd(id);
		
		
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		if(passwordEncoder.matches(passwd, dbPasswd)) {
			int deleteCount = service.quitMember(id);
			
			if(deleteCount > 0) {
				session.invalidate();
				
				model.addAttribute("msg", "탈퇴가 완료되었습니다!");
				model.addAttribute("target", "./");
				
				return "success";
			} else {
				model.addAttribute("msg", "탈퇴 실패!");
				return "fail_back";
			}
			
		} else {
			model.addAttribute("msg", "권한이 없습니다!");
			return "fail_back";
		}
		
	}
		
		
	
	
	
	
}	// 클래스 끝
