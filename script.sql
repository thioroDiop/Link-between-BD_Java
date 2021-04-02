create table if not exists serveur
(
	id_serveur serial not null
		constraint serveur_pk
			primary key,
	prenom varchar,
	nom varchar
);

alter table serveur owner to postgres;

create table if not exists tables
(
	id_tables serial not null
		constraint tables_pk
			primary key,
	nom_table varchar,
	nombre_convives integer
);

alter table tables owner to postgres;

create table if not exists facture
(
	id_facture serial not null
		constraint facture_pk
			primary key,
	serveur_idx integer
		constraint serveur_idx
			references serveur,
	tables_idx integer
		constraint table_idx
			references tables,
	prixtotal double precision
);

alter table facture owner to postgres;

create table if not exists plat
(
	id_plat serial not null
		constraint plat_pk
			primary key,
	nom_plat varchar,
	prix_unitaire double precision
);

alter table plat owner to postgres;

create table if not exists plat_join_facture
(
	plat_idx integer
		constraint plat_idx
			references plat,
	id serial not null
		constraint plat_join_table_pk
			primary key,
	facture_idx integer
		constraint facture_idx
			references facture,
	quantité integer
);

alter table plat_join_facture owner to postgres;

