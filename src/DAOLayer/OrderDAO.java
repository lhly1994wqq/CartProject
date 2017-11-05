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
			System.out.println("OrderDAO����ʧ��");
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
			
			ResultSet rs = ps.getGeneratedKeys();//��USER ID���������ݿ��У�ͬʱ�õ�����ID
			if(rs.next()){
				order.setId(rs.getInt(1));
			}
			
		}catch(Exception e){
			System.out.println("�޷�����Order���ݣ���������"+e.getClass().toString());
		}
	}
	
}
