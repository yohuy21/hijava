<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
	<h1>home.jsp</h1>
	<!-- html주석문 -->
	<%-- jsp주석문  --%>
	<%-- controller에서  모델이 전달된다 
			  여기에서는  com.ourDo.controller.TestController.java의
			  test4()요청함수내에서 
			  mv.addObject("USERNAME", "홍길동");
	--%>
	<p>USERNAME : ${USERNAME}</p>
	<P>  The time on the server is ${serverTime}. </P>
	<P>  현재  시간은  ${serverTime1}. </P>
	<P>  나이 : ${USERAGE}</P>
</body>
</html>






