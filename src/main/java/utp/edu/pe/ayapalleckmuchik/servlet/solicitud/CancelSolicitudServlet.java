package utp.edu.pe.ayapalleckmuchik.servlet.solicitud;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.SolicitudDAO;
import utp.edu.pe.ayapalleckmuchik.model.Solicitud;
import utp.edu.pe.ayapalleckmuchik.model.enums.Estado;
import utp.edu.pe.ayapalleckmuchik.service.ApachePOI;

import java.io.IOException;

@WebServlet(name = "cancelSolicitud", urlPatterns = {"/cancelSolicitud"})
public class CancelSolicitudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        SolicitudDAO solicitudDAO;

        try {
            solicitudDAO = new SolicitudDAO();
            Solicitud solicitud = solicitudDAO.getSolicitudById(id);
            solicitud.setEstado(Estado.CANCELADO);
            solicitudDAO.updateSolicitud(solicitud);
            ApachePOI apachePOI = new ApachePOI();
            apachePOI.sendEmail(solicitud.getNombre_cliente(), "Su solicitud ha sido rechazada",
                    String.format(
                            """
                                    Hola, estimado %s,
                                    
                                    Por el momento no tenemos disponibilidad para su solicitud. Las disculpas del caso.
                                    
                                    Saludos cordiales,
                                    Ayapalleck Muchik.
                                    """, solicitud.getNombre_cliente()));
            solicitud.setEstado(Estado.CANCELADO);

            solicitudDAO.close();
            resp.sendRedirect(req.getContextPath() + "/solicitudes");
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al cancelar la solicitud";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
