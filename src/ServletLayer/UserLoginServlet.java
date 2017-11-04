package ServletLayer;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOLayer.UserDAO;
import ModelLayer.User;

public class UserLoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		
		User u  = new UserDAO().getUser(name, password);
		
		if(null != u){
			System.out.println("登录成功");
			request.getSession().setAttribute("user", u);
			response.sendRedirect("productlistServlet");
		}else{
			System.out.println("未找到用户");
			response.sendRedirect("login.jsp");
		}
			
	}

}
