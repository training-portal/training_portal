<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="duration" uri="/WEB-INF/custom_tags/formatDuration" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Continue</title>
    <link rel="shortcut icon" type="image/png" href="${pageContext.request.contextPath}/resources/training-portal-favicon.png"/>
    <link type="text/css" rel="stylesheet" href="../../../resources/main.css">
</head>
<body>
<c:import url="../fragment/navbar.jsp"/>
<h2>Attention</h2>
<div>
    You should continue <strong>${sessionScope.currentQuiz.name}</strong> quiz.
    <a href="/student/quizzes/${sessionScope.currentQuiz.quizId}/passing">Continue</a>
</div>
<div>
    Or you can finish <strong>${sessionScope.currentQuiz.name}</strong> quiz.
    <a href="/student/quizzes/${sessionScope.currentQuiz.quizId}/congratulations">Finish</a>
</div>
<div>You have passed only ${sessionScope.currentQuestionSerial}/${sessionScope.questionsNumber} questions</div>
<div>Time left: <duration:format value="${sessionScope.timeLeft}"/></div>
</body>
</html>