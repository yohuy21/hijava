package com.ourDo.dto;

import java.util.Arrays;

public class TestDTO {

	//변수
	private String		mid;
	private String		mpw;
	private String		mname;
	private int			mage;
	private String[]	hobby;
	
	//생성자
	
	//함수
	@Override
	public String toString() {
		return "TestDTO [mid=" + mid + ", mpw=" + mpw + ", mname=" + mname + ", mage=" + mage + ", hobby="
				+ Arrays.toString(hobby) + "]";
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMpw() {
		return mpw;
	}
	public void setMpw(String mpw) {
		this.mpw = mpw;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public int getMage() {
		return mage;
	}
	public void setMage(int mage) {
		this.mage = mage;
	}
	public String[] getHobby() {
		return hobby;
	}
	public void setHobby(String[] hobby) {
		this.hobby = hobby;
	}

	
	
	
}
