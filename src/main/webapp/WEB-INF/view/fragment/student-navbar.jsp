<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/student">
        <img src="/resources/training-portal-favicon.png" width="30" height="30"> Training portal
    </a>
    <div class="collapse navbar-collapse">
        <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
            <li class="nav-item">
                <a class="nav-link" href="/student">Home</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/student/quizzes">Quizzes</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/student/teachers">Teachers</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/student/results">Results</a>
            </li>
        </ul>
        <ul class="navbar-nav justify-content-end">
            <li class="nav-item">
                <a href="/logout" class="nav-link">Log out</a>
            </li>
        </ul>
    </div>
</nav>
<script>
    var currentPath = window.location.pathname;
    var locationPath;
    if (currentPath === "/student") {
        locationPath = currentPath;
    } else {
        var url = currentPath.split("/");
        locationPath = "/" + url[1] + "/" + url[2];
    }
    $('ul li a[href="'+ locationPath + '"]').parent().addClass('active');
</script>
</body>
</html>
