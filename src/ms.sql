-- MySQL Workbench Synchronization
-- Generated: 2017-11-24 18:56
-- Model: New Model
-- Version: 1.0
-- Project: Name of the project
-- Author: Marco Desantes Gutierrez

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE TABLE IF NOT EXISTS `Modelado_de_software`.`producto` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(45) NOT NULL,
  `proveedor` VARCHAR(45) NOT NULL,
  `stock` INT(11) NOT NULL,
  `precio` DOUBLE NOT NULL,
  `activo` TINYINT(4) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Modelado_de_software`.`cliente` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `nombre` VARCHAR(20) NOT NULL,
  `telefono` INT(9) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `activo` TINYINT(4) NOT NULL,
  `dni` VARCHAR(9) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC),
  UNIQUE INDEX `email_UNIQUE` (`email` ASC))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Modelado_de_software`.`particular` (
  `idParticular` INT(11) NOT NULL AUTO_INCREMENT,
  `apellido` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idParticular`),
  CONSTRAINT `idParticular`
    FOREIGN KEY (`idParticular`)
    REFERENCES `Modelado_de_software`.`cliente` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Modelado_de_software`.`empresa` (
  `idEmpresa` INT(11) NOT NULL,
  `sector` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idEmpresa`),
  CONSTRAINT `idEmpresa`
    FOREIGN KEY (`idEmpresa`)
    REFERENCES `Modelado_de_software`.`cliente` (`id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;


CREATE TABLE IF NOT EXISTS `Modelado_de_software`.`venta` (
  `id` INT(11) NOT NULL AUTO_INCREMENT,
  `fecha` DATE NOT NULL,
  `total` DOUBLE NOT NULL,
  `cliente` INT(11) NOT NULL,
  PRIMARY KEY (`id`, `cliente`),
  INDEX `fk_venta_cliente1_idx` (`cliente` ASC),
  CONSTRAINT `cliente`
    FOREIGN KEY (`cliente`)
    REFERENCES `Modelado_de_software`.`cliente` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;

CREATE TABLE IF NOT EXISTS `Modelado_de_software`.`linea_de_venta` (
  `venta` INT(11) NOT NULL,
  `producto` INT(11) NOT NULL,
  `cantidad` INT(11) NOT NULL,
  `precio` DOUBLE NOT NULL,
  PRIMARY KEY (`venta`, `producto`),
  INDEX `fk_Venta_has_producto_producto1_idx` (`producto` ASC),
  INDEX `fk_Venta_has_producto_Venta_idx` (`venta` ASC),
  CONSTRAINT `venta`
    FOREIGN KEY (`venta`)
    REFERENCES `Modelado_de_software`.`venta` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `producto`
    FOREIGN KEY (`producto`)
    REFERENCES `Modelado_de_software`.`producto` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8;



SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
