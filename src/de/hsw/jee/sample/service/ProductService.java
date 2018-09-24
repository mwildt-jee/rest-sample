package de.hsw.jee.sample.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicLong;

import de.hsw.jee.sample.model.Product;

/**
 * Service-Klasse für den Zugriff auf CRUD Methoden für Produkte.
 * 
 * @author mwildt
 *
 */
public class ProductService {

	private final Map<Long, Product> data = new HashMap<>();
	private final AtomicLong sequence = new AtomicLong();
	
	/**
	 * Factory-Methode zum erzeugen einer neuen Service-Instanz mit Testdaten.
	 * @return
	 */
	public static ProductService withTestdata() {
		final ProductService service =  new ProductService();
		service.save(Product.create("Titel", "Beschreibung", "Test", "Demo"));
		service.save(Product.create("Titel N", "Noch eine Beschreibung", "Irrelevent"));
		return service;
	}
	
	/**
	 * List alle Produkte
	 * @return
	 */
	public List<Product> findAll() {
		return Collections.unmodifiableList(new ArrayList<>(data.values()));
	}
	
	/**
	 * erzeugt die Id für das nächste Produkt.
	 * @return
	 */
	private Long nextId() {
		return sequence.incrementAndGet();
	}
	
	/**
	 * Speichern oder Update eines Produktes. Beim Speichern wird eine Id vergebem (siehe nextid)
	 * @param product
	 * @return
	 */
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
	
	/**
	 * Liest ein Produkt anhand der Id.
	 * @param id
	 * @return
	 */
	public Optional<Product> findById(final Long id) {
		if(data.containsKey(id)) {
			return Optional.of(data.get(id));
		} else {
			return Optional.empty();
		}
	}

	/**
	 * Löscht ein Produkt.
	 * @param product
	 */
	public void delete(Product product) {
		this.data.remove(product.getId());
	}
	
}
