package DAOLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import ModelLayer.User;

public class UserDAO {
	public UserDAO(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			System.out.println("DAO≥ı ºªØ ß∞‹");
		}
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/cart?characterEncoding=utf-8", "root", "admin");
	}
	
	public User getUser(String name,String password){
		User u=null;
		String sql = "select * from user where name=? and password=?";
		
		try(Connection c = getConnection();PreparedStatement ps = c.prepareStatement(sql);){
			ps.setString(1, name);
			ps.setString(2, password);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()){
			    User p = new User();
				p.setId(rs.getInt(1));
				p.setName(name);
				p.setPassword(password);
				u=p;
			}
			
		}catch(SQLException e){
			System.out.println("≤È’“ ß∞‹£¨ ß∞‹¿‡–Õ"+e.getClass().toString());
		}
		
		return u;
	}
}
