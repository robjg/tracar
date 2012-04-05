create table ${tracar.db.schema}.PRODUCT
(
	ID					bigint GENERATED ALWAYS AS IDENTITY,
	CODE 				varchar(7),
	TYPE				varchar(7),
	DESCRIPTION			varchar(24),
	CURRENCY_ID			bigint,
	foreign key (CURRENCY_ID) references CURRENCY,
	primary key 		(ID)
);

