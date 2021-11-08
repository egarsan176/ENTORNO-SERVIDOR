<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <style>

    #header {
      text-align: center;
      border-style: dashed;
      background-color: #e3ae3f;
      margin: 20px;
    }

    #content{
      margin: 21px;
      background-color: #fbe9a0;
      padding: 5%;
    }

    button {
    margin-top: 2%;
    }

  </style>
  <title>Sesion Caducada</title>
</head>
<body>

  <div id="header">
    <h1>¡¡Aviso!!</h1>
  </div>
  <div id= "content">
    <h2>Uppss...</h2>
    <h3>Lo sentimos, ha ocurrido uno de los siguientes errores.</h3>
    <ul>
    <li>Su sesión ha caducado.</li>
    <li>No está registrado en el sistema.</li>
    </ul>
    <button><a href="index_login.jsp">VOLVER A INICIO</a></button>
  </div>
</body>
</html>