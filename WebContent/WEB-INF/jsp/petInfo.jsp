<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<style>
body {
	text-align: center;
}

table, th, td {
	border: 1px solid black;
	text-align: center;
	margin-left: auto;
	margin-right: auto;
	margin-top: 20px;
}
</style>
</head>
<body>
	<div>
		<c:forEach var="reqPar" items="${reqPar}">
			<c:out value="${reqPar.key}" />:
  <c:out value="${reqPar.value}" />
		</c:forEach>

	</div>
	<h2>Results</h2>
	<c:if test="${fn:length(petList) == 0}">
		<p>*****Sorry, we don't find any pets you want*****</p>
	</c:if>

	<c:if test="${fn:length(petList) > 0}">
		<table>
			<tr id="tableHeader">
				<th>ID</th>
				<th>Name</th>
				<th>Owner</th>
				<th>Species</th>
				<th>Sex</th>
				<th>Birth Day</th>
				<th>Death Day</th>
			</tr>
			<c:forEach items="${petList}" var="pet">
				<tr>
					<td><c:out value="${pet.id}" /></td>
					<td><c:out value="${pet.name}" /></td>
					<td><c:out value="${pet.owner}" /></td>
					<td><c:out value="${pet.species}" /></td>
					<td><c:out value="${pet.sex}" /></td>
					<td><c:out value="${pet.birth}" /></td>
					<td><c:out value="${pet.death}" /></td>
				</tr>
			</c:forEach>
		</table>
	</c:if>
	<a href="springDirectory.jsp">Search Again</a>
</body>
</html>