<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>회원가입 페이지</title>
</head>
<body>
        <form action="signup" method="POST">
            ID <input type="text" name="mid" required> <br>
            PW <input type="password" name="mpw" required> <br>
            <input type="submit" value="회원가입">
        </form>

        <br> <br>

        <a href="main"> 메인으로 돌아가기 </a>
</body>
</html>