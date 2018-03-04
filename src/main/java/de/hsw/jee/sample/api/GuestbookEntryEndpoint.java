package de.hsw.jee.sample.api;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import de.hsw.jee.sample.model.GuestbookEntry;
import de.hsw.jee.sample.service.GuestbookService;

@ApplicationScoped
@Path("/guestbook/entries")
public class GuestbookEntryEndpoint {

	@Inject private GuestbookService guestbookService;

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		return Response
				.ok(guestbookService.findAll())
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(final GuestbookEntry entry){
		return Response
				.ok(guestbookService.save(entry))
				.build(); 
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, GuestbookEntry entry){
		
		final Optional<GuestbookEntry> loaded = guestbookService.findById(id);
		if (!loaded.isPresent()) {
			return Response.status(404).build();
		} else {
			GuestbookEntry e = loaded.get();
			e.setMessage(entry.getMessage());
			guestbookService.save(e);
			return Response.ok(e).build();
		}
		
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Long id){
		final Optional<GuestbookEntry> loaded = guestbookService.findById(id);
		if (!loaded.isPresent()) {
			return Response.status(404).build();
		} else {
			guestbookService.delete(loaded.get());
			return Response.ok().build();
		}
	}
	
}
