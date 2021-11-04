<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

<div>
<!-- se carga el login de nuevo en la parte superior -->
<%@ include file = "index_login.jsp" %>
</div>

<%-- <%= request.getParameter("usuario")%>  --%>

<p style="text-align: center">El usuario no está registrado en el sistema</p>
</body>
</html>