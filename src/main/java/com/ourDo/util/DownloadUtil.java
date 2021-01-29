package com.ourDo.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

/*이 클래스는  다운로드용 뷰의 역할을 하는 사용자정의 뷰클래스이다
1.  AbstractView클래스를 상속받는다
2.  renderMergedOutputModel()함수를 오버라디딩한다
*/

public class DownloadUtil extends AbstractView {

	public DownloadUtil() {
		this.setContentType("application/download;charset=UTF-8");
	}
	
	@Override
	protected void renderMergedOutputModel(
			Map<String, Object> model, 
			HttpServletRequest  request,
			HttpServletResponse response) throws Exception {
		//첫번째 파라미터인 model: 다운로드 파일에 대한 정보를 기억할 변수
		// downloadFile이라는 키값으로  
		//  File클래스로 제공받기로 약속
		File file = (File)model.get("downloadFile");
		
		response.setContentType(this.getContentType());
		response.setContentLength((int)file.length());
		
		//파일이름에 한글이 있는 경우
		String encoding = new String(file.getName().getBytes("UTF-8"),"8859_1");
		response.setHeader("Content-Disposition", 
				           "attachment;filename="+encoding);
		
		//응답방식을  스트림방식으로 처리
		FileInputStream fin = null;
		OutputStream out = null;
		
		try {
			fin = new FileInputStream(file);
			out = response.getOutputStream();
			
			//기존 java에서 파일의 내용을 반복문을 이용해서 read했던 부분을
			//스프링에서는 FileCopyUtils을 이용해서 처리할 수 있다
			FileCopyUtils.copy(fin, out);
		
		}catch(Exception e) {
			System.out.println("다운로드처리 에러="+e);
		}finally {
			out.flush();
			try {
				fin.close();
				out.close();
			}catch(Exception e) {
				e.printStackTrace();
			}
		}
		
		
	}

}





