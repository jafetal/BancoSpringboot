--Si no se pone esto en el Server de sql no arranca en mi compu :P
SET GLOBAL time_zone ='-3:00';
--La contraseña de todos es 1234
--Este va a ser admin
INSERT INTO user (id_usuario,enabled,password,username) VALUES(1,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","admin");
--Estos van a ser Usuarios chafillos
INSERT INTO user (id_usuario,enabled,password,username) VALUES(2,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user1"); 
INSERT INTO user (id_usuario,enabled,password,username) VALUES(3,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user2");
INSERT INTO user (id_usuario,enabled,password,username) VALUES(4,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user3");
INSERT INTO user (id_usuario,enabled,password,username) VALUES(5,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","user4");
INSERT INTO user (id_usuario,enabled,password,username) VALUES(6,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","AristelaM");
INSERT INTO user (id_usuario,enabled,password,username) VALUES(7,0b1,"$2a$04$XJUMQEYpIDM8eiGHfl.DguvuyECHuXyGyZdZAVstKHtUCH4Y.YE0K","SonijieEmpress");


INSERT INTO authority (id,authority) VALUES (1,"ROLE_ADMIN");
INSERT INTO authority (id,authority) VALUES (2,"ROLE_USER");

INSERT INTO authorities_users (usuario_id, authority_id) VALUES (1,1);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (2,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (3,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (4,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (5,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (6,2);
INSERT INTO authorities_users (usuario_id, authority_id) VALUES (7,2);


INSERT INTO cliente (id,id_usuario,Nombre,Correo,Telefono,Direccion,Monto) VALUES(1,2,"User1","jafet@hai.com",8181818181,"Nueva vida#999",10000);
INSERT INTO cliente (id,id_usuario,Nombre,Correo,Telefono,Direccion,Monto) VALUES(2,3,"User2","ohls@hai.com",9191919191,"vieja vida#988",20000);
INSERT INTO cliente (id,id_usuario,Nombre,Correo,Telefono,Direccion,Monto) VALUES(3,4,"User3","yupi@hai.com",6161616161,"Actual vida#111",50000);
INSERT INTO cliente (id,id_usuario,Nombre,Correo,Telefono,Direccion,Monto) VALUES(4,5,"User4","kyouga@hai.com",7171717171,"Iggsadril#932",5000);
INSERT INTO cliente (id,id_usuario,Nombre,Correo,Telefono,Direccion,Monto) VALUES(5,6,"AristelaM","ashitaga@hai.com",3131313131,"Kanto #333",75000);
INSERT INTO cliente (id,id_usuario,Nombre,Correo,Telefono,Direccion,Monto) VALUES(6,7,"SonijieEmpress","mienakutemo@hai.com",21212121212,"hinokamikagura#276",4500);


INSERT INTO prestamo (monto,fecha_creacion,fecha_expiracion,Tipo,activo,FK_CLIENTE) VALUES(5000,CURRENT_TIMESTAMP,'2021-08-10',1,1,1);
INSERT INTO prestamo (monto,fecha_creacion,fecha_expiracion,Tipo,activo,FK_CLIENTE) VALUES(2000,CURRENT_TIMESTAMP,'2021-09-10',2,1,1);
INSERT INTO prestamo (monto,fecha_creacion,fecha_expiracion,Tipo,activo,FK_CLIENTE) VALUES(6000,CURRENT_TIMESTAMP,'2021-10-10',3,1,1);
INSERT INTO prestamo (monto,fecha_creacion,fecha_expiracion,Tipo,activo,FK_CLIENTE) VALUES(9000,CURRENT_TIMESTAMP,'2021-10-10',1,1,1);