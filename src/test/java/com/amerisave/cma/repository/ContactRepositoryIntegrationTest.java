package com.amerisave.cma.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import com.amerisave.cma.model.Contact;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ContactRepositoryIntegrationTest {

	@Autowired
	private TestEntityManager entityManager;

	@Autowired
	private ContactRepository contactRepository;

	@Test
	public void whenFindById_thenReturnContact() {
		// given
		Contact contact = new Contact();
		contact.setFirstName("Kamal");
		contact.setLastName("Khadka");
		contact.setEmail("kml.kdk@gmail.com");
		entityManager.persist(contact);
		entityManager.flush();

		// when
		Optional<Contact> found = contactRepository.findById(1L);

		// then
		assertThat(found.get().getFirstName()).isEqualTo(contact.getFirstName());
	}
}
