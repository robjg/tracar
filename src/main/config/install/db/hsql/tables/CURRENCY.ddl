create table ${tracar.db.schema}.CURRENCY
(
	ID				bigint GENERATED ALWAYS AS IDENTITY,
	CODE 			varchar(7),
	DESCRIPTION		varchar(24),
	primary key 	(ID)
);

