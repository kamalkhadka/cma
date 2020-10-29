package com.amerisave.cma.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amerisave.cma.model.Contact;

@Repository
public interface ContactRepository extends JpaRepository<Contact, Long> {

}
