package com.ourDo.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//@RequestMapping 실습을 위한 컨트롤러클래스

@Controller
public class RequestMappingController {

	// **문자를 이용하는 방법
	//실습 http://localhost:9000/app/rmc/notice/T7.do
	//실습 http://localhost:9000/app/rmc/board/T7.do
	//실습 http://localhost:9000/app/rmc/a/b/T7.do
	@RequestMapping("/rmc/**/T7")
	public void test7() {
		System.out.println("요청함수test7()야~~~");
	}
	
	
	
	// *문자를 이용하는 방법
	//실습 http://localhost:9000/app/rmc/noticeT6.do
	//실습 http://localhost:9000/app/rmc/boardT6.do
	@RequestMapping("/rmc/*T6")
	public void test6() {
		System.out.println("요청함수test6()야~~~");
	}
	
	
	
	//여러 개의 요청을    하나의  요청함수에서  처리 하는 방법
	//요청내용을 {}의   map형태로 묶어서 작성한다
	//실습 http://localhost:9000/app/rmc/t4.do
	//실습 http://localhost:9000/app/rmc/t5.do
	@RequestMapping(  { "/rmc/t4" ,   "/rmc/t5" } )
	public void test4() {
		System.out.println("요청함수test4()야~~~");
	}
	
	
	
	//실습 http://localhost:9000/app/rmc/t3.do
	@RequestMapping("/rmc/t3.do")
	public void test3() {
		System.out.println("요청함수test3()");
		//1.파라미터받기
		//2.비즈니스로직(->Service->DAO->myBatis->DB)
		//3.Model
		//4.View
	}
	
	
	
	//실습 http://localhost:9000/app/rmc/t2.do
	@RequestMapping("rmc/t2")
	public String   test2() {
		//1.파라미터받기
		//2.비즈니스로직
		//3.Model
		//4.View
		return "t/test1";
	}
	
	//실습 http://localhost:9000/app/rmc/t1.do
	@RequestMapping("/rmc/t1")
	public String  test1() {
		//1.파라미터받기
		//2.비즈니스로직
		//3.Model
		//4.View
		return "t/test1";
	}
	
	//실습   http://127.0.0.1:9000/app/rmc/t5.do?id=hongid
	//@RequestMapping(value="/rmc/t5.do")
	@RequestMapping(value="/rmc/t5.do", method=RequestMethod.GET)
	public String test5(HttpServletRequest request) {
		System.out.println("요청함수 test5()함수호출성공");
		//1.파라미터받기
		String id = request.getParameter("id");
		System.out.println( "id = "+id );
		
		
		//2.비즈니스로직수행(<->Service<->DAO<->myBatis<->DB)
		//3.Model
		//4.View
		return "rmc/t5";
	}
	
	
	
}







