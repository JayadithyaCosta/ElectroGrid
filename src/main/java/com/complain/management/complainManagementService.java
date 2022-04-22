package com.complain.management;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import model.Complain;

@Path("/Complain")
public class complainManagementService {
	
	Complain compObj = new Complain();
	

	@POST
	@Path("/AddComplain")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertNewBill(
					@FormParam("customerName") String customerName, 
					@FormParam("customerAddress") String customerAddress,
					@FormParam("complainDate") String complainDate,
					@FormParam("issue") String issue) {
		
		String output = compObj.insertComplain(customerName, customerAddress, complainDate, issue);
		return output;
		
	}
	
	@PUT
	@Path("/UpdateComplain")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateComplain(String complainData) {
		
		JsonObject compObject = new JsonParser().parse(complainData).getAsJsonObject();	
		
			 String complainID = compObject.get("complainID").getAsString();
			 String customerName = compObject.get("customerName").getAsString();
			 String address = compObject.get("customerAddress").getAsString();
			 String date = compObject.get("complainDate").getAsString();
			 String issue = compObject.get("issue").getAsString();
			 String status = compObject.get("status").getAsString();
			 String remark = compObject.get("remark").getAsString();
			 
			 String output = compObj.updateComplain(complainID, customerName, address, date, issue, status, remark);
			 
			 return output;
		
	}
	

}


