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
	
	
	public String readBills(){
		String output = "";
	
		try {
			 Connection con = connect();
			 if (con == null)
			 	{return "Error while connecting to the database for reading."; }
	 
				 output = "<table border='1'><tr><th>Customer Name</th>" +
				 "<th>Customer Address</th>" +
				 "<th>Bill Date</th>" +
				 "<th>Units Per User</th>" +
				 "<th>Unit Price</th>" +
				 "<th>Bill Amount</th>" +
				 "<th>TAX</th>" +
				 "<th>Total Amount</th>" ;
//				 "<th>Update</th><th>Remove</th></tr>";

				 String query = "select * from bill";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
			 while (rs.next())
			 {
				 String billID = Integer.toString(rs.getInt("billID"));
				 String billName = rs.getString("billName");
				 String address = rs.getString("address");
				 String date = rs.getString("date");;
				 String units = rs.getString("units");
				 String unitPrice = rs.getString("unitPrice");
				 String billAmount = rs.getString("billAmount");
				 String tax = rs.getString("tax");
				 String totalAfterTax = rs.getString("totalAfterTax");
	 
				 output += "<tr><td>" + billName + "</td>";
				 output += "<td>" + address + "</td>";
				 output += "<td>" + date + "</td>";
				 output += "<td>" + units + "</td>";
				 output += "<td>" + unitPrice + "</td>";
				 output += "<td>" + billAmount + "</td>";
				 output += "<td>" + tax + "</td>";
				 output += "<td>" + totalAfterTax + "</td>";
	 
//				 output += "<td><input name='btnUpdate' type='button' value='Update' class='btn btn-secondary'></td>"
//			 			+ "<td><form method='post' action='items.jsp'>"
//			 			+ "<input name='btnRemove' type='submit' value='Remove' class='btn btn-danger'>"
//			 					+ "<input name='itemID' type='hidden' value='" + billID + "'>" + "</form></td></tr>";
			 }
			 con.close();
			 output += "</table>";
	 }
	 catch (Exception e)
	 {
		 output = "Error while reading the items.";
		 System.err.println(e.getMessage());
	 }
	 return output;
	 } 
	
	
	//UPDATE 
	public String updateBill(String id, String name, String address, String date,
				String units, String unitPrice, String tax) {
		
		String output = "";
		
		try {
			
			Double billAmount = Integer.parseInt(units) * Double.parseDouble(unitPrice);
			Double totalAfterTax = billAmount + ((billAmount * Double.parseDouble(tax))/100);
			
			Connection con = connect();
			 if (con == null){
				 return "Error while connecting to the database for reading."; 
			 }
			 
			 String query = "UPDATE bill SET "
			 		+ "billName=?, address=?, date=?, units=?, unitPrice=?, billAmount=? , tax=?, totalAfterTax=?"
			 		+ "WHERE billID=?";
			 
			 PreparedStatement preparedStatement = con.prepareStatement(query);
			 
			 	
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, address);
				preparedStatement.setString(3, date);
				preparedStatement.setInt(4, Integer.parseInt(units));
				preparedStatement.setDouble(5, Double.parseDouble(unitPrice));
				preparedStatement.setDouble(6, billAmount);
				preparedStatement.setInt(7, Integer.parseInt(tax));
				preparedStatement.setDouble(8, totalAfterTax);
				preparedStatement.setInt(9, Integer.parseInt(id));

			 
				preparedStatement.execute();
				con.close();
				
				output = "Updated Successfully!";
			 
			
		} catch (Exception e) {
			 output = "Error while updating the item.";
			 System.err.println(e.getMessage());
		}
		return output;
	
	}
	
	
	//DELETE
	public String deleteBill(String billID) {
		
		String output = "";
		
		try {
			
			Connection con = connect();
			 if (con == null){
				 return "Error while connecting to the database for reading."; 
			 }
			 
			 String query = "delete from bill where billID=?";
			 
			 PreparedStatement preparedStatement = con.prepareStatement(query);
			 
			 preparedStatement.setInt(1, Integer.parseInt(billID));
			 
			 preparedStatement.execute();
			 con.close();
			 
			 output = "Deleted Successfully!";
			
		} catch (Exception e) {
			 output = "Error while deleting the item.";
			 System.err.println(e.getMessage());
		}
		
		return output;
	}
}