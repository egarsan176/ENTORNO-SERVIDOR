package dwes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/servletsession")
public class ServletSession extends HttpServlet {
	// Metodo para GET
    
    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) 
                   throws ServletException, IOException {
        response.setContentType("text/html");
        response.setHeader("Cache-Control", "no-cache");
         
        HttpSession sesion = request.getSession();	//obtenemos la sesion del request
        PrintWriter out = response.getWriter();
 
        if (sesion.isNew()) {
            // Mostramos un mensaje de primera visita
 
            out.println ("<HTML>");           
            out.println ("<BODY>");           
            out.println ("Primera visita a la pagina");
            out.println ("<BR>");
            out.println ("</BODY>");          
            out.println ("</HTML>");          
             
            sesion.setAttribute("contador", new Integer(1));
             
        } else {
 
            // Mostramos el numero de visitas 
            // y actualizamos el contador
             
            int contador = ((Integer)
                (sesion.getAttribute("contador"))).intValue();
            contador++;
 
            out.println ("<HTML>");           
            out.println ("<BODY>");           
            out.println ("Visita numero " + 
                         contador + 
                         " a la pagina en esta sesion");
            out.println ("<BR>");
            out.println ("</BODY>");
            out.println ("</HTML>");
             
            sesion.setAttribute("contador", 
                                new Integer(contador));
    }       
   }    
}