package co.code.mvc;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/page")
public class PageServlet extends HttpServlet {

	// Sources (Alt + Shift + S)
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("print get in console");
		// super.doPost를 process로 수정
		process(req, resp);
	}

	// Sources (Alt + Shift + S)
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		System.out.println("print post in console");
		// super.doPost를 process로 수정
		process(req, resp);
	}

	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// 한글 깨짐 방지
		req.setCharacterEncoding("utf-8");
		
		// 페이지 이동 방법 중 request의 forward 사용
		RequestDispatcher disp = req.getRequestDispatcher("/WEB-INF/views/jsp/hello.jsp");
		req.setAttribute("myName", "김수박");
		req.setAttribute("myAge", "30");
		
		disp.forward(req, resp);
	}

}
