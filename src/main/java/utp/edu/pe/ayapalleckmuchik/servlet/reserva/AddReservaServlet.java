package utp.edu.pe.ayapalleckmuchik.servlet.reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.dao.ReservaDAO;
import utp.edu.pe.ayapalleckmuchik.model.Administrador;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;
import utp.edu.pe.ayapalleckmuchik.model.Reserva;
import utp.edu.pe.ayapalleckmuchik.model.enums.Metodo_pago;

import java.io.IOException;
import java.time.LocalDateTime;

@WebServlet(name = "addReserva", urlPatterns = {"/addReserva"})
public class AddReservaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cliente_id = Integer.parseInt(req.getParameter("cliente"));
        int habitacion_id = Integer.parseInt(req.getParameter("habitacion"));
        Metodo_pago metodo_pago = Metodo_pago.valueOf(req.getParameter("metodo_pago"));
        ReservaDAO reservaDAO;
        HabitacionDAO habitacionDAO;
        Habitacion habitacion;

        try {
            reservaDAO = new ReservaDAO();
            habitacionDAO = new HabitacionDAO();
            habitacion = habitacionDAO.getHabitacionById(habitacion_id);

            Reserva reserva = new Reserva(cliente_id, habitacion_id, habitacion.getTipo_habitacion().getPrecio_noche(),
                    metodo_pago.toString(), LocalDateTime.now(),
                    LocalDateTime.now().plusHours(12), 1, "Ocupado");
            reservaDAO.createReserva(reserva);

            habitacion.setEstado("Ocupado");
            habitacion.setEstado_limpieza("Sucio");
            habitacionDAO.updateHabitacion(habitacion);

            habitacionDAO.close();
            reservaDAO.close();

            resp.sendRedirect(req.getContextPath() + "/reservas");

        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

}
