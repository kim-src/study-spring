package kr.com.web.board.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;

import kr.com.web.board.mapper.NoticeMapper;
import kr.com.web.board.vo.NoticeVO;
import kr.com.web.board.vo.NoticeVO.NoticeResp;
import kr.com.web.board.vo.PageVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoticeService {
	
	// DI
	private final NoticeMapper noticeMapper;
	
	public NoticeVO.NoticeResp getNoticeList(int nowPage) throws Exception {
		
		Map<String, Object> param = new HashMap<>();
		
		// 전체 리스트 수
		int totalCnt = noticeMapper.getNoticeTotal(param);
		// 페이지 계산을 위한 객체 호출
		PageVO page = new PageVO();
		page.setData(nowPage, totalCnt);
		
		// 리스트 객체
		List<NoticeVO.NoticeList> dataList = new ArrayList<>();
		
		// 전체 개수가 0이라면 select 할 필요 없음
		if(totalCnt > 0) {
			param.put("start", page.getStart());
			param.put("end", page.getEnd());
			
			dataList = noticeMapper.getNoticeList(param);
		}
		
		NoticeVO.NoticeResp resp = new NoticeResp();
		resp.setTotalCnt(totalCnt);
		resp.setNoticeList(dataList);
		resp.setNowPage(nowPage);
		resp.setPageHtml(page.pageHTML());
		
		return resp;
		
	}
	
	public NoticeVO.Notice getDetail(
			int noId, String cookieValue, HttpServletResponse resp) throws Exception {
		
		NoticeVO.Notice notice = noticeMapper.getNotice(noId);
		
		String readId = "read_" + noId;
		
		// 게시글 처음 조회했을 때
		if(!cookieValue.contains(readId)) {
			// 게시글 조회수 증가
			noticeMapper.updateReadCnt(noId);
			
			cookieValue += "_" + readId;
			// 쿠키 객체 선언
			Cookie cookie = new Cookie("notice", cookieValue);
			cookie.setMaxAge(60*60*3); // 3 hour
			resp.addCookie(cookie); // 쿠키 저장
		}
		
		return notice;
		
	}

}
