drop database if exists `Demanda_Unidades_Aprendizaje`;
create database `Demanda_Unidades_Aprendizaje`;
use `Demanda_Unidades_Aprendizaje`;

drop table if exists `Alumno_Cursa_Unidad_Aprendizaje`;
drop table if exists `Academia_Unidad_Aprendizaje`;
drop table if exists `Programa_Academico_Unidad_Aprendizaje`;
drop table if exists `ApellidosAlumno`;
drop table if exists `NombresAlumno`;
drop table if exists `Alumno`;
drop table if exists `Unidad_Aprendizaje`;
drop table if exists `Academia`;
drop table if exists `Programa_Academico`;

create table Programa_Academico(
    nombre varchar(100) not null,
    primary key(nombre)
);

create table Academia(
	nombre varchar(100) not null,
    primary key(nombre)
);

create table Unidad_Aprendizaje(
	nombre varchar(100) not null,
	primary key(nombre)
);

create table Alumno(
	boleta varchar(10) not null,
	idPrograma_Academico varchar(100) not null,
    primary key(boleta),
    foreign key(idPrograma_Academico) references Programa_Academico(nombre) on delete cascade on update cascade
);

create table ApellidosAlumno(
	boleta varchar(10) not null,
	apPaterno varchar(50) not null,
	apMaterno varchar(50) not null,
	primary key(boleta),
	foreign key(boleta) references Alumno(boleta) on delete cascade on update cascade
);

create table NombresAlumno(
	boleta varchar(10) not null,
	nombre varchar(50) not null,
	primary key(boleta,nombre),
	foreign key(boleta) references Alumno(boleta) on delete cascade on update cascade
);

create table Programa_Academico_Unidad_Aprendizaje(
	idPrograma_Academico varchar(100) not null,
	idUnidad_Aprendizaje varchar(100) not null,
	primary key(idPrograma_Academico,idUnidad_Aprendizaje),
	foreign key(idPrograma_Academico) references Programa_Academico(nombre) on delete cascade on update cascade,
	foreign key(idUnidad_Aprendizaje) references Unidad_Aprendizaje(nombre) on delete cascade on update cascade
);

create table Academia_Unidad_Aprendizaje(
	idAcademia varchar(100) not null,
	idUnidad_Aprendizaje varchar(100) not null,
	primary key(idAcademia,idUnidad_Aprendizaje),
	foreign key(idAcademia) references Academia(nombre) on delete cascade on update cascade,
	foreign key(idUnidad_Aprendizaje) references Unidad_Aprendizaje(nombre) on delete cascade on update cascade
);

create table Alumno_Cursa_Unidad_Aprendizaje(
	boleta varchar(10) not null,
	idUnidad_Aprendizaje varchar(100) not null,
	isRecurse int default 0,
	primary key(boleta,idUnidad_Aprendizaje),
	foreign key(boleta) references Alumno(boleta) on delete cascade on update cascade,
	foreign key(idUnidad_Aprendizaje) references Unidad_Aprendizaje(nombre) on delete cascade on update cascade
);
