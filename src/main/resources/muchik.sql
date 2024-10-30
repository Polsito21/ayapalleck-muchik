CREATE DATABASE IF NOT EXISTS muchik;
USE muchik;

-- Tabla tipo_habitacion
CREATE TABLE tipo_habitacion (
                                 id_tipo_habitacion INT AUTO_INCREMENT PRIMARY KEY,
                                 nombreHabitacion VARCHAR(50) NOT NULL,
                                 descripcion VARCHAR(255),
                                 precio_noche DECIMAL(10,2) NOT NULL
);

-- Tabla habitacion
CREATE TABLE habitacion (
                            id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
                            numero_habitacion VARCHAR(10) UNIQUE NOT NULL,
                            id_tipo_habitacion INT NOT NULL,
                            estado VARCHAR(50) NOT NULL,
                            estado_limpieza VARCHAR(50) NOT NULL,
                            FOREIGN KEY (id_tipo_habitacion) REFERENCES tipo_habitacion(id_tipo_habitacion)
);

-- Tabla cliente
CREATE TABLE cliente (
                         id_cliente INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(100) NOT NULL,
                         apellido VARCHAR(100) NOT NULL,
                         tipo_documento VARCHAR(20) NOT NULL,
                         numero_documento VARCHAR(20) UNIQUE NOT NULL,
                         email VARCHAR(100),
                         telefono VARCHAR(20),
                         fecha_nacimiento DATE
);

-- Tabla administrador
CREATE TABLE administrador (
                               id_admin INT AUTO_INCREMENT PRIMARY KEY,
                               usuario VARCHAR(100) UNIQUE NOT NULL,
                               password VARCHAR(64) NOT NULL
);

-- Tabla reserva
CREATE TABLE reserva (
                         id_reserva INT AUTO_INCREMENT PRIMARY KEY,
                         id_cliente INT NOT NULL,
                         id_habitacion INT NOT NULL,
                         monto_total DECIMAL(6,2) NOT NULL,
                         metodo_pago VARCHAR(20) NOT NULL,
                         fecha_ingreso TIMESTAMP NOT NULL,
                         fecha_salida TIMESTAMP NOT NULL,
                         id_admin INT NOT NULL,
                         estado_reserva VARCHAR(50) NOT NULL,
                         FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
                         FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion),
                         FOREIGN KEY (id_admin) REFERENCES administrador(id_admin)
);

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
