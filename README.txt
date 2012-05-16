TraCaR
======

A simple Trade Capture and Reporting application. This project is intended 
to demonstrate how Oddjob can be used to install, control, and test an 
application.

Supported DBs
-------------
HSQL
MySQL

Requirements
------------

Requires Oddjob 1.2 to run.

To Run
------

Unpack the tracar...-bin.zip.

$ bin\tracar-hsqlm

This will run tracar using an in memory HSQL instance.  

Other scripts:
 
To Install
----------

To install MySQL schema and main the main TraCaR processes as Window 
Services:

Edit profiles\mysql\tracar.properties (or copy the directory to create
a new profile and then edit the tracar.properites file).

$ bin\tracar-install

Select the profile directory i.e. mysql 

You will be prompted If you wish to install the db and/or the services.

Monitoring
----------

$ bin\tracar-mysql

This will launch monitoring of the services using servers and ports defined 
in profiles\mysql\tracar.properties.

If you are using a different profile then change the TRACAR_PROFILE 
environment variable in the script.

To Build
--------

Unpack the tracar...-src.zip.

$ mvn package

To Uninstall
------------

$ bin\tracar-uninstall 

Will prompt for a profile and uninstall the database and services. It won't
delete the tracar application.
 