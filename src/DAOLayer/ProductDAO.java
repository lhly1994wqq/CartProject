package DAOLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ModelLayer.Product;

public class ProductDAO {
	
	
	public ProductDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("未找到mysql.driver包");
		}
	}
	
	public Connection getConnection()throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root", "admin");
	}
	
	public List<Product> ListProduct(){
		List<Product> list = new ArrayList<>();
		
		String sql = "select * from product order by id desc";
		
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
				Product p = new Product();
				
				int id = rs.getInt(1);
				String name = rs.getString(2);
				float price = rs.getFloat(3);
				
//				System.out.println("id:"+id+" name:"+name+" price:"+price+"\n\r");		
				p.setId(id);
				p.setName(name);
				p.setPrice(price);
				
				list.add(p);
			}
		}catch(SQLException e){
			System.out.println("从数据库获取数据失败！");
		}
		
		return list;
		
	}
	
	public Product getPorduct(int id){
		Product p=null;
		String sql = "select * from product where id = ?";
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				p=new Product();
				p.setId(id);
				p.setName(rs.getString(2));
				p.setPrice(rs.getFloat(3));
			}
		}catch(SQLException e){
			System.out.println("查找商品失败");
		}
		
		return p;
	}

}
