package de.hsw.jee.sample.config;

import java.util.function.Supplier;

public class BeanHolder<T> {

	private final Supplier<T> factory;
	private T instance;
	
	private BeanHolder(Supplier<T> factory) {
		this.factory = factory;
	}
	
	public static <R> BeanHolder<R> of(Supplier<R> factory) {
		return new BeanHolder<R>(factory);
	}

	public T get() {
		if(instance == null) {
			instance = factory.get();
		}
		return instance;
	}
}
