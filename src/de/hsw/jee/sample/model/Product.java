package de.hsw.jee.sample.model;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Product {

	private Long id;
	
	private String title;
	
	private Set<String> tags;
	
	private String description;
	
	private Date created;
	
	private Date modified;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Set<String> getTags() {
		return tags;
	}

	public void setTags(Set<String> tags) {
		this.tags = tags;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getModified() {
		return modified;
	}

	public void setModified(Date modified) {
		this.modified = modified;
	}

	public void prePersist() {
		this.created = new Date();
		this.preUpdate();
	}
	
	public void preUpdate() {
		this.modified = new Date();
	}

	public void update(Product product) {
		this.description = product.description;
		this.title = product.title;
		this.tags = new HashSet<>(product.tags);
	}

	public static Product create(String title, String desciption, String ... tags) {
		Product product = new Product();
		product.setTitle(title);
		product.setDescription(desciption);
		product.setTags(new HashSet<>(Arrays.asList(tags)));
		return product;
	}
	
}
