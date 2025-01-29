package utp.edu.pe.ayapalleckmuchik.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.Tipo_habitacionDAO;

import javax.naming.NamingException;
import java.io.IOException;
import java.sql.SQLException;

public class IndexServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            Tipo_habitacionDAO tipoHabitacionDAO = new Tipo_habitacionDAO();
            req.setAttribute("tipoHabitaciones", tipoHabitacionDAO.getTipoHabitaciones());
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (NamingException e) {
            throw new RuntimeException(e);
        }
    }
}
