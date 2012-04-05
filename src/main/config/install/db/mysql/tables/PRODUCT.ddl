create table ${tracar.db.schema}.PRODUCT
(
	ID					bigint AUTO_INCREMENT,
	CODE 				varchar(7),
	TYPE				varchar(7),
	DESCRIPTION			varchar(24),
	CURRENCY_ID			bigint,
	foreign key (CURRENCY_ID) references CURRENCY (ID),
	primary key 		(ID)
);

