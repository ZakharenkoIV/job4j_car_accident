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

    <title>Accident</title>
</head>
<jsp:include page="/WEB-INF/view/header.jsp"/>
<body>

<h3 class="text-center my-5">Таблица инцидентов</h3>
<div class="container mt-5">
    <c:if test="${accidents.size() == 0}">
        <h4 class="text-center my-5">В таблице пока нет записей</h4>
        <div class="text-center">
            <a href="<c:url value='/create'/>">
                <button type="button" class="btn btn-success">Добавить первый инцидент</button>
            </a>
        </div>
    </c:if>
    <c:if test="${accidents.size() != 0}">
        <a href="<c:url value='/create'/>">Добавить инцидент</a>
        <table class="table table-success table-striped table-bordered">
            <thead>
            <tr>
                <th scope="col" >#</th>
                <th scope="col" class="text-center">Name</th>
                <th scope="col" class="text-center">Text</th>
                <th scope="col" class="text-center">Address</th>
                <th scope="col" class="text-center">Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="i" begin="0" end="${accidents.size() - 1}" step="1">
                <tr>
                    <th scope="row" class="align-middle"><c:out value="${i + 1}"/></th>
                    <td width="20%" class="align-middle"><c:out value="${accidents.get(i).getName()}"/></td>
                    <td width="50%" class="align-middle"><c:out value="${accidents.get(i).getText()}"/></td>
                    <td width="30%" class="align-middle"><c:out value="${accidents.get(i).getAddress()}"/></td>
                    <td width="140" class="align-middle">
                        <div>
                            <a href="<c:url value='/update?id=${accidents.get(i).getId()}'/>">
                                <button type="button" class="btn btn-success" title="Изменить">
                                    Редактировать
                                </button>
                            </a>
                        </div>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </c:if>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>