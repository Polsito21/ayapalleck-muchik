package utp.edu.pe.ayapalleckmuchik.servlet.habitacion;

import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "updateHabitacion", urlPatterns = {"/updateHabitacion"})
public class UpdateHabitacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("habitacion_id"));
        String numeroHabitacion = req.getParameter("numero_habitacion");
        int tipo_habitacion_id = Integer.parseInt(req.getParameter("tipo_habitacion"));
        HabitacionDAO habitacionDAO;

        try {
            habitacionDAO = new HabitacionDAO();
            Habitacion habitacion = habitacionDAO.getHabitacionById(id);
            habitacionDAO.updateHabitacion(new Habitacion(id, numeroHabitacion, tipo_habitacion_id, habitacion.getEstado(), habitacion.getEstado_limpieza()));
            habitacionDAO.close();
            resp.sendRedirect(req.getContextPath() + "/habitaciones");
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al actualizar la habitación";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
