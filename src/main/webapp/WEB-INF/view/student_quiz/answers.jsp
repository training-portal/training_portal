<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><spring:message code="title.answers"/></title>
    <c:import url="../fragment/head.jsp"/>
</head>
<body>
<c:import url="../fragment/navbar.jsp"/>
<div class="container">
    <h2><spring:message code="quiz.answers.for"/> '<c:out value="${quiz.name}"/>'</h2>
    <c:if test="${not empty questionsOneAnswer}">
        <h4 class="shifted-left"><spring:message code="quiz.questions.one.answer"/></h4>
        <c:forEach items="${questionsOneAnswer}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-md-9">
                        <h5><c:out value="${question.body}"/></h5>
                    </div>
                    <div class="col-md-2 offset-md-1">
                        <h6>${question.score} <spring:message code="quiz.passing.points"/></h6>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:forEach items="${quizAnswersSimple[question.questionId]}" var="answer">
                    <c:choose>
                        <c:when test="${answer.correct eq true}">
                            <div class="col-lg-8">
                                <div class="correct"><c:out value="${answer.body}"/></div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-lg-8">
                                <div class="incorrect"><c:out value="${answer.body}"/></div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${question.explanation ne null}">
                    <div>
                        <strong> <spring:message code="quiz.explanation"/>: </strong>
                        <c:out value="${question.explanation}"/>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsFewAnswers}">
        <h4 class="shifted-left"><spring:message code="quiz.questions.few.answers"/></h4>
        <c:forEach items="${questionsFewAnswers}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-md-9">
                        <h5><c:out value="${question.body}"/></h5>
                    </div>
                    <div class="col-md-2 offset-md-1">
                        <h6>${question.score} <spring:message code="quiz.passing.points"/></h6>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:forEach items="${quizAnswersSimple[question.questionId]}" var="answer">
                    <c:choose>
                        <c:when test="${answer.correct eq true}">
                            <div class="col-lg-8">
                                <div class="correct"><c:out value="${answer.body}"/></div>
                            </div>
                        </c:when>
                        <c:otherwise>
                            <div class="col-lg-8">
                                <div class="incorrect"><c:out value="${answer.body}"/></div>
                            </div>
                        </c:otherwise>
                    </c:choose>
                </c:forEach>
                <c:if test="${question.explanation ne null}">
                    <div>
                        <strong> <spring:message code="quiz.explanation"/>: </strong>
                        <c:out value="${question.explanation}"/>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsAccordance}">
        <h4 class="shifted-left"><spring:message code="quiz.questions.accordance"/></h4>
        <c:forEach items="${questionsAccordance}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-md-9">
                        <h5><c:out value="${question.body}"/></h5>
                    </div>
                    <div class="col-md-2 offset-md-1">
                        <h6>${question.score} <spring:message code="quiz.passing.points"/></h6>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:set var="leftSide" value="${quizAnswersAccordance[question.questionId].leftSide}" scope="page"/>
                <c:set var="rightSide" value="${quizAnswersAccordance[question.questionId].rightSide}" scope="page"/>
                <table class="col-lg-8 table-info">
                    <c:forEach items="${leftSide}" var="item" varStatus="status">
                        <tr>
                            <td style="width: 50%"><c:out value="${item}"/></td>
                            <td style="width: 50%"><c:out value="${rightSide[status.index]}"/></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${question.explanation ne null}">
                    <div>
                        <strong> <spring:message code="quiz.explanation"/>: </strong>
                        <c:out value="${question.explanation}"/>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsSequence}">
        <h4 class="shifted-left"><spring:message code="quiz.questions.sequence"/></h4>
        <c:forEach items="${questionsSequence}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-md-9">
                        <h5><c:out value="${question.body}"/></h5>
                    </div>
                    <div class="col-md-2 offset-md-1">
                        <h6>${question.score} <spring:message code="quiz.passing.points"/></h6>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <c:set var="correctList" value="${quizAnswersSequence[question.questionId].correctList}" scope="page"/>
                <table class="col-lg-8 table-info">
                    <c:forEach items="${correctList}" var="item" varStatus="status">
                        <tr>
                            <td style="width: 10%">${status.index + 1}</td>
                            <td style="width: 90%"><c:out value="${item}"/></td>
                        </tr>
                    </c:forEach>
                </table>
                <c:if test="${question.explanation ne null}">
                    <div>
                        <strong> <spring:message code="quiz.explanation"/>: </strong>
                        <c:out value="${question.explanation}"/>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    <c:if test="${not empty questionsNumber}">
        <h4 class="shifted-left"><spring:message code="quiz.questions.numerical"/></h4>
        <c:forEach items="${questionsNumber}" var="question">
            <div class="question-header">
                <div class="row">
                    <div class="col-md-9">
                        <h5><c:out value="${question.body}"/></h5>
                    </div>
                    <div class="col-md-2 offset-md-1">
                        <h6>${question.score} <spring:message code="quiz.passing.points"/></h6>
                    </div>
                </div>
            </div>
            <div class="question-answers">
                <table class="col-lg-8 table-info">
                    <tr>
                        <td style="width: 50%"><spring:message code="quiz.answer"/></td>
                        <td style="width: 50%">${quizAnswersNumber[question.questionId].correct}</td>
                    </tr>
                </table>
                <c:if test="${question.explanation ne null}">
                    <div>
                        <strong> <spring:message code="quiz.explanation"/>: </strong>
                        <c:out value="${question.explanation}"/>
                    </div>
                </c:if>
            </div>
        </c:forEach>
    </c:if>
    <button value="Back" class="btn btn-primary" onclick="window.history.go(-1);">
        <spring:message code="back"/>
    </button>
</div>
<br>
</body>
</html>
