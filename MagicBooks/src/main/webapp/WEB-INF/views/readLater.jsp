<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.util.List"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link
    href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
    rel="stylesheet"
    integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
    crossorigin="anonymous">
<script
    src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js"
    integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM"
    crossorigin="anonymous"></script>
<link href="resources/css/liked.css/" rel="stylesheet" type="text/css">

</head>
<body>
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <div class="container-fluid">

            <button class="navbar-toggler" type="button"
                data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
                aria-controls="navbarSupportedContent" aria-expanded="false"
                aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item"><a class="nav-link active"
                        aria-current="page" href="${pageContext.servletContext.contextPath}/index">Home</a></li>
                    <%
                    String id = (String) session.getAttribute("email");

                    if (id == null) {
                    %>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/login">Login</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/register">Register</a>
                    </li>
                    <%
                    }
                    if (id != null) {
                    %>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/likedbook/page">Liked books</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/dashboard">Dashboard</a>
                    </li>
                    <li class="nav-item"><a class="nav-link" href="${pageContext.servletContext.contextPath}/logout">Logout</a>
                    </li>
                    <%
                    }
                    %>
                </ul>
            </div>
        </div>
    </nav>
	<%
		String id2 = (String) session.getAttribute("email");

		if (id2 != null) {
		%>
<div class="container mt-4">
             <h1 class="mb-4">List Of Books</h1>
                 <div class="row">
                     <c:forEach var="book" items="${books}">
                         <div class="col-md-4">
                             <div class="card mb-4 shadow-sm">
                             <img src="${book.coverImage}" class="card-img-top" alt="${book.title} Cover Image">
                             <div class="card-body">
                               <h5 class="card-title">${book.title}</h5>
                               <p class="card-text"><strong>Author:</strong> ${book.author}</p>
                               <p class="card-text"><strong> Publication:</strong> ${book.publication}</p>
                               <p class="card-text"><strong>Genre:</strong> ${book.genre}</p>
                               <p class="card-text"><strong>Description:</strong> ${book.description}</p>
                               <p class="card-text"><strong>Rating:</strong> ${book.rating}</p>
                               <h6 class="card-subtitle mb-2 text-muted">Price: $${book.price}</h6>
                               <div class="d-flex justify-content-between align-items-center">
                                 <div>
                                   	<br> <a
                                   	href="${pageContext.servletContext.contextPath}/readlater/delete?bookid=${book.id}">
                                   	<button type="button" class="btn btn-primary">Remove</button></a>
                                 </div>
                                 <div>
                                   	<br> <a
                                    href="${pageContext.servletContext.contextPath}/likedbook?bookid=${book.id}">
                                   	<button type="button" class="btn btn-primary">Liked</button></a>
                                 </div>
                                 </div>
                               </div>
                             </div>
                           </div>
                         </c:forEach>
                     </div>
                 </div>
    	<%} else { %>

    		<div>
    			<h2>Please login first</h2>
    			<a href="${pageContext.servletContext.contextPath}/login">
    			<button >Login</button>
    			</a>
    		</div>
    	<%} %>
</body>
</html>