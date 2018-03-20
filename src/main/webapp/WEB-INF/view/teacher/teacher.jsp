<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="localDate" uri="/WEB-INF/custom_tags/formatLocalDate" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <c:import url="../fragment/teacher-navbar.jsp"/>
</head>
<body>
<div class="container">
    <br>
    <h2>Hello, teacher! Welcome to the training portal!</h2>
    <div>
        <a href="/teacher/groups/create" class="btn btn-success">+ Create group</a>
        <a href="#" class="btn btn-success">+ Create quiz</a>
    </div>
    <h3>Teacher information</h3>
    <table class="table-home">
        <tr>
            <td class="table-home">Name</td>
            <td class="table-home">${teacher.lastName} ${teacher.firstName}</td>
        <tr>
            <td class="table-home">E-mail</td>
            <td class="table-home">${teacher.email}</td>
        </tr>
        <tr>
            <td class="table-home">Phone number</td>
            <td class="table-home">${teacher.phoneNumber}</td>
        </tr>
        <tr>
            <td class="table-home">Date of birth</td>
            <td class="table-home"><localDate:format value="${teacher.dateOfBirth}"/></td>
        </tr>
    </table>
    <h4>Login and password</h4>
    <table class="table-home">
        <tr>
            <td class="table-home">Login</td>
            <td class="table-home">${teacher.login}</td>
        </tr>
        <tr>
            <td class="table-home">Password</td>
            <td class="table-home">${teacher.password}</td>
        </tr>
    </table>
    <div>
        <a href="/teacher/edit-profile" class="btn btn-primary">Edit profile</a>
    </div>
</div>
</body>
</html>