<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isELIgnored="false"%>
<%@ page import="java.util.List" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Admin Dashboard</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
    <link href="${pageContext.servletContext.contextPath}/resources/css/admin.css" rel="stylesheet" type="text/css">
</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand" href="${pageContext.servletContext.contextPath}/dashboard">Welcome, ${sessionScope.username}</a>
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
                            <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/book">Add New Book</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/logout">Logout</a></li>
                            <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/admin/approvals">Admin Approvals</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>

    <form method="GET" action="${pageContext.servletContext.contextPath}/book/search">
        <div class="form-outline mb-4 d-flex flex-row">
            <input type="text" name="searchData" placeholder="Search by Id or Author" class="form-control" />
            <button type="submit" class="btn btn-primary">Search</button>
        </div>
    </form>

    <c:choose>
        <c:when test="${not empty sessionScope.email}">
            <div class="container mt-4">
                <h1 class="mb-4">List Of Books</h1>
                <div class="row">
                    <c:forEach var="book" items="${books}">
                        <c:if test="${not empty book}">
                            <div class="col-md-4">
                                <div class="card mb-4 shadow-sm">
                                    <img src="${book.coverImage}" class="card-img-top" alt="${book.title} Cover Image">
                                    <div class="card-body">
                                        <h5 class="card-title">${book.title}</h5>
                                        <p class="card-text"><strong>Author:</strong> ${book.author}</p>
                                        <p class="card-text"><strong>Publication:</strong> ${book.publication}</p>
                                        <p class="card-text"><strong>Genre:</strong> ${book.genre}</p>
                                        <p class="card-text"><strong>Description:</strong> ${book.description}</p>
                                        <p class="card-text"><strong>Rating:</strong> ${book.rating}</p>
                                        <h6 class="card-subtitle mb-2 text-muted">Price: $${book.price}</h6>
                                        <div class="d-flex justify-content-between align-items-center">
                                            <div>
                                                <a href="${pageContext.servletContext.contextPath}/book/${book.id}" class="btn btn-primary">Update</a>
                                            </div>
                                            <div>
                                                <a href="${pageContext.servletContext.contextPath}/delete?bookid=${book.id}" class="btn btn-primary">Remove</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:if>
                    </c:forEach>
                </div>
            </div>
        </c:when>
        <c:otherwise>
            <div>
                <h2>Login Required!</h2>
                <a href="${pageContext.servletContext.contextPath}/login">
                    <button class="btn btn-primary">Login</button>
                </a>
            </div>
        </c:otherwise>
    </c:choose>
</body>
</html>
