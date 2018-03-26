<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Quiz edit</title>
    <c:import url="../fragment/head.jsp"/>
</head>
<body>
<c:import url="../fragment/navbar.jsp"/>
<div class="container">
    <h2>Edit quiz</h2>
    <form:form action="/teacher/quizzes/${quiz.quizId}/edit" method="post" modelAttribute="quiz">
        <div class="form-group">
            <%--<label for="name">Name<span class="error">*</span>:</label>--%>
            <%--<input type="text" class="col col-md-6 form-control" id="name" name="name" value="${quiz.name}">--%>
            <form:label path="name" for="name">Name<span class="error">*</span>:</form:label>
            <form:input path="name" cssClass="col col-md-6 form-control" id="name" placeholder="Name"/>
            <form:errors path="name" cssClass="error"/>
        </div>
        <div class="form-group">
            <div>Passing time:</div>
            <div class="row">
                <div class="col col-md-2">
                    <label for="hours">Hours </label>
                    <input type="text" class="form-control" id="hours" name="hours" value="${hours}">
                </div>
                <div class="col col-md-2">
                    <label for="minutes">Minutes </label>
                    <input type="text" class="form-control" id="minutes" name="minutes" value="${minutes}">
                </div>
                <div class="col col-md-2">
                    <label for="seconds">Seconds </label>
                    <input type="text" class="form-control" id="seconds" name="seconds" value="${seconds}">
                </div>
            </div>
        </div>
        <div class="form-group">
            <%--<label for="description">Description:</label>--%>
            <%--<textarea class="col col-md-6 form-control" rows="6" name="description" id="description"--%>
                      <%--placeholder="Description">${quiz.description}</textarea>--%>
            <form:label path="description" for="description">Description:</form:label>
            <form:textarea path="description" cssClass="col col-md-6 form-control"
                           rows="6" id="description" placeholder="Description"/>
            <form:errors path="description" cssClass="error"/>
        </div>
        <div class="form-group">
            <%--<label for="explanation">Explanation:</label>--%>
            <%--<textarea class="col col-md-6 form-control" rows="6" name="explanation" id="explanation"--%>
                      <%--placeholder="Description">${quiz.explanation}</textarea>--%>
            <form:label path="explanation" for="explanation">Explanation</form:label>
            <form:textarea path="explanation" cssClass="col col-md-6 form-control"
                           rows="6" id="explanation" placeholder="Explanation"/>
            <form:errors path="explanation" cssClass="error"/>
        </div>
        <div class="row">
            <div class="col-2">
                <button class="btn btn-primary" onclick="window.history.go(-1);">Back</button>
            </div>
            <div class="col-3"></div>
            <div class="col-1">
                <input type="submit" class="btn btn-success" value="Save">
            </div>
        </div>
    </form:form>
</div>
</body>
</html>