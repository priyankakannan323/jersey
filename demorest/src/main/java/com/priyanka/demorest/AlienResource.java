package com.priyanka.demorest;

import javax.servlet.http.HttpServletResponse;
//import javax.annotation.security.RolesAllowed;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import java.io.IOException;

//import com.project.portal.Record;

import java.util.*;
//import com.priyanka.demorest
@Path("aliens")
public class AlienResource {
	alienRepository repo = new alienRepository() ;
	portalRepo rep = new portalRepo();
	@GET
	@Produces({MediaType.APPLICATION_JSON,MediaType.APPLICATION_XML})
	public List<Alien> getAlienResource() {
		return repo.getAliens();
	}
	@POST
	@Path("alien")
	@Consumes(MediaType.APPLICATION_XML)
	public String createAlien(Alien a) {
		if(repo.create(a)==1)
		return "success";
		else return "fail";
	}
	@POST
	@Path("delalien/{id}")
	@Consumes(MediaType.APPLICATION_XML)
	public String createAlien(@PathParam("id") String id) {
		if(repo.delete(id)==1)
		return "success";
		else return "fail";
	}
	@GET
	@Path("alien/{id}")
	@Produces({MediaType.APPLICATION_XML,MediaType.APPLICATION_JSON})
	public Alien getAlien(@PathParam("id") int id) {
		return repo.getAlien(id);
	}
	//@RolesAllowed("ADMIN")
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/records")
    public List<Record> getAllRecords() 
    {
        return rep.getAllRecord();
    }
	@Path("/login")
    @POST
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void login(
            @FormParam("Username") String uname,
            @FormParam("Password") String pass,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.isAuthenticated(uname, pass)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/successpage.jsp");
		}
		else {
		servletResponse.sendRedirect("http://localhost:8080/demorest/index.jsp");
		}
	}
	@Path("/adminlog")
    @POST
    @Produces("text/plain")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void adminlogin(
            @FormParam("Username") String uname,
            @FormParam("Password") String pass,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.adminAuthenticated(uname, pass)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/adminPortal.jsp");
		}
		else {
		servletResponse.sendRedirect("http://localhost:8080/demorest/index.jsp");
		}
	}
	@Path("/specific")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Record getRecord(
            @FormParam("customerId") String custId,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		return rep.getUserRecord(custId);
	}
	@Path("/specificDel")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public void deleteRecord(
            @FormParam("customerId") String custId,
            @Context HttpServletResponse servletResponse
    ) throws IOException,RuntimeException {
		if(rep.deleteUserRecord(custId)) {
			servletResponse.sendRedirect("http://localhost:8080/demorest/successpage.jsp");
		}
		else {
			servletResponse.sendRedirect("http://localhost:8080/demorest/errorpage.jsp");
		}
	}
	
}
