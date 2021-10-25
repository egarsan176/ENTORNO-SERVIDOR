<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <!-- se inserta la llamada a la página de error que gestiona las excepciones -->
    <%@ page errorPage = "edadException.jsp" %>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Lista Empleados</title>
<link rel="stylesheet" href="css/HojaDeEstilo1.css"></link> <!-- se añade la hoja de estilos -->
</head>
<body>
<div class="contenido">
	
	<!-- se inicializa el bean Empleado y persiste durante la sesión-->
	<jsp:useBean id="nuevoEmple" class="bean.Empleado" />
	
	<%-- <jsp:setProperty property="*" name="nuevoEmple"/> 
	con el * automáticamente guarda todos los datos del formulario siempre que su nombre coincidan con los del bean--%>
	
		<!-- se modifican sus atributos -->
	<jsp:setProperty property="nombre" name="nuevoEmple" param="nombre"/>
	<jsp:setProperty property="apellidos" name="nuevoEmple" param="apellidos"/>
	<jsp:setProperty property="dni" name="nuevoEmple" param="dni"/>
	<jsp:setProperty property="telefono" name="nuevoEmple" param="telefono"/>
	<jsp:setProperty property="edad" name="nuevoEmple" param="edad"/>
	<jsp:setProperty property="experiencia" name="nuevoEmple" param="experiencia"/> 
	
	
	<h3>Hola, <jsp:getProperty property="nombre" name="nuevoEmple"/>
	<jsp:getProperty property="apellidos" name="nuevoEmple"/>, bienvenido a nuestra plantilla.</h3>

	<% 
		//este scriptlet sirve para asignar el sueldo al empleado en función de su experiencia
		if (nuevoEmple.getExperiencia() <= 2){%> 
		<jsp:setProperty property="sueldo" name="nuevoEmple" value="1050"/>
		<%} 
		else if(nuevoEmple.getExperiencia() > 2 && nuevoEmple.getExperiencia() <= 5){%> 
			<jsp:setProperty property="sueldo" name="nuevoEmple" value="1600"/>
		<%}else {%> 
			<jsp:setProperty property="sueldo" name="nuevoEmple" value="2000"/>
		<%}

	   // Si es menor de 18 años, lanza una excepción para invocar a la página de error
	   if (nuevoEmple.getEdad()<18) {
	      throw new Exception("¡¡ERRORR!!");
	   }%> 
	
	<p>Ficha de trabajo personal:</p>
	
	<table class="fichaEmple">
	  <tr>
	    <td id="title">Nombre:</td>
	    <td><jsp:getProperty property="nombre" name="nuevoEmple"/></td>
	  </tr>
	  <tr>
	    <td id="title">Apellidos:</td>
	    <td><jsp:getProperty property="apellidos" name="nuevoEmple"/></td>
	  </tr>
	    <tr>
	    <td id="title">Edad:</td>
	    <td><jsp:getProperty property="edad" name="nuevoEmple"/></td>
	  </tr>
	    <tr>
	    <td id="title">DNI:</td>
	    <td><jsp:getProperty property="dni" name="nuevoEmple"/></td>
	  </tr>
	    </tr>
	    <tr>
	    <td id="title">Teléfono:</td>
	    <td><jsp:getProperty property="telefono" name="nuevoEmple"/></td>
	  </tr>
	    <tr>
	    <td id="title">Experiencia Laboral:</td>
	    <td><jsp:getProperty property="experiencia" name="nuevoEmple"/></td>
	  </tr>
	      <tr>
	    <td id="title">Sueldo base:</td>
	    <td><jsp:getProperty property="sueldo" name="nuevoEmple"/> €</td>
	  </tr>
	      <tr>
	    <td id="title">Fecha incorporación a la empresa:</td>
	    <td><jsp:setProperty property="fechaIncorporacion" name="nuevoEmple" value="<%= new java.util.Date() %>"/>
	    <jsp:getProperty property="fechaIncorporacion" name="nuevoEmple"/></td>
	  </tr>
	</table>
	
	
 
 	<button id="botonAdd"><a href="index.jsp">Volver al Inicio</a></button>

	</div>

</body>
</html>