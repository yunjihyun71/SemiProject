package com.semi2.dto;

public class PageInfo {

	// 생성 시 입력할 값
	private int page; // 보여줄 페이지
	private int listCount; // 한 페이지에 보여줄 리스트 수
	private int pageCount; // 한 화면에 보여줄 페이지 수
	private int totalCount; // 총 게시물 수
	// 계산할 값
	private int startNum;  // 페이지에서 보여줄 첫번째 글번호
	private int endNum;  // 페이지에서 보여줄 마지막 글번호
	private int totalPage; // 총 페이지 수
	private int startPage; // 화면에 보여줄 시작 페이지
	private int endPage; // 화면에 보여줄 마지막 페이지
	private int nextPage;  // 다음 눌렀을 때 이동할 페이지
	private int prevPage;  // 이전 눌렀을 때 이동할 페이지
		
	// 생성자
	public PageInfo(int page, int listCount, int pageCount, int totalCount) {
		this.page = page;
		this.listCount = listCount;
		this.pageCount = pageCount;
		this.totalCount = totalCount;
		
		// 총 페이지수(나누어 떨어질 때 마지막 페이지가 빈페이지로 표시되는 것 방지)
		if(totalCount < listCount ) {
			totalPage = 1; 
		}
		totalPage = totalCount / listCount;
		if (totalCount % listCount > 0) {
			totalPage++;
		}

		// 총 페이지수보다 큰 페이지를 입력한 경우 처리
		if (totalPage < page) {
			page = totalPage;
		}

		// 화면에 보여줄 시작 페이지
		startPage = ((page - 1) / pageCount) * pageCount + 1;
		// 화면에 보여줄 마지막 페이지
		endPage = startPage + pageCount - 1;

		// 마지막 화면에서의 처리
		if (endPage > totalPage) {
			endPage = totalPage;
		}

		// 페이지에서 보여줄 시작 글번호
		startNum = (page - 1) * listCount + 1;
		// 페이지에서 보여줄 마지막 글번호
		endNum = page * listCount;
	
		// 다음 눌렀을 때 이동할 페이지
		nextPage = endPage + 1;
		// 이전 눌렀을 때 이동할 페이지
		prevPage = startPage - 1;

	}

	// Getters
	public int getPage() {
		return page;
	}

	public int getListCount() {
		return listCount;
	}

	public int getPageCount() {
		return pageCount;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public int getStartNum() {
		return startNum;
	}

	public int getEndNum() {
		return endNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public int getNextPage() {
		return nextPage;
	}

	public int getPrevPage() {
		return prevPage;
	}

}
