package utp.edu.pe.ayapalleckmuchik.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpSession;
import utp.edu.pe.ayapalleckmuchik.model.Administrador;
import utp.edu.pe.ayapalleckmuchik.service.Auth;

import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        try {
            Administrador admin = Auth.isValidAdmin(username, password);
            if (admin != null) {
                HttpSession session = req.getSession();
                session.setMaxInactiveInterval(30 * 60);
                session.setAttribute("admin", admin);
                req.getRequestDispatcher("recepcion").forward(req, resp);
            } else {
                req.setAttribute("error", true);
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (SQLException | NamingException e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
