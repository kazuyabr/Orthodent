-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 27-10-2014 a las 17:49:07
-- Versión del servidor: 5.6.12-log
-- Versión de PHP: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de datos: `clinica`
--
CREATE DATABASE IF NOT EXISTS `clinica` DEFAULT CHARACTER SET latin1 COLLATE latin1_spanish_ci;
USE `clinica`;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ficha_evolucion`
--

CREATE TABLE IF NOT EXISTS `ficha_evolucion` (
  `id_plantratamiento` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_cita` date DEFAULT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_plantratamiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `horario`
--

CREATE TABLE IF NOT EXISTS `horario` (
  `id_horario` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) NOT NULL,
  `dia` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `hora_inicio` int(11) NOT NULL,
  `hora_fin` int(11) NOT NULL,
  PRIMARY KEY (`id_horario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id_horario`, `id_usuario`, `dia`, `hora_inicio`, `hora_fin`) VALUES
(1, 4, 'Lunes', 540, 1020),
(5, 8, 'Lunes', 600, 595),
(6, 8, 'Miercoles', 660, 1140);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE IF NOT EXISTS `paciente` (
  `id_paciente` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_pat` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_mat` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `rut` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `edad` int(11) NOT NULL,
  `sexo` int(11) NOT NULL,
  `antecedente_medico` text COLLATE latin1_spanish_ci,
  `telefono` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `ciudad` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `comuna` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `direccion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_paciente`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=23 ;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id_paciente`, `nombre`, `apellido_pat`, `apellido_mat`, `rut`, `email`, `fecha_nacimiento`, `edad`, `sexo`, `antecedente_medico`, `telefono`, `ciudad`, `comuna`, `direccion`, `activo`, `created_at`, `update_at`) VALUES
(1, 'Mario', 'Sanhueza', 'Leiva', '17501528-0', '', '1989-11-18', 24, 2, 'nada', '89348184', ' ', ' ', '', 1, '2014-10-13 14:51:07', '0000-00-00 00:00:00'),
(2, 'Andres', 'Sanhueza', 'Leiva', '17501527-2-0', '', '2013-10-10', 1, 2, 'nada', '88888888', ' ', ' ', '', 1, '2014-10-13 14:51:44', '0000-00-00 00:00:00'),
(20, 'Mario', 'Uribe', 'Leiva', '1789292-k', '', '1990-12-14', 23, 2, 'nada', '78373636', ' ', ' ', '', 1, '2014-10-13 19:22:31', '0000-00-00 00:00:00'),
(21, 'Marianela Andrea', 'Iturriaga', 'Jimenez', '17157036-0', 'mari.ytu@gmail.com', '1989-07-19', 25, 1, '-', '56072407', 'Curico', 'Curico', 'Calle Diego Ramirez, 2146', 1, '2014-10-26 21:13:31', '0000-00-00 00:00:00'),
(22, 'Miriam del Carmen', 'Jimenez', 'Cerpa', '11368812-2', 'miriam.jimenez@hotmail.cl', '1969-03-21', 45, 1, '-', '99999999', 'Curico', 'Curico', 'Villa Galilea, pasaje Rio Tiber, 1916', 1, '2014-10-26 21:15:12', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE IF NOT EXISTS `pago` (
  `id_plantratamiento` int(11) NOT NULL AUTO_INCREMENT,
  `fecha` date DEFAULT NULL,
  `abono` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_plantratamiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_tratamiento`
--

CREATE TABLE IF NOT EXISTS `plan_tratamiento` (
  `id_plantratamiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) DEFAULT NULL,
  `id_tratamiento` int(11) DEFAULT NULL,
  `id_profesional` int(11) DEFAULT NULL,
  `fecha_creacion_´prestacion` date DEFAULT NULL,
  `fecha_modificacion_´prestacion` date DEFAULT NULL,
  `fecha_creacion_plan` date DEFAULT NULL,
  `fecha_modificacion_plan` date DEFAULT NULL,
  `costo_total` int(11) DEFAULT NULL,
  `totol_abonos` int(11) DEFAULT NULL,
  `avance` int(11) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_plantratamiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `presupuesto`
--

CREATE TABLE IF NOT EXISTS `presupuesto` (
  `id_presupuesto` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) DEFAULT NULL,
  `id_profesional` int(11) DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `costo_total` int(11) DEFAULT NULL,
  `cantidad_tratamiento` int(11) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_presupuesto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `presupuesto`
--

INSERT INTO `presupuesto` (`id_presupuesto`, `id_paciente`, `id_profesional`, `estado`, `costo_total`, `cantidad_tratamiento`, `activo`, `created_at`, `update_at`) VALUES
(1, 12, 12, 1, 2000, 3000, 1, '2014-10-27 17:07:47', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE IF NOT EXISTS `roles` (
  `id_rol` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_rol`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre`, `created_at`, `update_at`) VALUES
(1, 'ADMINISTRADOR', '2014-10-11 19:51:25', '0000-00-00 00:00:00'),
(2, 'ASISTENTE', '2014-10-11 19:52:15', '0000-00-00 00:00:00'),
(3, 'PROFESIONAL', '2014-10-11 19:52:24', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamiento`
--

CREATE TABLE IF NOT EXISTS `tratamiento` (
  `id_tratamiento` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `valor_colegio` int(11) DEFAULT NULL,
  `valor_clinica` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_tratamiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamiento_piezaplan`
--

CREATE TABLE IF NOT EXISTS `tratamiento_piezaplan` (
  `id_plantratamiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_tratamiento` int(11) DEFAULT NULL,
  `pieza` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `fecha_realizado` date DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_plantratamiento`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamiento_piezapresupuesto`
--

CREATE TABLE IF NOT EXISTS `tratamiento_piezapresupuesto` (
  `id_tratamiento` int(11) DEFAULT NULL,
  `id_presupuesto` int(11) DEFAULT NULL,
  `pieza` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `id_usuario` int(11) NOT NULL AUTO_INCREMENT,
  `id_rol` int(11) DEFAULT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_pat` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `apellido_mat` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `nombre_usuario` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `contrasena` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `email` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `telefono` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `especialidad` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `tiempo_cita` int(11) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=9 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `id_rol`, `nombre`, `apellido_pat`, `apellido_mat`, `nombre_usuario`, `contrasena`, `email`, `telefono`, `especialidad`, `tiempo_cita`, `activo`, `created_at`, `update_at`) VALUES
(1, 1, 'Admin', '', '', 'admin', '63a9f0ea7bb98050796b649e85481845', 'msanhuezal@hotmail.cl', '89348184', 'Cirujano', 15, 1, '2014-10-11 21:32:06', '0000-00-00 00:00:00'),
(2, 2, 'Marianela Andrea', 'Iturriaga', 'Jimenez', 'mari.ytu', 'angelito', 'mari.ytu@gmail.com', '56072407', 'null', 0, 1, '2014-10-19 04:12:08', '0000-00-00 00:00:00'),
(3, 3, 'Gonzalo', 'Sotomayor', '', 'gsotomayor', 'b8d8d98e92ba765212e4b9cebfea8e88', 'gsotomayor@orthodent.cl', '', 'Maxilofacial', 0, 1, '2014-10-19 04:28:33', '0000-00-00 00:00:00'),
(4, 3, 'Manuel Alejandro', 'Hoffhein', 'Alfaro', 'mahahein3', 'mahahein', 'mahahein3@gmail.com', '', 'Maxilofacial', 30, 1, '2014-10-20 01:53:02', '0000-00-00 00:00:00'),
(8, 3, 'profesional mario', 'apellido pat', 'apellido mat', 'msanhuezal', '827ccb0eea8a706c4c34a16891f84e7b', 'msanhuezal@hotmail.cl', '99889988', 'Maxilofacial', 15, 1, '2014-10-27 14:17:08', '0000-00-00 00:00:00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
