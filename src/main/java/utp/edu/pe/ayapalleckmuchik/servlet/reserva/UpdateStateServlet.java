package utp.edu.pe.ayapalleckmuchik.servlet.reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.dao.ReservaDAO;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;
import utp.edu.pe.ayapalleckmuchik.model.Reserva;

import java.io.IOException;

@WebServlet(name = "updateState", urlPatterns = {"/updateState"})
public class UpdateStateServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int habitacion_id = Integer.parseInt(req.getParameter("habitacion_id"));
        String state = req.getParameter("state");
        HabitacionDAO habitacionDAO;
        ReservaDAO reservaDAO;
        Habitacion habitacion;
        Reserva reserva;

        try {
            habitacionDAO = new HabitacionDAO();
            reservaDAO = new ReservaDAO();
            habitacion = habitacionDAO.getHabitacionById(habitacion_id);
            reserva = reservaDAO.getLastReservaByHabitacion(habitacion_id);

            switch (state) {
                case "Ocupado":
                    habitacion.setEstado("Libre");
                    habitacion.setEstado_limpieza("Limpio");
                    reserva.setEstado_reserva("Finalizado");
                    reservaDAO.updateReserva(reserva);
                    habitacionDAO.updateHabitacion(habitacion);
                    break;
                case "Libre":
                    habitacionDAO.close();
                    reservaDAO.close();
                    resp.sendRedirect(req.getContextPath()+"/addReservaRedirect");
                    break;
                case "Sucia":
                    habitacion.setEstado_limpieza("Limpio");
                    habitacionDAO.updateHabitacion(habitacion);
                    break;
            }
            habitacionDAO.close();
            reservaDAO.close();
            resp.sendRedirect(req.getContextPath()+"/recepcion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
