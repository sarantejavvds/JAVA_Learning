------create database JDBC_Connects_Eclipse_and_SSMS;

--select @@VERSION

--select name, is_expiration_checked, is_policy_checked from sys.sql_logins;

--select name FROM sys.databases;
---------------------------------------------------------------------------------------------------------------

use JDBC_Connects_Eclipse_and_SSMS;

-------create table GAMING(Gamer_id bigint primary key,Gamer_Name nvarchar(100), Game_Play varchar(50), Age bigint null check(Age>=18 and Age<=120), Country char(30), Currency_in_Game money);

select * from GAMING; ---- In this current version, user can check his data done CRUD operations in GAMING table
                      ---- The record will only be stored into GAMING_Main_Tabular_Data, if only the query inside if-else block is executed.

--select * into GAMING_Main_Tabular_Data from GAMING; ---- it only creates a table copy only once, 
                                                      ---- trying to create table with same name throws duplication error.

-------------------------------------------------------------------------------------
if exists (select 1 from GAMING)
BEGIN
    insert into GAMING_Main_Tabular_Data 
    select * from GAMING  
	where Gamer_id NOT IN (select Gamer_id from GAMING_Main_Tabular_Data)
END
else
BEGIN
  Print 'GAMING table is Empty. No rows to Insert'
END; -- Every existing row in original table will be copied into dummy table. 
--------------------------------------------------------------------------------------
select * from GAMING_Main_Tabular_Data; --order by Gamer_id asc;

--------------------------------------------------------------------------------

--SELECT COLUMN_NAME FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_NAME = 'GAMING';


------------------------------------------------------------------------------------

--update GAMING set Game_Play = 'COC'  where Gamer_Name='me';

--------------------------------------------------------------


ALTER TABLE GAMING ALTER column Game_Play varchar(50); ---// Increased size of varchar

-----------------------------------------------------------------
Alter table gaming drop constraint chk_fixed_range;


ALTER TABLE GAMING Add constraint chk_fixed_range check (Age>= 18 and Age <= 120)  ;

--------------------------------------------------------


create table GAMING(Gamer_id bigint primary key,
                    Gamer_Name nvarchar(100), 
					Game_Play varchar(50), 
					Age bigint null check(Age>=18 and Age<=120), 
					Country char(30), 
					Currency_in_Game money);
-----------------------------------------------------------
---insert into GAMING(Gamer_id, Gamer_Name, Game_Play, Age, Country, Currency_in_Game) values (123456, 'any', 'any', 23, 'any', 45);
----------------------------------------------------
SELECT 
    con.name AS constraint_name, 
    col.name AS column_name, 
    con.definition 
FROM sys.check_constraints con
JOIN sys.columns col ON con.parent_object_id = col.object_id
WHERE con.parent_object_id = OBJECT_ID('GAMING');
------------------------------------------------------------------
ALTER TABLE GAMING Add constraint chk_fixed_range CHECK (Age>= 18 and Age <= 120) --for age ;

SELECT 
    con.name AS constraint_name,
    con.definition
FROM sys.check_constraints con
WHERE con.parent_object_id = OBJECT_ID('GAMING');



------Alter table gaming drop constraint chk_fixed_range

--------------------------------------------------------------------------------------------------------------










