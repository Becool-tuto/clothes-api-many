create schema if not exists clothesdb;

create table if not exists clothesdb.CLOTHES (
  CLOTHES_ID varchar(40) not null,
  CLOTHES_SIZE varchar(4) not null,
  CLOTHES_COLOR varchar(12) not null,
  CLOTHES_TYPE varchar(40) not null,
  constraint CKC_CLOTHES_SIZE check (CLOTHES_SIZE in ('S','M','L','XL','XXL')),
  constraint PK_CLOTHES_ID primary key (CLOTHES_ID)
);

comment on column clothesdb.CLOTHES.CLOTHES_ID is 'unique identifier of the clothes record. It''s technical ID';
comment on column clothesdb.CLOTHES.CLOTHES_SIZE is 'clothes size';
comment on column clothesdb.CLOTHES.CLOTHES_COLOR is 'clothes color';

create table if not exists clothesdb.TYPES (
 TYPE_ID varchar(40) not null,
 TYPE_NAME varchar(12) not null,
 constraint PK_TYPES_ID primary key (TYPE_ID)
)

create table if not exists clothesdb.BRDS (
  BRD_ID varchar(40) not null,
  BRD_NAME varchar(12) not null,
  constraint PK_BRDS_ID primary key (BRD_ID)
);

comment on table clothesdb.BRDS is 'brand recording. This table store the brand to clothes';

comment on column clothesdb.BRDS.BRD_ID is 'unique identifier of the brand record. It''s technical ID';

comment on column clothesdb.BRDS.BRD_NAME is 'brand name';

create table if not exists clothesdb.CLOTHES_BRDS (
  CLOTHES_ID varchar(40) not null,
  BRD_ID varchar(40) not null
);

comment on table clothesdb.CLOTHES_BRDS is 'This table store clothes and brands';
comment on column clothesdb.CLOTHES_BRDS.CLOTHES_ID is 'unique identifier of the clothes record. It''s technical ID';
comment on column clothesdb.CLOTHES_BRDS.BRD_ID is 'unique identifier of the brand record. It''s technical ID';

ALTER TABLE CLOTHES_BRDS DROP CONSTRAINT IF EXISTS FK_CLOTHES_BRDS_CLOTHES;
ALTER TABLE clothesdb.CLOTHES_BRDS add constraint FK_CLOTHES_BRDS_CLOTHES FOREIGN KEY (CLOTHES_ID) references clothesdb.CLOTHES (CLOTHES_ID);
ALTER TABLE CLOTHES_BRDS DROP CONSTRAINT IF EXISTS FK_CLOTHES_BRDS_BRDS;
alter table clothesdb.CLOTHES_BRDS add constraint FK_CLOTHES_BRDS_BRD FOREIGN KEY (BRD_ID) references clothesdb.BRDS (BRD_ID);
ALTER TABLE CLOTHES DROP CONSTRAINT IF EXISTS FK_CLOTHES_TYPE;
ALTER TABLE CLOTHES ADD CONSTRAINT FK_CLOTHES_TYPE FOREIGN KEY (CLOTHES_TYPE) references clothesdb.TYPES (TYPE_ID);

insert into TYPES(TYPE_ID,TYPE_NAME) values ('d53ef9f4-8170-11eb-8dcd-0242ac130003','t-shirt');
insert into TYPES(TYPE_ID,TYPE_NAME) values ('0c1d34d6-8171-11eb-8dcd-0242ac130003','trousers');
insert into BRDS(BRD_ID,BRD_NAME) values ('665d378e-8171-11eb-8dcd-0242ac130003','NIKE');
insert into BRDS(BRD_ID,BRD_NAME) values ('7344a298-8171-11eb-8dcd-0242ac130003','ADIDAS');
insert into BRDS(BRD_ID,BRD_NAME) values ('78912faa-8171-11eb-8dcd-0242ac130003','PUMA');
Insert into CLOTHES (CLOTHES_ID,CLOTHES_SIZE,CLOTHES_COLOR,CLOTHES_TYPE) values ('73b0caa6-816f-11eb-8dcd-0242ac130003','S','blue','d53ef9f4-8170-11eb-8dcd-0242ac130003');
Insert into CLOTHES (CLOTHES_ID,CLOTHES_SIZE,CLOTHES_COLOR,CLOTHES_TYPE) values ('9d26ce30-816f-11eb-8dcd-0242ac130003','M','red','d53ef9f4-8170-11eb-8dcd-0242ac130003');
Insert into CLOTHES (CLOTHES_ID,CLOTHES_SIZE,CLOTHES_COLOR,CLOTHES_TYPE) values ('5f78224a-8170-11eb-8dcd-0242ac130003','L','blue','0c1d34d6-8171-11eb-8dcd-0242ac130003');
    Insert into CLOTHES_BRDS (CLOTHES_ID,BRD_ID) values ('73b0caa6-816f-11eb-8dcd-0242ac130003','665d378e-8171-11eb-8dcd-0242ac130003');
    Insert into CLOTHES_BRDS (CLOTHES_ID,BRD_ID) values ('73b0caa6-816f-11eb-8dcd-0242ac130003','7344a298-8171-11eb-8dcd-0242ac130003');
    Insert into CLOTHES_BRDS (CLOTHES_ID,BRD_ID) values ('9d26ce30-816f-11eb-8dcd-0242ac130003','78912faa-8171-11eb-8dcd-0242ac130003');
    Insert into CLOTHES_BRDS (CLOTHES_ID,BRD_ID) values ('5f78224a-8170-11eb-8dcd-0242ac130003','78912faa-8171-11eb-8dcd-0242ac130003');

