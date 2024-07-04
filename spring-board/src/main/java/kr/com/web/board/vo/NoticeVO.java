package kr.com.web.board.vo;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

public class NoticeVO {
	
	@Data
	public static class NoticeResp {
		
		private int totalCnt;
		private List<NoticeVO.NoticeList> noticeList;
		private String pageHtml;
		private int nowPage;
		
	}
	
	@Data
	// Req = Request
	public static class NoticeReq {

		private int noId;
		private String title;
		private String contents;
		private String writer;
		// MultipartFile = binary 파일을 받아줄 수 있는 객체
		private MultipartFile file;
		
	}
	
	@Data
	public static class NoticeList {
	
		private int noId;
		private String title;
		private String writer;
		private int readCnt;
		private String createDate;
		private String updateDate;
		
	}
	
	@Data
	public static class Notice {
	
		private int noId;
		private String title;
		private String contents;
		private String writer;
		private int readCnt;
		private NoticeFileVO file;
		
	}
	
}
