package com.ourDo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.ourDo.dto.TestDTO;

//Parameter 받는 방법을 실습하기위한 컨트롤러
@Controller
public class ParameterController {
	
	//변수
	//생성자
	//함수
	
	//DTO(VO)클래스이용
	//  컨트롤러의   요청함수에   매개변수로    DTO(VO)클래스를 지정
	//실습    http://localhost:9000/app/pm/test4.do
	@RequestMapping("/pm/test4")
	public String test4(TestDTO  tDTO, HttpServletRequest req ) {
	//public void test4(@ModelAttribute TestDTO  tDTO) {
		System.out.println("test3()요청함수 호출성공-~~~");
		//1.파라미터받기
		System.out.println("tDTO="+tDTO);
		System.out.println("tDTO.toString()="+tDTO.toString());
		
		System.out.println("tDTO.getMid()="+tDTO.getMid());
		System.out.println("tDTO.getMpw()="+tDTO.getMpw());
		
		String mname = tDTO.getMname();
		System.out.println("tDTO.getMname()="+mname);
		int mage = tDTO.getMage();
		System.out.println("tDTO.getMage()="+mage);
		
		String[] hobby = tDTO.getHobby();
		System.out.println("tDTO.getHobby()="+hobby);
		System.out.println("for문이용해서 출력------");
		for(int i=0; i<hobby.length ;i++) {
			String h = hobby[i];
			System.out.println(h);
		}
		
		System.out.println();
		System.out.println("향상된 for문으로 출력---");
		for( String  h  : hobby ) {
			System.out.println(h);
		}
		
		
		
		/*
		 * String[] hobby일때는
		 * for( String temp  : hobby )
		 * for( 데이터타입 변수  : 배열명 ) {  
			syso(변수명);
		}
		
		MemberDTO   mDto;
		for( Object temp  : mDto ) {
			MemberDTO  dto =(MemberDTO)temp;
			dto.get메소드()
			dto.변수
		}
		for( Object temp  : 컬렉션명 ) {
			syso((String)temp);
		}
		*/
		
		//2.비즈니스로직
		//3.Model
		//이름,나이,취미를 Model처리해서   View넘기고
		//View에서 출력
		
		//String mname = tDTO.getMname();
		//int mage = tDTO.getMage(); 앞에서 위와 같이 처리되었다
		req.setAttribute("MNAME", mname);//이름
		req.setAttribute("MAGE", mage);  //나이
		
		//DTO이용
		req.setAttribute("TDTO", tDTO); //취미,이름, 나이 전부 포함
		
		
		//4.View-
		return "t/memberInfo";
	}
	
	
	//요청함수
	//실습    http://localhost:9000/app/pm/test3.do
	//@RequestParam 을  이용
	//컨트롤러의   요청함수에   매개변수로      @RequestParam    데이터타입  변수명을  지정
	@RequestMapping("/pm/test3")
	public void test3(
			@RequestParam Map data) {
		System.out.println("test3()요청함수 호출성공-");
		//1.파라미터받기
		//Map에서   data를  추가    map객체.put(Obejct 키값,Object data)
		//편의상     우리는  map객체.put("키값",Object data)
		//Map에서   data를 추출      map객체.get("키값")
		System.out.println("아이디="+data.get("mid")  );
		System.out.println("비번="+data.get("mpw") );
		System.out.println("이름="+data.get("mname") );
		
		String strAge=(String)data.get("mage");
		System.out.println("나이="+strAge);
		System.out.println("나이+1="+(Integer.parseInt(strAge)+1));
		//2.비즈니스로직
		//3.Model
		//4.View
		//return "member/member";
	}
	
	
	
	
	//실습    http://localhost:9000/app/pm/test2.do
	//@RequestParam 을  이용
	//컨트롤러의   요청함수에   매개변수로      @RequestParam    데이터타입  변수명을  지정
	@RequestMapping("/pm/test2")
	public void test2(
			@RequestParam("mname") String  mname, 
			@RequestParam("mage") int  age) {
		System.out.println("test2()요청함수 호출성공-");
		//1.파라미터받기
		System.out.println("이름="+mname);
		System.out.println(age+1);
		//2.비즈니스로직
		//3.Model
		//4.View
		//return "member/member";
	}
	
	
	
	
	
	
	
	//기존에 알고 있던 방식으로 파라미터 받기
	//실습    http://localhost:9000/app/pm/test1.do
	@RequestMapping("/pm/test1")
	public void test1(HttpServletRequest request) {
		System.out.println("test1()요청함수 호출성공-");
		//1.파라미터받기
		String mname  = request.getParameter("mname");//이름
		String strAge = request.getParameter("mage");//나이
		int    age    = Integer.parseInt(strAge);
		
		
		System.out.println("이름="+mname);
		System.out.println(age+1);
		//2.비즈니스로직
		//3.Model
		//4.View
		//return "member/member";
	}
	
	
	
	//실습    http://localhost:9000/app/pm/test0.do
	@RequestMapping("/pm/test0")
	public String test0() {
		System.out.println("test0()요청함수 호출성공-");
		//1.파라미터받기
		//2.비즈니스로직
		//3.Model
		//4.View
		return "member/member";
	}
	
}










