<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="false"%>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Add Book</title>

  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">

  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.bundle.min.js" integrity="sha384-MrcW6ZMFYlzcLA8Nl+NtUVF0sA7MsXsP1UyJoMp4YLEuNSfAP+JcXn/tWtIaxVXM" crossorigin="anonymous"></script>
 <link href="resources/css/addBook.css/" rel="stylesheet" type="text/css">

</head>
<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
    <a class="navbar-brand" href="admin">Welcome, ${sessionScope.username}</a>
		<div class="container-fluid">

			<button class="navbar-toggler" type="button"
				data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<span class="navbar-toggler-icon"></span>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav me-auto mb-2 mb-lg-0">
					<li class="nav-item"><a class="nav-link active"
						aria-current="page"
						href="${pageContext.servletContext.contextPath}/">Home</a></li>
					<%
					String id = (String) session.getAttribute("email");

					if (id == null) {
					%>
					<li class="nav-item"><a class="nav-link" href="login">Login</a>
					</li>
					<li class="nav-item"><a class="nav-link" href="register">Register</a>
					</li>
					<%
					}
					if (id != null) {
					%>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/logout">Logout</a>
					</li>
					</form>
					<li class="nav-item"><a class="nav-link"
						href="${pageContext.servletContext.contextPath}/admin">Admin</a>
					</li>
					<%
					}
					%>
				</ul>
			</div>
		</div>
	</nav>
<div class="container">
    <div>
        <div class="wrapper fadeInDown">
            <div id="fadeIn first">
                <h2 class='book'>Add New Book</h2>
            </div class=".form-wrapper">
            <br/>
            <form action="${pageContext.request.contextPath}/book" method="POST">
                <input type='text' id='title' class="fadeIn second" name='title'
                placeholder="Title" value=""/>
                <input type='text' id='author' class="fadeIn third" name='author'
                placeholder="Author" value=""/>
                <input type='text' id='publication' class="fadeIn fourth" name='publication'
                placeholder="Publication" value=""/>
                <input type='text' id='genre' class="fadeIn fifth" name='genre'
                placeholder="Genre" value=""/>
                <input type='text' id='description' class="fadeIn sixth" name='description'
                placeholder="Description" value=""/>
                <input type='text' id='coverimage' class="fadeIn seventh" name='coverimage'
                placeholder="Cover Image" value=""/>
                <input type='number' id='rating' class="fadeIn eighth" name='rating'
                placeholder="Rating" value="" step="0.1"/>
                <input type='number' id='price' class="fadeIn ninth" name='price'
                placeholder="Price" value="" step="0.01"/>

                <input type="submit" class="btn-login" value="Add Book" />
            </form>
        </div>
    </div>
</div>
</body>
</html>