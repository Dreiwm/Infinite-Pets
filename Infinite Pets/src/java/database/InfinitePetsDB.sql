
DROP SCHEMA IF EXISTS `infinitepetsdb` ;

CREATE SCHEMA IF NOT EXISTS `infinitepetsdb` DEFAULT CHARACTER SET latin1 ;
USE `infinitepetsdb` ;

CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`accounts` (
	`UserId` INT NOT NULL AUTO_INCREMENT,
	`Username` VARCHAR(30) NOT NULL,
	`Password` VARCHAR(30) NOT NULL,
	`Email` VARCHAR(100) UNIQUE NOT NULL,
	`FirstName` VARCHAR(50) NOT NULL,
	`LastName` VARCHAR(50) NOT NULL,
	`IsEmployee` BIT NOT NULL,
	`IsConfirmed` BIT NOT NULL,
	PRIMARY KEY (`UserId`)
)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`pets` (
	`PetID` INT NOT NULL AUTO_INCREMENT,
	`Sex` CHAR(1) NOT NULL,
	`Species` VARCHAR(20) NOT NULL,
	`Breed` VARCHAR(20) NOT NULL,
	`PetName` VARCHAR(30) NOT NULL,
	`Owner` INT NOT NULL,
	PRIMARY KEY (`PetID`),
	INDEX `fk_pets_accounts_idx` (`Owner` ASC),
	CONSTRAINT `fk_pets_accounts`
		FOREIGN KEY (`Owner`)
		REFERENCES `infinitepetsdb`.`accounts` (`UserId`)
		ON DELETE CASCADE
		ON UPDATE NO ACTION,
	CONSTRAINT `chk_pets_sex`
		CHECK (`Sex` IN ('M','F','N','S'))
)
ENGINE = InnoDB;


INSERT INTO `accounts` (`Username`,`Password`,`Email`,`FirstName`,`LastName`,`IsEmployee`,`IsConfirmed`)
	VALUES 
		('admin','password','cprg352+admin@gmail.com','Admin','Admin',1,1),
		('employee','password','cprg352+employee@gmail.com','employee','employee',1,1),
		('anne','password','cprg352+anne@gmail.com','Anne','Annerson',0,1),
		('barb','password','cprg352+barb@gmail.com','Barb','Barber',0,1);
		
		