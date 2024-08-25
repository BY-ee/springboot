CREATE TABLE member01 (
                          NO NUMBER(4) PRIMARY KEY,
                          id varchar2(15),
                          name varchar2(10),
                          address varchar2(20),
                          tel varchar2(15),
                          time timestamp);

CREATE SEQUENCE list_no
    START WITH 1;

DROP TABLE member01;

ALTER TABLE member01 ADD time timestamp;

TRUNCATE TABLE member01;
DROP SEQUENCE list_no;

SELECT * FROM DATABASECHANGELOG;
SELECT * FROM DATABASECHANGELOGLOCK;
SELECT * FROM post;
DROP TABLE post PURGE;
DROP TABLE DATABASECHANGELOG;
DROP TABLE DATABASECHANGELOGLOCK;
DROP SEQUENCE post_seq;

CREATE TABLE MEMBER (
                        id NUMBER(5) PRIMARY KEY,
                        user_id VARCHAR2(20) UNIQUE NOT NULL,
                        password VARCHAR2(20) NOT NULL,
                        email VARCHAR2(50) UNIQUE NOT NULL,
                        nickname VARCHAR2(12) UNIQUE NOT NULL,
                        created_at TIMESTAMP);

CREATE SEQUENCE member_seq
    START WITH 1
    INCREMENT BY 1
    CACHE 10;

DROP TRIGGER trg_set_id;

CREATE OR REPLACE TRIGGER trg_set_id
    BEFORE INSERT ON member
    FOR EACH ROW
BEGIN
    :new.id := member_seq.NEXTVAL;
END;

DROP TABLE MEMBER PURGE;

SELECT * FROM member;

CREATE TABLE post (
                      id NUMBER(5) PRIMARY KEY NOT NULL,
                      nickname VARCHAR2(12) REFERENCES MEMBER(nickname),
                      title VARCHAR2(30),
                      content VARCHAR2(100));

DROP TABLE post PURGE;

SELECT CONSTRAINT_NAME FROM user_constraints;

ALTER TABLE post ADD CONSTRAINT fk_member_nickname FOREIGN KEY (nickname) REFERENCES member(nickname);
ALTER TABLE post DROP CONSTRAINT fk_member_nickname;