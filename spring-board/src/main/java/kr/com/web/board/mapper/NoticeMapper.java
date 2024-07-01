package kr.com.web.board.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import kr.com.web.board.vo.NoticeVO;

@Mapper
public interface NoticeMapper {
	
	// 게시글 전체 개수 산정
	int getNoticeTotal(Map<String, Object> param) throws Exception;
	// 게시글 리스트 호출
	List<NoticeVO.NoticeList> getNoticeList(Map<String, Object> param) throws Exception;
	// 게시글 정보 가져오기
	NoticeVO.Notice getNotice(@Param("noId") int noId) throws Exception;
	// 조회수 증가
	void updateReadCnt(@Param("noId") int noId) throws Exception;
}
