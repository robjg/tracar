create table ${tracar.db.schema}.CURRENCY
(
	ID				bigint AUTO_INCREMENT,
	CODE 			varchar(7),
	DESCRIPTION		varchar(24),
	primary key 	(ID)
);

