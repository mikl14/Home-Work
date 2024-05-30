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
