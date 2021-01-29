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
				
				$("#mfrm").submit();
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
	<%--
		컨트롤러에서 
		mv.addObject("BDTO",bDto);			//상세정보
		mv.addObject("nowPage",nowPage);//보고싶은페이지(릴레이용)
	 --%>
	
	<a href="../index.jsp">HOME</a>
	<hr/>
	
	<h3>수정폼 modifyFrm</h3>
	<p>http://localhost:9000/app/board/modifyFrm.do</p>
	
	게시판정보=${BDTO}<br/>
	보고싶은페이지=${nowPage}


	<form method="POST" action="../board/modifyProc.do" id="mfrm" encType="multipart/form-data">
		<input type="hidden" name="oriBNo"   value="${BDTO.no}" />
		<input type="hidden" name="nowPage"  value="${nowPage}" />
		<table width="600" border="1" align="center">
			<tr>
				<td>글쓴이</td>
				<td>${sessionScope.USERID}</td>
			</tr>
			<tr>
				<td>제목</td>
				<td>
					<input type="text" name="title" id="title" value="${BDTO.title}" required="required"/>
				</td>
			</tr>
			<tr>
				<td>내용</td>
				<td>
					<textarea name="content" id="content"  required="required">${BDTO.content}</textarea>
				</td>
			</tr>
			<tr>
				<td>비밀번호</td>
				<td>
					<input type="password" name="pw" id="pw"  required="required"/>
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









