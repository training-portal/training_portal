<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="duration" uri="/WEB-INF/custom_tags/formatDuration" %>
<%@ taglib prefix="localDateTime" uri="/WEB-INF/custom_tags/formatLocalDateTime" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz finished</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/training-portal-favicon.png"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/main.css">
</head>
<body>
<c:import url="../fragment/navbar.jsp"/>
<h2>Quiz finished</h2>
<div>You have finished <strong>${finishedQuiz.quizName}</strong> quiz from ${finishedQuiz.attempt} attempt.</div>
<div>Your result: ${finishedQuiz.result}/${finishedQuiz.score}</div>
<div>Time spent: <duration:format value="${finishedQuiz.timeSpent}"/></div>
<div>Finish date: <localDateTime:format value="${finishedQuiz.finishDate}"/></div>
<div>More information about this quiz <a href="/student/quizzes/${finishedQuiz.quizId}">here</a></div>
<div>
    <input onclick="window.history.go(-1);" type="button" value="Back"/>
</div>
</body>
</html>