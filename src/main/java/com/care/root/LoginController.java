package com.care.root;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {
	@GetMapping("login")
	public String login() {
		return "login/login";
	}
	
	@PostMapping("chkUser")
	public String chkUser(@RequestParam String id,
							@RequestParam String pwd,
							HttpSession session) {
		String db_id = "1", db_pwd="1", db_nick="홍길동구리구리";
		if(id.equals(db_id) && pwd.equals(db_pwd)){
			session.setAttribute("loginId", db_id);
			session.setAttribute("loginNick", db_nick);
			return "redirect:main";
		}else {
			return "redirect:login";
		}
	}
	
	@RequestMapping("main")
	public String main(HttpSession session) {
		if(session.getAttribute("loginId") != null) {	// 세션이 없으면 login 있으면 main으로가라
			return "login/main";
		}
		return "redirect:login"; 
	}
	
/*	@RequestMapping("logout")
	public String logout(HttpSession session) {
		return "login/logout";
	}*/
	
	@RequestMapping("logout")
	public void logout(HttpSession session, HttpServletResponse response) {
		session.invalidate();
		PrintWriter out = null;
		response.setContentType("text/html; charset=utf-8");
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
		}
		out.print("<script> alert('111로그아웃');"
				+"location.href='login'; </script>");
	//	return "login/logout";
	}
	
/*	@RequestMapping("main")					// 여기서 html을 써서 바로 넘겨줌
	public void main(HttpSession session, HttpServletResponse response) {
		PrintWriter out = null;
		response.setContentType("text/html; charset=utf-8");
		try {
			out = response.getWriter();
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(session.getAttribute("loginId") != null) {	// 세션이 없으면 login 있으면 main으로가라
			out.print("<script>alert('로그인 성공')</script>");
			return "login/main";
		}
		return "redirect:login"; 
	}
	*/
	
	
	
	
}








