------------------------------- TABLE DROP ------------------------------
DROP TABLE MEMBER;
DROP TABLE ADMIN;
DROP TABLE CONSULTANT;
DROP SEQUENCE CONSULTANT_SEQ;
DROP TABLE REVIEW_BOARD;
DROP SEQUENCE REVIEWBOARD_SEQ;
DROP TABLE INTERIOR_PRODUCT;
DROP SEQUENCE INTERIOR_PRODUCT_SEQ;
DROP TABLE NOTICE_BOARD;
DROP SEQUENCE NOTICEBOARD_SEQ;
--------------------------------- MEMBER ---------------------------------
CREATE TABLE MEMBER (
    mID VARCHAR2(100) PRIMARY KEY,
    mPW VARCHAR2(100) NOT NULL,
    mNAME VARCHAR2(100) NOT NULL,
    mTEL VARCHAR2(100) NOT NULL,
    mBIRTH DATE,
    mEMAIL VARCHAR2(100) NOT NULL,
    mGENDER VARCHAR2(5),
    mADDRESS VARCHAR2(100) NOT NULL,
    mRDATE DATE DEFAULT SYSDATE NOT NULL
);
SELECT * FROM MEMBER;
-- DUMMY DATA
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('aaa', '111', '김길마', '010-6666-6503', '1980/09/23','kim@naver.com', 'f','서울시 서대문구 신촌동');
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('bbb', '111', '김숙', '010-2342-3434', '1970/06/24','kim@naver.com', 'f','서울시 마포구 성산');
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('ccc', '111', '유재석', '010-1263-2344', '1975/05/12','kim@naver.com', 'm','서울시 종로구 평창동');
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('ddd', '111', '정지훈', '010-2357-7235', '1987/04/12','kim@naver.com', 'm','경기도 고양시 고양동');
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('eee', '111', '조세호', '010-2341-1525', '1979/07/14','kim@naver.com', 'm','서울시 은평구 역촌동');
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('fff', '111', '이동욱', '010-7435-6324', '1989/11/23','kim@naver.com', 'm','충청남도 충청시 충청동');
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('ggg', '111', '신봉선', '010-6324-7435', '1988/12/30','kim@naver.com', 'f','강원도 강릉시 강릉동');
--------------------------------- ADMIN ---------------------------------

CREATE TABLE ADMIN (
    aID VARCHAR2(100) PRIMARY KEY,
    aPW VARCHAR2(100) NOT NULL,
    aNAME VARCHAR2(100) NOT NULL
);
SELECT * FROM ADMIN;
-- DUMMY DATA
INSERT INTO ADMIN(aID, aPW, aNAME)
    VALUES ('admin', '1', '관리자');
--------------------------------- CONSULTANT ---------------------------------

CREATE TABLE CONSULTANT (
    cID NUMBER(6) PRIMARY KEY,
    mID VARCHAR2(100),
    aID VARCHAR2(100),
    cTITLE VARCHAR2(250) NOT NULL,
    cCONTENT VARCHAR2(4000),
    cFILENAME VARCHAR2(100),
    cRDATE DATE DEFAULT SYSDATE,
    cGROUP NUMBER(6) NOT NULL,
    cSTEP NUMBER(6) NOT NULL,
    cINDENT NUMBER(6) NOT NULL,
    cIP VARCHAR2(30) NOT NULL,
    FOREIGN KEY (mID) REFERENCES MEMBER (mID),
    FOREIGN KEY (aID) REFERENCES ADMIN (aID)
);
CREATE SEQUENCE CONSULTANT_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
-- DUMMY DATA (원글과 답변)
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'aaa', null, '팝업스토어 인테리어 견적', '글 본문입니다.', '1.docx', CONSULTANT_SEQ.CURRVAL, 0, 0, '192.168.0.05');
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'bbb', null, '전시공간 견적문의 드립니다.', '글 본문입니다.', null, CONSULTANT_SEQ.CURRVAL, 0, 0, '192.168.0.05');    
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'ccc', null, '푸드코트 설계', '글 본문입니다.', '2.xlsx', CONSULTANT_SEQ.CURRVAL, 0, 0, '185.854.3.13');
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'ddd', null, '카페 인테리어 견적문의드립니다.', '글 본문입니다.', '3.xlsx', CONSULTANT_SEQ.CURRVAL, 0, 0, '124.234.6.12');
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'eee', null, '백화점 인테리어 문의', '글 본문입니다.', null, CONSULTANT_SEQ.CURRVAL, 0, 0, '156.231.6.12');
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'fff', null, '애견카페 견적문의 드립니다.', '글 본문입니다.', null, CONSULTANT_SEQ.CURRVAL, 0, 0, '436.124.5.18');
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'ggg', null, '팝업스토어 견적문의 드립니다.', '글 본문입니다.', null, CONSULTANT_SEQ.CURRVAL, 0, 0, '127.235.7.89');

-- 3번글의 답변달기
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL, null, 'admin', '푸드코드설계 답변글입니다.', '글 본문입니다.', null, 3, 1, 1, '185.854.3.13');
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL, 'ccc', null, '푸드코드설계 답의 답변글입니다.', '글 본문입니다.', '3.xlsx', 3, 2, 2, '185.854.3.13');

SELECT * FROM CONSULTANT;
SELECT * FROM CONSULTANT ORDER BY cGROUP, cSTEP;
--------------------------------- REVIEW_BOARD ---------------------------------

CREATE TABLE REVIEW_BOARD(
    rID NUMBER(6) PRIMARY KEY,
    mID VARCHAR2(100) NOT NULL,
    rTITLE VARCHAR2(250) NOT NULL,
    rCONTENT VARCHAR2(4000) NOT NULL,
    rPHOTO VARCHAR2(100) NOT NULL, -- 이미지 첨부안할시 NOIMG.jpg로
    rDATE DATE DEFAULT SYSDATE,
    rHIT NUMBER(6) DEFAULT 0,
    rIP VARCHAR2(30) NOT NULL,
    FOREIGN KEY (mID) REFERENCES MEMBER (mID)
);
CREATE SEQUENCE REVIEWBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
SELECT * FROM REVIEW_BOARD;
INSERT INTO REVIEW_BOARD (rID, mID, rTITLE, rCONTENT, rPHOTO, rIp)
    VALUES (REVIEWBOARD_SEQ.NEXTVAL, 'aaa', '공사 후 너무 잘 지내고 있어요', 
            '34평형 아파트 입니다. 남편이랑 둘이 알콩달콩 살고있구요. 인테리어 디자이너라는 직업을 가지고 있지만중이 
            제 머리 못깍는다고 일하면서 집 공사를 하기엔 무리가 있을거 같아서 그냥 집닥에서 알아보고 진행했어요. 
            기본적으로 화려하고 복잡한걸 안좋아해서 화이트 톤으로 깔끔하고 심플하게 하고 바닥은 티크로 눌러주러 공간의 무게를 잡아주고 톤을 맞춘 가구로 그때그때 분위기 맞춰서 바꿔주고 있어요.', 
            '1.jpg' ,'192.434.21.2');
INSERT INTO REVIEW_BOARD (rID, mID, rTITLE, rCONTENT, rPHOTO, rIP)
    VALUES (REVIEWBOARD_SEQ.NEXTVAL, 'bbb', '오픈더 도어로 시작된 행복', '안녕하세요. 결혼을 앞둔 예비 부부입니다. 
            한정된 예산안에 원래 살고 있던 집을 인테리어하기 위해서 7-8군데 업체를 발품팔았는데 최종적으로 고른 업체였습니다.', 
            '2.jpg', '124.244.61.5');
INSERT INTO REVIEW_BOARD (rID, mID, rTITLE, rCONTENT, rPHOTO, rIP)
    VALUES (REVIEWBOARD_SEQ.NEXTVAL,'ccc', '최고최고최고', 
            '사장님의 꼼꼼함 덕분에 너무너무 즐겁게 인테리어 했어요! 솔직하시고 알아서 척척 
            수정해야할부분 바로 잡아주시고  같이 고민해주셔서 끝까지 믿고 진행 마무리 했어요',
            '3.jpg', '116.233.77.8');
--------------------------------- INTERIOR_PRODUCT ---------------------------------

CREATE TABLE INTERIOR_PRODUCT (
    pID NUMBER(6) PRIMARY KEY,
    aID VARCHAR2(100) NOT NULL,
    pTITLE VARCHAR2(250) NOT NULL,
    pCONTENT VARCHAR2(4000) NOT NULL,
    pFILENAME VARCHAR2(100) NOT NULL,
    pRDATE DATE DEFAULT SYSDATE,
    FOREIGN KEY (aID) REFERENCES ADMIN (aID)
);
CREATE SEQUENCE INTERIOR_PRODUCT_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
SELECT * FROM INTERIOR_PRODUCT;
INSERT INTO INTERIOR_PRODUCT (pID, aID, pTITLE, pCONTENT, pFILENAME)
    VALUES (INTERIOR_PRODUCT_SEQ.NEXTVAL, 'admin', '29평 아파트', '29평', '1.jpg');
COMMIT;
--------------------------------- NOTICE_BOARD ---------------------------------

CREATE TABLE NOTICE_BOARD (
    nID NUMBER(6) PRIMARY KEY,
    aID VARCHAR2(100) NOT NULL,
    nTITLE VARCHAR2(250) NOT NULL,
    nCONTENT VARCHAR2(4000) NOT NULL,
    nRDATE DATE DEFAULT SYSDATE,
    FOREIGN KEY (aID) REFERENCES ADMIN (aID)
);
CREATE SEQUENCE NOTICEBOARD_SEQ MAXVALUE 999999 NOCACHE NOCYCLE;
SELECT * FROM NOTICE_BOARD;
INSERT INTO NOTICE_BOARD (nID, aID, nTITLE, nCONTENT)
    VALUES (NOTICEBOARD_SEQ.NEXTVAL, 'admin', '[KINTERIOR] 코로나 19 방역소독 솔루션 무료 제공', 
            '많은 분들이 코로나 바이러스로 인해 불안에 떨고 있습니다. 이에 인테리어베이 또한, 
            힘을 모아 코로나19 확진 방지를 위해 최선을 다하도록 노력하고자 방역소독 솔로션을 제공하게 되었습니다.');
COMMIT;