create table ${tracar.db.schema}.BUSINESS_DATE
(
	ID				bigint GENERATED ALWAYS AS IDENTITY,
	PREVIOUS		date,
	CURRENT			date,
	NEXT			date,
	primary key 	(ID)
);
