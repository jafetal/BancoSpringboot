--La contraseña de todos es 1234
--Este va a ser admin
INSERT INTO user (id,enabled,password,username) VALUES(1,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","admin");
--Estos van a ser Usuarios chafillos
INSERT INTO user (id,enabled,password,username) VALUES(2,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user1"); 
INSERT INTO user (id,enabled,password,username) VALUES(3,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user2");
INSERT INTO user (id,enabled,password,username) VALUES(4,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user3");
INSERT INTO user (id,enabled,password,username) VALUES(5,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user4");
INSERT INTO user (id,enabled,password,username) VALUES(6,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user5");

INSERT INTO authority (id,authority) VALUES (1,"ROLE_ADMIN");
INSERT INTO authority (id,authority) VALUES (2,"ROLE_USER");
INSERT INTO authority (id,authority) VALUES (3,"ROLE_USER");
INSERT INTO authority (id,authority) VALUES (4,"ROLE_USER");
INSERT INTO authority (id,authority) VALUES (5,"ROLE_USER");
INSERT INTO authority (id,authority) VALUES (6,"ROLE_USER");


INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,1);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (2,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (3,3);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (4,4);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (5,5);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (6,6);