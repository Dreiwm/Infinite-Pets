
DROP SCHEMA IF EXISTS `infinitepetsdb` ;

CREATE SCHEMA IF NOT EXISTS `infinitepetsdb` DEFAULT CHARACTER SET latin1 ;
USE `infinitepetsdb` ;

-- Account
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`account` (
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

-- Pet
-- Preferred Vet contains 60 chars to account for fulll name.
CREATE TABLE IF NOT EXISTS `infinitepetsdb`.`pet` (
    `PetID` INT NOT NULL AUTO_INCREMENT,
    `Sex` CHAR(1) NOT NULL,
    `Species` VARCHAR(20) NOT NULL,
    `Breed` VARCHAR(60) NOT NULL,
    `PetName` VARCHAR(30) NOT NULL,
    `Owner` INT NOT NULL,
    `Birthday` DATE NOT NULL,
    `Preferred Vet` VARCHAR(60),
    `MedicalInfo` VARCHAR,
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

INSERT INTO `account` (`Username`,`Password`,`Email`,`FirstName`,`LastName`,`IsEmployee`,`IsConfirmed`)
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
		
-- Insert all of the dog breeds
INSERT INTO breed (animal_type_id, breed_name)
    VALUES (1, 'Akita'),
        (1, 'Alaskan Malamute'),
        (1, 'American Bulldog'),
        (1, 'American Bully (Standard)'),
        (1, 'American Eskimo Dog (Miniature)'),
        (1, 'American Eskimo Dog (Standard)'),
        (1, 'Am. Staffordshire Terrier'),
        (1, 'American Pit Bull Terrier'),
        (1, 'Australian Cattle Dog (Heeler)'),
        (1, 'Australian Kelpie'),
        (1, 'Australian Shepherd'),
        (1, 'Austrialian Terrier'),
        (1, 'Barbet'),
        (1, 'Basenji'),
        (1, 'Basset Hound'),
        (1, 'Beagle'),
        (1, 'Beauceron'),
        (1, 'Bedlington Terrier'),
        (1, 'Belgian Malinois'),
        (1, 'Belgian Tervuren'),
        (1, 'Bernese Mountain Dog'),
        (1, 'Bichon Frise'),
        (1, 'Black and Tan Coonhound'),
        (1, 'Bloodhound'),
        (1, 'Bluetick Coonhound'),
        (1, 'Boerboel'),
        (1, 'Border Collie'),
        (1, 'Border Terrier'),
        (1, 'Boston Terrier'),
        (1, 'Bouvier des Flandres'),
        (1, 'Boxer'),
        (1, 'Boykin Spaniel'),
        (1, 'Bracco Italiano'),
        (1, 'Briard'),
        (1, 'Brittany'),
        (1, 'Bull Terrier (Standard)'),
        (1, 'Bull Terrier (Miniature)'),
        (1, 'Bulldog'),
        (1, 'Bullmastiff'),
        (1, 'Cairn Terrier'),
        (1, 'Cane Corso'),
        (1, 'Cardigan Welsh Corgi'),
        (1, 'Catahoula Leopard Dog'),
        (1, 'Caucasian Shepherd (Ovcharka)'),
        (1, 'Cavalier King Charles Spaniel'),
        (1, 'Chesapeake Bay Retriever'),
        (1, 'Chihuahua (Long hair)'),
        (1, 'Chihuahua (Smooth)'),
        (1, 'Chinese Crested'),
        (1, 'Chinese Shar-Pei'),
        (1, 'Chinook'),
        (1, 'Chow Chow'),
        (1, 'Clumber Spaniel'),
        (1, 'Cocker Spaniel (American)'),
        (1, 'Cocker Spaniel (English)'),
        (1, 'Collie (Smooth)'),
        (1, 'Collie (Rough)'),
        (1, 'Coton De Tulear'),
        (1, 'Dachshund (smooth)'),
        (1, 'Dachshund (wire/long haired)'),
        (1, 'Dalmatian'),
        (1, 'Doberman Pinscher'),
        (1, 'Dogo Argentino'),
        (1, 'Dutch Shepherd'),
        (1, 'English Setter'),
        (1, 'English Shepherd'),
        (1, 'English Springer Spaniel'),
        (1, 'English Toy Spaniel'),
        (1, 'English Toy Terrier'),
        (1, 'Eurasier'),
        (1, 'Field Spaniel'),
        (1, 'Finnish Lapphund'),
        (1, 'Finnish Spitz'),
        (1, 'Flat Coat Retriever'),
        (1, 'French Bulldog'),
        (1, 'German Pinscher'),
        (1, 'German Shepherd Dog'),
        (1, 'German Shorthaired Pointer'),
        (1, 'Giant Schnauzer'),
        (1, 'Glen of Imaal Terrier'),
        (1, 'Golden Retriever'),
        (1, 'Gordon Setter'),
        (1, 'Great Dane'),
        (1, 'Great Pyrenees'),
        (1, 'Greyhound'),
        (1, 'Harrier'),
        (1, 'Havanese'),
        (1, 'Irish Setter'),
        (1, 'Irish Terrier'),
        (1, 'Irish Wolfhound'),
        (1, 'Italian Greyhound'),
        (1, 'Japanese Chin'),
        (1, 'Japanese Spitz'),
        (1, 'Keeshond'),
        (1, 'Komondor'),
        (1, 'Kooikerhondje'),
        (1, 'Kuvasz'),
        (1, 'Labrador Retriever'),
        (1, 'Lagotto Romagnolo'),
        (1, 'Lancashire Heeler'),
        (1, 'Leonberger'),
        (1, 'Lhasa Apso'),
        (1, 'Maltese'),
        (1, 'Miniature American Shepherd'),
        (1, 'Miniature Pinscher'),
        (1, 'Miniature Schnauzer'),
        (1, 'Newfoundland'),
        (1, 'Norfolk Terrier'),
        (1, 'Norwich Terrier'),
        (1, 'Nova Scotia Duck Tolling Retriever'),
        (1, 'Olde English Bulldogge'),
        (1, 'Old English Sheepdog'),
        (1, 'Papillon'),
        (1, 'Parson Russell Terrier'),
        (1, 'Patterdale Terrier (Smooth or Broken)'),
        (1, 'Patterdale Terrier (Rough)'),
        (1, 'Pekingese'),
        (1, 'Pembroke Welsh Corgi'),
        (1, 'Pharaoh Hound'),
        (1, 'Plott'),
        (1, 'Pointer (English)'),
        (1, 'Pomeranian'),
        (1, 'Poodle (Miniature)'),
        (1, 'Poodle (Standard)'),
        (1, 'Poodle (Toy)'),
        (1, 'Portugese Water Dog'),
        (1, 'Presa Canario'),
        (1, 'Pug'),
        (1, 'Puli'),
        (1, 'Pumi'),
        (1, 'Rat Terrier'),
        (1, 'Redbone Coonhound'),
        (1, 'Rhodesian Ridgeback'),
        (1, 'Rottweiler'),
        (1, 'Russian Toy'),
        (1, 'Saluki'),
        (1, 'Samoyed'),
        (1, 'Schipperke'),
        (1, 'Scottish Deerhound'),
        (1, 'Scottish Terrier'),
        (1, 'Shetland Sheepdog (Sheltie)'),
        (1, 'Shiba Inu'),
        (1, 'Shih Tzu'),
        (1, 'Shiloh Shepherd'),
        (1, 'Siberian Husky'),
        (1, 'Silky Terrier'),
        (1, 'Smooth Fox Terrier'),
        (1, 'Soft Coated Wheaten Terrier'),
        (1, 'Spanish Water Dog'),
        (1, 'Spinone Italiano'),
        (1, 'St. Bernard'),
        (1, 'Staffordshire Bull Terrier'),
        (1, 'Standard Schnauzer'),
        (1, 'Swedish Vallhund'),
        (1, 'Thai Ridgeback'),
        (1, 'Tibetan Mastiff'),
        (1, 'Tibetan Terrier'),
        (1, 'Toy Fox Terrier'),
        (1, 'Treeing Walker Coonhound'),
        (1, 'Vizsla'),
        (1, 'Weimaraner'),
        (1, 'Welsh Springer Spaniel'),
        (1, 'West Highland White Terrier'),
        (1, 'Whippet'),
        (1, 'White Shepherd'),
        (1, 'Wire Fox Terrier'),
        (1, 'Wirehaired Pointing Griffon'),
        (1, 'Xoloitzcuintli'),
        (1, 'Yorkshire Terrier');		
