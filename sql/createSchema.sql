CREATE SCHEMA animals;

CREATE TABLE animals.creature (id_creature BIGINT PRIMARY KEY,name text NOT NULL,type_id INT NOT NULL,age SMALLINT NOT NULL);

CREATE TABLE animals.animal_type (id_type INT PRIMARY KEY,type CHAR(50) NOT NULL,is_wild BOOLEAN NOT NULL);