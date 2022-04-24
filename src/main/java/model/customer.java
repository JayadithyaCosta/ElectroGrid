package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.xml.bind.ParseConversionEvent;

import com.mysql.cj.ParseInfo;

public class customer {
	private static String url = "jdbc:mysql://localhost:3306/school";
	private static String userName = "root";
	private static String password = "Udesh@1975";
	
	
	public Connection connect()
	{
	Connection con = null;
	
	try
	{
	  Class.forName("com.mysql.jdbc.Driver");
	  con= DriverManager.getConnection(url,userName,password);
	  //For testing
	  System.out.print("Successfully connected");
	}
	catch(Exception e)
	{
		System.out.println("Database connection is not success!!!");
	}
	
	return con;
	}
	
	
	//insert method
		public String insertItem(String AccountNumber, String name, String NIC, String Phone,String Email)
		{
		   String output = "";
		  try
		   {
		      Connection con = connect();
		      if (con == null)
		   {
		    return "Error while connecting to the database";
		   }
		   // create a prepared statement
		   String query = "insert into school.customer (idcustomer,AccountNumber,Name,NIC,Phone,Email) values (?,?,?,?,?,?)";
		   
		   
		   PreparedStatement preparedStmt = con.prepareStatement(query);
		   // binding values
		   preparedStmt.setInt(1, 0);
		   preparedStmt.setInt(2,Integer.parseInt(AccountNumber));
		   preparedStmt.setString(3, name);
		   preparedStmt.setString(4,NIC);
		   preparedStmt.setString(5, Phone);
		   preparedStmt.setString(6, Email);
		
		
		  //execute the statement
		  preparedStmt.execute();
		  con.close();
		  output = "Inserted successfully";
		  }
		  catch (Exception e)
		  {
		  output = "Error while inserting";
		  System.err.println(e.getMessage());
		  }
		  return output;
		  }
		
		//read
		
		public String readItems()
		  {
		     String output = "";
		     try
		    {
		      Connection con = connect();
		      if (con == null)
		    {
		        return "Error while connecting to the database for reading.";
		    }
		// Prepare the html table to be displayed
		   output = "<table border='1'><tr><th>AccountNumber</th>"
		            +"<th>Name</th><th>NIC</th>"
		            + "<th>Phone</th>"
		            + "<th>Email</th>"
		            + "<th>Update</th><th>Remove</th></tr>";
		   
		    String query = "select * from customer";
		    Statement stmt = con.createStatement();
		    ResultSet rs = stmt.executeQuery(query);
		// iterate through the rows in the result set
		   while (rs.next())
		   {
		     String itemID = Integer.toString(rs.getInt("idcustomer"));
		     String itemCode = Integer.toString(rs.getInt("AccountNumber"));
		     String itemName = rs.getString("Name");
		     String itemPrice = rs.getString("NIC");
		     String itemDesc = rs.getString("Phone");
		     String itemDesc2 = rs.getString("Email");
		     
		// Add a row into the html table
		    output += "<tr><td>" + itemCode + "</td>";
		    output += "<td>" + itemName + "</td>";
		    output += "<td>" + itemPrice + "</td>";
		
		    output += "<td>" + itemDesc + "</td>";
		    output += "<td>" + itemDesc2 + "</td>";
		// buttons
		    output += "<td><input name='btnUpdate' "
		    + " type='button' value='Update'></td>"
		    + "<td><form method='post' action='items.jsp'>"
		    + "<input name='btnRemove' "
		    + " type='submit' value='Remove'>"
		    + "<input name='itemID' type='hidden' "
		    + " value='" + itemID + "'>" + "</form></td></tr>";
		    }
		    con.close();
		// Complete the html table
		    output += "</table>";
		    }
		   catch (Exception e)
		  {
		      output = "Error while reading the items.";
		      System.err.println(e.getMessage());
		  }
		    return output;
		 }
		
		//delete
		
		public String deleteItem(String cusID)
		 {
		   String output = "";
		 try
		 {
		    Connection con = connect();
		    if (con == null)
		  {
		       return "Error while connecting to the database for deleting.";
		   }
		// create a prepared statement
		      String query = "delete from customer where idcustomer=?";
		      PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		      preparedStmt.setInt(1, Integer.parseInt(cusID));
		// execute the statement
		      preparedStmt.execute();
		      con.close();
		      output = "Deleted successfully";
		 }
		 catch (Exception e)
		 {
		      output = "Error while deleting the item.";
		      System.err.println(e.getMessage());
		 }
		 return output;
		 }
		
		//update
		public String updateItem(String ID, String ACnumber, String name, String NIC, String Phone,String Email)
		
		{
		    String output = "";
		try
		{
		   Connection con = connect();
		   if (con == null)
		    {  return "Error while connecting to the database for updating."; }
		// create a prepared statement
		  String query = "UPDATE customer SET AccountNumber=?,Name=?,NIC=?,Phone=?,Email=? WHERE idcustomer=?";
		  PreparedStatement preparedStmt = con.prepareStatement(query);
		// binding values
		   preparedStmt.setInt(1, Integer.parseInt(ACnumber));
		   preparedStmt.setString(2, name);
		   preparedStmt.setString(3, NIC);
		   preparedStmt.setString(4, Phone);
		   preparedStmt.setString(5, Email);
		   preparedStmt.setInt(6, Integer.parseInt(ID));
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

	
}
