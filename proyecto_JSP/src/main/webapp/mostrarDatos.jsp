<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Empleados</title>
</head>
<body>

<jsp:useBean id="nuevoEmple" class="bean.Empleado" scope="application"/>;
	<%-- <%bean.Empleado nuevoEmple = new bean.Empleado()%>; --%>
<jsp:setProperty property="nombre" name="nuevoEmple"/>
hola, <jsp:getProperty property="nombre" name="nuevoEmple"/>
</body>
</html>