package com.ourDo.dto;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

//이 클래스는 파일게시판과 관련한 DATA를 다루는데 필요한 기능을 제공하는 클래스
public class BoardDTO {

	//변수
	private	String	writer;		//글쓴이
	private	String	title;		//제목
	private	String	content;	//내용
	private	String	pw;			//비밀번호
	//private	MultipartFile	files;	//첨부파일  동일name으로 업로드되는 파일이 1개인 경우
	private	MultipartFile[]	files;	//첨부파일  동일name으로 업로드되는 파일이 여러개인 경우
	
	private int		no;
	private String	id;				//유저id
	private String	nick;			//유저nickname
	
	private int		oriBNo;			//원글번호
	private	String	name;			//파일명
	private	String	saveName;		//저장된파일명
	private	String	path;			//경로 
	private long	fsize;			//크기
	
	private Date	wdate;			//작성일
	private	int		hit;			//조회수
	private int		fcount;			//첨부파일수
	
	private int		rno;
	
	private int		start;			//시작글번호
	private int		end;
	
	private int		nowPage;
	private String 	target;			//검색대상
	private String 	word; 			//검색어
		
	public String getTarget() {
		return target;
	}
	public void setTarget(String target) {
		this.target = target;
	}
	public String getWord() {
		return word;
	}
	public void setWord(String word) {
		this.word = word;
	}
	public String getWriter() {
		return writer;
	}
	public void setWriter(String writer) {
		this.writer = writer;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getContent2() {
		String result = null;
		if(content!=null && content.length()!=0) {
			result = content.replaceAll("\r", "<br/>");
		}
		return result;
	}
	
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public MultipartFile[] getFiles() {
		return files;
	}
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	public int getOriBNo() {
		return oriBNo;
	}
	public void setOriBNo(int oriBNo) {
		this.oriBNo = oriBNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSaveName() {
		return saveName;
	}
	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public long getFsize() {
		return fsize;
	}
	public void setFsize(long fsize) {
		this.fsize = fsize;
	}
	
	public String getFsize2() {
		DecimalFormat fmt = new DecimalFormat("###,###,###,###");
		return fmt.format(fsize);
	}
	
	public Date getWdate() {
		return wdate;
	}
	public void setWdate(Date wdate) {
		this.wdate = wdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	public int getFcount() {
		return fcount;
	}
	public void setFcount(int fcount) {
		this.fcount = fcount;
	}
	public int getRno() {
		return rno;
	}
	public void setRno(int rno) {
		this.rno = rno;
	}
	public int getStart() {
		return start;
	}
	public void setStart(int start) {
		this.start = start;
	}
	public int getEnd() {
		return end;
	}
	public void setEnd(int end) {
		this.end = end;
	}
	public int getNowPage() {
		return nowPage;
	}
	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}
	
	
	@Override
	public String toString() {
		return "BoardDTO [writer=" + writer + ", title=" + title + ", content=" + content + ", pw=" + pw + ", files="
				+ Arrays.toString(files) + ", no=" + no + ", id=" + id + ", nick=" + nick + ", oriBNo=" + oriBNo
				+ ", name=" + name + ", saveName=" + saveName + ", path=" + path + ", fsize=" + fsize + ", wdate="
				+ wdate + ", hit=" + hit + ", fcount=" + fcount + ", rno=" + rno + ", start=" + start + ", end=" + end
				+ ", nowPage=" + nowPage + ", target=" + target + ", word=" + word + "]";
	}
	
	
}







