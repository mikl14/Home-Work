INSERT INTO animals.animal_type
(id_type,type,is_wild)
VALUES
(1,'CAT',false),
(2,'FISH',false),
(3,'BEAR',true),
(4,'WOLF',true);

INSERT INTO animals.habitats
(id_area,area)
VALUES
(1,'HOME'),
(2,'FOREST');

INSERT INTO animals.animal
(name,birth_date,"character",cost,type)
VALUES
('Boris','2023-05-09','Bad',200,3),
('Goldie','2002-01-01','Silence',50,2),
('Beluga','2001-07-03','White',5000,1),
('Tomas','2004-05-01','Beer Bear',250,1),
('Grey','2007-04-02','Grey',400,4);

INSERT INTO animals.animals_habitats
(id_animal_type,id_area)
VALUES
(1,1),
(2,1),
(3,2),
(4,2);

INSERT INTO animals.provider
(id_provider,name,phone)
VALUES
(1,'John','8800555'),
(2,'Ben','796421234'),
(3,'Nikolay','98620461'),
(4,'Masha','97856212');

INSERT INTO animals.animals_provider
(id_animal_type,id_provider)
VALUES
(1,1),
(2,2),
(3,3),
(4,4);

