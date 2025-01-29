package utp.edu.pe.ayapalleckmuchik.servlet.reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.ClienteDAO;
import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Cliente;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "addReservaRedirect", urlPatterns = {"/addReservaRedirect"})
public class AddReservaRedirectServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Cliente> clientes;
        List<Habitacion> habitaciones;
        ClienteDAO clienteDAO;
        HabitacionDAO habitacionDAO;

        try {
            clienteDAO = new ClienteDAO();
            clientes = clienteDAO.getClientes();
            clienteDAO.close();
            habitacionDAO = new HabitacionDAO();
            habitaciones = habitacionDAO.getHabitacionesByEstado("Libre");
            habitacionDAO.close();
            req.setAttribute("clientes", clientes);
            req.setAttribute("habitaciones", habitaciones);
            req.getRequestDispatcher("add-reserva.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
