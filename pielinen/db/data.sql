use PIELINEN;
/*----------------------------------------------------------------------------------------------------------------------*/
insert into GROUPS(GROUP_ID,TITLE,GROUP_DESCRIPTION) values('RFID0','Group #0 title','Group #0 description.');
insert into GROUPS(GROUP_ID,TITLE,GROUP_DESCRIPTION) values('RFID1','Group #1 title','Group #1 description.');
insert into GROUPS(GROUP_ID,TITLE,GROUP_DESCRIPTION) values('RFID2','Group #2 title','Group #2 description.');
insert into GROUPS(GROUP_ID,TITLE,GROUP_DESCRIPTION) values('RFID3','Group #3 title','Group #3 description.');
/*----------------------------------------------------------------------------------------------------------------------*/
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID0','Exhibit g0e1 title','Exhibit g0e1 description.','sample.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID0','Exhibit g0e2 title','Exhibit g0e2 description.','sample2.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID0','Exhibit g0e3 title','Exhibit g0e3 description.','sample3.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID0','Exhibit g0e4 title','Exhibit g0e4 description.','sample4.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID1','Exhibit g1e1 title','Exhibit g1e1 description.','sample.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID1','Exhibit g1e2 title','Exhibit g1e2 description.','sample2.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID1','Exhibit g1e3 title','Exhibit g1e3 description.','sample3.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID1','Exhibit g1e4 title','Exhibit g1e4 description.','sample4.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID2','Exhibit g2e1 title','Exhibit g2e1 description.','sample.png');
insert into EXHIBITS(FK_GROUP_ID,TITLE,EXHIBIT_DESC,IMG_URL) values('RFID2','Exhibit g2e2 title','Exhibit g2e2 description.','sample2.png');
/*----------------------------------------------------------------------------------------------------------------------*/
/*
insert into exhibit_comments(fk_exhibit_id, user_id,comments)
values ('1',0,'This text is comments of the user for exhibit #1');
insert into exhibit_comments(fk_exhibit_id, user_id,comments)
values ('2',0,'This text is comments of the user for exhibit #2');
*/
/*----------------------------------------------------------------------------------------------------------------------*/
