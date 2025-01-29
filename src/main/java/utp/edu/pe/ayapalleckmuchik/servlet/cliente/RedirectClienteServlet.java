package utp.edu.pe.ayapalleckmuchik.servlet.cliente;

import utp.edu.pe.ayapalleckmuchik.dao.ClienteDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "redirectCliente", urlPatterns = {"/redirectCliente"})
public class RedirectClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("cliente_id"));
        ClienteDAO clienteDAO;

        try {
            clienteDAO = new ClienteDAO();
            req.setAttribute("cliente", clienteDAO.getClienteById(id));
            clienteDAO.close();
            req.getRequestDispatcher("update-cliente.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al redirigir al cliente";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
