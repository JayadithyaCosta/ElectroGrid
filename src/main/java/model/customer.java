package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

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
		
	
}
