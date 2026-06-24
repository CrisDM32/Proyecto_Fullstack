CREATE TABLE `profesional` (
                               `id_profesional` BIGINT NOT NULL AUTO_INCREMENT,
                               `rut` INT NOT NULL,
                               `nombre_profesional` VARCHAR(150) NOT NULL,
                               `especialidad` VARCHAR(100) NOT NULL,
                               `telefono` INT NOT NULL,
                               `correo` VARCHAR(100) NOT NULL,
                               `direccion` VARCHAR(255) NOT NULL,
                               `numero_licencia` INT NOT NULL,
                               `fecha_contratacion` DATE NOT NULL,

                               PRIMARY KEY (`id_profesional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `agenda_medica` (
                                 `id_agenda` BIGINT NOT NULL AUTO_INCREMENT,
                                 `fecha` DATE NOT NULL,
                                 `hora_inicio_turno` DATETIME NOT NULL,
                                 `hora_fin_turno` DATETIME NOT NULL,
                                 `asistencia_turno` VARCHAR(50) NOT NULL,
                                 `id_profesional` BIGINT,

                                 PRIMARY KEY (`id_agenda`),

                                 KEY `FK_AGENDA_PROFESIONAL` (`id_profesional`),

                                 CONSTRAINT `FK_AGENDA_PROFESIONAL`
                                     FOREIGN KEY (`id_profesional`)
                                         REFERENCES `profesional` (`id_profesional`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;


CREATE TABLE `citas` (
                         `id_cita` BIGINT NOT NULL AUTO_INCREMENT,
                         `id_paciente` BIGINT NOT NULL,
                         `id_profesional` BIGINT NOT NULL,
                         `id_pago` BIGINT NOT NULL,
                         `fecha_hora_cita` DATETIME NOT NULL,
                         `motivo_consulta` VARCHAR(200) NOT NULL,
                         `estado_cita` VARCHAR(50) NOT NULL,
                         `id_agenda` BIGINT,

                         PRIMARY KEY (`id_cita`),

                         KEY `FK_CITA_AGENDA` (`id_agenda`),

                         CONSTRAINT `FK_CITA_AGENDA`
                             FOREIGN KEY (`id_agenda`)
                                 REFERENCES `agenda_medica` (`id_agenda`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;