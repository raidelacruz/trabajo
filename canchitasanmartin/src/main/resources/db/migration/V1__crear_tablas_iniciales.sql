CREATE TABLE clientes(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    dni VARCHAR(15) UNIQUE NULL,
    telefono VARCHAR(20) NOT NULL,
    fecha_registro TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE canchas(
    id INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(50) DEFAULT 'Canchita San Martin 1',
    capacidad INT NOT NULL,
    precio_hora_dia DECIMAL(10,2) NOT NULL,
    precio_hora_noche DECIMAL(10,2) NOT NULL
);

CREATE TABLE reservas (
    id_reserva INT PRIMARY KEY AUTO_INCREMENT,
    id_clientes INT  NOT NULL,
    id_canchas INT NOT NULL, 
    fecha_cancha DATE NOT NULL,
    hora_inicio TIME NOT NULL,
    hora_fin TIME NOT NULL,
    estado ENUM('pendiente','confirmada','cancelada','finalizada')DEFAULT 'pendiente',
    fecha_creacion TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    
    CONSTRAINT fk_reservas_clientes FOREIGN KEY (id_clientes) REFERENCES clientes(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT fk_reservas_canchas FOREIGN KEY (id_canchas) REFERENCES canchas(id)
    ON UPDATE CASCADE ON DELETE RESTRICT,

    CONSTRAINT uq_cancha_fecha_hora UNIQUE (id_canchas, fecha_cancha, hora_inicio),

    -- VALIDAR FECHAS
    
    CONSTRAINT chk_fechas_reserva CHECK (hora_fin > hora_inicio)
);

CREATE TABLE pagos(
    id_pago INT PRIMARY KEY AUTO_INCREMENT,
    monto DECIMAL(10,2) NOT NULL,
    id_reservas INT NOT NULL,
    fecha_pago DATE NOT NULL,
    hora_pago TIME NOT NULL,

    CONSTRAINT fk_pagos_reservas FOREIGN KEY (id_reservas) REFERENCES reservas(id_reserva)
    ON UPDATE CASCADE ON DELETE RESTRICT

);