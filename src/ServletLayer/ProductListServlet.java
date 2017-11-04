package ServletLayer;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAOLayer.ProductDAO;
import ModelLayer.Product;

public class ProductListServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request,HttpServletResponse response){
		 List<Product> list = new ProductDAO().ListProduct();
		 request.setAttribute("ProductList", list);
		 
		 try {
			request.getRequestDispatcher("listProduct.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			System.out.println("servlet²ãÌø×ªÊ§°Ü");
		}
	}

}
