package com.ourDo.dao;

import java.util.ArrayList;

import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.support.SqlSessionDaoSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.ourDo.dto.BoardDTO;

//이 클래스는   DB의   board, fileInfo  테이블과 관련한  기능을 제공하는 클래스이다

//myBatis를 사용하는  DAO 클래스를 만들기 위해서는
//반드시   SqlSessionDaoSupport을 상속받아서 제작해야 한다
//이 클래스가   커넥션 풀을 이용해서   connection받고,
// 스테이트먼트를 만들어서 관리하는 클래스이다
//이 클래스가 없으면 myBatis를 이용해서  db에 접속할 수 없다
public class BoardDAO extends SqlSessionDaoSupport{

	//변수
	/* 스테이트먼트역할 할  SqlSessionTemplate를 자동주입해서 사용  */
	@Autowired
	private SqlSessionTemplate  session;
	
	//함수
	//다운로드 첨부파일 조회
	public BoardDTO   getDownLoadFile(int fno) {
		return (BoardDTO)session.selectOne("board.downLoadFile", fno);
	}
	
	
	
	//글삭제
	public void  delBoard(BoardDTO bDto) {
		session.update("board.delBoard", bDto);
	}
	
	
	
	//상세조회- 게시판정보 조회
	public BoardDTO boardDetail(int oriNo) {
		return (BoardDTO)session.selectOne("board.boardDetail", oriNo);
	}
	
	//첨부파일정보 조회
	public ArrayList<BoardDTO>   getBoardFile(int oriNo) {
		return (ArrayList)session.selectList("board.boardFile", oriNo);
	}
	
	//조회수증가
	public void hitUp(int oriNo) {
		session.update("board.hitUp",oriNo);
	}
	
	//글 목록 조회 
	public ArrayList<BoardDTO> getList(BoardDTO bDto) {
		/* ArrayList list = (ArrayList)session.selectList("board.boardList", bDto);
		   return    list; */
		return (ArrayList)session.selectList("board.boardList", bDto);
	}
	
	//총 게시물수 조회
	public int getTotalCnt() {
		int cnt = session.selectOne("board.totalCnt");
		return cnt;
	}

	//게시글 입력(첨부파일정보)
	// 일반적으로 하나의 dao함수에서  쿼리문을 하나씩 실행하지만
	// 여기에서는 하나의 dao함수에서 2개의 쿼리문을 실행할 수 도 있다는 것을.. 
	public  void  insertBoard(BoardDTO bDto, String type) {
		
		if(type.equals("board")) {//게시글 입력
			session.insert("board.insBoard", bDto);
		}else if(type.equals("fileInfo")) {//첨부파일정보 입력
			session.insert("board.insFileInfo", bDto);
		}
		
	}

	//수정처리 - 누가, 제목, 내용, 비번,..
	public void updateBoard(BoardDTO bDto) {
		session.update("board.updateBoard", bDto);
		
	}

	//수정처리-기존의 첨부된 파일들을 db에서도 삭제
	public void deleteFileInfo(int oriBNo) {
		session.delete("board.deleteFileInfo", oriBNo);
	}


	//검색관련 페이징처리를 위한 검색게시물수 조회
	public int getSearchTotalCnt(BoardDTO bDto) {
		return (Integer)session.selectOne("board.searchTotalCnt",bDto);
		
	}


	//검색결과 조회
	public ArrayList<BoardDTO> getSearchList(BoardDTO bDto) {
		return (ArrayList)session.selectList("board.searchBoardList",bDto);
	}




	
}
















