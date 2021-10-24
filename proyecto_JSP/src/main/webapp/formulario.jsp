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

<h2>Formulario de Inscripción:</h2>

<form action="mostrarDatos.jsp" method="POST">
<label>Nombre: </label>
<input type="text" name="nombre">

<br>

<label>Apellidos: </label>
<input type="text" name="apellidos">

<br>

<label>DNI: </label>
<input type="text" name="dni">

<br>

<label>Teléfono: </label>
<input type="text" name="telefono">

<br>

<label>Edad: </label>
<input type="text" name="edad">

<br>

<label>Años de experiencia: </label>
<input type="number" name="experiencia">

<br>

<button type="submit" value ="submit" id="botonEnvio">ENVIAR</button>


</form>

</div>


</body>
</html>