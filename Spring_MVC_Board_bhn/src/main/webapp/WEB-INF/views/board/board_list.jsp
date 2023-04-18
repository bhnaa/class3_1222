<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>      
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> 
<%-- JSTL에서 표시 방식(날짜 등) 변경하려면 JSTL의 fmt(format) 라이브러리 필요 --%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>MVC 게시판</title>
<style type="text/css">
	#listForm {
		width: 1024px;
		max-height: 610px;
		margin: auto;
	}
	
	h2 {
		text-align: center;
	}
	
	table {
		margin: auto;
		width: 1024px;
	}
	
	#tr_top {
		background: orange;
		text-align: center;
	}
	
	table td {
		text-align: center;
	}
	
	#subject {
		text-align: left;
		padding-left: 20px;
		
		/* 제목 길이 제한 (잘린 부분은 ... 으로 표시) */
		max-width: 450px;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
	
	#name {
		/* 작성자 길이 제한 (잘린 부분은 ... 으로 표시) */
		max-width: 100px;
		text-overflow: ellipsis;
		overflow: hidden;
		white-space: nowrap;
	}
	
	#pageList {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#emptyArea {
		margin: auto;
		width: 1024px;
		text-align: center;
	}
	
	#buttonArea {
		margin: auto;
		width: 1024px;
		text-align: right;
	}
	
	/*하이퍼링크 밑줄 제거*/
	a {
		text-decoration: none;
	}
</style>
<link href="${pageContext.request.contextPath }/resources/css/inc.css" rel="stylesheet" type="text/css">
</head>
<body>
	<header>
		<jsp:include page="../inc/top.jsp"></jsp:include>
	</header>
	
	<!-- 게시판 리스트 -->
	<h2>게시판 글 목록</h2>
	<section id="buttonArea">
		<input type="button" value="글쓰기" onclick="location.href='BoardWriteForm.bo'" />
	</section>
	<section id="listForm">
		<table>
			<tr id="tr_top">
				<td width="100px">번호</td>
				<td>제목</td>
				<td width="150px">작성자</td>
				<td width="150px">날짜</td>
				<td width="100px">조회수</td>
			</tr>
			<%-- 자바코드를 활용하여 글목록 표시 --%>
			<%
// 			// request객체의 getAttribute() 메서드를 호출하여 List 객체 가져오기
// 			List<BoardBean> boardList = (List<BoardBean>)request.getAttribute("boardList");
// 			// 향상된 for문을 활용하여 List 객체만큼 반복
// 			for(BoardBean board : boardList){
				%>
<!-- 				<tr> -->
<%-- 					<td><%=board.getBoard_num() %></td> --%>
<%-- 					<td id="subject"><a href="BoardDetail.bo?board_num=<%=board.getBoard_num() %>"><%=board.getBoard_subject() %></a></td> --%>
<%-- 					<td><%=board.getBoard_name() %></td> --%>
<%-- 					<td><%=sdf.format(board.getBoard_date()) %></td> --%>
<%-- 					<td><%=board.getBoard_readcount() %></td> --%>
<!-- 				</tr> -->
				<%
//			}
			%>
			
			
			<%-- JSTL과 EL 활용하여 글목록 표시를 위한 반복문 작성 --%>
	 	<c:forEach var="board" items="${boardList }">
		 		<tr>
					<td>${board.board_num }</td>
					<!-- 제목에 하이퍼링크 추가(BoardDetail.bo) => 글번호(board_num), 페이지번호(pageNum)전달 -->
					<td id="subject">
						<c:if test="${board.board_re_lev > 0}">
							<c:forEach var="i" begin="1" end="${board.board_re_lev }">
								&nbsp;&nbsp;
							</c:forEach>
							<img src="images/re.gif">
						</c:if>
						<a href="BoardDetail.bo?board_num=${board.board_num }&pageNum=${pageNum }">${board.board_subject }</a>
					</td>
					
					<td id="name">${board.board_name }</td>
					<%-- JSTL fmt 라이브러리를 활용하여 날짜 포맷 변경 시 --%>
					<td><fmt:formatDate value="${board.board_date }" pattern="yy-MM-dd HH:mm" /></td>
					<td>${board.board_readcount }</td>
				</tr>
		</c:forEach>
		</table>
	</section>
	<section id="pageList">
	
		<c:choose>
			<c:when test="${pageNum > 1 }">
				<input type="button" value="이전" onclick="location.href='BoardList.bo?pageNum=${pageNum - 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="이전">
			</c:otherwise>
		</c:choose>
						
		<%-- 반복문으로 startPage ~ endPage 표시 --%>
		<c:forEach var="num" begin="${pageInfo.startPage }" end="${pageInfo.endPage }" >
			<c:choose>
				<c:when test="${pageNum eq num }">
					<b>${num }</b>
				</c:when>
				<c:otherwise>
					<a href="BoardList.bo?pageNum=${num }">${num }</a>
				</c:otherwise>
			</c:choose>
		</c:forEach>
		
		<c:choose>
			<c:when test="${pageNum < pageInfo.maxPage }">
				<input type="button" value="다음" onclick="location.href='BoardList.bo?pageNum=${pageNum + 1}'">
			</c:when>
			<c:otherwise>
				<input type="button" value="다음">
			</c:otherwise>
		</c:choose>

	</section>
</body>
</html>


