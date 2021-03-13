
DROP SCHEMA IF EXISTS `infinitepetsdb` ;

CREATE SCHEMA IF NOT EXISTS `infinitepetsdb` DEFAULT CHARACTER SET latin1 ;
USE `infinitepetsdb` ;

-- ServiceType
-- To be used in Service and employee tables.
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.serviceType (
	`ServiceTypeID` INT NOT NULL auto_increment,
        `ServiceType` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`ServiceTypeID`)
)
ENGINE=InnoDB;

-- Services
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`service` (
    `ServiceID` INT NOT NULL AUTO_INCREMENT,
    `ServiceTypeID` INT NOT NULL,
    `ServiceName` VARCHAR(40) NOT NULL,
    `ServiceDescription` TEXT CHARACTER SET utf16 COLLATE utf16_bin, -- 64 KB blob of text storage. will hold something like 2000 words,
    `BasePrice` DECIMAL(6,2) NOT NULL, -- can hold up to 9999.99, not that I expect anything to be more than 300, but youknow.
    `Active` BIT NOT NULL, -- currently available
    `SpecifyPet` BIT NOT NULL, -- if the client needs to specify what pet will have the service, things like pet/house sitting, don't I'd imagine.
    `DateRange` BIT NOT NULL, -- if it's a long term thing, again, the sitting
    PRIMARY KEY (`ServiceID`),
    CONSTRAINT `fk_service_type`
            FOREIGN KEY (ServiceTypeID)
    REFERENCES infinitepetsdb.serviceType (ServiceTypeID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- Promotion
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`promotion` (
    `PromoID` INT NOT NULL AUTO_INCREMENT,
    `PromotionName` VARCHAR (50) NOT NULL,
    `PromoDescription` VARCHAR (200) NOT NULL,
    `StartDate` Date NOT NULL,
    `EndDate` Date NOT NULL,
    `Active` Boolean NOT NULL,
    PRIMARY KEY (`PromoID`)
)
ENGINE = InnoDB;

-- Discount
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`discount` (
    `DiscountID` INT NOT NULL AUTO_INCREMENT,
    `ServiceID` INT NOT NULL,
    `PromoID` INT NOT NULL,
    `Discount` DECIMAL(6,2) NOT NULL,
    `DiscountType` CHAR(1) NOT NULL,
    PRIMARY KEY (`DiscountID`),
    INDEX `fk_service_idx` (`ServiceID` ASC),
    CONSTRAINT `fk_service_id`
            FOREIGN KEY (`ServiceID`)
            REFERENCES `infinitepetsdb`.`service` (`ServiceID`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    INDEX `fk_promo_idx` (`PromoID` ASC),
    CONSTRAINT `fk_promo_id`
            FOREIGN KEY (`PromoID`)
            REFERENCES `infinitepetsdb`.`promotion` (`PromoID`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- Location
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`location` (
    `LocationID` INT NOT NULL AUTO_INCREMENT,
    `LocationType` CHAR(1) NOT NULL,
    `PostalCode` VARCHAR(6) NOT NULL,
    `Address` VARCHAR(30) NOT NULL,
    `City` VARCHAR(30) NOT NULL,
    `Country` VARCHAR(30) NOT NULL,
    `Province` VARCHAR(30) NOT NULL,
    `Area` VARCHAR(2) NOT NULL,
    CONSTRAINT `chk_area`
            CHECK (`Area` IN ('N','S', 'W', 'E', 'NW', 'NE', 'SW', 'SE')),
    PRIMARY KEY (`LocationID`)
    )
ENGINE = InnoDB;

-- Account
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`account` (
    `UserId` INT NOT NULL AUTO_INCREMENT,
    `Password` VARCHAR(30) NOT NULL,
    `Email` VARCHAR(100) UNIQUE NOT NULL,
    `FirstName` VARCHAR(50) NOT NULL,
    `LastName` VARCHAR(50) NOT NULL,
    `IsEmployee` BIT NOT NULL,
    `IsConfirmed` BIT NOT NULL,
    PRIMARY KEY (`UserId`)
)
ENGINE = InnoDB;

-- QualificationType
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`empQualificationType` (
    `QualificationTypeID` INT NOT NULL AUTO_INCREMENT,
    `QualificationName` VARCHAR(50) NOT NULL,
    `QualificationDescription` VARCHAR(100) NOT NULL,
    PRIMARY KEY (`QualificationTypeID`)
)
ENGINE = InnoDB;

-- Employee
-- this is just temporary, it's not complete.
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`employee` (
    `EmployeeID` INT NOT NULL AUTO_INCREMENT,
    `UserID` INT NOT NULL,
    `IsAdmin` BIT NOT NULL,
    `OnVacation` BIT NOT NULL,
    `Active` BIT NOT NULL,
    PRIMARY KEY (`EmployeeID`),
	INDEX `fk_employees_accounts_idx` (`UserId` ASC),
    CONSTRAINT `fk_employees_accounts`
        FOREIGN KEY (`UserId`)
    REFERENCES `infinitepetsdb`.`account` (`UserId`)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;

-- Employee Service Preferences
-- This table will be referring (FK) to ServiceType and Employee
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`empServicePreference` (
    `EmployeeID` INT NOT NULL,
    `ServiceTypeID` INT NOT NULL,
    PRIMARY KEY (EmployeeID, ServiceTypeID),
    INDEX `fk_employee_idx` (`EmployeeID` ASC),
    CONSTRAINT `fk_employee_id`
        FOREIGN KEY (EmployeeID)
    REFERENCES infinitepetsdb.employee (EmployeeID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    INDEX `fk_serviceType_idx` (`ServiceTypeID` ASC),
    CONSTRAINT `fk_serviceType_id`
        FOREIGN KEY (ServiceTypeID)
    REFERENCES infinitepetsdb.serviceType (ServiceTypeID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB;

-- Employee Qualifications
-- This table will be referring (FK) to ServiceType and Employee
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`empQualification` (
    `EmployeeID` INT NOT NULL,
    `QualificationID` INT NOT NULL,
    PRIMARY KEY (EmployeeID, QualificationID),
    INDEX `fk_employeeidx` (`EmployeeID` ASC),
    CONSTRAINT `fk_employeeid`
        FOREIGN KEY (EmployeeID)
    REFERENCES infinitepetsdb.employee (EmployeeID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION,
    INDEX `fk_qualificationidx` (`QualificationID` ASC),
    CONSTRAINT `fk_qualificationid`
        FOREIGN KEY (QualificationID)
    REFERENCES infinitepetsdb.empQualificationType (QualificationTypeID)
    ON DELETE CASCADE
    ON UPDATE NO ACTION
    )
ENGINE = InnoDB;

-- Animal Type Table
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`animal_type` (
    `animal_Type_ID` INT NOT NULL AUTO_INCREMENT,
    `animal_Type` VARCHAR(30) NOT NULL,
    PRIMARY KEY (`animal_Type_ID`)
)
ENGINE = InnoDB;

-- Breed 
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`breed` (
    breed_id INT NOT NULL AUTO_INCREMENT,
    `animal_type_id` INT NOT NULL,
    breed_name VARCHAR(50),
    PRIMARY KEY (breed_id, animal_type_id),
    CONSTRAINT fk_breed_animal_type
        FOREIGN KEY (animal_type_id)
        REFERENCES infinitepetsdb.animal_type (animal_type_id)
        ON DELETE CASCADE
        ON UPDATE NO ACTION
)
ENGINE = innoDB;

-- Pet
-- Preferred Vet contains 60 chars to account for full name.
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`pet` (
    `PetID` INT NOT NULL AUTO_INCREMENT,
    `Sex` CHAR(1) NOT NULL,
    `Species` VARCHAR(20) NOT NULL,
    `Breed` VARCHAR(60) NOT NULL,
    `PetName` VARCHAR(30) NOT NULL,
    `Owner` INT NOT NULL,
    `Birthday` DATE NOT NULL,
    `PreferredVet` VARCHAR(60),
    `MedicalInfo` VARCHAR(120),
    PRIMARY KEY (`PetID`),
    INDEX `fk_pets_accounts_idx` (`Owner` ASC),
    CONSTRAINT `fk_pets_accounts`
            FOREIGN KEY (`Owner`)
            REFERENCES `infinitepetsdb`.`account` (`UserId`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
    CONSTRAINT `chk_pets_sex`
            CHECK (`Sex` IN ('M','F','N','S'))
)
ENGINE = InnoDB;

-- Appointment
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`appointment` (
	`AppointmentID` INT NOT NULL AUTO_INCREMENT,
	`ClientID` INT NOT NULL,
	`ServiceID` INT NOT NULL,
	`PetID` INT DEFAULT NULL,
	`AppointmentDate` DATE NOT NULL,
	`EndDate` DATE DEFAULT NULL, -- used for range
	`AppointmentTime` TIME DEFAULT NULL,
	`Confirmed` BOOLEAN NOT NULL,
	`EmployeeID` INT DEFAULT NULL,
	`Paid` BOOLEAN NOT NULL,
	`Active` BOOLEAN NOT NULL,
	PRIMARY KEY (`appointmentID`),
	INDEX `fk_appointments_clients_idx` (`ClientID` ASC),
    CONSTRAINT `fk_appointments_clients`
            FOREIGN KEY (`ClientID`)
            REFERENCES `infinitepetsdb`.`account` (`UserId`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION,
	INDEX `fk_appointments_employees_idx` (`EmployeeID` ASC),
    CONSTRAINT `fk_appointments_employees`
            FOREIGN KEY (`EmployeeID`)
            REFERENCES `infinitepetsdb`.`employee` (`EmployeeID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
	INDEX `fk_appointments_services_idx` (`ServiceID` ASC),
    CONSTRAINT `fk_appointments_services`
            FOREIGN KEY (`ServiceID`)
            REFERENCES `infinitepetsdb`.`service` (`ServiceID`)
            ON DELETE NO ACTION
            ON UPDATE NO ACTION,
	INDEX `fk_appointments_pets_idx` (`PetID` ASC),
    CONSTRAINT `fk_appointments_pets`
            FOREIGN KEY (`PetID`)
            REFERENCES `infinitepetsdb`.`pet` (`PetID`)
            ON DELETE CASCADE
            ON UPDATE NO ACTION
) 
ENGINE = InnoDB;

-- Content Management
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`management` (
    `ItemID` INT NOT NULL AUTO_INCREMENT,
    `ItemName` VARCHAR(40),
    `ItemDescription` VARCHAR(100),
    `ItemLink` VARCHAR(200),
    `ItemPrice` DECIMAL(6,2),
    PRIMARY KEY (`ItemID`)
)
ENGINE = InnoDB;

INSERT INTO `account` (`Username`,`Password`,`Email`,`FirstName`,`LastName`,`IsEmployee`,`IsConfirmed`)
    VALUES 
        ('admin','password','cprg352+admin@gmail.com','Admin','Admin',1,1),
        ('employee','password','cprg352+employee@gmail.com','employee','employee',1,1),
        ('anne','password','cprg352+anne@gmail.com','Anne','Annerson',0,1),
        ('barb','password','cprg352+barb@gmail.com','Barb','Barber',0,1);

-- insert into animal_type
INSERT INTO animal_Type (animal_Type)
    VALUES 
        ('Dog'),
        ('Cat'),
        ('Bird');
		
-- Insert all of the dog breeds
SELECT `animal_Type_ID`
	INTO @dog
	FROM `animal_Type`
	WHERE `animal_Type` = 'Dog';
	
INSERT INTO breed (animal_type_id, breed_name)
    VALUES (@dog, 'Akita'),
        (@dog, 'Alaskan Malamute'),
        (@dog, 'American Bulldog'),
        (@dog, 'American Bully (Standard)'),
        (@dog, 'American Eskimo Dog (Miniature)'),
        (@dog, 'American Eskimo Dog (Standard)'),
        (@dog, 'Am. Staffordshire Terrier'),
        (@dog, 'American Pit Bull Terrier'),
        (@dog, 'Australian Cattle Dog (Heeler)'),
        (@dog, 'Australian Kelpie'),
        (@dog, 'Australian Shepherd'),
        (@dog, 'Austrialian Terrier'),
        (@dog, 'Barbet'),
        (@dog, 'Basenji'),
        (@dog, 'Basset Hound'),
        (@dog, 'Beagle'),
        (@dog, 'Beauceron'),
        (@dog, 'Bedlington Terrier'),
        (@dog, 'Belgian Malinois'),
        (@dog, 'Belgian Tervuren'),
        (@dog, 'Bernese Mountain Dog'),
        (@dog, 'Bichon Frise'),
        (@dog, 'Black and Tan Coonhound'),
        (@dog, 'Bloodhound'),
        (@dog, 'Bluetick Coonhound'),
        (@dog, 'Boerboel'),
        (@dog, 'Border Collie'),
        (@dog, 'Border Terrier'),
        (@dog, 'Boston Terrier'),
        (@dog, 'Bouvier des Flandres'),
        (@dog, 'Boxer'),
        (@dog, 'Boykin Spaniel'),
        (@dog, 'Bracco Italiano'),
        (@dog, 'Briard'),
        (@dog, 'Brittany'),
        (@dog, 'Bull Terrier (Standard)'),
        (@dog, 'Bull Terrier (Miniature)'),
        (@dog, 'Bulldog'),
        (@dog, 'Bullmastiff'),
        (@dog, 'Cairn Terrier'),
        (@dog, 'Cane Corso'),
        (@dog, 'Cardigan Welsh Corgi'),
        (@dog, 'Catahoula Leopard Dog'),
        (@dog, 'Caucasian Shepherd (Ovcharka)'),
        (@dog, 'Cavalier King Charles Spaniel'),
        (@dog, 'Chesapeake Bay Retriever'),
        (@dog, 'Chihuahua (Long hair)'),
        (@dog, 'Chihuahua (Smooth)'),
        (@dog, 'Chinese Crested'),
        (@dog, 'Chinese Shar-Pei'),
        (@dog, 'Chinook'),
        (@dog, 'Chow Chow'),
        (@dog, 'Clumber Spaniel'),
        (@dog, 'Cocker Spaniel (American)'),
        (@dog, 'Cocker Spaniel (English)'),
        (@dog, 'Collie (Smooth)'),
        (@dog, 'Collie (Rough)'),
        (@dog, 'Coton De Tulear'),
        (@dog, 'Dachshund (smooth)'),
        (@dog, 'Dachshund (wire/long haired)'),
        (@dog, 'Dalmatian'),
        (@dog, 'Doberman Pinscher'),
        (@dog, 'Dogo Argentino'),
        (@dog, 'Dutch Shepherd'),
        (@dog, 'English Setter'),
        (@dog, 'English Shepherd'),
        (@dog, 'English Springer Spaniel'),
        (@dog, 'English Toy Spaniel'),
        (@dog, 'English Toy Terrier'),
        (@dog, 'Eurasier'),
        (@dog, 'Field Spaniel'),
        (@dog, 'Finnish Lapphund'),
        (@dog, 'Finnish Spitz'),
        (@dog, 'Flat Coat Retriever'),
        (@dog, 'French Bulldog'),
        (@dog, 'German Pinscher'),
        (@dog, 'German Shepherd Dog'),
        (@dog, 'German Shorthaired Pointer'),
        (@dog, 'Giant Schnauzer'),
        (@dog, 'Glen of Imaal Terrier'),
        (@dog, 'Golden Retriever'),
        (@dog, 'Gordon Setter'),
        (@dog, 'Great Dane'),
        (@dog, 'Great Pyrenees'),
        (@dog, 'Greyhound'),
        (@dog, 'Harrier'),
        (@dog, 'Havanese'),
        (@dog, 'Irish Setter'),
        (@dog, 'Irish Terrier'),
        (@dog, 'Irish Wolfhound'),
        (@dog, 'Italian Greyhound'),
        (@dog, 'Japanese Chin'),
        (@dog, 'Japanese Spitz'),
        (@dog, 'Keeshond'),
        (@dog, 'Komondor'),
        (@dog, 'Kooikerhondje'),
        (@dog, 'Kuvasz'),
        (@dog, 'Labrador Retriever'),
        (@dog, 'Lagotto Romagnolo'),
        (@dog, 'Lancashire Heeler'),
        (@dog, 'Leonberger'),
        (@dog, 'Lhasa Apso'),
        (@dog, 'Maltese'),
        (@dog, 'Miniature American Shepherd'),
        (@dog, 'Miniature Pinscher'),
        (@dog, 'Miniature Schnauzer'),
        (@dog, 'Newfoundland'),
        (@dog, 'Norfolk Terrier'),
        (@dog, 'Norwich Terrier'),
        (@dog, 'Nova Scotia Duck Tolling Retriever'),
        (@dog, 'Olde English Bulldogge'),
        (@dog, 'Old English Sheepdog'),
        (@dog, 'Papillon'),
        (@dog, 'Parson Russell Terrier'),
        (@dog, 'Patterdale Terrier (Smooth or Broken)'),
        (@dog, 'Patterdale Terrier (Rough)'),
        (@dog, 'Pekingese'),
        (@dog, 'Pembroke Welsh Corgi'),
        (@dog, 'Pharaoh Hound'),
        (@dog, 'Plott'),
        (@dog, 'Pointer (English)'),
        (@dog, 'Pomeranian'),
        (@dog, 'Poodle (Miniature)'),
        (@dog, 'Poodle (Standard)'),
        (@dog, 'Poodle (Toy)'),
        (@dog, 'Portugese Water Dog'),
        (@dog, 'Presa Canario'),
        (@dog, 'Pug'),
        (@dog, 'Puli'),
        (@dog, 'Pumi'),
        (@dog, 'Rat Terrier'),
        (@dog, 'Redbone Coonhound'),
        (@dog, 'Rhodesian Ridgeback'),
        (@dog, 'Rottweiler'),
        (@dog, 'Russian Toy'),
        (@dog, 'Saluki'),
        (@dog, 'Samoyed'),
        (@dog, 'Schipperke'),
        (@dog, 'Scottish Deerhound'),
        (@dog, 'Scottish Terrier'),
        (@dog, 'Shetland Sheepdog (Sheltie)'),
        (@dog, 'Shiba Inu'),
        (@dog, 'Shih Tzu'),
        (@dog, 'Shiloh Shepherd'),
        (@dog, 'Siberian Husky'),
        (@dog, 'Silky Terrier'),
        (@dog, 'Smooth Fox Terrier'),
        (@dog, 'Soft Coated Wheaten Terrier'),
        (@dog, 'Spanish Water Dog'),
        (@dog, 'Spinone Italiano'),
        (@dog, 'St. Bernard'),
        (@dog, 'Staffordshire Bull Terrier'),
        (@dog, 'Standard Schnauzer'),
        (@dog, 'Swedish Vallhund'),
        (@dog, 'Thai Ridgeback'),
        (@dog, 'Tibetan Mastiff'),
        (@dog, 'Tibetan Terrier'),
        (@dog, 'Toy Fox Terrier'),
        (@dog, 'Treeing Walker Coonhound'),
        (@dog, 'Vizsla'),
        (@dog, 'Weimaraner'),
        (@dog, 'Welsh Springer Spaniel'),
        (@dog, 'West Highland White Terrier'),
        (@dog, 'Whippet'),
        (@dog, 'White Shepherd'),
        (@dog, 'Wire Fox Terrier'),
        (@dog, 'Wirehaired Pointing Griffon'),
        (@dog, 'Xoloitzcuintli'),
        (@dog, 'Yorkshire Terrier');		
