create table ${tracar.db.schema}.TRADE
(
	ID				bigint GENERATED ALWAYS AS IDENTITY,
	PRODUCT_ID 	bigint,
	ACCOUNT_ID 		bigint,
	TRADE_DATE 		date,
	QUANTITY 		int,	
	TRADE_PRICE		double,
	foreign key (PRODUCT_ID) references PRODUCT,
	foreign key (ACCOUNT_ID) references ACCOUNT,
	primary key (ID)
);

