package ServletLayer;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ModelLayer.OrderItem;

public class DeleteOrderItemServlet extends HttpServlet {
	
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException{
		String name = request.getParameter("name");
		List<OrderItem> list = (ArrayList<OrderItem>) request.getSession().getAttribute("order");
		
		if(null == list){
			System.out.println("获取购物单失败");
			return;
		}
		
		Iterator itor = list.iterator();
		while(itor.hasNext()){
			OrderItem oi = (OrderItem) itor.next();
			if(oi.getProduct().getName().equals(name)){
				itor.remove();
				break;
			}
		}
		
		request.getSession().setAttribute("order", list);
		
		request.getRequestDispatcher("listOrderItem").forward(request, response);
	}

}
