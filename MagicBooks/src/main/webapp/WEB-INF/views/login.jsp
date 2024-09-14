<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link href="${pageContext.servletContext.contextPath}/resources/css/login.css" rel="stylesheet" type="text/css">
    <style>
        .error {
            color: red;
        }
    </style>
</head>
<body>

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <div class="container-fluid">
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item"><a class="nav-link active" aria-current="page" href="${pageContext.servletContext.contextPath}/">Home</a></li>
                <c:choose>
                    <c:when test="${empty sessionScope.email}">
                        <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/login">Login</a></li>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/register">Register</a></li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
                        <li class="nav-item">
                            <c:out value="${sessionScope.username}" />
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="wrapper fadeInDown">
        <div id="formContent">
            <div class="fadeIn first">
                <h2 class="sign">Sign In!</h2>
                <div>
                    <span class="error">
                        <c:if test="${not empty error}">
                            ${error}
                        </c:if>
                    </span>
                </div>
            </div>
            <br />
            <form action="${pageContext.servletContext.contextPath}/login" method="POST">
                <!-- CSRF Token -->
                <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>

                <div class="mb-3">
                    <input type="text" id="login" class="form-control" name="email" placeholder="Email" required/>
                </div>
                <div class="mb-3">
                    <input type="password" id="password" class="form-control" name="password" placeholder="Password" required/>
                </div>
                <div class="mb-3">
                    <select name="role" class="form-select" required>
                        <c:forEach items="${roles}" var="role">
                            <option value="${role}">${role}</option>
                        </c:forEach>
                    </select>
                </div>
                <button type="submit" class="btn btn-primary">Log In</button>
            </form>
        </div>
    </div>
</div>
</body>
</html>
