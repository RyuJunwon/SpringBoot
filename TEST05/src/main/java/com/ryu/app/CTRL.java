package com.ryu.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jakarta.servlet.http.HttpSession;

@Controller
public class CTRL {

	@Autowired
	MemberDAO mDAO;
	@Autowired
	BoardDAO bDAO;

	@RequestMapping("/")
	public String root() {

		return "redirect:/main";
	}

	@RequestMapping("/main") // 메인 페이지
	public String main(BoardDTO bDTO, Model model) { 
		model.addAttribute("boardDatas", bDAO.selectAll(null));
		return "main";
	}

	@RequestMapping("/login") // 로그인
	public String login(MemberDTO mDTO, HttpSession session) {
		
		mDTO.setSearchCondition("로그인");
		if(mDAO.selectOne(mDTO) != null) {
			session.setAttribute("sessionMemberId", mDTO.getMid());
			System.out.println("로그인 성공");
		} else { 
			System.out.println("로그인 실패");
		}

		return "redirect:/";
	}

	@RequestMapping("/logout") // 로그아웃
	public String logout(HttpSession session) {	
		session.removeAttribute("sessionMemberId");
		return "redirect:/main";
	}

	@GetMapping("/signup")
	public String joingo() {
		return "join";
	}

	@PostMapping("/signup")
	public String join(MemberDTO mDTO, Model model) {

		mDTO.setSearchCondition("중복확인");
		if(mDAO.selectOne(mDTO) == null) {
			if(!mDAO.insert(mDTO)) {
				System.out.println("회원가입 실패");
			}
			else {
				System.out.println("회원가입 성공");
			}
		}
		return "redirect:/";
	}

	@GetMapping("/updateMember")
	public String mypagego(MemberDTO mDTO, Model model) { 
		mDTO.setSearchCondition("중복확인");
		model.addAttribute("memberData", mDAO.selectOne(mDTO));
		return "mypage";
	}

	@PostMapping("/updateMember")
	public String updateMember(MemberDTO mDTO) { 
		if(!mDAO.update(mDTO)) {
			System.out.println("회원 정보 수정 실패");
		}
		else {
			System.out.println("회원 정보 수정 성공");
		}
		return "redirect:/logout";
	}

	@RequestMapping("/deleteMember")
	public String deleteMember(MemberDTO mDTO, HttpSession session) {
		mDTO.setMid((String)session.getAttribute("sessionMemberId"));
		if(!mDAO.delete(mDTO)) {
			System.out.println("회원 탈퇴 실패");
		} else {
			System.out.println("회원 탈퇴 성공");
		}
		return "redirect:/logout";
	}

	// ----------------------------- 글 ---------------------------------

	@RequestMapping("/insertBoard") // 글작성
	public String board(BoardDTO bDTO, HttpSession session) {
		
		if(!bDAO.insert(bDTO)) {
			System.out.println("글 작성 실패");
		}
		else {
			System.out.println("글 작성 성공");
		}
		return "redirect:/";
	}

	@RequestMapping("/board") // 글 상세
	public String boardDetail(BoardDTO bDTO, Model model) {

		model.addAttribute("boardData", bDAO.selectOne(bDTO));
		return "board";
	}

	@RequestMapping("/updateBoard")
	public String updateBoard(BoardDTO bDTO) {
		if(!bDAO.update(bDTO)) {
			System.out.println("글 수정 실패");
		}
		else {
			System.out.println("글 수정 성공");
		}
		return "redirect:/";
	}

	@RequestMapping("/deleteBoard")
	public String deleteBoard(BoardDTO bDTO) {
		if(!bDAO.delete(bDTO)) {
			System.out.println("글 삭제 실패");
		} else {
			System.out.println("글 삭제 실패");
		}
		return "redirect:/";
	}

}
