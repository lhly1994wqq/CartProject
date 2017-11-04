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
			System.out.println("��¼�ɹ�");
			request.getSession().setAttribute("user", u);
			response.sendRedirect("productlistServlet");
		}else{
			System.out.println("δ�ҵ��û�");
			response.sendRedirect("login.jsp");
		}
			
	}

}
