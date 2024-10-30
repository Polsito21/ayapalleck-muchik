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

@WebServlet(name = "updateReserva", urlPatterns = {"/updateReserva"})
public class UpdateReservaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int reserva_id = Integer.parseInt(req.getParameter("reserva_id"));
        int cliente_id = Integer.parseInt(req.getParameter("cliente"));
        int habitacion_id = Integer.parseInt(req.getParameter("habitacion"));
        Metodo_pago metodo_pago = Metodo_pago.valueOf(req.getParameter("metodo_pago"));
        ReservaDAO reservaDAO;
        HttpSession ses = req.getSession();
        Reserva reserva;

        try {
            reservaDAO = new ReservaDAO();

            HabitacionDAO habitacionDAO = new HabitacionDAO();
            Administrador administrador = (Administrador) ses.getAttribute("admin");
            reserva = reservaDAO.getReservaById(reserva_id);
            Habitacion habitacion_nueva = habitacionDAO.getHabitacionById(habitacion_id);
            if (reserva.getId_habitacion() != habitacion_id) {
                Habitacion habitacion_antigua = habitacionDAO.getHabitacionById(reserva.getId_habitacion());
                habitacion_antigua.setEstado("Disponible");
                habitacion_antigua.setEstado_limpieza("Limpia");
                habitacion_nueva.setEstado("Ocupado");
                habitacion_nueva.setEstado_limpieza("Sucio");
            }
            reservaDAO.updateReserva(new Reserva(reserva_id, cliente_id, habitacion_id, habitacion_nueva.getTipo_habitacion().getPrecio_noche(), metodo_pago.toString(), reserva.getFecha_ingreso(), reserva.getFecha_salida(), 1, reserva.getEstado_reserva()));
            habitacionDAO.close();
            reservaDAO.close();
            resp.sendRedirect(  req.getContextPath() + "/reservas");
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
