package model;

import java.sql.*;

public class Bill{
	
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
	
	
	public String insertBill(String id, String name, String address, String date, String units, String unitPrice) {
		
		String output = "";
		Double totalBillAmount = null;
		Integer unitAmount = null;
		Double unitPriceForBill= null;
		
		String tax = "10";
		Double totalAfterTax = null;
		
		
		try {
			
			Connection con = connect();
			
			unitAmount = Integer.parseInt(units);
			unitPriceForBill= Double.parseDouble(unitPrice);
			
			totalBillAmount = unitAmount * unitPriceForBill;
			
			totalAfterTax = totalBillAmount + ((totalBillAmount * Double.parseDouble(tax))/100);
			
			System.out.println(totalBillAmount + " AMOUNT");
			System.out.println(totalAfterTax + " TAX");
			
			if(con == null) {
				return "Error while connecting to Database!";
			}
			
			String query = "insert into bill (`billID`, `billName`, `address`, `date`, `units`, `unitPrice`, `billAmount`, `tax`, `totalAfterTax`)" + "values (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			PreparedStatement preparedStatement = con.prepareStatement(query);
						
			preparedStatement.setInt(1, 0);
			preparedStatement.setString(2, name);
			preparedStatement.setString(3, address);
			preparedStatement.setString(4, date);
			preparedStatement.setInt(5, unitAmount);
			preparedStatement.setDouble(6, unitPriceForBill);
			preparedStatement.setDouble(7, totalBillAmount);
			preparedStatement.setString(8, tax);
			preparedStatement.setDouble(9, totalAfterTax);

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