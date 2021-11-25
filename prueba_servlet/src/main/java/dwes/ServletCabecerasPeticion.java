package dwes;

import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
 
@WebServlet("/servletpeticion")
public class ServletCabecerasPeticion extends HttpServlet {
 
    // Metodo para GET
    public void doGet(HttpServletRequest request, 
                     HttpServletResponse response) 
                   throws ServletException, IOException {
        PrintWriter out = response.getWriter();
 
        // Mostramos las cabeceras enviadas 
        // en la peticion
 
        out.println ("<HTML>");
        out.println ("<BODY>");   
        out.println ("<H1>Cabeceras</H1>");
        out.println ("<BR>");
 
        Enumeration cabeceras = request.getHeaderNames();
 
        while (cabeceras.hasMoreElements())
        {       
            String nombre = (String)(cabeceras.nextElement());
            out.println ("Nombre: " + nombre + 
               ", Valor: " + request.getHeader(nombre));
            out.println ("<BR><BR>");
        }
 
        out.println ("</BODY>");          
        out.println ("</HTML>");
    }   
 
    // Metodo para POST
    public void doPost(HttpServletRequest request, 
                      HttpServletResponse response) 
                   throws ServletException, IOException {
        doGet(request, response);
    }   
}