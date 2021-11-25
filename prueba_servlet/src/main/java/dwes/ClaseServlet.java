package dwes;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.WebServlet;

//@WebServlet(name="ejemplo1_1", urlPatterns="/ejemploservlet")
@WebServlet(urlPatterns="/ejemploservlet")
public class ClaseServlet extends HttpServlet {
 
    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) 
                   throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        out.println ("Este es un servlet de prueba");
    }
}