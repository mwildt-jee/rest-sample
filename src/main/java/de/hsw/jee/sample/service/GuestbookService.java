
package de.hsw.jee.sample.service;

import java.util.List;
import java.util.Optional;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import de.hsw.jee.sample.model.GuestbookEntry;
import de.hsw.jee.sample.repository.GuestbookRepository;
import de.hsw.jee.sample.repository.Mock;

@ApplicationScoped
public class GuestbookService {

	@Inject @Mock  
	private GuestbookRepository guestbookRepository;

	public List<GuestbookEntry> findAll() {
		return guestbookRepository.findAll();
	}

	public void create(final String author, final String message) {
		final GuestbookEntry entry = new GuestbookEntry();
		
		entry.setAuthor(author);
		entry.setMessage(message);
		
		guestbookRepository.save(entry);
	}

	public GuestbookEntry save(GuestbookEntry entry) {
		return guestbookRepository.save(entry);
	}
	
	public void delete(GuestbookEntry entry) {
		guestbookRepository.delete(entry);
	}

	public Optional<GuestbookEntry> findById(Long id) {
		return guestbookRepository.findById(id);
	}
	
}
