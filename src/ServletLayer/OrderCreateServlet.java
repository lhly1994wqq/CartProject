package ServletLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOLayer.OrderDAO;
import DAOLayer.OrderItemDAO;
import ModelLayer.Order;
import ModelLayer.OrderItem;
import ModelLayer.User;

public class OrderCreateServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws IOException, ServletException{
		User user = (User)request.getSession().getAttribute("user");
		//获得当前用户
		if(null == user){
			response.sendRedirect("login.jsp");
			return;
		}//判断用户是否登录，若未登录，则跳转到登录页面
		
		List<OrderItem> list = (ArrayList<OrderItem>)request.getSession().getAttribute("order");
		//获得session中的订单
		
		Order od = new Order();
		//生成一个订单对象
		
		od.setUser(user);
		//将使用用户添加到对象中
		
		new OrderDAO().setOrder(od);
		//将订单添加到数据库中，同时获得了订单的ID号
		
		for(OrderItem oi:list){
			OrderItemDAO oiDAO = new OrderItemDAO();
			oi.setOrder(od);
			oiDAO.setOrderItem(oi);
		}//遍历订单，将每个订单项添加进数据库中，并设置对应的订单ID号
		
		request.getSession().setAttribute("order", null);
		//清空订单项
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("订单创建成功");
	}

}
