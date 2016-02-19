package com.uga.wins.Controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.uga.wins.Entities.UgaWins;
import com.uga.wins.Exceptions.ESPNParserException;
import com.uga.wins.Utilities.ESPNScraper;

@Path("/uga")
public class UGAController {
	
	@GET
	@Path("/wins")
	@Produces(MediaType.APPLICATION_JSON)
	public Response doesUGAWin(){
		
		try {
			String gameOutCome = ESPNScraper.lastSeasonGameOutcome();
			return Response.ok()
					.entity(new Gson().toJson(new UgaWins(gameOutCome)))
					.build();
		} 
		catch (ESPNParserException e) {
			return Response.serverError()
					.entity(new Gson().toJson(new UgaWins(getMessageAndCausesMessages(e, "\n"))))
					.build();
		}
	}
	
	private static String getMessageAndCausesMessages(Throwable thro, String delim) {
		return String.valueOf(thro) + getCausesMessages(thro, delim);
	}

	private static String getCausesMessages(Throwable thro, String delim) {
		Throwable cause = thro.getCause();
		if (cause != null){
			return delim + "Cause=" + cause.toString()  // includes type and message
			+ getCausesMessages(cause, delim); /* Recursive call */
		}
		return "";
	}
}
