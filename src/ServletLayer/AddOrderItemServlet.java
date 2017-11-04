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
			return;     //���������Ϊ0����ʲô��������
		
		Product product = new ProductDAO().getPorduct(pid);
		OrderItem oi = new OrderItem();
		int id = 0;
		boolean found = false;
		
		oi.setNumber(number);
		oi.setProduct(product);
		
		List<OrderItem> order = (ArrayList<OrderItem>)request.getSession().getAttribute("order");
		//����Session����ȡ���б�Ϊnull,����ת��Ҳ�ܳɹ����У�������ȻΪnull
		
		if(null == order){
			order = new ArrayList<OrderItem>();
		}
		//��orderΪ�գ����½�һ�������session��
		
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
		//���������ҵ��������в�Ʒ����ͬ�ģ�������ԭ������������
		
		if(!found){
			oi.setId(id);
			//��δ�ҵ���������������id
			
			order.add(oi);
			//��������orderitem�������
		}
		
		request.getSession().setAttribute("order", order);
		//���������滻ԭ���ı��������ڣ�������½��ı�
		
		try {
			response.sendRedirect("listOrderItem");
		} catch (IOException e) {
			System.out.println("��Ӷ�����ҳ����תʧ�ܣ�");
		}
	}
}
