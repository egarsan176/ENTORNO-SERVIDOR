<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<br/><br/><br/><br/>
      
     <%! private int numVisitas = 0; %>
      <p>Visitas de la página: <%= ++numVisitas %> .</p>
      
      <meta http-equiv="Refresh" CONTENT="10;url=index.jsp">
      
   </body>
</html>