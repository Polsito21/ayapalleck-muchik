package utp.edu.pe.ayapalleckmuchik.servlet.tipo_habitacion;

import utp.edu.pe.ayapalleckmuchik.dao.Tipo_habitacionDAO;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "redirectTipoHabitacion", urlPatterns = {"/redirectTipoHabitacion"})
public class RedirectTipoHabitacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("tipo_habitacion_id"));
        Tipo_habitacionDAO tipo_habitacionDAO;

        try {
            tipo_habitacionDAO = new Tipo_habitacionDAO();
            req.setAttribute("tipo_habitacion", tipo_habitacionDAO.getTipoHabitacionById(id));
            tipo_habitacionDAO.close();
            req.getRequestDispatcher("update-tipo-habitacion.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al redirigir al tipo de habitación";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
