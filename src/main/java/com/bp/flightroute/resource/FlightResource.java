package com.bp.flightroute.resource;

import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import java.io.InputStream;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.StringUtil;

import com.sun.jersey.multipart.FormDataParam;
import com.bp.flightroute.control.FlightController;
import com.bp.flightroute.model.FlatFlight;

@Path("flights")
@Produces(MediaType.APPLICATION_JSON)
public class FlightResource {

	@GET
	@Consumes(MediaType.APPLICATION_JSON)
	public Response getFlights(@QueryParam("start")String start, @QueryParam("end")String end) {
		
		if(StringUtil.isNotBlank(start) && StringUtil.isNotBlank(end)) {
			return Response.ok(FlightController.search(start, end)).build();
		} else if(StringUtil.isBlank(start) && StringUtil.isBlank(end)) {
			return Response.ok(FlightController.getAllFlights()).build();
		} else {
			return Response.status(HttpStatus.BAD_REQUEST_400).build();
		}
	}
	
	@POST
	@Consumes(MediaType.APPLICATION_JSON)
	public Response postFlight(FlatFlight flight) {
		
		FlightController.postFlight(flight);
		return Response.status(HttpStatus.CREATED_201).build();
	}
	
	@POST
	@Path("import")
	@Consumes(MediaType.MULTIPART_FORM_DATA)
	public Response bulkUpload(@FormDataParam("cvs") InputStream cvs) {
		
		FlightController.bulkUpload(cvs);
		return Response.status(HttpStatus.CREATED_201).build();
	}
}