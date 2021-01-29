package com.ourDo.dto;

//이 클래스는 member와 관련된 value를 다루기위한  클래스이다

public class MemberDTO {
	//변수
	private	String id;	//id
	private String pw;	//비번
	private	String name;//이름
	private String nick;//별명
	
	//생성자
	
	//함수
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getNick() {
		return nick;
	}
	public void setNick(String nick) {
		this.nick = nick;
	}
	
	@Override
	public String toString() {
		return "MemberDTO [id=" + id + ", pw=" + pw + ", name=" + name + ", nick=" + nick + "]";
	}
	
	
}
