package de.hsw.jee.sample.config;

import de.hsw.jee.sample.service.ProductService;

public class ServiceFactory {
	
	public static final BeanHolder<ProductService> productService = BeanHolder.of(ProductService::withTestdata);

	public static ProductService getProductService() {
		return productService.get();
	}
	
}
