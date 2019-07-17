package configuration;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class database {

	Connection con;
	PreparedStatement ps;
	ResultSet rs;
	Statement stm;
	
	public database() {

		
	}
	
	public void readData(String query) {
		
		try {
			ps=con.prepareStatement(query);
			rs=ps.executeQuery();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
	
	public void closeDB() {
		try {
			con.close();
			ps.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void openDB(Connection con) {
		this.con=con;
		
	}
	
	
	public void writeData(Connection con,String query) {
		openDB(con);
		try {
			stm = con.createStatement();
			stm.executeUpdate(query);
			closeDB();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
	public ResultSet getResultSet() {
		
		return rs;
	}
	
	
}
