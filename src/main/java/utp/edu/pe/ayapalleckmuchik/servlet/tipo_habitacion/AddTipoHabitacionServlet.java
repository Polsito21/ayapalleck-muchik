package utp.edu.pe.ayapalleckmuchik.servlet.tipo_habitacion;

import utp.edu.pe.ayapalleckmuchik.dao.Tipo_habitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "addTipoHabitacion", urlPatterns = {"/addTipoHabitacion"})
public class AddTipoHabitacionServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String nombreHabitacion = req.getParameter("nombreHabitacion");
        String descripcion = req.getParameter("descripcion");
        float precio_noche = Float.parseFloat(req.getParameter("precio_noche"));

        if (precio_noche <= 0) {
            String error = "El precio de noche no puede ser menor o igual a 0";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        } else {
            Tipo_habitacionDAO tipo_habitacionDAO;

            try {
                tipo_habitacionDAO = new Tipo_habitacionDAO();
                tipo_habitacionDAO.createTipoHabitacion(new Tipo_habitacion(nombreHabitacion, descripcion, precio_noche));
                tipo_habitacionDAO.close();
                resp.sendRedirect(req.getContextPath() + "/tiposhabitacion");
            } catch (Exception e) {
                e.printStackTrace();
                String error = "Error al agregar el tipo de habitación";
                req.setAttribute("msg", error);
                req.getRequestDispatcher("error.jsp").forward(req, resp);
            }
        }


    }
}
