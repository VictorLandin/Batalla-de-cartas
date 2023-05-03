-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8mb3 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`carta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`carta` (
  `idCarta` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Ataque` INT NOT NULL,
  `Defensa` INT NOT NULL,
  `Repeticiones` INT NOT NULL,
  `Imagen` VARCHAR(45) NOT NULL,
  `Objetivo` ENUM('Enemigo', 'Jugador', 'Area') NOT NULL DEFAULT 'Enemigo',
  PRIMARY KEY (`idCarta`),
  UNIQUE INDEX `idCarta_UNIQUE` (`idCarta` ASC) VISIBLE,
  UNIQUE INDEX `Imagen_UNIQUE` (`Imagen` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`efecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`efecto` (
  `idEfecto` INT UNSIGNED NOT NULL AUTO_INCREMENT,
  `Nombre` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEfecto`),
  UNIQUE INDEX `idEfecto_UNIQUE` (`idEfecto` ASC) VISIBLE,
  UNIQUE INDEX `Nombre_UNIQUE` (`Nombre` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`carta_tiene_efecto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`carta_tiene_efecto` (
  `Carta_idCarta` INT UNSIGNED NOT NULL,
  `Efecto_idEfecto` INT UNSIGNED NOT NULL,
  `Objetivo` ENUM('Enemigo', 'Jugador', 'Area') NOT NULL DEFAULT 'Enemigo',
  PRIMARY KEY (`Carta_idCarta`, `Efecto_idEfecto`),
  INDEX `fk_Carta_has_Efecto_Efecto1_idx` (`Efecto_idEfecto` ASC) VISIBLE,
  INDEX `fk_Carta_has_Efecto_Carta_idx` (`Carta_idCarta` ASC) VISIBLE,
  CONSTRAINT `fk_Carta_has_Efecto_Carta`
    FOREIGN KEY (`Carta_idCarta`)
    REFERENCES `mydb`.`carta` (`idCarta`),
  CONSTRAINT `fk_Carta_has_Efecto_Efecto1`
    FOREIGN KEY (`Efecto_idEfecto`)
    REFERENCES `mydb`.`efecto` (`idEfecto`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`personaje`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`personaje` (
  `idPersonaje` INT NOT NULL,
  `Vida` INT NOT NULL,
  `Imagen` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idPersonaje`),
  UNIQUE INDEX `idPersonaje_UNIQUE` (`idPersonaje` ASC) VISIBLE,
  UNIQUE INDEX `Imagen_UNIQUE` (`Imagen` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `mydb`.`mazo`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`mazo` (
  `Personaje_idPersonaje` INT NOT NULL,
  `Carta_idCarta` INT UNSIGNED NOT NULL,
  PRIMARY KEY (`Personaje_idPersonaje`, `Carta_idCarta`),
  INDEX `fk_Personaje_has_Carta_Carta1_idx` (`Carta_idCarta` ASC) VISIBLE,
  INDEX `fk_Personaje_has_Carta_Personaje1_idx` (`Personaje_idPersonaje` ASC) VISIBLE,
  CONSTRAINT `fk_Personaje_has_Carta_Carta1`
    FOREIGN KEY (`Carta_idCarta`)
    REFERENCES `mydb`.`carta` (`idCarta`),
  CONSTRAINT `fk_Personaje_has_Carta_Personaje1`
    FOREIGN KEY (`Personaje_idPersonaje`)
    REFERENCES `mydb`.`personaje` (`idPersonaje`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
