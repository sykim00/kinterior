-------------------------------------------------------------------------------------------
--------------------------------- MemberDao에 들어갈 query ---------------------------------
-------------------------------------------------------------------------------------------
-- 1. 회원 id중복체크
SELECT * FROM MEMBER WHERE mID = 'aaa';
-- 2. 회원가입
INSERT INTO MEMBER (mID, mPW, mNAME, mTEL, mBIRTH, mEMAIL, mGENDER, mADDRESS)
    VALUES ('hhh', '111', '김길마', '010-6666-6503', '1980/09/23','kim@naver.com', 'f','서울시 서대문구 신촌동');
-- 3. 전체 등록된 회원수
SELECT COUNT(*)CNT FROM MEMBER;
-- 4. 로그인
SELECT * FROM MEMBER WHERE mID = 'aaa' AND mPW = '111';
-- 5. mid로 dto가져오기 (로그인 성공시 session에 넣기 위해)
SELECT * FROM MEMBER WHERE mID = 'aaa';
-- 6. 회원정보 수정
UPDATE MEMBER SET 
        mPW = '1',
        mNAME = '홍길마',
        mTEL = '010-7777-7777',
        mBIRTH = '1978/12/28',
        mEMAIL = 'hong@naver.com',
        mGENDER = 'f',
        mADDRESS = '경기도 수원시'
    WHERE mID = 'aaa';
-- 7. 회원리스트 (TOP-N)
SELECT * FROM 
    (SELECT ROWNUM RN, A.* FROM (SELECT * FROM MEMBER ORDER BY mRDATE DESC)A)
    WHERE RN BETWEEN 2 AND 3;
-- 8. 회원탈퇴 (후기게시판, 견적문의 글 입력했을 경우 글 모두 다 지우고 탈퇴처리)
DELETE FROM MEMBER WHERE mID = 'aaa';
SELECT * FROM MEMBER;
------------------------------------------------------------------------------------------------
--------------------------------- AdminDao에 들어갈 query ---------------------------------
------------------------------------------------------------------------------------------------
-- 1. 관리자 로그인
SELECT * FROM ADMIN WHERE aID = 'admin' AND aPW = '1';
-- 2. 관리자 aid로 dto 가져오기 (로그인 후 session에 넣을 용도)
SELECT * FROM ADMIN WHERE aID = 'admin';
------------------------------------------------------------------------------------------------
-------------------------------- ConsultantDao에 들어갈 query (MEMBER, ADMIN) -----------------------------------
------------------------------------------------------------------------------------------------
-- 1. 견적문의 글 목록 출력   
SELECT V.*, 
    (SELECT mNAME FROM MEMBER M WHERE V.mID = M.mID) mNAME,
    (SELECT aNAME FROM ADMIN A WHERE V.aID = A.aID) aNAME
        FROM (SELECT ROWNUM RN, C.*
                FROM (SELECT * FROM CONSULTANT ORDER BY cGROUP DESC, cINDENT, cRDATE DESC)C)V
        WHERE RN BETWEEN 1 AND 11;
SELECT V.*, 
    (SELECT mNAME FROM MEMBER M WHERE V.mID = M.mID) mNAME,
    (SELECT aNAME FROM ADMIN A WHERE V.aID = A.aID) aNAME
        FROM (SELECT ROWNUM RN, C.*
            FROM (SELECT * FROM CONSULTANT ORDER BY cGROUP DESC, cSTEP)C)V
        WHERE RN BETWEEN 1 AND 11;
        
-- 2. 견적문의 글 갯수
SELECT COUNT(*)CNT FROM CONSULTANT;
-- 3. 견적문의 글쓰기(원글쓰기)
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL,'aaa', null, '팝업스토어 인테리어 견적', '글 본문입니다.', '1.docx', CONSULTANT_SEQ.CURRVAL, 0, 0, '192.168.0.05');
-- 4. 견적문의 글번호(cid)로 전체내용(consultantDto) 가져오기
SELECT V.*, (SELECT mNAME FROM MEMBER M WHERE V.mID = M.mID) mNAME,
    (SELECT aNAME FROM ADMIN A WHERE V.aID = A.aID) aNAME
    FROM (SELECT * FROM CONSULTANT)V
    WHERE cID = 1;
--  SELECT * FROM CONSULTANT WHERE cID = 6;
-- 5. 견적문의 글 수정하기
UPDATE CONSULTANT SET 
        cTITLE = '팝업스토어 인테리어 견적 수정요청',
        cCONTENT = '글 본문 수정',
        cFILENAME = null,
        cIP = '192.456.21.4'
    WHERE cID = '1';
-- 6. 견적문의 글 삭제하기
    -- 글 삭제시 답변글까지 삭제하는(삭제하려는 글의 cgroup, cindent, cstep이 필요)
    -- 3번글 (3-1 지우기)
    DELETE FROM CONSULTANT WHERE cGROUP = 3 AND (cSTEP >=1 AND cSTEP < (SELECT NVL(MIN(cSTEP),99) FROM CONSULTANT WHERE cGROUP = 3 AND cSTEP >1 AND cINDENT <=1)); 
    -- 6번글 (6-1 지우기)
    DELETE FROM CONSULTANT 
                WHERE cGROUP = 6 AND (cSTEP >=1 AND cSTEP < (SELECT NVL(MIN(cSTEP),99) 
                    FROM CONSULTANT WHERE cGROUP = 6 AND cSTEP > 1 AND cINDENT <=1));
-- 7. 견적문의 답변글 쓰기 전단계
UPDATE CONSULTANT SET cSTEP = cSTEP + 1 WHERE cGROUP = 3 AND cSTEP > 0;
-- 8. 견적문의 답변글 쓰기
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL, null, 'admin', 
            '애견카페 견적문의 드립니다. 답변글입니다.', '글 본문입니다.', null, 6, 1, 1, '185.854.3.13');
INSERT INTO CONSULTANT (cID, mID, aID, cTITLE, cCONTENT, cFILENAME, cGROUP, cSTEP, cINDENT, cIP)
    VALUES (CONSULTANT_SEQ.NEXTVAL, 'fff', null, 
            '애견카페 견적문의 드립니다. 답의 답변글입니다.', '글 본문입니다.', '3.xlsx', 6, 2, 2, '185.854.3.13');
-- 9. 회원탈퇴시 탈퇴하는 회원(mid)이 쓴 견적문의 글 모두 삭제하기
DELETE FROM CONSULTANT WHERE mID = 'aaa';
-- 답글
SELECT LPAD('└', 4, ' ') FROM DUAL;
SELECT cID, cTITLE, mID, cRDATE FROM CONSULTANT;
SELECT cID, LPAD('└', (cINDENT * 2), ' ')||cTITLE TITLE, cGROUP, cINDENT, cSTEP FROM CONSULTANT ORDER BY cGROUP, cSTEP;
SELECT * FROM CONSULTANT WHERE MID = 'aaa' AND cGROUP = 1;
-- 10. ccc가 쓴 글의 group
SELECT DISTINCT CGROUP FROM CONSULTANT WHERE MID = 'ccc';
------------------------------------------------------------------------------------------------
--------------------------------- ReviewBoardDao에 들어갈 query (MEMBER)---------------------------------
------------------------------------------------------------------------------------------------
-- 1. 리뷰글 목록 출력
SELECT * FROM 
    (SELECT ROWNUM RN, R.* FROM (SELECT R.*, mNAME FROM REVIEW_BOARD R, MEMBER M 
                                WHERE R.mID = M.mID ORDER BY rID DESC)R)
    WHERE RN BETWEEN 1 AND 3; 
-- 2. 리뷰글 갯수
SELECT COUNT(*)CNT FROM REVIEW_BOARD;
-- 3. 리뷰글쓰기(원글쓰기)
INSERT INTO REVIEW_BOARD (rID, mID, rTITLE, rCONTENT, rPHOTO, rIP)
    VALUES (REVIEWBOARD_SEQ.NEXTVAL,'ddd', '헌집이 새집 되었어요', 
            '견적서에 있는것만 잘해도 되겠거니 하고 공사 진행했는데 말씀드리지 않았던 부분도 세세하게 모두 손봐주셨어요', 
            '4.jpg' , '142.512.21.0');
-- 4. 리뷰글번호(rid)로 전체내용(reviewBoard) 가져오기
SELECT R.*, mNAME FROM REVIEW_BOARD R, MEMBER M WHERE R.mID = M.mID AND rID = 2;
-- 5. 리뷰글 HIT수 올리기
UPDATE REVIEW_BOARD SET rHIT = rHIT +1 WHERE rID = 1;
-- 6. 리뷰글 수정하기
UPDATE REVIEW_BOARD SET 
        rTITLE = '집 너무 이뻐서, 내집 같지 않음',
        rCONTENT = '타업체보다  꼼꼼하시고 젊은 분들이라 감각, 안목, 소통, 공유 정말 뭐하나 불만족인게 없었습니다.',
        rPHOTO = '5.jpg',
        rIP = '131.58.225.1'
    WHERE rID = 4;
-- 7. 리뷰글 삭제하기
DELETE FROM REVIEW_BOARD WHERE rID = 4;
-- 8. 회원탈퇴시 탈퇴하는 회원(mid)이 쓴 리뷰글 모두 삭제하기
DELETE FROM REVIEW_BOARD WHERE mID = 'ddd';
--------------------------------- Interior_ProductDao에 들어갈 query (ADMIN) ---------------------------------
-- 1. 시공사례글 목록 출력
SELECT * FROM 
    (SELECT ROWNUM RN, P.* FROM (SELECT P.*, aNAME FROM INTERIOR_PRODUCT P, ADMIN A
                                 WHERE P.aID = A.aID ORDER BY pRDATE DESC)P)
    WHERE RN BETWEEN 1 AND 3;
-- 3. 시공사례글쓰기(원글쓰기)
INSERT INTO INTERIOR_PRODUCT (pID, aID, pTITLE, pCONTENT, pFILENAME)
    VALUES (INTERIOR_PRODUCT_SEQ.NEXTVAL, 'admin', '29평 아파트', '29평', '2.jpg');
-- 4. 시공사례글번호(pid)로 전체내용(InteriorDto) 가져오기
SELECT P.*, aNAME FROM INTERIOR_PRODUCT P, ADMIN A WHERE P.aID = A.aID AND pID = 1 ;
-- 5. 시공사례글 수정하기
UPDATE INTERIOR_PRODUCT SET 
        pTITLE = '40평 아파트',
        pCONTENT = '40평',
        pFILENAME = 'interior.jpg'
    WHERE pID = 1;
-- 6. 시공사례글 삭제하기
DELETE FROM INTERIOR_PRODUCT WHERE pID = 1;
--------------------------------- Notice_BoardDao에 들어갈 query(ADMIN) ---------------------------------
-- 1. 공지사항글 목록 출력
SELECT * FROM 
    (SELECT ROWNUM RN, N.* FROM (SELECT N.*, aNAME FROM NOTICE_BOARD N, ADMIN A 
                                 WHERE N.aID = A.aID ORDER BY nRDATE DESC)N)
    WHERE RN BETWEEN 1 AND 3;
-- 2. 공지사항글쓰기
INSERT INTO NOTICE_BOARD (nID, aID, nTITLE, nCONTENT)
    VALUES (NOTICEBOARD_SEQ.NEXTVAL, 'admin', 'KINTERIOR 홈페이지 신규 리뉴얼 안내', 
            '안녕하십니까? 2023년 3월 30일, KINTERIOR 홈페이지가 새로운 모습으로 문을 엽니다. 많은 성원과 관심 부탁드립니다. 감사합니다.');
-- 3. 공지사항글번호(nID)로 전체내용(NoticeDto) 가져오기
SELECT N.*, aNAME FROM NOTICE_BOARD N, ADMIN A WHERE N.aID = A.aID AND nID = 1;
-- 4. 공지사항글 수정하기
UPDATE NOTICE_BOARD SET 
        nTITLE = 'KINTERIOR 홈페이지 신규 리뉴얼 안내',
        nCONTENT = '안녕하십니까? 2023년 3월 30일, KINTERIOR 홈페이지가 새로운 모습으로 문을 엽니다. 많은 성원과 관심 부탁드립니다. 감사합니다.'
    WHERE nID = 1;
-- 5. 공지사항글 삭제하기
DELETE FROM NOTICE_BOARD WHERE nID = 1;

-- 6. 공지사항 글 갯수
SELECT COUNT(*)CNT FROM NOTICE_BOARD;
ROLLBACK;
COMMIT;