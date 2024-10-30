package utp.edu.pe.ayapalleckmuchik.servlet.habitacion;

import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "deleteHabitacion", urlPatterns = {"/deleteHabitacion"})
public class DeleteHabitacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("habitacion_id"));
        HabitacionDAO habitacionDAO;

        try {
            habitacionDAO = new HabitacionDAO();
            habitacionDAO.deleteHabitacion(id);
            habitacionDAO.close();
            resp.sendRedirect(req.getContextPath() + "/habitaciones");
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al eliminar la habitación";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
