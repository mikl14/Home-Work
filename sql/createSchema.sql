CREATE SCHEMA animals;

CREATE TABLE animals.creature (id_creature BIGINT PRIMARY KEY,name text NOT NULL,type_id INT NOT NULL,age SMALLINT NOT NULL);

CREATE TABLE animals.animal_type (id_type INT PRIMARY KEY,type CHAR(50) NOT NULL,is_wild BOOLEAN NOT NULL);

ALTER TABLE animals.creature ADD CONSTRAINT creature_type FOREIGN KEY (type_id) REFERENCES animals.animal_type (id_type);

CREATE TABLE animals.habitats (id_area INT PRIMARY KEY,area TEXT);

CREATE TABLE animals.animals_habitats (id_animal_type INT,id_area INT,
CONSTRAINT creature_type FOREIGN KEY (id_animal_type)  REFERENCES animals.animal_type (id_type),
CONSTRAINT area_type FOREIGN KEY (id_area)  REFERENCES animals.habitats (id_area)
);

CREATE TABLE animals.provider (id_provider INT PRIMARY KEY,name TEXT,phone CHAR(50));

CREATE TABLE animals.animals_provider (id_animal_type INT,id_provider INT,
CONSTRAINT animal_type FOREIGN KEY (id_animal_type)  REFERENCES animals.animal_type (id_type),
CONSTRAINT provide_id FOREIGN KEY (id_provider)  REFERENCES animals.provider (id_provider));

ALTER TABLE animals.animals_habitats ADD PRIMARY KEY (id_animal_type, id_area);

ALTER TABLE animals.animals_provider ADD PRIMARY KEY (id_animal_type, id_provider);

INSERT INTO animals.animal_type (id_type,type,is_wild) VALUES(1,'CAT',false);
INSERT INTO animals.animal_type (id_type,type,is_wild) VALUES(2,'FISH',false);
INSERT INTO animals.animal_type (id_type,type,is_wild) VALUES(3,'BEAR',true);
INSERT INTO animals.animal_type (id_type,type,is_wild) VALUES(4,'WOLF',true);

INSERT INTO animals.habitats (id_area,area) VALUES(1,'HOME');
INSERT INTO animals.habitats (id_area,area) VALUES(2,'FOREST');

INSERT INTO animals.creature (id_creature,name,type_id,age) VALUES(1,'Boris',1,12);
INSERT INTO animals.creature (id_creature,name,type_id,age) VALUES(2,'Goldie',2,5);
INSERT INTO animals.creature (id_creature,name,type_id,age) VALUES(3,'Beluga',3,7);
INSERT INTO animals.creature (id_creature,name,type_id,age) VALUES(4,'Grey',4,3);

INSERT INTO animals.animals_habitats (id_animal_type,id_area) VALUES(1,1);
INSERT INTO animals.animals_habitats (id_animal_type,id_area) VALUES(2,1);
INSERT INTO animals.animals_habitats (id_animal_type,id_area) VALUES(3,2);
INSERT INTO animals.animals_habitats (id_animal_type,id_area) VALUES(4,2);

INSERT INTO animals.provider (id_provider,name,phone) VALUES(1,'John','8800555');
INSERT INTO animals.provider (id_provider,name,phone) VALUES(2,'Ben','796421234');
INSERT INTO animals.provider (id_provider,name,phone) VALUES(3,'Nikolay','98620461');
INSERT INTO animals.provider (id_provider,name,phone) VALUES(4,'Masha','97856212');

INSERT INTO animals.animals_provider (id_animal_type,id_provider) VALUES(1,1);
INSERT INTO animals.animals_provider (id_animal_type,id_provider) VALUES(2,2);
INSERT INTO animals.animals_provider (id_animal_type,id_provider) VALUES(3,3);
INSERT INTO animals.animals_provider (id_animal_type,id_provider) VALUES(4,4);
