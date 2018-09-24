
package de.hsw.jee.sample.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import de.hsw.jee.sample.model.Product;

public class ProductService {

	private final Map<Long, Product> data = new HashMap<>();
	private final AtomicLong sequence = new AtomicLong();
	
	public static ProductService withTestdata() {
		final ProductService service =  new ProductService();
		service.save(Product.create("Titel", "Beschreibung", "Test", "Demo"));
		service.save(Product.create("Titel N", "Noch eine Beschreibung", "Irrelevent"));
		return service;
	}
	
	public List<Product> findAll() {
		return Collections.unmodifiableList(new ArrayList<>(data.values()));
	}
	
	private Long nextId() {
		return sequence.incrementAndGet();
	}
	
	public Product save(final Product product) {
		if (product.getId() == null) {
			product.setId(this.nextId());
			product.prePersist();
		} else {
			product.preUpdate();
		}
		this.data.put(product.getId(), product);
		return product;
	}
	
	public Optional<Product> findById(final Long id) {
		if(data.containsKey(id)) {
			return Optional.of(data.get(id));
		} else {
			return Optional.empty();
		}
	}

	public void delete(Product product) {
		this.data.remove(product.getId());
	}
	
}
