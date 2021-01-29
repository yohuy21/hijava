<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core"%>        
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script>
		$(document).ready(function(){
			//검색 클릭시 
			$("#sBtn").click(function(){
				//폼의 유효성검사
				//검색단어  id="word"			
				$("#sFrm").submit();
			});
			
			
			//글쓰기 버튼 클릭시
			$("#wBtn").on("click",function(){
				//폼의 액션~~~  name="wBtnFrm" id="wBtnFrm"
				//서브밋~~~할 수도 있고  다른 방법사용할 수 도 있고
				$("#wBtnFrm").attr("action","../board/writeFrm.do");
				$("#wBtnFrm").submit();
			});  
			
		});
	</script>		
</head>
<body>
	
	<a href="../index.jsp">HOME</a>
	<hr/>
	
	<h3>파일게시판목록 list</h3>
	<p>http://localhost:9000/app/board/list.do</p>
		
	PAGEINFO.nowPage=${PAGEINFO.nowPage}<br/><br/>	
		
	<%-- 검색부분 --%>	
	<form name="sFrm" id="sFrm" action="../board/searchInfo.do" method="GET">
		<table border="1" width="700">
			<tbody>
				<tr>
					<td align="center">
						<%-- 검색대상 --%>
						<select name="target" id="target">
							<option value="title" selected="selected">제목</option>
							<option value="content">내용</option>
							<option value="writer">글쓴이</option>
							<option value="both">제목+내용</option>
						</select>
						<%-- 검색단어 --%>
						<input type="text" name="word" id="word" required="required"/> 
						<%-- 검색버튼 --%>
						<input type="button" name="sBtn" id="sBtn" value="검색"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>

	<%-- 목록출력 --%>
	<form name="listFrm" id="listFrm">
		<table border="1" width="700">
			<tbody>
				<tr>
					<th width="50">번호</th>
					<th width="300">제목</th>
					<th width="100">글쓴이</th>
					<th width="150">작성일</th>
					<th width="50">조회수</th>
					<th width="50">첨부f</th>
				</tr>
				<%-- 반복문을 이용하여  출력   mv.addObject("LIST",list);//게시물정보 --%>
				<c:forEach var="data" items="${LIST}">
					<tr>
						<td width="50">${data.no}</td>
						<td width="300"><a href="../board/hitUp.do?oriNo=${data.no}&nowPage=${PAGEINFO.nowPage}">${data.title}</a></td>
						<td width="100">${data.id}</td>
						<td width="150">${data.wdate}</td>
						<td width="50">${data.hit}</td>
						<td width="50">${data.fcount}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</form>
		
	<%-- 페이징처리 출력  
		mv.addObject("PAGEINFO",pageInfo);//페이징정보--%>	
	<table border="1" width="700">
		<tbody>
			<tr>
				<td align="center">
					<%-- prev출력 --%>
					<c:if  test="${PAGEINFO.nowPage==1}">
					 [prev]
					</c:if>
					
					<c:if  test="${PAGEINFO.nowPage ne 1}">
					 <a href="../board/list.do?nowPage=${PAGEINFO.nowPage-1}">[prev]</a>
					</c:if>
					
					<%-- [1] [2] [3] [4] [5]출력--%>
					<c:forEach var="i"  begin="${PAGEINFO.startPage}" end="${PAGEINFO.endPage}">
					 <a href="../board/list.do?nowPage=${i}">[${i}]</a>
					</c:forEach>
					
					<%-- next출력 --%>
					<c:if  test="${PAGEINFO.nowPage==PAGEINFO.totalPage}">
					 [next]
					</c:if>
					
					<c:if  test="${PAGEINFO.nowPage!=PAGEINFO.totalPage}">
					 <a href="../board/list.do?nowPage=${PAGEINFO.nowPage+1}">[next]</a>
					</c:if>
				</td>
			</tr>
		</tbody>
	</table>

		
	<%-- 기타(글쓰기) --%>	
	<form name="wBtnFrm" id="wBtnFrm">
		<table border="1" width="700">
			<tbody>
				<tr>
					<td align="center">
						<input type="button"  value="글쓰기" id="wBtn"/>
					</td>
				</tr>
			</tbody>
		</table>
	</form>	
</body>
</html>

















