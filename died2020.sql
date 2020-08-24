-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1:3306
-- Tiempo de generación: 18-08-2020 a las 00:22:10
-- Versión del servidor: 8.0.21
-- Versión de PHP: 7.2.24-0ubuntu0.18.04.6

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `died2020`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `camion`
--

CREATE TABLE `camion` (
  `ID` int NOT NULL,
  `PATENTE` varchar(14) DEFAULT NULL,
  `MARCA` varchar(45) DEFAULT NULL,
  `MODELO` varchar(45) DEFAULT NULL,
  `KM` int DEFAULT NULL,
  `COSTO_KM` decimal(12,2) DEFAULT NULL,
  `COSTO_HORA` decimal(12,2) DEFAULT NULL,
  `FECHA_COMPRA` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `camion`
--

INSERT INTO `camion` (`ID`, `PATENTE`, `MARCA`, `MODELO`, `KM`, `COSTO_KM`, `COSTO_HORA`, `FECHA_COMPRA`) VALUES
(1, 'ABC478', 'EDITADO', 'CARGA', 5000, '50.00', '50.00', '2013-10-22'),
(5, '123', 'HONDA', 'A2', 123, '123.00', '123.00', '1999-10-22'),
(7, 'QWE123', 'KIA', 'qw', 10, '123.00', '12.00', '2011-12-10'),
(12, 'HHH11', 'KIA', 'L', 123, '12.00', '123.00', '2012-02-01'),
(13, '123ABC', 'RENO', '19', 100, '100.00', '100.00', '1999-12-12'),
(14, '456ASQ', 'FORD', 'FOCUS', 30, '556.00', '25.30', '2019-01-09'),
(15, '126ABC', 'FIAT', 'CARGO', 50, '50.00', '50.00', '2017-04-12'),
(16, '123456', '23456', '23456', 123, '12.00', '12.00', '1912-12-12'),
(19, 'GONZALITU', '123123', '123', 123, '123.00', '123.00', '1999-12-12'),
(22, '12345678YTV', 'TOYOTA', 'YKC', 12, '12.00', '12.00', '1999-12-12'),
(23, 'ASL456', 'EDITANDO', 'K', 200, '100.00', '135.00', '2019-12-22'),
(24, '123XYZ', 'FIAT', 'CARGO', 50, '100.00', '100.00', '2019-10-22'),
(25, '2345', 'HONDA', 'ALGO', 123, '123.00', '123.00', '1992-12-12'),
(27, '11112222', 'XIAOMI', 'REDMI', 123, '123.00', '123.00', '1999-12-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumo`
--

CREATE TABLE `insumo` (
  `idInsumo` int NOT NULL,
  `descripcion` varchar(30) NOT NULL,
  `unidadMedida` varchar(30) NOT NULL,
  `costo` double NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `insumo`
--

INSERT INTO `insumo` (`idInsumo`, `descripcion`, `unidadMedida`, `costo`, `precio`) VALUES
(1, 'un insumo', 'GR', 50, 50),
(2, 'editado2 liquido', 'CM3', 23, 56),
(3, 'i liquido', 'CM3', 24, 56),
(4, 'i2 liquido', 'KG', 45, 16),
(5, 'i3 liquido', 'LT', 65, 78),
(6, 'i4 general', 'CM3', 48, 36),
(7, 'i5 general', 'M3', 46.3, 58.1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumoGeneral`
--

CREATE TABLE `insumoGeneral` (
  `idInsumo` int NOT NULL,
  `peso` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `insumoGeneral`
--

INSERT INTO `insumoGeneral` (`idInsumo`, `peso`) VALUES
(1, 150),
(6, 970),
(7, 571);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumoLiquido`
--

CREATE TABLE `insumoLiquido` (
  `idInsumo` int NOT NULL,
  `densidad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `insumoLiquido`
--

INSERT INTO `insumoLiquido` (`idInsumo`, `densidad`) VALUES
(2, 270),
(3, 270),
(4, 465),
(5, 153);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planta`
--

CREATE TABLE `planta` (
  `idPlanta` int NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `planta`
--

INSERT INTO `planta` (`idPlanta`, `nombre`) VALUES
(1, 'PLANTA 1'),
(2, 'PLANTADELOSPIES'),
(3, 'CHACABUCO'),
(4, 'BUENOS AIRES'),
(5, 'JUANJO C'),
(6, 'PEDRITO VM'),
(8, 'THE PLANTA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `idRuta` int NOT NULL,
  `distancia` double NOT NULL,
  `duracionViaje` double NOT NULL,
  `pesoMaximo` double NOT NULL,
  `plantaOrigen` int NOT NULL,
  `plantaDestino` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`idRuta`, `distancia`, `duracionViaje`, `pesoMaximo`, `plantaOrigen`, `plantaDestino`) VALUES
(1, 25, 2, 85, 1, 2);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stockInsumo`
--

CREATE TABLE `stockInsumo` (
  `idStockInsumo` int NOT NULL,
  `planta` int NOT NULL,
  `insumo` int NOT NULL,
  `cantidad` int NOT NULL,
  `puntoReposicion` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camion`
--
ALTER TABLE `camion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `insumo`
--
ALTER TABLE `insumo`
  ADD PRIMARY KEY (`idInsumo`);

--
-- Indices de la tabla `insumoGeneral`
--
ALTER TABLE `insumoGeneral`
  ADD PRIMARY KEY (`idInsumo`);

--
-- Indices de la tabla `insumoLiquido`
--
ALTER TABLE `insumoLiquido`
  ADD PRIMARY KEY (`idInsumo`);

--
-- Indices de la tabla `planta`
--
ALTER TABLE `planta`
  ADD PRIMARY KEY (`idPlanta`);

--
-- Indices de la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD PRIMARY KEY (`idRuta`),
  ADD KEY `fk_idOrigen` (`plantaOrigen`),
  ADD KEY `fk_idDestino` (`plantaDestino`);

--
-- Indices de la tabla `stockInsumo`
--
ALTER TABLE `stockInsumo`
  ADD PRIMARY KEY (`idStockInsumo`),
  ADD KEY `FK_planta` (`planta`),
  ADD KEY `FK_insumo` (`insumo`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `camion`
--
ALTER TABLE `camion`
  MODIFY `ID` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `insumo`
--
ALTER TABLE `insumo`
  MODIFY `idInsumo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `insumoGeneral`
--
ALTER TABLE `insumoGeneral`
  MODIFY `idInsumo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `insumoLiquido`
--
ALTER TABLE `insumoLiquido`
  MODIFY `idInsumo` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `planta`
--
ALTER TABLE `planta`
  MODIFY `idPlanta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `idRuta` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `stockInsumo`
--
ALTER TABLE `stockInsumo`
  MODIFY `idStockInsumo` int NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `insumoGeneral`
--
ALTER TABLE `insumoGeneral`
  ADD CONSTRAINT `fk_idInsumo` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`);

--
-- Filtros para la tabla `insumoLiquido`
--
ALTER TABLE `insumoLiquido`
  ADD CONSTRAINT `fk_idInsumo_Liquido` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`);

--
-- Filtros para la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `fk_idDestino` FOREIGN KEY (`plantaDestino`) REFERENCES `planta` (`idPlanta`),
  ADD CONSTRAINT `fk_idOrigen` FOREIGN KEY (`plantaOrigen`) REFERENCES `planta` (`idPlanta`);

--
-- Filtros para la tabla `stockInsumo`
--
ALTER TABLE `stockInsumo`
  ADD CONSTRAINT `FK_insumo` FOREIGN KEY (`insumo`) REFERENCES `insumo` (`idInsumo`),
  ADD CONSTRAINT `FK_planta` FOREIGN KEY (`planta`) REFERENCES `planta` (`idPlanta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
