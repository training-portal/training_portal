<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="/student">
        <img src="${pageContext.request.contextPath}/resources/training-portal-favicon.png"
             width="30" height="30">
        Training portal
    </a>
    <div class="collapse navbar-collapse">
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
        <ul class="navbar-nav justify-content-end">
            <li class="nav-item">
                <a href="/logout" class="nav-link">Log out</a>
            </li>
        </ul>
    </div>
</nav>
