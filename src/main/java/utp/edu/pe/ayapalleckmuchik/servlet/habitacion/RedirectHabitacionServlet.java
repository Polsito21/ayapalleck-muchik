package utp.edu.pe.ayapalleckmuchik.servlet.habitacion;

import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.Tipo_habitacionDAO;

import java.io.IOException;

@WebServlet(name = "redirectHabitacion", urlPatterns = {"/redirectHabitacion"})
public class RedirectHabitacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("habitacion_id"));
        HabitacionDAO habitacionDAO;
        Tipo_habitacionDAO tipo_habitacionDAO;

        try {
            habitacionDAO = new HabitacionDAO();
            tipo_habitacionDAO = new Tipo_habitacionDAO();
            req.setAttribute("tipos_habitacion", tipo_habitacionDAO.getTipoHabitaciones());
            req.setAttribute("habitacion", habitacionDAO.getHabitacionById(id));
            habitacionDAO.close();
            req.getRequestDispatcher("update-habitacion.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al redirigir a la habitación";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
