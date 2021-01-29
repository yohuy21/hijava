<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>memberInfo.jsp문서</h2>
	<%--
	ParameterController에서  
	모델로 넘어온 데이터를 받아 출력예정
	request객체.setAttribute("id값", data)
	
	req.setAttribute("MNAME", mname);//이름
	req.setAttribute("MAGE", mage);  //나이
	
	req.setAttribute("TDTO", tDTO); //취미,이름, 나이 전부 포함
	 --%>
	<p>이름:${MNAME}</p>
	<p>나이:${MAGE}</p>
	<hr/>
	*DTO형태로 받은 모델DATA출력
	<p>이름:${TDTO.mname}</p>
	<p>나이:${TDTO.mage}</p>
	<p>취미:${TDTO.hobby}</p>
	
	<%-- 
	<c:forEach var="변수명"  items="배열명또는 컬렉션명" >
	</c:forEach> 
	--%>
	
	<c:forEach var="h"  items="${TDTO.hobby}" >
		${h}<br/>
	</c:forEach>
	
	<hr/>
	
	<p>jstl의  out태그를 이용한 i값출력</p>	
	<c:forEach var="i" begin="0" end="4" step="1">  
   <c:out value="${i}"/><br/>  
  </c:forEach> 	
  
  <p>el을 이용한 i값출력${i}</p>	
	<c:forEach var="i" begin="0" end="4" step="1">  
   ${i}<br/>  
  </c:forEach>
		
	<%--	
		for(int i=0; i<5 ;i++) {
			syso(i);
		}
	 --%>
	</p> 
</body>
</html>







