<%@ page contentType="text/html;charset=UTF-8" %>
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

    <title>Edit accident</title>
</head>
<body>

<h3 class="text-center my-5">Редактировать инцидент</h3>
<div class="container mt-5">
    <form action="<c:url value='save?id=${accident.id}'/>" method='POST'>
        <div class="mb-3">
            <label for="inputType" class="form-label">Тип:</label>
            <select name="type.id" class="form-control" id="inputType">
                <c:forEach var="type" items="${types}">
                    <option value="${type.id}"
                            <c:if test="${type.id == accident.type.id}"> selected="selected" </c:if>
                    >${type.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="inputName" class="form-label">Название:</label>
            <input type="text" name="name" class="form-control" id="inputName" value="${accident.name}">
        </div>
        <div class="mb-3">
            <label for="inputText" class="form-label">Описание:</label>
            <input type="text" name="text" class="form-control" id="inputText" value="${accident.text}">
        </div>
        <div class="mb-3">
            <label for="inputRules" class="form-label">Тип:</label>
            <select name="rIds" class="form-control" id="inputRules" multiple>
                <c:forEach var="rule" items="${rules}">
                    <option value="${rule.id}"
                            <c:forEach var="aRule" items="${accident.rules}">
                                <c:if test="${rule.id == aRule.id}"> selected="selected" </c:if>
                            </c:forEach>
                    >${rule.name}</option>
                </c:forEach>
            </select>
        </div>
        <div class="mb-3">
            <label for="inputAddress" class="form-label">Адрес:</label>
            <input type="text" name="address" class="form-control" id="inputAddress" value="${accident.address}">
        </div>
        <button type="submit" class="btn btn-primary">Сохранить</button>
    </form>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>