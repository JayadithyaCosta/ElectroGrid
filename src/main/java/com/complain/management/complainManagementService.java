package com.complain.management;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

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
}