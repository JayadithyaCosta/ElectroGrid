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
@Path("/Items") 
public class InterruptionService 
{ 
 /*Interruptions itemObj = new Interruptions(); 
@GET
@Path("/") 
@Produces(MediaType.TEXT_HTML) 
public String readInterruptions() 
 { 
 return "Hello"; 
 } 
*/
	Interruptions itemObj = new Interruptions();

//read
@GET
@Path("/")
@Produces(MediaType.TEXT_HTML)
public String readItems()
{
return itemObj.readInterruptions();
}
//DELETE
@DELETE
@Path("/")
@Consumes(MediaType.APPLICATION_XML)
@Produces(MediaType.TEXT_PLAIN)
public String deleteItem(String itemData)
{
//Convert the input string to an XML document
Document doc = Jsoup.parse(itemData, "", Parser.xmlParser());
//Read the value from the element <itemID>
String itemID = doc.select("itemID").text();
String output = itemObj.deleteInterruptions(itemID);
return output;
}

}