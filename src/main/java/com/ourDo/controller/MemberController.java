package com.ourDo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ourDo.dao.MemberDAO;
import com.ourDo.dto.MemberDTO;
import com.ourDo.service.MemberService;

// member와 관련된 기능을 가진  컨트롤러 클래스이다

@Controller
public class MemberController {
	//변수
	@Autowired
	private MemberDAO memberDAO;
	
	@Autowired
	private MemberService memberS;
	
	//생성자
	
	//일반함수 + 요청함수(@RequestMapping("요청내용"))
	//로그인 처리
	//요청내용  http://localhost:9000/app/member/loginProc.do
	@RequestMapping("/member/loginProc")
	public  ModelAndView   loginProc(MemberDTO memberDto,
							HttpSession session) {
		System.out.println("loginProc() memberDto="+memberDto);
		//1.파라미터받기
		//  id=아이디   memberDto.getId();
		//  pw=비번	 memberDto.getPw();
		
		//2.비즈니스로직(<-->Service<-->DAO<-->myBatis<->DB)
		memberS.loginProc(memberDto,session);
		
		//3.Model
		//4.View
		//로그인성공시 & 로그인실패시 로그인폼뷰페이지
		ModelAndView mv = new ModelAndView();
		//Spring에서의 sendRedirect용 뷰
		RedirectView rv = new RedirectView("../member/loginFrm.do");
		//http://localhost:9000/app/member/loginProc.do
		//http://localhost:9000/app/member/loginFrm.do
		mv.setView(rv);
		return mv;
	}

	
	//로그인폼 보이기 
	//http://localhost:9000/app/member/loginFrm.do
	@RequestMapping("/member/loginFrm")
	public void showLoginFrm() {
		System.out.println("로그인폼보이기-showLoginFrm()");
		//1.파라미터받기
		//2.비즈니스로직(<-->Service<-->DAO<-->myBatis<->DB)
		//3.Model
		//4.View
	}
	
	//회원수 조회를 요청함수
	//요청내용  http://ip주소:9000/app/member/mcnt.do
	@RequestMapping("/member/mcnt")
	public void  getMemberCnt() {
		//1.파라미터받기
		//2.비즈니스로직(<-->Service<-->DAO<-->myBatis<->DB)
		int result = memberDAO.getMemberCnt();
		
		System.out.println("회원수="+result);
		//3.Model
		//4.View
	}
	
}









