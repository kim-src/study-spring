package kr.com.web.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class LoginController {
	
	@GetMapping(value="/login.do")
	public ModelAndView loginView() {
		ModelAndView view = new ModelAndView();
		view.setViewName("login/loginView");
		
		return view;
	}

}
