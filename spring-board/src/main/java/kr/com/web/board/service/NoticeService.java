package kr.com.web.board.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import kr.com.web.board.mapper.NoticeMapper;
import kr.com.web.board.vo.NoticeFileVO;
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
		
		// 수정된 상태로 조회수 수집 가능
		NoticeVO.Notice notice = noticeMapper.getNotice(noId);
		
		return notice;
		
	}
	
	@Transactional
	public void addNotice(NoticeVO.NoticeReq newNotice) throws Exception {
		/* 트랜잭션 대상 항목 */
		// 1. 게시글 저장
		// 2. 파일 업로드
		// 3. 파일 정보 저장
		
		newNotice.setWriter("관리자");
		noticeMapper.addNotice(newNotice);
		
		// 파일 객체가 존재하고 전송된 파일이 있는 경우 파일 업로드 및 저장 처리
		if(newNotice.getFile() != null && !newNotice.getFile().isEmpty()) {
			NoticeFileVO fileVO = this.uploadFile(newNotice.getFile(), newNotice.getNoId());
			// 파일 업로드 성공 시
			if(fileVO != null) {
				noticeMapper.addNoticeFile(fileVO);
			}
		}
			
	}
	
	private NoticeFileVO uploadFile(MultipartFile file, int noId) {
	      
		NoticeFileVO fileVO = null;
	      
	      try {
	         if(file == null) {
	            throw new Exception("파일이 존재하지 않습니다.");
	         }
	         
	         String originName = file.getOriginalFilename();
	         int extIndex = originName.lastIndexOf(".");
	         //파일 확장자 얻기 
	         String ext = originName.substring(extIndex+1);
	         String uuIdName = UUID.randomUUID().toString().replaceAll("-",  "");
	         uuIdName = uuIdName.substring(0, 16);
	         
	         String newFileName = uuIdName + "." + ext;
	         
	         String filePath =  "c:/download/file/";
	         // panda.png를 포함할 부모 디렉토리가 없으면 생성시켜버림
	         String fullPath =  filePath +  newFileName; // c:/download/file/panda.png (실제 파일 위치 예시)
	         
	         //새로 만들 파일객체를 만든다.
	         File newFile = new File(fullPath);
	         
	         //경로여부 판단
	         if( !newFile.getParentFile().exists()) {
	            newFile.mkdirs();
	         }
	         
	         // 비어있는 파일 만들기
	         // 새로운 종이를 생성하는 것과 같은 원리임
	         newFile.createNewFile();
	         
	         //복사
	         file.transferTo(newFile);
	         
	         fileVO = new NoticeFileVO();
	         
	         fileVO.setNoId(noId);
	         fileVO.setFileStoredName(newFileName);
	         fileVO.setFileName(originName);
	         fileVO.setFilePath(filePath);
	      }
	      catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      return fileVO;
	      
	   }

}
