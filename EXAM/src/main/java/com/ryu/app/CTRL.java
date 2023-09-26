package com.ryu.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import jakarta.servlet.http.HttpSession;

@Controller
public class CTRL {

	@Autowired
	private MemberDAO mDAO;
	@Autowired
	private BoardDAO bDAO;

	@GetMapping("/")
	public String root(Model model) {
		model.addAttribute("datas", bDAO.selectAll(null));
		return "main";
	}

	@PostMapping("/join")
	public String join(MemberDTO mDTO) {
		mDAO.insert(mDTO);
		return "redirect:/";
	}
	@RequestMapping("/login")
	public String login(MemberDTO mDTO, HttpSession session) {
		try {
			mDTO=mDAO.selectOne(mDTO); // throws 대신, error.jsp를 views 하위에 위치시켜도 ㄱㅊ
		} catch (Exception e) {
			mDTO=null;
		}
		if(mDTO!=null) {
			session.setAttribute("member", mDTO.getMid());
		}
		return "redirect:/";
	}
	@GetMapping("/logout")
	public String logout(HttpSession session) {
		session.removeAttribute("member");
		return "redirect:/";
	}
	@GetMapping("/mypage")
	public String mypage() {
		return "mypage";
	}
	@PostMapping("/mypage")
	public String mypage(MemberDTO mDTO) {
		mDAO.update(mDTO);	
		return "redirect:logout";
	}
	@GetMapping("/deleteAccount/{mid}")
	public String deleteAccount(MemberDTO mDTO) {
		mDAO.delete(mDTO);
		return "redirect:/logout";
	}

	@GetMapping("/insert")
	public String insert() {
		return "insert";
	}
	@PostMapping("/insert")
	public String insert(BoardDTO bDTO) {
		bDAO.insert(bDTO);
		return "redirect:/";
	}
	@GetMapping("/board/{bid}")
	public String board(BoardDTO bDTO, Model model) {
		try {
			model.addAttribute("board", bDAO.selectOne(bDTO)); // .selectOnt()이 예외처리를 미뤄버려서 에러발생!
		}
		catch (Exception e) {
			return "error";
			// forward  V  VR가 동작(/WEB-INF/views/ + 경로 + .jsp)
			// redirect  C
		}
		return "board";
	}
	@PostMapping("/update")
	public String update(BoardDTO bDTO) {
		System.out.println("bDTO : "+bDTO);
		bDAO.update(bDTO);
		return "redirect:/board/"+bDTO.getBid();
	}
	@GetMapping("/delete/{bid}")
	public String delete(BoardDTO bDTO) {
		bDAO.delete(bDTO);
		return "redirect:/";
	}

}
