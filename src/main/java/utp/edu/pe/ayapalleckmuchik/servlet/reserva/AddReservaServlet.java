package utp.edu.pe.ayapalleckmuchik.servlet.reserva;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import utp.edu.pe.ayapalleckmuchik.dao.ClienteDAO;
import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.dao.ReservaDAO;
import utp.edu.pe.ayapalleckmuchik.model.Administrador;
import utp.edu.pe.ayapalleckmuchik.model.Cliente;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;
import utp.edu.pe.ayapalleckmuchik.model.Reserva;
import utp.edu.pe.ayapalleckmuchik.model.enums.Metodo_pago;
import utp.edu.pe.ayapalleckmuchik.service.ApachePOI;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "addReserva", urlPatterns = {"/addReserva"})
public class AddReservaServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int cliente_id = Integer.parseInt(req.getParameter("cliente"));
        int habitacion_id = Integer.parseInt(req.getParameter("habitacion"));
        Metodo_pago metodo_pago = Metodo_pago.valueOf(req.getParameter("metodo_pago"));
        ReservaDAO reservaDAO;
        HabitacionDAO habitacionDAO;
        Habitacion habitacion;
        ClienteDAO clienteDAO;
        Cliente cliente;
        ApachePOI apachePOI;
        HttpSession ses;
        Administrador admin;

        try {
            reservaDAO = new ReservaDAO();
            habitacionDAO = new HabitacionDAO();
            clienteDAO = new ClienteDAO();
            habitacion = habitacionDAO.getHabitacionById(habitacion_id);
            ses = req.getSession();
            admin = (Administrador) ses.getAttribute("admin");

            LocalDateTime reserveDate;
            if (LocalDateTime.now().getHour() < 4) {
                reserveDate = LocalDate.now().atTime(12, 0);
            } else {
                reserveDate = LocalDate.now().atTime(12, 0).plusDays(1);
            }

            Reserva reserva = new Reserva(cliente_id, habitacion_id, habitacion.getTipo_habitacion().getPrecio_noche(),
                    metodo_pago.toString(), LocalDateTime.now(),
                    reserveDate, admin.getId_admin(), "Ocupado");
            reservaDAO.createReserva(reserva);

            cliente = clienteDAO.getClienteById(cliente_id);
            apachePOI = new ApachePOI();
            apachePOI.sendEmail(cliente.getEmail(), "Reserva Exitosa", "Le agradecemos su preferencia!\n Su habitación es la " + habitacion.getNumero_habitacion() + " y el precio es de S/" + habitacion.getTipo_habitacion().getPrecio_noche());

            habitacion.setEstado("Ocupado");
            habitacion.setEstado_limpieza("Sucio");
            habitacionDAO.updateHabitacion(habitacion);

            habitacionDAO.close();
            reservaDAO.close();
            clienteDAO.close();

            resp.sendRedirect(req.getContextPath() + "/reservas");
        } catch (Exception e) {
            req.setAttribute("msg", e.getMessage());
            req.getRequestDispatcher("/error.jsp").forward(req, resp);
        }
    }

}
