
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
-- INSERT INTO breed (animal_type_id, breed_name)
--     VALUES
--         (1, 'Labrador Retriever'),
--         (1, 'Golden Retriever'),
--         (2, 'tortoiseshell'),
--         (2, 'breed 2');

-- So, let's say customer chose animalt type of Dog (which will be id 1), 
-- then based on that, show customer all options with id 1 (whcih is dog)
-- custoem then select Golden Retriever
-- so now we have id 2 for breed_id.
-- we now know it is aniaml type of dog and breed of golden retriever.



-- Insert all of the dog breeds
INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Akita');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Alaskan Malamute');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'American Bulldog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'American Bully (Standard)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'American Eskimo Dog (Miniature)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'American Eskimo Dog (Standard)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Am. Staffordshire Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'American Pit Bull Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Australian Cattle Dog (Heeler)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Australian Kelpie');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Australian Shepherd');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Austrialian Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Barbet');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Basenji');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Basset Hound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Beagle');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Beauceron');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bedlington Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Belgian Malinois');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Belgian Tervuren');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bernese Mountain Dog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bichon Frise');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Black and Tan Coonhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bloodhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bluetick Coonhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Boerboel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Border Collie');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Border Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Boston Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bouvier des Flandres');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Boxer');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Boykin Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bracco Italiano');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Briard');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Brittany');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bull Terrier (Standard)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bull Terrier (Miniature)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bulldog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Bullmastiff');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Cairn Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Cane Corso');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Cardigan Welsh Corgi');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Catahoula Leopard Dog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Caucasian Shepherd (Ovcharka)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Cavalier King Charles Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Chesapeake Bay Retriever');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Chihuahua (Long hair)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Chihuahua (Smooth)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Chinese Crested');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Chinese Shar-Pei');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Chinook');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Chow Chow');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Clumber Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Cocker Spaniel (American)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Cocker Spaniel (English)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Collie (Smooth)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Collie (Rough)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Coton De Tulear');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Dachshund (smooth)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Dachshund (wire/long haired)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Dalmatian');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Doberman Pinscher');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Dogo Argentino');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Dutch Shepherd');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'English Setter');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'English Shepherd');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'English Springer Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'English Toy Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'English Toy Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Eurasier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Field Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Finnish Lapphund');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Finnish Spitz');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Flat Coat Retriever');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'French Bulldog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'German Pinscher');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'German Shepherd Dog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'German Shorthaired Pointer');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Giant Schnauzer');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Glen of Imaal Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Golden Retriever');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Gordon Setter');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Great Dane');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Great Pyrenees');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Greyhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Harrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Havanese');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Irish Setter');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Irish Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Irish Wolfhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Italian Greyhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Japanese Chin');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Japanese Spitz');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Keeshond');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Komondor');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Kooikerhondje');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Kuvasz');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Labrador Retriever');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Lagotto Romagnolo');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Lancashire Heeler');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Leonberger');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Lhasa Apso');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Maltese');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Miniature American Shepherd');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Miniature Pinscher');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Miniature Schnauzer');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Newfoundland');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Norfolk Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Norwich Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Nova Scotia Duck Tolling Retriever');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Olde English Bulldogge');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Old English Sheepdog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Papillon');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Parson Russell Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Patterdale Terrier (Smooth or Broken)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Patterdale Terrier (Rough)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Pekingese');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Pembroke Welsh Corgi');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Pharaoh Hound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Plott');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Pointer (English)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Pomeranian');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Poodle (Miniature)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Poodle (Standard)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Poodle (Toy)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Portugese Water Dog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Presa Canario');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Pug');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Puli');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Pumi');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Rat Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Redbone Coonhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Rhodesian Ridgeback');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Rottweiler');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Russian Toy');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Saluki');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Samoyed');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Schipperke');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Scottish Deerhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Scottish Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Shetland Sheepdog (Sheltie)');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Shiba Inu');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Shih Tzu');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Shiloh Shepherd');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Siberian Husky');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Silky Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Smooth Fox Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Soft Coated Wheaten Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Spanish Water Dog');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Spinone Italiano');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'St. Bernard');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Staffordshire Bull Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Standard Schnauzer');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Swedish Vallhund');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Thai Ridgeback');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Tibetan Mastiff');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Tibetan Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Tibetan Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Toy Fox Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Treeing Walker Coonhound');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Vizsla');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Weimaraner');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Welsh Springer Spaniel');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'West Highland White Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Whippet');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'White Shepherd');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Wire Fox Terrier');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Wirehaired Pointing Griffon');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Xoloitzcuintli');

INSERT INTO breed (animal_type_id, breed_name)
	VALUES (1, 'Yorkshire Terrier');
