package utp.edu.pe.ayapalleckmuchik.servlet.reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "recepcion", urlPatterns = {"/recepcion"})
public class RecepcionSevlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Habitacion> habitaciones;
        HabitacionDAO habitacionDAO;

        try {
            habitacionDAO = new HabitacionDAO();
            habitaciones = habitacionDAO.getHabitaciones();
            habitacionDAO.close();
            req.setAttribute("habitaciones", habitaciones);
            req.getRequestDispatcher("recepcion.jsp").forward(req, resp);
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}