-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 29-08-2020 a las 08:06:55
-- Versión del servidor: 10.4.13-MariaDB
-- Versión de PHP: 7.2.32

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
  `ID` int(11) NOT NULL,
  `PATENTE` varchar(14) DEFAULT NULL,
  `MARCA` varchar(45) DEFAULT NULL,
  `MODELO` varchar(45) DEFAULT NULL,
  `KM` int(11) DEFAULT NULL,
  `COSTO_KM` decimal(12,2) DEFAULT NULL,
  `COSTO_HORA` decimal(12,2) DEFAULT NULL,
  `FECHA_COMPRA` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `camion`
--

INSERT INTO `camion` (`ID`, `PATENTE`, `MARCA`, `MODELO`, `KM`, `COSTO_KM`, `COSTO_HORA`, `FECHA_COMPRA`) VALUES
(1, 'ABC478', 'EDITADO', 'CARGA', 5000, '50.00', '50.00', '2013-10-22'),
(5, '123', 'HONDA', 'A2', 123, '123.00', '123.00', '1999-10-22'),
(7, 'QWE123', 'KIA', 'qw', 109, '123.00', '12.00', '2011-12-10'),
(12, 'HHH11', 'KIA', 'L', 123, '12.00', '123.00', '2012-02-01'),
(13, '123ABC', 'RENO', '19', 100, '100.00', '100.00', '1999-12-12'),
(14, '456ASQ', 'FORD', 'FOCUS', 104, '556.00', '25.30', '2019-01-09'),
(15, '126ABC', 'FIAT', 'CARGO', 112, '50.00', '50.00', '2017-04-12'),
(16, '123456', '23456', '23456', 123, '12.00', '12.00', '1912-12-12'),
(19, 'GONZALITU', '123123', '123', 123, '123.00', '123.00', '1999-12-12'),
(22, '12345678YTV', 'TOYOTA', 'YKC', 123, '12.00', '12.00', '1999-12-12'),
(23, 'ASL456', 'EDITANDO', 'K', 200, '100.00', '135.00', '2019-12-22'),
(24, '123XYZ', 'FIAT', 'CARGO', 82, '100.00', '100.00', '2019-10-22'),
(25, '2345', 'HONDA', 'ALGO', 123, '123.00', '123.00', '1992-12-12'),
(27, '11112222', 'XIAOMI', 'REDMI', 123, '123.00', '123.00', '1999-12-12');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `enviodetalle`
--

CREATE TABLE `enviodetalle` (
  `idEnvio` int(11) NOT NULL,
  `numOrden` int(11) NOT NULL,
  `camionAsignado` int(11) NOT NULL,
  `costoEnvio` double NOT NULL,
  `plantaOrigen` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `enviodetalle`
--

INSERT INTO `enviodetalle` (`idEnvio`, `numOrden`, `camionAsignado`, `costoEnvio`, `plantaOrigen`) VALUES
(10, 1111, 15, 4750, 3),
(11, 6, 24, 1400, 4),
(12, 9, 22, 1584, 5),
(13, 33, 14, 8088.8, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumo`
--

CREATE TABLE `insumo` (
  `idInsumo` int(11) NOT NULL,
  `descripcion` varchar(30) NOT NULL,
  `unidadMedida` varchar(30) NOT NULL,
  `costo` double NOT NULL,
  `precio` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
-- Estructura de tabla para la tabla `insumogeneral`
--

CREATE TABLE `insumogeneral` (
  `idInsumo` int(11) NOT NULL,
  `peso` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `insumogeneral`
--

INSERT INTO `insumogeneral` (`idInsumo`, `peso`) VALUES
(1, 150),
(6, 970),
(7, 571);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `insumoliquido`
--

CREATE TABLE `insumoliquido` (
  `idInsumo` int(11) NOT NULL,
  `densidad` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `insumoliquido`
--

INSERT INTO `insumoliquido` (`idInsumo`, `densidad`) VALUES
(2, 270),
(3, 270),
(4, 465),
(5, 153);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `itemdetalle`
--

CREATE TABLE `itemdetalle` (
  `idDetalle` int(11) NOT NULL,
  `numOrden` int(11) NOT NULL,
  `insumo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `precioItem` double DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `itemdetalle`
--

INSERT INTO `itemdetalle` (`idDetalle`, `numOrden`, `insumo`, `cantidad`, `precioItem`) VALUES
(13, 1111, 1, 50, 2500),
(14, 76, 1, 20, 1000),
(15, 76, 3, 2, 48),
(16, 5, 1, 20, 1000),
(17, 6, 1, 30, 1500),
(18, 9, 3, 30, 720),
(19, 11, 6, 20, 960),
(20, 23, 1, 1400, 70000),
(21, 23, 2, 1400, 32200),
(22, 23, 4, 1400, 63000),
(23, 27, 6, 1, 48),
(24, 29, 2, 2, 46),
(25, 33, 1, 10, 500),
(26, 4, 2, 3000, 69000),
(27, 4, 5, 3000, 195000);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pedido`
--

CREATE TABLE `pedido` (
  `numOrden` int(11) NOT NULL,
  `plantaDestino` int(11) NOT NULL,
  `fechaSolicitud` date NOT NULL,
  `fechaEntrega` date NOT NULL,
  `estado` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pedido`
--

INSERT INTO `pedido` (`numOrden`, `plantaDestino`, `fechaSolicitud`, `fechaEntrega`, `estado`) VALUES
(4, 3, '2020-08-29', '2020-12-31', 'CREADA'),
(5, 10, '2020-08-29', '2020-12-31', 'CREADA'),
(6, 2, '2020-08-29', '2020-12-31', 'PROCESADA'),
(9, 6, '2020-08-29', '2020-12-31', 'PROCESADA'),
(11, 10, '2020-08-29', '2020-12-31', 'CREADA'),
(23, 5, '2020-08-29', '2020-12-31', 'CANCELADA'),
(27, 8, '2020-08-29', '2020-12-31', 'CREADA'),
(29, 2, '2020-08-29', '2020-12-31', 'CANCELADA'),
(33, 1, '2020-08-29', '2020-12-31', 'ENTREGADA'),
(76, 2, '2020-08-29', '2020-07-12', 'CREADA'),
(1111, 2, '2020-08-28', '2020-12-31', 'ENTREGADA');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pertenecea`
--

CREATE TABLE `pertenecea` (
  `idRuta` int(11) NOT NULL,
  `numOrden` int(11) NOT NULL,
  `orden` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `pertenecea`
--

INSERT INTO `pertenecea` (`idRuta`, `numOrden`, `orden`) VALUES
(1, 1111, 1),
(2, 33, 0),
(2, 1111, 0),
(4, 6, 1),
(5, 6, 0),
(9, 9, 0);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `planta`
--

CREATE TABLE `planta` (
  `idPlanta` int(11) NOT NULL,
  `nombre` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

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
(8, 'THE PLANTA'),
(9, 'CORDOBA'),
(10, 'HERNANDARIAS');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ruta`
--

CREATE TABLE `ruta` (
  `idRuta` int(11) NOT NULL,
  `distancia` double NOT NULL,
  `duracionViaje` double NOT NULL,
  `pesoMaximo` double NOT NULL,
  `plantaOrigen` int(11) NOT NULL,
  `plantaDestino` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `ruta`
--

INSERT INTO `ruta` (`idRuta`, `distancia`, `duracionViaje`, `pesoMaximo`, `plantaOrigen`, `plantaDestino`) VALUES
(1, 25, 2, 85, 1, 2),
(2, 12, 56, 78, 3, 1),
(3, 67, 45, 78, 3, 2),
(4, 5, 5, 5, 5, 2),
(5, 2, 2, 2, 4, 5),
(7, 7, 85, 99, 2, 3),
(8, 76, 858, 9, 4, 3),
(9, 44, 88, 99, 5, 6),
(10, 85, 25, 22, 8, 9);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `stockinsumo`
--

CREATE TABLE `stockinsumo` (
  `idStockInsumo` int(11) NOT NULL,
  `planta` int(11) NOT NULL,
  `insumo` int(11) NOT NULL,
  `cantidad` int(11) NOT NULL,
  `puntoReposicion` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Volcado de datos para la tabla `stockinsumo`
--

INSERT INTO `stockinsumo` (`idStockInsumo`, `planta`, `insumo`, `cantidad`, `puntoReposicion`) VALUES
(1, 1, 1, 123, 12),
(2, 3, 1, 63, 12),
(3, 1, 3, 40, 50),
(4, 4, 1, 78, 14),
(5, 8, 6, 80, 12),
(6, 9, 1, 800, 100),
(7, 9, 6, 500, 200),
(8, 9, 4, 50, 500),
(9, 5, 1, 55, 200),
(10, 5, 3, 555, 444),
(11, 5, 7, 600, 700),
(12, 4, 3, 55, 1),
(13, 4, 6, 600, 500),
(14, 2, 1, 800, 10),
(15, 2, 5, 55, 700),
(16, 10, 1, 1, 1000);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `camion`
--
ALTER TABLE `camion`
  ADD PRIMARY KEY (`ID`);

--
-- Indices de la tabla `enviodetalle`
--
ALTER TABLE `enviodetalle`
  ADD PRIMARY KEY (`idEnvio`),
  ADD UNIQUE KEY `camionAsignado` (`camionAsignado`),
  ADD KEY `FK_ORDENENVIO` (`numOrden`);

--
-- Indices de la tabla `insumo`
--
ALTER TABLE `insumo`
  ADD PRIMARY KEY (`idInsumo`);

--
-- Indices de la tabla `insumogeneral`
--
ALTER TABLE `insumogeneral`
  ADD PRIMARY KEY (`idInsumo`);

--
-- Indices de la tabla `insumoliquido`
--
ALTER TABLE `insumoliquido`
  ADD PRIMARY KEY (`idInsumo`);

--
-- Indices de la tabla `itemdetalle`
--
ALTER TABLE `itemdetalle`
  ADD PRIMARY KEY (`idDetalle`),
  ADD KEY `FK_insumo_ITEM` (`insumo`),
  ADD KEY `FK_ITEM_ENVIO` (`numOrden`);

--
-- Indices de la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD PRIMARY KEY (`numOrden`),
  ADD UNIQUE KEY `numOrden` (`numOrden`),
  ADD KEY `FK_plantaDestino` (`plantaDestino`);

--
-- Indices de la tabla `pertenecea`
--
ALTER TABLE `pertenecea`
  ADD PRIMARY KEY (`idRuta`,`numOrden`),
  ADD KEY `FK_ORDEN` (`numOrden`);

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
-- Indices de la tabla `stockinsumo`
--
ALTER TABLE `stockinsumo`
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
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `enviodetalle`
--
ALTER TABLE `enviodetalle`
  MODIFY `idEnvio` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=14;

--
-- AUTO_INCREMENT de la tabla `insumo`
--
ALTER TABLE `insumo`
  MODIFY `idInsumo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `insumogeneral`
--
ALTER TABLE `insumogeneral`
  MODIFY `idInsumo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT de la tabla `insumoliquido`
--
ALTER TABLE `insumoliquido`
  MODIFY `idInsumo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT de la tabla `itemdetalle`
--
ALTER TABLE `itemdetalle`
  MODIFY `idDetalle` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- AUTO_INCREMENT de la tabla `planta`
--
ALTER TABLE `planta`
  MODIFY `idPlanta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `ruta`
--
ALTER TABLE `ruta`
  MODIFY `idRuta` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT de la tabla `stockinsumo`
--
ALTER TABLE `stockinsumo`
  MODIFY `idStockInsumo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `enviodetalle`
--
ALTER TABLE `enviodetalle`
  ADD CONSTRAINT `FK_CAMIONENVIO` FOREIGN KEY (`camionAsignado`) REFERENCES `camion` (`ID`),
  ADD CONSTRAINT `FK_CAMION_ENVIO` FOREIGN KEY (`camionAsignado`) REFERENCES `camion` (`ID`),
  ADD CONSTRAINT `FK_ENVIO_CAMION` FOREIGN KEY (`camionAsignado`) REFERENCES `camion` (`ID`),
  ADD CONSTRAINT `FK_ENVIO_ORDEN` FOREIGN KEY (`numOrden`) REFERENCES `pedido` (`numOrden`),
  ADD CONSTRAINT `FK_ENVIO_PEDIDO` FOREIGN KEY (`numOrden`) REFERENCES `pedido` (`numOrden`),
  ADD CONSTRAINT `FK_ORDENENVIO` FOREIGN KEY (`numOrden`) REFERENCES `pedido` (`numOrden`);

--
-- Filtros para la tabla `insumogeneral`
--
ALTER TABLE `insumogeneral`
  ADD CONSTRAINT `fk_idInsumo` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`);

--
-- Filtros para la tabla `insumoliquido`
--
ALTER TABLE `insumoliquido`
  ADD CONSTRAINT `fk_idInsumo_Liquido` FOREIGN KEY (`idInsumo`) REFERENCES `insumo` (`idInsumo`);

--
-- Filtros para la tabla `itemdetalle`
--
ALTER TABLE `itemdetalle`
  ADD CONSTRAINT `FK_ITEM_ENVIO` FOREIGN KEY (`numOrden`) REFERENCES `pedido` (`numOrden`),
  ADD CONSTRAINT `FK_ITEM_ORDEN` FOREIGN KEY (`numOrden`) REFERENCES `pedido` (`numOrden`),
  ADD CONSTRAINT `FK_insumo_ITEM` FOREIGN KEY (`insumo`) REFERENCES `insumo` (`idInsumo`);

--
-- Filtros para la tabla `pedido`
--
ALTER TABLE `pedido`
  ADD CONSTRAINT `FK_plantaDestino` FOREIGN KEY (`plantaDestino`) REFERENCES `planta` (`idPlanta`);

--
-- Filtros para la tabla `pertenecea`
--
ALTER TABLE `pertenecea`
  ADD CONSTRAINT `FK_IDRUTA_P` FOREIGN KEY (`idRuta`) REFERENCES `ruta` (`idRuta`),
  ADD CONSTRAINT `FK_ORDEN` FOREIGN KEY (`numOrden`) REFERENCES `pedido` (`numOrden`),
  ADD CONSTRAINT `FK_RUTAASIGNADA` FOREIGN KEY (`idRuta`) REFERENCES `ruta` (`idRuta`);

--
-- Filtros para la tabla `ruta`
--
ALTER TABLE `ruta`
  ADD CONSTRAINT `fk_idDestino` FOREIGN KEY (`plantaDestino`) REFERENCES `planta` (`idPlanta`),
  ADD CONSTRAINT `fk_idOrigen` FOREIGN KEY (`plantaOrigen`) REFERENCES `planta` (`idPlanta`);

--
-- Filtros para la tabla `stockinsumo`
--
ALTER TABLE `stockinsumo`
  ADD CONSTRAINT `FK_insumo` FOREIGN KEY (`insumo`) REFERENCES `insumo` (`idInsumo`),
  ADD CONSTRAINT `FK_planta` FOREIGN KEY (`planta`) REFERENCES `planta` (`idPlanta`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
