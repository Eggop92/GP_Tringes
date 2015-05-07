CREATE DATABASE if not exists `tringes`;
USE tringes;

CREATE TABLE `modalidad` (
	`ID_Modalidad` int(11) NOT NULL,
	`Descripcion` varchar(200) COLLATE utf8_spanish_ci NOT NULL,
	`NumMaxPerros` int(11) NOT NULL,
	PRIMARY KEY (`ID_Modalidad`)
) DEFAULT CHARSET=utf8;
insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (01, 'Seis Perros', 6);
insert into modalidad(id_modalidad, descripcion, numMaxPerros) values (02, 'Ocho Perros', 8);

CREATE TABLE `campeonato` (
	`FechaCampeonato` date NOT NULL,
	`Nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL unique,
	`Estado` binary(1) NOT NULL,
	PRIMARY KEY (`FechaCampeonato`)
) DEFAULT CHARSET=utf8;
insert into campeonato(fechaCampeonato, nombre, estado) values ('2013-01-20', 'Alaska 13', TRUE);
insert into campeonato(fechaCampeonato, nombre, estado) values ('2012-01-20', 'Alaska 12', FALSE);

CREATE TABLE `carrera` (
	`FechaCarrera` date NOT NULL,
	`FechaCampeonato` date NOT NULL,
	`Nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL unique,
	`Lugar` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Recorrido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Estado` binary(1) NOT NULL,
	`ID_Modalidad` int(11) NOT NULL,
	PRIMARY KEY (`FechaCarrera`,`FechaCampeonato`),
	foreign key (`id_Modalidad`) references `modalidad` (`ID_modalidad`) on delete no action on update cascade, 
	FOREIGN KEY (`FechaCampeonato`) REFERENCES `campeonato` (`FechaCampeonato`) ON DELETE NO ACTION ON UPDATE CASCADE
) DEFAULT CHARSET=utf8;
insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-20', '2013-01-20', 'Alaska 1 13', 'Alaska', 'Chungo', FALSE, 01);
insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2013-01-21', '2013-01-20', 'Alaska 2 13', 'Alaska', 'Chungo', TRUE, 01);
insert into carrera(FechaCarrera, FechaCampeonato, nombre, lugar, recorrido, estado, id_Modalidad) values ('2012-01-21', '2012-01-20', 'Alaska 1 12', 'Alaska', 'Chungo', FALSE, 01);

CREATE TABLE `usuario` (
	`NombreUsuario` int(11) NOT NULL,
	`Contraseña` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Tipo` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	PRIMARY KEY (`NombreUsuario`)
) DEFAULT CHARSET=utf8;
insert into usuario (nombreusuario, contraseña, tipo) values ( 01, '01D', 'C');
insert into usuario (nombreusuario, contraseña, tipo) values ( 02, '02C', 'C');
insert into usuario (nombreusuario, contraseña, tipo) values ( 03, '03A', 'D');
insert into usuario (nombreusuario, contraseña, tipo) values ( 04, '04C', 'D');
insert into usuario (nombreusuario, contraseña, tipo) values ( 05, '05D', 'A');

CREATE TABLE `club` (
	`ID_CLUB` int(11) NOT NULL,
	`Nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL unique,
	`Patrocinador` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Estado` binary(1) NOT NULL,
	PRIMARY KEY (`ID_CLUB`),
	foreign key (`id_Club`) references `usuario`(`NombreUsuario`) on delete no action on update cascade
) DEFAULT CHARSET=utf8;
insert into club(id_club, nombre, patrocinador, estado) values (01, 'club1', 'Patrocinador1', TRUE);
insert into club(id_club, nombre, patrocinador, estado) values (02, 'club2', 'Patrocinador2', FALSE);

CREATE TABLE `dueño` (
	`NumFederado` int(11) NOT NULL,
	`Nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Apellido` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Estado` binary(1) NOT NULL,
	PRIMARY KEY (`NumFederado`),
	foreign key (`NumFederado`) references `usuario`(`NombreUsuario`) on delete no action on update cascade
) DEFAULT CHARSET=utf8;
insert into dueño(numFederado, nombre, apellido, estado) values (03, 'dueño3', 'apellido1', TRUE);
insert into dueño(numFederado, nombre, apellido, estado) values (04, 'dueño4', 'apellido2', FALSE);

CREATE TABLE `perro` (
	`ID_Perro` int(11) NOT NULL,
	`Nombre` varchar(45) COLLATE utf8_spanish_ci NOT NULL unique,
	`Raza` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Sexo` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`Estado` binary(1) NOT NULL,
	PRIMARY KEY (`ID_Perro`)
) DEFAULT CHARSET=utf8;
insert into perro(id_perro, nombre, raza, sexo, estado) values (01, 'perro1', 'Husky', 'M', TRUE);
insert into perro(id_perro, nombre, raza, sexo, estado) values (02, 'perro2', 'Husky', 'M', FALSE);

CREATE TABLE `apareamiento` (
	`Fecha` date NOT NULL,
	`ID_Perro` int(11) NOT NULL,
	`NumCrias` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	PRIMARY KEY (`Fecha`,`ID_Perro`),
	foreign key (`id_Perro`) references `perro` (`id_perro`) ON DELETE NO ACTION ON UPDATE CASCADE
) DEFAULT CHARSET=utf8;
insert into apareamiento(fecha, id_Perro, numCrias) values ('2013-04-16', 01, 5);
insert into apareamiento(fecha, id_Perro, numCrias) values ('2013-04-16', 02, 7);

CREATE TABLE `trineo` (
	`ID_Trineo` int(11) NOT NULL,
	`ID_Club` int(11) NOT NULL,
	`Fabricante` varchar(45) COLLATE utf8_spanish_ci NOT NULL,
	`FechaFabricacion` date NOT NULL,
	`Estado` binary(1) NOT NULL,
	PRIMARY KEY (`ID_Trineo`),
	FOREIGN KEY (`ID_Club`) REFERENCES `club` (`ID_CLUB`) ON DELETE NO ACTION ON UPDATE CASCADE
) DEFAULT CHARSET=utf8;
insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (01, 01, 'Fabricante1', '2000-01-01', FALSE);
insert into trineo(id_trineo, id_Club, fabricante, FechaFabricacion,Estado) values (02, 01, 'Fabricante1', '2000-01-01', TRUE);

CREATE TABLE `llevara` (
	`ID_Perro` int(11) NOT NULL,
	`ID_Trineo` int(11) NOT NULL,
	`FechaCarrera` date NOT NULL,
	`fechaCampeonato` date not null,
	PRIMARY KEY (`ID_Perro`,`ID_Trineo`,`FechaCarrera`, `FechaCampeonato`),
	FOREIGN KEY (`FechaCarrera`, `fechaCampeonato`) REFERENCES `carrera` (`FechaCarrera`, `fechaCampeonato`) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (`ID_Trineo`) REFERENCES `trineo` (`ID_Trineo`) ON DELETE NO ACTION ON UPDATE CASCADE,
	foreign key (`ID_Perro`) references `perro` (`ID_Perro`) ON DELETE NO ACTION ON UPDATE CASCADE
) DEFAULT CHARSET=utf8;
insert into llevara(id_Perro, id_trineo, fechaCarrera, fechaCampeonato) values (01, 01, '2013-01-21', '2013-01-20');
insert into llevara(id_Perro, id_trineo, fechaCarrera, fechaCampeonato) values (01, 01, '2013-01-20', '2013-01-20');

CREATE TABLE `perropertenece` (
	`FechaComienzoDueño` date NOT NULL,
	`NumFederado` int(11) NOT NULL,
	`ID_Perro` int(11) NOT NULL,
	`FechaFinDueño` date,
	PRIMARY KEY (`FechaComienzoDueño`,`NumFederado`,`ID_Perro`),
	foreign key (`id_Perro`) references `perro` (`id_perro`) ON DELETE NO ACTION ON UPDATE CASCADE,
	FOREIGN KEY (`NumFederado`) REFERENCES `dueño` (`NumFederado`) ON DELETE NO ACTION ON UPDATE CASCADE
) DEFAULT CHARSET=utf8;
insert into perropertenece(FechaComienzoDueño, numFederado, id_Perro) values ('2013-04-20', 03, 01);
insert into perropertenece(FechaComienzoDueño, numFederado, id_Perro, fechaFinDueño) values ('2013-01-01', 04, 01, '2013-04-20');

CREATE TABLE `recorridocarrera` (
	`FechaCarrera` date NOT NULL,
	`fechaCampeonato` date not null,
	`ID_Trineo` int(11) NOT NULL,
	`Posicion` int(11) ,
	`Puntos` int(11) ,
	PRIMARY KEY (`FechaCarrera`, `fechaCampeonato`,`ID_Trineo`),
	foreign key (`fechaCarrera`, `fechaCampeonato`) references `carrera`(`fechaCarrera`, `fechaCampeonato`) on delete no action on update cascade,
	foreign key (`id_Trineo`) references `trineo`(`id_trineo`) on delete no action on update cascade
) DEFAULT CHARSET=utf8;
insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-20', '2013-01-20',01,0,0);
insert into recorridoCarrera(fechaCarrera, fechaCampeonato, id_trineo, posicion, puntos) values ('2013-01-20', '2013-01-20',02,0,0);

CREATE TABLE `utiliza` (
	`ID_Club` int(11) NOT NULL,
	`ID_Perro` int(11) NOT NULL,
	`FechaInicioUtilizacion` date NOT NULL,
	`FechaFinUtilizacion` date,
	PRIMARY KEY (`ID_Club`,`ID_Perro`,`FechaInicioUtilizacion`),
	foreign key (`ID_club`) references `club` (`id_club`)  on delete no action on update cascade,
	foreign key (`id_Perro`) references `perro`(`id_Perro`) on delete no action on update cascade
) DEFAULT CHARSET=utf8;
insert into utiliza(id_Club, id_perro, fechaInicioUtilizacion) values (01, 01, '2013-04-20');
insert into utiliza(id_Club, id_perro, fechaInicioUtilizacion, FechaFinUtilizacion) values (02, 01, '2013-01-01','2013-04-20');

CREATE TABLE `ventas` (
	`ID_Perro` int(11) NOT NULL,
	`NumVendedor` int(11) NOT NULL,
	`NumComprador` int(11) NOT NULL,
	`Fecha` date NOT NULL,
	`Confirmado` binary(1) NOT NULL,
	PRIMARY KEY (`ID_Perro`,`NumVendedor`,`Fecha`),
	foreign key (`id_Perro`) references `perro`(`id_Perro`) on delete no action on update cascade,
	foreign key (`numVendedor`) references `dueño`(`numFederado`) on delete no action on update cascade,
	foreign key (`numComprador`) references `dueño`(`numFederado`) on delete no action on update cascade
) DEFAULT CHARSET=utf8;
insert into ventas(id_Perro, NumVendedor, NumComprador, FEcha, confirmado) values (01, 04, 03, '2013-04-20', true);