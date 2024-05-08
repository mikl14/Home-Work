CREATE TABLE IF NOT EXISTS animals.animal (
id integer NOT NULL,
birth_date date,
"character" text,
cost numeric(19,2),
name character varying(255),
type integer,
breed integer
);

CREATE TABLE animals.animal_type (
id_type INT PRIMARY KEY,
type CHAR(50) NOT NULL,
is_wild BOOLEAN NOT NULL
);

ALTER TABLE animals.animal ADD CONSTRAINT animal_type FOREIGN KEY (type) REFERENCES animals.animal_type (id_type);

CREATE TABLE animals.habitats (
id_area INT PRIMARY KEY,
area TEXT
);

CREATE TABLE animals.animals_habitats (
id_animal_type INT,
id_area INT,
CONSTRAINT creature_type FOREIGN KEY (id_animal_type)  REFERENCES animals.animal_type (id_type),
CONSTRAINT area_type FOREIGN KEY (id_area)  REFERENCES animals.habitats (id_area)
);

CREATE TABLE animals.provider (
id_provider INT PRIMARY KEY,
name TEXT,
phone CHAR(50)
);

CREATE TABLE animals.animals_provider (
id_animal_type INT,
id_provider INT,
CONSTRAINT animal_type FOREIGN KEY (id_animal_type)  REFERENCES animals.animal_type (id_type),
CONSTRAINT provide_id FOREIGN KEY (id_provider)  REFERENCES animals.provider (id_provider)
);

ALTER TABLE animals.animals_habitats ADD PRIMARY KEY (id_animal_type, id_area);

ALTER TABLE animals.animals_provider ADD PRIMARY KEY (id_animal_type, id_provider);
