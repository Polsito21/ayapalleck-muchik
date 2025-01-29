package utp.edu.pe.ayapalleckmuchik.servlet.tipo_habitacion;

import utp.edu.pe.ayapalleckmuchik.dao.Tipo_habitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "tiposhabitacion", urlPatterns = {"/tiposhabitacion"})
public class TipoHabitacionesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Tipo_habitacion> tipos_habitacion;
        Tipo_habitacionDAO tipo_habitacionDAO;

        try {
            tipo_habitacionDAO = new Tipo_habitacionDAO();
            tipos_habitacion = tipo_habitacionDAO.getTipoHabitaciones();
            tipo_habitacionDAO.close();
            req.setAttribute("tipos_habitacion", tipos_habitacion);
            req.getRequestDispatcher("tipo-habitacion.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al mostrar los tipos de habitaciones";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
