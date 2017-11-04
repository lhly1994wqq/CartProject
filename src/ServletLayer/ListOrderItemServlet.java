package ServletLayer;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ListOrderItemServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response){
			try {
				request.getRequestDispatcher("listOrderItem.jsp").forward(request, response);
			} catch (ServletException e1) {
				System.out.println("订单列表跳转失败");
			} catch (IOException e1) {
				System.out.println("订单列表跳转失败");
			}
		
	}

}
