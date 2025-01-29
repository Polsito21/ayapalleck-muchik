import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.pe.ayapalleckmuchik.dao.ReservaDAO;
import utp.edu.pe.ayapalleckmuchik.model.Reserva;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ReservaDAOTest {

    private ReservaDAO reservaDAO;

    @BeforeEach
    void setUp() throws SQLException, NamingException {
        // Inicializar el objeto ReservaDAO antes de cada prueba
        reservaDAO = new ReservaDAO();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        reservaDAO.close();
    }

    @Test
    void testCreateReserva() {
        // Arrange: Crear un nuevo objeto Reserva con datos válidos
        Reserva reserva = new Reserva();
        reserva.setId_cliente(1); // Asegúrate de que existe un cliente con ID 1
        reserva.setId_habitacion(1); // Asegúrate de que existe una habitación con ID 1
        reserva.setMonto_total(100.0);
        reserva.setMetodo_pago("Tarjeta");
        reserva.setFecha_ingreso(LocalDateTime.now());
        reserva.setFecha_salida(LocalDateTime.now().plusDays(1));
        reserva.setId_admin(1); // Asegúrate de que existe un administrador con ID 1
        reserva.setEstado_reserva("Confirmada");

        // Act & Assert: Verificar que createReserva no lanza ninguna excepción
        assertDoesNotThrow(() -> reservaDAO.createReserva(reserva),
                "Debería crear la reserva sin lanzar excepciones");
    }

    @Test
    void testCreateReservaWithInvalidData() {
        // Arrange: Crear una reserva con datos inválidos (cliente ID nulo o no existente)
        Reserva reserva = new Reserva();
        reserva.setId_cliente(-1); // Valor inválido
        reserva.setId_habitacion(1);
        reserva.setMonto_total(100.0);
        reserva.setMetodo_pago("Tarjeta");
        reserva.setFecha_ingreso(LocalDateTime.now());
        reserva.setFecha_salida(LocalDateTime.now().plusDays(1));
        reserva.setId_admin(1);
        reserva.setEstado_reserva("Confirmada");

        // Act & Assert: Verificar que se lanza una excepción al intentar crear una reserva inválida
        assertThrows(SQLException.class, () -> reservaDAO.createReserva(reserva),
                "Debe lanzar SQLException para datos inválidos");
    }

    @Test
    void testUpdateReserva() throws SQLException {
        // Arrange: Crear una reserva y luego actualizarla
        Reserva reserva = new Reserva();
        reserva.setId_cliente(1);
        reserva.setId_habitacion(1);
        reserva.setMonto_total(150.0);
        reserva.setMetodo_pago("Tarjeta");
        reserva.setFecha_ingreso(LocalDateTime.now());
        reserva.setFecha_salida(LocalDateTime.now().plusDays(1));
        reserva.setId_admin(1);
        reserva.setEstado_reserva("Confirmada");

        reservaDAO.createReserva(reserva);

        // Modificar los detalles de la reserva
        reserva.setMonto_total(200.0);

        // Act & Assert: Verificar que no lanza excepción al actualizar
        assertDoesNotThrow(() -> reservaDAO.updateReserva(reserva),
                "Debería actualizar la reserva sin lanzar excepciones");
    }

    @Test
    void testDeleteReserva() throws SQLException {
        // Arrange: Crear una reserva y luego eliminarla
        Reserva reserva = new Reserva();
        reserva.setId_cliente(1);
        reserva.setId_habitacion(1);
        reserva.setMonto_total(100.0);
        reserva.setMetodo_pago("Tarjeta");
        reserva.setFecha_ingreso(LocalDateTime.now());
        reserva.setFecha_salida(LocalDateTime.now().plusDays(1));
        reserva.setId_admin(1);
        reserva.setEstado_reserva("Confirmada");

        reservaDAO.createReserva(reserva);

        // Act & Assert: Verificar que no lanza excepción al eliminar
        assertDoesNotThrow(() -> reservaDAO.deleteReserva(reserva.getId_reserva()),
                "Debería eliminar la reserva sin lanzar excepciones");
    }

    @Test
    void testGetReservaById() throws SQLException {
        // Arrange: Crear una reserva y luego recuperarla por ID
        Reserva reserva = new Reserva();
        reserva.setId_cliente(1);
        reserva.setId_habitacion(1);
        reserva.setMonto_total(100.0);
        reserva.setMetodo_pago("Tarjeta");
        reserva.setFecha_ingreso(LocalDateTime.now());
        reserva.setFecha_salida(LocalDateTime.now().plusDays(1));
        reserva.setId_admin(1);
        reserva.setEstado_reserva("Confirmada");

        reservaDAO.createReserva(reserva);

        // Act: Recuperar la reserva por su ID
        Reserva retrievedReserva = reservaDAO.getReservaById(reserva.getId_reserva());

        // Assert: Verificar que la reserva recuperada es igual a la creada
        assertNotNull(retrievedReserva, "La reserva debería existir en la base de datos");
        assertEquals(reserva.getMonto_total(), retrievedReserva.getMonto_total(),
                "El monto de la reserva debería coincidir");
    }

    @Test
    void testGetReservas() throws SQLException {
        // Act: Obtener todas las reservas
        List<Reserva> reservas = reservaDAO.getReservas();

        // Assert: Verificar que la lista de reservas no es nula
        assertNotNull(reservas, "La lista de reservas no debería ser nula");
        assertTrue(reservas.size() > 0, "La lista de reservas debería contener al menos un elemento");
    }

    @Test
    void testGetLastReservaByHabitacion() throws SQLException {
        // Arrange: Crear una reserva para una habitación específica
        Reserva reserva = new Reserva();
        reserva.setId_cliente(1);
        reserva.setId_habitacion(2); // Usar una habitación específica
        reserva.setMonto_total(100.0);
        reserva.setMetodo_pago("Tarjeta");
        reserva.setFecha_ingreso(LocalDateTime.now());
        reserva.setFecha_salida(LocalDateTime.now().plusDays(1));
        reserva.setId_admin(1);
        reserva.setEstado_reserva("Confirmada");

        reservaDAO.createReserva(reserva);

        // Act: Obtener la última reserva por habitación
        Reserva lastReserva = reservaDAO.getLastReservaByHabitacion(reserva.getId_habitacion());

        // Assert: Verificar que la última reserva es la que se acaba de crear
        assertNotNull(lastReserva, "Debería existir una reserva para la habitación");
        assertEquals(reserva.getId_habitacion(), lastReserva.getId_habitacion(),
                "La habitación de la reserva debería coincidir");
    }

    @Test
    void testGetReservasByEstado() throws SQLException {
        // Arrange: Crear una reserva con un estado específico
        Reserva reserva = new Reserva();
        reserva.setId_cliente(1);
        reserva.setId_habitacion(1);
        reserva.setMonto_total(100.0);
        reserva.setMetodo_pago("Tarjeta");
        reserva.setFecha_ingreso(LocalDateTime.now());
        reserva.setFecha_salida(LocalDateTime.now().plusDays(1));
        reserva.setId_admin(1);
        reserva.setEstado_reserva("Confirmada");

        reservaDAO.createReserva(reserva);

        // Act: Obtener reservas por estado
        List<Reserva> reservas = reservaDAO.getReservasByEstado("Confirmada");

        // Assert: Verificar que la lista de reservas no es nula y contiene la reserva creada
        assertNotNull(reservas, "La lista de reservas no debería ser nula");
        assertTrue(reservas.stream().anyMatch(r -> r.getEstado_reserva().equals("Confirmada")),
                "La lista de reservas debería contener al menos una reserva con el estado 'Confirmada'");
    }
}
