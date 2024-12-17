-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 17-12-2024 a las 20:31:49
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bazaar`
--

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customers`
--

CREATE TABLE `customers` (
  `id` bigint(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `birth_date` date NOT NULL,
  `email` varchar(255) NOT NULL,
  `gender` varchar(15) NOT NULL,
  `name` varchar(20) NOT NULL,
  `national_id` varchar(15) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `surname` varchar(20) NOT NULL,
  `status` enum('ACTIVE','CANCELED','COMPLETED','DELETED','INACTIVE') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `customers`
--

INSERT INTO `customers` (`id`, `address`, `birth_date`, `email`, `gender`, `name`, `national_id`, `phone`, `surname`, `status`) VALUES
(102, 'Palo alto, California', '1940-12-21', 'FrankZ@gmail.com', 'male', 'Frank', '45233890', '01154567899', 'Zappa', 'ACTIVE'),
(152, 'New York', '1926-05-26', 'jazzMeansBlack@gmail.com', 'male', 'Miles', '14289988', '2920252341', 'Davis', 'ACTIVE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `customers_seq`
--

CREATE TABLE `customers_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `customers_seq`
--

INSERT INTO `customers_seq` (`next_val`) VALUES
(251);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products`
--

CREATE TABLE `products` (
  `id` bigint(20) NOT NULL,
  `brand` varchar(20) NOT NULL,
  `category` enum('BEAUTY','BOOKS','CLOTHING','ELECTRONICS','FURNITURE','GARDEN','GIFTS','GROCERIES','KITCHEN','TOOLS') NOT NULL,
  `name` varchar(20) NOT NULL,
  `sale_price` double NOT NULL,
  `status` enum('ACTIVE','CANCELED','COMPLETED','DELETED','INACTIVE') NOT NULL,
  `stock` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `products`
--

INSERT INTO `products` (`id`, `brand`, `category`, `name`, `sale_price`, `status`, `stock`) VALUES
(1, 'Kitchen ready', 'KITCHEN', 'wood fork', 8, 'ACTIVE', 149),
(2, 'LOVELY COOK', 'KITCHEN', 'steel spoon', 15, 'ACTIVE', 41),
(3, 'LOVELY COOK', 'KITCHEN', 'wood spoon', 15, 'ACTIVE', 40),
(4, 'LOVELY COOK', 'KITCHEN', 'wood fork', 15, 'ACTIVE', 50),
(5, 'LOVELY COOK', 'KITCHEN', 'steel knife', 19, 'ACTIVE', 50);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `products_seq`
--

CREATE TABLE `products_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `products_seq`
--

INSERT INTO `products_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sales`
--

CREATE TABLE `sales` (
  `id` bigint(20) NOT NULL,
  `date` date NOT NULL,
  `payment_method` enum('BANK_TRANSFER','CASH','CREDIT_CARD','DEBIT_CARD','PAYPAL') NOT NULL,
  `status` varchar(255) NOT NULL,
  `total_amount` double DEFAULT NULL,
  `id_customer` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sales`
--

INSERT INTO `sales` (`id`, `date`, `payment_method`, `status`, `total_amount`, `id_customer`) VALUES
(1, '2024-11-18', 'DEBIT_CARD', 'CANCELED', 40, 102),
(2, '2024-11-18', 'PAYPAL', 'COMPLETED', 160, 102),
(3, '2024-11-18', 'PAYPAL', 'COMPLETED', 165, 152);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sales_seq`
--

CREATE TABLE `sales_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sales_seq`
--

INSERT INTO `sales_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale_details`
--

CREATE TABLE `sale_details` (
  `id` bigint(20) NOT NULL,
  `product_quantity` int(11) NOT NULL,
  `sale_price` double NOT NULL,
  `id_product` bigint(20) NOT NULL,
  `id_sale` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sale_details`
--

INSERT INTO `sale_details` (`id`, `product_quantity`, `sale_price`, `id_product`, `id_sale`) VALUES
(1, 5, 8, 1, 1),
(2, 5, 8, 1, 2),
(3, 8, 15, 2, 2),
(4, 10, 15, 3, 3),
(5, 1, 15, 2, 3);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sale_details_seq`
--

CREATE TABLE `sale_details_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `sale_details_seq`
--

INSERT INTO `sale_details_seq` (`next_val`) VALUES
(101);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suppliers`
--

CREATE TABLE `suppliers` (
  `id` bigint(20) NOT NULL,
  `business_name` varchar(255) NOT NULL,
  `contact` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `status` enum('ACTIVE','CANCELED','COMPLETED','DELETED','INACTIVE','PENDING') NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `suppliers`
--

INSERT INTO `suppliers` (`id`, `business_name`, `contact`, `location`, `status`) VALUES
(52, 'XYZS Traders', '555-5678', '456 Commerce Avenue, Metropolis', 'ACTIVE'),
(53, 'Super Retail', '555-4231', '201 New Avenue, Atlanta', 'ACTIVE');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `suppliers_seq`
--

CREATE TABLE `suppliers_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `suppliers_seq`
--

INSERT INTO `suppliers_seq` (`next_val`) VALUES
(151);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supply_orders`
--

CREATE TABLE `supply_orders` (
  `id` bigint(20) NOT NULL,
  `order_date` date NOT NULL,
  `payment_method` enum('BANK_TRANSFER','CASH','CREDIT_CARD','DEBIT_CARD','PAYPAL') NOT NULL,
  `status` enum('ACTIVE','CANCELED','COMPLETED','DELETED','INACTIVE','PENDING') NOT NULL,
  `total_supply_cost` double DEFAULT NULL,
  `id_supplier` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `supply_orders`
--

INSERT INTO `supply_orders` (`id`, `order_date`, `payment_method`, `status`, `total_supply_cost`, `id_supplier`) VALUES
(1, '2024-11-22', 'CASH', 'COMPLETED', 300, 52);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supply_orders_seq`
--

CREATE TABLE `supply_orders_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `supply_orders_seq`
--

INSERT INTO `supply_orders_seq` (`next_val`) VALUES
(51);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supply_order_details`
--

CREATE TABLE `supply_order_details` (
  `id` bigint(20) NOT NULL,
  `quantity` int(11) NOT NULL,
  `unit_price` double NOT NULL,
  `id_product` bigint(20) NOT NULL,
  `id_supply_order` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `supply_order_details`
--

INSERT INTO `supply_order_details` (`id`, `quantity`, `unit_price`, `id_product`, `id_supply_order`) VALUES
(1, 30, 10, 1, 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `supply_order_details_seq`
--

CREATE TABLE `supply_order_details_seq` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `supply_order_details_seq`
--

INSERT INTO `supply_order_details_seq` (`next_val`) VALUES
(51);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `customers`
--
ALTER TABLE `customers`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `products`
--
ALTER TABLE `products`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `sales`
--
ALTER TABLE `sales`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKftulmppbj2dv7fdvrytf57q60` (`id_customer`);

--
-- Indices de la tabla `sale_details`
--
ALTER TABLE `sale_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKmsa4qvbymisxf79bff99g3mj0` (`id_product`),
  ADD KEY `FKc563x1lab0ngm3r8d29r10msx` (`id_sale`);

--
-- Indices de la tabla `suppliers`
--
ALTER TABLE `suppliers`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `supply_orders`
--
ALTER TABLE `supply_orders`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FK1pht4p7ly9r2jfyyoimf6r7co` (`id_supplier`);

--
-- Indices de la tabla `supply_order_details`
--
ALTER TABLE `supply_order_details`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKkilmk985ya3oyhkslv8y0c6ll` (`id_product`),
  ADD KEY `FKs4c6oi75yqyx7e95cyivuqu1` (`id_supply_order`);

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `sales`
--
ALTER TABLE `sales`
  ADD CONSTRAINT `FKftulmppbj2dv7fdvrytf57q60` FOREIGN KEY (`id_customer`) REFERENCES `customers` (`id`);

--
-- Filtros para la tabla `sale_details`
--
ALTER TABLE `sale_details`
  ADD CONSTRAINT `FKc563x1lab0ngm3r8d29r10msx` FOREIGN KEY (`id_sale`) REFERENCES `sales` (`id`),
  ADD CONSTRAINT `FKmsa4qvbymisxf79bff99g3mj0` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`);

--
-- Filtros para la tabla `supply_orders`
--
ALTER TABLE `supply_orders`
  ADD CONSTRAINT `FK1pht4p7ly9r2jfyyoimf6r7co` FOREIGN KEY (`id_supplier`) REFERENCES `suppliers` (`id`);

--
-- Filtros para la tabla `supply_order_details`
--
ALTER TABLE `supply_order_details`
  ADD CONSTRAINT `FKkilmk985ya3oyhkslv8y0c6ll` FOREIGN KEY (`id_product`) REFERENCES `products` (`id`),
  ADD CONSTRAINT `FKs4c6oi75yqyx7e95cyivuqu1` FOREIGN KEY (`id_supply_order`) REFERENCES `supply_orders` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
