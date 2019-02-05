-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 05-02-2019 a las 01:57:29
-- Versión del servidor: 10.1.34-MariaDB
-- Versión de PHP: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `petclinic`
--
CREATE DATABASE IF NOT EXISTS `petclinic` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `petclinic`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `authorities`
--
-- Creación: 24-01-2019 a las 17:39:02
--

CREATE TABLE IF NOT EXISTS `authorities` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) DEFAULT NULL,
  `authority` varchar(45) NOT NULL DEFAULT 'USER',
  PRIMARY KEY (`id`),
  KEY `a_fk` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `authorities`:
--   `username`
--       `usuarios` -> `username`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log`
--
-- Creación: 31-01-2019 a las 18:01:13
--

CREATE TABLE IF NOT EXISTS `log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `informacion` varchar(255) COLLATE latin1_spanish_ci DEFAULT '   ',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- RELACIONES PARA LA TABLA `log`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `medicamento`
--
-- Creación: 24-01-2019 a las 17:39:03
--

CREATE TABLE IF NOT EXISTS `medicamento` (
  `id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT,
  `nombre` varchar(40) NOT NULL,
  `ingrediente_activo` varchar(40) NOT NULL,
  `presentacion` varchar(40) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `medicamento`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `owners`
--
-- Creación: 02-02-2019 a las 06:02:30
--

CREATE TABLE IF NOT EXISTS `owners` (
  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(80) DEFAULT NULL,
  `country` varchar(80) DEFAULT NULL,
  `estado` varchar(80) DEFAULT NULL,
  `telephone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `last_name` (`last_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `owners`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pets`
--
-- Creación: 02-02-2019 a las 06:01:55
--

CREATE TABLE IF NOT EXISTS `pets` (
  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(30) DEFAULT NULL,
  `birth_date` date DEFAULT NULL,
  `type_id` int(4) UNSIGNED NOT NULL,
  `owner_id` int(4) UNSIGNED NOT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`),
  KEY `owner_id` (`owner_id`),
  KEY `type_id` (`type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `pets`:
--   `owner_id`
--       `owners` -> `id`
--   `type_id`
--       `types` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `producto`
--
-- Creación: 02-02-2019 a las 05:51:06
--

CREATE TABLE IF NOT EXISTS `producto` (
  `Id` int(5) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(50) NOT NULL,
  `descripcion` varchar(255) NOT NULL,
  `numero_serie` varchar(255) NOT NULL,
  `cantidad` varchar(255) NOT NULL,
  `precio` varchar(255) NOT NULL,
  `file` text,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `producto`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `specialties`
--
-- Creación: 24-01-2019 a las 17:39:03
--

CREATE TABLE IF NOT EXISTS `specialties` (
  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `specialties`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `types`
--
-- Creación: 24-01-2019 a las 17:39:03
--

CREATE TABLE IF NOT EXISTS `types` (
  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `name` varchar(80) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `types`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--
-- Creación: 04-02-2019 a las 20:57:22
--

CREATE TABLE IF NOT EXISTS `usuarios` (
  `idusuarios` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(45) NOT NULL,
  `password` varchar(255) NOT NULL,
  `email` varchar(45) NOT NULL,
  `enabled` int(11) NOT NULL DEFAULT '1',
  `codigopostal` varchar(10) NOT NULL,
  PRIMARY KEY (`idusuarios`),
  KEY `username` (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- RELACIONES PARA LA TABLA `usuarios`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vets`
--
-- Creación: 24-01-2019 a las 17:39:04
--

CREATE TABLE IF NOT EXISTS `vets` (
  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `first_name` varchar(30) DEFAULT NULL,
  `last_name` varchar(30) DEFAULT NULL,
  `telephone` varchar(10) NOT NULL,
  `Schedule` varchar(100) NOT NULL,
  `specialty_id` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `last_name` (`last_name`),
  KEY `specialty` (`specialty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `vets`:
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vet_specialties`
--
-- Creación: 24-01-2019 a las 17:39:04
--

CREATE TABLE IF NOT EXISTS `vet_specialties` (
  `vet_id` int(4) UNSIGNED NOT NULL,
  `specialty_id` int(4) UNSIGNED NOT NULL,
  UNIQUE KEY `vet_id` (`vet_id`,`specialty_id`),
  KEY `specialty_id` (`specialty_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `vet_specialties`:
--   `vet_id`
--       `vets` -> `id`
--   `specialty_id`
--       `specialties` -> `id`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `visits`
--
-- Creación: 24-01-2019 a las 17:39:04
--

CREATE TABLE IF NOT EXISTS `visits` (
  `id` int(4) UNSIGNED NOT NULL AUTO_INCREMENT,
  `pet_id` int(4) UNSIGNED NOT NULL,
  `visit_date` date DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `pet_id` (`pet_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- RELACIONES PARA LA TABLA `visits`:
--   `pet_id`
--       `pets` -> `id`
--

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `authorities`
--
ALTER TABLE `authorities`
  ADD CONSTRAINT `a_fk` FOREIGN KEY (`username`) REFERENCES `usuarios` (`username`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Filtros para la tabla `pets`
--
ALTER TABLE `pets`
  ADD CONSTRAINT `pets_ibfk_1` FOREIGN KEY (`owner_id`) REFERENCES `owners` (`id`),
  ADD CONSTRAINT `pets_ibfk_2` FOREIGN KEY (`type_id`) REFERENCES `types` (`id`);

--
-- Filtros para la tabla `vet_specialties`
--
ALTER TABLE `vet_specialties`
  ADD CONSTRAINT `vet_specialties_ibfk_1` FOREIGN KEY (`vet_id`) REFERENCES `vets` (`id`),
  ADD CONSTRAINT `vet_specialties_ibfk_2` FOREIGN KEY (`specialty_id`) REFERENCES `specialties` (`id`);

--
-- Filtros para la tabla `visits`
--
ALTER TABLE `visits`
  ADD CONSTRAINT `visits_ibfk_1` FOREIGN KEY (`pet_id`) REFERENCES `pets` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
