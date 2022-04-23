package com.user.management;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import model.customer;

@Path("/customer")

public class customerService {
	
	customer itemObj = new customer();
	
	//insert
		@POST
		@Path("/")
		@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
		@Produces(MediaType.TEXT_PLAIN)
		public String insertItem(@FormParam("AccountNumber") String AccountNumber,
		@FormParam("name") String name,
		@FormParam("NIC") String NIC,
		@FormParam("Phone") String Phone,
		@FormParam("Email") String Email)
		{
		String output = itemObj.insertItem(AccountNumber, name, NIC, Phone,Email);
		return output;
		

		}

}
