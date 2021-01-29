package com.ourDo.service;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ourDo.dao.MemberDAO;
import com.ourDo.dto.MemberDTO;

//이 클래스는 member관련한 기능을 제공하는 서비스클래스이다
@Service
public class MemberService {
	//변수
	//MemberDAO의 변수를 선언하면서 자동주입
	@Autowired
	private MemberDAO memberDAO;
	
	//함수
	//로그인처리
	public void   loginProc(MemberDTO memberDto,
			                  HttpSession session) {
		System.out.println("MemberService-loginProc()진입");
		
		//파라미터로 MemberDTO가 필요한 이유는
		//서비스의 loginProc()호출할 때 map에 내용을 채워야 하므로.
		
		//파라미터로  HttpSession이  필요한 이유는
		//로그인에 성공한 유저의 정보를 세션에 담기위함.
		
		HashMap map = new HashMap();
		//map에 필요한 정보를 기억시킨다
		//map.put(Object key, Object value)
		System.out.println("memberDto.getId()="+memberDto.getId());
		System.out.println("memberDto.getPw()="+memberDto.getPw());
		map.put("id", memberDto.getId()); //user가 입력id
		map.put("pw", memberDto.getPw()); //user가 입력pw
		
		HashMap result = memberDAO.loginProc(map);
		//result에는  쿼리문의 실행결과가 담겨있다
		/* myBatis에 건네준 결과를 map으로 받을 때
		 * Map은 K는  컬럼명이  키값이 되고
			    V는  컬럼값이 된다
		   이 때, 컬럼의 명은 항상 대문자.	    
			SELECT  
				m_id		AS 	id,  맵.put("ID","hongid");
				m_pw		AS	pw,  맵.put("PW","1234");
		 */
		
		//쿼리문실행실패-> 로그인실패 -> 회원x
		if(result==null || result.size()==0) {
			
		}else {
		//쿼리문실행성공-> 로그인성공 ->회원정보를 가져왔다->회원이구나
		//->로그인처리를 해야겠구나
		//로그인한 유저의 정보를 보관해야겠구나
		//세션객체.setAttribute("id값", data)
			session.setAttribute("USERID", result.get("ID"));	//로그인한 유저의 id
			session.setAttribute("USERNAME",result.get("NAME") );	//로그인한 유저의 name
			session.setAttribute("USERNICK",result.get("NICK") );	//로그인한 유저의 nick
			
		}
	}
	
}














