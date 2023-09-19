<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>글 상세 페이지</title>
</head>
<body>

    <c:if test="${ sessionMemberId ne boardData.mid }">
        작성자 : <input type="text" name="mid" value="${ boardData.mid }" disabled> <br>
        글내용 : <input type="text" name="content" value="${ boardData.content }" disabled> <br>
    </c:if>

    <c:if test="${ sessionMemberId eq boardData.mid }">
        <form action="#" method="POST">
            <input type="hidden" name="bid" value="${ boardData.bid }">
            작성자 : <input type="text" name="mid" value="${ boardData.mid }" disabled> <br>
            글내용 : <input type="text" name="content" value="${ boardData.content }"> <br>
        <input type="submit" value="글 수정하기" onclick="javascript: form.action='/updateBoard';">
        <input type="submit" value="글 삭제하기" onclick="javascript: form.action='/deleteBoard';"> <br>
    </form>
    </c:if>
    <a href="main"> 메인으로 돌아가기 </a>
</body>
</html>