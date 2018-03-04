package de.hsw.jee.sample;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.jackson.JacksonFeature;

import de.hsw.jee.sample.api.GuestbookEntryEndpoint;

@ApplicationPath("/api")
public class RestConfiguration extends Application {
	
	@Override
	public Set<Class<?>> getClasses() {
	
        final Set<Class<?>> classes = new HashSet<Class<?>>();
        classes.add(JacksonFeature.class);
        classes.add(GuestbookEntryEndpoint.class);
        return classes;
    }

}
