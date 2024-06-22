// 예시용 서블릿
package co.code.mvc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// 배열 형태로 Servlet을 등록 받음
@WebServlet(urlPatterns = {"/hello"},
			initParams = {
					@WebInitParam(name="myName", value="홍길동"),
					@WebInitParam(name="myAge", value="30")
			})
public class HelloServlet extends HttpServlet {

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		process(req, resp);
	}

	// request 메서드 중 get, post를 동시에 받을 수 있게 설정함
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		// UTF-8이 적용된 상태에서 PrintWriter가 실행되어야 됨
//		resp.setContentType("text/html; charset=utf-8");
		// 더욱 완벽한 방법
		resp.setCharacterEncoding("utf-8");
		PrintWriter out = resp.getWriter();
		
		String myName = this.getInitParameter("myName");
		String myAge = this.getInitParameter("myAge");
		
		out.print("<html>");
		out.print("	<body>");
		out.print("		<span>이름 : " + myName + "</span><br>");
		out.print("		<span>나이 : " + myAge + "</span><br>");
		out.print("	</body>");
		out.print("</html>");
	}

}
