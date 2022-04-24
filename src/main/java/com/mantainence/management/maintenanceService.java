package com.mantainence.management;

import model.Maintenance;

//For REST Service
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
//For JSON
import com.google.gson.*;
//For XML
import org.jsoup.*;
import org.jsoup.parser.*;
import org.jsoup.nodes.Document;


@Path("/Maintenance")
public class maintenanceService {
	Maintenance MaintenanceObj = new Maintenance();
	@GET
	@Path("/AddMaintenance")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertNewMaintenance(
			@FormParam("name")String name,
			@FormParam("area")String area,
			@FormParam("place")String place,
			@FormParam("time")String time,
			@FormParam("status")String status) {
	   String output= MaintenanceObj.insertMaintenance(name, area, place, 0, status);
			   return output;
	}
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getMaintenance() {
		return MaintenanceObj.readMaintenance();
	}
	
	@PUT
	@Path("/UpdateMeintenace")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String UpdateMaintenace(String MaintenanceData){
		
		JsonObject MaintenanceObject = new JsonParser().parse(MaintenanceData).getAsJsonObject();
		
		String MaintenanceID = MaintenanceObject.get("MaintenanceID").getAsString();
		String Maintenancename = MaintenanceObject.get("MaintenanceName").getAsString();
		String MaintenanceArea = MaintenanceObject.get("MaintenanceArea").getAsString();
		String MaintenancePlace = MaintenanceObject.get("MaintenancePlace").getAsString();
		String MaintenanceTime = MaintenanceObject.get("MaintenanceTime").getAsString();
		String MaintenanceStatus = MaintenanceObject.get("MaintenanceStatus").getAsString();
		
		String output= MaintenanceObj.updateMaintenance(MaintenanceID, Maintenancename, MaintenanceArea, MaintenancePlace, MaintenanceTime, MaintenanceStatus);
				
		return output;
		
	}
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteMaintenance(String MaintenanceData)
	{
	//Convert the input string to an XML document
		Document doc = Jsoup.parse(MaintenanceData, "", Parser.xmlParser());
		//Read the value from the element <MaintenanceID>
		String MaintenanceID = doc.select("MaintenanceID").text();
		
		String output = MaintenanceObj.deleteMaintenance(MaintenanceID);
				
		return output;
	}


	
	

}
