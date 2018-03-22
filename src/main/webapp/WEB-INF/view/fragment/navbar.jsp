<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/student">
        <img src="/resources/training-portal-favicon.png" width="30" height="30"> Training portal
    </a>
    <button class="navbar-toggler" data-toggle="collapse" data-target="#navbarSupportedContent"
            aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div id="navbarSupportedContent" class="collapse navbar-collapse">
        <c:choose>
            <c:when test="${sessionScope.teacherId ne null}">
                <ul class="navbar-nav mr-auto mt-2 mt-lg-0">
                    <li class="nav-item">
                        <a class="nav-link" href="/teacher">Home</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/teacher/quizzes">Quizzes</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/teacher/groups">Groups</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/teacher/students">Students</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="/teacher/results">Results</a>
                    </li>
                </ul>
            </c:when>
            <c:when test="${sessionScope.studentId ne null}">
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
            </c:when>
            <c:otherwise>
                <div class="error">AUTHENTICATION ERROR</div>
            </c:otherwise>
        </c:choose>
        <ul class="navbar-nav justify-content-end">
            <li class="nav-item">
                <a href="/logout" class="nav-link">Log out</a>
            </li>
        </ul>
    </div>
</nav>