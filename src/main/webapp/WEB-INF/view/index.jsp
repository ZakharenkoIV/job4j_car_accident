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
<body>

<h3 class="text-center my-5">Таблица инцидентов</h3>
<div class="container mt-5">
    <a href="<c:url value='/create'/>">Добавить инцидент</a>
    <table class="table table-success table-striped">
        <thead>
        <tr>
            <th scope="col">#</th>
            <th scope="col">Name</th>
            <th scope="col">Text</th>
            <th scope="col">Address</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="i" begin="0" end="${accidents.size() - 1}" step="1">
            <tr>
                <th scope="row"><c:out value="${i + 1}"/></th>
                <td><c:out value="${accidents.get(i).getName()}"/></td>
                <td><c:out value="${accidents.get(i).getText()}"/></td>
                <td><c:out value="${accidents.get(i).getAddress()}"/></td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
</div>

<!-- Bootstrap Bundle -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
        crossorigin="anonymous">
</script>
</body>
</html>