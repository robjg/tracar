create table ${tracar.db.schema}.FEED_TRADE
(
	ID					bigint GENERATED ALWAYS AS IDENTITY,
	SEQ_NUM				varchar(9),
	TRADE_REF			varchar(24),
	TRADE_DATE			date,
	ACCOUNT_CODE		varchar(12),
	EXCHANGE_CODE   	varchar(7),
	PRODUCT_TYPE		varchar(7),
	PRODUCT_CODE   		varchar(24),
	SIDE				char(1),
	QUANTITY			integer,
	TRADE_PRICE			double,
	COUNTERPARTY_CODE	varchar(7),
	primary key 		(ID)
);

