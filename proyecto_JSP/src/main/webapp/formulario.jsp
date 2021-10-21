<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Datos</title>
</head>
<link rel="stylesheet" href="css/HojaDeEstilo1.css"></link>
<body>

<div class="contenido">

<form action="mostrarDatos.jsp" method="get">
<label>Nombre: </label>
<input type="text" id="nombre">

<br>

<label>Apellidos: </label>
<input type="text" id="apellidos">

<br>

<label>Introduce tu DNI: </label>
<input type="text" id="dni">

<br>

<label>Edad: </label>
<input type="text" id="edad">

<br>

<label>Sueldo: </label>
<input type="text" id="sueldo">

<br>

<label name ="estado">Estado Civil: </label>
<select id="estado">
	<option value="">--</option>
	<option value="1">Soltero</option>
	<option value="2">Casado</option>
	<option value="3">Separado</option>
	<option value="4">Viudo</option>
</select>

<br>

<button type="submit" id="botonEnvio">ENVIAR</button>


</form>

</div>


</body>
</html>