package com.test.iot.servlet;

import java.io.IOException;
import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.iot.service.UserService;
import com.test.iot.service.UserServiceImpl;

public class UserServlet extends HttpServlet {
	UserService us = new UserServiceImpl();
	public String getCommand(String uri) {	
		
		int idx = uri.lastIndexOf("/");       //오른쪽부터 /른 찾아서 위치값을반환한다.
		if(idx != -1) {			
			return uri.substring(idx+1);		  //받아온 uri의 맨 마지막 파일경로(/를 제외하고)를 문자열로 받으려고 하는 (그 번째포함하여 뒤로 문자열을 받아온다 )
		}		
		return "";		
	}
	
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
				
	}
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		doProcess(req, res);
				
	}
	
	//get 방식 
	//GET방식은 보안의 취약하다는 단점이 있습니다. GET방식은 URL 뒤에 데이터의 값이 출력되어 전송됩니다. http://blog.naver.com/editor?name=algorithm&num=1의 방식처럼, ? 뒤에 요청값을 붙여 전송됩니다. 이와 같이 전송되기 때문에, 보안에 취약하며 많은양의 데이터를 보낼 수 없습니다. 그렇기 때문에, GET방식은 단순 페이지 요청과 같은 작업을 실행하며, 데이터를 전송하여 서버의 값이나 상태를 바꾸는 역할은 수행할 수는 없습니다.
	
	//post 방
	//POST 방식은 GET과는 달리, 요청값이 HTTP BODY에 저장되어 전송됩니다. BODY에 저장되어 파일에 형태로 전송되기 때문에 많은양의 전송이 가능합니다. 또한, URL주소값 뒤에 추가하여 보내는 방식이 아니기 때문에, GET방식의 보안문제를 해결할 수 있습니다.

	
	public void doProcess(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		req.setCharacterEncoding("utf-8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/html;charset=utf-8");

		String uri = req.getRequestURI();     //uri: 프로젝트와 파일결로를 다 가져온다  (/포함), getContextPath: 프로젝트 경로만 가져온다 
		String cmd = getCommand(uri);		//getCommand 함수호출 
	
		if(cmd.equals("list")) {					 //마지막 파일 경로가 list 라면 ~
			req.setAttribute("list", us.getUserList());	//  setAttribute 속성값을 변경하는 메소드 그럼 Action으로 넘어온 값을 변경시킨후 JSP 페이지로 넘겨주기 위해서는 request.setAttribute() 를 써서 넘겨주고 JSP 페이지에서는 request.getAttribute()를 써서 받아야한다.이때 type이 Object 이기 때문에 반드시 형변환을 해줘야 한다.
		


		}else if(cmd.equals("view")) {			
			us.getUser();
		}else if(cmd.equals("insert")) {         //????어떻게 이렇게 insert 일때 바로 insertok 를 다시 cmd로 받을수있지..?
		} if(cmd.equals("insertok")){
			HashMap<String,String> hm = new HashMap<String,String>();
			hm.put("uiname", req.getParameter("uiname"));      //*중요* 폼을 서브밋해서 액션을 탓을때에 insert.jsp 에서 사용자가 입력받은 name 값을 req.getParameter("name") 으로 값을가져올수있다.
			hm.put("uiage", req.getParameter("uiage"));
			hm.put("uiid", req.getParameter("uiid"));
			hm.put("uipwd", req.getParameter("uipwd"));
			hm.put("address", req.getParameter("address"));
			us.insertUser(hm);
			req.setAttribute("list", us.getUserList());
			uri="/user/list";
		}else {
			cmd = "/common/error";
		}
//		cmd += ".jsp";			
//		RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/user/" +cmd);
		
		uri = "/WEB-INF/view" + uri + ".jsp" ;
		RequestDispatcher rd = req.getRequestDispatcher(uri);     //지금여기UsreServlet 페이지에 request 를 다른 페이지로 이동할때 ....괄호안에 이동할페이지파일명 입력하기 
		rd.forward(req, res);										//forward 일경우 페이지를 이동시킨후 작업후 다시 돌아오지 않는다.(돌아오고 싶을땐 include)
//		PrintWriter out = res.getWriter();	
		
		
		
	}
}