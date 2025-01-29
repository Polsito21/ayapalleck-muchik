package utp.edu.pe.ayapalleckmuchik.servlet.cliente;

import utp.edu.pe.ayapalleckmuchik.dao.ClienteDAO;
import utp.edu.pe.ayapalleckmuchik.model.Cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "clientes", urlPatterns = {"/clientes"})
public class ClientesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes;
        ClienteDAO clienteDAO;

        try {
            clienteDAO = new ClienteDAO();
            clientes = clienteDAO.getClientes();
            clienteDAO.close();
            req.setAttribute("clientes", clientes);
            req.getRequestDispatcher("clientes.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
