package kr.com.web.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;

// HandlerInterceptor 구현
// default 메서드를 사용하기 때문에 인터페이스에서 구현된 메서드를 생성할 수 있음
public class LoginInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		boolean isTrue = true;
		
		// 로그인 여부 → 세션 정보 → 로그인 사용자 정보 유무
		HttpSession session = request.getSession();
		
		if(session.getAttribute("userInfo") == null) {
			// 로그인이 아닌 상태
			isTrue = false;
			response.sendRedirect("/login.do");
		}
		
		return isTrue;
	}

}
