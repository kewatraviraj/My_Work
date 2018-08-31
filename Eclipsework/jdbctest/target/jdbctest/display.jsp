<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<html>
<head>
<title>Insert title here</title>
</head>
<body>
	<table align="center" width=18%>
		<tr>
			<td><h3>
					<c:out value="${requestScope.message}" />
				</h3></td>
		</tr>
		<c:forEach items="${requestScope.users}" var="emp">
			<tr>
				<td><c:out value="${emp.name}"></c:out></td>
				<td><a href="del?action=delete&id=${emp.id}">&#10008;</a></td>
			</tr>
		</c:forEach>
	</table>
</body>
</html>