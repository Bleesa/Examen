<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.io.*,java.util.*,es.salesianos.model.*"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Inserting New Film</title>
</head>
<body>
<form action="LoadFilmsList" method="post">
		<input type="submit" value="Show List">
</form>

	<%
		List<Film> peliculas = (List<Film>) request.getAttribute("listAllFilms");
		pageContext.setAttribute("peliculas", peliculas);

		
	%>

	<form action="AddPelicula" method="post">
	
		<span>Title:</span><input type="text" name="TITTLE">
		<span>Director's Code:</span><input type="number" name="CODOWNER">
		<input type="submit" value="Send">
		
	</form>
	<table border="1">
		<thead>
			<tr>
				<td>Title</td>
				<td>CodOwner</td>
				<td>Erase</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach var="pelicula1" items="${listAllFilms}">
				<tr>
					<td><c:out value="${pelicula1.title}" /></td>
					<td><c:out value="${pelicula1.codDirector}" /></td>
					<td><a href="/deletePelicula?codPelicula=${pelicula1.COD}">DELETE</a>
					</td>
				</tr>
			</c:forEach>
				
		</tbody>
	</table>
</body>
</html>