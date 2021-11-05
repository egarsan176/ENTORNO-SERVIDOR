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
       <div id="contenedor">

            <div id="header">
                <h1>Don Tornillos</h1>
                <h3>La ferretería de tu barrio, pero en la web</h3>
            </div>
                
            <div id="form">

                <form id="addUser" method="POST" action="/proyecto_SERVLET/LoginServlet">
                    <label>Nombre de usuario:</label>
                    <input name="usuario" placeholder="usuario" type="text">
                    <br>
            
                    <label>Contraseña:</label>
                    <input name="password" placeholder="contraseña" type="password"> 
                    <br>
                    

                    <div id="botonesIndex">
                        <button id ="send">ENTRAR</button>
                    </div>

                </form>

            </div>

            <div id="imag">
                <img src="../img/ferrLogo.jpg">
            </div>


        </div>
    </body>
</html>