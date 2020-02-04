package com.example.spring01.common.common;

public class Pager {
    private int pageBegin; // #{start}
    private int pageEnd; // #{end} 
    public int getPageBegin() {
    	// 시작번호 = (현재페이지-1)*페이지당 게시물수 +1
    	pageBegin = (getCurrentPageNo() - 1) * getPageSize()+1;
        return pageBegin;
    }
    public int getPageEnd() {
    	// 끝번호 = 시작번호+페이지당 게시물수 -1
    	pageEnd = getFirstRecordIndex()+getRecordCountPerPage()-1;
        return pageEnd;
    }
    
	/**
	 * Required Fields
	 * - 이 필드들은 페이징 계산을 위해 반드시 입력되어야 하는 필드 값들이다.  
	 * 
	 * currentPageNo : 현재 페이지 번호
	 * recordCountPerPage : 한 페이지당 게시되는 게시물 건 수
	 * pageSize : 페이지 리스트에 게시되는 페이지 건수,
	 * totalRecordCount : 전체 게시물 건 수. 
	 */
	
	private int currentPageNo;
	private int recordCountPerPage;
	private int pageSize;
	private int totalRecordCount;
	
	public int getRecordCountPerPage() {
		return recordCountPerPage;
	}
	
	public void setRecordCountPerPage(int recordCountPerPage) {
		this.recordCountPerPage = recordCountPerPage;
	}
	
	public int getPageSize() {
		return pageSize;
	}
	
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	public int getCurrentPageNo() {
		return currentPageNo;
	}
	
	public void setCurrentPageNo(int currentPageNo) {
		this.currentPageNo = currentPageNo;
	}
	
	public void setTotalRecordCount(int totalRecordCount) {
		this.totalRecordCount = totalRecordCount;
	}
	
	public int getTotalRecordCount() {
		return totalRecordCount;
	}
	
	/**
	 * Not Required Fields
	 * - 이 필드들은 Required Fields 값을 바탕으로 계산해서 정해지는 필드 값이다.
	 * 
	 * totalPageCount: 페이지 개수
	 * firstPageNoOnPageList : 페이지 리스트의 첫 페이지 번호
	 * lastPageNoOnPageList : 페이지 리스트의 마지막 페이지 번호
	 * firstRecordIndex : 페이징 SQL의 조건절에 사용되는 시작 rownum. 
	 * lastRecordIndex : 페이징 SQL의 조건절에 사용되는 마지막 rownum.
	 */
	
	private int totalPageCount;
	private int firstPageNoOnPageList;
	private int lastPageNoOnPageList;
	private int firstRecordIndex;
	private int lastRecordIndex;	
	
	public int getTotalPageCount() {
		totalPageCount = ((getTotalRecordCount()-1)/getRecordCountPerPage()) + 1;
		return totalPageCount;
	}
	
	public int getFirstPageNo(){
		return 1;
	}
	
	public int getLastPageNo(){
		return getTotalPageCount();		
	}
	
	public int getFirstPageNoOnPageList() {
		// *이전을 눌렀을 때 이동할 페이지 번호
		firstPageNoOnPageList = (getCurrentPageNo() == 1)? 1:((getCurrentPageNo()-1)/getPageSize())*getPageSize() + 1;
		return firstPageNoOnPageList;
	}
	
	public int getLastPageNoOnPageList() {		
		lastPageNoOnPageList = getFirstPageNoOnPageList() + getPageSize() - 1;		
		if(lastPageNoOnPageList > getTotalPageCount()){
			lastPageNoOnPageList = getTotalPageCount();
		}
		return lastPageNoOnPageList;
	}

	public int getFirstRecordIndex() {
		firstRecordIndex = (getCurrentPageNo() - 1) * getPageSize()+1;
		System.out.println("getRecordCountPerPage(): " + getRecordCountPerPage());
		System.out.println("firstRecordIndex: " + firstRecordIndex);
		return firstRecordIndex;
	}

	public int getLastRecordIndex() {
		lastRecordIndex = getCurrentPageNo() * getRecordCountPerPage();
		return lastRecordIndex;
	}
    
}
