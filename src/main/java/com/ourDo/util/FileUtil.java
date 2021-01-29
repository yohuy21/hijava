package com.ourDo.util;

import java.io.File;

//이 클래스는 첨부파일을 업로드할 때 파일의 이름변경등 과 같은 여러 기능을 제공
public class FileUtil {

	//폴더가 존재하지 않으면 폴더생성
	public  static void makeFoler(String path) {
		File file = new File(path);
		file.mkdirs();
	}
	
	//이름변경
	public static String rename(String path, String oriName) {
		System.out.println("path="+path);
		System.out.println("oriName="+oriName);
		makeFoler(path);
		
		//첫 번째 파라미터 path : 어느 폴더에
		//2 번째 파라미터 oriName : 어떤 이름으로 
		//인데  이름을 변경해주라..
		
		String tempName = oriName;
		int cnt = 0; //이름옆에 붙여줄 번호를 저장하기 위한 변수 선언
		File file = new File(path,tempName);
	
		//파일이 존재하니?
		while( file.exists()  ) {
			//  ex.png
			//  ex_1.png 
			//  .의 위치   : 문자열.lastIndexOf("찾고자하는 문자");
			int endIndex = oriName.lastIndexOf(".");  //2
			
			//"abc".substring(beginIndex, endIndex)  0,2-1  0,1
			//substring(beginIndex, endIndex): beginIndex and extends to the character at index endIndex - 1. 
			String fileN = oriName.substring(0, endIndex);  //ex
			String extN =  oriName.substring(endIndex+1);  //png
			
			cnt=cnt+1; //번호증가
			
			//분리된 파일명 뒤에  "_" 뒤에 번호를 연결
			fileN = fileN+"_"+cnt;  //ex_1
			
			//변경된 이름뒤에 "."뒤에  확장자를 연결  ex_1png
			tempName = fileN+"."+extN;   //ex_1.png
			
			file = new File(path,tempName);
		}
		
		return tempName;
	}
}














