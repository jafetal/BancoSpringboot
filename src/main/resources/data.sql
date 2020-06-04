--Si no se pone esto en el Server de sql no arranca en mi compu :P
SET GLOBAL time_zone ='-3:00';
--La contraseña de todos es 1234
--Este va a ser admin
INSERT INTO user (id,enabled,password,username) VALUES(1,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","admin");
--Estos van a ser Usuarios chafillos
INSERT INTO user (id,enabled,password,username) VALUES(2,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user1"); 
INSERT INTO user (id,enabled,password,username) VALUES(3,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user2");

INSERT INTO authority (id,authority) VALUES (1,"ROLE_ADMIN");
INSERT INTO authority (id,authority) VALUES (2,"ROLE_USER");

INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,1);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (2,2);

INSERT INTO cliente (id,id_usuario,Nombre,Correo,Telefono,Direccion,Monto) VALUES(1,1,"Jafet Alvarado","jafet@hai.com",8181818181,"Nueva vida#999",10000);

INSERT INTO prestamo (monto,fecha_creacion,fecha_expiracion,Tipo,activo,FK_CLIENTE) VALUES(5000,CURRENT_TIMESTAMP,'2021-08-10',"prestamo",1,1);