<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%-- 이  문서는   컨트롤러요청함수에서  파라미터를 받는 실습을 위한
	     데이터 제공용  문서이다. --%>
	<h2>member.jsp</h2>
	   
	<p> http://localhost:9000/app/pm/test0.do </p>
	                           ../pm/test1.do   
	    http://localhost:9000/app/pm/test1.do      
	     
	<form name="frm1" id="frm1" method="GET" action="../pm/test4.do">
		<table border="1" width="400">
			<tbody>
				<tr>
					<th width="100">아이디</th>
					<td width="300">
						<input type="text" name="mid" id="mid" required="required"/>
					</td>
				</tr>
				<tr>
					<th width="100">비밀번호</th>
					<td width="300">
						<input type="password" name="mpw" id="mpw" required="required"/>
					</td>
				</tr>
				<tr>
					<th width="100">이름</th>
					<td width="300">
						<input type="text" name="mname" id="mname" required="required"/>
					</td>
				</tr>
				<tr>
					<th width="100">나이</th>
					<td width="300">
						<input type="number" name="mage" id="mage"
						       min="1"       max="130"  
						       required="required"/>
					</td>
				</tr>
					<tr>
					<th width="100">취미</th>
					<td width="300">
					  <label for="h1">낚시</label>
						<input type="checkbox" name="hobby" value="낚시" id="h1"/>
						
						<input type="checkbox" name="hobby" value="등산" id="h2" />등산
						<input type="checkbox" name="hobby" value="바둑"  id="h3"/>바둑
						<input type="checkbox" name="hobby" value="coding"  id="h4"/>코딩
						<input type="checkbox" name="hobby" value="요리"  id="h5"/>요리
						<input type="checkbox" name="hobby" value="game"  id="h6"/>게임
					</td>
				</tr>
				<tr>
					<td colspan="2" style="text-align:center;">
						<input type="submit" value="확인" />
						<input type="reset"  value="취소" />
					</td>
				</tr>
			</tbody>
		</table>
		
	</form>     
</body>
</html>









