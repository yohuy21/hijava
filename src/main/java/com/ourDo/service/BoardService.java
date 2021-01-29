package com.ourDo.service;

import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;

import com.ourDo.dao.BoardDAO;
import com.ourDo.dto.BoardDTO;
import com.ourDo.util.PageUtil;

//이 클래스는  파일게시판과 관련한 기능을 제공하는 클래스이다
public class BoardService {
	//변수
	@Autowired
	private BoardDAO bDAO;
	
	//생성자
	//다운로드 첨부파일 조회
	public BoardDTO   getDownLoadFile(int fno) {
		return bDAO.getDownLoadFile(fno);
	}
	
	//함수
	//글삭제
	public void  delBoard(BoardDTO bDto) {
		bDAO.delBoard(bDto);
	}
	
	//상세조회- 게시판정보 조회
	public BoardDTO boardDetail(int oriNo) {
		return bDAO.boardDetail(oriNo);
		
	}
	
	//첨부파일정보 조회
	public ArrayList<BoardDTO> getBoardFile(int oriNo) {
		ArrayList<BoardDTO> list = bDAO.getBoardFile(oriNo);
		return list;
	}
	
	//조회수증가
	public void hitUp(int oriNo) {
		bDAO.hitUp(oriNo);
	}

	//글목록조회
	public ArrayList<BoardDTO> getList(PageUtil pageInfo) {
		//실행하고자하는 쿼리문 where rno BETWEEN #{start} AND #{end}이므로
		//필요한 정보를 완성해서 보내야 한다
		//#{start} : 보고싶은페이지에서의 시작글번호
		//#{end}   : 보고싶은페이지에서의 종료글번호
		//한페이지당 출력하는 글개수를 3개로 지정한 상태
		//보고싶은페이지	start	end
		//1				1		3
		//2				4		6
		int start = (pageInfo.getNowPage()-1)*pageInfo.getLineCount()+1;
		int end	  = start + pageInfo.getLineCount() - 1;
		
		//실행하게 되는 SQL문에서 파라미터는 BoardDTO로 받기로 했으므로
		BoardDTO bDto = new BoardDTO();
		bDto.setStart(start);
		bDto.setEnd(end);
		
		ArrayList<BoardDTO> list = bDAO.getList(bDto);
		return list;
	}
	
	//검색관련 페이징처리를 위한 검색게시물수 조회
	public PageUtil getSearchPageInfo(int nowPage, BoardDTO bDto) {

		int totalCount = bDAO.getSearchTotalCnt(bDto);
		System.out.println("검색관련 totalCount ="+totalCount);
		
		PageUtil pageInfo = new PageUtil(nowPage, totalCount); 
		//PageUtil(보고싶은페이지, 총게시물수, 한페이지당 출력하는 글개수, 페이지블록);
		
		return pageInfo;
	}
	
	//글목록조회를 위한  페이지정보구하기
	public PageUtil getPageInfo(int nowPage) {
		//필수적인 정보(총 게시물수)가 필요하다
		int totalCount = bDAO.getTotalCnt();
		
		PageUtil pageInfo = new PageUtil(nowPage, totalCount); 
		//PageUtil(보고싶은페이지, 총게시물수, 한페이지당 출력하는 글개수, 페이지블록);
		//PageUtil(int nowPage, int totalCount, int lineCount, int pageGroup) {
		//PageUtil pageInfo = new PageUtil(nowPage, totalCount, 3, 5); 
		
		return pageInfo;
	}

	
	
	//글 쓰기
	//"board" 게시글 입력
	//"fileInfo" 첨부파일정보 입력
	public void insertBoard(BoardDTO bDto,HttpSession session,ArrayList list) {
		
		//첫번째 파라미터인  BoardDTO: 유저가 입력한 정보를 컨트롤러에서 받는다
		//2번째 파라미터인  HttpSession: 로그인한 유저의 정보를 세팅하기 위함
		//3번째 파라미터인  ArrayList :  첨부파일(들)에 대한 정보를 받기 위함
		
		/*
		*로그인한 유저의 정보
		session.setAttribute("USERID", result.get("ID"));	//로그인한 유저의 id
		session.setAttribute("USERNAME",result.get("NAME") );	//로그인한 유저의 name
		session.setAttribute("USERNICK",result.get("NICK") );	//로그인한 유저의 nick
	 	*/
		String writer = (String)session.getAttribute("USERID");		
		bDto.setWriter(writer);
		bDAO.insertBoard(bDto, "board");// 게시글 입력
		
		//첨부파일의 개수만큼 정보를 입력해야한다
	/* //컨트롤러로부터  list안에  업로드된 하나의 파일에 대한 정보가  Map으로 넘어왔다 
		map.put("name", oriName); 
		map.put("saveName", saveName); 
		map.put("path", path); 
		map.put("size", file.length()); */
		for(int i=0; i<list.size() ;i++) {
			HashMap map = (HashMap)list.get(i);
			//#{oriBNo}, #{name}, #{saveName}, #{path}, #{size}
			bDto.setOriBNo(bDto.getNo());
			bDto.setName( (String)map.get("name") );
			bDto.setSaveName((String)map.get("saveName"));
			bDto.setPath((String)map.get("path"));
			bDto.setFsize((Long)map.get("size"));
			
			bDAO.insertBoard(bDto, "fileInfo");// 첨부파일정보 입력
		}
				
	}

	//수정처리 -board테이블에  update 누가,제목,내용,비번
	public void updateBoard(BoardDTO bDto) {
		bDAO.updateBoard(bDto);
	}

	//수정처리-기존의 첨부된 파일들을 db에서도 삭제
	public void deleteFileInfo(int oriBNo) {
		bDAO.deleteFileInfo(oriBNo);
	}

	//수정처리-파일정보만 인서트
	public void insertFileInfo(BoardDTO dto) {
		bDAO.insertBoard(dto, "fileInfo");// 첨부파일정보 입력	
	}

	//검색내용가져오기
	public ArrayList<BoardDTO> getSearchList(PageUtil pageInfo, 
			                                 BoardDTO bDto) {
		int start = (pageInfo.getNowPage()-1)*pageInfo.getLineCount()+1;
		int end	  = start + pageInfo.getLineCount() - 1;
		
		//실행하게 되는 SQL문에서 파라미터는 BoardDTO로 받기로 했으므로
		bDto.setStart(start);
		bDto.setEnd(end);
		
		ArrayList<BoardDTO> list = bDAO.getSearchList(bDto);
		return list;
	}



	





	
}
















