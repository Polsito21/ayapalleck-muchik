package utp.edu.pe.ayapalleckmuchik.servlet.cliente;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.ClienteDAO;
import utp.edu.pe.ayapalleckmuchik.model.Cliente;
import utp.edu.pe.ayapalleckmuchik.model.enums.Tipo_documento;


import java.io.IOException;
import java.time.LocalDate;
import java.time.Period;

@WebServlet(name = "addCliente", urlPatterns = {"/addCliente"})
public class AddClienteServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombre = req.getParameter("nombre");
        String apellido = req.getParameter("apellido");
        Tipo_documento tipo_documento = Tipo_documento.valueOf(req.getParameter("tipo_documento"));
        String numero_documento = req.getParameter("numero_documento");
        String email = req.getParameter("email");
        String telefono = req.getParameter("telefono");
        LocalDate fecha_nacimiento = LocalDate.parse(req.getParameter("fecha_nacimiento"));
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(fecha_nacimiento, fechaActual);

        if (periodo.getYears() < 18) {
            String error = "El cliente debe ser mayor de edad";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else {
            ClienteDAO clienteDAO;

            try {
                clienteDAO = new ClienteDAO();
                clienteDAO.createCliente(new Cliente(nombre, apellido, tipo_documento.toString(), numero_documento, email, telefono, fecha_nacimiento));
                clienteDAO.close();
                resp.sendRedirect(req.getContextPath() + "/clientes");
            } catch (Exception e) {
                req.setAttribute("msg", e.getMessage());
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }


    }
}