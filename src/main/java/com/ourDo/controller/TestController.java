package com.ourDo.controller;



import java.text.DateFormat;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ourDo.dao.MemberDAO;

/*컨트롤러 클래스를 만드는 방법
1.(우리의 경우는 do-context.xml문서에서 지정한)
     base-package에서 지정한 곳에 반드시 위치해야 한다
   <context:component-scan      base-package="com.ourDo" />
2. @Controller  선언한다*/

@Controller
public class TestController {
	//일반 클래스와 동일하게 변수,함수를 선언하면 된다
	
	//변수
	
	//함수
	//[접근제한자] [속성] 리턴유형  함수명(매개변수리스트){}
	//Model지정방법으로서    기존에 알고 있던 방식
	//형식> 세션객체.setAttribute("id값", data)
	//형식> request객체.setAttribute("id값", data)
	//실습   http://localhost:9000/app/t/test5.do
	@RequestMapping("/t/test5")
	public String  test5(HttpServletRequest request, 
			Locale locale,Model model) {
		System.out.println("요청함수 test5()진입");
		//1.파라미터받기
		//2.비즈니스로직(->Service->DAO->myBatis->DB)
		//3.Model
		//형식> request.setAttribute(String arg0, Object arg1);
  //참고 ModelAndView객체.addObject(String attributeName, Object attributeValue);
		java.util.Date today = new java.util.Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(today);
		
		request.setAttribute("serverTime", today); //Date객체
		model.addAttribute("serverTime1", formattedDate );//String
		request.setAttribute("USERNAME", "이순신");//String
		int age = 40;
		request.setAttribute("USERAGE", age);//Integer
		
		//4.View
		return "t/home";
	}
	
	
	
	//Model지정방법으로서  요청함수의 리턴유형이   ModelAndView인 경우
	//실습   http://localhost:9000/app/t/test4.do
	@RequestMapping("/t/test4")
	public  ModelAndView  test4(ModelAndView mv) {
		System.out.println("요청함수 test4()진입");
		//1.파라미터받기
		//2.비즈니스로직(->Service->DAO->myBatis->DB)
		
		
		//ModelAndView mv = new ModelAndView();
		//3.Model
		//ModelAndView객체.addObject(String attributeName, Object attributeValue);
		mv.addObject("USERNAME", "홍길동");
		
		//4.View
		mv.setViewName("t/home");
		return mv;
	}
	
	
	
	//--------------------------------------------------
	//View지정할 때 요청함수의 리턴유형이 ModelAndView인 경우
	//실습   http://localhost:9000/app/t/test3.do
	@RequestMapping("/t/test3")
	public ModelAndView test3(){
		System.out.println("요청함수 test3()함수호출성공");
		//1.파라미터받기
		//2.비즈니스로직수행(<->Service<->DAO<->myBatis<->DB)
		//3.Model
		//4.View
		ModelAndView mv = new ModelAndView();
		//ModelAndView객체.setViewName("뷰명")
		mv.setViewName("t/test1");  // /WEB-INF/views/t폴더/test1.jsp문서
		return mv;
	}
	
	
	//View지정할 때 요청함수의 리턴유형이 String인 경우
	//실습   http://127.0.0.1:9000/app/t/test2.do
	@RequestMapping("/t/test2")
	public String test2() {
		System.out.println("요청함수 test2()함수호출성공");
		//1.파라미터받기
		//2.비즈니스로직수행(<->Service<->DAO<->myBatis<->DB)
		//3.Model
		//4.View
		return "t/home";
	}
	
	
	//View지정할 때 요청함수의 리턴유형이 void인 경우
	//요청함수를 선언할 때에는  @RequestMapping("요청내용")
	//실습형식  http://ip주소:포트번호/컨텍스트패스/t/test1.do
	//실습       http://ip주소:9000/app/t/test1.do
	@RequestMapping("/t/test1")
	public void  test1(){
		System.out.println("요청함수 test1()함수호출성공");
		//할일
		//1.파라미터받기
		//2.비즈니스로직수행(<->Service<->DAO<->myBatis<->DB)
		//3.Model
		//4.View
		/*(우리의 경우는 do-context.xml문서에서 지정한)
		<beans:property name="prefix" value="/WEB-INF/views/" />
	    <beans:property name="suffix" value=".jsp" />
		    /WEB-INF/views/t폴더/test1.jsp문서
		 */
		
	}
	
	
	
}


















