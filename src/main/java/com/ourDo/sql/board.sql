-- 목록조회
SELECT
   b_no    AS no,
   b_title         AS title,
   b_writer   AS writer,
   b_wdate AS wdate,
   b_hit   AS hit,
   NVL(f1.CNT,0)       AS fcount
FROM     BOARD,
   (SELECT            count(*) AS CNT
    FROM       fileinfo
    GROUP BY  f_oriBno     ) f1
WHERE  b_show=1



SELECT
				b_no		AS no,
				b_title AS title,
				b_writer AS writer,
				b_wdate	AS 작성일,
				b_hit	AS 조회수
				/*,첨부파일수*/
FROM   BOARD
WHERE  b_show=1;

SELECT 	  f_oriBno, count(*) AS CNT
FROM	   	fileinfo
GROUP BY  f_oriBno	
/


-- ----------------------------------------------
show user

DROP TABLE  board;

--게시판글 정보
CREATE TABLE  board(
	b_No 		number(5),		/*글번호*/
	b_writer		varchar2(100),	/*작성자*/
	b_title		varchar2(200),	/*제목*/
	b_content	clob,			/*내용*/
	b_wdate  	date,			/*작성일-SYSDATE	*/
	b_pw		varchar2(30),		/*비밀번호*/
	b_hit		number(4),		/*조회수- 0(기본값)	*/
	b_show		number(1)		 /*노출여부-	노출:1(기본값)	비노출:0*/
);   

INSERT   INTO   board   VALUES(  1, 'hongid', '제목1', '내용1', SYSDATE, '1234', 0, 1 );
INSERT   INTO   board   VALUES(  2, 'hongid', '제목2', '내용2', SYSDATE, '1234', 0, 1 );
INSERT   INTO   board   VALUES(  3, 'kimid',   '제목3', '내용3', SYSDATE, '1234', 0, 1 );
COMMIT;


DROP TABLE  fileInfo;

--첨부파일 정보
create table   fileInfo(
	f_no			number(5),	  /*첨부파일번호*/
	f_oriBNo		number(5),	  /*원글번호*/
	f_name		varchar2(200), /*파일명*/
	f_saveName	varchar2(600), /*저장파일명*/
	f_path		varchar2(200), /*파일이 저장된 경로*/
	f_size		number(12)	 /*파일크기*/
);   