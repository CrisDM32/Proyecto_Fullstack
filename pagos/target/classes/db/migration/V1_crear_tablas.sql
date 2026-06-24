CREATE TABLE `cita` (
                        `id_cita` BIGINT NOT NULL AUTO_INCREMENT,
                        `nombre_paciente` VARCHAR(100) NOT NULL,

                        PRIMARY KEY (`id_cita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `pagos` (
                         `id_pago` BIGINT NOT NULL AUTO_INCREMENT,
                         `monto_pago` INT NOT NULL,
                         `metodo_pago` VARCHAR(100) NOT NULL,
                         `id_transaccion_tarjeta` INT,
                         `estado_pago` VARCHAR(50) NOT NULL,
                         `fecha_pago` DATE NOT NULL,
                         `id_cita` BIGINT NOT NULL,

                         PRIMARY KEY (`id_pago`),

                         KEY `FK_PAGO_CITA` (`id_cita`),

                         CONSTRAINT `FK_PAGO_CITA`
                             FOREIGN KEY (`id_cita`)
                                 REFERENCES `cita` (`id_cita`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;