<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.io.*,java.util.*,es.salesianos.model.*" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%

	List<Film> listAllFilms = (List<Film>)request.getAttribute("listAllFilms");
%>



<table border="1">
	<thead>
		<tr>
			<td>cod</td>
			<td>title</td>
			<td>asociate Actor</td>
		</tr>
	</thead>
	<tbody>
		<c:forEach var="film" items="${listAllFilms}">
			<tr>
				<td><c:out value="${film.cod}"/> </td>
				<td><c:out value="${film.tittle}"/> </td>
				<td><a href="/recoveryFilm?cod=${film.cod}">Asociate</a></td>
	    	</tr>
		</c:forEach>
	</tbody>
</table>
</body>
</html>