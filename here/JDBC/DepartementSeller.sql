-- phpMyAdmin SQL Dump
-- version 5.1.0
-- https://www.phpmyadmin.net/
--
-- Hôte : localhost:8889
-- Généré le : lun. 24 avr. 2023 à 22:10
-- Version du serveur :  5.7.34
-- Version de PHP : 7.4.21

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `DepartementSeller`
--

-- --------------------------------------------------------

--
-- Structure de la table `department`
--

CREATE TABLE `department` (
  `id` int(20) NOT NULL,
  `name` varchar(30) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `department`
--

INSERT INTO `department` (`id`, `name`) VALUES
(1, 'informatique'),
(2, 'RH'),
(3, 'Commercial'),
(4, 'Achat'),
(5, 'Finance');

-- --------------------------------------------------------

--
-- Structure de la table `Seller`
--

CREATE TABLE `Seller` (
  `id` int(20) NOT NULL,
  `name` varchar(30) NOT NULL,
  `email` varchar(30) NOT NULL,
  `birthDate` date NOT NULL,
  `baseSalary` decimal(20,0) NOT NULL,
  `DepartmentId` int(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Déchargement des données de la table `Seller`
--

INSERT INTO `Seller` (`id`, `name`, `email`, `birthDate`, `baseSalary`, `DepartmentId`) VALUES
(1, 'Mohamed', 'm@emsi.ma', '1970-01-01', '150000', 1),
(2, 'Nada', 'nada@emsi.ma', '1970-01-01', '150000', 1),
(3, 'Adil', 'adil@emsi.ma', '2000-04-10', '20000', 3),
(4, 'Houda', 'houda@gmail.com', '2001-08-18', '140000', 4),
(5, 'Nabil', 'nabil@contact.com', '2002-07-26', '10000', 2),
(6, 'Aymen', 'aymen@soc.com', '2003-09-19', '120000', 5);

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`id`);

--
-- Index pour la table `Seller`
--
ALTER TABLE `Seller`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `department`
--
ALTER TABLE `department`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- AUTO_INCREMENT pour la table `Seller`
--
ALTER TABLE `Seller`
  MODIFY `id` int(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
