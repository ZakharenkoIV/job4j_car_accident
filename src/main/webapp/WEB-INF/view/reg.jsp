<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <!-- Required meta tags -->
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous">

    <title>Registration</title>
</head>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<body>
<h3 class="text-center my-5">Регистрация</h3>
<div class="container mt-5">
    <form name='reg' action="<c:url value='/reg'/>" method='POST'>
        <div class="mb-3">
            <label for="inputName" class="form-label">Имя:</label>
            <input type="text" name="username" class="form-control" id="inputName">
        </div>
        <div class="mb-3">
            <label for="inputPass" class="form-label">Пароль:</label>
            <input type="password" name="password" class="form-control" id="inputPass">
        </div>
        <button type="submit" class="btn btn-primary">Зарегистрироваться</button>
    </form>
</div>
</body>
</html>