-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema checklistdb
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `checklistdb` ;

-- -----------------------------------------------------
-- Schema checklistdb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `checklistdb` DEFAULT CHARACTER SET utf8 ;
USE `checklistdb` ;

-- -----------------------------------------------------
-- Table `check_list_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `check_list_type` ;

CREATE TABLE IF NOT EXISTS `check_list_type` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `user` ;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `email` VARCHAR(255) NULL,
  `username` VARCHAR(255) NULL,
  `password` VARCHAR(255) NULL,
  `enabled` TINYINT(4) NULL,
  `role` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
  UNIQUE INDEX `username_UNIQUE` (`username` ASC))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `check_list`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `check_list` ;

CREATE TABLE IF NOT EXISTS `check_list` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(100) NOT NULL,
  `description` TEXT NULL,
  `frequency` VARCHAR(200) NULL,
  `completed` TINYINT(4) NULL,
  `due_date` VARCHAR(45) NULL,
  `complete_date` VARCHAR(45) NULL,
  `check_list_type_id` INT NOT NULL,
  `user_id` INT NOT NULL,
  PRIMARY KEY (`id`),
  INDEX `fk_check_list_check_list_type_idx` (`check_list_type_id` ASC),
  INDEX `fk_check_list_user1_idx` (`user_id` ASC),
  CONSTRAINT `fk_check_list_check_list_type`
    FOREIGN KEY (`check_list_type_id`)
    REFERENCES `check_list_type` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_check_list_user1`
    FOREIGN KEY (`user_id`)
    REFERENCES `user` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

SET SQL_MODE = '';
DROP USER IF EXISTS checklistuser@localhost;
SET SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';
CREATE USER 'checklistuser'@'localhost' IDENTIFIED BY 'checklistuser';

GRANT SELECT, INSERT, TRIGGER, UPDATE, DELETE ON TABLE * TO 'checklistuser'@'localhost';

SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

-- -----------------------------------------------------
-- Data for table `check_list_type`
-- -----------------------------------------------------
START TRANSACTION;
USE `checklistdb`;
INSERT INTO `check_list_type` (`id`, `name`) VALUES (1, 'Personal Hygiene');
INSERT INTO `check_list_type` (`id`, `name`) VALUES (2, 'Meal');
INSERT INTO `check_list_type` (`id`, `name`) VALUES (3, 'Chores');
INSERT INTO `check_list_type` (`id`, `name`) VALUES (4, 'Work');
INSERT INTO `check_list_type` (`id`, `name`) VALUES (5, 'Bills');

COMMIT;


-- -----------------------------------------------------
-- Data for table `user`
-- -----------------------------------------------------
START TRANSACTION;
USE `checklistdb`;
INSERT INTO `user` (`id`, `email`, `username`, `password`, `enabled`, `role`) VALUES (1, 'john@aol.com', 'johnnyboy', '1234', 1, 'standard');

COMMIT;


-- -----------------------------------------------------
-- Data for table `check_list`
-- -----------------------------------------------------
START TRANSACTION;
USE `checklistdb`;
INSERT INTO `check_list` (`id`, `name`, `description`, `frequency`, `completed`, `due_date`, `complete_date`, `check_list_type_id`, `user_id`) VALUES (1, 'Brush Teeth', 'Brush and floss', 'Every morning', 1, 'Sep 30 2023', 'Sep 29 2023', 1, 1);
INSERT INTO `check_list` (`id`, `name`, `description`, `frequency`, `completed`, `due_date`, `complete_date`, `check_list_type_id`, `user_id`) VALUES (2, 'Shower', 'Shampoo, condition hair, wash body and face', 'Every morning', 1, 'Sep 30 2023', 'Sep 29 2023', 1, 1);

COMMIT;

