-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-02-2019 a las 07:23:25
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

--
-- Volcado de datos para la tabla `authorities`
--

INSERT IGNORE INTO `authorities` (`id`, `username`, `authority`) VALUES
(1, 'david', 'USER'),
(2, 'admin', 'USER');

--
-- Volcado de datos para la tabla `medicamento`
--

INSERT IGNORE INTO `medicamento` (`id`, `nombre`, `ingrediente_activo`, `presentacion`) VALUES
(1, 'Paracetamol', 'Paracetamol', 'Pastillas');

--
-- Volcado de datos para la tabla `owners`
--

INSERT IGNORE INTO `owners` (`id`, `first_name`, `last_name`, `address`, `city`, `country`, `estado`, `telephone`) VALUES
(1, 'George', 'Franklin', '110 W. Liberty St.', 'Madison', 'USA', 'Ohio', '6085551023'),
(2, 'Betty', 'Davis', '638 Cardinal Ave.', 'Sun Prairie', 'USA', 'Wisconsin', '6085551749'),
(3, 'Eduardo', 'Rodriquez', '2693 Commerce St.', 'McFarland', 'USA', 'Wisconsin', '6085558763'),
(4, 'Harold', 'Davis', '563 Friendly St.', 'Eugene', 'USA', 'Oregón', '6085553198'),
(5, 'Peter', 'McTavish', '2387 S. Fair Way', 'Madison', 'USA', 'Wisconsin', '6085552765'),
(6, 'Jean', 'Coleman', '105 N. Lake St.', 'Mundelein', 'USA', 'Illinois', '6085552654'),
(7, 'Jeff', 'Black', '1450 Oak Blvd.', 'Monona', 'USA', 'Lowa', '6085555387'),
(8, 'Maria', 'Escobito', '345 Maple St.', 'Madison', 'USA', 'Tennesee', '6085557683'),
(9, 'David', 'Schroeder', '2749 Blackhawk Trail', 'Madison', 'USA', 'Wisconsin', '6085559435'),
(10, 'Carlos', 'Estaban', '2335 Independence La.', 'Waunakee', 'USA', 'Wisconsin', '6085555487');

--
-- Volcado de datos para la tabla `pets`
--

INSERT IGNORE INTO `pets` (`id`, `name`, `birth_date`, `type_id`, `owner_id`) VALUES
(1, 'Leo', '2000-09-07', 1, 1),
(2, 'Basil', '2002-08-06', 6, 2),
(3, 'Rosy', '2001-04-17', 2, 3),
(4, 'Jewel', '2000-03-07', 2, 3),
(5, 'Iggy', '2000-11-30', 3, 4),
(6, 'George', '2000-01-20', 4, 5),
(7, 'Samantha', '1995-09-04', 1, 6),
(8, 'Max', '1995-09-04', 1, 6),
(9, 'Lucky', '1999-08-06', 5, 7),
(10, 'Mulligan', '1997-02-24', 2, 8),
(11, 'Freddy', '2000-03-09', 5, 9),
(12, 'Lucky', '2000-06-24', 2, 10),
(13, 'Sly', '2002-06-08', 1, 10);

--
-- Volcado de datos para la tabla `producto`
--

INSERT IGNORE INTO `producto` (`Id`, `nombre`, `descripcion`, `numero_serie`, `cantidad`, `precio`, `file`) VALUES
(1, 'Gasas', 'Material de curacion para las mascotas.', '12x23bqw2', 12, 34, NULL);

--
-- Volcado de datos para la tabla `specialties`
--

INSERT IGNORE INTO `specialties` (`id`, `name`) VALUES
(3, 'dentistry'),
(1, 'radiology'),
(2, 'surgery');

--
-- Volcado de datos para la tabla `types`
--

INSERT IGNORE INTO `types` (`id`, `name`) VALUES
(5, 'bird'),
(1, 'cat'),
(2, 'dog'),
(6, 'hamster'),
(3, 'lizard'),
(4, 'snake');

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT IGNORE INTO `usuarios` (`idusuarios`, `username`, `password`, `email`, `enabled`, `codigopostal`) VALUES
(1, 'david', '$2a$10$uxUu14ZltBy/Y9K9ofNt3.hb5IJtUTyxZCOabIqRerEXGm9Ku5agy', 'playeando.97@gmail.com', 1, '29100'),
(2, 'admin', '$2a$10$uxUu14ZltBy/Y9K9ofNt3.hb5IJtUTyxZCOabIqRerEXGm9Ku5agy', 'admin@admin.com', 1, '29100');

--
-- Volcado de datos para la tabla `vets`
--

INSERT IGNORE INTO `vets` (`id`, `first_name`, `last_name`, `telephone`, `Schedule`, `specialty_id`) VALUES
(2, 'Helen', 'Leary', '961 445 74', 'Fin de semanas', '1'),
(3, 'Linda', 'Douglas', '961 121 23', 'De lunes a sabado', '3'),
(4, 'Rafael', 'Ortega', '961 456 65', 'Toda la semana', '3'),
(5, 'Henry', 'Stevens', '961 899 65', '7 dias y 24 horas', '2'),
(6, 'Sharon', 'Jenkins', '961 002 12', 'Lunes, miercoles y viernes', '1');

--
-- Volcado de datos para la tabla `vet_specialties`
--

INSERT IGNORE INTO `vet_specialties` (`vet_id`, `specialty_id`) VALUES
(2, 1),
(3, 2),
(3, 3),
(4, 2),
(5, 1);

--
-- Volcado de datos para la tabla `visits`
--

INSERT IGNORE INTO `visits` (`id`, `pet_id`, `visit_date`, `description`) VALUES
(1, 7, '2010-03-04', 'rabies shot'),
(2, 8, '2011-03-04', 'rabies shot'),
(3, 8, '2009-06-04', 'neutered'),
(4, 7, '2008-09-04', 'spayed');
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
