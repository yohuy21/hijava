package com.ourDo.util;

public class PageUtil {
	
	//꼭  필요한 정보
	private	int	nowPage;		//	보고싶은 페이지
	private	int	totalCount;		//	총 게시물수   100   39
	
	//필요한 정보
	private	int	lineCount;		//	한페이지당 보여주고 싶은 게시물의 개수 10
	private	int	pageGroup;		//	페이지블록  [1] [2] [3] [4] [5]
	
	//계산해서 얻어야 하는 정보
	private	int	totalPage;		//  총 페이지수   39/10  10  총게시물수totalCount/한페이지당 보여주고 싶은 게시물의 개수lineCount +1
	private	int	startPage;		//	시작페이지
	private	int	endPage;		//	종료페이지
	

	public PageUtil(int nowPage, int totalCount) {
		this(nowPage, totalCount, 3, 5);
	}
	
	public PageUtil(int nowPage, int totalCount, int lineCount, int pageGroup) {
		this.nowPage = nowPage;
		this.totalCount = totalCount;
		this.lineCount = lineCount;
		this.pageGroup = pageGroup;

		calcTotalPage();
		calcStartPage();
		calcEndPage();
	}

	//총 페이지수
	private	void calcTotalPage() {
		//총게시물수totalCount/한페이지당 보여주고 싶은 게시물의 개수lineCount +1
		totalPage = (totalCount % lineCount == 0) ? (totalCount / lineCount) : (totalCount / lineCount + 1);
	}
	
	//	시작페이지
	private	void calcStartPage() {
		//[][] [보고싶은페이지] [][]
		//[1][2] [3] [4][5]
		//   3   4   5  6   7
		startPage = nowPage - (pageGroup / 2);
		//                3   - (페이지블록5/2)  int 2
		//                2   -(5/2)  2-2
		
		if(startPage <= 0) {
			startPage = 1;
		}
	}
	
	//	종료페이지
	private	void calcEndPage() {
	
		endPage = startPage + (pageGroup - 1);
        //                  1+ (5-1) 1+4
		
		if(endPage >= totalPage) {
		   endPage =  totalPage;
		}
	}

	public int getNowPage() {
		return nowPage;
	}

	public void setNowPage(int nowPage) {
		this.nowPage = nowPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getLineCount() {
		return lineCount;
	}

	public void setLineCount(int lineCount) {
		this.lineCount = lineCount;
	}

	public int getPageGroup() {
		return pageGroup;
	}

	public void setPageGroup(int pageGroup) {
		this.pageGroup = pageGroup;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	@Override
	public String toString() {
		return "PageUtil [nowPage=" + nowPage + ", totalCount=" + totalCount + ", lineCount=" + lineCount
				+ ", pageGroup=" + pageGroup + ", totalPage=" + totalPage + ", startPage=" + startPage + ", endPage="
				+ endPage + "]";
	}

	
	
}





