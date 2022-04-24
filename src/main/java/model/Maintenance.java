package model;
import java.sql.*;

public class Maintenance{
	private Connection connect() {
		Connection con=null;
		
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			
			con=DriverManager.getConnection("");
		}
		catch(Exception e )
		{e.printStackTrace();}
		
		return con;
		
	}
	
	public String insertMaintenance(String name, String area, String place, double time, String status) 
	{
		
		String output="";
		
		try 
		{
			Connection con=connect();
			
			if(con ==null)
			{return "Error while connecting to the inserting.";}
			
			String query ="insert in to Maintenance('MaintenanceID','name','area','place', 'time','satus')"
					+"value(?,?,?,?,?,?)";
			
			PreparedStatement preparedStmt= con.prepareStatement(query);
			
			//binding values
			preparedStmt.setInt(1,0);
			preparedStmt.setString(2,name);
			preparedStmt.setString(3,area);
			preparedStmt.setString(4,place);
			preparedStmt.setDouble(5,time);
			preparedStmt.setString(5,status);
			
			//execute the statement
			
			preparedStmt.execute();
			
			output="inserted successfully";
				
		}
		catch(Exception e) {
			output="error while inserting the Maitenace request";
			System.err.println(e.getMessage());
		}
		return output;
	}
	public String readMaintenance() 
	{
		String output="";
		
			try
			{
				Connection con=connect();
			
				if(con== null)
				{return "Error while connecting to the database for reading."; }
			
				// Prepare the html table to be displayed
				output = "<table border='1'><tr><th>Maintenance ID</th><th>Item Name</th>" +
						"<th>name</th>" +
						"<th>Area</th>" +
						"<th>Place</th>" +
						"<th>Time</th>" +
						"<th>Status</th>" +
						"<th>Update</th><th>Remove</th></tr>";
				String query = "select * from items";
				Statement stmt = con.createStatement();
				ResultSet rs = stmt.executeQuery(query);
			
			
		
				while(rs.next()) {
					String MaintenanceID=Integer.toString(rs.getInt("MaintenanceID"));
					String name=rs.getString("name");
					String area=rs.getString("area");
					String place=rs.getString("place");
					Double time=rs.getDouble("time");
					String status=rs.getString("status");
					
					
					// Add into the html table
					output += "<tr><td>" + name + "</td>";
					output += "<td>" + area + "</td>";
					output += "<td>" + place + "</td>";
					output += "<td>" + time + "</td>";
					output += "<td>" + status + "</td>";
					
		
				//buttons
				output = "<td><input name='btnpdate' type ='button' value='Update "
					+ "class='btn-secndary'></td>"
					+"<td><form method='post' action='items.jsp'>"
					+"<input name='itemID' type='hidden value'"+ MaintenanceID
					+"'>"+"</form></td></tr>";
							
				}
				con.close();
		//cmplete the html table
					output += "</table>";
	}
	catch(Exception e)
	{
		output = "Error while reading the Maintenance.";
		System.err.println(e.getMessage());
			
	}
	return output;
	}
	
	public String updateMaintenance(String MaintenanceID, String name, String area, String place, String time, String status) 
	{

		String output = "";
			try
			{
				Connection con = connect();
				if (con == null)
				{return "Error while connecting to the database for updating."; }
				// create a prepared statement
				String query = "UPDATE MAintenance SET name=?, area=?,place=?,time=?, status=? where MaintenaceID=? ";
						
						
				PreparedStatement preparedStmt = con.prepareStatement(query);
				// binding values
				preparedStmt.setString(1, name);
				preparedStmt.setString(2, area);
				preparedStmt.setString(3, place);
				preparedStmt.setDouble(3, Double.parseDouble(time));
				preparedStmt.setString(4, status);
				preparedStmt.setInt(5, Integer.parseInt(MaintenanceID));
				// execute the statement
				preparedStmt.execute();
				con.close();
				output = "Updated successfully";
			}
		catch (Exception e)
		{
		output = "Error while updating the item.";
		System.err.println(e.getMessage());
		}
		return output;
		}	
	
	public String deleteMaintenance(String MaintenaceID)
	{
		String output = "";
		try
		{
				Connection con = connect();
				if (con == null)
					{return "Error while connecting to the database for deleting."; }
					// create a prepared statement
					String query = "delete from items where MAintenanceID=?";
					PreparedStatement preparedStmt = con.prepareStatement(query);
					// binding values
					preparedStmt.setInt(1, Integer.parseInt(MaintenaceID));
					// execute the statement
					preparedStmt.execute();
					con.close();
					output = "Deleted successfully";
		}
		catch (Exception e)
		{
			output = "Error while deleting the Maintenance.";
			System.err.println(e.getMessage());
		}
		return output;
	}
	
	
	
	
	

}