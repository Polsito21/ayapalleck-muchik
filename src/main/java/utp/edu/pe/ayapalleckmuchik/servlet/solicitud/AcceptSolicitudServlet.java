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

@WebServlet(name = "acceptSolicitud", urlPatterns = {"/acceptSolicitud"})
public class AcceptSolicitudServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        try {
            SolicitudDAO solicitudDAO = new SolicitudDAO();
            Solicitud solicitud = solicitudDAO.getSolicitudById(id);
            ApachePOI apachePOI = new ApachePOI();
            apachePOI.sendEmail(solicitud.getNombre_cliente(), "Su solicitud ha sido aceptada!",
                    String.format(
                            """
                                    Hola, estimado %s,
                                    
                                    Necesitamos la siguiente información para proseguir con la reserva:
                                    
                                    1. Nombres.
                                    2. Apellidos.
                                    3. Tipo de documento.
                                    4. Número de documento.
                                    5. Email.
                                    6. Teléfono.
                                    7. Fecha de nacimiento.
                                    
                                    Saludos cordiales,
                                    Ayapalleck Muchik.
                                    """, solicitud.getNombre_cliente()));
            solicitud.setEstado(Estado.RESERVADO);
            solicitudDAO.updateSolicitud(solicitud);
            solicitudDAO.close();
            resp.sendRedirect(req.getContextPath() + "/solicitudes");
        } catch (Exception e) {
            e.printStackTrace();
            String error = "Error al aceptar la solicitud";
            req.setAttribute("msg", error);
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}