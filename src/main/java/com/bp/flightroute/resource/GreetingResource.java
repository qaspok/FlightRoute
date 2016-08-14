package com.bp.flightroute.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import com.bp.flightroute.database.GreetingsDatabase;

@Path("greeting")
public class GreetingResource {

	@GET
	public Response getGreeting() {
		
		return Response.ok(GreetingsDatabase.getGreeting()).build();
	}
}
