package utp.edu.pe.ayapalleckmuchik.interfaces;
import utp.edu.pe.ayapalleckmuchik.model.Reserva;

import java.sql.SQLException;
import java.util.List;

public interface ReservaDAOinterface {void createReserva(Reserva reserva) throws SQLException;

    // Método para actualizar los datos de una reserva en la base de datos
    void updateReserva(Reserva reserva) throws SQLException;

    // Método para eliminar una reserva usando su ID
    void deleteReserva(int id_reserva) throws SQLException;

    // Método para obtener una reserva por su ID
    Reserva getReservaById(int id_reserva) throws SQLException;

    // Método para obtener todas las reservas
    List<Reserva> getReservas() throws SQLException;

    // Método para obtener la última reserva de una habitación específica
    Reserva getLastReservaByHabitacion(int habitacion_id) throws SQLException;

    // Método para cerrar la conexión
    void close() throws SQLException;
}
