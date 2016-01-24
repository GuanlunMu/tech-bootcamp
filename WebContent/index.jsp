<%@ include file="/WEB-INF/jsp/head.jsp"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<%-- Redirected because we can't set the welcome page to a virtual URL. --%>
<style>
body {
	text-align: center;
}
</style>
<body>
	<h2>Pet Center</h2>
	<a href="user?mylocale=en">English </a> | <a href="user?mylocale=de">German </a>
	<h3> <spring:message code="user.title"/></h3>
	<ul>
		<li><a href="springDirectory.jsp">Spring Directory</a></li>
		<li><a href="ajaxDirectory.html">Ajax Directory</a></li>
		<li><a href = "addPet.jsp">Add Pet</a></li>
	</ul>
</body>
