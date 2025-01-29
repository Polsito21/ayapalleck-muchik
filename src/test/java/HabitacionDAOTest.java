import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.pe.ayapalleckmuchik.dao.HabitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Habitacion;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HabitacionDAOTest {

    private HabitacionDAO habitacionDAO;

    @BeforeEach
    void setUp() throws SQLException, NamingException {
        // Inicializar el objeto HabitacionDAO antes de cada prueba
        habitacionDAO = new HabitacionDAO();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        habitacionDAO.close();
    }

    @Test
    void testCreateHabitacion() {
        // Arrange: Crear un nuevo objeto Habitacion con datos válidos
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion("101");
        habitacion.setId_tipo_habitacion(1); // Asegúrate de que existe un tipo de habitación con ID 1
        habitacion.setEstado("Disponible");
        habitacion.setEstado_limpieza("Limpio");

        // Act & Assert: Verificar que createHabitacion no lanza ninguna excepción
        assertDoesNotThrow(() -> habitacionDAO.createHabitacion(habitacion),
                "Debería crear la habitación sin lanzar excepciones");
    }

    @Test
    void testCreateHabitacionWithInvalidData() {
        // Arrange: Crear una habitación con datos inválidos (número de habitación nulo)
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion(null); // Valor inválido
        habitacion.setId_tipo_habitacion(1);
        habitacion.setEstado("Disponible");
        habitacion.setEstado_limpieza("Limpio");

        // Act & Assert: Verificar que se lanza una excepción al intentar crear una habitación inválida
        assertThrows(SQLException.class, () -> habitacionDAO.createHabitacion(habitacion),
                "Debe lanzar SQLException para datos inválidos");
    }

    @Test
    void testUpdateHabitacion() throws SQLException {
        // Arrange: Crear una habitación y luego actualizarla
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion("102");
        habitacion.setId_tipo_habitacion(1);
        habitacion.setEstado("Disponible");
        habitacion.setEstado_limpieza("Limpio");

        habitacionDAO.createHabitacion(habitacion);

        // Modificar los detalles de la habitación
        habitacion.setEstado("Ocupado");

        // Act & Assert: Verificar que no lanza excepción al actualizar
        assertDoesNotThrow(() -> habitacionDAO.updateHabitacion(habitacion),
                "Debería actualizar la habitación sin lanzar excepciones");
    }

    @Test
    void testDeleteHabitacion() throws SQLException {
        // Arrange: Crear una habitación y luego eliminarla
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion("103");
        habitacion.setId_tipo_habitacion(1);
        habitacion.setEstado("Disponible");
        habitacion.setEstado_limpieza("Limpio");

        habitacionDAO.createHabitacion(habitacion);

        // Act & Assert: Verificar que no lanza excepción al eliminar
        assertDoesNotThrow(() -> habitacionDAO.deleteHabitacion(habitacion.getId_habitacion()),
                "Debería eliminar la habitación sin lanzar excepciones");
    }

    @Test
    void testGetHabitacionById() throws SQLException {
        // Arrange: Crear una habitación y luego recuperarla por ID
        Habitacion habitacion = new Habitacion();
        habitacion.setNumero_habitacion("104");
        habitacion.setId_tipo_habitacion(1);
        habitacion.setEstado("Disponible");
        habitacion.setEstado_limpieza("Limpio");

        habitacionDAO.createHabitacion(habitacion);

        // Act: Recuperar la habitación por su ID
        Habitacion retrievedHabitacion = habitacionDAO.getHabitacionById(habitacion.getId_habitacion());

        // Assert: Verificar que la habitación recuperada es igual a la creada
        assertNotNull(retrievedHabitacion, "La habitación debería existir en la base de datos");
        assertEquals(habitacion.getNumero_habitacion(), retrievedHabitacion.getNumero_habitacion(),
                "El número de habitación debería coincidir");
    }

    @Test
    void testGetHabitaciones() throws SQLException {
        // Act: Obtener todas las habitaciones
        List<Habitacion> habitaciones = habitacionDAO.getHabitaciones();

        // Assert: Verificar que la lista de habitaciones no es nula
        assertNotNull(habitaciones, "La lista de habitaciones no debería ser nula");
        assertTrue(habitaciones.size() > 0, "La lista de habitaciones debería contener al menos un elemento");
    }
}
