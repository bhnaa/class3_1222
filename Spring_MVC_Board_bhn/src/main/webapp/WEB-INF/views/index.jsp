<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css/main.css 파일 불러오기 -->
<link href="${pageContext.request.contextPath }/resources/css/inc.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/main.css" rel="stylesheet" type="text/css">

</head>
<body>

	<header>
		<jsp:include page="inc/top.jsp"></jsp:include>
	</header>
	<article id="main">
		<h1>MVC 게시판 프로젝트</h1>
		<h3><a href="BoardWriteForm.bo">글쓰기</a></h3>
		<h3><a href="BoardList.bo">글목록</a></h3>
		<h3><a href="Main2.bo">샘플 메인페이지</a></h3>
		<hr>
		<h3><a href="BoardWriteForm.bo">자유게시판 글쓰기</a></h3>
		<h3><a href="BoardList.bo">자유게시판 글목록</a></h3>
		
		<hr>
		<h3>spring이 수정</h3>
	</article>
</body>
</html>