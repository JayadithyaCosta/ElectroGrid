package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

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

	//INSERT
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

	
	//UPDATE 
	public String updateComplain(String id, String name, String address, String date,
								String issue, String status, String remark) {
				
				String output = "";
				
				try {
					
					
					Connection con = connect();
					 if (con == null){
						 return "Error while connecting to the database for reading."; 
					 }
					 
					 String query = "UPDATE complain SET "
					 		+ "customerName=?, customerAddress=?, complainDate=?, issue=?, status=?, remark=?"
					 		+ "WHERE complainID=?";
					 
					 PreparedStatement preparedStatement = con.prepareStatement(query);
					 
					 	
						preparedStatement.setString(1, name);
						preparedStatement.setString(2, address);
						preparedStatement.setString(3, date);
						preparedStatement.setString(4, issue);
						preparedStatement.setString(5, status);
						preparedStatement.setString(6, remark);
						preparedStatement.setInt(7, Integer.parseInt(id));

					 
						preparedStatement.execute();
						con.close();
						
						output = "Updated Successfully!";
					 
					
				} catch (Exception e) {
					 output = "Error while updating the item.";
					 System.err.println(e.getMessage());
				}
				return output;
			
			}
	
	//READ
	public String readComplains(){
		String output = "";
	
		try {
			 Connection con = connect();
			 if (con == null)
			 	{return "Error while connecting to the database for reading."; }
	 
				 output = "<table border='1'><tr><th>Customer Name</th>" +
				 "<th>Customer Address</th>" +
				 "<th>Complain Date</th>" +
				 "<th>Issue</th>" +
				 "<th>Status</th>" +
				 "<th>Remarks</th>";
//				 "<th>Update</th><th>Remove</th></tr>";

				 String query = "select * from complain";
				 Statement stmt = con.createStatement();
				 ResultSet rs = stmt.executeQuery(query);
				 
			 while (rs.next())
			 {
//				 String complainID = Integer.toString(rs.getInt("complainID"));
				 String customerName = rs.getString("customerName");
				 String customerAddress = rs.getString("customerAddress");
				 String complainDate = rs.getString("complainDate");;
				 String issue = rs.getString("issue");
				 String status = rs.getString("status");
				 String remark = rs.getString("remark");
	 
				 output += "<tr><td>" + customerName + "</td>";
				 output += "<td>" + customerAddress + "</td>";
				 output += "<td>" + complainDate + "</td>";
				 output += "<td>" + issue + "</td>";
				 output += "<td>" + status + "</td>";
				 output += "<td>" + remark + "</td>";
	 

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

	}


	
