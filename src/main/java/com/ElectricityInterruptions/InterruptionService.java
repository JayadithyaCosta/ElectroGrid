package com.ElectricityInterruptions; 
import model.Interruptions; 
//For REST Service
import javax.ws.rs.*; 
import javax.ws.rs.core.MediaType; 
//For JSON
import com.google.gson.*; 
//For XML
import org.jsoup.*; 
import org.jsoup.parser.*; 
import org.jsoup.nodes.Document; 
@Path("/interruption") 
public class InterruptionService 
{ 

	Interruptions itemObj = new Interruptions();

//read
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
{
return itemObj.readInterruptions();
}
// Calling INSERT method

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String insertItem(@FormParam("region") String region,
	@FormParam("date") String date,
	@FormParam("stime") String stime,
	@FormParam("etime") String etime,
	@FormParam("status") String status)
	{
	String output = itemObj.insertInterruptions(region, date, stime, etime, status);
	return output;
	

	}

//Calling UPDATE method 

	
		@PUT
		@Path("/")
		@Consumes(MediaType.APPLICATION_JSON)
		@Produces(MediaType.TEXT_PLAIN)
		public String updateInterruptions(String itemData)
		{
		//Convert the input string to a JSON object
		JsonObject itemObject = new JsonParser().parse(itemData).getAsJsonObject();
		//Read the values from the JSON object
		String interruptionid = itemObject.get("interruptionid").getAsString();
		String region = itemObject.get("region").getAsString();
		String date = itemObject.get("date").getAsString();
		String stime = itemObject.get("stime").getAsString();
		String etime = itemObject.get("etime").getAsString();
		String status = itemObject.get("status").getAsString();
		
		String output = itemObj.updateInterruptions(interruptionid, region, date, stime, etime,status);
		return output;
		}
//Calling DELETE method
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteInterruptions(String itemData)
{
//Convert the input string to an XML document
	Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
//Read the value from the element <itemID>
String interruptionid = doc.select("interruptionid").text();
String output = itemObj.deleteInterruptions(interruptionid);
return output;
}

}