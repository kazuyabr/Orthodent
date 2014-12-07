-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 07-12-2014 a las 14:50:20
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
-- Estructura de tabla para la tabla `bitacora`
--

CREATE TABLE IF NOT EXISTS `bitacora` (
  `id_bitacora` int(11) NOT NULL AUTO_INCREMENT,
  `operacion` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `usuario` int(11) NOT NULL,
  `tabla` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `primary_key` int(11) NOT NULL,
  PRIMARY KEY (`id_bitacora`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria1`
--

CREATE TABLE IF NOT EXISTS `categoria1` (
  `id_categoria1` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_categoria1`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `categoria1`
--

INSERT INTO `categoria1` (`id_categoria1`, `nombre`, `activo`, `created_at`, `update_at`) VALUES
(1, 'Acciones de caracter general', 1, '2014-11-26 01:33:59', '0000-00-00 00:00:00'),
(2, 'Acciones de cirugia bucal y maxilofacial', 1, '2014-11-26 01:34:31', '0000-00-00 00:00:00'),
(3, 'lalalas', 1, '2014-12-02 21:01:37', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria2`
--

CREATE TABLE IF NOT EXISTS `categoria2` (
  `id_categoria2` int(11) NOT NULL AUTO_INCREMENT,
  `id_categoria1` int(11) DEFAULT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_categoria2`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `categoria2`
--

INSERT INTO `categoria2` (`id_categoria2`, `id_categoria1`, `nombre`, `activo`, `created_at`, `update_at`) VALUES
(1, 1, 'Acciones Generales', 1, '2014-11-26 01:35:21', '0000-00-00 00:00:00'),
(2, 1, 'ACCIONES DE PREVENCIÓN, INTERCEPCIÓN E HIGIENE', 1, '2014-11-26 01:35:37', '0000-00-00 00:00:00'),
(3, 1, 'lalalala nuevos', 1, '2014-12-02 21:02:21', '0000-00-00 00:00:00'),
(4, 3, 'nose', 1, '2014-12-02 21:02:33', '0000-00-00 00:00:00'),
(5, 2, 'asdad', 1, '2014-12-02 21:04:41', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cita`
--

CREATE TABLE IF NOT EXISTS `cita` (
  `id_cita` int(11) NOT NULL AUTO_INCREMENT,
  `id_profesional` int(11) NOT NULL,
  `id_paciente` int(11) NOT NULL,
  `semana` int(11) NOT NULL,
  `fecha` date NOT NULL,
  `hora_inicio` time NOT NULL,
  `duracion` int(11) NOT NULL,
  `comentario` longtext COLLATE latin1_spanish_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '2001-01-01 04:01:01',
  `confirmada` tinyint(1) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id_cita`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `cita`
--

INSERT INTO `cita` (`id_cita`, `id_profesional`, `id_paciente`, `semana`, `fecha`, `hora_inicio`, `duracion`, `comentario`, `created_at`, `update_at`, `confirmada`) VALUES
(1, 3, 1, 48, '2014-11-24', '09:00:00', 14, 'Sin Comentario', '2014-11-26 00:52:51', '2001-01-01 04:01:01', 0),
(2, 3, 1, 48, '2014-11-24', '09:15:00', 14, 'Sin Comentario', '2014-11-26 00:52:54', '2001-01-01 04:01:01', 0),
(3, 3, 1, 48, '2014-11-24', '09:30:00', 14, 'Sin Comentario', '2014-11-26 00:52:59', '2001-01-01 04:01:01', 0),
(6, 3, 1, 48, '2014-11-26', '10:00:00', 29, 'Sin Comentario', '2014-11-26 05:32:02', '2001-01-01 04:01:01', 0),
(8, 3, 1, 48, '2014-11-27', '09:00:00', 59, 'Sin Comentario', '2014-11-26 05:20:05', '2001-01-01 04:01:01', 0),
(9, 4, 1, 47, '2014-11-17', '09:15:00', 14, 'Sin Comentario', '2014-11-26 05:42:00', '2001-01-01 04:01:01', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ficha_evolucion`
--

CREATE TABLE IF NOT EXISTS `ficha_evolucion` (
  `id_fichaevolucion` int(11) NOT NULL AUTO_INCREMENT,
  `id_plantratamiento` int(11) DEFAULT NULL,
  `fecha_cita` date DEFAULT NULL,
  `descripcion` text COLLATE latin1_spanish_ci,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_fichaevolucion`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `ficha_evolucion`
--

INSERT INTO `ficha_evolucion` (`id_fichaevolucion`, `id_plantratamiento`, `fecha_cita`, `descripcion`, `created_at`, `update_at`) VALUES
(1, 5, '2014-11-12', 'asdadad', '2014-11-15 23:04:57', '0000-00-00 00:00:00'),
(2, 5, '2014-11-12', 'uhadjsg', '2014-11-17 23:16:43', '0000-00-00 00:00:00'),
(3, 5, '2014-11-17', 'hola', '2014-11-17 23:17:17', '0000-00-00 00:00:00'),
(4, 6, '2014-11-18', 'algo...', '2014-11-18 21:29:37', '0000-00-00 00:00:00');

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
-- Estructura de tabla para la tabla `laboratorio_piezapresupuesto`
--

CREATE TABLE IF NOT EXISTS `laboratorio_piezapresupuesto` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_presupuesto` int(11) NOT NULL,
  `pieza` int(11) NOT NULL,
  `prestacion` varchar(100) CHARACTER SET latin1 COLLATE latin1_spanish_ci NOT NULL,
  `valor` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

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
  `id_pago` int(11) NOT NULL AUTO_INCREMENT,
  `id_plantratamiento` int(11) DEFAULT NULL,
  `fecha` date DEFAULT NULL,
  `abono` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_pago`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `pago`
--

INSERT INTO `pago` (`id_pago`, `id_plantratamiento`, `fecha`, `abono`, `created_at`, `update_at`) VALUES
(1, 5, '2014-11-15', 2000, '2014-11-15 22:51:26', '0000-00-00 00:00:00'),
(2, 5, '2014-11-18', 1000, '2014-11-18 03:17:14', '0000-00-00 00:00:00'),
(3, 6, '2014-11-18', 1000, '2014-11-18 21:30:13', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `plan_tratamiento`
--

CREATE TABLE IF NOT EXISTS `plan_tratamiento` (
  `id_plantratamiento` int(11) NOT NULL AUTO_INCREMENT,
  `id_paciente` int(11) DEFAULT NULL,
  `id_profesional` int(11) DEFAULT NULL,
  `fecha_creacion_presupuesto` timestamp NULL DEFAULT NULL,
  `fecha_modificacion_presupuesto` timestamp NULL DEFAULT NULL,
  `costo_total` int(11) DEFAULT NULL,
  `total_abonos` int(11) DEFAULT NULL,
  `avance` int(11) DEFAULT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_plantratamiento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `plan_tratamiento`
--

INSERT INTO `plan_tratamiento` (`id_plantratamiento`, `id_paciente`, `id_profesional`, `fecha_creacion_presupuesto`, `fecha_modificacion_presupuesto`, `costo_total`, `total_abonos`, `avance`, `activo`, `created_at`, `update_at`) VALUES
(5, 21, 4, '2014-10-27 17:07:47', '2014-11-05 03:02:17', 4000, 3000, 66, 1, '2014-11-05 03:02:17', '2014-11-18 03:13:47'),
(6, 21, 8, '2014-10-28 06:48:28', '2014-11-18 21:27:38', 3000, 1000, 50, 1, '2014-11-18 21:27:38', '2014-11-18 21:29:13');

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
  `costo_lab` int(11) NOT NULL,
  `activo` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_presupuesto`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=5 ;

--
-- Volcado de datos para la tabla `presupuesto`
--

INSERT INTO `presupuesto` (`id_presupuesto`, `id_paciente`, `id_profesional`, `estado`, `costo_total`, `cantidad_tratamiento`, `costo_lab`, `activo`, `created_at`, `update_at`) VALUES
(3, 22, 8, 1, 19312, 2, 0, 1, '2014-10-30 14:30:53', '2014-12-03 01:57:03'),
(4, 21, 3, 1, 1000, 1, 0, 0, '2014-11-03 19:23:02', '2014-11-03 19:23:02');

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
  `id_categoria2` int(11) DEFAULT NULL,
  `nombre` varchar(100) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cantidad_uco` double DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_tratamiento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `tratamiento`
--

INSERT INTO `tratamiento` (`id_tratamiento`, `id_categoria2`, `nombre`, `cantidad_uco`, `activo`, `created_at`, `update_at`) VALUES
(1, 1, 'Examen inicial, Plan de Tratamiento y Presupuesto', 1.5, 1, '2014-11-26 03:18:51', '0000-00-00 00:00:00'),
(2, 1, 'Control y examen periódico de rigor', 1, 1, '2014-11-26 03:19:10', '0000-00-00 00:00:00'),
(3, 4, 'lalala', 1, 1, '2014-12-02 21:02:42', '0000-00-00 00:00:00'),
(4, 4, 'lalal', 1, 1, '2014-12-02 21:02:53', '0000-00-00 00:00:00'),
(5, 1, 'asd', 1, 1, '2014-12-02 21:03:07', '0000-00-00 00:00:00'),
(6, 5, 'asd', 2, 1, '2014-12-02 21:04:46', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamiento_piezaplan`
--

CREATE TABLE IF NOT EXISTS `tratamiento_piezaplan` (
  `id_tratamiento_piezaplan` int(11) NOT NULL AUTO_INCREMENT,
  `id_plantratamiento` int(11) DEFAULT NULL,
  `id_tratamiento` int(11) DEFAULT NULL,
  `pieza` int(11) DEFAULT NULL,
  `fecha_realizado` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_tratamiento_piezaplan`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `tratamiento_piezaplan`
--

INSERT INTO `tratamiento_piezaplan` (`id_tratamiento_piezaplan`, `id_plantratamiento`, `id_tratamiento`, `pieza`, `fecha_realizado`, `estado`, `created_at`, `update_at`) VALUES
(1, 5, 1, 4, '2014-11-18', 1, '2014-11-05 03:02:17', '0000-00-00 00:00:00'),
(2, 5, 2, 20, '2014-11-18', 1, '2014-11-05 03:02:17', '0000-00-00 00:00:00'),
(3, 5, 3, 5, '2014-11-17', 0, '2014-11-05 03:02:17', '0000-00-00 00:00:00'),
(4, 6, 3, 5, '2014-11-18', 1, '2014-11-18 21:27:38', '0000-00-00 00:00:00'),
(5, 6, 2, 45, NULL, 0, '2014-11-18 21:27:38', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamiento_piezapresupuesto`
--

CREATE TABLE IF NOT EXISTS `tratamiento_piezapresupuesto` (
  `id_tratamiento` int(11) DEFAULT NULL,
  `id_presupuesto` int(11) DEFAULT NULL,
  `pieza` int(11) DEFAULT NULL,
  `valor_colegio` int(11) NOT NULL,
  `valor_clinica` int(11) NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00'
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `tratamiento_piezapresupuesto`
--

INSERT INTO `tratamiento_piezapresupuesto` (`id_tratamiento`, `id_presupuesto`, `pieza`, `valor_colegio`, `valor_clinica`, `created_at`, `update_at`) VALUES
(3, 4, 5, 0, 0, '2014-11-03 19:23:03', '0000-00-00 00:00:00'),
(1, 3, 5, 23175, 11587, '2014-12-03 01:57:03', '0000-00-00 00:00:00'),
(2, 3, 7, 15450, 7725, '2014-12-03 01:57:03', '0000-00-00 00:00:00');

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
  `id_clinica` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=10 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `id_rol`, `nombre`, `apellido_pat`, `apellido_mat`, `nombre_usuario`, `contrasena`, `email`, `telefono`, `especialidad`, `tiempo_cita`, `activo`, `created_at`, `update_at`, `id_clinica`) VALUES
(1, 1, 'Admin', '', '', 'admin', '63a9f0ea7bb98050796b649e85481845', 'msanhuezal@hotmail.cl', '89348184', 'Cirujano', 15, 1, '2014-10-11 21:32:06', '0000-00-00 00:00:00', 1),
(2, 2, 'Marianela Andrea', 'Iturriaga', 'Jimenez', 'mari.ytu', 'b67912b31fcba0a3173f45f2058226fe', 'mari.ytu@gmail.com', '56072407', 'null', 0, 1, '2014-10-19 04:12:08', '0000-00-00 00:00:00', NULL),
(3, 3, 'Gonzalo', 'Sotomayor', '', 'gsotomayor', 'b8d8d98e92ba765212e4b9cebfea8e88', 'gsotomayor@orthodent.cl', '', 'Maxilofacial', 0, 1, '2014-10-19 04:28:33', '0000-00-00 00:00:00', NULL),
(4, 3, 'Manuel Alejandro', 'Hoffhein', 'Alfaro', 'mahahein3', '826497e60d6f1646760510f55d4360dd', 'mahahein3@gmail.com', '', 'Maxilofacial', 30, 1, '2014-10-20 01:53:02', '0000-00-00 00:00:00', NULL),
(8, 3, 'Mario', 'Sanhueza', 'Leiva', 'msanhuezal', '26c2b2f13b7e8622fe1734324c65f5fb', 'msanhuezal@hotmail.cl', '99889988', 'Maxilofacial', 15, 1, '2014-10-27 14:17:08', '0000-00-00 00:00:00', NULL),
(9, 2, 'Felipe', 'Astroza', '', 'fastroza', '827ccb0eea8a706c4c34a16891f84e7b', 'fastroza@gmail.com', '', NULL, NULL, 1, '2014-11-18 21:18:24', '0000-00-00 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `valor_uco`
--

CREATE TABLE IF NOT EXISTS `valor_uco` (
  `id_valor_uco` int(11) NOT NULL AUTO_INCREMENT,
  `valor` int(11) DEFAULT NULL,
  `porcentaje` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_valor_uco`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=2 ;

--
-- Volcado de datos para la tabla `valor_uco`
--

INSERT INTO `valor_uco` (`id_valor_uco`, `valor`, `porcentaje`, `created_at`, `update_at`) VALUES
(1, 15450, 50, '2014-11-25 23:46:19', '0000-00-00 00:00:00');

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
