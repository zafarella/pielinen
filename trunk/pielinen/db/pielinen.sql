/*==============================================================*/
/* Database name:  PIELINEN                                     */
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     09.05.2009 18:21:36                          */
/*==============================================================*/


/* DROP USER PIELINEN; */



DROP DATABASE IF EXISTS PIELINEN;

/*==============================================================*/
/* Database: PIELINEN                                           */
/*==============================================================*/
CREATE DATABASE PIELINEN;

USE PIELINEN;

/*==============================================================*/
/* User: PIELINEN                                               */
/*==============================================================*/
/* CREATE USER PIELINEN IDENTIFIED BY 'PIELINEN'; */

/*==============================================================*/
/* Table: GROUPS                                                */
/*==============================================================*/
CREATE TABLE PIELINEN.GROUPS
(
   GROUP_ID             VARCHAR(100) NOT NULL COMMENT 'Why the type is ''varchar(50)'' 
            because it is tag from rfid sensor',
   TITLE                VARCHAR(50),
   GROUP_DESCRIPTION    VARCHAR(1000),
   WHEN_ADDED           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (GROUP_ID)
);

ALTER TABLE PIELINEN.GROUPS COMMENT 'Exhibit groups';

/*==============================================================*/
/* Table: EXHIBITS                                              */
/*==============================================================*/
CREATE TABLE PIELINEN.EXHIBITS
(
   EXHIBIT_ID           INT UNSIGNED NOT NULL AUTO_INCREMENT,
   FK_GROUP_ID          VARCHAR(100),
   TITLE                VARCHAR(50),
   EXHIBIT_DESC         VARCHAR(1000),
   IMG_URL              VARCHAR(100),
   PRIMARY KEY (EXHIBIT_ID),
   CONSTRAINT FK_REFERENCE_1 FOREIGN KEY (FK_GROUP_ID)
      REFERENCES PIELINEN.GROUPS (GROUP_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE PIELINEN.EXHIBITS COMMENT 'lIST OF EXHIBITS OF A MUSEUM';

/*==============================================================*/
/* Index: EXHIBITS_IDX                                          */
/*==============================================================*/
CREATE INDEX EXHIBITS_IDX ON PIELINEN.EXHIBITS
(
   FK_GROUP_ID
);

/*==============================================================*/
/* Table: EXHIBIT_COMMENTS                                      */
/*==============================================================*/
CREATE TABLE PIELINEN.EXHIBIT_COMMENTS
(
   COMMENT_ID           INT NOT NULL AUTO_INCREMENT,
   FK_EXHIBIT_ID        INT UNSIGNED,
   USER_ID              INT,
   DISPLAY              SMALLINT DEFAULT 1 COMMENT '1-display comment 0-don''t display',
   COMMENTS             VARCHAR(1000),
   WHEN_ADDED           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (COMMENT_ID),
   CONSTRAINT FK_REFERENCE_2 FOREIGN KEY (FK_EXHIBIT_ID)
      REFERENCES PIELINEN.EXHIBITS (EXHIBIT_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE PIELINEN.EXHIBIT_COMMENTS COMMENT 'EXHIBIT_COMMENTS';

/*==============================================================*/
/* Index: USER_COMMENTS_IDX                                     */
/*==============================================================*/
CREATE INDEX USER_COMMENTS_IDX ON PIELINEN.EXHIBIT_COMMENTS
(
   FK_EXHIBIT_ID
);

/*==============================================================*/
/* Index: GROUPS_IDX                                            */
/*==============================================================*/
CREATE INDEX GROUPS_IDX ON PIELINEN.GROUPS
(
   GROUP_ID
);

/*==============================================================*/
/* Table: GROUPS_COMMENTS                                       */
/*==============================================================*/
CREATE TABLE PIELINEN.GROUPS_COMMENTS
(
   COMMENT_ID           INT NOT NULL AUTO_INCREMENT,
   FK_GROUP_ID          VARCHAR(100),
   COMMENTS             VARCHAR(1000),
   DISPLAY              SMALLINT DEFAULT 1,
   WHEN_ADDED           TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
   PRIMARY KEY (COMMENT_ID),
   CONSTRAINT FK_REFERENCE_3 FOREIGN KEY (FK_GROUP_ID)
      REFERENCES PIELINEN.GROUPS (GROUP_ID) ON DELETE CASCADE ON UPDATE CASCADE
);

ALTER TABLE PIELINEN.GROUPS_COMMENTS COMMENT 'GROUPS_COMMENTS';

/*==============================================================*/
/* Index: GROUPS_COMMENTS_IDX                                   */
/*==============================================================*/
CREATE INDEX GROUPS_COMMENTS_IDX ON PIELINEN.GROUPS_COMMENTS
(
   COMMENT_ID,
   FK_GROUP_ID
);



GRANT ALL ON PIELINEN.* TO PIELINEN;


