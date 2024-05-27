ALTER TABLE animals.animal ADD CONSTRAINT ani_type FOREIGN KEY (type) REFERENCES animals.animal_type (id_type);

ALTER TABLE animals.animals_habitats ADD PRIMARY KEY (id_animal_type, id_area);

ALTER TABLE animals.animals_provider ADD PRIMARY KEY (id_animal_type, id_provider);

ALTER TABLE animals.animals_habitats ADD CONSTRAINT animal_type FOREIGN KEY (id_animal_type)  REFERENCES animals.animal_type (id_type);

ALTER TABLE animals.animals_habitats ADD CONSTRAINT area_type FOREIGN KEY (id_area)  REFERENCES animals.habitats (id_area);

ALTER TABLE animals.animals_provider ADD CONSTRAINT animal_type FOREIGN KEY (id_animal_type)  REFERENCES animals.animal_type (id_type);

ALTER TABLE animals.animals_provider ADD CONSTRAINT provide_id FOREIGN KEY (id_provider)  REFERENCES animals.provider (id_provider);
