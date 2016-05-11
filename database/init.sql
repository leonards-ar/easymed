create database easymed;
grant all on easymed.* to 'easyme'@'%' identified by 'java1234';

CREATE TABLE `easymed`.`user` (
  `id_user` INT NOT NULL AUTO_INCREMENT,
  `dni` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_users`),
  UNIQUE INDEX `dni_UNIQUE` (`dni` ASC));

  CREATE TABLE `easymed`.`patient_user` (
    `id_patient` INT NOT NULL AUTO_INCREMENT,
    `user_id` INT NULL,
    `full_name` VARCHAR(100) NULL,
    `birth` DATE NULL,
    `phone` VARCHAR(45) NULL,
    `cell_phone` VARCHAR(45) NULL,
    `email` VARCHAR(45) NULL,
    `nationality` VARCHAR(45) NULL,
    `street` VARCHAR(100) NULL,
    `zipcode` VARCHAR(20) NULL,
    `city` VARCHAR(45) NULL,
    `state` VARCHAR(45) NULL,
    PRIMARY KEY (`id_patient`),
    INDEX `fk_patient_user_idx` (`user_id` ASC),
    CONSTRAINT `fk_patient_user`
      FOREIGN KEY (`user_id`)
      REFERENCES `easymed`.`user` (`id_user`)
      ON DELETE CASCADE
      ON UPDATE NO ACTION);
