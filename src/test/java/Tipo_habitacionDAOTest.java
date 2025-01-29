import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.pe.ayapalleckmuchik.dao.Tipo_habitacionDAO;
import utp.edu.pe.ayapalleckmuchik.model.Tipo_habitacion;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class Tipo_habitacionDAOTest {

    private Tipo_habitacionDAO tipoHabitacionDAO;

    @BeforeEach
    void setUp() throws SQLException, NamingException {
        // Inicializar el objeto Tipo_habitacionDAO antes de cada prueba
        tipoHabitacionDAO = new Tipo_habitacionDAO();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        tipoHabitacionDAO.close();
    }

    @Test
    void testCreateTipoHabitacion() {
        // Arrange: Crear un nuevo objeto Tipo_habitacion con datos válidos
        Tipo_habitacion tipoHabitacion = new Tipo_habitacion();
        tipoHabitacion.setNombre_habitacion("Suite");
        tipoHabitacion.setDescripcion("Habitación con vista al mar");
        tipoHabitacion.setPrecio_noche(150.0);

        // Act & Assert: Verificar que createTipoHabitacion no lanza ninguna excepción
        assertDoesNotThrow(() -> tipoHabitacionDAO.createTipoHabitacion(tipoHabitacion),
                "Debería crear el tipo de habitación sin lanzar excepciones");
    }

    @Test
    void testCreateTipoHabitacionWithInvalidData() {
        // Arrange: Crear un Tipo_habitacion con datos inválidos (nombre nulo)
        Tipo_habitacion tipoHabitacion = new Tipo_habitacion();
        tipoHabitacion.setNombre_habitacion(null); // Valor inválido
        tipoHabitacion.setDescripcion("Habitación sin nombre");
        tipoHabitacion.setPrecio_noche(100.0);

        // Act & Assert: Verificar que se lanza una excepción al intentar crear un tipo de habitación inválido
        assertThrows(SQLException.class, () -> tipoHabitacionDAO.createTipoHabitacion(tipoHabitacion),
                "Debe lanzar SQLException para datos inválidos");
    }

    @Test
    void testUpdateTipoHabitacion() throws SQLException {
        // Arrange: Crear un tipo de habitación y luego actualizarlo
        Tipo_habitacion tipoHabitacion = new Tipo_habitacion();
        tipoHabitacion.setNombre_habitacion("Económica");
        tipoHabitacion.setDescripcion("Habitación económica");
        tipoHabitacion.setPrecio_noche(80.0);

        tipoHabitacionDAO.createTipoHabitacion(tipoHabitacion);

        // Modificar detalles del tipo de habitación
        tipoHabitacion.setDescripcion("Habitación económica renovada");
        tipoHabitacion.setPrecio_noche(90.0);

        // Act & Assert: Verificar que no lanza excepción al actualizar
        assertDoesNotThrow(() -> tipoHabitacionDAO.updateTipoHabitacion(tipoHabitacion),
                "Debería actualizar el tipo de habitación sin lanzar excepciones");
    }

    @Test
    void testDeleteTipoHabitacion() throws SQLException {
        // Arrange: Crear un tipo de habitación y luego eliminarlo
        Tipo_habitacion tipoHabitacion = new Tipo_habitacion();
        tipoHabitacion.setNombre_habitacion("Temporal");
        tipoHabitacion.setDescripcion("Habitación para pruebas temporales");
        tipoHabitacion.setPrecio_noche(50.0);

        tipoHabitacionDAO.createTipoHabitacion(tipoHabitacion);

        // Act & Assert: Verificar que no lanza excepción al eliminar
        assertDoesNotThrow(() -> tipoHabitacionDAO.deleteTipoHabitacion(tipoHabitacion.getId_tipo_habitacion()),
                "Debería eliminar el tipo de habitación sin lanzar excepciones");
    }

    @Test
    void testGetTipoHabitacionById() throws SQLException {
        // Arrange: Crear un tipo de habitación y luego recuperarlo por ID
        Tipo_habitacion tipoHabitacion = new Tipo_habitacion();
        tipoHabitacion.setNombre_habitacion("Deluxe");
        tipoHabitacion.setDescripcion("Habitación de lujo");
        tipoHabitacion.setPrecio_noche(200.0);

        tipoHabitacionDAO.createTipoHabitacion(tipoHabitacion);

        // Act: Recuperar el tipo de habitación por su ID
        Tipo_habitacion retrievedTipoHabitacion = tipoHabitacionDAO.getTipoHabitacionById(tipoHabitacion.getId_tipo_habitacion());

        // Assert: Verificar que el tipo de habitación recuperado es igual al creado
        assertNotNull(retrievedTipoHabitacion, "El tipo de habitación debería existir en la base de datos");
        assertEquals(tipoHabitacion.getNombre_habitacion(), retrievedTipoHabitacion.getNombre_habitacion(),
                "El nombre de la habitación debería coincidir");
    }

    @Test
    void testGetTipoHabitaciones() throws SQLException {
        // Act: Obtener todos los tipos de habitación
        List<Tipo_habitacion> tipoHabitaciones = tipoHabitacionDAO.getTipoHabitaciones();

        // Assert: Verificar que la lista de tipos de habitación no es nula y contiene elementos
        assertNotNull(tipoHabitaciones, "La lista de tipos de habitación no debería ser nula");
        assertTrue(tipoHabitaciones.size() > 0, "La lista de tipos de habitación debería contener al menos un elemento");
    }
}
