
CREATE TABLE USERS
(
  ID INT AUTO_INCREMENT
    PRIMARY KEY,
  USERNAME VARCHAR(255) NULL,
  PASSWORD VARCHAR(255) NULL
)
  ENGINE=INNODB
;

CREATE TABLE USER_ROLES
(
  ID INT AUTO_INCREMENT
    PRIMARY KEY,
  USERNAME VARCHAR(20) NULL,
  ROLE_NAME VARCHAR(20) NOT NULL
)
  ENGINE=INNODB
;


CREATE TABLE ROLES_PERMISSIONS
(
  ID INT AUTO_INCREMENT
    PRIMARY KEY,
  ROLE_NAME VARCHAR(22) NOT NULL,
  PERMISSION VARCHAR(22) NOT NULL
)
  ENGINE=INNODB
;