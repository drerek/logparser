create table if not exists log_unit
(
	id serial not null
		constraint logunit_pk
			primary key,
	time text,
	elapsed text,
	remotehost text,
	code_status text,
	bytes text,
	method text,
	url text,
	username text,
	peerstatus_peerhost text,
	type text
);

create sequence log_unit_id_seq;
