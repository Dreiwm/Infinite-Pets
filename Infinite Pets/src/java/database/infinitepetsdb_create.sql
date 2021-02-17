
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

-- Animal Type Table
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`animal_type` (
    `animal_Type_ID` INT NOT NULL AUTO_INCREMENT,
    `animal_Type` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`animal_Type_ID`)
)

ENGINE = innoDB;

-- Breed 
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`breed` (
    breed_id INT NOT NULL AUTO_INCREMENT,
    `animal_type_id` INT NOT NULL,
    breed_name VARCHAR(30),
    PRIMARY KEY (breed_id, animal_type_id),
    CONSTRAINT fk_breed_animal_type
        FOREIGN KEY (animal_type_id)
        REFERENCES infinitepetsdb.animal_type (animal_type_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
)


ENGINE = innoDB;

CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`pets` (
	`PetID` INT NOT NULL AUTO_INCREMENT,
	`Sex` CHAR(1) NOT NULL,
	`Animal_type_breed_id` INT NOT NULL,
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
		CHECK (`Sex` IN ('M','F','N','S')),
        CONSTRAINT fk_animal_type_breed
            FOREIGN KEY (Animal_type_breed_id)
            REFERENCES infinitepetsdb.breed (breed_id)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION
)
ENGINE = InnoDB;


INSERT INTO `accounts` (`Username`,`Password`,`Email`,`FirstName`,`LastName`,`IsEmployee`,`IsConfirmed`)
	VALUES 
		('admin','password','cprg352+admin@gmail.com','Admin','Admin',1,1),
		('employee','password','cprg352+employee@gmail.com','employee','employee',1,1),
		('anne','password','cprg352+anne@gmail.com','Anne','Annerson',0,1),
		('barb','password','cprg352+barb@gmail.com','Barb','Barber',0,1);
		
		
-- insert into animal_type
INSERT INTO animal_type (animal_type)
    VALUES 
        ('Dog'),
        ('Cat'),
        ('Bird');

-- insert into breed table
INSERT INTO breed (animal_type_id, breed_name)
    VALUES
        (1, 'Labrador Retriever'),
        (1, 'Golden Retriever'),
        (2, 'tortoiseshell'),
        (2, 'breed 2');

-- So, let's say customer chose animalt type of Dog (which will be id 1), 
-- then based on that, show customer all options with id 1 (whcih is dog)
-- custoem then select Golden Retriever
-- so now we have id 2 for breed_id.
-- we now know it is aniaml type of dog and breed of golden retriever.
