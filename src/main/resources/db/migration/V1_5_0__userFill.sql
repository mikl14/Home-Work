INSERT INTO animals.role
(role_name)
VALUES
('USER'),
('ADMIN');

INSERT INTO animals.animal_user
(name,password)
VALUES
('mikl','schef2002'),
('test','pass');;

INSERT INTO animals.user_roles
(user_id,role_id)
VALUES
(1,2),
(2,1);