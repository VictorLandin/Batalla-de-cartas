-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema batalla
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `batalla` DEFAULT CHARACTER SET utf8mb3 ;
USE `batalla` ;

-- -----------------------------------------------------
-- Table `batalla`.`accion`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`accion` (
  `idCarta` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ataque` INT NOT NULL,
  `Defensa` INT NOT NULL,
  `Repeticiones` INT NOT NULL,
  PRIMARY KEY (`idCarta`),
  UNIQUE INDEX `idCarta_UNIQUE` (`idCarta` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `batalla`.`efecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`efecto` (
  `idEfecto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEfecto`),
  UNIQUE INDEX `idEfecto_UNIQUE` (`idEfecto` ASC) VISIBLE,
  UNIQUE INDEX `Nombre_UNIQUE` (`Nombre` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `batalla`.`accion_tiene_efecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`accion_tiene_efecto` (
  `idAccion` INT UNSIGNED NOT NULL,
  `idEfecto` INT UNSIGNED NOT NULL,
  `Objetivo` ENUM('Enemigo', 'Jugador', 'Area') NOT NULL DEFAULT 'Enemigo',
  PRIMARY KEY (`idAccion`, `idEfecto`),
  INDEX `fk_Carta_has_Efecto_Efecto1_idx` (`idEfecto` ASC) VISIBLE,
  INDEX `fk_Carta_has_Efecto_Carta_idx` (`idAccion` ASC) VISIBLE,
  CONSTRAINT `fk_Carta_has_Efecto_Carta0`
    FOREIGN KEY (`idAccion`)
    REFERENCES `batalla`.`accion` (`idCarta`),
  CONSTRAINT `fk_Carta_has_Efecto_Efecto10`
    FOREIGN KEY (`idEfecto`)
    REFERENCES `batalla`.`efecto` (`idEfecto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `batalla`.`carta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`carta` (
  `idCarta` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ataque` INT NOT NULL,
  `Defensa` INT NOT NULL,
  `Repeticiones` INT NOT NULL,
  `Imagen` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idCarta`),
  UNIQUE INDEX `idCarta_UNIQUE` (`idCarta` ASC) VISIBLE,
  UNIQUE INDEX `Imagen_UNIQUE` (`Imagen` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 1
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `batalla`.`carta_tiene_efecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`carta_tiene_efecto` (
  `idCarta` INT UNSIGNED NOT NULL,
  `idEfecto` INT UNSIGNED NOT NULL,
  `Objetivo` ENUM('Enemigo', 'Jugador', 'Area') NOT NULL DEFAULT 'Enemigo',
  PRIMARY KEY (`idCarta`, `idEfecto`),
  INDEX `fk_Carta_has_Efecto_Efecto1_idx` (`idEfecto` ASC) VISIBLE,
  INDEX `fk_Carta_has_Efecto_Carta_idx` (`idCarta` ASC) VISIBLE,
  CONSTRAINT `fk_Carta_has_Efecto_Carta`
    FOREIGN KEY (`idCarta`)
    REFERENCES `batalla`.`carta` (`idCarta`),
  CONSTRAINT `fk_Carta_has_Efecto_Efecto1`
    FOREIGN KEY (`idEfecto`)
    REFERENCES `batalla`.`efecto` (`idEfecto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `batalla`.`personaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`personaje` (
  `idPersonaje` INT NOT NULL,
  `Vida` INT NOT NULL,
  `Imagen` VARCHAR(45) NULL DEFAULT NULL,
  `Energia` INT NOT NULL,
  `RegeneracionEnergia` INT NOT NULL,
  PRIMARY KEY (`idPersonaje`),
  UNIQUE INDEX `idPersonaje_UNIQUE` (`idPersonaje` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `batalla`.`comportamiento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`comportamiento` (
  `idPersonaje` INT NOT NULL,
  `idAccion` INT UNSIGNED NOT NULL,
  `orden` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idPersonaje`, `orden`),
  INDEX `fk_Personaje_has_Carta_Carta1_idx` (`idAccion` ASC) VISIBLE,
  INDEX `fk_Personaje_has_Carta_Personaje1_idx` (`idPersonaje` ASC) VISIBLE,
  CONSTRAINT `fk_Personaje_has_Carta_Carta10`
    FOREIGN KEY (`idAccion`)
    REFERENCES `batalla`.`accion` (`idCarta`),
  CONSTRAINT `fk_Personaje_has_Carta_Personaje10`
    FOREIGN KEY (`idPersonaje`)
    REFERENCES `batalla`.`personaje` (`idPersonaje`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `batalla`.`mazo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `batalla`.`mazo` (
  `idPersonaje` INT NOT NULL,
  `idCarta` INT UNSIGNED NOT NULL,
  `LugarMazo` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`idPersonaje`, `LugarMazo`),
  INDEX `fk_Personaje_has_Carta_Carta1_idx` (`idCarta` ASC) VISIBLE,
  INDEX `fk_Personaje_has_Carta_Personaje1_idx` (`idPersonaje` ASC) VISIBLE,
  CONSTRAINT `fk_Personaje_has_Carta_Carta1`
    FOREIGN KEY (`idCarta`)
    REFERENCES `batalla`.`carta` (`idCarta`),
  CONSTRAINT `fk_Personaje_has_Carta_Personaje1`
    FOREIGN KEY (`idPersonaje`)
    REFERENCES `batalla`.`personaje` (`idPersonaje`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
