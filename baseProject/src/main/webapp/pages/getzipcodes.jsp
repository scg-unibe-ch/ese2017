<%@page import="java.util.Iterator"%>
<%@page import="ch.unibe.ese.team1.model.Location"%>
<%@page import="java.util.List"%>
<%@page import="ch.unibe.ese.team1.controller.service.GeoDataService"%>
<%@page
	import="org.springframework.web.servlet.support.RequestContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%
	ApplicationContext ac = RequestContextUtils
	.getWebApplicationContext(request);
	GeoDataService geoDataService = (GeoDataService) ac
	.getBean(GeoDataService.class);
	
	List<Location> zipCodes = geoDataService.getAllLocations();
	Iterator<Location> iterator = zipCodes.iterator();
	out.print("[");
	Location zip = iterator.next();
	out.print("\"" + zip.getZip() + " - " + zip.getCity() + "\"");
	while(iterator.hasNext()){
		zip = iterator.next();
		out.print(",\"" + zip.getZip() + " - " + zip.getCity() + "\"");
	}
	out.print("]");
%>