package utp.edu.pe.ayapalleckmuchik.servlet.administrador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import utp.edu.pe.ayapalleckmuchik.dao.AdministradorDAO;
import utp.edu.pe.ayapalleckmuchik.model.Administrador;
import utp.edu.pe.ayapalleckmuchik.service.Auth;

import java.io.IOException;

@WebServlet(name = "updateAdmin", urlPatterns = {"/updateAdmin"})
public class UpdateAdminServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        AdministradorDAO administradorDAO;

        try {
            administradorDAO = new AdministradorDAO();
            administradorDAO.updateAdministrador(new Administrador(username, Auth.md5(password)));
            administradorDAO.close();
            resp.sendRedirect("perfil.jsp");
        } catch (Exception e) {
            req.setAttribute("message", e.getMessage());
            req.getRequestDispatcher("error.jsp").forward(req, resp);
        }
    }
}
