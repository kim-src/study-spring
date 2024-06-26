package kr.com.web.user.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.com.web.user.mapper.LoginMapper;
import lombok.RequiredArgsConstructor;

@Service
// @AllArgsConstructor
// @NoArgsConstructor
@RequiredArgsConstructor
public class DIService {
	
	private int count;
	
	// Dependency Injection
	
	// 1. 직접 주입 방식
	@Autowired
	private final LoginMapper loginMapper;
	
//	// 2. Setter 주입 방식
//	@Autowired // Setter로 의존성을 주입할 때는 Autowired 생략 가능함
//	public void setLoginMapper(LoginMapper loginMapper) {
//		this.loginMapper = loginMapper;
//	}
//	
//	// 3. 생성자 주입 방식
//	@Autowired // 생성자로 의존성을 주입할 때는 Autowired 생략 가능함
//	public DIService(LoginMapper loginMapper) {
//		this.loginMapper = loginMapper;
//	}

}
