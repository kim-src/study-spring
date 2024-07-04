package kr.com.web.board.controller;

import java.io.File;
import java.net.URLEncoder;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import kr.com.web.board.service.NoticeService;
import kr.com.web.board.vo.NoticeFileVO;
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
	
	@GetMapping("/write.do")
	public ModelAndView writeView(@RequestParam(name="nowPage") int nowPage) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardWrite");
		// key-value 쌍으로 데이터 추가
		view.addObject("nowPage", nowPage);
		return view;
	}
	
	@PostMapping("/add.do")
	// writeNotice의 파라미터에 @ModelAttribute 생략 가능
	public ModelAndView writeNotice(NoticeVO.NoticeReq newNotice) {
		ModelAndView view = new ModelAndView();
		// 성공하면 list 페이지로 이동
		view.setViewName("redirect:/notice/list.do");
		
		try {
			noticeService.addNotice(newNotice);
		}
		// 실패하면 error 페이지로 이동
		catch(Exception e) {
			e.printStackTrace();
			view.setViewName("error/writeError");
		}
		
		return view;
	}
	
	@GetMapping("/update/view.do")
	public ModelAndView getUpdateView(@RequestParam(name = "nowPage") int nowPage,
									  @RequestParam(name = "noId") int noId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("board/boardUpdate");
		
		
		try {
			view.addObject("nowPage", nowPage);
			NoticeVO.Notice notice = noticeService.getNoticeInfo(noId);
			view.addObject("notice", notice);
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		
		return view;
	}
	
	@GetMapping("/delete.do")
	public ModelAndView deleteNotice(@RequestParam(name = "nowPage") int nowPage,
									 @RequestParam(name = "noId") int noId) {
		ModelAndView view = new ModelAndView();
		view.setViewName("redirect:/notice/list.do?nowPage=" + nowPage);		
		try {
			noticeService.deleteNotice(noId);
		}
		catch(Exception e) {
			e.printStackTrace();			
		}
		
		return view;
	}
	
	@PostMapping("/update.do")
	// writeNotice의 파라미터에 @ModelAttribute 생략 가능
	public ModelAndView updateNotice(NoticeVO.NoticeReq newNotice) {
		ModelAndView view = new ModelAndView();
		// 성공하면 list 페이지로 이동
		view.setViewName("redirect:/notice/list.do");
		
		try {
			noticeService.updateNotice(newNotice);
		}
		// 실패하면 error 페이지로 이동
		catch(Exception e) {
			e.printStackTrace();
			view.setViewName("error/writeError");
		}
		
		return view;
	}
	
	@GetMapping("/file/down.do")
	/* public부터 Service로 변경 가능 */
	public ResponseEntity<UrlResource>  downBoardFile(@RequestParam("fileId") int fileId) {
	      String originFileName = null;
	       String storedFileName = null;
	       //파일다운로드 시 필요 
	       HttpHeaders header = new HttpHeaders();
	       UrlResource  res = null;
	      
	       try {
	          
	          NoticeFileVO fileVO = noticeService.getFileInfo(fileId);
	          
	          if(fileVO != null) {
	             
	             originFileName = fileVO.getFileName();
	             storedFileName  = fileVO.getFileStoredName();
	             
	             String fullPath = fileVO.getFilePath() + storedFileName;
	             
	             File file = new File(fullPath);
	             
	             if(!file.exists()) {
	                throw new Exception("파일이 존재하지 않습니다.");
	             }
	             //파일은 전송할 때 필요한 파일의  확장 타입 
	             String mimeType = Files.probeContentType(Paths.get(file.getAbsolutePath()));
	             
	             if(mimeType == null) {
	                // 일반 이진파일 타입 
	                mimeType = "octet-stream";
	             }
	             //전송할 파일을 객체 담는다 
	             res =  new UrlResource(file.toURI());
	             /* res 코드까지 Service로 변경 가능 */
	             
	             
	             //한글깨짐 방지 정책
	             //+ 기호는 경로에서 오류발생 때문에 encode 후 + 기호를 바이트 코드인 %20 으로 변경 
	             String encodedName = URLEncoder.encode(originFileName, "UTF-8").replace("+", "%20");
	             //http 헤더 세팅
	             //파일 다운로드 옵션 과 다운로드 할 때의 파일 이름 지정 
	             header.set("Content-Disposition",   "attachment;filename="  + encodedName +";filename*=UTF-8''" + encodedName);
	            // 캐쉬 사용 안함 
	             header.setCacheControl("no-cache"); 
	             //다운로드할 파일의 타입 
	               header.setContentType(MediaType.parseMediaType(mimeType));
	             
	          }else {
	             throw new Exception("SQL 오류 .");
	          }
	          
	       }catch (Exception e) {
	         e.printStackTrace();
	      }
	       
	       // ResponseEntity = HTTP의 성공 또는 실패에 대한 상태값을 알기 위한 객체
	       return new ResponseEntity<UrlResource>(res, header, HttpStatus.OK);
	   }

}
