<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Questions</title>
    <c:import url="../fragment/head.jsp"/>
</head>
<body>
<c:import url="../fragment/navbar.jsp"/>
<div class="container">
    <h2>Answers for quiz '${quiz.name}'</h2>
    <c:if test="${not empty questionsOneAnswer}">
        <h4 class="shifted-left">Questions with one correct answer</h4>
        <c:forEach items="${questionsOneAnswer}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-7">
                        <h5>${question.body}</h5>
                    </div>
                    <div class="col-2">
                        <h6>${question.score} points</h6>
                    </div>
                    <div class="col-1">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-edit"></i> Edit</a>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-trash-o"></i> Delete</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:forEach items="${quizAnswersSimple[question.questionId]}" var="answer">
                    <c:choose>
                        <c:when test="${answer.correct eq true}">
                            <div class="col-6">
                                <div class="correct">${answer.body}</div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-6">
                                <div class="incorrect">${answer.body}</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <div><strong> Explanation: </strong>${question.explanation}</div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsFewAnswers}">
        <h4 class="shifted-left">Questions with few correct answers</h4>
        <c:forEach items="${questionsFewAnswers}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-7">
                        <h5>${question.body}</h5>
                    </div>
                    <div class="col-2">
                        <h6>${question.score} points</h6>
                    </div>
                    <div class="col-1">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-edit"></i> Edit</a>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-trash-o"></i> Delete</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:forEach items="${quizAnswersSimple[question.questionId]}" var="answer">
                    <c:choose>
                        <c:when test="${answer.correct eq true}">
                            <div class="col-6">
                                <div class="correct">${answer.body}</div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-6">
                                <div class="incorrect">${answer.body}</div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <div><strong> Explanation: </strong>${question.explanation}</div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsAccordance}">
        <h4 class="shifted-left">Accordance questions</h4>
        <c:forEach items="${questionsAccordance}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-7">
                        <h5>${question.body}</h5>
                    </div>
                    <div class="col-2">
                        <h6>${question.score} points</h6>
                    </div>
                    <div class="col-1">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-edit"></i> Edit</a>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-trash-o"></i> Delete</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:set var="leftSide" value="${quizAnswersAccordance[question.questionId].leftSide}" scope="page"/>
                <c:set var="rightSide" value="${quizAnswersAccordance[question.questionId].rightSide}" scope="page"/>
                <table class="col-6 table-info">
                    <c:forEach items="${leftSide}" var="item" varStatus="status">
                        <tr>
                            <td>${item}</td>
                            <td>${rightSide[status.index]}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div><strong> Explanation: </strong>${question.explanation}</div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsSequence}">
        <h4 class="shifted-left">Sequence questions</h4>
        <c:forEach items="${questionsSequence}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-7">
                        <h5>${question.body}</h5>
                    </div>
                    <div class="col-2">
                        <h6>${question.score} points</h6>
                    </div>
                    <div class="col-1">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-edit"></i> Edit</a>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-trash-o"></i> Delete</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:set var="correctList" value="${quizAnswersSequence[question.questionId].correctList}" scope="page"/>
                <table class="col-6 table-info">
                    <c:forEach items="${correctList}" var="item" varStatus="status">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${item}</td>
                        </tr>
                    </c:forEach>
                </table>
                <div><strong> Explanation: </strong>${question.explanation}</div>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsNumber}">
        <h4 class="shifted-left">Questions with numerical answers</h4>
        <c:forEach items="${questionsNumber}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-7">
                        <h5>${question.body}</h5>
                    </div>
                    <div class="col-2">
                        <h6>${question.score} points</h6>
                    </div>
                    <div class="col-1">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-edit"></i> Edit</a>
                        </div>
                    </div>
                    <div class="col-2">
                        <div class="shifted-down">
                            <a href="#"><i class="fa fa-trash-o"></i> Delete</a>
                        </div>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <table class="col-6 table-info">
                    <tr>
                        <td>Answer</td>
                        <td>${quizAnswersNumber[question.questionId].correct}</td>
                    </tr>
                </table>
                <div><strong> Explanation: </strong>${question.explanation}</div>
            </div>
        </c:forEach>
    </c:if>
    <button value="Back" class="btn btn-primary" onclick="window.history.go(-1);">Back</button>
</div>
<br>
</body>
</html>