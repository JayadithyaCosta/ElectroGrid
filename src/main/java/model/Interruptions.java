package model; 
import java.sql.*; 
public class Interruptions 
{
	//A common method to connect to the DB
	private static String url = "jdbc:mysql://localhost:3306/electricity_interruptions";
	private static String userName = "root";
	private static String password = "12345";
private Connection connect() 
 { 
 Connection con = null; 
 try
 { 
 Class.forName("com.mysql.jdbc.Driver"); 
 
 //Provide the correct details: DBServer/DBName, username, password 
 con= DriverManager.getConnection(url,userName,password);
 //For testing
 System.out.print("Successfully connected");
 } 
 catch (Exception e) 
 { 
 	System.out.println("Database connection is not success!!!");
 }
 return con; 
 } 

// INSERT Interruption details to the database
public String insertInterruptions(String region, String date, String stime, String etime, String status) 
 { 
 String output = ""; 
 try
 { 
 Connection con = connect(); 
 if (con == null) 
 {return "Error while connecting to the database for inserting."; } 
 // create a prepared statement
 String query = "insert into electricity_interruptions.interruption_table(interruptionid,region,date,starting_time,end_time,status) values (?,?,?,?,?,?)"; 

 PreparedStatement preparedStmt = con.prepareStatement(query);
 // binding values
 preparedStmt.setInt(1, 0);
 preparedStmt.setString(2, region);
 preparedStmt.setString(3, date);
 preparedStmt.setString(4, stime);
 preparedStmt.setString(5, etime);
 preparedStmt.setString(6, status);


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

//RETRIVE Interruptions details

public String readInterruptions()
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
output = "<table border='1'><tr><th>Region</th>"
+"<th>Date</th><th>Starting Time</th>"
+ "<th>End Time</th>"+"<th>Status</th>";
//+ "<th>Update</th><th>Remove</th></tr>";
String query = "select * from interruption_table";
Statement stmt = con.createStatement();
ResultSet rs = stmt.executeQuery(query);
// iterate through the rows in the result set
while (rs.next())
{
String interruptionid = Integer.toString(rs.getInt("interruptionid"));
String region = rs.getString("region");
String date = rs.getString("date");
String starting_time = rs.getString("starting_time");
String end_time = rs.getString("end_time");
String status = rs.getString("status");

// Add a row into the html table
output += "<tr><td>" + region + "</td>";
output += "<td>" + date + "</td>";
output += "<td>" + starting_time + "</td>";
output += "<td>" + end_time + "</td>";
output += "<td>" + status + "</td>";

// buttons

output += "<td><form method='post' action='interruption.jsp'>"/*"<td><input name='btnUpdate' "
+ " type='button' value='Update'></td>"*/

/*+ "<input name='btnRemove' "
+ " type='submit' value='Remove'>"
+ "<input name='itemID' type='hidden' "
+ " value='" + interruptionid + "'>"*/ + "</form></td></tr>";
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
//UPDATE Interruption data 

	public String updateInterruptions(String interruptionid, String region, String date, String stime, String etime, String status)
	
	{
	    String output = "";
	try
	{
	   Connection con = connect();
	   if (con == null)
	    {  return "Error while connecting to the database for updating."; }
	// create a prepared statement
	  String query = "UPDATE interruption_table SET region=?,date=?,starting_time=?,end_time=?,status=? WHERE interruptionid=?";
	  PreparedStatement preparedStmt = con.prepareStatement(query);
	// binding values
	   preparedStmt.setString(1, region);
	   preparedStmt.setString(2, date);
	   preparedStmt.setString(3, stime);
	   preparedStmt.setString(4, etime);
	   preparedStmt.setString(5, status);
	   preparedStmt.setInt(6, Integer.parseInt(interruptionid));
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


//Remove Interruption details from the database
	
public String deleteInterruptions(String Interuptiondata)
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
String query = "delete from interruption_table where interruptionid=?";
PreparedStatement preparedStmt = con.prepareStatement(query);
// binding values
preparedStmt.setInt(1, Integer.parseInt(Interuptiondata));
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


}