create table cliente (
cedula int,
tipoCC char(2),
nombre varchar(200),
apellido varchar(200),
correo varchar(200),
celular int,
direccion varchar(200),
passw varchar(200)
);

create table solicitud(
ticket int,
fecha date,
estado varchar,
cedula int,
tipoCC char(2)
);

create table tecnico(
cedula int,
tipoCC char(2),
nombre varchar(200),
apellido varchar(200),
correo varchar(200),
celular int,
passw varchar(200)
)
 

create table servicio(
idServicio int,
nombre varchar(200),
valor bigint,
calificacion int
)

create table solicitudServicio(
ticket int,
idServicio int,
)

create table tecnicoSolicitud(
token int,
ticket int,
tecnico int,
tipocc varchar(4)
)



ALTER TABLE cliente ADD PRIMARY KEY (cedula,tipoCC);

ALTER TABLE solicitud ADD PRIMARY KEY (ticket);

ALTER TABLE tecnico ADD PRIMARY KEY (cedula,tipoCC);

ALTER TABLE servicio ADD PRIMARY KEY (idServicio);

ALTER TABLE tecnicoSolicitud ADD PRIMARY KEY (token);


alter table solicitudServicio add constraint fk_solicitudSer1 foreign key (ticket) references solicitud(ticket);
alter table solicitudServicio add constraint fk_solicitudSer2 foreign key (idServicio) references servicio(idServicio);

alter table tecnicoSolicitud add constraint fk_solicitudSer1 foreign key (tecnico,tipocc) references tecnico(cedula,tipoCC);
alter table tecnicoSolicitud add constraint fk_solicitudSer2 foreign key (ticket) references solicitud(ticket);

alter table solicitud add constraint fk_solicitud foreign key (cedula,tipocc) references cliente(cedula,tipoCC);

