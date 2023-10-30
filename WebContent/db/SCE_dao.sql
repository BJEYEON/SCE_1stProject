--------------------------------------------------------------
---------------  MemberDao에 들어갈 query ---------------------
--------------------------------------------------------------
-- (1) 회원id 중복체크
SELECT * FROM MEMBER WHERE mId='go';
-- (2) 회원 EMAIL 중복체크
SELECT COUNT(*) CNT FROM MEMBER WHERE MEMAIL='aaa@naver.com';
-- (3) 회원가입
INSERT INTO MEMBER (MID, MPW, MNAME, MEMAIL, MTEL, MBIRTH, MGENDER, MADDRESS)
    VALUES ('jimin', '1', '카리나', 'gayun@naver.com', '010-9999-9999', '1972/12/12','여', '서울');
-- (4) 로그인
SELECT * FROM MEMBER WHERE mID='jimin' and mPW='1';
-- (5) mid로 dto가져오기(로그인 성공시 session에 넣기 위함)
SELECT * FROM MEMBER WHERE mId='jimin';
-- (6) 회원정보 수정
UPDATE MEMBER SET mPw = '1',
                    mName = '카리나2',
                    mEmail = 'aaa1@naver.com',
                    MTEL = '010-9999-9999',
                    mBirth = '1991/12/12',
                    mGENDER = '남',
                    mAddress = '서울'
        WHERE mId='gayun';
COMMIT;
-- (7) 회원탈퇴
DELETE FROM MEMBER WHERE MID='카리나2';
ROLLBACK;

--------------------------------------------------------------
-----------------  BoardDao에 들어갈 query --------------------
--------------------------------------------------------------
-- (1) 글목록(startRow~endRow)
SELECT B.*, MNAME FROM BOARD B, MVC_MEMBER M
  WHERE B.MID=M.MID 
  ORDER BY BGROUP DESC, BSTEP; -- 출력 기준
SELECT * FROM
  (SELECT ROWNUM RN, A.* FROM (SELECT B.*, MNAME FROM BOARD B, MEMBER M
                              WHERE B.MID=M.MID 
                              ORDER BY BGROUP DESC, BSTEP) A)
  WHERE RN BETWEEN 1 AND 7; -- dao에 쓸 query
-- (2) 글갯수
SELECT COUNT(*) CNT FROM BOARD;
-- (3) 글쓰기(원글쓰기)
SELECT * FROM BOARD;
INSERT INTO BOARD (BNUM, MID, BTITLE, BCONTENT, BIMAGE, BHIT, BGROUP, BSTEP, BINDENT, BIP)
    VALUES (BOARD_SEQ.NEXTVAL, 'go', '제목', '내용', 'a.docs', 0, BOARD_SEQ.CURRVAL, 0, 0, '192.267.5.4');
-- (4) hit 1회 올리기
UPDATE BOARD SET BHIT = BHIT+1 WHERE BNUM='1';
-- (5) 글번호(Bid)로 글전체 내용(BoardDto) 가져오기
SELECT B.*, MNAME
  FROM BOARD B, MEMBER M WHERE B.MID=M.MID AND BNUM=1;
-- (6) 글 수정하기(Bid, Btitle, Bcontent, BIMAGE, Bdate(SYSDATE), Bip 수정)
UPDATE BOARD
    SET BTITLE = '바꾼제목',
        BCONTENT = '바꾼내용',
        BIMAGE = 'B',
        BDATE = SYSDATE,
        BIP = '192.268.5.4'
    WHERE BNUM=1;
-- (7) 글 삭제하기
COMMIT;
-- 글 삭제시 해당 글 하나 삭제하기 (삭제하려는 글의 BID필요. 3번글 삭제)
DELETE FROM BOARD WHERE BNUM=3;
ROLLBACK;
-- 글 삭제시 답변글까지 삭제하는 로직(삭제하려는 글의 BGROUP, BSTEP, BINDENT 필요)
SELECT * FROM BOARD ORDER BY BGROUP DESC, BSTEP;
DELETE FROM BOARD WHERE BGROUP = 1 AND (BSTEP>=1 AND 
    BSTEP<(SELECT NVL(MIN(BSTEP),9999) 
          FROM BOARD WHERE BGROUP=1 AND BSTEP>1 AND BINDENT<=1));
UPDATE BOARD SET BSTEP = BSTEP-2 WHERE BGROUP=1 AND BSTEP>2;-- 생략 가능(2은 위의 DELETE문 수행결과) :BSTEP재조정

-- (8) 답변글 쓰기 전 단계(원글의 Bgroup과 같고, 원글의 Bstep보다 크면 Bstep을 하나 증가하기)
UPDATE BOARD SET BSTEP = BSTEP +1
    WHERE BGROUP=1 AND BSTEP>0;
-- (9) 답변글 쓰기
SELECT * FROM BOARD;
INSERT INTO BOARD (BNUM, MID, BTITLE, BCONTENT, BIMAGE, BGROUP, BSTEP, BINDENT, BIP)
  VALUES (BOARD_SEQ.NEXTVAL, 'go', '제목', '내용', 'a.docs', 1, 0, 0, '192.267.5.4');
-- (10) 회원탈퇴시 탈퇴하는 회원(mid)이 쓴 글 모두 삭제하기
DELETE FROM BOARD WHERE MID='go';
--------------------------------------------------------------
-----------------  AdminDao에 들어갈 query --------------------
--------------------------------------------------------------
-- (1) admin 로그인
SELECT * FROM ADMIN;
SELECT * FROM ADMIN WHERE AID='admin' and APW='1';
-- (2) 로그인 후 세션에 넣을 용도 : admin aid로 dto 가져오기
SELECT * FROM ADMIN WHERE AId='admin';

--------------------------------------------------------------
-----------------  TestCarApplyDao에 들어갈 query --------------------
--------------------------------------------------------------
-- (1) 시승신청목록(startRow~endRow)
SELECT * FROM TESTCARAPPLY ORDER BY TNUM;
SELECT ROWNUM RN, A.*
    FROM (SELECT * FROM TESTCARAPPLY ORDER BY TNUM DESC) A;
    SELECT T.*, CNAME FROM TESTCARAPPLY T, CAR C WHERE T.CNUM=C.CNUM ORDER BY TNUM DESC;
SELECT * 
    FROM (SELECT ROWNUM RN, A.*
        FROM (SELECT T.*, CNAME FROM TESTCARAPPLY T, CAR C WHERE T.CNUM=C.CNUM ORDER BY TNUM DESC) A)  
    WHERE RN BETWEEN 1 AND 12;
-- (2) 시승신청갯수
SELECT COUNT(*) CNT FROM TESTCARAPPLY;
-- (3) 시승신청 받기
SELECT * FROM TESTCARAPPLY;
INSERT INTO TESTCARAPPLY (TNUM, CNUM, TNAME, TTEL, TGENDER, TAREA, THALL, TSELMIND, TEMAIL)
    VALUES (TESTCARAPPLY_SEQ.NEXTVAL, 1, '카리나', '010-8888-8888', '남', '서울', '강남전시장', '1년뒤', 'CA@naver.com');
--4. 차량상세보기(BID로 DTO가져오기)
SELECT * FROM TESTCARAPPLY WHERE TNUM=1;
--------------------------------------------------------------
-----------------  CarDao에 들어갈 query --------------------
--------------------------------------------------------------
-- (1) 차량목록
SELECT * FROM CAR ORDER BY CNUM;
-- (2) 차량대수 
SELECT COUNT(*) CNT FROM CAR;
-- (3) 차량 상세정보
SELECT * FROM CAR WHERE CNAME=UPPER('우루스s');


commit;



