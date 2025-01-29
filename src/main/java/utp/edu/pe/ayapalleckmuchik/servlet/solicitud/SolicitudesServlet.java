package utp.edu.pe.ayapalleckmuchik.servlet.solicitud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.SolicitudDAO;

import java.io.IOException;

@WebServlet(name = "solicitudes", urlPatterns = {"/solicitudes"})
public class SolicitudesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SolicitudDAO solicitudDAO = new SolicitudDAO();
            req.setAttribute("solicitudes", solicitudDAO.getSolicitudes());
            solicitudDAO.close();
            req.getRequestDispatcher("solicitudes.jsp").forward(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al cargar las solicitudes";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
