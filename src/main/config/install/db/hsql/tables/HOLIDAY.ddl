create table ${tracar.db.schema}.HOLIDAY
(
	ID				bigint GENERATED ALWAYS AS IDENTITY,
	HOLIDAY			date,
	primary key 	(ID)
);

