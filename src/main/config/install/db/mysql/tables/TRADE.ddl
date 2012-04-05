create table ${tracar.db.schema}.TRADE
(
	ID				bigint AUTO_INCREMENT,
	PRODUCT_ID 	bigint,
	ACCOUNT_ID 		bigint,
	TRADE_DATE 		date,
	QUANTITY 		int,	
	TRADE_PRICE		double,
	foreign key (PRODUCT_ID) references PRODUCT (ID),
	foreign key (ACCOUNT_ID) references ACCOUNT (ID),
	primary key (ID)
);

