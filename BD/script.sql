CREATE DATABASE IF NOT EXISTS `banket` ;
USE `banket`;

CREATE TABLE `client` (
  `id_client` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `fio` varchar(150) NOT NULL,
  `birthday` date NOT NULL,
  `mobile` varchar(15) NOT NULL,
  `email` varchar(45) NOT NULL
);

INSERT INTO `client` (`id_client`, `fio`, `birthday`, `mobile`, `email`) VALUES
(1, 'Пупсик Иван', '1990-11-25', '+356289996569', '-'),
(2, 'Иванов Роман', '1879-02-23', '+325365559875', '-'),
(3, 'Геннадий Васильев', '1988-02-25', '+372966586593', 'chegkfga@mail.ru'),
(4, 'Иванов Петруша', '2020-12-09', '+356895698547', 'cherepawka13@mail.ru'),
(5, 'Привет Иван', '2020-12-09', '+375291234589', 'cherepawka13@mail.ru');

CREATE TABLE `kind_of_event` (
  `id_kind_of_event` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `kind_of_event` varchar(150) NOT NULL
);

INSERT INTO `kind_of_event` (`id_kind_of_event`, `kind_of_event`) VALUES
(1, 'День рождения'),
(2, 'Поминки'),
(3, 'Новый год'),
(4, 'Свадьба');

CREATE TABLE `kind_of_place` (
  `id_kind_of_place` int PRIMARY KEY NOT NULL,
  `kind_of_place` varchar(150) NOT NULL
);

INSERT INTO `kind_of_place` (`id_kind_of_place`, `kind_of_place`) VALUES
(1, 'Кафе'),
(2, 'Ресторан'),
(3, 'Усадьба');

CREATE TABLE `mc` (
  `id_mc` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `fio` varchar(150) NOT NULL,
  `birthday` date NOT NULL,
  `mobile` varchar(45) NOT NULL,
  `email` varchar(45) NOT NULL
);

INSERT INTO `mc` (`id_mc`, `fio`, `birthday`, `mobile`, `email`) VALUES
(1, 'Иванов Иван Иванович', '1980-05-03', '+375296536598', '-'),
(2, 'Денис Витальев', '2020-12-02', '+375291112233', 'cherepawka13@mail.ru'),
(3, 'Юрьев Игорь', '2020-12-17', '+375291112233', 'cherepawka13@mail.ru'),
(4, 'Макаров Макар', '2020-12-09', '+375291112233', 'cherepawka13@mail.ru'),
(5, 'Ириина Капустина', '2020-12-23', '+375291234589', 'cherepawka13@mail.ru');

CREATE TABLE `place` (
  `id_place` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `place` text NOT NULL,
  `address` varchar(100) NOT NULL,
  `contacts` varchar(50) DEFAULT NULL,
  `kind_of_place` int NOT NULL
);

INSERT INTO `place` (`id_place`, `place`, `address`, `contacts`, `kind_of_place`) VALUES
(1, '"Васильки"', 'пр-т Независимости 57/1', '+375296356569', '1'),
(2, 'Пираты', 'Пушкина 17', '+375893265236', '3'),
(3, 'Инферно', 'Беды 39', '+375899638574', '1'),
(4, 'Лидо', 'Независимости 32', '+375894589856', '2');

CREATE TABLE `order` (
  `id_order` int PRIMARY KEY NOT NULL AUTO_INCREMENT,
  `place` int NOT NULL,
  `client` int NOT NULL,
  `mc` int NOT NULL,
  `date` date NOT NULL,
  `guests` int NOT NULL,
  `budget` int NOT NULL,
  `kind_of_event` int NOT NULL
);

INSERT INTO `order` (`id_order`, `place`, `client`, `mc`, `date`, `guests`, `budget`, `kind_of_event`) VALUES
(1, 1, 1, 1, '2020-10-10', 10, 1200, 1),
(2, 2, 3, 2, '2020-10-12', 20, 1500, 2),
(3, 3, 5, 5, '2020-10-15', 30, 2000, 3),
(4, 4, 4, 3, '2020-12-10', 12, 12352, 3),
(5, 3, 2, 4, '2020-12-10', 45, 325635, 4),
(6, 2, 1, 5, '2020-12-10', 12, 12352, 2),
(7, 4, 5, 2, '2020-12-24', 12, 12352, 1);