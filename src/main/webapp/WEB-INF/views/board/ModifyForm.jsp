<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>Document</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		
		$(document).ready(function(){
			
			$("#mBtn").click(function(){
				//	무결성 검사하고
				
			});
			
			
			$("#dBtn").click(function(){
				
			});
			
			
			$("#aBtn").click(function(){
				
			});
			
		});
	</script>
</head>
<body>

	<form method="POST" action="" id="wfrm" encType="multipart/form-data">
		
		<table width="600" border="1" align="center">
			<tr>
				<td>글쓴이</td>
				<td>세션에서 출력</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" id="title" value=""/>
				</td>
			</tr>
			<tr>
				<td>본문</td>
				<td>
					<textarea name="content" id="content"></textarea>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pw" id="pw"/>
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
					<input type="button" id="mBtn" value="수정하기"/>
					<input type="button" value="취소"/>
				</td>
			</tr>
		</table>
	</form>
</body>
</html>









