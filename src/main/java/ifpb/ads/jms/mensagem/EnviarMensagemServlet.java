package ifpb.ads.jms.mensagem;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "EnviarMensagemServlet", urlPatterns = {"/mensagem"})
public class EnviarMensagemServlet extends HttpServlet {

    @Inject
    private EnviarMensagem produtor;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String mensagem = request.getParameter("mensagem");
        String categoria = request.getParameter("categoria");
        this.produtor.enviar(categoria, mensagem);
        response.sendRedirect("mensagem.html");
    }

}
