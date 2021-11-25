package dwes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

 
@WebServlet(name="ejemplo1_2", urlPatterns="/ejemploservletHTML")
public class ClaseServletHTML extends HttpServlet {
 
    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) 
                   throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println ("<!DOCTYPE HTML PUBLIC \""+
                     "-//W3C//DTD HTML 4.0 " + 
                     "Transitional//EN\">");
        out.println ("<HTML>");
        out.println ("<BODY>");
        out.println ("<h1>Titulo</h1>");
        out.println ("<br>Servlet que genera HTML");
        out.println ("</BODY>");
        out.println ("</HTML>");
    }
}