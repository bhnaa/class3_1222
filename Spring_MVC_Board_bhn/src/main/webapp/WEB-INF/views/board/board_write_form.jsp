<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<link href="${pageContext.request.contextPath }/resources/css/inc.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/subpage.css" rel="stylesheet" type="text/css">
</head>
<body>
	<!-- 게시판 등록 -->
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<article id="writeForm">
		<h1>게시판 글 등록</h1>
		<!-- 파일 업로드 기능 enctype -->
		<form action="BoardWritePro.bo" name="writeForm" method="post" enctype="multipart/form-data">
			<table>
				<!-- board_name 값은 세션 아이디를 전달(hidden) -->
				<input type="hidden" name="board_name" value="${sessionScope.sid }" />
				<tr>
					<td class="write_td_left"><label for="board_subject">제목</label></td>
					<td class="write_td_right"><input type="text" id="board_subject" name="board_subject" required="required" /></td>
				</tr>
				<tr>
					<td class="write_td_left"><label for="board_content">내용</label></td>
					<td class="write_td_right">
						<textarea id="board_content" name="board_content" cols="40" rows="15" required="required"></textarea>
					</td>
				</tr>
				<tr>
					<td class="write_td_left"><label for="board_file">파일 첨부</label></td>
					<!-- 파일 첨부 형식은 input 태그의 type="file" 속성 사용 -->
					<td class="write_td_right"><input type="file" name="file" required="required" /></td>
				</tr>
			</table>
			<section id="btnArea">
				<input type="submit" value="등록">&nbsp;&nbsp;
				<input type="reset" value="다시쓰기">&nbsp;&nbsp;
				<input type="button" value="취소" onclick="history.back()">
			</section>
		</form>
	</article>
</body>
</html>






