<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<!-- css/main.css 파일 불러오기 -->
<link href="${pageContext.request.contextPath }/resources/css/main.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/resources/css/inc.css" rel="stylesheet" type="text/css">
<script src="js/jquery-3.6.4.js"></script>
<script type="text/javascript">
	
	$(function() {
		
		// 페이지 로딩 시 AJAX 요청 수행
		$.ajax({
			url: "MemberSendAuthMail.me",
			data: {
				id: "${param.id}",
				email: "${param.email}"
			},
			dataType: "text"
			
		}); // ajax 끝
	});

</script>
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	<article>
		<h1>회원 가입 완료</h1>
		<h4>가입을 축하합니다. 포인트 1000점 적립 완료.</h4>
		<h4>이메일 인증을 반드시 수행한 후 로그인 가능합니다.</h4>
		<h3>
			<input type="button" value="홈으로" onclick="location.href='./'">
			<input type="button" value="로그인" onclick="location.href='MemberLoginForm.me'">
		</h3>
	</article>
</body>
</html>