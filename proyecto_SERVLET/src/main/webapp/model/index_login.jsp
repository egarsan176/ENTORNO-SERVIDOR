<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <title>Don Tornillos</title>
        <meta name="description" content="">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="../CSS/hojaDeEstilo.css"></link>
    </head>
    <body>
       <div class="intro">
           <h1>Bienvenid@ a Don Tornillos</h1>
           <h3>La ferretería de tu barrio, pero en la web</h3>
       </div>
       <div>
       
           <form class="login" method="POST" action="/proyecto_SERVLET/LoginServlet"> <!-- en action la ruta del servlet al que se dirije -->
               <label>Nombre de usuario:</label>
               <input name="usuario" placeholder="usuario" type="text">
               <br>
              
               <label>Contraseña:</label>
               <input name="password" placeholder="contraseña" type="password"> 
               <br>
               <button type="submit">ENTRAR</button>
           </form>
       </div>
    </body>
</html>