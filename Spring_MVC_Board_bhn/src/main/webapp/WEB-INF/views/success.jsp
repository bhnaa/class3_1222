<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%-- 전달받은 메세지(msg속성) 출력 후 지정된 페이지(target)로 이동하기 (자바스크립트) --%>
<script>
	alert("${msg}");
	location.href = "${target}";
</script>