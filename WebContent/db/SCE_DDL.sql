DROP TABLE BOARD CASCADE CONSTRAINTS;
DROP SEQUENCE BOARD_SEQ;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE TESTCARAPPLY;
DROP SEQUENCE TESTCARAPPLY_SEQ;
DROP TABLE CAR CASCADE CONSTRAINTS;
DROP SEQUENCE CAR_SEQ;
DROP TABLE ADMIN;
----------------------------------------------------------------------
--                    MEMBER    테이블 생성                      --
----------------------------------------------------------------------
CREATE TABLE MEMBER(
    MID VARCHAR2(100) PRIMARY KEY,
    MPW VARCHAR2(100) NOT NULL,
    MNAME VARCHAR2(100) NOT NULL,
    MEMAIL VARCHAR2(100),
    MTEL VARCHAR2(100), 
    MBIRTH DATE,
    MGENDER VARCHAR2(5) NOT NULL,
    MADDRESS VARCHAR2(100));
    
-- DUMMY DATA
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('gayun', '1', '김가연', 'gayun@naver.com', '010-9999-9999', '1972/12/12', '서울');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('gico', '1', '지코', 'gico@naver.com', '010-8888-8888', '1972/12/12', '경기');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('go', '1', '고소영', 'go@naver.com', '010-7777-7777', '1972/12/12', '서울');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('han', '1', '한지민', 'han@naver.com', '010-6666-6666', '1972/12/12', '서울');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('kang', '1', '강동원', null, null, '1972/12/12', '서울');
    
----------------------------------------------------------------------
--                   BOARD 테이블 생성                           --

CREATE SEQUENCE BOARD_SEQ MAXVALUE 9999999 NOCACHE NOCYCLE;
CREATE TABLE BOARD(
    BNUM NUMBER(4) PRIMARY KEY,
    MID VARCHAR2(100) REFERENCES MEMBER(MID) NOT NULL,
    BTITLE VARCHAR2(100) NOT NULL,
    BCONTENT VARCHAR2(300),
    BPW VARCHAR2(100) NOT NULL,
    BDATE DATE DEFAULT SYSDATE NOT NULL,
    BIMAGE VARCHAR2(100),
    BHIT NUMBER(4) DEFAULT 0 NOT NULL,
    BGROUP NUMBER(4) NOT NULL,
    BSTEP NUMBER(4) NOT NULL,
    BINDENT NUMBER(4) NOT NULL,
    BIP VARCHAR2(100) NOT NULL);
----------------------------------------------------------------------

SELECT * FROM BOARD ORDER BY BGROUP DESC, BSTEP;
----------------------------------------------------------------------
--                   Car 테이블 생성                      -- 
CREATE SEQUENCE CAR_SEQ MAXVALUE 9999999 NOCACHE NOCYCLE;
CREATE TABLE CAR(
    CNUM NUMBER(4) PRIMARY KEY,
    CNAME VARCHAR2(100) NOT NULL,
    CTYPE VARCHAR2(100) NOT NULL,
    COIL VARCHAR2(100) NOT NULL,
    CYEAR VARCHAR2(100) NOT NULL,
    CIMAGE VARCHAR2(100) NOT NULL);
    
-- DUMMY DATA

SELECT * FROM CAR;
    
----------------------------------------------------------------------
--                   TestCarApply 테이블 생성                      --
CREATE SEQUENCE TESTCARAPPLY_SEQ MAXVALUE 9999999 NOCACHE NOCYCLE;
CREATE TABLE TESTCARAPPLY(
    TNUM NUMBER(4) PRIMARY KEY,
    CNUM NUMBER(4) REFERENCES CAR(CNUM) NOT NULL,
    TNAME VARCHAR2(100) NOT NULL,
    TTEL VARCHAR2(100) NOT NULL,
    TGENDER VARCHAR2(5) NOT NULL,
    TAREA VARCHAR2(100) NOT NULL,
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    