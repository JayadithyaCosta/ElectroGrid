package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class Complain {
	
private Connection connect() {
		
		Connection con = null;
		
		try {
			
			Class.forName("com.mysql.jdbc.Driver"); 	
			
			con = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/eg", "root", "root");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return con;
	}

	
	public String insertComplain(String name, String address, String date, String issue) {
		
		String output = "";
		
		try {
			
			String setStatus = "Pending";
			String setRemarks = "Under Review";
			
			Connection con = connect();

			if(con == null) {
				return "Error while connecting to Database!";
			}
			
			String query = "insert into complain (`complainID`, `customerName`, `customerAddress`, `complainDate`, `issue`, `status`, `remark`)" + "values (?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, date);
			preparedStatement.setString(5, issue);
			preparedStatement.setString(6, setStatus);
			preparedStatement.setString(7, setRemarks);


			preparedStatement.execute();
			con.close();
			output = "Inserted successfully";
			

			
			} catch (Exception e) {
				output = "Error while inserting the item.";
				System.err.println(e.getMessage());
			}
		
		return output;
	}
	
}