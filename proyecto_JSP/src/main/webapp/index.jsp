<%@page import="java.util.Calendar"%>
<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Bienvenida</title>
</head>
<link rel="stylesheet" href="css/HojaDeEstilo1.css"></link>
<body>



<%

//creo la fecha actual
java.util.Calendar dateNow = java.util.Calendar.getInstance();

int day = dateNow.get(java.util.Calendar.DAY_OF_WEEK);
String dias []= {"Domingo", "Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado"};
String dayName = dias[day-1];

int numberDay = dateNow.get(java.util.Calendar.DAY_OF_MONTH);

int month = dateNow.get(java.util.Calendar.MONTH);
String meses [] = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
String monthName = meses[month];

int year = dateNow.get(java.util.Calendar.YEAR);

%>
<div class="contenido"> 
<h1><%= new String ("Bienvenido a la página principal") %></h1>
<h3>Hoy es <%=dayName%>, <%=numberDay%> de <%=monthName%> de <%=year%>.</h3> 

<!-- se añade el espacio en el que se insertará el código javascript -->
<p id="reloj">[loading...]<p>

<button id="botonAdd"><a href="formulario.jsp">Añadir empleado</a></button>

<!-- se incluye el footer a la página principal a través de otra página jsp -->
<%@ include file = "piePagina.jsp" %>
</div>

<!-- insercción del código javascript del reloj de la página principal -->
<script src="js/reloj.js"></script>
</body>
</html>