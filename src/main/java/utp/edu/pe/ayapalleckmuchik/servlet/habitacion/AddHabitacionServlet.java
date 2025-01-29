package utp.edu.pe.ayapalleckmuchik.servlet.habitacion;

import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addHabitacion", urlPatterns = {"/addHabitacion"})
public class AddHabitacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String numeroHabitacion = req.getParameter("numero_habitacion");
        int tipo_habitacion = Integer.parseInt(req.getParameter("tipo_habitacion"));
        HabitacionDAO habitacionDAO;

        try {
            habitacionDAO = new HabitacionDAO();
            habitacionDAO.createHabitacion(new Habitacion(numeroHabitacion, tipo_habitacion, "Libre", "Limpia"));
            habitacionDAO.close();
            resp.sendRedirect(req.getContextPath() + "/habitaciones");

        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al agregar la habitación";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
