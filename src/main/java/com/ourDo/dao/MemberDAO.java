package com.ourDo.dao;

import java.util.HashMap;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

//이 클래스는
//DB의  member테이블과   연동시  필요한 함수를 선언한 클래스 

//myBatis를 사용하는  DAO 클래스를 만들기 위해서는
// 반드시   SqlSessionDaoSupport을 상속받아서 제작해야 한다
// 이 클래스가   커넥션 풀을 이용해서   connection받고,
//   스테이트먼트를 만들어서 관리하는 클래스이다
// 이 클래스가 없으면 myBatis를 이용해서  db에 접속할 수 없다
public class MemberDAO  extends SqlSessionDaoSupport {
	
	//변수
	/* 스테이트먼트역할 할  SqlSessionTemplate를 자동주입해서 사용  */
	@Autowired
	private SqlSessionTemplate  session;
	
	//함수
	
	//로그인처리
	public HashMap  loginProc(HashMap map) {
		//MemberService에서  아래와 같이 데이터가 담겨졌다
		//map.put("id", memberDto.getId()); //user가 입력id
		//map.put("pw", memberDto.getPw()); //user가 입력pw
		//확인용 id와 pw출력
		System.out.println("MemberDAO-loginProc()id="+map.get("id"));
		System.out.println("MemberDAO-loginProc()pw="+map.get("pw"));
		
		//session.메소드명("mapper의 네임스페이스명.실행쿼리문요소의id명",Object parameter);
		HashMap result = (HashMap)session.selectOne("member.loginProc", map);
		return result;
		/* myBatis에 건네준 결과를 map으로 받을 때
		 * Map은 K는  컬럼명이  키값이 되고
			    V는  컬럼값이 된다
		   이 때, 컬럼의 명은 항상 대문자.	    
			SELECT  
				m_id		AS 	id,  맵.put("ID","hongid");
				m_pw		AS	pw,  맵.put("PW","1234");
		 */
		
	}
	
	//회원수 조회
	public  int   getMemberCnt(){
		//session.메소드명("mapper의 네임스페이스명.실행쿼리문요소의id명");
		//session.메소드명("mapper의 네임스페이스명.실행쿼리문요소의id명",Object parameter);
		
		/*session.selectOne()메소드는  
		  select쿼리문의 실행결과로 리턴받는 레코드의 수가 1행
		  
		  session.selectList()메소드는  
		  select쿼리문의 실행결과로 리턴받는 레코드의 수가 다수행
		  */
		int cnt = session.selectOne("member.memberCnt");
		return cnt;
		
	}
	
}









