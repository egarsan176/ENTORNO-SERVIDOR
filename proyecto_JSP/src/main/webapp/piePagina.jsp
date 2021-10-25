<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<br/><br/><br/><br/>
      <!-- esta declaración incluye el contador de visitas de la página y que se incluye a la página principal -->
     <%! private int numVisitas = 0; %>
      <p>Visitas de la página: <%= ++numVisitas %> .</p>
      
   </body>
</html>