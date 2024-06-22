package kr.com.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {
	
	// GET/POST 모두 수용
	// @RequestMapping(value="/hello.do")
	// only GET 수용
	// @RequestMapping(value="/hello.do", method = RequestMethod.GET)
	// only POST 수용
	// @RequestMapping(value="/hello.do", method = RequestMethod.POST)
	
	@GetMapping(value="/hello.do")
	public String hello(Model model) {
		model.addAttribute("msg", "2024-06-21 강의 주제 : ");
		return "hello";
	}

}
