package DAOLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ModelLayer.OrderItem;

public class OrderItemDAO {
	
	public OrderItemDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("OrderItemDAO启动失败");
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
	}
	
	public void setOrderItem(OrderItem oi){
		String sql = "insert into orderitem values(null,?,?,?)";
		int pid = oi.getProduct().getId();
		int number = oi.getNumber();
		int oid = oi.getOrder().getId();
		
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, pid);
			ps.setInt(2, number);
			ps.setInt(3, oid);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();
			
			if(rs.next()){ //将订单项保存到数据库中，同时获得自增id
				oi.setId(rs.getInt(1));
			}
			
		}catch(SQLException e){
			
		}
		
	}

}
