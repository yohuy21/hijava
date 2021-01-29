package com.ourDo.controller;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.ourDo.dto.BoardDTO;
import com.ourDo.service.BoardService;
import com.ourDo.util.FileUtil;
import com.ourDo.util.PageUtil;

//이 클래스는 파일게시판처리를 위한 컨트롤러 클래스이다

@Controller
@RequestMapping("/board")
public class BoardController {

	//변수
	//private static final Logger logger = LoggerFactory.getLogger(BoardController.class);
	@Autowired
	private BoardService boardS;
	
	//생성자
	
	//함수+요청함수
	//검색
	@RequestMapping("/searchInfo")
	public ModelAndView searchInfo(
			@ModelAttribute BoardDTO bDto,
			ModelAndView mv,
			@RequestParam(value="nowPage", required=false, defaultValue="1")  int  nowPage) {
		//1.파라미터받기  target=검색대상   word = 검색어
		System.out.println("target="+bDto.getTarget());
		System.out.println("word="+bDto.getWord());
		
		//2.비즈니스로직->Service->DAO->DB
		//2-1. 페이징처리
		//2-2. 검색내용가져오기
		PageUtil pageInfo = boardS.getSearchPageInfo(nowPage, bDto);
		ArrayList<BoardDTO> list = boardS.getSearchList(pageInfo,bDto);
		/*참고 : 목록보기 요청함수
		 * PageUtil pageInfo = boardS.getPageInfo(nowPage);
		   ArrayList<BoardDTO> list = boardS.getList(pageInfo);*/
		
		//3.Model & View
		mv.setViewName("board/boardSearch");
		mv.addObject("target",bDto.getTarget());
		mv.addObject("word",bDto.getWord());
		mv.addObject("LIST",list);        //게시물정보
		mv.addObject("PAGEINFO",pageInfo);//페이징정보
		return mv;
	}
	
	//다운로드
	@RequestMapping("/downLoadFile")
	public ModelAndView  downLoadFile(@RequestParam("f_no") int fno) {
		//1.파라미터받기    f_no=첨부파일번호   
		//2.비즈니스로직->Service->DAO->DB
		BoardDTO bDto=boardS.getDownLoadFile(fno);
		
		//3.Model
		//4.View   다운로드용 뷰 
		File	file = new File(bDto.getPath(), bDto.getName());
		
		//new ModelAndView(String viewName, String modelName, Object modelObject)
		ModelAndView	mv = new ModelAndView("download", "downloadFile", file);
		return mv;
	}
	
	
	//삭제
	@RequestMapping("/del")
	public ModelAndView  delBoard(@ModelAttribute BoardDTO bDto,
			ModelAndView mv, RedirectView rv) {
		//1.파라미터받기   oriBNo=글번호  pw=비번
		
		//2.비즈니스로직->Service->DAO->DB
		boardS.delBoard(bDto);
		
		//3.Model
		//4.View   목록보기   http://localhost:9000/app/board/list.do
		rv.setUrl("../board/list.do");
		mv.setView(rv);
		return mv;
	}
	
	
	//수정처리
	@RequestMapping("/modifyProc")
	public ModelAndView  modifyProc(BoardDTO bDto,
			ModelAndView mv, RedirectView rv) {
		//1.파라미터받기  oriBNo=글번호  nowPage=보고싶은페이지  //릴레이용
		System.out.println("글번호="+bDto.getOriBNo());
		System.out.println("보고싶은페이지="+bDto.getNowPage());
		
		//2.비즈니스로직->Service->DAO->DB
		//유저가 입력한  제목,내용,비번 -> board에서 update
		//첨부					-> fileInfo에서 작업
		//첨부파일이 있는지 확인후 
		//-> 첨부파일이 있으면 ..첨부파일정보가져온다-> db보낼정보세팅+파일복사
		boolean isUpload = false;
		
		//첨부파일이 몇개인지 모르니까
		for(int i=0; i<bDto.getFiles().length ;i++){ 
			String tempName =bDto.getFiles()[i].getOriginalFilename();
			if( tempName!=null && tempName.length()!=0 ) {
				isUpload = true;
				break;
			}
		}
		
		String path = "D:\\upload";
		ArrayList fileList= new ArrayList();
		
		if(isUpload==true) {
			//첨부파일의 개수만큼 반복해서
			//첨부파일의 개수는 BoardDTO에서 가져온다 : bDto.getFiles().length
			for(int i=0; i<bDto.getFiles().length ;i++) {
				//#{oriBNo}, #{name}, #{saveName}, #{path}, #{fsize}
				String oriName = bDto.getFiles()[i].getOriginalFilename(); //#{name}예정
				
				if(oriName == null || oriName.length() == 0) {
					continue;
				}
				
				String saveName =  FileUtil.rename(path, oriName); //#{saveName}예정
				
				
				//1.복사 대상 파일을  File클래스로 생성
				File file = new File(path, saveName);
				
				//2.파일복사 함수 : transferTo()
				try {
					bDto.getFiles()[i].transferTo(file);
				}catch(Exception e) {
					System.out.println("파일 복사 에러="+e);
				}
				
				//업로드된 하나의 파일에 대한 정보를 Map으로 넘긴다 
				HashMap map = new HashMap();
				map.put("name", oriName); 
				map.put("saveName", saveName); 
				//map.put("path", path); 
				map.put("size", file.length()); 
				
				fileList.add(map);
			}//end for
		}//end if(isUpload==true)
		
		boardS.updateBoard(bDto);//board테이블에  update
		
		//첨부파일이 존재하면
		// db에서 삭제할파일정보-경로에 파일 삭제 ->복사,db에 파일정보입력 
		if(isUpload==true) {
			
			//db에서 삭제할파일 정보가져온다
			//ArrayList         list= (db에서 삭제할)파일정보를찾자
			ArrayList<BoardDTO> list= boardS.getBoardFile(bDto.getOriBNo());
			
			//업로드된 파일을 삭제
			if( list!=null  && list.size()!=0  ) {//첨부파일이 있으면
				for(int i=0; i<list.size() ;i++) {//첨부파일의 개수만큼 반복해서 가져온다 
				  //BoardDTO tempDto = (BoardDTO)list.get(i);
					BoardDTO tempDto = list.get(i);
					File tempFile = new File(path, tempDto.getSaveName());
					
					//파일삭제
					tempFile.delete();
				}//for
			}//if
			
			//수정처리-기존의 첨부된 파일들을 db에서도 삭제
			boardS.deleteFileInfo(bDto.getOriBNo());
			
			//db에 등록
			for(int i=0; i<fileList.size() ;i++) {
				BoardDTO dto = new BoardDTO();
				dto.setOriBNo(bDto.getOriBNo());
				dto.setPath(path); 
				
				//수정업로드되는 하나의 파일에 대한 정보를 BoardDTO으로 넘긴다
				//위에서  수정용업로드되는 파일에 대한 정보를 map으로 넣어놓았다
				HashMap<String,Object> tempMap = (HashMap)fileList.get(i);
				/*map.put("name", oriName); 
				  map.put("saveName", saveName); 
				  map.put("size", file.length()); */ 
				dto.setName( (String)tempMap.get("name")  );
				dto.setSaveName( (String)tempMap.get("saveName") );
				dto.setFsize( (Long)tempMap.get("size")  );
				
				boardS.insertFileInfo(dto);
				
			}//for
				
		}//if(isUpload==true)
		
		
		//3.Model
		mv.addObject("oriNo",bDto.getOriBNo());
		mv.addObject("nowPage", bDto.getNowPage());
		
		//4.View   RedirectView  상세보기 http://localhost:9000/app/board/detail.do
		rv.setUrl("../board/detail.do");
		mv.setView(rv);
		return mv;
	}
	
	
	//수정폼 보기
	@RequestMapping("/modifyFrm")
	public ModelAndView  modifyFrm(@RequestParam(value="nowPage") int nowPage,
			@RequestParam(value="oriNo") int oriNo,
			ModelAndView mv) {
		//1.파라미터받기  oriNo=글번호  nowPage=보고싶은페이지  //릴레이용
		
		//2.비즈니스로직->Service->DAO->DB
		//db에서 상세정보를 가져와 폼에 뿌려줘야 한다
		BoardDTO  bDto = boardS.boardDetail(oriNo);
		
		//3.Model
		mv.addObject("BDTO",bDto);		//상세정보
		mv.addObject("nowPage",nowPage);//보고싶은페이지(릴레이용)
		
		//4.View         /WEB-INF/views/board/modifyFrm.jsp
		mv.setViewName("board/modifyFrm");
		return mv;
	}
	
	
	
	//상세조회
	@RequestMapping("/detail")
	public String boardDetail(@RequestParam(value="nowPage") int nowPage,
			@RequestParam(value="oriNo") int oriNo,
			HttpServletRequest request) {
		//1.파라미터받기  oriNo=글번호      nowPage=보고싶은페이지
		System.out.println("boardDetail() oriNo="+oriNo);
		System.out.println("boardDetail() nowPage="+nowPage);
		
		//2.비즈니스로직->Service->DAO->DB
		//2-1.게시판정보
		BoardDTO  bDto = boardS.boardDetail(oriNo);
		
		//2-2.첨부파일정보
		ArrayList<BoardDTO> fileList = boardS.getBoardFile(oriNo);
			
		//3.Model
		request.setAttribute("nowPage",nowPage); //보고싶은페이지
		request.setAttribute("BDTO",bDto); //게시판정보
		request.setAttribute("FILELIST", fileList);//첨부파일정보
		
		//4.View         /WEB-INF/views/board/boardDetail.jsp
		return "board/boardDetail";
	}
	
	
	
	//조회수증가
	@RequestMapping("/hitUp")
	public ModelAndView hitUp(HttpServletRequest request,ModelAndView mv) {
		//1.파라미터받기  oriNo=글번호      nowPage=보고싶은페이지(선택)//릴레이용
		int oriNo = Integer.parseInt(request.getParameter("oriNo"));//글번호 
		String nowPage=request.getParameter("nowPage");//보고싶은페이지//릴레이용
		
		//2.비즈니스로직
		boardS.hitUp(oriNo);
		
		//3.Model & View RedirectView  상세보기 /board/detail.do
		RedirectView rv = new RedirectView("../board/detail.do");
		// addStaticAttribute("키값", Obejct data); RedirectView로  모델을 넘길때 사용
		rv.addStaticAttribute("oriNo", oriNo);    //글번호
		rv.addStaticAttribute("nowPage", nowPage);//보고싶은페이지(릴레이용)
		mv.setView(rv);
		return mv;
	}

	
	//글쓰기처리 
	//요청내용  http://localhost:9000/app/board/writeProc.do
	@RequestMapping("/writeProc")
	public ModelAndView writeProc(BoardDTO boardDto,HttpSession session) {
		//logger.info("글쓰기폼",boardDto);
		System.out.println(boardDto);
		
		//1.파라미터받기
		/* writer(글쓴이)
			title=제목
			content=내용
			pw=비밀번호
			files=첨부파일(첨부파일의 수는 최대 3개로 제한)*/
		
		//강제로 파일을 복사
		//1.복사 대상 파일을  File클래스로 생성 ..어디에 무슨이름으로 파일을 만든다
		String path = "D:\\upload";
		
		ArrayList list= new ArrayList();
		
		//첨부파일의 개수만큼 반복해서
		//첨부파일의 개수는 BoardDTO에서 가져온다 : boardDto.getFiles().length
		for(int i=0; i<boardDto.getFiles().length ;i++) {
			//#{oriBNo}, #{name}, #{saveName}, #{path}, #{size}
			String oriName = boardDto.getFiles()[i].getOriginalFilename(); //#{name}예정
			
			if(oriName == null || oriName.length() == 0) {
				continue;
			}
			
			String saveName =  FileUtil.rename(path, oriName); //#{saveName}예정
			
			
			//1.복사 대상 파일을  File클래스로 생성
			File file = new File(path, saveName);
			
			//2.파일복사 함수 : transferTo()
			try {
				boardDto.getFiles()[i].transferTo(file);
			}catch(Exception e) {
				System.out.println("파일 복사 에러="+e);
			}
			
			//업로드된 하나의 파일에 대한 정보를 Map으로 넘긴다 
			HashMap map = new HashMap();
			map.put("name", oriName); 
			map.put("saveName", saveName); 
			map.put("path", path); 
			map.put("size", file.length()); 
			
			list.add(map);
		}//end for
		
		
		//2.비즈니스로직 수행(<->Service<->DAO<->DB)
		boardS.insertBoard(boardDto, session, list);
		
		//3.Model
		//4.View    RedirectView 목록보기  http://localhost:9000/app/board/list.do
		ModelAndView mv = new ModelAndView();
		RedirectView view = new RedirectView("../board/list.do");
		mv.setView(view);
		return mv;
	}
	
	//글쓰기폼 보기
	//요청내용  http://localhost:9000/app/board/writeFrm.do
	@RequestMapping("/writeFrm")
	public void writeFrm() {
	//	logger.info("글쓰기폼");
		//1.파라미터받기
		//2.비즈니스로직 수행(<->Service<->DAO<->DB)
		//3.Model
		//4.View    /WEB-INF/views/board/writeFrm.jsp
	}
	
	//글목록조회  요청함수
	//요청내용  http://localhost:9000/app/board/list.do
	@RequestMapping("/list")
	public  ModelAndView  list(@RequestParam(value="nowPage", required=false, defaultValue="1")  int  nowPage,
			ModelAndView mv) {
		System.out.println("글목록조회 list()nowPage="+nowPage);
		
		//1.파라미터받기  nowPage=보고싶은페이지(선택)
		PageUtil pageInfo = boardS.getPageInfo(nowPage);
		System.out.println("글목록조회 list()pageInfo="+pageInfo);
				
		//2.비즈니스로직 수행(<->Service<->DAO<->DB)
		ArrayList<BoardDTO> list = boardS.getList(pageInfo);//게시물정보
	
		
		//3.Model
		mv.addObject("LIST",list);//게시물정보
		mv.addObject("PAGEINFO",pageInfo);//페이징정보
		
		//4.View    /WEB-INF/views/board/list.jsp
		mv.setViewName("board/list");
		return mv;
	}
}










