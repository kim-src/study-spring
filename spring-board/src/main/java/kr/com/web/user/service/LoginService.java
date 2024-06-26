package kr.com.web.user.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import kr.com.web.user.mapper.LoginMapper;
import kr.com.web.user.vo.UserVO;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LoginService {

	private final LoginMapper loginMapper;
		
	public UserVO loginUser(String memId, String memPassword) throws Exception {
			
		// Map for return
		Map<String, Object> param  = new HashMap<>();
		param.put("memId", memId);
		param.put("memPassword", memPassword);
		
		UserVO user = loginMapper.getLoginUser(param);
		
		// UserVO 객체에 데이터가 없으면 로그인 실패
		if(user == null) {
			throw new Exception("Failed Login.");
		}
		
		return user;
		
	}
	
}
