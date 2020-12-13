/*
* Engine: MSSQL
* VERSION: 0.0.1
* Description: database init
*/

create table users (id varchar(255) not null, name varchar(255), primary key (id)) engine=InnoDB;
