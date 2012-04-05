create table ${tracar.db.schema}.BUSINESS_DATE
(
	ID				bigint AUTO_INCREMENT,
	PREVIOUS		date,
	CURRENT			date,
	NEXT			date,
	primary key 	(ID)
);
