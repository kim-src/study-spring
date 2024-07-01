package kr.com.web.board.controller;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.com.web.board.service.NoticeService;
import kr.com.web.board.vo.NoticeVO;
import kr.com.web.board.vo.NoticeVO.Notice;
import kr.com.web.board.vo.NoticeVO.NoticeResp;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/notice")
public class NoticeController {
	
	// DI
	private final NoticeService noticeService;
	
	@GetMapping("/list.do")
	public ModelAndView getList(@RequestParam(name="nowPage",
											  defaultValue="0") int nowPage) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardList");
		
		// 예외 발생 되더라도 반환할 것
		// 클라이언트 사이드에 전송할 데이터 객체 선언
		NoticeVO.NoticeResp resp = new NoticeResp();
		
		try {
			resp = noticeService.getNoticeList(nowPage);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			// 클라이언트에 전송할 데이터 할당
			view.addObject("data", resp);
		}
		
		return view;
	}
	
	@GetMapping("/detail.do")
	public ModelAndView getDetail(@RequestParam(name = "nowPage") int nowPage,
								  @RequestParam(name = "noId") int noId,
								  @CookieValue(name = "notice", defaultValue = "") String cookieValue,
								  HttpServletResponse resp) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardDetail");
		view.addObject("nowPage", nowPage);
		NoticeVO.Notice notice = new Notice();
		
		try {
			notice = noticeService.getDetail(noId, cookieValue, resp);
		}
		catch(Exception e) {
			e.printStackTrace();
		}
		finally {
			view.addObject("notice", notice);
		}
		
		return view;
	}

}
