<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>글입력</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
	
		$(document).ready(function(){
			
			//글쓰기쓰기 버튼클릭시
			$("#sBtn").click(function(){
				//alert('글쓰기쓰기');
				//	무결성 검사 하시고
				//  submit
				$("#wfrm").submit();
			});
			
			var cnt=1; //첨부파일의 개수를 저장하기위한  전역변수
			//첨부파일추가 버튼클릭시
			$("#aBtn").click(function(){
				cnt++;
				
				if(cnt==4){
					alert('첨부파일은 최대 3개까지 가능합니다');
					cnt=3;
					return;
				}
				
				//alert('첨부파일추가');
				var tr = '<tr><td>첨부파일</td><td>';
					 tr += '<input type="file" name="files" id="files'+cnt+'"/>';
					 tr += '</td></tr>';
				
				//어디에?  추가하자
				$("#copy").before(tr);
						
			});
			
			//첨부파일삭제 버튼클릭시
			$("#dBtn").click(function(){
				
				if(cnt==1){
					alert('한개는 반드시 있어야 해요');
					return;
				}
				
				var tr = $("#files"+cnt).parents("tr");
				tr.remove();
				
				cnt--;
			});
			
	
		});
	</script>
</head>
<body>
	
	<a href="../index.jsp">HOME</a>
	<hr/>
	
	<h3>글입력폼 writeFrm</h3>
	<p>http://localhost:9000/app/board/writeFrm.do</p>
		<%--  파일을 업로드하기위해서는
				반드시  encType="multipart/form-data"  
						 method="POST" 으로 지정해야 한다
				--%>
	<%--
		*로그인한 유저의 정보
		session.setAttribute("USERID", result.get("ID"));	//로그인한 유저의 id
		session.setAttribute("USERNAME",result.get("NAME") );	//로그인한 유저의 name
		session.setAttribute("USERNICK",result.get("NICK") );	//로그인한 유저의 nick
	 --%>			
 	<form method="POST" action="../board/writeProc.do" id="wfrm" encType="multipart/form-data"> 
		<table width="600" border="1">
			<tr>
				<td>글쓴이</td>
				<td>${sessionScope.USERID}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" id="title" required="required"/>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="content" id="content" required="required"></textarea>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pw" id="pw" required="required"/>
				</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
					<input type="button" value="추가" id="aBtn"/>
					<input type="button" value="삭제" id="dBtn"/>
				</td>
			</tr>
			<tr>
				<td>첨부파일</td>
				<td>
			 		<input type="file" name="files" id="files"/> 
				</td>
			</tr>
			<tr id="copy">
				<td colspan="2" align="center">
					<input type="button" id="sBtn" value="글쓰기"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>