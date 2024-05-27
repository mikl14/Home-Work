
create schema IF NOT EXISTS animals;

CREATE TABLE IF NOT EXISTS animals.provider (
    id_provider integer PRIMARY KEY,
    name character varying(255),
    phone character varying(255)
);

CREATE TABLE IF NOT EXISTS animals.habitats (
    id_area integer PRIMARY KEY,
    area character varying(255)
);

CREATE TABLE IF NOT EXISTS animals.animal_type (
    id_type integer PRIMARY KEY,
    is_wild boolean NOT NULL,
    type character varying(255)
);

CREATE TABLE IF NOT EXISTS animals.animals_provider (
    id_animal_type integer NOT NULL,
    id_provider integer NOT NULL);


CREATE TABLE IF NOT EXISTS animals.breed (
    id integer NOT NULL
);

CREATE TABLE IF NOT EXISTS animals.animal (
    id INTEGER PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    birth_date date,
    "character" character varying(255),
    cost numeric(19,2),
    name character varying(255),
    type integer,
    breed integer
);

CREATE TABLE IF NOT EXISTS animals.animal_type_animal_list (
    animal_type_id_type integer NOT NULL,
    animal_list_id integer NOT NULL
);

CREATE TABLE IF NOT EXISTS animals.animals_habitats (
    id_animal_type integer NOT NULL,
    id_area integer NOT NULL
);

CREATE TABLE IF NOT EXISTS animals.animal_user (
     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     name character varying(255),
     password character varying(255)
);

CREATE TABLE IF NOT EXISTS animals.role (
     id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
     role_name character varying(255)
);

CREATE TABLE IF NOT EXISTS animals.user_roles (
     user_id BIGINT NOT NULL,
     role_id BIGINT NOT NULL
);

