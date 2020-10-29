package com.amerisave.cma.resource;

import java.net.URI;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.amerisave.cma.advice.ApiMessage;
import com.amerisave.cma.exception.ContactNotFoundException;
import com.amerisave.cma.model.Contact;
import com.amerisave.cma.repository.ContactRepository;

@RestController
@RequestMapping(path = "/contacts")
public class ContactResource {

	private static final Logger logger = LoggerFactory.getLogger(ContactResource.class);

	@Autowired
	private ContactRepository contactRepository;

	@PostMapping
	@Transactional
	public ResponseEntity<Object> addContact(@Valid @RequestBody Contact contact) {
		Contact savedContact = contactRepository.save(contact);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(savedContact.getId()).toUri();

		logger.info(contact.toString() + " created");

		return ResponseEntity.created(location).build();
	}

	@GetMapping(path = "")
	public List<Contact> getAllContacts() {
		return contactRepository.findAll();
	}

	@GetMapping(path = "/{id}")
	public Contact getContactById(@PathVariable("id") Long id) {
		Optional<Contact> contact = contactRepository.findById(id);

		if (!contact.isPresent())
			throw new ContactNotFoundException("Contact not found for id: " + id);

		return contact.get();
	}

	@PutMapping(path = "/{id}")
	@Transactional
	public ResponseEntity<Object> updateContact(@Valid @RequestBody Contact contact, @PathVariable long id) {

		Optional<Contact> optionalContact = contactRepository.findById(id);

		if (!optionalContact.isPresent())
			throw new ContactNotFoundException("Contact not found for id: " + id);

		contact.setId(id);

		logger.info(optionalContact + " updated to " + contact);

		contactRepository.save(contact);

		return ResponseEntity.ok(new ApiMessage(new Date(), "Updated contact"));
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Object> deleteContact(@PathVariable long id) {

		contactRepository.deleteById(id);
		logger.info("Deleted contact with id " + id);
		return ResponseEntity.ok(new ApiMessage(new Date(), "Contact deleted"));
	}

}
