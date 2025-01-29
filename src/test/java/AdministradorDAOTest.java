import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.pe.ayapalleckmuchik.dao.AdministradorDAO;
import utp.edu.pe.ayapalleckmuchik.model.Administrador;

import javax.naming.NamingException;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

class AdministradorDAOTest {


    private AdministradorDAO administradorDAO;

    @BeforeEach
    void setUp() throws SQLException, NamingException {
        // Inicializar el objeto AdministradorDAO antes de cada prueba
        administradorDAO = new AdministradorDAO();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        administradorDAO.close();
    }

    @Test
    void testCreateAdministrador() {
        // Arrange: Crear un nuevo objeto Administrador con datos válidos
        Administrador admin = new Administrador();
        admin.setUsuario("nuevoAdmin");
        admin.setPassword("passwordSeguro");

        // Act & Assert: Verificar que createAdministrador no lanza ninguna excepción
        assertDoesNotThrow(() -> administradorDAO.createAdministrador(admin),
                "Debería crear el administrador sin lanzar excepciones");
    }

    @Test
    void testCreateAdministradorWithInvalidData() {
        // Arrange: Crear un Administrador con datos inválidos (usuario nulo)
        Administrador admin = new Administrador();
        admin.setUsuario(null); // Valor inválido
        admin.setPassword("passwordSeguro");

        // Act & Assert: Verificar que se lanza una excepción al intentar crear un administrador inválido
        assertThrows(SQLException.class, () -> administradorDAO.createAdministrador(admin),
                "Debe lanzar SQLException para datos inválidos");
    }

    @Test
    void testCreateDuplicateAdministrador() {
        // Arrange: Crear un Administrador con un usuario duplicado
        Administrador admin1 = new Administrador();
        admin1.setUsuario("adminDuplicado");
        admin1.setPassword("passwordSeguro");

        Administrador admin2 = new Administrador();
        admin2.setUsuario("adminDuplicado"); // Mismo usuario, debería ser duplicado
        admin2.setPassword("otroPassword");

        // Act: Insertar el primer administrador
        assertDoesNotThrow(() -> administradorDAO.createAdministrador(admin1),
                "El primer administrador debería crearse correctamente");

        // Act & Assert: Intentar insertar el segundo administrador con usuario duplicado y esperar excepción
        assertThrows(SQLException.class, () -> administradorDAO.createAdministrador(admin2),
                "Debe lanzar SQLException para usuario duplicado");
    }
}