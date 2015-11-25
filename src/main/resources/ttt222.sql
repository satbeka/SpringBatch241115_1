-- Create table
create table TWLTRAINING
(
  ID          NUMBER not null,
  VTTNAME     VARCHAR2(50),
  VTTNAME_L   VARCHAR2(250),
  VTCID       NUMBER,
  VVALID_FROM DATE,
  VVALID_TO   DATE,
  VCREATEDATE TIMESTAMP(6) default systimestamp
)
);
-- Create/Recreate primary, unique and foreign key constraints 
alter table TWLTRAINING
  add constraint IDD primary key (ID)
  ;
