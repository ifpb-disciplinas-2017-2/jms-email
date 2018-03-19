package ifpb.ads.jms.email;

import java.io.IOException;
import java.io.PrintWriter;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EnviarEmailServlet", urlPatterns = {"/email"})
public class EnviarEmailServlet extends HttpServlet {

    @Inject
    private EnviarEmail produtor;

    @Inject
    private LerEmail consumidor;
    

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = consumidor.ler();
        System.out.println("email = " + email);
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EnviarEmailServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>"+email+"</h1>");
            out.println("</body>");
            out.println("</html>");
        }
        
       
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String email = request.getParameter("email");
        this.produtor.enviar(email);

    }

}
