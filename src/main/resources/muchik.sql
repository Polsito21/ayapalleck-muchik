CREATE DATABASE IF NOT EXISTS muchik;
USE muchik;

-- Tabla tipo_habitacion
CREATE TABLE tipo_habitacion (
                                 id_tipo_habitacion INT AUTO_INCREMENT PRIMARY KEY,
                                 nombreHabitacion VARCHAR(255) NOT NULL,
                                 descripcion VARCHAR(255),
                                 precio_noche DECIMAL(10,2) NOT NULL
);

-- Tabla habitacion
CREATE TABLE habitacion (
                            id_habitacion INT AUTO_INCREMENT PRIMARY KEY,
                            numero_habitacion VARCHAR(255) UNIQUE NOT NULL,
                            id_tipo_habitacion INT NOT NULL,
                            estado VARCHAR(255) NOT NULL,
                            estado_limpieza VARCHAR(255) NOT NULL,
                            FOREIGN KEY (id_tipo_habitacion) REFERENCES tipo_habitacion(id_tipo_habitacion)
);

-- Tabla cliente
CREATE TABLE cliente (
                         id_cliente INT AUTO_INCREMENT PRIMARY KEY,
                         nombre VARCHAR(255) NOT NULL,
                         apellido VARCHAR(255) NOT NULL,
                         tipo_documento VARCHAR(255) NOT NULL,
                         numero_documento VARCHAR(255) UNIQUE NOT NULL,
                         email VARCHAR(255),
                         telefono VARCHAR(255),
                         fecha_nacimiento DATE
);

-- Tabla administrador
CREATE TABLE administrador (
                               id_admin INT AUTO_INCREMENT PRIMARY KEY,
                               usuario VARCHAR(255) UNIQUE NOT NULL,
                               password VARCHAR(255) NOT NULL
);

-- Tabla reserva
CREATE TABLE reserva (
                         id_reserva INT AUTO_INCREMENT PRIMARY KEY,
                         id_cliente INT NOT NULL,
                         id_habitacion INT NOT NULL,
                         monto_total DECIMAL(6,2) NOT NULL,
                         metodo_pago VARCHAR(255) NOT NULL,
                         fecha_ingreso TIMESTAMP NOT NULL,
                         fecha_salida TIMESTAMP NOT NULL,
                         id_admin INT NOT NULL,
                         estado_reserva VARCHAR(50) NOT NULL,
                         FOREIGN KEY (id_cliente) REFERENCES cliente(id_cliente),
                         FOREIGN KEY (id_habitacion) REFERENCES habitacion(id_habitacion),
                         FOREIGN KEY (id_admin) REFERENCES administrador(id_admin)
);

CREATE TABLE solicitud (
                           id_solicitud INT AUTO_INCREMENT PRIMARY KEY,
                           id_tipo_habitacion INT NOT NULL,
                           fecha_solicitud TIMESTAMP NOT NULL,
                           fecha_reserva TIMESTAMP NOT NULL,
                           nombre_cliente VARCHAR(255) NOT NULL,
                           correo VARCHAR(255) NOT NULL,
                           duracion INT NOT NULL,
                           estado ENUM ('PENDIENTE', 'RESERVADO', 'CANCELADO') NOT NULL,
                           FOREIGN KEY (id_tipo_habitacion) REFERENCES tipo_habitacion(id_tipo_habitacion)
);