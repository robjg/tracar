insert into ${tracar.db.schema}.ACCOUNT
	(CODE, DESCRIPTION)
  values 
    ('A10123', 'FARMER PICKLES');
    
insert into ${tracar.db.schema}.ACCOUNT
	(CODE, DESCRIPTION)
  values 
    ('A10456', 'FARMER GILES');
    
insert into ${tracar.db.schema}.CURRENCY 
	(CODE, DESCRIPTION)
  values 
    ('GBP', 'BRITISH POUND');

insert into ${tracar.db.schema}.PRODUCT 
	(CODE, TYPE, DESCRIPTION, CURRENCY_ID)
  values 
    ('APPLE', 'FRUIT', 'APPLE', 
    (SELECT ID FROM ${tracar.db.schema}.CURRENCY WHERE CURRENCY.CODE = 'GBP')
    );

insert into ${tracar.db.schema}.PRODUCT 
	(CODE, TYPE, DESCRIPTION, CURRENCY_ID)
  values 
    ('ORANGE', 'FRUIT', 'ORANGE', 
    (SELECT ID FROM ${tracar.db.schema}.CURRENCY WHERE CURRENCY.CODE = 'GBP')
    );

