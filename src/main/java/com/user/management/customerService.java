package com.user.management;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

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
		
		//read
		@GET
		@Path("/")
		@Produces(MediaType.TEXT_HTML)
		public String readItems()
		{
		return itemObj.readItems();
		}
		
		//delete
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
		String output = itemObj.deleteItem(itemID);
		return output;
		}
		

}
