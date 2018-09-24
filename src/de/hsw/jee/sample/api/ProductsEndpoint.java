package de.hsw.jee.sample.api;

import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
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

import de.hsw.jee.sample.config.ServiceFactory;
import de.hsw.jee.sample.model.Product;
import de.hsw.jee.sample.service.ProductService;

@ApplicationScoped
@Path("/products")
public class ProductsEndpoint {

	private final ProductService productService = ServiceFactory.getProductService();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response get(){
		return Response
				.ok(productService.findAll())
				.build();
	}
	
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response post(final Product product){
		return Response
				.ok(productService.save(product))
				.build(); 
	}
	
	@PUT
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response update(@PathParam("id") Long id, Product product){
		
		final Optional<Product> loaded = productService.findById(id);
		if (!loaded.isPresent()) {
			return Response.status(404).build();
		} else {
			loaded.get().update(product);
			productService.save(loaded.get());
			return Response.ok(loaded.get()).build();
		}
	}
	
	@DELETE
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response delete(@PathParam("id") Long id){
		final Optional<Product> loaded = productService.findById(id);
		if (!loaded.isPresent()) {
			return Response.status(404).build();
		} else {
			productService.delete(loaded.get());
			return Response.ok().build();
		}
	}

}
