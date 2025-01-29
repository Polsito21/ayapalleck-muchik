package utp.edu.pe.ayapalleckmuchik.servlet.solicitud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.SolicitudDAO;
import utp.edu.pe.ayapalleckmuchik.model.enums.Estado;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "pendingSolicitud", urlPatterns = {"/pendingSolicitud"})
public class PendingSolicitudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            SolicitudDAO solicitudDAO = new SolicitudDAO();
            req.setAttribute("solicitudes", solicitudDAO.getSolicitudesByEstado(Estado.PENDIENTE));
            solicitudDAO.close();
            req.getRequestDispatcher("pending-solicitudes.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
