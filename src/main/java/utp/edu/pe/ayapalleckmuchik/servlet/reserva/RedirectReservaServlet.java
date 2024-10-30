package utp.edu.pe.ayapalleckmuchik.servlet.reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.ReservaDAO;

import java.io.IOException;

@WebServlet(name = "redirectReserva", urlPatterns = {"/redirectReserva"})
public class RedirectReservaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int reserva_id = Integer.parseInt(req.getParameter("reserva_id"));
        ReservaDAO reservaDAO;

        try {
            reservaDAO = new ReservaDAO();
            reservaDAO.getReservaById(reserva_id);
            reservaDAO.close();
            resp.sendRedirect("reservas");
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
