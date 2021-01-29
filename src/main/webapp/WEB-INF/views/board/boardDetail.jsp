<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ko">
<head>
	<meta charset="UTF-8">
	<title>상세보기</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			
			//수정버튼 클릭시
			$("#mBtn").click(function(){
				$(location).attr("href","../board/modifyFrm.do?oriNo=${BDTO.no}&nowPage=${nowPage}"); //속성값 설정
			});
			
			//삭제버튼 클릭시
			$("#dBtn").click(function(){
				//글을 삭제하기 위해서는 글의 비밀번호와 일치해야만 삭제할 수 있다.
				var pw = window.prompt("비밀번호를 입력하세요","");
				
				//비밀번호는 노출되면 아니되므로  post 방식을 적용하여
				//삭제요청을 해야한다.---이때 사용방식?????? 생각해보세요
				//$(form요소의 하위요소를 선택자).val();  //요소의 value를  get
				//$(form요소의 하위요소를 선택자).val(value);  //요소의 value를  set
				$("#pw").val(pw);  //요소의 value를  set
				$("#delFrm").submit();
			});
			
			//목록보기버튼 클릭시
			$("#lBtn").click(function(){
				//js의 방식을 이용
				//객체명.속성명					//속성값 리턴받기
				//객체명.속성명 = 값;   //속성값 설정
				//location.href="default.htm"
				window.location.href="../board/list.do?nowPage=${nowPage}";
				
				//jQuery의 방식을 이용
				//$(location).attr("속성명"); //속성값 리턴받기
				//$(location).attr("속성명","값"); //속성값 설정
				//$(location).attr("href","../board/list.do?nowPage=${nowPage}"); //속성값 설정
			});
			
		});
	</script>
</head>
<body>
	
	<a href="../index.jsp">HOME</a>
	<hr/>
	
	<h3>상세조회 boardDetail</h3>
	<p>http://localhost:9000/app/board/detail.do</p>
	
	게시판정보=${BDTO}<br/>
	보고싶은페이지=${nowPage}
	
	<%-- 	상세 출력	
				컨트롤러에서  
				request.setAttribute("nowPage",nowPage); //보고싶은페이지
				request.setAttribute("BDTO",bDto); //게시판정보
	--%>
	<table border="1" style="text-align:center;width:600px;">
		<tr>
			<th style="width:60px;">글번호</th>
			<td>${BDTO.no}</td>
		</tr>
		<tr>
			<th style="width:120px;">작성자</th>
			<td>${BDTO.id}</td>
		</tr>
		<tr>
			<th style="width:120px;">작성일</th>
			<td>${BDTO.wdate}</td>
		</tr>
		<tr>
			<th style="width:120px;">조회수</th>
			<td>${BDTO.hit}</td>
		</tr>
		<tr>
			<th style="width:120px;">제목</th>
			<td>${BDTO.title}</td>
		</tr>
		<tr>
			<th style="width:60px;">본문</th>
			<td>${BDTO.content2}</td>
		</tr>
	</table>
	
	
	<%-- 	첨부 파일출력 
	       컨트롤러에서  request.setAttribute("FILELIST", fileList);--%>
	<table border="1" style="text-align:center;width:600px;">
  <c:forEach var="fData" items="${FILELIST}">
		<tr>
			<td style="text-align:center;">
				<a href="../board/downLoadFile.do?f_no=${fData.no}">${fData.name}</a>
				( ${fData.fsize2} Byte)
			</td>
		</tr>
	</c:forEach>
	</table>
	
	<%--	기타 : 목록보기/ 수정하기 / 삭제하기	--%>
	<table border="1" style="text-align:center;width:600px;">
		<tr>
			<td style="text-align:center;">
				<input type="button" id="lBtn" value="목록보기">
				<%-- 로그인한 회원 본인이 쓴 글이라면  수정하기/삭제하기 버튼이 보이게.. --%>
				<c:if  test="${sessionScope.USERID==BDTO.id}">
					<input type="button" id="mBtn" value="수정하기">
					<input type="button" id="dBtn" value="삭제하기">
				</c:if>
			</td>
		</tr>	
	</table>	
	
	<!-- 삭제용 폼 -->
	<form id="delFrm" action="../board/del.do" method="POST">
		<input type="hidden" name="pw" 		 id="pw" 	 />
		<input type="hidden" name="oriBNo" id="oriBNo" value="${BDTO.no}"/>
	</form>
	
</body>
</html>












