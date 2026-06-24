CREATE TABLE `pacientes` (
                             `id_paciente` BIGINT NOT NULL AUTO_INCREMENT,
                             `nombre_completo` VARCHAR(100) NOT NULL,
                             `rut` INT NOT NULL,
                             `fecha_nacimiento` DATE NOT NULL,
                             `genero` VARCHAR(30) NOT NULL,
                             `telefono` INT NOT NULL,
                             `correo` VARCHAR(75),
                             `direccion` VARCHAR(255) NOT NULL,
                             `grupo_sanguineo` VARCHAR(20) NOT NULL,

                             PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `contactos_emergencia` (
                                        `id_contacto_emergencia` BIGINT NOT NULL AUTO_INCREMENT,
                                        `nombre_contacto_emergencia` VARCHAR(100) NOT NULL,
                                        `telefono_contacto_emergencia` INT NOT NULL,
                                        `id_paciente` BIGINT NOT NULL,

                                        PRIMARY KEY (`id_contacto_emergencia`),

                                        KEY `FK_CONTACTO_PACIENTE` (`id_paciente`),

                                        CONSTRAINT `FK_CONTACTO_PACIENTE`
                                            FOREIGN KEY (`id_paciente`)
                                                REFERENCES `pacientes` (`id_paciente`)
                                                ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;