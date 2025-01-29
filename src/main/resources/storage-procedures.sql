
-- Procedimientos almacenados cliente

DELIMITER //

CREATE PROCEDURE sp_create_cliente(
    IN p_nombre VARCHAR(100),
    IN p_apellido VARCHAR(100),
    IN p_tipo_documento VARCHAR(20),
    IN p_numero_documento VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_fecha_nacimiento DATE
)
BEGIN
INSERT INTO cliente (nombre, apellido, tipo_documento, numero_documento, email, telefono, fecha_nacimiento)
VALUES (p_nombre, p_apellido, p_tipo_documento, p_numero_documento, p_email, p_telefono, p_fecha_nacimiento);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_update_cliente(
    IN p_id_cliente INT,
    IN p_nombre VARCHAR(100),
    IN p_apellido VARCHAR(100),
    IN p_tipo_documento VARCHAR(20),
    IN p_numero_documento VARCHAR(20),
    IN p_email VARCHAR(100),
    IN p_telefono VARCHAR(20),
    IN p_fecha_nacimiento DATE
)
BEGIN
UPDATE cliente
SET nombre = p_nombre,
    apellido = p_apellido,
    tipo_documento = p_tipo_documento,
    numero_documento = p_numero_documento,
    email = p_email,
    telefono = p_telefono,
    fecha_nacimiento = p_fecha_nacimiento
WHERE id_cliente = p_id_cliente;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_delete_cliente(IN p_id_cliente INT)
BEGIN
DELETE FROM cliente WHERE id_cliente = p_id_cliente;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_cliente_by_id(IN p_id_cliente INT)
BEGIN
SELECT * FROM cliente WHERE id_cliente = p_id_cliente;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_all_clientes()
BEGIN
SELECT * FROM cliente;
END //

DELIMITER ;

-- Procedures almacenados habitacion

DELIMITER //

CREATE PROCEDURE sp_create_habitacion(
    IN p_numero_habitacion VARCHAR(10),
    IN p_id_tipo_habitacion INT,
    IN p_estado VARCHAR(50),
    IN p_estado_limpieza VARCHAR(50)
)
BEGIN
INSERT INTO habitacion (numero_habitacion, id_tipo_habitacion, estado, estado_limpieza)
VALUES (p_numero_habitacion, p_id_tipo_habitacion, p_estado, p_estado_limpieza);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_update_habitacion(
    IN p_id_habitacion INT,
    IN p_numero_habitacion VARCHAR(10),
    IN p_id_tipo_habitacion INT,
    IN p_estado VARCHAR(50),
    IN p_estado_limpieza VARCHAR(50)
)
BEGIN
UPDATE habitacion
SET numero_habitacion = p_numero_habitacion,
    id_tipo_habitacion = p_id_tipo_habitacion,
    estado = p_estado,
    estado_limpieza = p_estado_limpieza
WHERE id_habitacion = p_id_habitacion;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_delete_habitacion(IN p_id_habitacion INT)
BEGIN
DELETE FROM habitacion WHERE id_habitacion = p_id_habitacion;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_habitacion_by_id(IN p_id_habitacion INT)
BEGIN
SELECT * FROM habitacion WHERE id_habitacion = p_id_habitacion;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_all_habitaciones()
BEGIN
SELECT * FROM habitacion;
END //

DELIMITER ;

-- Procedimientos almacenados reserva

DELIMITER //

CREATE PROCEDURE sp_create_reserva(
    IN p_id_cliente INT,
    IN p_id_habitacion INT,
    IN p_monto_total DECIMAL(6,2),
    IN p_metodo_pago VARCHAR(20),
    IN p_fecha_ingreso TIMESTAMP,
    IN p_fecha_salida TIMESTAMP,
    IN p_id_admin INT,
    IN p_estado_reserva VARCHAR(50)
)
BEGIN
INSERT INTO reserva (id_cliente, id_habitacion, monto_total, metodo_pago, fecha_ingreso, fecha_salida, id_admin, estado_reserva)
VALUES (p_id_cliente, p_id_habitacion, p_monto_total, p_metodo_pago, p_fecha_ingreso, p_fecha_salida, p_id_admin, p_estado_reserva);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_update_reserva(
    IN p_id_reserva INT,
    IN p_id_cliente INT,
    IN p_id_habitacion INT,
    IN p_monto_total DECIMAL(6,2),
    IN p_metodo_pago VARCHAR(20),
    IN p_fecha_ingreso TIMESTAMP,
    IN p_fecha_salida TIMESTAMP,
    IN p_id_admin INT,
    IN p_estado_reserva VARCHAR(50)
)
BEGIN
UPDATE reserva
SET id_cliente = p_id_cliente,
    id_habitacion = p_id_habitacion,
    monto_total = p_monto_total,
    metodo_pago = p_metodo_pago,
    fecha_ingreso = p_fecha_ingreso,
    fecha_salida = p_fecha_salida,
    id_admin = p_id_admin,
    estado_reserva = p_estado_reserva
WHERE id_reserva = p_id_reserva;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_delete_reserva(IN p_id_reserva INT)
BEGIN
DELETE FROM reserva WHERE id_reserva = p_id_reserva;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_reserva_by_id(IN p_id_reserva INT)
BEGIN
SELECT * FROM reserva WHERE id_reserva = p_id_reserva;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_all_reservas()
BEGIN
SELECT * FROM reserva;
END //

DELIMITER ;


-- Procedimientos almacenados tipo_habitacion

DELIMITER //

CREATE PROCEDURE sp_create_tipo_habitacion(
    IN p_nombre_habitacion VARCHAR(50),
    IN p_descripcion VARCHAR(255),
    IN p_precio_noche DECIMAL(10,2)
)
BEGIN
INSERT INTO tipo_habitacion (nombreHabitacion, descripcion, precio_noche)
VALUES (p_nombre_habitacion, p_descripcion, p_precio_noche);
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_update_tipo_habitacion(
    IN p_id_tipo_habitacion INT,
    IN p_nombre_habitacion VARCHAR(50),
    IN p_descripcion VARCHAR(255),
    IN p_precio_noche DECIMAL(10,2)
)
BEGIN
UPDATE tipo_habitacion
SET nombreHabitacion = p_nombre_habitacion,
    descripcion = p_descripcion,
    precio_noche = p_precio_noche
WHERE id_tipo_habitacion = p_id_tipo_habitacion;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_delete_tipo_habitacion(IN p_id_tipo_habitacion INT)
BEGIN
DELETE FROM tipo_habitacion WHERE id_tipo_habitacion = p_id_tipo_habitacion;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_tipo_habitacion_by_id(IN p_id_tipo_habitacion INT)
BEGIN
SELECT * FROM tipo_habitacion WHERE id_tipo_habitacion = p_id_tipo_habitacion;
END //

DELIMITER ;

DELIMITER //

CREATE PROCEDURE sp_get_all_tipo_habitaciones()
BEGIN
SELECT * FROM tipo_habitacion;
END //

DELIMITER ;

-- Procedimientos almacenados solicitud

DELIMITER //

DELIMITER $$

CREATE PROCEDURE sp_create_solicitud (
    IN p_id_tipo_habitacion INT,
    IN p_fecha_solicitud TIMESTAMP,
    IN p_fecha_reserva TIMESTAMP,
    IN p_nombre_cliente VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_duracion INT,
    IN p_estado ENUM('PENDIENTE', 'RESERVADO', 'CANCELADO')
)
BEGIN
    INSERT INTO solicitud (id_tipo_habitacion, fecha_solicitud, fecha_reserva, nombre_cliente, correo, duracion, estado)
    VALUES (p_id_tipo_habitacion, p_fecha_solicitud, p_fecha_reserva, p_nombre_cliente, p_correo, p_duracion, p_estado);
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_update_solicitud (
    IN p_id_solicitud INT,
    IN p_id_tipo_habitacion INT,
    IN p_fecha_solicitud TIMESTAMP,
    IN p_fecha_reserva TIMESTAMP,
    IN p_nombre_cliente VARCHAR(100),
    IN p_correo VARCHAR(100),
    IN p_duracion INT,
    IN p_estado ENUM('PENDIENTE', 'RESERVADO', 'CANCELADO')
)
BEGIN
    UPDATE solicitud
    SET id_tipo_habitacion = p_id_tipo_habitacion,
        fecha_solicitud = p_fecha_solicitud,
        fecha_reserva = p_fecha_reserva,
        nombre_cliente = p_nombre_cliente,
        correo = p_correo,
        duracion = p_duracion,
        estado = p_estado
    WHERE id_solicitud = p_id_solicitud;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_delete_solicitud (
    IN p_id_solicitud INT
)
BEGIN
    DELETE FROM solicitud WHERE id_solicitud = p_id_solicitud;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_get_solicitud_by_id (
    IN p_id_solicitud INT
)
BEGIN
    SELECT * FROM solicitud WHERE id_solicitud = p_id_solicitud;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_get_all_solicitudes()
BEGIN
    SELECT * FROM solicitud;
END $$

DELIMITER ;

DELIMITER $$

CREATE PROCEDURE sp_get_all_solicitudes_by_estado(IN estado INT)
BEGIN
    SELECT * FROM solicitud WHERE estado = estado;
END $$

DELIMITER ;