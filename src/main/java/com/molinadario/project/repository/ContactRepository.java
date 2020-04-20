package com.molinadario.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.molinadario.project.entity.Contact;

@Repository("contactRepository")
public interface ContactRepository extends JpaRepository<Contact, Long> {
 
	public Contact findById(long idContact);
}
