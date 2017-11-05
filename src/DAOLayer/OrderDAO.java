package DAOLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ModelLayer.Order;

public class OrderDAO {
	
	public OrderDAO(){
		try{
			Class.forName("com.mysql.jdbc.Driver");
		}catch(ClassNotFoundException e){
			System.out.println("OrderDAO启动失败");
		}
	}
	
	public Connection getConnection() throws SQLException{
			return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=UTF-8","root","admin");
	}
	
	public void setOrder(Order order){
		String sql = "insert into order1 values(null,?)";
		int uid = order.getUser().getId();
		
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql)){
			ps.setInt(1, uid);
			
			ps.execute();
			
			ResultSet rs = ps.getGeneratedKeys();//将USER ID保存在数据库中，同时得到订单ID
			if(rs.next()){
				order.setId(rs.getInt(1));
			}
			
		}catch(Exception e){
			System.out.println("无法插入Order数据，错误类型"+e.getClass().toString());
		}
	}
	
}
