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
		//��õ�ǰ�û�
		if(null == user){
			response.sendRedirect("login.jsp");
			return;
		}//�ж��û��Ƿ��¼����δ��¼������ת����¼ҳ��
		
		List<OrderItem> list = (ArrayList<OrderItem>)request.getSession().getAttribute("order");
		//���session�еĶ���
		
		Order od = new Order();
		//����һ����������
		
		od.setUser(user);
		//��ʹ���û���ӵ�������
		
		new OrderDAO().setOrder(od);
		//��������ӵ����ݿ��У�ͬʱ����˶�����ID��
		
		for(OrderItem oi:list){
			OrderItemDAO oiDAO = new OrderItemDAO();
			oi.setOrder(od);
			oiDAO.setOrderItem(oi);
		}//������������ÿ����������ӽ����ݿ��У������ö�Ӧ�Ķ���ID��
		
		request.getSession().setAttribute("order", null);
		//��ն�����
		
		response.setContentType("text/html;charset=UTF-8");
		response.getWriter().println("���������ɹ�");
	}

}
