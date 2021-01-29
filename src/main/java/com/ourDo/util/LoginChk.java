package com.ourDo.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

//이 클래스는   컨트롤러가 실행되기  전에 인터셉터해서  먼저 실행하게 될 클래스
// USER가  요청하면 -> 인터셉터클래스호출실행-> 컨트롤러(요청함수)호출실행

/* 인터셉터클래스가 되기 위해서는
 * 1. HandlerInterceptorAdapter를 상속받아야 한다
 * 2. (여기에서는) preHandle함수를 오버라이딩해야 한다
 */
public class LoginChk extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, 
							 HttpServletResponse response, 
							 Object handler)
			throws Exception {
		//이 함수가 true를   반환하면   컨트롤러가 실행
		//이 함수가 false를 반환하면   컨트롤러가 실행x
	
		/**로그인한 유저의 정보
		session.setAttribute("USERID", result.get("ID"));	//로그인한 유저의 id
		session.setAttribute("USERNAME",result.get("NAME") );	//로그인한 유저의 name
		session.setAttribute("USERNICK",result.get("NICK") );	//로그인한 유저의 nick*/
		HttpSession  session= request.getSession();
		String id = (String)session.getAttribute("USERID");
		
		if( id==null || id.length()==0 ) { //로그인이 안되었다
			
			//로그인이 아니되었으니 로그인폼페이지로  강제 이동시켜겠다
			response.sendRedirect("../member/loginFrm.do");
			return false;
		}else {//로그인이 되었다
			return true;
		}
	}

	
}












