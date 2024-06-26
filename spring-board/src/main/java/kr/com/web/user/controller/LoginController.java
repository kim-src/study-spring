package kr.com.web.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@GetMapping("/login.do")
	public ModelAndView loginView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("login/loginView");
		
		return view;
	}
	
	@GetMapping("/login/proc.do")
	@ResponseBody
	public loginProc(@RequestParam("memId") String memId,
					 @RequestParam("memPassword") String memPassword,
					 HttpServletRequest request) {
		
	}

}
