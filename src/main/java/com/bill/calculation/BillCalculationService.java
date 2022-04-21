package com.bill.calculation;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.Bill;

@Path("/bill")
public class BillCalculationService {
	
	Bill billObj = new Bill();

	
	@POST
	@Path("/addBill")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_HTML)
	public String insertNewBill(
					@FormParam("billCode") String billCode, 
					@FormParam("billName") String billName,
					@FormParam("address") String address,
					@FormParam("date") String date,
					@FormParam("units") String units,
					@FormParam("unitPrice") String unitPrice) {
		String output = billObj.insertBill(billCode, billName, address, date, units, unitPrice);
		return output;
	}
	
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getBills() {
		
		return billObj.readBills();
	}
	
}