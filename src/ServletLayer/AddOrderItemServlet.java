package ServletLayer;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOLayer.ProductDAO;
import ModelLayer.OrderItem;
import ModelLayer.Product;

public class AddOrderItemServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response){
		int pid = Integer.parseInt(request.getParameter("pid"));
		int number = Integer.parseInt(request.getParameter("num"));
		
		if(number == 0)
			return;     //如果订单数为0，则什么都不操作
		
		Product product = new ProductDAO().getPorduct(pid);
		OrderItem oi = new OrderItem();
		int id = 0;
		boolean found = false;
		
		oi.setNumber(number);
		oi.setProduct(product);
		
		List<OrderItem> order = (ArrayList<OrderItem>)request.getSession().getAttribute("order");
		//若从Session中提取的列表为null,类型转换也能成功进行，但是依然为null
		
		if(null == order){
			order = new ArrayList<OrderItem>();
		}
		//若order为空，则新建一个表加入session中
		
		Iterator itor = order.iterator();
		while(itor.hasNext()){
			OrderItem newoi = (OrderItem) itor.next();
			if(newoi.getProduct().getId() == oi.getProduct().getId()){
				newoi.setNumber(newoi.getNumber()+oi.getNumber());
				found = true;
				break;
			}
			id++;
		}
		//遍历表，若找到订单项中产品名相同的，则在其原本数量上增加
		
		if(!found){
			oi.setId(id);
			//若未找到，则给订单项添加id
			
			order.add(oi);
			//将订单项orderitem加入表中
		}
		
		request.getSession().setAttribute("order", order);
		//若存在则替换原来的表，若不存在，则加入新建的表
		
		try {
			response.sendRedirect("listOrderItem");
		} catch (IOException e) {
			System.out.println("添加订单项页面跳转失败！");
		}
	}
}
