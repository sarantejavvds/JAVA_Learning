------create database JDBC_Connects_Eclipse_and_SSMS;

--select @@VERSION

--select name, is_expiration_checked, is_policy_checked from sys.sql_logins;

--select name FROM sys.databases;
---------------------------------------------------------------------------------------------------------------

use JDBC_Connects_Eclipse_and_SSMS;

-------create table GAMING(Gamer_id bigint primary key,Gamer_Name nvarchar(100), Game_Play varchar(20), Age bigint, Country char(20), Currency_in_Game money);

select * from GAMING;

--select * into Gaming_COPY from GAMING;

-------------------------------------------------------------------------------------
if exists (select 1 from GAMING)
BEGIN
    insert into Gaming_COPY 
    select * from GAMING  
	where Gamer_id NOT IN (select Gamer_id from Gaming_COPY)
END
else
BEGIN
  Print 'GAMING table is Empty. No rows to Insert'
END; -- Every existing row in original table will be copied into dummy table. 
--------------------------------------------------------------------------------------
select * from Gaming_COPY; --order by Gamer_id asc;

