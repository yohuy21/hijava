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
		//검색 클릭시 
		$("#sBtn").click(function(){
			//폼의 유효성검사
			//검색단어  id="word"			
			$("#sFrm").submit();
		});
		
	});
</script>		
</head>
<body>

<a href="../index.jsp">HOME</a>
<hr/>
	<%-- 검색부분 --%>	
	<form name="sFrm" id="sFrm" action="../board/searchInfo.do" method="GET">
		<table border="1" style="text-align:center;width:600px;">
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
	<%--  컨트롤러로부터 mv.addObject("LIST",list);        //게시물정보--%>
	<table border="1" style="text-align:center;width:600px;">
		<tr>
			<th style="width:60px;">번호</th>
			<th style="width:120px;">제목</th>
			<th style="width:120px;">작성일</th>
			<th style="width:120px;">조회수</th>
			<th style="width:120px;">작성자</th>
			<th style="width:60px;">첨부파일</th>
		</tr>
		
		<c:forEach items="${LIST}" var="board">
		<tr>
			<td>${board.no}</td>
			<td>
				<a href="../board/hitUp.do?oriNo=${board.no}&nowPage=${PAGEINFO.nowPage}">${board.title}</a>
			</td>
			<td>${board.wdate}</td>
			<td>${board.hit}</td>
			<td>${board.writer}</td>
			<td>${board.fcount}</td>
		</tr>		
		</c:forEach><%-- 반복문끝 --%>		
	</table>
	
	<%--	페이징 처리 
	mv.addObject("target",bDto.getTarget());
		mv.addObject("word",bDto.getWord());--%>
	<table border="1" style="text-align:center;width:600px;">
		<tr>
			<td align="center">
				
			<c:if test="${PAGEINFO.nowPage==1}">
				[prev]
			</c:if>
			<c:if test="${PAGEINFO.nowPage ne 1}">
				<a href="../board/searchInfo.do?target=${target}&word=${word}&nowPage=${PAGEINFO.nowPage-1}">[prev]</a>
			</c:if>

			<c:forEach begin="${PAGEINFO.startPage}" 
								 end  ="${PAGEINFO.endPage}"
								 var  ="page">
				<a href="../board/searchInfo.do?target=${target}&word=${word}&nowPage=${page}">[${page}]</a>
			</c:forEach>
			
			<c:if test="${PAGEINFO.nowPage==PAGEINFO.totalPage}">
				[next]
			</c:if>	
			<c:if test="${PAGEINFO.nowPage!=PAGEINFO.totalPage}">
				<a href="../board/searchInfo.do?target=${target}&word=${word}&nowPage=${PAGEINFO.nowPage+1}">[next]</a>
			</c:if>	
				
			</td>
		</tr>
	</table>

	
</body>
</html>
