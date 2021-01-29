<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			$("#loginBtn").click(function(){
				//폼의 유효성검사
				//여러분들이 하시고..
				
				//로그인처리
				$("#loginFrm").submit();
					
			});
		});
	</script>
</head>
<body>
 <a href="../index.jsp">HOME</a>
<hr/>
	<h3>loginFrm.jsp</h3>
	<p>http://localshot:9000/app/member/loginFrm.do</p>
	                    
	   http://localhost:9000/app/<br/>
	   http://localhost:9000/app/index.jsp<br/><br/>

	<%-- 로그인성공시  
			로그인한 유저의 정보 출력
			session.setAttribute("USERID", result.get("ID"));	//로그인한 유저의 id
			session.setAttribute("USERNAME",result.get("NAME") );	//로그인한 유저의 name
			session.setAttribute("USERNICK",result.get("NICK") );	//로그인한 유저의 nick
	
	<c:if  test="조건">
		조건충족시 코드
	</c:if>
	--%>
	<c:if  test="${ not empty sessionScope.USERID }">
		<table  border="1" width="300">
			<tbody>
				<tr>
					<td style="text-align:center;">
						<%-- 세션의 정보 sessionScope.키값 --%>
						${sessionScope.USERID}님 접속중(${sessionScope.USERNICK})
					</td>
				</tr>
				<tr>
					<td style="text-align:center;">
						<input type="button" id="logoutBtn"        value="로그아웃"/>
						<input type="button" id="acrticleListBtn"  value="글목록조회"/>
					</td>
				</tr>
			</tbody>
		</table>	
	</c:if>
	
		
		
	<%--  로그인전  혹은 로그인실패시  로그인폼 출력 --%>
	<c:if  test="${ empty sessionScope.USERID }">
		<form name="loginFrm" id="loginFrm" action="../member/loginProc.do" method="get">
			<table border="1" width="300">
				<tbody>
					<tr>
						<th>ID</th>
						<td>
							<input type="text" name="id" id="id"  required="required"/>	
						</td>		
					</tr>
					<tr>
						<th>비번</th>
						<td>
							<input type="passowrd" name="pw" id="pw"  required="required"/>	
						</td>		
					</tr>
					<tr>
						<td colspan="2">
							<input type="button" id="loginBtn" value="로그인"/>	
							<input type="reset"  value="취소"/>	
						</td>		
					</tr>
				</tbody>
			</table>
		</form>
  </c:if>
</body>
</html>








