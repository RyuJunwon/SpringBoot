<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>메인 페이지</title>
</head>
<body>
    <c:if test="${ sessionMemberId ne null }">
        <form action="insertBoard" method="POST">
            <a href="logout">로그아웃</a> 
            <a href="updateMember">회원정보수정</a> <br>
            <input type="text" name="content" placeholder="글 작성하세요" required>
            <input type="hidden" name="mid" value="${ sessionMemberId }">
            <input type="submit" value="글 작성">
        </form>
    </c:if>
    <c:if test="${ sessionMemberId eq null }">
        <form action="login" method="POST">
            ID <input type="text" name="mid" required> <br>
            PW <input type="password" name="mpw" required> <br>
            <a href="signup"> 회원가입 </a>
            <input type="submit" value="로그인"> 
        </form>
    </c:if>

    <hr>

    <c:if test="${ empty boardDatas }">
        <h1>출력할 글이 없습니다.</h1>
    </c:if>

    <c:if test="${ not empty boardDatas }">
    <table border="1">
        <tr>
        <th>글 번호</th>
        <th>작성자</th>
        <th>글 내용</th>
        </tr>

        <c:forEach var="boardData" items="${ boardDatas }"> 
            <tr>
            <td><a href="board?bid=${ boardData.bid }">${ boardData.bid }</a></td>
            <td>${ boardData.mid }</td>
            <td>${ boardData.content }</td>
            </tr>
        </c:forEach>
    </c:if>
    </table>
</body>
</html>