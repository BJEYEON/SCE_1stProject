DROP TABLE BOARD CASCADE CONSTRAINTS;
DROP SEQUENCE BOARD_SEQ;
DROP TABLE MEMBER CASCADE CONSTRAINTS;
DROP TABLE TESTCARAPPLY;
DROP SEQUENCE TESTCARAPPLY_SEQ;
DROP TABLE CAR CASCADE CONSTRAINTS;
DROP SEQUENCE CAR_SEQ;
DROP TABLE ADMIN CASCADE CONSTRAINTS;
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
    VALUES ('gayun', '1', '김가연', 'gayun@naver.com', '010-9999-9999', '1972/12/12','여', '서울');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('gico', '1', '지코', 'gico@naver.com', '010-8888-8888', '1972/12/12','남', '경기');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('go', '1', '고소영', 'go@naver.com', '010-7777-7777', '1972/12/12','여', '서울');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('han', '1', '한지민', 'han@naver.com', '010-6666-6666', '1972/12/12','여', '서울');
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('kang', '1', '강동원', null, null, '1972/12/12','남', '서울');

SELECT * FROM MEMBER;
----------------------------------------------------------------------
--                   BOARD 테이블 생성                           --

CREATE SEQUENCE BOARD_SEQ MAXVALUE 9999999 NOCACHE NOCYCLE;
CREATE TABLE BOARD(
    BNUM NUMBER(4) PRIMARY KEY,
    MID VARCHAR2(100) REFERENCES MEMBER(MID) NOT NULL,
    BTITLE VARCHAR2(100) NOT NULL,
    BCONTENT VARCHAR2(300),
    BDATE DATE DEFAULT SYSDATE NOT NULL,
    BIMAGE VARCHAR2(100),
    BHIT NUMBER(4) DEFAULT 0 NOT NULL,
    BGROUP NUMBER(4) NOT NULL,
    BSTEP NUMBER(4) NOT NULL,
    BINDENT NUMBER(4) NOT NULL,
    BIP VARCHAR2(100) NOT NULL);
----------------------------------------------------------------------
--검색
SELECT * FROM BOARD ORDER BY BGROUP DESC, BSTEP;
----------------------------------------------------------------------
--                   Car 테이블 생성                      -- 
CREATE SEQUENCE CAR_SEQ MAXVALUE 9999999 NOCACHE NOCYCLE;
CREATE TABLE CAR(
    CNUM NUMBER(4) PRIMARY KEY,
    CNAME VARCHAR2(100) NOT NULL,
    COIL VARCHAR2(100),
    CENGINE VARCHAR2(100),
    CTRANCE VARCHAR2(100),
    CHP VARCHAR2(100),
    CCC VARCHAR2(100),
    CYEAR VARCHAR2(100),
    CIMAGE VARCHAR2(100));
    
-- DUMMY DATA
INSERT INTO CAR (CNUM, CNAME, COIL, CENGINE, CTRANCE, CHP, CCC, CYEAR, CIMAGE)
    VALUES(CAR_SEQ.NEXTVAL, '레부엘토', '하이브리드', 'V12', 'DCT8단', '1,015hp', '6,498cc', '2023년식', '레부엘토.JPG');
INSERT INTO CAR (CNUM, CNAME, COIL, CENGINE, CTRANCE, CHP, CCC, CYEAR, CIMAGE)
    VALUES(CAR_SEQ.NEXTVAL, '아벤타도르SVJ', '가솔린', 'V12', '싱글클러치7단', '770hp', '6,498cc', '2019년식', '아벤타도르SVJ.JPG');
INSERT INTO CAR (CNUM, CNAME, COIL, CENGINE, CTRANCE, CHP, CCC, CYEAR, CIMAGE)
    VALUES(CAR_SEQ.NEXTVAL, '우라칸STO', '가솔린', 'V10', 'DCT7단', '640hp', '5,204cc', '2023년식', '우라칸STO.JPG');
INSERT INTO CAR (CNUM, CNAME, COIL, CENGINE, CTRANCE, CHP, CCC, CYEAR, CIMAGE)
    VALUES(CAR_SEQ.NEXTVAL, '우루스S', '가솔린', 'V8', '자동8단', '666hp', '3,996cc', '2023년식', '우루스S.JPG');
--검색
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
    THALL VARCHAR2(100) NOT NULL,
    TSELMIND VARCHAR2(100) NOT NULL,
    TEMAIL VARCHAR2(100));
--검색
SELECT * FROM TESTCARAPPLY;
----------------------------------------------------------------------
--                   Admin 테이블 생성                      -- 
CREATE TABLE ADMIN(
    AID VARCHAR2(100) PRIMARY KEY,
    APW VARCHAR2(100) NOT NULL,
    ANAME VARCHAR2(100) NOT NULL,
    ATEL VARCHAR2(100));
-- DUMMY DATA
INSERT INTO ADMIN(AID, APW, ANAME, ATEL)
    VALUES('admin', '1', '관리자', NULL);
--검색
SELECT * FROM ADMIN;

COMMIT;
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    