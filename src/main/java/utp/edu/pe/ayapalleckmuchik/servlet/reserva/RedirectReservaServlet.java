package utp.edu.pe.ayapalleckmuchik.servlet.reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.ClienteDAO;
import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.dao.ReservaDAO;
import utp.edu.pe.ayapalleckmuchik.model.Cliente;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;
import utp.edu.pe.ayapalleckmuchik.model.Reserva;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "redirectReserva", urlPatterns = {"/redirectReserva"})
public class RedirectReservaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int reserva_id = Integer.parseInt(req.getParameter("reserva_id"));
        ReservaDAO reservaDAO;
        ClienteDAO clienteDAO;
        HabitacionDAO habitacionDAO;
        List<Cliente> clientes;
        List<Habitacion> habitaciones;

        try {
            reservaDAO = new ReservaDAO();
            clienteDAO = new ClienteDAO();
            habitacionDAO = new HabitacionDAO();
            Reserva reserva = reservaDAO.getReservaById(reserva_id);
            clientes = clienteDAO.getClientes();
            habitaciones = habitacionDAO.getHabitacionesByEstado("Libre");
            clienteDAO.close();
            habitacionDAO.close();
            reservaDAO.close();
            req.setAttribute("reserva", reserva);
            req.setAttribute("clientes", clientes);
            req.setAttribute("habitaciones", habitaciones);
            req.getRequestDispatcher("update-reserva.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
