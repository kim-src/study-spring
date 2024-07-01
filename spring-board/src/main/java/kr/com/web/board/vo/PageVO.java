package kr.com.web.board.vo;


public class PageVO {
	
	private int totalPage; // 전체 페이지 개수
	private int nowPage; // 현재 페이지 O부터
	private int blockPerPageCnt; // 한 블럭에 표시할 페이지 수
	private int pagePerRows; // 한 페이지에 보여줄 게시글 수
	private int totalRows; // 전체 데이터 수
	private int nowBlock; // 현재 블럭 위치
	private int totalBlock; // 전체 블럭 개수
	
	// 페이징을 처리할 기본 데이터 입력 메서드
	public void setData(int nowPage, int totalRows) {
		this.nowPage = nowPage;
		this.totalRows = totalRows;
		this.pagePerRows = 10; // 한 페이지에 10개씩 보여지도록 설저
		this.blockPerPageCnt = 10; // 한 블럭에 보여질 개수
	}
	
	// SQL에서 사용할 데이터 시작 위치
	// 페이지 설정 로직
	public int getStart() {
		return this.nowPage * this.pagePerRows;
	}
	
	// SQL에서 사용할 데이터 개수
	public int getEnd() {
		return this.pagePerRows;
	}
	
	// 페이지 전체 개수
	public int getTotalPage() {
		double val = (double)this.totalRows / this.pagePerRows;
		// 올림하여 페이지 추가
		int totalPage = (int)(Math.ceil(val));
		return totalPage;
	}
	
	// 현재 블럭 위치
	public void getNowBlock() {
		double val = (double)this.nowPage / this.blockPerPageCnt;
		this.nowBlock = (int)(Math.floor(val));
	}
	
	// 전체 블럭 개수
	public void getTotalBlock() {
		double val = (double)this.getTotalPage() / this.blockPerPageCnt;
		this.totalBlock = (int)(Math.ceil(val));
	}
	
	// 페이징 HTML 구성
	public String pageHTML() {
		StringBuilder sb = new StringBuilder();
		
		this.totalPage = this.getTotalPage();
		this.getNowBlock();
		this.getTotalBlock();
		
		int pageNum = 0;
		String isDisabled = "disabled";
		String isActive = "";
		
		if(this.nowPage > 10) {
			isDisabled = "";
		}
		
		// 첫 페이지일 때이기 때문에 처음으로를 안눌리게 하기 위해 isDisabled
		sb.append("<li class='page-item " + isDisabled +"'>");
		// 링크를 사용하지 않는다는 의미의 javascript:void(0)
		sb.append("  <a class='page-link' href='javascript:void(0)' onclick='movePage(0);'>");
		sb.append("처음</a>");
		sb.append("</li>");
		
		// 블럭 넘어가기
		if(this.nowBlock > 0) {
			// 블럭의 첫 페이지에서 -1을 하여 직전 블럭으로 이동
			pageNum = (this.nowBlock * this.blockPerPageCnt) -1;
			sb.append("<li class='page-item'>");
			String str = "<a class='page-link' href='javascript:void(0)' onclick='movePage(%s);'>";
			sb.append(String.format(str, pageNum));
			sb.append("이전</a>");
			sb.append("</li>");
		}
		
		// 페이지 번호 표시
		for(int i = 0; i < this.blockPerPageCnt; i++) {
			isActive = "";
			pageNum = (this.nowBlock * this.blockPerPageCnt) + i;
			
			if(pageNum == this.nowPage) {
				isActive = "active";
			}
			
			sb.append("<li class='page-item " + isActive +"'>");
			String str = "<a class='page-link' href='javascript:void(0)' onclick='movePage(%s);'>";
			sb.append(String.format(str, pageNum));
			sb.append((pageNum + 1) + "</a>");
			sb.append("</li>");
			
			// 만일 페이지가 1 블럭(10개)보다 작을 경우
			if(this.totalPage == (pageNum + 1)) {
				break;
			}
		}
		
		// 다음 페이지
		if(this.nowBlock + 1 < this.totalBlock) {
			pageNum = (this.nowBlock + 1) * this.blockPerPageCnt;
			sb.append("<li class='page-item'>");
			String str = "<a class='page-link' href='javascript:void(0)' onclick='movePage(%s);'>";
			sb.append(String.format(str, pageNum));
			sb.append("다음</a>");
			sb.append("</li>");
		}
		
		isDisabled = "";
		
		// 현재 위치가 마지막이면 마지막 페이지는 안눌리게
		if(this.totalPage ==(this.nowPage + 1)) {
			isDisabled = "  disabled";
		}
		
		pageNum = this.totalPage - 1;
		
		// 마지막 페이지
		sb.append("<li class='page-item " + isDisabled +"'>");
		sb.append("<li class='page-item'>");
		String str = "<a class='page-link' href='javascript:void(0)' onclick='movePage(%s);'>";
		sb.append(String.format(str, pageNum));
		sb.append("마지막</a>");
		sb.append("</li>");
		
		return sb.toString();
	}

}
