drop procedure if exists `insertaPrograma_Academico`;
DELIMITER %%
create procedure `insertaPrograma_Academico`(IN nombre varchar(100))
begin
	insert into `Programa_Academico`(nombre) values(nombre);
end%%
DELIMITER ;

drop procedure if exists `insertaAcademia`;
DELIMITER //
create procedure `insertaAcademia`(IN nombre varchar(100))
begin
	insert into `Academia` values(nombre);
end//
DELIMITER ;

drop procedure if exists `insertaUnidad_Aprendizaje`;
DELIMITER //
create procedure `insertaUnidad_Aprendizaje`(IN nombre varchar(100))
begin
	insert into `Unidad_Aprendizaje`(nombre) values(nombre);
end//
DELIMITER ;

drop procedure if exists `insertaAlumno`;
DELIMITER //
create procedure `insertaAlumno`(IN boleta varchar(100), IN apPaterno varchar(50), IN apMaterno varchar(50), IN programaAcademico varchar(100))
begin
	insert into `Alumno`(boleta,idPrograma_Academico) values(boleta,programaAcademico);
	insert into `ApellidosAlumno`(boleta,apPaterno,apMaterno) values(boleta,apPaterno,apMaterno);
end//
DELIMITER ;

drop procedure if exists `obtenerBoletaFromApellidos`;
DELIMITER $$
create procedure `obtenerBoletaFromApellidos`(IN apPaterno varchar(100), IN apMaterno varchar(100))
begin
	select boleta from ApellidosAlumno where ApellidosAlumno.apPaterno = apPaterno and ApellidosAlumno.apMaterno = apMaterno;
end$$
DELIMITER ;

drop procedure if exists `participantes`;
DELIMITER //
create procedure `participantes`()
begin
	select count(*) from (select boleta from alumno_cursa_unidad_aprendizaje group by boleta) t;
end//
DELIMITER ;

drop procedure if exists `insertaNombreAlumno`;
DELIMITER $$
create procedure `insertaNombreAlumno`(IN boleta varchar(20), IN nombre varchar(50))
begin
	insert into NombresAlumno values(boleta,nombre);
end$$
DELIMITER ;

drop procedure if exists `insertaPrograma_Academico_Unidad_Aprendizaje`;
DELIMITER %%
create procedure `insertaPrograma_Academico_Unidad_Aprendizaje`(IN programaAcademico varchar(100), IN academia varchar(100))
begin
	insert into Programa_Academico_Unidad_Aprendizaje values(programaAcademico,academia);
end%%
DELIMITER ;

drop procedure if exists `insertaAcademia_Unidad_Aprendizaje`;
DELIMITER //
create procedure `insertaAcademia_Unidad_Aprendizaje`(IN academia varchar(100), IN unidadAprendizaje varchar(100))
begin
	insert into Academia_Unidad_Aprendizaje values(academia,unidadAprendizaje);
end//
DELIMITER ;

drop procedure if exists `insertaAlumno_Cursa_Unidad_Aprendizaje`;
DELIMITER $$
create procedure `insertaAlumno_Cursa_Unidad_Aprendizaje`(IN boleta varchar(10), IN unidadAprendizaje varchar(100), IN isrecurse int)
begin
	insert into Alumno_Cursa_Unidad_Aprendizaje values(boleta,unidadAprendizaje,isRecurse);
end$$
DELIMITER ;

drop procedure if exists `eliminaAlumno_Cursa_Unidad_Aprendizaje`;
DELIMITER $$
create procedure `eliminaAlumno_Cursa_Unidad_Aprendizaje`(IN boleta varchar(10), IN unidadAprendizaje varchar(100))
begin
	delete from Alumno_Cursa_Unidad_Aprendizaje where Alumno_Cursa_Unidad_Aprendizaje.boleta = boleta and Alumno_Cursa_Unidad_Aprendizaje.idUnidad_Aprendizaje = unidadAprendizaje;
end$$
DELIMITER ;

drop procedure if exists `actualizaRecurse`;
DELIMITER //
create procedure `actualizaRecurse`(IN boleta varchar(10), IN unidadAprendizaje varchar(100), IN isRecurse int)
begin
	update Alumno_Cursa_Unidad_Aprendizaje SET Alumno_Cursa_Unidad_Aprendizaje.isRecurse = isRecurse where 
	Alumno_Cursa_Unidad_Aprendizaje.boleta = boleta and Alumno_Cursa_Unidad_Aprendizaje.idUnidad_Aprendizaje = unidadAprendizaje;
end//
DELIMITER ;

drop procedure if exists `getMaterias`;
DELIMITER %%
create procedure `getMaterias`(IN boleta varchar(20))
begin 
	declare c int;
	select count(*) into c from Alumno_Cursa_Unidad_Aprendizaje where Alumno_Cursa_Unidad_Aprendizaje.boleta = boleta;
	if( c > 0 ) then
		select 'Usted ya ha participado';
	else
		select idUnidad_Aprendizaje from (Programa_Academico_Unidad_Aprendizaje join (select idPrograma_Academico from Alumno where Alumno.boleta = boleta) t on Programa_Academico_Unidad_Aprendizaje.idPrograma_Academico = t.idPrograma_Academico);
	end if;
end%%
DELIMITER ;
