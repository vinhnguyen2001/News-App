use demo;

DROP TABLE IF EXISTS USER;
CREATE TABLE USER (

id bigint NOT NULL PRIMARY KEY auto_increment,
username varchar(150),
password varchar(150),
fullname varchar(150),
roleid BIGINT,
status int
);

use demo;

INSERT INTO user (username,password,fullname, roleid, status)
VALUES('vinhntn','123456','Nguyễn Trần Ngọc Vinh',3,1)
