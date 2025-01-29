import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import utp.edu.pe.ayapalleckmuchik.dao.ClienteDAO;
import utp.edu.pe.ayapalleckmuchik.model.Cliente;

import javax.naming.NamingException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClienteDAOTest {

    private ClienteDAO clienteDAO;

    @BeforeEach
    void setUp() throws SQLException, NamingException {
        // Inicializar el objeto ClienteDAO antes de cada prueba
        clienteDAO = new ClienteDAO();
    }

    @AfterEach
    void tearDown() throws SQLException {
        // Cerrar la conexión después de cada prueba
        clienteDAO.close();
    }

    @Test
    void testCreateCliente() {
        // Arrange: Crear un nuevo objeto Cliente con datos válidos
        Cliente cliente = new Cliente();
        cliente.setNombre("Juan");
        cliente.setApellido("Pérez");
        cliente.setTipo_documento("DNI");
        cliente.setNumero_documento("12345678");
        cliente.setEmail("juan.perez@example.com");
        cliente.setTelefono("987654321");
        cliente.setFecha_nacimiento(LocalDate.of(1990, 1, 1));

        // Act & Assert: Verificar que createCliente no lanza ninguna excepción
        assertDoesNotThrow(() -> clienteDAO.createCliente(cliente),
                "Debería crear el cliente sin lanzar excepciones");
    }

    @Test
    void testUpdateCliente() throws SQLException {
        // Arrange: Crear y guardar un cliente existente
        Cliente cliente = new Cliente();
        cliente.setNombre("Ana");
        cliente.setApellido("Gómez");
        cliente.setTipo_documento("DNI");
        cliente.setNumero_documento("87654321");
        cliente.setEmail("ana.gomez@example.com");
        cliente.setTelefono("912345678");
        cliente.setFecha_nacimiento(LocalDate.of(1992, 2, 2));
        clienteDAO.createCliente(cliente);

        // Update
        cliente.setNombre("Ana María");

        // Act & Assert: Verificar que updateCliente no lanza ninguna excepción
        assertDoesNotThrow(() -> clienteDAO.updateCliente(cliente),
                "Debería actualizar el cliente sin lanzar excepciones");
    }

    @Test
    void testDeleteCliente() throws SQLException {
        // Arrange: Crear y guardar un cliente existente
        Cliente cliente = new Cliente();
        cliente.setNombre("Carlos");
        cliente.setApellido("Lopez");
        cliente.setTipo_documento("DNI");
        cliente.setNumero_documento("12312312");
        cliente.setEmail("carlos.lopez@example.com");
        cliente.setTelefono("987123456");
        cliente.setFecha_nacimiento(LocalDate.of(1985, 3, 3));
        clienteDAO.createCliente(cliente);

        // Act: Borrar el cliente
        assertDoesNotThrow(() -> clienteDAO.deleteCliente(cliente.getId_cliente()),
                "Debería borrar el cliente sin lanzar excepciones");

        // Assert: Verificar que el cliente ha sido borrado
        assertNull(clienteDAO.getClienteById(cliente.getId_cliente()),
                "El cliente debería ser nulo después de la eliminación");
    }

    @Test
    void testGetClienteById() throws SQLException {
        // Arrange: Crear y guardar un cliente existente
        Cliente cliente = new Cliente();
        cliente.setNombre("Pedro");
        cliente.setApellido("Martinez");
        cliente.setTipo_documento("DNI");
        cliente.setNumero_documento("11111111");
        cliente.setEmail("pedro.martinez@example.com");
        cliente.setTelefono("998877665");
        cliente.setFecha_nacimiento(LocalDate.of(1995, 5, 5));
        clienteDAO.createCliente(cliente);

        // Act: Obtener el cliente por ID
        Cliente retrievedCliente = clienteDAO.getClienteById(cliente.getId_cliente());

        // Assert: Verificar que el cliente obtenido es el mismo que el creado
        assertNotNull(retrievedCliente, "El cliente debería ser encontrado");
        assertEquals(cliente.getNombre(), retrievedCliente.getNombre(), "Los nombres deberían coincidir");
        assertEquals(cliente.getApellido(), retrievedCliente.getApellido(), "Los apellidos deberían coincidir");
    }

    @Test
    void testGetClientes() throws SQLException {
        // Act: Obtener la lista de clientes
        List<Cliente> clientes = clienteDAO.getClientes();

        // Assert: Verificar que la lista no sea nula y contenga al menos un cliente
        assertNotNull(clientes, "La lista de clientes no debería ser nula");
        assertFalse(clientes.isEmpty(), "La lista de clientes debería tener al menos un cliente");
    }
}
