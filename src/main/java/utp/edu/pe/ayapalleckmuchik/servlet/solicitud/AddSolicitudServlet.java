package utp.edu.pe.ayapalleckmuchik.servlet.solicitud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.SolicitudDAO;
import utp.edu.pe.ayapalleckmuchik.model.Solicitud;
import utp.edu.pe.ayapalleckmuchik.model.enums.Estado;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;

@WebServlet(name = "addSolicitud", urlPatterns = {"/addSolicitud"})
public class AddSolicitudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String nombre = req.getParameter("nombre");
        String correo = req.getParameter("correo");
        int duracion = Integer.parseInt(req.getParameter("duracion"));
        LocalDateTime fechaReserva = LocalDateTime.now();
        LocalDateTime fechaIngreso = LocalDateTime.parse(req.getParameter("fechaIngreso"));

        LocalDateTime reserveDate;
        int daysDiff = fechaIngreso.getDayOfMonth() - fechaReserva.getDayOfMonth();
        if (fechaIngreso.getHour() < 4) {
            reserveDate = LocalDate.now().atTime(12, 0).plusDays(daysDiff);
        } else {
            reserveDate = LocalDate.now().atTime(12, 0).plusDays(daysDiff + 1);
        }


        Solicitud solicitud = new Solicitud(id, reserveDate, fechaIngreso, nombre, correo, duracion, Estado.PENDIENTE);

        try {
            SolicitudDAO solicitudDAO = new SolicitudDAO();
            solicitudDAO.createSolicitud(solicitud);
            solicitudDAO.close();
            resp.sendRedirect("index.jsp");
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al agregar la solicitud";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
