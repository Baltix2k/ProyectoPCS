CREATE TABLE Organizacion(
    calle VARCHAR(30) NOT NULL,
    codigoPostal VARCHAR(5) NOT NULL,
    colonia VARCHAR(30) NOT NULL,
    correo VARCHAR(30) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    numExt VARCHAR(4) NOT NULL,
    telefono VARCHAR(20) NOT NULL,
    PRIMARY KEY(nombre)
);

CREATE TABLE Proyecto(
    nombreOrganizacion VARCHAR(100) NOT NULL,   
    actividades VARCHAR(500),
    claveProyecto INTEGER NOT NULL,
    descripcion VARCHAR(500),
    fechaRegistro DATE,
    noEstudiantes INTEGER,
    nombre VARCHAR(100) NOT NULL,
    responsableNombre VARCHAR(100) NOT NULL,
    PRIMARY KEY(claveProyecto)
);

CREATE TABLE Estudiante(
    apellidoMatenro VARCHAR(20) NOT NULL,
    apellidoPaterno VARCHAR(20) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    correoPersonal VARCHAR(20) NOT NULL,
    estado VARCHAR(10) NOT NULL,
    matricula VARCHAR(10) NOT NULL,
    promedio FLOAT,
    telefono VARCHAR(20) NOT NULL,
    PRIMARY KEY(matricula)
);

CREATE TABLE SeleccionProyecto(
    matricula VARCHAR(10) NOT NULL,
    claveProyecto INTEGER NOT NULL,
    fecha DATE,
    periodo VARCHAR(20) NOT NULL,
    PRIMARY KEY(matricula,claveProyecto) 
);

CREATE TABLE Inscripcion(
    matricula VARCHAR(10) NOT NULL,
    claveProyecto INTEGER NOT NULL,
    rfc INTEGER NOT NULL,
    bloque INTEGER NOT NULL,
    cupo INTEGER NOT NULL,
    estatus VARCHAR(10),
    fecha DATE,
    nrc INTEGER NOT NULL,
    periodo VARCHAR(20) NOT NULL,
    seccion INTEGER,
    tipo VARCHAR(25) NOT NULL,
    PRIMARY KEY(matricula,claveProyecto)
);

CREATE TABLE Profesor(
    apellidoMatenro VARCHAR(20) NOT NULL,
    apellidoPaterno VARCHAR(20) NOT NULL,
    nombre VARCHAR(20) NOT NULL,
    rfc INTEGER NOT NULL,
    PRIMARY KEY(rfc)
);

CREATE TABLE Expediente(
    clave INTEGER NOT NULL AUTO_INCREMENT,
    fechaFinPP DATE,
    fechaInicioPP DATE,
    horasAcomuladas INTEGER,
    numReportesEntregados INTEGER,
    matricula VARCHAR(10) NOT NULL,
    claveProyecto INTEGER NOT NULL,
    PRIMARY KEY(clave)
);

CREATE TABLE Archivo(
    claveExp INT NOT NULL,
    estado VARCHAR(10),
    rutaUbicacion VARCHAR(100) NOT NULL,
    titulo VARCHAR(20),
    PRIMARY KEY(rutaUbicacion)
);

CREATE TABLE Reporte(
    rutaUbicacion VARCHAR(100) NOT NULL,
    fechaEntrega DATE,
    horasReportadas INTEGER,
    mes VARCHAR(10),
    numero INTEGER,
    tipoReporte VARCHAR(20),
    PRIMARY KEY(rutaUbicacion)
);

ALTER TABLE Proyecto ADD CONSTRAINT 
fk_proyecto_1 FOREIGN KEY (nombreOrganizacion) 
REFERENCES Organizacion (nombre);

ALTER TABLE SeleccionProyecto ADD CONSTRAINT 
fk_seleccion_1 FOREIGN KEY (matricula) 
REFERENCES Estudiante (matricula);

ALTER TABLE SeleccionProyecto ADD CONSTRAINT 
fk_seleccion_2 FOREIGN KEY (claveProyecto) 
REFERENCES Proyecto (claveProyecto);

ALTER TABLE Inscripcion ADD CONSTRAINT 
fk_inscripcion_1 FOREIGN KEY (matricula) 
REFERENCES Estudiante (matricula);

ALTER TABLE Inscripcion ADD CONSTRAINT 
fk_inscripcion_2 FOREIGN KEY (claveProyecto) 
REFERENCES Proyecto (claveProyecto);

ALTER TABLE Inscripcion ADD CONSTRAINT 
fk_inscripcion_4 FOREIGN KEY (rfc) 
REFERENCES Profesor (rfc);

ALTER TABLE Expediente ADD CONSTRAINT 
fk_expediente_1 FOREIGN KEY (matricula) 
REFERENCES Inscripcion (matricula);

ALTER TABLE Expediente ADD CONSTRAINT 
fk_expediente_2 FOREIGN KEY (claveProyecto) 
REFERENCES Inscripcion (claveProyecto);

ALTER TABLE Archivo ADD CONSTRAINT 
fk_arch_1 FOREIGN KEY (claveExp) 
REFERENCES Expediente (clave);

ALTER TABLE Reporte ADD CONSTRAINT 
fk_reporte_1 FOREIGN KEY (rutaUbicacion) 
REFERENCES Archivo (rutaUbicacion);

INSERT INTO Estudiante VALUES
('Baltazar', 'Islas','Omar', 'omar@gmail.com','Inscrito','S18012180',9.2,'2283661974'),
('Alarcon', 'Santos', 'Emilio Antonio','emilio@gmail.com','Inscrito','S18012181',8.7,'2281776654'),
('Ruiz', 'Alarcon', 'Ricardo','ricardo@gmail.com','Inscrito','S18012182',9.1,'2283557901'),
('Trujillo', 'Martinez', 'Alma','alma@gmail.com','Inscrito','S18012183',8.4,'2281599101'),
('Carmona', 'Hernandez', 'Benito','benito@gmail.com','Inscrito','S18012184',8.0,'2283539607');

INSERT INTO Organizacion VALUES
('Flores','91183','Bugambilias','telmex@gmail.com','Telmex','102','8116716'),
('Jacaranda','91181','Vanguardista','cocacola@gmail.com','CocaCola','125','8116657'),
('Arboleda','91171','Revolucion','softeck@gmail.com','Softeck','1025','8113025');

INSERT INTO Proyecto VALUES
('Telmex','Actividades varias', 911,'Mantenimiento a sistema de atencion a cliente','2019/11/20',2,'Clientserver','Daniel Ortiz'),
('Telmex','Actividades varias', 912,'Mantenimiento a sistema de pago','2019/11/20',2,'PagoMex','Alejandro Castro'),
('CocaCola','Actividades varias', 811,'Mantenimiento a sistema de quejas','2019/11/21',2,'CoCaQuejas','Armando Luna'),
('CocaCola','Actividades varias', 812,'Mantenimiento a sistema de inventairo','2019/11/20',2,'SistemaConteo','Gerardo Gutierrez'),
('Softeck','Actividades varias', 711,'Mantenimiento a sistema de productividad','2019/11/21',2,'ProdutivityService','Bruno Diaz'),
('Softeck','Actividades varias', 712,'Mantenimiento a sistema de seguros','2019/11/20',2,'SecureServ','Norman Osborn');

INSERT INTO Profesor VALUES
('Valdes','Rivera','Oscar Jose',234);

INSERT INTO SeleccionProyecto VALUES
('S18012180',911,'2020/02/10','FEB2020-AGO2020'),
('S18012180',811,'2020/02/10','FEB2020-AGO2020'),
('S18012180',711,'2020/02/10','FEB2020-AGO2020'),
('S18012181',912,'2020/02/09','FEB2020-AGO2020'),
('S18012181',812,'2020/02/09','FEB2020-AGO2020'),
('S18012181',712,'2020/02/09','FEB2020-AGO2020'),
('S18012182',711,'2020/02/15','FEB2020-AGO2020'),
('S18012182',811,'2020/02/15','FEB2020-AGO2020'),
('S18012182',812,'2020/02/15','FEB2020-AGO2020'),
('S18012183',912,'2020/02/11','FEB2020-AGO2020'),
('S18012183',711,'2020/02/11','FEB2020-AGO2020'),
('S18012183',712,'2020/02/11','FEB2020-AGO2020'),
('S18012184',711,'2020/02/09','FEB2020-AGO2020'),
('S18012184',911,'2020/02/09','FEB2020-AGO2020'),
('S18012184',712,'2020/02/09','FEB2020-AGO2020');

INSERT INTO Inscripcion VALUES
('S18012183',912,234,1,35,'Inscrito','2020/02/20',123,'FEB2020-AGO2020',1,'Primera inscripcion'),
('S18012184',712,234,1,35,'Inscrito','2020/02/20',123,'FEB2020-AGO2020',1,'Primera inscripcion');

INSERT INTO Expediente VALUES
(NULL,'2020/08/20','2020/02/25',0,0,'S18012183',912),
(NULL,'2020/08/20','2020/02/25',0,0,'S18012184',712);






