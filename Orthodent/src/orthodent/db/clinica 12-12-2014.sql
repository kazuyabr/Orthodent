-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Servidor: localhost
-- Tiempo de generación: 12-12-2014 a las 04:29:20
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
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_bitacora`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=3 ;

--
-- Volcado de datos para la tabla `bitacora`
--

INSERT INTO `bitacora` (`id_bitacora`, `operacion`, `usuario`, `tabla`, `primary_key`, `created_at`, `update_at`) VALUES
(1, 'AGREGAR', 1, 'FICHA EVOLUCION', 2, '2014-12-07 23:02:52', '0000-00-00 00:00:00'),
(2, 'AGREGAR', 1, 'FICHA EVOLUCION', 1, '2014-12-07 23:53:04', '0000-00-00 00:00:00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=15 ;

--
-- Volcado de datos para la tabla `categoria1`
--

INSERT INTO `categoria1` (`id_categoria1`, `nombre`, `activo`, `created_at`, `update_at`) VALUES
(1, 'ACCIONES DE CARÁCTER GENERAL', 1, '2014-12-12 01:12:38', '0000-00-00 00:00:00'),
(2, 'ACCIONES DE CIRUGÍA BUCAL Y MÁXILOFACIAL', 1, '2014-12-12 01:13:18', '0000-00-00 00:00:00'),
(3, 'ACCIONES DE ENDODONCIA O TRATAMIENTO DE CONDUCTO', 1, '2014-12-12 01:13:39', '0000-00-00 00:00:00'),
(4, 'ACCIONES DE ODONTOPEDIATRÍA', 1, '2014-12-12 01:13:53', '0000-00-00 00:00:00'),
(5, 'ACCIONES DE OPERATORIA ODONTÓLOGO GENERAL', 1, '2014-12-12 01:14:08', '0000-00-00 00:00:00'),
(6, 'ACCIONES DE ORTODONCIA Y ORTOPEDIA DENTO MAXILO FACIAL', 1, '2014-12-12 01:14:21', '0000-00-00 00:00:00'),
(7, 'ACCIONES DE PATOLOGÍA BUCO MAXILO FACIAL', 1, '2014-12-12 01:14:34', '0000-00-00 00:00:00'),
(8, 'ACCIONES DE PERIODONCIA ESPECIALISTA', 1, '2014-12-12 01:14:47', '0000-00-00 00:00:00'),
(9, 'ACCIONES DEL ÁREA PRÓTESIS Y REHABILITACIÓN ORAL', 1, '2014-12-12 01:14:59', '0000-00-00 00:00:00'),
(10, 'ACCIONES DE IMPLANTOLOGÍA', 1, '2014-12-12 01:15:12', '0000-00-00 00:00:00'),
(11, 'ACCIONES TRASTORNOS TEMPORO MANDIBULARES Y', 1, '2014-12-12 01:15:31', '0000-00-00 00:00:00'),
(12, 'ACCIONES POR ESPECIALISTASEN OPERATORIA', 1, '2014-12-12 03:00:57', '0000-00-00 00:00:00'),
(14, 'ACCIONES DE REHABILITACION ORAL CON PROTESIS REMOVIBLE', 1, '2014-12-12 04:09:10', '0000-00-00 00:00:00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=61 ;

--
-- Volcado de datos para la tabla `categoria2`
--

INSERT INTO `categoria2` (`id_categoria2`, `id_categoria1`, `nombre`, `activo`, `created_at`, `update_at`) VALUES
(1, 1, 'Acciones Generales', 1, '2014-12-12 01:25:37', '0000-00-00 00:00:00'),
(2, 1, 'ACCIONES DE PREVENCIÓN, INTERCEPCIÓN E HIGIENE', 1, '2014-12-12 01:25:43', '0000-00-00 00:00:00'),
(3, 2, 'ESTANDAR', 1, '2014-12-12 01:26:03', '0000-00-00 00:00:00'),
(4, 2, 'CIRUGÍA RECONSTRUCTIVA, INJERTOS E IMPLANTES', 1, '2014-12-12 01:26:13', '0000-00-00 00:00:00'),
(5, 2, 'ALGIAS FACIALES', 1, '2014-12-12 01:27:07', '0000-00-00 00:00:00'),
(6, 2, 'ARTICULACIÓN TEMPOROMANDIBULAR', 1, '2014-12-12 01:27:15', '0000-00-00 00:00:00'),
(7, 2, 'CIRUGIA COMPLEMENTARIA A OTRAS ESPECIALIDADES', 1, '2014-12-12 01:27:25', '0000-00-00 00:00:00'),
(8, 2, 'CIRUGIA ORTOGNÁTICA', 1, '2014-12-12 01:27:30', '0000-00-00 00:00:00'),
(9, 2, 'CIRUGÍA ORTOPÉDICA', 1, '2014-12-12 01:27:45', '0000-00-00 00:00:00'),
(10, 2, 'CIRUGÍA DE LAS FISURAS LABIO ALVÉOLO PALATINAS', 1, '2014-12-12 01:27:55', '0000-00-00 00:00:00'),
(11, 2, 'CIRUGIA CRANEOFACIAL', 1, '2014-12-12 01:28:03', '0000-00-00 00:00:00'),
(12, 2, 'TRATAMIENTO QUIRÚRGICO DE QUISTES O TUMORES Y GLANDULAS SALIVALES', 1, '2014-12-12 01:28:24', '0000-00-00 00:00:00'),
(13, 2, 'TRATAMIENTO MÉDICO QUIRÚRGICO DE LA INFECCIÓN', 1, '2014-12-12 01:28:37', '0000-00-00 00:00:00'),
(14, 2, 'TRAUMATOLOGÍA', 1, '2014-12-12 01:28:48', '0000-00-00 00:00:00'),
(15, 2, 'TRAUMATOLOGÍA', 0, '2014-12-12 01:28:53', '0000-00-00 00:00:00'),
(16, 3, 'Cirujano Dentista General', 1, '2014-12-12 02:22:19', '0000-00-00 00:00:00'),
(17, 3, 'ACCIONES DE ENDODONCIA ESPECIALISTAS', 1, '2014-12-12 02:24:05', '0000-00-00 00:00:00'),
(18, 3, 'RETRATAMIENTO', 1, '2014-12-12 02:26:39', '0000-00-00 00:00:00'),
(19, 3, 'ACCIONES DE ENDODONCIA ESPECIALISTAS', 1, '2014-12-12 02:27:34', '0000-00-00 00:00:00'),
(20, 4, 'Acciones generales de prevención e higiene', 1, '2014-12-12 02:35:13', '0000-00-00 00:00:00'),
(21, 4, 'CIRUJANO DENTISTA GENERAL', 1, '2014-12-12 02:35:20', '0000-00-00 00:00:00'),
(22, 4, 'OBTURACIONES DE RESINA DE FOTOCURADO', 1, '2014-12-12 02:41:31', '0000-00-00 00:00:00'),
(23, 4, 'OBTURACIONES DE AMALGAMA', 1, '2014-12-12 02:43:24', '0000-00-00 00:00:00'),
(24, 4, 'ENDODONCIA', 1, '2014-12-12 02:43:48', '0000-00-00 00:00:00'),
(25, 4, 'TRAUMATOLOGÍA- CIRUGÍA', 1, '2014-12-12 02:44:54', '0000-00-00 00:00:00'),
(26, 4, 'ACCIONES DE ODONTOPEDIATRÍA ESPECIALISTA', 1, '2014-12-12 02:45:44', '0000-00-00 00:00:00'),
(27, 4, 'OPERATORIA: OBTURACIONES COMPLEJAS ', 1, '2014-12-12 02:46:31', '0000-00-00 00:00:00'),
(28, 4, 'ENDODONCIA', 1, '2014-12-12 02:50:09', '0000-00-00 00:00:00'),
(29, 4, 'ORTODONCIA PREVENTIVA E INTERCEPTIVA', 1, '2014-12-12 02:51:41', '0000-00-00 00:00:00'),
(30, 4, 'CIRUGÍA', 1, '2014-12-12 02:53:47', '0000-00-00 00:00:00'),
(31, 5, 'ESTANDAR', 1, '2014-12-12 02:54:50', '0000-00-00 00:00:00'),
(32, 5, 'RESTAURACIONES DE RESINA COMPUESTA', 1, '2014-12-12 02:55:37', '0000-00-00 00:00:00'),
(33, 5, 'RESTAURACIONES DE AMALGAMA', 1, '2014-12-12 02:57:10', '0000-00-00 00:00:00'),
(34, 5, 'INCRUSTACIONES', 1, '2014-12-12 02:58:09', '0000-00-00 00:00:00'),
(35, 5, 'OTRAS ACCIONES', 1, '2014-12-12 02:59:12', '0000-00-00 00:00:00'),
(36, 12, 'INCRUSTACIONES ESTÉTICAS', 1, '2014-12-12 03:01:03', '0000-00-00 00:00:00'),
(37, 12, 'OTRAS ACCIONES ESTÉTICAS', 1, '2014-12-12 03:01:54', '0000-00-00 00:00:00'),
(38, 6, 'Procedimientos diagnósticos y de estudio', 1, '2014-12-12 03:07:53', '0000-00-00 00:00:00'),
(39, 6, 'Tratamiento Ortodóncico Honorarios 1 Semestre', 1, '2014-12-12 03:07:59', '0000-00-00 00:00:00'),
(40, 6, 'TRATAMIENTO ORTODÓNTICO HONORARIOS POR 1 AÑO', 1, '2014-12-12 03:12:47', '0000-00-00 00:00:00'),
(41, 6, 'Aparatos Ortodónticos, costo por cada arco y aditamentos', 1, '2014-12-12 03:14:33', '0000-00-00 00:00:00'),
(42, 6, 'Aparatos Ortodónticos, costo por cada arco y aditamentos', 1, '2014-12-12 03:19:35', '0000-00-00 00:00:00'),
(43, 7, 'ESTANDAR', 1, '2014-12-12 03:21:11', '0000-00-00 00:00:00'),
(44, 8, 'ESTANDAR', 1, '2014-12-12 03:23:03', '0000-00-00 00:00:00'),
(45, 8, 'TRATAMIENTO DE LA INFECCIÓN PERIODONTAL', 1, '2014-12-12 03:23:53', '0000-00-00 00:00:00'),
(46, 8, 'PERIODONTITIS DEL ADULTO', 1, '2014-12-12 03:24:15', '0000-00-00 00:00:00'),
(47, 8, 'CIRUGÍAS PERIODONTALES', 1, '2014-12-12 03:25:51', '0000-00-00 00:00:00'),
(48, 8, 'ACCIONES DE PREVENCIÓN O MANTENCIÓN PERIODONTAL', 1, '2014-12-12 03:30:31', '0000-00-00 00:00:00'),
(49, 9, 'Acciones de Rehabilitación oral con Prótesis fija nivel Odontólogo general', 1, '2014-12-12 03:32:12', '0000-00-00 00:00:00'),
(50, 9, 'NIVEL ESPECIALISTA ', 1, '2014-12-12 03:32:18', '0000-00-00 00:00:00'),
(51, 9, 'NIVEL ODONTÓLOGO GENERAL', 1, '2014-12-12 03:39:12', '0000-00-00 00:00:00'),
(52, 9, 'ACCIONES NIVEL ESPECIALISTA ', 1, '2014-12-12 03:41:11', '0000-00-00 00:00:00'),
(53, 9, 'ACCIONES DE REHABILITACIÓN SOBRE IMPLANTES', 1, '2014-12-12 03:42:46', '0000-00-00 00:00:00'),
(54, 14, 'ACCIONES ODONTÓLOGO GENERAL ', 1, '2014-12-12 03:58:43', '0000-00-00 00:00:00'),
(55, 14, 'Acciones de Rehabilitación Oral con prótesis removible', 1, '2014-12-12 04:04:28', '0000-00-00 00:00:00'),
(56, 14, 'ACCIONES NIVEL ESPECIALISTA ', 1, '2014-12-12 04:10:08', '0000-00-00 00:00:00'),
(57, 10, 'Procedimientos, diagnósticos y de estudio ', 1, '2014-12-12 04:13:15', '0000-00-00 00:00:00'),
(58, 10, 'CIRUGÍA DE IMPLANTES', 1, '2014-12-12 04:15:18', '0000-00-00 00:00:00'),
(59, 10, 'REHABILITACIÓN SOBRE IMPLANTES', 1, '2014-12-12 04:19:39', '0000-00-00 00:00:00'),
(60, 11, 'DOLOR OROFACIAL', 1, '2014-12-12 04:22:33', '0000-00-00 00:00:00');

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
(6, 3, 1, 48, '2014-11-26', '10:00:00', 29, 'Sin Comentario', '2014-12-10 13:04:02', '2001-01-01 04:01:01', 1),
(8, 3, 1, 48, '2014-11-27', '09:00:00', 59, 'Sin Comentario', '2014-11-26 05:20:05', '2001-01-01 04:01:01', 0),
(9, 4, 1, 47, '2014-11-17', '09:15:00', 14, 'Sin Comentario', '2014-11-26 05:42:00', '2001-01-01 04:01:01', 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clinica`
--

CREATE TABLE IF NOT EXISTS `clinica` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nombre` varchar(100) COLLATE latin1_spanish_ci NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  `activo` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `clinica`
--

INSERT INTO `clinica` (`id`, `nombre`, `created_at`, `update_at`, `activo`) VALUES
(1, 'Clinica Uno', '2014-12-07 23:03:59', '0000-00-00 00:00:00', 1),
(2, 'Clinica Dos', '2014-12-07 23:03:59', '0000-00-00 00:00:00', 1),
(3, 'Clinica Tres', '2014-12-07 23:03:59', '0000-00-00 00:00:00', 0),
(4, 'Clinica Cuatro asd', '2014-12-10 00:38:35', '0000-00-00 00:00:00', 0),
(5, 'Clinica Cuatro', '2014-12-10 00:47:49', '0000-00-00 00:00:00', 1),
(6, 'Clinica Cinco', '2014-12-10 00:56:00', '0000-00-00 00:00:00', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `comunas`
--

CREATE TABLE IF NOT EXISTS `comunas` (
  `codigoInterno` int(11) unsigned NOT NULL DEFAULT '0',
  `nombre` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `padre` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `comunas`
--

INSERT INTO `comunas` (`codigoInterno`, `nombre`, `padre`) VALUES
(346, 'ALTO HOSPICIO', 1),
(296, 'CAMINA', 1),
(297, 'COLCHANE', 1),
(3, 'HUARA', 1),
(2, 'IQUIQUE', 1),
(4, 'PICA', 1),
(5, 'POZO ALMONTE', 1),
(7, 'ANTOFAGASTA', 2),
(10, 'CALAMA', 2),
(298, 'MARIA ELENA', 2),
(8, 'MEJILLONES', 2),
(300, 'OLLAGÜE', 2),
(301, 'SAN PEDRO DE ATACAMA', 2),
(299, 'SIERRA GORDA', 2),
(9, 'TALTAL', 2),
(6, 'TOCOPILLA', 2),
(302, 'ALTO DEL CARMEN', 3),
(14, 'CALDERA', 3),
(11, 'CHAÑARAL', 3),
(13, 'COPIAPO', 3),
(12, 'DIEGO DE ALMAGRO', 3),
(17, 'FREIRINA', 3),
(18, 'HUASCO', 3),
(15, 'TIERRA AMARILLA', 3),
(16, 'VALLENAR', 3),
(22, 'ANDACOLLO', 4),
(31, 'CANELA', 4),
(29, 'COMBARBALA', 4),
(21, 'COQUIMBO', 4),
(30, 'ILLAPEL', 4),
(20, 'LA HIGUERA', 4),
(19, 'LA SERENA', 4),
(33, 'LOS VILOS', 4),
(26, 'MONTE PATRIA', 4),
(25, 'OVALLE', 4),
(24, 'PAIHUANO', 4),
(27, 'PUNITAQUI', 4),
(28, 'RIO HURTADO', 4),
(32, 'SALAMANCA', 4),
(23, 'VICUÑA', 4),
(44, 'ALGARROBO', 5),
(56, 'CABILDO', 5),
(67, 'CALLE LARGA', 5),
(46, 'CARTAGENA', 5),
(40, 'CASABLANCA', 5),
(63, 'CATEMU', 5),
(340, 'CONCON', 5),
(45, 'EL QUISCO', 5),
(47, 'EL TABO', 5),
(51, 'HIJUELAS', 5),
(41, 'ISLA DE PASCUA', 5),
(321, 'JUAN FERNANDEZ', 5),
(50, 'LA CALERA', 5),
(49, 'LA CRUZ', 5),
(59, 'LA LIGUA', 5),
(53, 'LIMACHE', 5),
(65, 'LLAY LLAY', 5),
(66, 'LOS ANDES', 5),
(52, 'NOGALES', 5),
(54, 'OLMUE', 5),
(62, 'PANQUEHUE', 5),
(57, 'PAPUDO', 5),
(55, 'PETORCA', 5),
(36, 'PUCHUNCAVI', 5),
(61, 'PUTAENDO', 5),
(48, 'QUILLOTA', 5),
(38, 'QUILPUE', 5),
(35, 'QUINTERO', 5),
(68, 'RINCONADA', 5),
(42, 'SAN ANTONIO', 5),
(69, 'SAN ESTEBAN', 5),
(60, 'SAN FELIPE', 5),
(64, 'SANTA MARIA', 5),
(43, 'SANTO DOMINGO', 5),
(34, 'VALPARAISO', 5),
(39, 'VILLA ALEMANA', 5),
(37, 'VIÑA DEL MAR', 5),
(58, 'ZAPALLAR', 5),
(132, 'CHEPICA', 6),
(125, 'CHIMBARONGO', 6),
(110, 'CODEGUA', 6),
(114, 'COINCO', 6),
(113, 'COLTAUCO', 6),
(112, 'DOÑIHUE', 6),
(107, 'GRANEROS', 6),
(139, 'LA ESTRELLA', 6),
(116, 'LAS CABRAS', 6),
(136, 'LITUECHE', 6),
(129, 'LOLOL', 6),
(106, 'MACHALI', 6),
(122, 'MALLOA', 6),
(134, 'MARCHIGUE', 6),
(126, 'NANCAGUA', 6),
(138, 'NAVIDAD', 6),
(120, 'OLIVAR', 6),
(130, 'PALMILLA', 6),
(133, 'PAREDONES', 6),
(131, 'PERALILLO', 6),
(115, 'PEUMO', 6),
(118, 'PICHIDEGUA', 6),
(137, 'PICHILEMU', 6),
(127, 'PLACILLA', 6),
(135, 'PUMANQUE', 6),
(123, 'QUINTA DE TILCOCO', 6),
(105, 'RANCAGUA', 6),
(121, 'RENGO', 6),
(119, 'REQUINOA', 6),
(124, 'SAN FERNANDO', 6),
(111, 'SAN FRANCISCO DE MOSTAZAL', 6),
(117, 'SAN VICENTE', 6),
(128, 'SANTA CRUZ', 6),
(166, 'CAUQUENES', 7),
(167, 'CHANCO', 7),
(161, 'COLBUN', 7),
(157, 'CONSTITUCION', 7),
(155, 'CUREPTO', 7),
(140, 'CURICO', 7),
(158, 'EMPEDRADO', 7),
(144, 'HUALAÑE', 7),
(145, 'LICANTEN', 7),
(159, 'LINARES', 7),
(162, 'LONGAVI', 7),
(154, 'MAULE', 7),
(147, 'MOLINA', 7),
(164, 'PARRAL', 7),
(152, 'PELARCO', 7),
(320, 'PELLUHUE', 7),
(153, 'PENCAHUE', 7),
(143, 'RAUCO', 7),
(165, 'RETIRO', 7),
(149, 'RIO CLARO', 7),
(141, 'ROMERAL', 7),
(148, 'SAGRADA FAMILIA', 7),
(151, 'SAN CLEMENTE', 7),
(156, 'SAN JAVIER', 7),
(341, 'SAN RAFAEL', 7),
(150, 'TALCA', 7),
(142, 'TENO', 7),
(146, 'VICHUQUEN', 7),
(163, 'VILLA ALEGRE', 7),
(160, 'YERBAS BUENAS', 7),
(303, 'ANTUCO', 8),
(198, 'ARAUCO', 8),
(180, 'BULNES', 8),
(208, 'CABRERO', 8),
(201, 'CAÑETE', 8),
(344, 'CHIGUAYANTE', 8),
(168, 'CHILLAN', 8),
(342, 'CHILLAN VIEJO', 8),
(175, 'COBQUECURA', 8),
(186, 'COELEMU', 8),
(170, 'COIHUECO', 8),
(188, 'CONCEPCION', 8),
(202, 'CONTULMO', 8),
(194, 'CORONEL', 8),
(197, 'CURANILAHUE', 8),
(185, 'EL CARMEN', 8),
(193, 'FLORIDA', 8),
(192, 'HUALQUI', 8),
(210, 'LAJA', 8),
(199, 'LEBU', 8),
(200, 'LOS ALAMOS', 8),
(204, 'LOS ANGELES', 8),
(195, 'LOTA', 8),
(214, 'MULCHEN', 8),
(212, 'NACIMIENTO', 8),
(213, 'NEGRETE', 8),
(174, 'NINHUE', 8),
(184, 'PEMUCO', 8),
(191, 'PENCO', 8),
(169, 'PINTO', 8),
(171, 'PORTEZUELO', 8),
(215, 'QUILACO', 8),
(206, 'QUILLECO', 8),
(182, 'QUILLON', 8),
(172, 'QUIRIHUE', 8),
(187, 'RANQUIL', 8),
(176, 'SAN CARLOS', 8),
(178, 'SAN FABIAN', 8),
(177, 'SAN GREGORIO DE ÑIQUEN', 8),
(181, 'SAN IGNACIO', 8),
(179, 'SAN NICOLAS', 8),
(343, 'SAN PEDRO DE LA PAZ', 8),
(211, 'SAN ROSENDO', 8),
(205, 'SANTA BARBARA', 8),
(196, 'SANTA JUANA', 8),
(189, 'TALCAHUANO', 8),
(203, 'TIRUA', 8),
(190, 'TOME', 8),
(173, 'TREHUACO', 8),
(209, 'TUCAPEL', 8),
(207, 'YUMBEL', 8),
(183, 'YUNGAY', 8),
(216, 'ANGOL', 9),
(235, 'CARAHUE', 9),
(220, 'COLLIPULLI', 9),
(230, 'CUNCO', 9),
(225, 'CURACAUTIN', 9),
(305, 'CURARREHUE', 9),
(221, 'ERCILLA', 9),
(229, 'FREIRE', 9),
(232, 'GALVARINO', 9),
(238, 'GORBEA', 9),
(231, 'LAUTARO', 9),
(240, 'LONCOCHE', 9),
(226, 'LONQUIMAY', 9),
(218, 'LOS SAUCES', 9),
(223, 'LUMACO', 9),
(304, 'MELIPEUCO', 9),
(234, 'NUEVA IMPERIAL', 9),
(345, 'PADRE LAS CASAS', 9),
(233, 'PERQUENCO', 9),
(237, 'PITRUFQUEN', 9),
(242, 'PUCON', 9),
(236, 'PUERTO SAAVEDRA', 9),
(217, 'PUREN', 9),
(219, 'RENAICO', 9),
(227, 'TEMUCO', 9),
(306, 'TEODORO SCHMIDT', 9),
(239, 'TOLTEN', 9),
(222, 'TRAIGUEN', 9),
(224, 'VICTORIA', 9),
(228, 'VILCUN', 9),
(241, 'VILLARRICA', 9),
(277, 'ANCUD', 10),
(265, 'CALBUCO', 10),
(270, 'CASTRO', 10),
(280, 'CHAITEN', 10),
(271, 'CHONCHI', 10),
(262, 'COCHAMO', 10),
(276, 'CURACO DE VELEZ', 10),
(279, 'DALCAHUE', 10),
(268, 'FRESIA', 10),
(269, 'FRUTILLAR', 10),
(281, 'FUTALEUFU', 10),
(308, 'HUALAIHUE', 10),
(267, 'LLANQUIHUE', 10),
(264, 'LOS MUERMOS', 10),
(263, 'MAULLIN', 10),
(255, 'OSORNO', 10),
(282, 'PALENA', 10),
(261, 'PUERTO MONTT', 10),
(258, 'PUERTO OCTAY', 10),
(266, 'PUERTO VARAS', 10),
(274, 'PUQUELDON', 10),
(260, 'PURRANQUE', 10),
(256, 'PUYEHUE', 10),
(272, 'QUEILEN', 10),
(273, 'QUELLON', 10),
(278, 'QUEMCHI', 10),
(275, 'QUINCHAO', 10),
(259, 'RIO NEGRO', 10),
(307, 'SAN JUAN DE LA COSTA', 10),
(257, 'SAN PABLO', 10),
(285, 'AYSEN', 11),
(287, 'CHILE CHICO', 11),
(286, 'CISNES', 11),
(289, 'COCHRANE', 11),
(284, 'COYHAIQUE', 11),
(309, 'GUAITECAS', 11),
(312, 'LAGO VERDE', 11),
(310, 'O´HIGGINS', 11),
(288, 'RIO IBAÑEZ', 11),
(311, 'TORTEL', 11),
(316, 'LAGUNA BLANCA', 12),
(319, 'NAVARINO', 12),
(292, 'PORVENIR', 12),
(317, 'PRIMAVERA', 12),
(291, 'PUERTO NATALES', 12),
(290, 'PUNTA ARENAS', 12),
(314, 'RIO VERDE', 12),
(315, 'SAN GREGORIO', 12),
(318, 'TIMAUKEL', 12),
(313, 'TORRES DEL PAINE', 12),
(109, 'ALHUE', 13),
(103, 'BUIN', 13),
(99, 'CALERA DE TANGO', 13),
(333, 'CERRILLOS', 13),
(324, 'CERRO NAVIA', 13),
(76, 'COLINA', 13),
(75, 'CONCHALI', 13),
(83, 'CURACAVI', 13),
(338, 'EL BOSQUE', 13),
(89, 'EL MONTE', 13),
(328, 'ESTACION CENTRAL', 13),
(334, 'HUECHURABA', 13),
(330, 'INDEPENDENCIA', 13),
(87, 'ISLA DE MAIPO', 13),
(96, 'LA CISTERNA', 13),
(93, 'LA FLORIDA', 13),
(97, 'LA GRANJA', 13),
(327, 'LA PINTANA', 13),
(92, 'LA REINA', 13),
(78, 'LAMPA', 13),
(71, 'LAS CONDES', 13),
(332, 'LO BARNECHEA', 13),
(337, 'LO ESPEJO', 13),
(325, 'LO PRADO', 13),
(323, 'MACUL', 13),
(94, 'MAIPU', 13),
(90, 'MARIA PINTO', 13),
(88, 'MELIPILLA', 13),
(91, 'ÑUÑOA', 13),
(339, 'PADRE HURTADO', 13),
(104, 'PAINE', 13),
(336, 'PEDRO AGUIRRE CERDA', 13),
(85, 'PEÑAFLOR', 13),
(322, 'PEÑALOLEN', 13),
(101, 'PIRQUE', 13),
(72, 'PROVIDENCIA', 13),
(82, 'PUDAHUEL', 13),
(100, 'PUENTE ALTO', 13),
(79, 'QUILICURA', 13),
(81, 'QUINTA NORMAL', 13),
(329, 'RECOLETA', 13),
(77, 'RENCA', 13),
(98, 'SAN BERNARDO', 13),
(335, 'SAN JOAQUIN', 13),
(102, 'SAN JOSE DE MAIPO', 13),
(95, 'SAN MIGUEL', 13),
(108, 'SAN PEDRO', 13),
(326, 'SAN RAMON', 13),
(70, 'SANTIAGO CENTRO', 13),
(73, 'SANTIAGO OESTE', 13),
(84, 'SANTIAGO SUR', 13),
(86, 'TALAGANTE', 13),
(80, 'TIL-TIL', 13),
(331, 'VITACURA', 13),
(244, 'CORRAL', 14),
(248, 'FUTRONO', 14),
(251, 'LA UNION', 14),
(254, 'LAGO RANCO', 14),
(249, 'LANCO', 14),
(247, 'LOS LAGOS', 14),
(246, 'MAFIL', 14),
(245, 'MARIQUINA', 14),
(243, 'VALDIVIA', 14),
(250, 'PANGUIPULLI', 14),
(252, 'PAILLACO', 14),
(253, 'RIO BUENO', 14),
(1, 'ARICA', 15),
(295, 'CAMARONES', 15),
(293, 'GENERAL LAGOS', 15),
(294, 'PUTRE', 15);

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `ficha_evolucion`
--

INSERT INTO `ficha_evolucion` (`id_fichaevolucion`, `id_plantratamiento`, `fecha_cita`, `descripcion`, `created_at`, `update_at`) VALUES
(1, 5, '2014-11-12', 'asdadad', '2014-11-15 23:04:57', '0000-00-00 00:00:00'),
(2, 5, '2014-11-12', 'uhadjsg', '2014-11-17 23:16:43', '0000-00-00 00:00:00'),
(3, 5, '2014-11-17', 'hola', '2014-11-17 23:17:17', '0000-00-00 00:00:00'),
(4, 6, '2014-11-18', 'algo...', '2014-11-18 21:29:37', '0000-00-00 00:00:00'),
(5, 5, '2014-12-14', 'probando', '2014-12-07 23:53:04', '0000-00-00 00:00:00'),
(6, 5, '2014-12-14', 'addssadds', '2014-12-08 00:04:56', '0000-00-00 00:00:00');

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
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_horario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `horario`
--

INSERT INTO `horario` (`id_horario`, `id_usuario`, `dia`, `hora_inicio`, `hora_fin`, `created_at`, `update_at`) VALUES
(1, 4, 'Lunes', 540, 1020, '2014-12-07 23:05:05', '0000-00-00 00:00:00'),
(5, 8, 'Lunes', 600, 595, '2014-12-07 23:05:05', '0000-00-00 00:00:00'),
(6, 8, 'Miercoles', 660, 1140, '2014-12-07 23:05:05', '0000-00-00 00:00:00');

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
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Volcado de datos para la tabla `laboratorio_piezapresupuesto`
--

INSERT INTO `laboratorio_piezapresupuesto` (`id`, `id_presupuesto`, `pieza`, `prestacion`, `valor`, `created_at`, `update_at`) VALUES
(1, 3, 5, 'asd', 1000, '2014-12-07 23:06:44', '0000-00-00 00:00:00'),
(2, 6, 8, 'aaaaa', 2000, '2014-12-07 23:06:44', '0000-00-00 00:00:00'),
(3, 5, 5, 'aaa', 500, '2014-12-07 23:06:44', '0000-00-00 00:00:00');

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
(1, 'Mario', 'Sanhueza', 'Leiva', '17501528-0', '', '1989-11-18', 24, 2, 'nada', '89348184', ' Región del Maule', 'CURICO', '', 1, '2014-10-13 14:51:07', '0000-00-00 00:00:00'),
(2, 'Andres', 'Sanhueza', 'Leiva', '17501527-2-0', '', '2013-10-10', 1, 2, 'nada', '88888888', ' Región del Maule', 'CURICO', '', 1, '2014-10-13 14:51:44', '0000-00-00 00:00:00'),
(20, 'Mario', 'Uribe', 'Leiva', '1789292-k', '', '1990-12-14', 23, 2, 'nada', '78373636', ' Región del Maule', 'CURICO', '', 1, '2014-10-13 19:22:31', '0000-00-00 00:00:00'),
(21, 'Marianela Andrea', 'Iturriaga', 'Jimenez', '17157036-0', 'mari.ytu@gmail.com', '1989-07-19', 25, 1, '-', '56072407', 'Región del Libertador General Bernardo O Higgins', 'MACHALI', 'Calle Diego Ramirez, 2146', 1, '2014-10-26 21:13:31', '0000-00-00 00:00:00'),
(22, 'Miriam del Carmen', 'Jimenez', 'Cerpa', '11368812-2', 'miriam.jimenez@hotmail.cl', '1969-03-21', 45, 1, '-', '99999999', 'Región del Maule', 'CURICO', 'Villa Galilea, pasaje Rio Tiber, 1916', 1, '2014-10-26 21:15:12', '0000-00-00 00:00:00');

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
(5, 21, 4, '2014-10-27 17:07:47', '2014-11-05 03:02:17', 4000, 3000, 66, 1, '2014-11-05 03:02:17', '2014-12-07 19:51:25'),
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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=7 ;

--
-- Volcado de datos para la tabla `presupuesto`
--

INSERT INTO `presupuesto` (`id_presupuesto`, `id_paciente`, `id_profesional`, `estado`, `costo_total`, `cantidad_tratamiento`, `costo_lab`, `activo`, `created_at`, `update_at`) VALUES
(3, 22, 8, 1, 19312, 2, 1000, 1, '2014-10-30 14:30:53', '2014-12-07 17:38:17'),
(4, 21, 3, 1, 1000, 1, 0, 0, '2014-11-03 19:23:02', '2014-11-03 19:23:02'),
(5, 22, 3, 1, 11587, 1, 500, 1, '2014-12-07 15:42:26', '2014-12-07 17:40:32'),
(6, 22, 8, 1, 7725, 1, 2000, 1, '2014-12-07 17:40:06', '2014-12-07 17:40:06');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `regiones`
--

CREATE TABLE IF NOT EXISTS `regiones` (
  `codigo` int(11) NOT NULL,
  `nombre` char(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `orden` int(11) NOT NULL,
  `activo` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci;

--
-- Volcado de datos para la tabla `regiones`
--

INSERT INTO `regiones` (`codigo`, `nombre`, `orden`, `activo`) VALUES
(7, 'Región del Maule', 0, 1),
(15, 'Región de Arica y Parinacota', 0, 1),
(1, 'Región de Tarapacá', 0, 1),
(2, 'Región de Antofagasta', 0, 1),
(3, 'Región de Atacama', 0, 1),
(4, 'Región de Coquimbo', 0, 1),
(5, 'Región de Valparaiso', 0, 1),
(13, 'Región Metropolitana', 0, 1),
(6, 'Región del Libertador General Bernardo O Higgins', 0, 1),
(8, 'Región del Bío-Bío', 0, 1),
(9, 'Región de la Araucanía', 0, 1),
(14, 'Región de Los Ríos', 0, 1),
(10, 'Región de Los Lagos', 0, 1),
(11, 'Región de Aysén del General Carlos Ibáñez del Campo', 0, 1),
(12, 'Región de Magallanes y la Antártica Chilena', 0, 1);

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
  `nombre` varchar(255) COLLATE latin1_spanish_ci DEFAULT NULL,
  `cantidad_uco` double DEFAULT NULL,
  `activo` tinyint(1) DEFAULT '1',
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_tratamiento`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=596 ;

--
-- Volcado de datos para la tabla `tratamiento`
--

INSERT INTO `tratamiento` (`id_tratamiento`, `id_categoria2`, `nombre`, `cantidad_uco`, `activo`, `created_at`, `update_at`) VALUES
(1, 1, 'Examen inicial, Plan de Tratamiento y Presupuesto ', 1.5, 1, '2014-12-12 01:16:58', '0000-00-00 00:00:00'),
(2, 1, 'Control y examen periódico de rigor ', 1, 1, '2014-12-12 01:17:08', '0000-00-00 00:00:00'),
(3, 1, 'Interconsulta con informe escrito 1 sesión ', 2, 1, '2014-12-12 01:17:22', '0000-00-00 00:00:00'),
(4, 1, 'Urgencias. Tratamiento inicial 1 sesión', 1, 1, '2014-12-12 01:17:34', '0000-00-00 00:00:00'),
(5, 1, 'Urgencias a domicilio ID anterior', 2, 1, '2014-12-12 01:17:42', '0000-00-00 00:00:00'),
(6, 1, 'Urgencias en Hospital ID anterior', 2, 1, '2014-12-12 01:17:50', '0000-00-00 00:00:00'),
(7, 1, 'Estudio preliminar clínico, Rx y modelos', 2, 1, '2014-12-12 01:18:01', '0000-00-00 00:00:00'),
(8, 1, 'Informes periciales 1 hora profesional', 3, 1, '2014-12-12 01:18:12', '0000-00-00 00:00:00'),
(9, 1, 'Consultorías y estudio profesional: 1 hora', 3, 1, '2014-12-12 01:18:22', '0000-00-00 00:00:00'),
(10, 2, 'Higiene o Profilaxis en adultos', 2, 1, '2014-12-12 01:18:39', '0000-00-00 00:00:00'),
(11, 2, 'Higiene o Profilaxis en niños', 1, 1, '2014-12-12 01:18:48', '0000-00-00 00:00:00'),
(12, 2, 'Instrucción y control higiene oral adultos', 1, 1, '2014-12-12 01:18:57', '0000-00-00 00:00:00'),
(13, 2, 'Instrucción y control higiene oral niños', 1, 1, '2014-12-12 01:19:08', '0000-00-00 00:00:00'),
(14, 2, 'Aplicación de flúor en colutorios (tto)', 1, 1, '2014-12-12 01:19:17', '0000-00-00 00:00:00'),
(15, 2, 'Aplicación flúor gel total en niños y adultos', 2, 1, '2014-12-12 01:19:32', '0000-00-00 00:00:00'),
(16, 2, 'Aplicación flúor Silano Total, niños y adultos', 5, 1, '2014-12-12 01:19:41', '0000-00-00 00:00:00'),
(17, 2, 'Aplicación Sellante pieza temp. Fotocurado', 1, 1, '2014-12-12 01:19:49', '0000-00-00 00:00:00'),
(18, 2, 'Aplicación Sellante pieza def. fotocurado', 1.5, 1, '2014-12-12 01:19:58', '0000-00-00 00:00:00'),
(19, 2, 'Mantenedor espacio fijo', 4, 1, '2014-12-12 01:20:05', '0000-00-00 00:00:00'),
(20, 2, 'Mantenedor de espacio removible', 4, 1, '2014-12-12 01:20:14', '0000-00-00 00:00:00'),
(21, 3, 'Interconsulta (junta de especialistas)', 4, 1, '2014-12-12 01:29:17', '0000-00-00 00:00:00'),
(22, 3, 'Consulta y examen máxilo-facial', 2, 1, '2014-12-12 01:29:28', '0000-00-00 00:00:00'),
(23, 3, 'Interconsulta e informe', 3, 1, '2014-12-12 01:29:35', '0000-00-00 00:00:00'),
(24, 3, 'Controles de la especialidad', 2, 1, '2014-12-12 01:29:44', '0000-00-00 00:00:00'),
(25, 3, 'Recargo por tratamiento fuera de lugar habitual', 4, 1, '2014-12-12 01:29:52', '0000-00-00 00:00:00'),
(26, 3, 'Exodoncia simple', 3, 1, '2014-12-12 01:30:01', '0000-00-00 00:00:00'),
(27, 3, 'Exodoncia a colgajo', 4, 1, '2014-12-12 01:30:09', '0000-00-00 00:00:00'),
(28, 3, 'Exodoncia de incluidos', 8, 1, '2014-12-12 01:30:19', '0000-00-00 00:00:00'),
(29, 3, 'Exodoncia de 4 terceros molares incluidos', 20, 1, '2014-12-12 01:30:27', '0000-00-00 00:00:00'),
(30, 3, 'Tratamiento de alveolorragia, alveolitis', 3, 1, '2014-12-12 01:30:39', '0000-00-00 00:00:00'),
(31, 3, 'Alveoloplastía (no incluye exodoncias)', 10, 1, '2014-12-12 01:30:49', '0000-00-00 00:00:00'),
(32, 3, 'Tratamiento de comunicación bucosinusal inmediata a exodoncia', 8, 1, '2014-12-12 01:30:59', '0000-00-00 00:00:00'),
(33, 3, 'Cirugía complementaria a exodoncia complicada (remoción de agujas u otros c/e)', 16, 1, '2014-12-12 01:32:42', '0000-00-00 00:00:00'),
(34, 3, 'Plastía de comunicación bucosinusal o retiro de cuerpo extraño en seno maxilar', 20, 1, '2014-12-12 01:33:27', '0000-00-00 00:00:00'),
(35, 4, 'Reconstrucción de rebordes con auto-injerto', 20, 1, '2014-12-12 01:34:42', '0000-00-00 00:00:00'),
(36, 4, 'Reconstrucción de rebordes con homo o heteroinjertos', 20, 1, '2014-12-12 01:34:59', '0000-00-00 00:00:00'),
(37, 4, 'Reconstrucción de rebordes con elementos haloplásticos', 20, 1, '2014-12-12 01:35:14', '0000-00-00 00:00:00'),
(38, 4, 'Reconstrucción de mandíbula post resección simple', 20, 1, '2014-12-12 01:35:23', '0000-00-00 00:00:00'),
(39, 4, 'Reconstrucción de mandíbula post resección compleja', 40, 1, '2014-12-12 01:35:44', '0000-00-00 00:00:00'),
(40, 4, 'Reconstrucción de mandíbula post resección bilateral', 80, 1, '2014-12-12 01:36:00', '0000-00-00 00:00:00'),
(41, 4, 'Toma de injerto de cartílago', 20, 1, '2014-12-12 01:36:09', '0000-00-00 00:00:00'),
(42, 4, 'Toma de injerto óseo intraoral', 20, 1, '2014-12-12 01:36:18', '0000-00-00 00:00:00'),
(43, 4, 'Toma de injerto óseo extraoral', 30, 1, '2014-12-12 01:36:29', '0000-00-00 00:00:00'),
(44, 4, 'Toma de inerto de tejido blando (piel, mucosa)', 20, 1, '2014-12-12 01:36:38', '0000-00-00 00:00:00'),
(45, 4, 'Implante haloplástico (malar, mentón, frontal)', 30, 1, '2014-12-12 01:37:01', '0000-00-00 00:00:00'),
(46, 4, 'Infiltraciones de fármacos (esclerosantes, esteroides, botox, colágeno, etc.) ', 10, 1, '2014-12-12 01:37:38', '0000-00-00 00:00:00'),
(47, 4, 'Punciones aspirativas (hematoma, etc.)', 10, 1, '2014-12-12 01:37:50', '0000-00-00 00:00:00'),
(48, 4, 'Elevación de piso del seno maxilar (para instalación de implantes)', 30, 1, '2014-12-12 01:38:04', '0000-00-00 00:00:00'),
(49, 5, 'Examen y diagnóstico', 2, 1, '2014-12-12 01:38:16', '0000-00-00 00:00:00'),
(50, 5, 'Tratamiento médico del dolor (cada sesión)', 3, 1, '2014-12-12 01:38:29', '0000-00-00 00:00:00'),
(51, 5, 'Infiltraciones tronculares intra-orales', 4, 1, '2014-12-12 01:38:37', '0000-00-00 00:00:00'),
(52, 5, 'Infiltraciones tronculares extra-orales', 6, 1, '2014-12-12 01:39:01', '0000-00-00 00:00:00'),
(53, 5, 'Plano de alivio oclusal (no incluye laboratorio)', 8, 1, '2014-12-12 01:39:10', '0000-00-00 00:00:00'),
(54, 5, 'Tratamiento de disfunción dolorosa de ATM por sesión', 3, 1, '2014-12-12 01:39:21', '0000-00-00 00:00:00'),
(55, 5, 'Tratamiento de urgencia del dolor facial', 3, 1, '2014-12-12 01:39:31', '0000-00-00 00:00:00'),
(56, 6, 'Artrocentesis', 12, 1, '2014-12-12 01:39:46', '0000-00-00 00:00:00'),
(57, 6, 'Artroscopía', 20, 1, '2014-12-12 01:39:55', '0000-00-00 00:00:00'),
(58, 6, 'Condilectomía mandibular', 35, 1, '2014-12-12 01:40:04', '0000-00-00 00:00:00'),
(59, 6, 'Condilectomía temporal', 35, 1, '2014-12-12 01:40:15', '0000-00-00 00:00:00'),
(60, 6, 'Coronoindectomía', 20, 1, '2014-12-12 01:40:25', '0000-00-00 00:00:00'),
(61, 6, 'Meniscopexia', 35, 1, '2014-12-12 01:40:33', '0000-00-00 00:00:00'),
(62, 6, 'Tratamiento quirúrgico de la anquilosis ATM', 40, 1, '2014-12-12 01:40:41', '0000-00-00 00:00:00'),
(63, 6, 'Reconstrucción de la ATM', 40, 1, '2014-12-12 01:40:50', '0000-00-00 00:00:00'),
(64, 6, 'Reconstrucción de la ATM con reemplazo total', 55, 1, '2014-12-12 01:41:00', '0000-00-00 00:00:00'),
(65, 6, 'Tratamiento quirúrgico de la fractura de cóndilo uni o bilateral', 40, 1, '2014-12-12 01:41:40', '0000-00-00 00:00:00'),
(66, 6, 'Estudio de trismus (clínico, imagenológico, interconsultas)', 15, 1, '2014-12-12 01:41:54', '0000-00-00 00:00:00'),
(67, 7, 'Resección o plastía de frenillos, bridas o sinequias', 5, 1, '2014-12-12 01:42:14', '0000-00-00 00:00:00'),
(68, 7, 'Vestibuloplastía simple', 10, 1, '2014-12-12 01:42:21', '0000-00-00 00:00:00'),
(69, 7, 'Vestibuloplastía compleja (con injerto u otros sistemas de contención)', 26, 1, '2014-12-12 01:42:38', '0000-00-00 00:00:00'),
(70, 7, 'Plastía de rebordes alveolares, paladar duro', 20, 1, '2014-12-12 01:42:50', '0000-00-00 00:00:00'),
(71, 7, 'Implante oseointegrado (unitario)', 28, 1, '2014-12-12 01:42:57', '0000-00-00 00:00:00'),
(72, 7, 'Implante cigomático y otros extra alveolares', 30, 1, '2014-12-12 01:43:07', '0000-00-00 00:00:00'),
(73, 7, 'Abordaje submentoniano para intubación submental', 12, 1, '2014-12-12 01:43:16', '0000-00-00 00:00:00'),
(74, 7, 'Cirugía apical y pararradicular con/sin relleno a retro (incisivos o caninos)', 8, 1, '2014-12-12 01:43:32', '0000-00-00 00:00:00'),
(75, 7, 'Cirugía apical y pararadicular con/sin relleno a retro (premolares y molares)', 10, 1, '2014-12-12 01:43:43', '0000-00-00 00:00:00'),
(76, 7, 'Fenestración simple', 7, 1, '2014-12-12 01:43:50', '0000-00-00 00:00:00'),
(77, 7, 'Fenestración compleja', 8, 1, '2014-12-12 01:43:59', '0000-00-00 00:00:00'),
(78, 7, 'Colocación de micro-tornillos', 2.5, 1, '2014-12-12 01:44:07', '0000-00-00 00:00:00'),
(79, 8, 'Osteotomía correctora del cigoma', 20, 1, '2014-12-12 01:44:24', '0000-00-00 00:00:00'),
(80, 8, 'Osteotomía segmentaria del maxilar', 30, 1, '2014-12-12 01:44:32', '0000-00-00 00:00:00'),
(81, 8, 'Osteotomía Le Fort I', 85, 1, '2014-12-12 01:44:42', '0000-00-00 00:00:00'),
(82, 8, 'Osteotomía segmentaria de mandíbula', 30, 1, '2014-12-12 01:44:50', '0000-00-00 00:00:00'),
(83, 8, 'Osteotomía mandibular en el cuerpo', 50, 1, '2014-12-12 01:44:58', '0000-00-00 00:00:00'),
(84, 8, 'Osteotomía vertical de rama mandibular', 70, 1, '2014-12-12 01:45:07', '0000-00-00 00:00:00'),
(85, 8, 'Osteotomía sagital de rama mandibluar', 70, 1, '2014-12-12 01:45:20', '0000-00-00 00:00:00'),
(86, 8, 'Osteotomía de rama. Abordaje cutáneo', 80, 1, '2014-12-12 01:45:36', '0000-00-00 00:00:00'),
(87, 8, 'Genioplastía', 30, 1, '2014-12-12 01:45:49', '0000-00-00 00:00:00'),
(88, 8, 'Cirugía ortognática combinada (doble)', 120, 1, '2014-12-12 01:45:58', '0000-00-00 00:00:00'),
(89, 9, 'Distracción osteogénica alveolar', 25, 1, '2014-12-12 01:46:16', '0000-00-00 00:00:00'),
(90, 9, 'Distracción osteogénica mandibular', 35, 1, '2014-12-12 01:46:23', '0000-00-00 00:00:00'),
(91, 9, 'Distracción osteogénica maxilar', 30, 1, '2014-12-12 01:46:31', '0000-00-00 00:00:00'),
(92, 9, 'Distracción osteogénica del recién nacido', 35, 1, '2014-12-12 01:46:39', '0000-00-00 00:00:00'),
(93, 9, 'Distracción osteogénica craneofacial', 35, 1, '2014-12-12 01:46:47', '0000-00-00 00:00:00'),
(94, 9, 'Colocación de microtornillos', 2.5, 1, '2014-12-12 01:46:56', '0000-00-00 00:00:00'),
(95, 9, 'Glosoplastía', 2, 1, '2014-12-12 01:47:03', '0000-00-00 00:00:00'),
(96, 10, 'Cierre primario de fisura lateral (macrostomía)', 25, 1, '2014-12-12 01:47:20', '0000-00-00 00:00:00'),
(97, 10, 'Cierre primario de fisura maxilopalatina (1er tiempo)', 30, 1, '2014-12-12 01:47:29', '0000-00-00 00:00:00'),
(98, 10, 'Cierre primario de fisura maxilopalatina o cierre maxilar (2do tiempo)', 20, 1, '2014-12-12 01:47:41', '0000-00-00 00:00:00'),
(99, 10, 'Cierre secundario de fisura maxiloplatina', 30, 1, '2014-12-12 01:47:50', '0000-00-00 00:00:00'),
(100, 10, 'Cierre labio fisurado unilateral primario', 30, 1, '2014-12-12 01:47:59', '0000-00-00 00:00:00'),
(101, 10, 'Cierre labio fisurado unilateral secundario', 25, 1, '2014-12-12 01:48:06', '0000-00-00 00:00:00'),
(102, 10, 'Cierre labio fisurado biateral primario', 40, 1, '2014-12-12 01:48:14', '0000-00-00 00:00:00'),
(103, 10, 'Cierre labio fisurado bilateral secundario', 30, 1, '2014-12-12 01:48:27', '0000-00-00 00:00:00'),
(104, 10, 'Gingivoperiostioplastía unilateral', 20, 1, '2014-12-12 01:48:39', '0000-00-00 00:00:00'),
(105, 10, 'Cierre fístula buconasal', 15, 1, '2014-12-12 01:48:48', '0000-00-00 00:00:00'),
(106, 10, 'Faringoplastía', 30, 1, '2014-12-12 01:48:57', '0000-00-00 00:00:00'),
(107, 10, 'Uvuloplastía', 20, 1, '2014-12-12 01:49:05', '0000-00-00 00:00:00'),
(108, 10, 'Plastía nasal parcial', 30, 1, '2014-12-12 01:49:14', '0000-00-00 00:00:00'),
(109, 10, 'Vestibuloplastía', 10, 1, '2014-12-12 01:49:22', '0000-00-00 00:00:00'),
(110, 11, 'Osteotomía Le Fort II', 90, 1, '2014-12-12 01:49:36', '0000-00-00 00:00:00'),
(111, 11, 'Osteotomía Le Fort III', 90, 1, '2014-12-12 01:49:46', '0000-00-00 00:00:00'),
(112, 11, 'Cirugía de las craneosinostosis', 90, 1, '2014-12-12 01:49:56', '0000-00-00 00:00:00'),
(113, 12, 'Biopsia de mucosa (no incluye laboratorio)', 4, 1, '2014-12-12 01:51:43', '0000-00-00 00:00:00'),
(114, 12, 'Biopsia de piel (no incluye laboratorio)', 5, 1, '2014-12-12 01:51:51', '0000-00-00 00:00:00'),
(115, 12, 'Biopsia ósea', 8, 1, '2014-12-12 01:51:59', '0000-00-00 00:00:00'),
(116, 12, 'Biopsia por punción (no incluye laboratorio)', 4, 1, '2014-12-12 01:52:08', '0000-00-00 00:00:00'),
(117, 12, 'Biopsia ósea mas instalación de collera', 20, 1, '2014-12-12 01:52:17', '0000-00-00 00:00:00'),
(118, 12, 'Enucleación de quiste o tumor simple', 14, 1, '2014-12-12 01:52:25', '0000-00-00 00:00:00'),
(119, 12, 'Enucleación de quiste o tumor, con tratamiento del lecho operatorio', 25, 1, '2014-12-12 01:53:28', '0000-00-00 00:00:00'),
(120, 12, 'Resección marginal quiste o tumor con reconstrucción', 45, 1, '2014-12-12 01:55:41', '0000-00-00 00:00:00'),
(121, 12, 'Resección radical quiste o tumor con reconstrucción (no incluye toma de injerto) ', 60, 1, '2014-12-12 01:56:13', '0000-00-00 00:00:00'),
(122, 12, 'Maxilectomía con reconstrucción (no incluye toma de injerto)', 80, 1, '2014-12-12 01:56:34', '0000-00-00 00:00:00'),
(123, 12, 'Hemimandibulectomía con reconstrucción (no incluye toma de injerto)', 80, 1, '2014-12-12 01:56:50', '0000-00-00 00:00:00'),
(124, 12, 'Mandibulectomía con reconstrucción (no incluye toma de injerto) ', 120, 1, '2014-12-12 01:57:24', '0000-00-00 00:00:00'),
(125, 12, 'Exéresis de mucocele', 10, 1, '2014-12-12 01:57:46', '0000-00-00 00:00:00'),
(126, 12, 'Exéresis de cálculos salivales (intra-oral)', 18, 1, '2014-12-12 01:57:54', '0000-00-00 00:00:00'),
(127, 12, 'Exéresis de glándula sublingual', 30, 1, '2014-12-12 01:58:02', '0000-00-00 00:00:00'),
(128, 12, 'Exéresis glándula sub-mandibular', 60, 1, '2014-12-12 01:58:11', '0000-00-00 00:00:00'),
(129, 12, 'Tratamiento quirúrgico de fístula salival', 18, 1, '2014-12-12 01:58:26', '0000-00-00 00:00:00'),
(130, 13, 'Toma de muestra para cultivo', 2, 1, '2014-12-12 01:59:09', '0000-00-00 00:00:00'),
(131, 13, 'Tratamiento de absceso submucoso (vestíbulo, paladar)', 5, 1, '2014-12-12 01:59:26', '0000-00-00 00:00:00'),
(132, 13, 'Tratamiento de absceso submucoso (piso de boca, periamigdaliano)', 10, 1, '2014-12-12 01:59:48', '0000-00-00 00:00:00'),
(133, 13, 'Tratamiento de absceso cutáneo', 6, 1, '2014-12-12 01:59:56', '0000-00-00 00:00:00'),
(134, 13, 'Tratamiento de estomatitis infecciosas (guna, etc)', 5, 1, '2014-12-12 02:00:06', '0000-00-00 00:00:00'),
(135, 13, 'Secuestrectomía o aseo quirúrgico de osteomielitis u osteorradionecrosis', 12, 1, '2014-12-12 02:00:33', '0000-00-00 00:00:00'),
(136, 13, 'Tratamiento de la infección periimplantaria (por sesión)', 6.5, 1, '2014-12-12 02:01:11', '0000-00-00 00:00:00'),
(137, 13, 'Tratamiento médico de celulitis (flegmón) cérvico-facial', 14, 1, '2014-12-12 02:02:44', '0000-00-00 00:00:00'),
(138, 13, 'Tratamiento médico-quirúrgico de celulitis (flegmón) Cérvico-facial unilateral', 20, 1, '2014-12-12 02:03:00', '0000-00-00 00:00:00'),
(139, 13, 'Tratamiento médico-quirúrgico de celulitis (flegmón) Cérvico-facial bilateral', 30, 1, '2014-12-12 02:03:26', '0000-00-00 00:00:00'),
(140, 13, 'Tratamiento quirúrgico de paraflegmones', 50, 1, '2014-12-12 02:03:37', '0000-00-00 00:00:00'),
(141, 14, 'Tratamiento de heridas de la mucosa bucal (simples) ', 4, 1, '2014-12-12 02:04:04', '0000-00-00 00:00:00'),
(142, 14, 'Tratamiento de heridas de la mucosa bucal (complejas o bajo anestesia general)', 8, 1, '2014-12-12 02:04:19', '0000-00-00 00:00:00'),
(143, 14, 'Tratamiento de heridas faciales simples (hasta 5 cm  o que sólo comprometen piel)', 6, 1, '2014-12-12 02:04:35', '0000-00-00 00:00:00'),
(144, 14, 'Tratamiento de heridas faciales complejas (más de 5 cm o que comprometan músculos, nervios o conductos más de 2,5 porciento adicional)', 16, 1, '2014-12-12 02:09:45', '0000-00-00 00:00:00'),
(145, 14, 'Reducción e inmovilización de luxación dentaria simple (hasta 2 piezas)', 5, 1, '2014-12-12 02:15:22', '0000-00-00 00:00:00'),
(146, 14, 'Reducción e inmovilización de luxación dentaria compleja (por segmento)', 10, 1, '2014-12-12 02:15:48', '0000-00-00 00:00:00'),
(147, 14, 'Tratamiento de fractura dentaria (manejo de urgencia)', 4, 1, '2014-12-12 02:16:08', '0000-00-00 00:00:00'),
(148, 14, 'Reimplante dentario único', 6, 1, '2014-12-12 02:16:18', '0000-00-00 00:00:00'),
(149, 14, 'Reimplante dentario múltiple', 10, 1, '2014-12-12 02:16:31', '0000-00-00 00:00:00'),
(150, 14, 'Tratamiento de fractura mandibular cerrada', 20, 1, '2014-12-12 02:16:40', '0000-00-00 00:00:00'),
(151, 14, 'Reducción cerrada de fractura cigomática y/o arco cigomático', 20, 1, '2014-12-12 02:16:57', '0000-00-00 00:00:00'),
(152, 14, 'Reduccion y OTS única de fractura (mandíbula, maxilar o cigoma)', 30, 1, '2014-12-12 02:17:23', '0000-00-00 00:00:00'),
(153, 14, 'Reducción y OTS múltiples de fractura (2 o más)  (mandíbula, maxilar o cigoma)', 40, 1, '2014-12-12 02:17:49', '0000-00-00 00:00:00'),
(154, 14, 'Reducción y reparación de fractura orbitaria (blow Out) (con implante o injerto)', 40, 1, '2014-12-12 02:18:18', '0000-00-00 00:00:00'),
(155, 14, 'Reducción y OTS de fracturas complejas (con placa de reconstrucción, implantes, injertos y/o abordajes Combinados) ', 60, 1, '2014-12-12 02:18:39', '0000-00-00 00:00:00'),
(156, 14, 'Reducción y OTS de fracturas extendidas tipo Le Fort (con abordajes múltiples)', 50, 1, '2014-12-12 02:19:02', '0000-00-00 00:00:00'),
(157, 14, 'Reducción y OTS de fracturas panfaciales con abordaje coronal y/u otros en conjunto con equipos Quirúrgicos. Tiempo facial', 70, 1, '2014-12-12 02:19:42', '0000-00-00 00:00:00'),
(158, 14, 'Reconstrucción de partes duras y blandas de la cara mediante abordajes múltiples, hemicoronal o bicoronal', 80, 1, '2014-12-12 02:20:33', '0000-00-00 00:00:00'),
(159, 14, 'Remoción de arcos o elementos de contención', 7, 1, '2014-12-12 02:20:50', '0000-00-00 00:00:00'),
(160, 14, 'Remoción de elementos de síntesis', 10, 1, '2014-12-12 02:20:59', '0000-00-00 00:00:00'),
(161, 14, 'Taponamiento nasal anterior', 3, 1, '2014-12-12 02:21:10', '0000-00-00 00:00:00'),
(162, 14, 'Taponamiento nasal posterior', 8, 1, '2014-12-12 02:21:18', '0000-00-00 00:00:00'),
(163, 14, 'Reducción cerrada de fractura nasal', 8, 1, '2014-12-12 02:21:31', '0000-00-00 00:00:00'),
(164, 16, 'Diagnóstico con vitalometría', 1.5, 1, '2014-12-12 02:22:30', '0000-00-00 00:00:00'),
(165, 16, 'Trepanación por urgencia', 2.5, 1, '2014-12-12 02:22:39', '0000-00-00 00:00:00'),
(166, 16, 'Vaciamiento de obsesos', 3, 1, '2014-12-12 02:22:47', '0000-00-00 00:00:00'),
(167, 16, 'Endodoncia incisivo y caninos vitales', 7.5, 1, '2014-12-12 02:22:55', '0000-00-00 00:00:00'),
(168, 16, 'Endodoncia en premolares vitales', 9, 1, '2014-12-12 02:23:04', '0000-00-00 00:00:00'),
(169, 16, 'Desobturación parcial en conductos para anclaje', 1.5, 1, '2014-12-12 02:23:13', '0000-00-00 00:00:00'),
(170, 17, 'Endodoncia en incisivos y caninos vitales', 8.5, 1, '2014-12-12 02:24:16', '0000-00-00 00:00:00'),
(171, 17, 'Endodoncia en incisivos y caninos no vitales sin lesión', 9, 1, '2014-12-12 02:24:25', '0000-00-00 00:00:00'),
(172, 17, 'Endodoncia en incisivos y caninos no vitales con lesión', 9.5, 1, '2014-12-12 02:24:36', '0000-00-00 00:00:00'),
(173, 17, 'Endodoncia premolares vitales', 9.5, 1, '2014-12-12 02:24:49', '0000-00-00 00:00:00'),
(174, 17, 'Endodoncia premolares no vitales sin lesión', 10, 1, '2014-12-12 02:25:04', '0000-00-00 00:00:00'),
(175, 17, 'Endodoncia premolares no vitales con lesión', 10.5, 1, '2014-12-12 02:25:12', '0000-00-00 00:00:00'),
(176, 17, 'Endodoncia premolares tri-radiculares (sin lesión)', 13, 1, '2014-12-12 02:25:19', '0000-00-00 00:00:00'),
(177, 17, 'Endodoncia premolares tri-radiculares (con lesión)', 15, 1, '2014-12-12 02:25:27', '0000-00-00 00:00:00'),
(178, 17, 'Endodoncia en molares superiores vitales', 15, 1, '2014-12-12 02:25:37', '0000-00-00 00:00:00'),
(179, 17, 'Endodoncia en molares inferiores vitales', 13, 1, '2014-12-12 02:25:44', '0000-00-00 00:00:00'),
(180, 17, 'Endodoncia en molar superior no vital sin lesión', 16, 1, '2014-12-12 02:25:52', '0000-00-00 00:00:00'),
(181, 17, 'Endodoncia en molar superior no vital con lesión', 16.5, 1, '2014-12-12 02:26:01', '0000-00-00 00:00:00'),
(182, 17, 'Endodoncia en molar inferior no vital sin lesión', 15.5, 1, '2014-12-12 02:26:12', '0000-00-00 00:00:00'),
(183, 17, 'Endodoncia en molar inferior no vital con lesión', 16, 1, '2014-12-12 02:26:20', '0000-00-00 00:00:00'),
(184, 18, 'Incisivos y caninos sin lesión', 16.5, 1, '2014-12-12 02:26:52', '0000-00-00 00:00:00'),
(185, 18, 'Incisivos y caninos con lesión', 17, 1, '2014-12-12 02:27:04', '0000-00-00 00:00:00'),
(186, 18, 'Premolares sin lesión', 17.5, 1, '2014-12-12 02:27:11', '0000-00-00 00:00:00'),
(187, 18, 'Premolares con lesión', 18.5, 1, '2014-12-12 02:27:24', '0000-00-00 00:00:00'),
(188, 19, 'Premolares tri-radiculares sin lesión', 21, 1, '2014-12-12 02:28:23', '0000-00-00 00:00:00'),
(189, 19, 'Premolares tri-radiculares con lesión', 22, 1, '2014-12-12 02:28:29', '0000-00-00 00:00:00'),
(190, 19, 'Molares (superior e inferior) con lesión apical', 24, 1, '2014-12-12 02:28:36', '0000-00-00 00:00:00'),
(191, 19, 'Control endodóntico', 2, 1, '2014-12-12 02:28:49', '0000-00-00 00:00:00'),
(192, 19, 'Diagnóstico pulpar', 2, 1, '2014-12-12 02:28:56', '0000-00-00 00:00:00'),
(193, 19, 'Urgencia endodóntica', 3, 1, '2014-12-12 02:29:04', '0000-00-00 00:00:00'),
(194, 19, 'Recubrimiento indirecto y directo', 3, 1, '2014-12-12 02:29:11', '0000-00-00 00:00:00'),
(195, 19, 'Pulpotomía en diente permanente joven, parcial o total', 5, 1, '2014-12-12 02:29:20', '0000-00-00 00:00:00'),
(196, 19, 'Inducción al cierre apical mediata (por sesión)', 3, 1, '2014-12-12 02:29:30', '0000-00-00 00:00:00'),
(197, 19, 'Inducción al cierre apical inmediata (por sesión incluye MTA)', 5, 1, '2014-12-12 02:29:42', '0000-00-00 00:00:00'),
(198, 19, 'Tratamiento de revascularización (valor por cada sesión)', 5, 1, '2014-12-12 02:29:50', '0000-00-00 00:00:00'),
(199, 19, 'Sesión de medicación intra-conducto', 3, 1, '2014-12-12 02:30:01', '0000-00-00 00:00:00'),
(200, 19, 'Trepanación de incrustaciones y férulas', 2, 1, '2014-12-12 02:30:09', '0000-00-00 00:00:00'),
(201, 19, 'Desobturación parcial en conductos para anclaje', 2, 1, '2014-12-12 02:30:16', '0000-00-00 00:00:00'),
(202, 19, 'Conducto calcificado (por sesión)', 3, 1, '2014-12-12 02:30:26', '0000-00-00 00:00:00'),
(203, 19, 'Sellado de perforaciones y falsas vías', 4, 1, '2014-12-12 02:30:35', '0000-00-00 00:00:00'),
(204, 19, 'Instrumentación mecanizada', 3, 1, '2014-12-12 02:30:43', '0000-00-00 00:00:00'),
(205, 19, 'Retiro de instrumento fracturado del conducto', 2, 1, '2014-12-12 02:30:50', '0000-00-00 00:00:00'),
(206, 19, 'Retiro de espiga del conducto', 4.5, 1, '2014-12-12 02:30:58', '0000-00-00 00:00:00'),
(207, 19, 'Obturación con técnicas termoplásticas', 4, 1, '2014-12-12 02:31:07', '0000-00-00 00:00:00'),
(208, 19, 'Aislación unitaria compleja', 1, 1, '2014-12-12 02:31:15', '0000-00-00 00:00:00'),
(209, 19, 'Reconstrucción para aislamiento', 3, 1, '2014-12-12 02:31:25', '0000-00-00 00:00:00'),
(210, 19, 'Doble sellado', 2.5, 1, '2014-12-12 02:32:18', '0000-00-00 00:00:00'),
(211, 19, 'Gingivectomía unitaria para aislación', 2, 1, '2014-12-12 02:32:25', '0000-00-00 00:00:00'),
(212, 19, 'Apicectomía (no incluye pabellón)', 8, 1, '2014-12-12 02:32:38', '0000-00-00 00:00:00'),
(213, 19, 'Apicectomía y obturación a retro (incluye MTA - no incluye pabellón)', 15, 1, '2014-12-12 02:32:59', '0000-00-00 00:00:00'),
(214, 19, 'Radectomía y/o hemi-sección sin endodoncia', 8, 1, '2014-12-12 02:33:07', '0000-00-00 00:00:00'),
(215, 19, 'Blanqueamiento de dientes desvitalizados', 4, 1, '2014-12-12 02:33:16', '0000-00-00 00:00:00'),
(216, 19, 'Ferulización por grupo (por traumatismo dento-alveolar)', 10, 1, '2014-12-12 02:33:27', '0000-00-00 00:00:00'),
(217, 19, 'Radiografía retro-alveolar de conductometría', 0.5, 1, '2014-12-12 02:33:38', '0000-00-00 00:00:00'),
(218, 19, 'Radiografía retro-alveolar control de obturación', 0.5, 1, '2014-12-12 02:33:49', '0000-00-00 00:00:00'),
(219, 19, 'Radiografía retro-alveolar control de desobturación parcial o total', 0.5, 1, '2014-12-12 02:34:06', '0000-00-00 00:00:00'),
(220, 19, 'Microscopía', 5, 1, '2014-12-12 02:34:57', '0000-00-00 00:00:00'),
(221, 20, 'Destartraje supra-gingival y limpieza coronaria', 3, 1, '2014-12-12 02:35:39', '0000-00-00 00:00:00'),
(222, 20, 'Resina infiltrante lesión incipiente en pieza permant.', 2.5, 1, '2014-12-12 02:35:47', '0000-00-00 00:00:00'),
(223, 20, 'Educación en colegios e institución (hora pedagógica)', 3, 1, '2014-12-12 02:35:54', '0000-00-00 00:00:00'),
(224, 20, 'Higiene y profilaxis en niños', 2, 1, '2014-12-12 02:36:01', '0000-00-00 00:00:00'),
(225, 20, 'Instrucción y control de higiene en niños', 1.5, 1, '2014-12-12 02:36:09', '0000-00-00 00:00:00'),
(226, 20, 'Aplicación de flúor barniz total', 5, 1, '2014-12-12 02:36:16', '0000-00-00 00:00:00'),
(227, 20, 'Aplicación de sellante en pieza temporal', 1.5, 1, '2014-12-12 02:36:26', '0000-00-00 00:00:00'),
(228, 20, 'Aplicación de sellante en pieza permanente', 1.5, 1, '2014-12-12 02:36:58', '0000-00-00 00:00:00'),
(229, 21, 'Consulta', 2, 1, '2014-12-12 02:37:09', '0000-00-00 00:00:00'),
(230, 21, 'Control de niño sano', 1.5, 1, '2014-12-12 02:37:17', '0000-00-00 00:00:00'),
(231, 21, 'Adaptación a la atención odontológica', 3, 1, '2014-12-12 02:37:23', '0000-00-00 00:00:00'),
(232, 21, 'Consulta de urgencia', 2, 1, '2014-12-12 02:37:36', '0000-00-00 00:00:00'),
(233, 21, 'Educación en salud oral', 2, 1, '2014-12-12 02:37:47', '0000-00-00 00:00:00'),
(234, 21, 'Instrucción de higiene oral por sesión', 1.5, 1, '2014-12-12 02:37:55', '0000-00-00 00:00:00'),
(235, 21, 'Asesoramiento dietético para control de caries', 1, 1, '2014-12-12 02:38:04', '0000-00-00 00:00:00'),
(236, 21, 'Aplicación tópica de barniz de clorhexidina', 4, 1, '2014-12-12 02:38:13', '0000-00-00 00:00:00'),
(237, 21, 'Aplicación tópica de barniz de flúor silano', 5, 1, '2014-12-12 02:38:23', '0000-00-00 00:00:00'),
(238, 21, 'Control de sellante durante el primer año esta incluido en el valor de la consulta control', 0, 1, '2014-12-12 02:38:42', '0000-00-00 00:00:00'),
(239, 21, 'Sellante en pieza temporal o permanente por pieza', 1.5, 1, '2014-12-12 02:40:02', '0000-00-00 00:00:00'),
(240, 21, 'Inactivación de caries en cavidad bucal (4 cuadrantes)', 2, 1, '2014-12-12 02:40:11', '0000-00-00 00:00:00'),
(241, 21, 'Obturación con cementos intermedios en piezas temporal y definitiva', 1.5, 1, '2014-12-12 02:40:19', '0000-00-00 00:00:00'),
(242, 21, 'Técnica ART modificada por pieza', 2, 1, '2014-12-12 02:40:49', '0000-00-00 00:00:00'),
(243, 21, 'Obturaciones con mínima intervención', 3, 1, '2014-12-12 02:41:08', '0000-00-00 00:00:00'),
(244, 21, 'Obturaciones de cemento de vidrio ionómero de fotocurado en piezas temporales y permanentes', 2.5, 1, '2014-12-12 02:41:22', '0000-00-00 00:00:00'),
(245, 22, 'Pieza temporal anterior simple', 2.5, 1, '2014-12-12 02:41:40', '0000-00-00 00:00:00'),
(246, 22, 'Pieza temporal anterior compuesta', 3, 1, '2014-12-12 02:41:51', '0000-00-00 00:00:00'),
(247, 22, 'Pieza temporal posterior simple', 2.5, 1, '2014-12-12 02:42:05', '0000-00-00 00:00:00'),
(248, 22, 'Pieza temporal posterior compuesta', 3, 1, '2014-12-12 02:42:16', '0000-00-00 00:00:00'),
(249, 22, 'Restauración de fractura coronaria con resina compuesta pieza temporal', 3, 1, '2014-12-12 02:42:49', '0000-00-00 00:00:00'),
(250, 22, 'Restauración de fractura coronaria con resina compuesta pieza permanente', 4, 1, '2014-12-12 02:43:12', '0000-00-00 00:00:00'),
(251, 23, 'Simple en pieza temporal o permanente', 2, 1, '2014-12-12 02:43:34', '0000-00-00 00:00:00'),
(252, 23, 'Compuesta en pieza temporal o permanente', 2.5, 1, '2014-12-12 02:43:42', '0000-00-00 00:00:00'),
(253, 24, 'Pulpotomía en pieza temporal', 2, 1, '2014-12-12 02:44:12', '0000-00-00 00:00:00'),
(254, 24, 'Recubrimiento pulpar directo en piezas permanente', 2.5, 1, '2014-12-12 02:44:34', '0000-00-00 00:00:00'),
(255, 24, 'Recubrimiento pulpar indirecto en pieza permanente', 2.5, 1, '2014-12-12 02:44:46', '0000-00-00 00:00:00'),
(256, 25, 'Inmovilización en traumatismo', 5, 1, '2014-12-12 02:45:01', '0000-00-00 00:00:00'),
(257, 25, 'Exodoncia simple piezas temporales (incluye un control post-operatorio)', 2.5, 1, '2014-12-12 02:45:16', '0000-00-00 00:00:00'),
(258, 25, 'Exodoncia simple piezas permanentes (incluye un control post-operatorio)', 3, 1, '2014-12-12 02:45:36', '0000-00-00 00:00:00'),
(259, 26, 'Interconsulta con odontopediatra', 2.5, 1, '2014-12-12 02:45:54', '0000-00-00 00:00:00'),
(260, 26, 'Adaptación y/o preparación de pacientes con necesidades especiales en salud', 3, 1, '2014-12-12 02:46:13', '0000-00-00 00:00:00'),
(261, 26, 'Adaptación de paciente de difícil manejo sesión', 1.5, 1, '2014-12-12 02:46:23', '0000-00-00 00:00:00'),
(262, 26, 'Atención con sedación por sesión (máximo 4 sesiones)', 3, 1, '2014-12-12 02:47:34', '0000-00-00 00:00:00'),
(263, 26, 'Atención con óxido nitroso por sesión', 0, 1, '2014-12-12 02:47:48', '0000-00-00 00:00:00'),
(264, 26, 'Atención con anestesia general', 0, 1, '2014-12-12 02:47:57', '0000-00-00 00:00:00'),
(265, 26, 'Programa terapéutico para paciente de alto riesgo cariogénico o para pacientes especiales (2 sesiones)', 7, 1, '2014-12-12 02:48:13', '0000-00-00 00:00:00'),
(266, 26, 'Programa de remineralización de caries incipientes por sesión (incluye rx diagnósticas - máx. 4 sesiones)', 2, 1, '2014-12-12 02:48:27', '0000-00-00 00:00:00'),
(267, 27, 'Coronas de resinas con anclaje sector anterior en pieza temporal', 3, 1, '2014-12-12 02:48:47', '0000-00-00 00:00:00'),
(268, 27, 'Corona metálica preformada en pieza temporal ', 5, 1, '2014-12-12 02:49:06', '0000-00-00 00:00:00'),
(269, 27, 'Prótesis en niños (incluye laboratorio)', 7, 1, '2014-12-12 02:49:16', '0000-00-00 00:00:00'),
(270, 27, 'Tratamiento de piezas dentarias definitivas anteriores con alt. de estructura', 5, 1, '2014-12-12 02:49:33', '0000-00-00 00:00:00'),
(271, 27, 'Tratamiento de piezas dentarias definitivas posteriores con alt. De estructura', 5, 1, '2014-12-12 02:49:44', '0000-00-00 00:00:00'),
(272, 28, 'Pulpectomía en pieza temporal anterior ', 2.5, 1, '2014-12-12 02:50:19', '0000-00-00 00:00:00'),
(273, 28, 'Pulpectomía en pieza temporal posterior', 3, 1, '2014-12-12 02:50:25', '0000-00-00 00:00:00'),
(274, 28, 'Tratamiento pieza temporal desvitalizada anterior', 3, 1, '2014-12-12 02:50:33', '0000-00-00 00:00:00'),
(275, 28, 'Tratamiento pieza temporal desvitalizada posterior', 4, 1, '2014-12-12 02:50:40', '0000-00-00 00:00:00'),
(276, 28, 'Pulpotomía vital (pieza permanente joven)', 3.5, 1, '2014-12-12 02:50:47', '0000-00-00 00:00:00'),
(277, 28, 'Recubrimiento pulpar directo en pieza permanente joven', 2.5, 1, '2014-12-12 02:50:56', '0000-00-00 00:00:00'),
(278, 28, 'Recubrimiento pulpar indirecto en pieza permanente joven', 2.5, 1, '2014-12-12 02:51:06', '0000-00-00 00:00:00'),
(279, 28, 'Trepanación de urgencia en pieza temporal', 2.5, 1, '2014-12-12 02:51:16', '0000-00-00 00:00:00'),
(280, 28, 'Trepanación de urgencia en pieza permanente joven', 2.5, 1, '2014-12-12 02:51:29', '0000-00-00 00:00:00'),
(281, 29, 'Desgastes interferencias cúspideas', 2.5, 1, '2014-12-12 02:51:51', '0000-00-00 00:00:00'),
(282, 29, 'Mantenedor de espacio fijo (no incluye laboratorio) ', 4, 1, '2014-12-12 02:52:00', '0000-00-00 00:00:00'),
(283, 29, 'Mantenedor de espacio removible (no incluye laboratorio)', 4, 1, '2014-12-12 02:52:11', '0000-00-00 00:00:00'),
(284, 29, 'Tratamiento de mordida invertida simple ( sin aparato )', 3, 1, '2014-12-12 02:52:22', '0000-00-00 00:00:00'),
(285, 29, 'Tratamiento de mordida invertida simple ( con aparato, no incluye laboratorio) ', 7, 1, '2014-12-12 02:52:39', '0000-00-00 00:00:00'),
(286, 29, 'Estudio del paciente para ortodoncia interceptiva (modelos-radiografías-fotografías)', 4, 1, '2014-12-12 02:52:55', '0000-00-00 00:00:00'),
(287, 29, 'Tratamiento de mordida cruzada lateral dentición temporal (no incluye laboratorio)', 7, 1, '2014-12-12 02:53:11', '0000-00-00 00:00:00'),
(288, 29, 'Tratamiento de mordida abierta por mal hábito (no incluye laboratorio)', 7, 1, '2014-12-12 02:53:25', '0000-00-00 00:00:00'),
(289, 29, 'Todo tratamiento que considere aparato debe incluir controles por sesiones (máximo 6)', 1.5, 1, '2014-12-12 02:53:39', '0000-00-00 00:00:00'),
(290, 30, 'Fenestraciones mucosas', 5, 1, '2014-12-12 02:53:54', '0000-00-00 00:00:00'),
(291, 30, 'Fenestraciones óseas', 7, 1, '2014-12-12 02:54:03', '0000-00-00 00:00:00'),
(292, 30, 'Resección de frenillos', 7, 1, '2014-12-12 02:54:09', '0000-00-00 00:00:00'),
(293, 30, 'Exodoncia de supernumerario incluido', 8, 1, '2014-12-12 02:54:22', '0000-00-00 00:00:00'),
(294, 31, 'Primera consulta, examen y diagnóstico preliminar', 2, 1, '2014-12-12 02:55:01', '0000-00-00 00:00:00'),
(295, 31, 'Sellantes de resina o vidrio ionómero', 2, 1, '2014-12-12 02:55:09', '0000-00-00 00:00:00'),
(296, 31, 'Sensibilidad cervical medicamentosa por pieza dent.', 1, 1, '2014-12-12 02:55:18', '0000-00-00 00:00:00'),
(297, 31, 'Tratamiento sensibilidad cervical sin cavidad con vidrios ionómeros, barnices.', 2.5, 1, '2014-12-12 02:55:31', '0000-00-00 00:00:00'),
(298, 32, 'Restauraciones Clase I (una cara) ', 4, 1, '2014-12-12 02:55:47', '0000-00-00 00:00:00'),
(299, 32, 'Restauraciones Clase II (dos caras)', 4.5, 1, '2014-12-12 02:55:56', '0000-00-00 00:00:00'),
(300, 32, 'Restauraciones Clase II (tres caras)', 5.5, 1, '2014-12-12 02:56:04', '0000-00-00 00:00:00'),
(301, 32, 'Restauraciones Clase III (dos caras)', 4, 1, '2014-12-12 02:56:12', '0000-00-00 00:00:00'),
(302, 32, 'Restauraciones Clase III (tres caras)', 4.5, 1, '2014-12-12 02:56:20', '0000-00-00 00:00:00'),
(303, 32, 'Restauraciones Clase IV (compromiso 1 ángulo)', 5, 1, '2014-12-12 02:56:28', '0000-00-00 00:00:00'),
(304, 32, 'Restauraciones Clase IV (compromiso 2 ángulos con borde incisal)', 6, 1, '2014-12-12 02:56:43', '0000-00-00 00:00:00'),
(305, 32, 'Restauraciones Clase V lesiones por caries, erosiones, abfracciones', 4, 1, '2014-12-12 02:57:03', '0000-00-00 00:00:00'),
(306, 33, 'Restauraciones (Clase I) 1 Cara', 3, 1, '2014-12-12 02:57:19', '0000-00-00 00:00:00'),
(307, 33, 'Restauraciones (Clase II) 2 Caras', 3.5, 1, '2014-12-12 02:57:27', '0000-00-00 00:00:00'),
(308, 33, 'Restauraciones (Clase II) compleja', 4.5, 1, '2014-12-12 02:57:36', '0000-00-00 00:00:00'),
(309, 34, 'Inlay Metálico Una Cara', 4, 1, '2014-12-12 02:58:16', '0000-00-00 00:00:00'),
(310, 34, 'Inlay Metálico Dos Caras', 6, 1, '2014-12-12 02:58:24', '0000-00-00 00:00:00'),
(311, 34, 'Inlay Metálico Tres Caras', 7, 1, '2014-12-12 02:58:31', '0000-00-00 00:00:00'),
(312, 34, 'Onlay U Overlay', 7.5, 1, '2014-12-12 02:58:42', '0000-00-00 00:00:00'),
(313, 35, 'Reobturaciones de piezas antes de un año', 1.5, 1, '2014-12-12 02:59:42', '0000-00-00 00:00:00'),
(314, 35, 'Recementación de incrustación sin correcciones', 2, 1, '2014-12-12 02:59:49', '0000-00-00 00:00:00'),
(315, 35, 'Recementación Incrustación Con Ajuste Operatorio', 3, 1, '2014-12-12 02:59:55', '0000-00-00 00:00:00'),
(316, 35, 'Recubrimiento Pulpar Indirecto + Curac. Temporal', 2, 1, '2014-12-12 03:00:02', '0000-00-00 00:00:00'),
(317, 35, 'Recementación de incrustación tipo Inlay', 6, 1, '2014-12-12 03:00:09', '0000-00-00 00:00:00'),
(318, 35, 'Recementación de incrustación tipo Onlay', 7, 1, '2014-12-12 03:00:18', '0000-00-00 00:00:00'),
(319, 36, 'Cerómeros Inlay', 8, 1, '2014-12-12 03:01:16', '0000-00-00 00:00:00'),
(320, 36, 'Cerómeros Onlay u Overlay', 9, 1, '2014-12-12 03:01:27', '0000-00-00 00:00:00'),
(321, 36, 'Porcelana Inlay', 9, 1, '2014-12-12 03:01:34', '0000-00-00 00:00:00'),
(322, 36, 'Porcelana Onlay u Overlay', 9, 1, '2014-12-12 03:01:41', '0000-00-00 00:00:00'),
(323, 36, 'Carillas indirectas', 8, 1, '2014-12-12 03:01:48', '0000-00-00 00:00:00'),
(324, 37, 'Cierre de diastema (por pieza)', 4, 1, '2014-12-12 03:02:14', '0000-00-00 00:00:00'),
(325, 37, 'Carillas directas', 4.5, 1, '2014-12-12 03:02:25', '0000-00-00 00:00:00'),
(326, 37, 'Ferulizaciones de un diente', 3, 1, '2014-12-12 03:02:34', '0000-00-00 00:00:00'),
(327, 37, 'Ferulizaciones de más de un diente cada uno', 2.5, 1, '2014-12-12 03:02:43', '0000-00-00 00:00:00'),
(328, 37, 'Reconstitución de corona provisoria sobre muñón de resina', 3.5, 1, '2014-12-12 03:02:53', '0000-00-00 00:00:00'),
(329, 37, 'Remodelación de pieza dentaria con corona atípica', 5, 1, '2014-12-12 03:03:02', '0000-00-00 00:00:00'),
(330, 37, 'Recuperación de guía canina por pieza', 4, 1, '2014-12-12 03:03:09', '0000-00-00 00:00:00'),
(331, 37, 'Reconstrucción provisoria de corona perdida', 4.5, 1, '2014-12-12 03:03:17', '0000-00-00 00:00:00'),
(332, 37, 'Cementación y colocación de perno técnica adhesiva', 5, 1, '2014-12-12 03:03:25', '0000-00-00 00:00:00'),
(333, 37, 'Confección de muñón de resina', 5, 1, '2014-12-12 03:03:31', '0000-00-00 00:00:00'),
(334, 37, 'Corona de cerómero muñón vital', 9.5, 1, '2014-12-12 03:03:40', '0000-00-00 00:00:00'),
(335, 37, 'Corona cerámica muñón vital', 10, 1, '2014-12-12 03:03:49', '0000-00-00 00:00:00'),
(336, 37, 'Corona metal cerámica', 11, 1, '2014-12-12 03:03:57', '0000-00-00 00:00:00'),
(337, 37, 'Tratamiento de remineralizaciones, hipocalcificaciones medicamentosas, por sesión', 3, 1, '2014-12-12 03:04:05', '0000-00-00 00:00:00'),
(338, 37, 'Blanqueamiento de pieza permanente, tto químico, intra cameral por sesión', 3.5, 1, '2014-12-12 03:04:40', '0000-00-00 00:00:00'),
(339, 37, 'Blanqueamiento por cuadrante', 3.5, 1, '2014-12-12 03:04:53', '0000-00-00 00:00:00'),
(340, 38, 'Primera consulta, examen y diagnóstico preliminar', 2.4, 1, '2014-12-12 03:08:10', '0000-00-00 00:00:00'),
(341, 38, 'Control de evolución', 2.4, 1, '2014-12-12 03:08:20', '0000-00-00 00:00:00'),
(342, 38, 'Urgencia ortodóncica', 1.65, 1, '2014-12-12 03:08:29', '0000-00-00 00:00:00'),
(343, 38, 'Examen clínico, estudio de modelos y radiográficos, diagnóstico plan de tratamiento, presupuesto y estimación de tiempo ', 6, 1, '2014-12-12 03:08:44', '0000-00-00 00:00:00'),
(344, 38, 'Reestudios con modelos', 6, 1, '2014-12-12 03:09:21', '0000-00-00 00:00:00'),
(345, 38, 'Deprogramación mediante Jig', 13.2, 1, '2014-12-12 03:09:30', '0000-00-00 00:00:00'),
(346, 38, 'Estabilización mandibular mediante plano deprogramador (mínimo 3 meses)', 36.3, 1, '2014-12-12 03:10:00', '0000-00-00 00:00:00'),
(347, 38, 'Estudio cefalométrico', 2.4, 1, '2014-12-12 03:10:08', '0000-00-00 00:00:00'),
(348, 38, 'SET-UP', 8.8, 1, '2014-12-12 03:10:16', '0000-00-00 00:00:00'),
(349, 38, 'Montaje en articulador', 6, 1, '2014-12-12 03:10:25', '0000-00-00 00:00:00'),
(350, 38, 'Montaje en articulador', 3.1, 1, '2014-12-12 03:10:33', '0000-00-00 00:00:00'),
(351, 38, 'Predicción quirúrgica ortognática', 12.1, 1, '2014-12-12 03:10:42', '0000-00-00 00:00:00'),
(352, 39, 'Tratamiento con aparatos fijos, presupuesto semestral', 30, 1, '2014-12-12 03:11:02', '0000-00-00 00:00:00'),
(353, 39, 'Tratamiento con aparatos removibles, presupuesto semestral', 24.7, 1, '2014-12-12 03:11:17', '0000-00-00 00:00:00'),
(354, 39, 'Tratamiento adulto, presupuesto semestral', 38.5, 1, '2014-12-12 03:11:32', '0000-00-00 00:00:00'),
(355, 39, 'Tratamiento Ortodóncico quirúrgico semestral', 38.5, 1, '2014-12-12 03:11:46', '0000-00-00 00:00:00'),
(356, 39, 'Tratamiento con aparatos fijos técnica lingual, presupuesto semestral', 49, 1, '2014-12-12 03:12:05', '0000-00-00 00:00:00'),
(357, 39, 'Tratamiento Invisalign, presupuesto semestral', 50, 1, '2014-12-12 03:12:14', '0000-00-00 00:00:00'),
(358, 39, 'Tratamiento clear aligner, presupuesto semestral', 30, 1, '2014-12-12 03:12:24', '0000-00-00 00:00:00'),
(359, 39, 'Tratamiento de contención presupuesto semestral', 15.4, 1, '2014-12-12 03:12:36', '0000-00-00 00:00:00'),
(360, 40, 'Tratamiento con aparatos fijos, presupuesto anual', 60, 1, '2014-12-12 03:12:57', '0000-00-00 00:00:00'),
(361, 40, 'Tratamiento con aparatos removibles, presupuesto anual', 49.5, 1, '2014-12-12 03:13:10', '0000-00-00 00:00:00'),
(362, 40, 'Tratamiento adulto, presupuesto anual', 77, 1, '2014-12-12 03:13:43', '0000-00-00 00:00:00'),
(363, 40, 'Tratamiento Ortodóncico quirúrgico', 77, 1, '2014-12-12 03:13:49', '0000-00-00 00:00:00'),
(364, 40, 'Tratamiento con aparatos fijos técnica lingual, presupuesto anual', 100, 1, '2014-12-12 03:14:03', '0000-00-00 00:00:00'),
(365, 40, 'Tratamiento de contención, presupuesto anual', 31, 1, '2014-12-12 03:14:11', '0000-00-00 00:00:00'),
(366, 40, 'Tratamiento clear aligner, presupuesto anual', 60, 1, '2014-12-12 03:14:18', '0000-00-00 00:00:00'),
(367, 40, 'Tratamiento invisalign, presupuesto anual', 100, 1, '2014-12-12 03:14:25', '0000-00-00 00:00:00'),
(368, 41, 'Aparatos fijos técnica estándar', 18.1, 1, '2014-12-12 03:14:55', '0000-00-00 00:00:00'),
(369, 41, 'Aparatos fijos técnica arco recto (programada)', 24, 1, '2014-12-12 03:15:06', '0000-00-00 00:00:00'),
(370, 41, 'Aparatos fijos técnica arco recto (programada) autoligante', 27.5, 1, '2014-12-12 03:15:18', '0000-00-00 00:00:00'),
(371, 41, 'Técnica arco recto con slot vertical (Tip edge)', 27.5, 1, '2014-12-12 03:15:42', '0000-00-00 00:00:00'),
(372, 41, 'Aparatos fijos tratamiento parcial fijo con aparatos cerámicos', 36.3, 1, '2014-12-12 03:16:04', '0000-00-00 00:00:00'),
(373, 41, 'Aparatos fijos técnica lingual, sin costo de laboratorio', 49.5, 1, '2014-12-12 03:16:22', '0000-00-00 00:00:00'),
(374, 41, 'Aparatos removibles costo por cada arco, instalación sin costo de laboratorio', 6, 1, '2014-12-12 03:16:34', '0000-00-00 00:00:00'),
(375, 41, 'Disyuntor cementado y esquelético Hyrax, instalación sin costo de laboratorio', 6, 1, '2014-12-12 03:16:48', '0000-00-00 00:00:00'),
(376, 41, 'Instalación, evaluación y retiro de micro-tornillos (más insumos)', 4, 1, '2014-12-12 03:17:03', '0000-00-00 00:00:00'),
(377, 41, 'Arco transpalatino o lingual instalación sin costo de laboratorio', 3.85, 1, '2014-12-12 03:17:17', '0000-00-00 00:00:00'),
(378, 41, 'Mentonera', 3.85, 1, '2014-12-12 03:17:29', '0000-00-00 00:00:00'),
(379, 41, 'Fuerza Extra-oral', 3.85, 1, '2014-12-12 03:17:40', '0000-00-00 00:00:00'),
(380, 41, 'Quad Hélix instalación, sin costo de laboratorio', 6.6, 1, '2014-12-12 03:17:51', '0000-00-00 00:00:00'),
(381, 41, 'Instalación propulsor mandibular tipo Herbst, Forsus, asper Jumper, etc ', 12.1, 1, '2014-12-12 03:18:14', '0000-00-00 00:00:00'),
(382, 41, 'Contención estampada', 3.85, 1, '2014-12-12 03:18:24', '0000-00-00 00:00:00'),
(383, 41, 'Contención rígida lingual o palatina', 3.85, 1, '2014-12-12 03:18:33', '0000-00-00 00:00:00'),
(384, 41, 'Arco facial', 3.85, 1, '2014-12-12 03:18:46', '0000-00-00 00:00:00'),
(385, 41, 'Mascara Delaire', 24, 1, '2014-12-12 03:18:56', '0000-00-00 00:00:00'),
(386, 41, 'Reposición de brackets metálicos', 1.65, 1, '2014-12-12 03:19:12', '0000-00-00 00:00:00'),
(387, 41, 'Reposición de brackets Tip edge', 2, 1, '2014-12-12 03:19:18', '0000-00-00 00:00:00'),
(388, 41, 'Reposición de brackets cerámicos', 2, 1, '2014-12-12 03:19:28', '0000-00-00 00:00:00'),
(389, 42, 'Reposición de brackets lingual', 2.5, 1, '2014-12-12 03:19:48', '0000-00-00 00:00:00'),
(390, 42, 'Reposición de bandas con tubo de arco recto', 3.3, 1, '2014-12-12 03:19:55', '0000-00-00 00:00:00'),
(391, 42, 'Reposición de arcos', 1.65, 1, '2014-12-12 03:20:04', '0000-00-00 00:00:00'),
(392, 42, 'Reparación de contención ortodóncica', 2, 1, '2014-12-12 03:20:11', '0000-00-00 00:00:00'),
(393, 42, 'Cementación de aditamentos (no incluye el costo del aditamento)', 1, 1, '2014-12-12 03:20:31', '0000-00-00 00:00:00'),
(394, 42, 'Higienización por cada arcada (retiro de composite sobre esmalte)', 4, 1, '2014-12-12 03:20:44', '0000-00-00 00:00:00'),
(395, 42, 'Retiro de aparatos fijos por arcada', 8, 1, '2014-12-12 03:20:57', '0000-00-00 00:00:00'),
(396, 43, 'Examen especialidad', 3, 1, '2014-12-12 03:21:38', '0000-00-00 00:00:00'),
(397, 43, 'Controles por sesión', 1.5, 1, '2014-12-12 03:21:47', '0000-00-00 00:00:00'),
(398, 43, 'Biopsia tejidos blandos (no incluye pabellón)', 6, 1, '2014-12-12 03:21:55', '0000-00-00 00:00:00'),
(399, 43, 'Biopsia tejidos duros (no incluye pabellón)', 8, 1, '2014-12-12 03:22:02', '0000-00-00 00:00:00'),
(400, 43, 'Frotis, sialometría u otros procedimientos menores', 2, 1, '2014-12-12 03:22:07', '0000-00-00 00:00:00'),
(401, 43, 'Tinciones clínicas u otros (se debe sumar a anteriores)', 2, 1, '2014-12-12 03:22:14', '0000-00-00 00:00:00'),
(402, 43, 'Estudio histopatológico T. Convencional (no incluye laboratorio)', 3, 1, '2014-12-12 03:22:28', '0000-00-00 00:00:00'),
(403, 43, 'Otras tinciones o técnicas adicionales, sumar a ítem anterior (no incluye laboratorio)', 2, 1, '2014-12-12 03:22:40', '0000-00-00 00:00:00'),
(404, 44, 'Limpieza coronaria (eliminación de pigmentos y manchas de esmalte', 3.5, 1, '2014-12-12 03:23:16', '0000-00-00 00:00:00'),
(405, 44, 'Eliminación mecánica de factores etiológicos secundarios por sesión', 4, 1, '2014-12-12 03:23:30', '0000-00-00 00:00:00'),
(406, 44, 'Evaluación post-terapeútica (con periodontograma final e índices) ', 3.5, 1, '2014-12-12 03:23:45', '0000-00-00 00:00:00'),
(407, 45, 'Tratamiento químico del saco periodental por sextante (no incluye valor materiales)', 4, 1, '2014-12-12 03:24:08', '0000-00-00 00:00:00'),
(408, 46, 'Pulido radicular incipientes a moderadas por sesión o sextante', 5, 1, '2014-12-12 03:24:29', '0000-00-00 00:00:00'),
(409, 46, 'Pulido radicular moderadas avanzadas por sesión o sextante', 6, 1, '2014-12-12 03:24:44', '0000-00-00 00:00:00'),
(410, 46, 'Pulido radicular avanzadas y complejas por sesión o sextante', 7, 1, '2014-12-12 03:24:58', '0000-00-00 00:00:00'),
(411, 46, 'Ferulización (no incluye material ni laboratorio) ', 5, 1, '2014-12-12 03:25:32', '0000-00-00 00:00:00'),
(412, 46, 'Tratamiento infección peri-implantaria, por sesión ', 6.5, 1, '2014-12-12 03:25:40', '0000-00-00 00:00:00'),
(413, 47, 'Gingivoplastía en pacientes sin compromiso general, por sextante', 7, 1, '2014-12-12 03:26:02', '0000-00-00 00:00:00'),
(414, 47, 'Gingivoplastía en pacientes con enfermedades generales por sextante', 10.5, 1, '2014-12-12 03:26:17', '0000-00-00 00:00:00'),
(415, 47, 'Control postquirúrgico o recambio de cemento quirúrgico', 2, 1, '2014-12-12 03:26:25', '0000-00-00 00:00:00'),
(416, 47, 'Disección quirúrgica del injerto en zona donante', 5, 1, '2014-12-12 03:26:32', '0000-00-00 00:00:00'),
(417, 47, 'Injerto gingival libre por zona a injertar', 9, 1, '2014-12-12 03:26:39', '0000-00-00 00:00:00'),
(418, 47, 'Colgajo periodontal de acceso, por sitio quirúrgico', 7, 1, '2014-12-12 03:26:47', '0000-00-00 00:00:00'),
(419, 47, 'Colgajo reposicionado desplazado lateral coronario o apical', 8, 1, '2014-12-12 03:26:57', '0000-00-00 00:00:00'),
(420, 47, 'Colgajo para preservación de papila', 9, 1, '2014-12-12 03:27:06', '0000-00-00 00:00:00'),
(421, 47, 'Radectomía u odonto-sección por pieza (no incluye colgajo de acceso)', 4, 1, '2014-12-12 03:27:18', '0000-00-00 00:00:00'),
(422, 47, 'Cirugía ósea resectiva, por sextante', 8, 1, '2014-12-12 03:27:44', '0000-00-00 00:00:00'),
(423, 47, 'Curetaje o debridamiento sub-gingival por grupo', 7, 1, '2014-12-12 03:28:05', '0000-00-00 00:00:00'),
(424, 47, 'Cobertura acrílica post-operatoria', 5, 1, '2014-12-12 03:28:14', '0000-00-00 00:00:00'),
(425, 47, 'Control post-quirúrgico con o sin retiro de puntos', 2, 1, '2014-12-12 03:28:25', '0000-00-00 00:00:00'),
(426, 47, 'Tratamiento de complicaciones post-quirúrgica, por sesión', 5, 1, '2014-12-12 03:28:33', '0000-00-00 00:00:00'),
(427, 47, 'Técnica de tunelización para cubrimiento radicular (por grupo)', 10, 1, '2014-12-12 03:28:51', '0000-00-00 00:00:00'),
(428, 47, 'Injertos óseos, no incluye colgajo de acceso', 10, 1, '2014-12-12 03:29:02', '0000-00-00 00:00:00'),
(429, 47, 'Injertos aloplásticos, no incluye material ni colgajo', 5, 1, '2014-12-12 03:29:10', '0000-00-00 00:00:00'),
(430, 47, 'Cirugía peri-implantaria de manejo de tejidos blandos', 9, 1, '2014-12-12 03:29:16', '0000-00-00 00:00:00'),
(431, 47, 'Regeneración tisular guiada por sitio, no incluye valor de membrana', 10, 1, '2014-12-12 03:29:29', '0000-00-00 00:00:00'),
(432, 47, 'Freenctomía', 7, 1, '2014-12-12 03:29:38', '0000-00-00 00:00:00'),
(433, 47, 'Otras cirugías muco-gingivales', 7, 1, '2014-12-12 03:29:47', '0000-00-00 00:00:00'),
(434, 47, 'Injerto de tejido conectivo, por zona', 8.5, 1, '2014-12-12 03:29:56', '0000-00-00 00:00:00'),
(435, 47, 'Avance de colgajo, incisión perióstica', 8, 1, '2014-12-12 03:30:05', '0000-00-00 00:00:00'),
(436, 47, 'Implante Óseo-integrado (no incluye valor del implante', 28, 1, '2014-12-12 03:30:12', '0000-00-00 00:00:00'),
(437, 47, 'Tunelización radicular en compromiso de furca', 10, 1, '2014-12-12 03:30:21', '0000-00-00 00:00:00'),
(438, 48, 'Aplicación de flúor gel por sextante', 2, 1, '2014-12-12 03:30:39', '0000-00-00 00:00:00'),
(439, 48, 'Tratamiento de hipersensibilidad dentaria post-terapia, por sesión', 4, 1, '2014-12-12 03:30:55', '0000-00-00 00:00:00'),
(440, 48, 'Refuerzo de técnicas de higiene y motivación', 3, 1, '2014-12-12 03:31:03', '0000-00-00 00:00:00'),
(441, 48, 'Interconsulta con informe escrito', 4, 1, '2014-12-12 03:31:09', '0000-00-00 00:00:00'),
(442, 48, 'Terapia de mantención periodontal en enfermedad inactiva por sesión', 5, 1, '2014-12-12 03:31:26', '0000-00-00 00:00:00'),
(443, 48, 'Terapia de mantención con pulido radicular y tratamiento de infección', 7, 1, '2014-12-12 03:31:41', '0000-00-00 00:00:00'),
(444, 48, 'Terapia de mantención de implantes por sesión', 5.5, 1, '2014-12-12 03:31:58', '0000-00-00 00:00:00'),
(445, 49, 'Primera consulta, examen y diagnóstico preliminar', 1.5, 1, '2014-12-12 03:32:27', '0000-00-00 00:00:00'),
(446, 49, 'Control de evolución', 1.5, 1, '2014-12-12 03:32:53', '0000-00-00 00:00:00'),
(447, 49, 'Examen clínico, estudio de modelos y radiográfico, diagnóstico, plan de tratamiento, presupuesto y estimación de tiempo', 3.5, 1, '2014-12-12 03:33:18', '0000-00-00 00:00:00'),
(448, 49, 'Montaje en articulador', 4, 1, '2014-12-12 03:33:25', '0000-00-00 00:00:00'),
(449, 49, 'Carillas indirectas', 6, 1, '2014-12-12 03:33:30', '0000-00-00 00:00:00'),
(450, 49, 'Coronas provisorias de acrílico o policarbonato', 4.5, 1, '2014-12-12 03:33:39', '0000-00-00 00:00:00'),
(451, 49, 'Corona metálica completa', 13, 1, '2014-12-12 03:33:47', '0000-00-00 00:00:00');
INSERT INTO `tratamiento` (`id_tratamiento`, `id_categoria2`, `nombre`, `cantidad_uco`, `activo`, `created_at`, `update_at`) VALUES
(452, 49, 'Corona metálica tres cuartos', 13, 1, '2014-12-12 03:33:54', '0000-00-00 00:00:00'),
(453, 49, 'Corona de porcelana sobre casquete cerámico', 14, 1, '2014-12-12 03:34:04', '0000-00-00 00:00:00'),
(454, 49, 'Corona periférica de porcelana sobre metal', 18, 1, '2014-12-12 03:34:15', '0000-00-00 00:00:00'),
(455, 49, 'Corona periférica completa sobre núcleo cerámico o estético', 19, 1, '2014-12-12 03:34:23', '0000-00-00 00:00:00'),
(456, 49, 'Espiga muñón colado simple', 6, 1, '2014-12-12 03:34:32', '0000-00-00 00:00:00'),
(457, 49, 'Espiga muñón prefabricado (no incluye valor espiga)', 5, 1, '2014-12-12 03:34:51', '0000-00-00 00:00:00'),
(458, 50, 'Primera consulta, examen y diagnóstico preliminar', 2, 1, '2014-12-12 03:35:16', '0000-00-00 00:00:00'),
(459, 50, 'Control por la especialidad', 2, 1, '2014-12-12 03:35:21', '0000-00-00 00:00:00'),
(460, 50, 'Examen clínico, estudio de modelos y radiográfico diagnóstico, plan de tratamiento, presupuesto y estimación de tiempo', 5, 1, '2014-12-12 03:35:53', '0000-00-00 00:00:00'),
(461, 50, 'Deprogramación mediante jig', 5, 1, '2014-12-12 03:36:02', '0000-00-00 00:00:00'),
(462, 50, 'Estabilización mandibular mediante plano deprogramador (mínimo tres meses)', 3, 1, '2014-12-12 03:36:16', '0000-00-00 00:00:00'),
(463, 50, 'Montaje en articulador', 5, 1, '2014-12-12 03:36:24', '0000-00-00 00:00:00'),
(464, 50, 'Encerado de diagnóstico', 5, 1, '2014-12-12 03:36:31', '0000-00-00 00:00:00'),
(465, 50, 'Mock-Up', 7, 1, '2014-12-12 03:36:42', '0000-00-00 00:00:00'),
(466, 50, 'Coronas provisorias de acrílico o policarbonato', 5, 1, '2014-12-12 03:36:53', '0000-00-00 00:00:00'),
(467, 50, 'Corona metálica completa', 17, 1, '2014-12-12 03:37:02', '0000-00-00 00:00:00'),
(468, 50, 'Corona metálica tres cuartos', 17, 1, '2014-12-12 03:37:10', '0000-00-00 00:00:00'),
(469, 50, 'Corona periférica sobre casquete metálico', 18, 1, '2014-12-12 03:37:18', '0000-00-00 00:00:00'),
(470, 50, 'Corona periférica sobre perno muñón', 18, 1, '2014-12-12 03:37:26', '0000-00-00 00:00:00'),
(471, 50, 'Corona de porcelana sobre casquete cerámico', 19.5, 1, '2014-12-12 03:37:55', '0000-00-00 00:00:00'),
(472, 50, 'Corona de porcelana completa sobre núcleo metálico', 18, 1, '2014-12-12 03:38:03', '0000-00-00 00:00:00'),
(473, 50, 'Espiga muñón colado simple', 8, 1, '2014-12-12 03:38:13', '0000-00-00 00:00:00'),
(474, 50, 'Espiga muñón bipartito o pasante', 8, 1, '2014-12-12 03:38:21', '0000-00-00 00:00:00'),
(475, 50, 'Espiga muñón prefabricado (no incluye valor espiga) ', 8, 1, '2014-12-12 03:38:29', '0000-00-00 00:00:00'),
(476, 50, 'Tallado de retenciones adicionales (cajas, rieleras etc.)', 2, 1, '2014-12-12 03:38:38', '0000-00-00 00:00:00'),
(477, 50, 'Retirar espiga metálica del conducto', 4.5, 1, '2014-12-12 03:38:48', '0000-00-00 00:00:00'),
(478, 50, 'Retirar prótesis fijas existentes', 2, 1, '2014-12-12 03:38:56', '0000-00-00 00:00:00'),
(479, 50, 'Carillas indirectas', 8, 1, '2014-12-12 03:39:03', '0000-00-00 00:00:00'),
(480, 51, 'Corona inserción prótesis fija plural', 15, 1, '2014-12-12 03:39:23', '0000-00-00 00:00:00'),
(481, 51, 'Incrustación metálica como inserción PFP', 8, 1, '2014-12-12 03:39:31', '0000-00-00 00:00:00'),
(482, 51, 'Corona como intermediario en P. Fija', 10, 1, '2014-12-12 03:39:39', '0000-00-00 00:00:00'),
(483, 51, 'Inserciones para Puente Marylan', 7, 1, '2014-12-12 03:39:46', '0000-00-00 00:00:00'),
(484, 51, 'Intermediarios para Puente Marylan', 6, 1, '2014-12-12 03:39:54', '0000-00-00 00:00:00'),
(485, 51, 'Frente estético Marylan o porcelana', 8, 1, '2014-12-12 03:40:00', '0000-00-00 00:00:00'),
(486, 51, 'Espiga muñon colado simple', 6, 1, '2014-12-12 03:40:08', '0000-00-00 00:00:00'),
(487, 51, 'Recementación corona sin correcciones', 2, 1, '2014-12-12 03:40:15', '0000-00-00 00:00:00'),
(488, 51, 'Recementación corona con ajuste operatorio', 3, 1, '2014-12-12 03:40:23', '0000-00-00 00:00:00'),
(489, 51, 'Recementación puente def. sin correcciones', 2.5, 1, '2014-12-12 03:40:31', '0000-00-00 00:00:00'),
(490, 51, 'Recementación puente con ajuste operatorio', 4, 1, '2014-12-12 03:40:45', '0000-00-00 00:00:00'),
(491, 52, 'Corona inserción prótesis fija plural', 21, 1, '2014-12-12 03:41:20', '0000-00-00 00:00:00'),
(492, 52, 'Incrustación metálica como inserción PFP', 10.5, 1, '2014-12-12 03:41:30', '0000-00-00 00:00:00'),
(493, 52, 'Corona como intermediario en p. Fija', 14.5, 1, '2014-12-12 03:41:40', '0000-00-00 00:00:00'),
(494, 52, 'Artificios como anclajes de precisión', 3.5, 1, '2014-12-12 03:41:48', '0000-00-00 00:00:00'),
(495, 52, 'Inserciones para puente Marylan', 9, 1, '2014-12-12 03:41:55', '0000-00-00 00:00:00'),
(496, 52, 'Intermediarios para puente Marylan', 8, 1, '2014-12-12 03:42:03', '0000-00-00 00:00:00'),
(497, 52, 'Frente estético Marylan o porcelana', 10.5, 1, '2014-12-12 03:42:16', '0000-00-00 00:00:00'),
(498, 52, 'Espiga muñón colado simple', 8, 1, '2014-12-12 03:42:25', '0000-00-00 00:00:00'),
(499, 52, 'Espiga muñón colado bipartito o pasante', 8, 1, '2014-12-12 03:42:32', '0000-00-00 00:00:00'),
(500, 52, 'Espiga muñón prefabricado (no incluye valor espiga) ', 8, 1, '2014-12-12 03:42:38', '0000-00-00 00:00:00'),
(501, 53, 'Instalación de pilar sobre implantes', 6, 1, '2014-12-12 03:55:06', '0000-00-00 00:00:00'),
(502, 53, 'Coronas provisorias de acrílico o policarbonato sobre implantes', 5, 1, '2014-12-12 03:55:21', '0000-00-00 00:00:00'),
(503, 53, 'Prótesis fija sobre implante atornillada o cementada intermediario', 10, 1, '2014-12-12 03:55:35', '0000-00-00 00:00:00'),
(504, 53, 'Prótesis total implanto-soportada', 35, 1, '2014-12-12 03:55:42', '0000-00-00 00:00:00'),
(505, 53, 'Prótesis sobre implantes AD- Modum', 110, 1, '2014-12-12 03:55:50', '0000-00-00 00:00:00'),
(506, 53, 'Prótesis total removible implanto retenida (no incluye broches)', 20, 1, '2014-12-12 03:56:13', '0000-00-00 00:00:00'),
(507, 53, 'Instalación de broche (o-ring)', 16, 1, '2014-12-12 03:56:22', '0000-00-00 00:00:00'),
(508, 53, 'Barra para prótesis sobre implante', 25, 1, '2014-12-12 03:56:29', '0000-00-00 00:00:00'),
(509, 53, 'Desmontaje de prótesis y profilaxis', 8, 1, '2014-12-12 03:56:35', '0000-00-00 00:00:00'),
(510, 53, 'Recambio de O´ring o clip de barra (no incluye valor aditamentos)', 6, 1, '2014-12-12 03:56:50', '0000-00-00 00:00:00'),
(511, 53, 'Reapriete o cambio de tornillos protésicos (c/u)(no incluye valor de tornillo)', 2.5, 1, '2014-12-12 03:57:06', '0000-00-00 00:00:00'),
(512, 53, 'Cambio acondicionador de tejidos', 2, 1, '2014-12-12 03:57:14', '0000-00-00 00:00:00'),
(513, 53, 'Prótesis removible provisoria', 5, 1, '2014-12-12 03:57:24', '0000-00-00 00:00:00'),
(514, 54, 'Prótesis total superior de acrílico', 15, 1, '2014-12-12 03:58:58', '0000-00-00 00:00:00'),
(515, 54, 'Prótesis total inferior de acrílico', 17.5, 1, '2014-12-12 03:59:07', '0000-00-00 00:00:00'),
(516, 54, 'Juego de prótesis totales superior e inferior acrílico', 35, 1, '2014-12-12 03:59:15', '0000-00-00 00:00:00'),
(517, 54, 'Prótesis sup o inf parcial de base acrílica', 15, 1, '2014-12-12 03:59:21', '0000-00-00 00:00:00'),
(518, 54, 'Prótesis sup o inf parcial de base metálica', 16, 1, '2014-12-12 03:59:28', '0000-00-00 00:00:00'),
(519, 54, 'Prótesis sup o inf unilateral base acrílica', 15, 1, '2014-12-12 03:59:38', '0000-00-00 00:00:00'),
(520, 54, 'Prótesis sup o inf unilateral base metálica', 16, 1, '2014-12-12 03:59:46', '0000-00-00 00:00:00'),
(521, 55, 'Prótesis inmediata', 16, 1, '2014-12-12 04:04:36', '0000-00-00 00:00:00'),
(522, 55, 'Rebasado total', 3.5, 1, '2014-12-12 04:04:47', '0000-00-00 00:00:00'),
(523, 55, 'Reparación de prótesis con toma de impresión', 3, 1, '2014-12-12 04:05:03', '0000-00-00 00:00:00'),
(524, 55, 'Reparación de prótesis sin toma de impresión', 2, 1, '2014-12-12 04:05:15', '0000-00-00 00:00:00'),
(525, 56, 'Prótesis total superior de acrílico', 22, 1, '2014-12-12 04:10:15', '0000-00-00 00:00:00'),
(526, 56, 'Prótesis total inferior de acrílico', 24, 1, '2014-12-12 04:10:20', '0000-00-00 00:00:00'),
(527, 56, 'Juego de prótesis totales superior e inferior acrílico', 46, 1, '2014-12-12 04:10:32', '0000-00-00 00:00:00'),
(528, 56, 'Prótesis total sup o inf en maxilares con reabsorción extrema', 25, 1, '2014-12-12 04:10:53', '0000-00-00 00:00:00'),
(529, 56, 'Prótesis sup o inf parcial de base acrílica', 15, 1, '2014-12-12 04:11:23', '0000-00-00 00:00:00'),
(530, 56, 'Prótesis sup o inf parcial de base metálica', 18, 1, '2014-12-12 04:11:31', '0000-00-00 00:00:00'),
(531, 56, 'Prótesis sup o inf unilateral base acrílica', 15, 1, '2014-12-12 04:11:39', '0000-00-00 00:00:00'),
(532, 56, 'Prótesis sup o inf unilateral base metálica', 16, 1, '2014-12-12 04:11:49', '0000-00-00 00:00:00'),
(533, 56, 'Prótesis inmediata', 17, 1, '2014-12-12 04:11:56', '0000-00-00 00:00:00'),
(534, 56, 'Rebasado total', 5, 1, '2014-12-12 04:12:05', '0000-00-00 00:00:00'),
(535, 56, 'Reparación de prótesis con toma de impresión', 4, 1, '2014-12-12 04:12:12', '0000-00-00 00:00:00'),
(536, 56, 'Reparación de prótesis sin toma de impresión', 2.5, 1, '2014-12-12 04:12:21', '0000-00-00 00:00:00'),
(537, 56, 'Artificios de anclaje para prótesis de complementación', 4, 1, '2014-12-12 04:12:39', '0000-00-00 00:00:00'),
(538, 57, 'Primera consulta, examen y diagnóstico preliminar', 2, 1, '2014-12-12 04:13:23', '0000-00-00 00:00:00'),
(539, 57, 'Diagnóstico. Estudio inicial sobre modelos y plan de tratamiento', 5, 1, '2014-12-12 04:13:29', '0000-00-00 00:00:00'),
(540, 57, 'Interconsulta pre-quirúrgica para implantes', 3, 1, '2014-12-12 04:13:42', '0000-00-00 00:00:00'),
(541, 57, 'Montaje en articulador y confección de encerado diagnóstico (no incluye valor de laboratorio)', 5, 1, '2014-12-12 04:14:01', '0000-00-00 00:00:00'),
(542, 57, 'Confección de guías radiográfico quirúrgica por maxilar (no incluye valor de laboratorio)', 5, 1, '2014-12-12 04:14:20', '0000-00-00 00:00:00'),
(543, 57, 'Análisis de estudio tomográfico (rx) y registro fotográfico', 2, 1, '2014-12-12 04:14:35', '0000-00-00 00:00:00'),
(544, 57, 'Controles de especialidad', 2, 1, '2014-12-12 04:14:42', '0000-00-00 00:00:00'),
(545, 57, 'Estabilización mandibular mediante plano deprogramador (mínimo tres meses)', 3, 1, '2014-12-12 04:14:58', '0000-00-00 00:00:00'),
(546, 57, 'Toma de muestra, examen microbiológico (mas valor examen)', 1, 1, '2014-12-12 04:15:11', '0000-00-00 00:00:00'),
(547, 58, 'Extracciones simples para implantes (solo especialistas en implantes)', 3, 1, '2014-12-12 04:15:36', '0000-00-00 00:00:00'),
(548, 58, 'Extracciones complejas para implantes (solo especialistas en implantes)', 4, 1, '2014-12-12 04:15:50', '0000-00-00 00:00:00'),
(549, 58, 'Instalación quirúrgica de implantes óseo-integrables valor unitario (valor no incluye valor del implante)', 28, 1, '2014-12-12 04:16:04', '0000-00-00 00:00:00'),
(550, 58, 'Instalación de implantes provisorios, valor unitario (valor no incluye valor del implante)', 8, 1, '2014-12-12 04:16:35', '0000-00-00 00:00:00'),
(551, 58, 'Instalación de micro-tornillos para ortodoncia', 0, 1, '2014-12-12 04:16:45', '0000-00-00 00:00:00'),
(552, 58, 'Conexión de implante (no incluye valor de aditamentos)', 3, 1, '2014-12-12 04:16:56', '0000-00-00 00:00:00'),
(553, 58, 'Cirugía peri-implantaria de tejidos blandos (no incluye insumos)', 9, 1, '2014-12-12 04:17:05', '0000-00-00 00:00:00'),
(554, 58, 'Reconstrucción de rebordes con injerto (no incluye insumos)', 20, 1, '2014-12-12 04:17:14', '0000-00-00 00:00:00'),
(555, 58, 'Toma de injerto intra-oral (mentón, rama, tuberosidad) (no incluye insumos)', 20, 1, '2014-12-12 04:17:33', '0000-00-00 00:00:00'),
(556, 58, 'Manejo de injerto intra-oral y manejo de injerto Extra-oral', 20, 1, '2014-12-12 04:17:55', '0000-00-00 00:00:00'),
(557, 58, 'Elevación de piso de seno maxilar (para instalación de implantes)*(no incluye insumos)', 30, 1, '2014-12-12 04:18:11', '0000-00-00 00:00:00'),
(558, 58, 'Regeneración tisular guiada por sextantes *(no incluye insumos)', 10, 1, '2014-12-12 04:18:26', '0000-00-00 00:00:00'),
(559, 58, 'Injerto de tejido conectivo (por zona)', 8.5, 1, '2014-12-12 04:18:40', '0000-00-00 00:00:00'),
(560, 58, 'Tratamiento de infección peri-implantaria por sesión *(no incluye insumos)', 6.5, 1, '2014-12-12 04:19:08', '0000-00-00 00:00:00'),
(561, 58, 'Tratamiento de urgencia (no incluye insumos)', 5, 1, '2014-12-12 04:19:17', '0000-00-00 00:00:00'),
(562, 58, 'Ayudantía quirúrgica 25% de honorarios quirúrgicos', 0, 1, '2014-12-12 04:19:23', '0000-00-00 00:00:00'),
(563, 58, 'Arsenalera 7% de los honorarios quirúrgicos', 0, 1, '2014-12-12 04:19:31', '0000-00-00 00:00:00'),
(564, 59, 'Pilar muñón sobre implantes', 6, 1, '2014-12-12 04:19:53', '0000-00-00 00:00:00'),
(565, 59, 'Corona temporal sobre implantes', 5, 1, '2014-12-12 04:19:59', '0000-00-00 00:00:00'),
(566, 59, 'Prótesis fija implante soportada atornillada o cementada por implante', 24, 1, '2014-12-12 04:20:13', '0000-00-00 00:00:00'),
(567, 59, 'Intermediario', 10, 1, '2014-12-12 04:20:58', '0000-00-00 00:00:00'),
(568, 59, 'Prótesis parcial implanto-soportada por maxilar', 35, 1, '2014-12-12 04:21:07', '0000-00-00 00:00:00'),
(569, 59, 'Prótesis total implanto-soportada por maxilar', 70, 1, '2014-12-12 04:21:16', '0000-00-00 00:00:00'),
(570, 59, 'Barra para prótesis sobre implante', 25, 1, '2014-12-12 04:21:25', '0000-00-00 00:00:00'),
(571, 59, 'Desmontaje de prótesis y profilaxis', 8, 1, '2014-12-12 04:21:34', '0000-00-00 00:00:00'),
(572, 59, 'Recambio de o´ring o clip barra', 6, 1, '2014-12-12 04:21:43', '0000-00-00 00:00:00'),
(573, 59, 'Cambio de tornillos protésicos (c/u)', 2.5, 1, '2014-12-12 04:21:54', '0000-00-00 00:00:00'),
(574, 59, 'Cambio acondicionador de tejidos', 2, 1, '2014-12-12 04:22:00', '0000-00-00 00:00:00'),
(575, 59, 'Anclaje de precisión sobre implante', 16, 1, '2014-12-12 04:22:07', '0000-00-00 00:00:00'),
(576, 60, 'Consulta de la especialidad', 3, 1, '2014-12-12 04:22:41', '0000-00-00 00:00:00'),
(577, 60, 'Atención de urgencia de la especialidad', 3, 1, '2014-12-12 04:22:48', '0000-00-00 00:00:00'),
(578, 60, 'Evaluación funcional', 6, 1, '2014-12-12 04:22:58', '0000-00-00 00:00:00'),
(579, 60, 'Estudio y explicación de exámenes complementarios', 3, 1, '2014-12-12 04:23:06', '0000-00-00 00:00:00'),
(580, 60, 'Montaje en articulador', 5, 1, '2014-12-12 04:23:13', '0000-00-00 00:00:00'),
(581, 60, 'Set up de modelos', 7, 1, '2014-12-12 04:23:19', '0000-00-00 00:00:00'),
(582, 60, 'Axiografías', 25, 1, '2014-12-12 04:23:26', '0000-00-00 00:00:00'),
(583, 60, 'Sonografía', 6, 1, '2014-12-12 04:23:33', '0000-00-00 00:00:00'),
(584, 60, 'Electromiografía de superficie', 8, 1, '2014-12-12 04:23:41', '0000-00-00 00:00:00'),
(585, 60, 'Blanqueo anestésico', 3, 1, '2014-12-12 04:23:48', '0000-00-00 00:00:00'),
(586, 60, 'Diagnóstico, plan de tto y estimación de tiempo', 3, 1, '2014-12-12 04:23:55', '0000-00-00 00:00:00'),
(587, 60, 'Tratamiento patología neuromuscular', 30, 1, '2014-12-12 04:24:05', '0000-00-00 00:00:00'),
(588, 60, 'Tratameinto patología crónico', 90, 1, '2014-12-12 04:24:17', '0000-00-00 00:00:00'),
(589, 60, 'Tratamiento patología articular', 60, 1, '2014-12-12 04:24:25', '0000-00-00 00:00:00'),
(590, 60, 'Tratamiento patología articular crónico', 120, 1, '2014-12-12 04:24:32', '0000-00-00 00:00:00'),
(591, 60, 'Tratamiento patología estructural', 120, 1, '2014-12-12 04:24:39', '0000-00-00 00:00:00'),
(592, 60, 'Aplicación de electro física (USS. TENS, Láser) por sesión', 4, 1, '2014-12-12 04:24:47', '0000-00-00 00:00:00'),
(593, 60, 'Terapia bioconductual (por sesión)', 4, 1, '2014-12-12 04:24:56', '0000-00-00 00:00:00'),
(594, 60, 'Técnicas de movilización mandibular asistida por sesión', 4, 1, '2014-12-12 04:25:04', '0000-00-00 00:00:00'),
(595, 60, 'Informes periciales en TTM y DOF', 10, 1, '2014-12-12 04:25:17', '0000-00-00 00:00:00');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tratamiento_piezaplan`
--

CREATE TABLE IF NOT EXISTS `tratamiento_piezaplan` (
  `id_tratamiento_piezaplan` int(11) NOT NULL AUTO_INCREMENT,
  `id_plantratamiento` int(11) DEFAULT NULL,
  `id_tratamiento` int(11) DEFAULT NULL,
  `pieza` int(11) DEFAULT NULL,
  `valor` int(11) NOT NULL,
  `fecha_realizado` date DEFAULT NULL,
  `estado` tinyint(1) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `update_at` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00',
  PRIMARY KEY (`id_tratamiento_piezaplan`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=6 ;

--
-- Volcado de datos para la tabla `tratamiento_piezaplan`
--

INSERT INTO `tratamiento_piezaplan` (`id_tratamiento_piezaplan`, `id_plantratamiento`, `id_tratamiento`, `pieza`, `valor`, `fecha_realizado`, `estado`, `created_at`, `update_at`) VALUES
(1, 5, 1, 4, 1000, '2014-11-18', 1, '2014-11-05 03:02:17', '0000-00-00 00:00:00'),
(2, 5, 2, 20, 2000, '2014-11-18', 1, '2014-11-05 03:02:17', '0000-00-00 00:00:00'),
(3, 5, 3, 5, 1000, '2014-11-17', 0, '2014-11-05 03:02:17', '0000-00-00 00:00:00'),
(4, 6, 3, 5, 1000, '2014-11-18', 1, '2014-11-18 21:27:38', '0000-00-00 00:00:00'),
(5, 6, 2, 45, 2000, NULL, 0, '2014-11-18 21:27:38', '0000-00-00 00:00:00');

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
(1, 3, 5, 23175, 11587, '2014-12-07 17:38:17', '0000-00-00 00:00:00'),
(2, 3, 7, 15450, 7725, '2014-12-07 17:38:17', '0000-00-00 00:00:00'),
(2, 6, 6, 15450, 7725, '2014-12-07 17:40:06', '0000-00-00 00:00:00'),
(1, 5, 3, 23175, 11587, '2014-12-07 17:40:32', '0000-00-00 00:00:00');

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
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 COLLATE=latin1_spanish_ci AUTO_INCREMENT=12 ;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id_usuario`, `id_rol`, `nombre`, `apellido_pat`, `apellido_mat`, `nombre_usuario`, `contrasena`, `email`, `telefono`, `especialidad`, `tiempo_cita`, `activo`, `created_at`, `update_at`, `id_clinica`) VALUES
(1, 1, 'Admin', '', '', 'admin', '63a9f0ea7bb98050796b649e85481845', 'msanhuezal@hotmail.cl', '89348184', 'Cirujano', 15, 1, '2014-10-11 21:32:06', '0000-00-00 00:00:00', 0),
(2, 2, 'Marianela Andrea', 'Iturriaga', 'Jimenez', 'mari.ytu', 'b67912b31fcba0a3173f45f2058226fe', 'mari.ytu@gmail.com', '56072407', 'null', 0, 1, '2014-10-19 04:12:08', '0000-00-00 00:00:00', 0),
(3, 3, 'Gonzalo', 'Sotomayor', '', 'gsotomayor', 'b8d8d98e92ba765212e4b9cebfea8e88', 'gsotomayor@orthodent.cl', '', 'Maxilofacial', 0, 1, '2014-10-19 04:28:33', '0000-00-00 00:00:00', 1),
(4, 3, 'Manuel Alejandro', 'Hoffhein', 'Alfaro', 'mahahein3', '826497e60d6f1646760510f55d4360dd', 'mahahein3@gmail.com', '', 'Maxilofacial', 30, 1, '2014-10-20 01:53:02', '0000-00-00 00:00:00', 2),
(8, 3, 'Mario', 'Sanhueza', 'Leiva', 'msanhuezal', '26c2b2f13b7e8622fe1734324c65f5fb', 'msanhuezal@hotmail.cl', '99889988', 'Maxilofacial', 15, 1, '2014-10-27 14:17:08', '0000-00-00 00:00:00', 2),
(9, 2, 'Felipe', 'Astroza', '', 'fastroza', '827ccb0eea8a706c4c34a16891f84e7b', 'fastroza@gmail.com', '', NULL, NULL, 1, '2014-11-18 21:18:24', '0000-00-00 00:00:00', 2),
(10, 1, 'Juan', 'Salfate', 'Gonzalez', 'sgonzalez', '202cb962ac59075b964b07152d234b70', 'sgonzalez@gmail.com', '88998899', NULL, NULL, 1, '2014-12-09 23:48:55', '0000-00-00 00:00:00', 0),
(11, 1, 'asd', 'asd', 'asd', 'sgonzalez', '202cb962ac59075b964b07152d234b70', 'sgonzalez@gmail.com', '88998899', NULL, NULL, 1, '2014-12-09 23:49:51', '0000-00-00 00:00:00', 0);

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
