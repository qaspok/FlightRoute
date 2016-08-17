package com.bp.flightroute;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

import com.bp.flightroute.database.FlightDatabase;
import com.bp.flightroute.database.GreetingsDatabase;
import com.bp.flightroute.resource.FlightResource;
import com.bp.flightroute.resource.GreetingResource;

/**
 * Standalone Executable JAR for Demos
 * @author piercb2
 */
public class Application {

	/**
	 * Application entrypoint
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		
		// Setup Derby in-memory database
		GreetingsDatabase.init();
		FlightDatabase.init();
		
		// Setup Jetty web server
		ServletContextHandler servletContextHandler = new ServletContextHandler(ServletContextHandler.SESSIONS);
		servletContextHandler.setContextPath("/");
		servletContextHandler.setResourceBase(Application.class.getClassLoader().getResource("root").toExternalForm());

		Server server = new Server(8080);
		server.setHandler(servletContextHandler);

		// Jersey servlet for serving up API content
		ServletHolder jerseyServlet = servletContextHandler.addServlet(org.glassfish.jersey.servlet.ServletContainer.class, "/api/*");
		jerseyServlet.setInitOrder(0);
		jerseyServlet.setInitParameter("jersey.config.server.provider.classnames", GreetingResource.class.getCanonicalName() + "," + FlightResource.class.getCanonicalName());

		// Default servlet for serving up static content
		ServletHolder httpServlet = servletContextHandler.addServlet(org.eclipse.jetty.servlet.DefaultServlet.class, "/*");
		httpServlet.setInitOrder(1);

		// Start the show
		try {
			server.start();
			server.join();
		} finally {
			server.destroy();
		}
	}
}