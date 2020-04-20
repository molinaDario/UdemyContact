package com.molinadario.project.service;

import java.util.List;
import com.molinadario.project.dto.ContactDTO;
import com.molinadario.project.entity.Contact;

public interface ContactService {

	public ContactDTO newContact(ContactDTO contactDto);

	public List<ContactDTO> showAllContact();

	public ContactDTO findContactId(long idContact);

	public ContactDTO deleteContact(long idContact);
	
	
	public ContactDTO updateContact(long idContact);
}
