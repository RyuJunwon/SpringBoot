<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원 정보 수정 페이지</title>
</head>
<body>
    <form action="#" method="POST">
        ID <input type="text" name="mid" value="${ sessionMemberId }" readonly> <br>
        PW <input type="password" name="mpw"> <br>
    <input type="submit" value="정보 수정" onclick="javascript: form.action='/updateMember';">
    <input type="submit" value="회원 탈퇴" onclick="javascript: form.action='/deleteMember';"> <br>
</body>
</html>