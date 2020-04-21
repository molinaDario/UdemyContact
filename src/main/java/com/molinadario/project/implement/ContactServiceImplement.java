package com.molinadario.project.implement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.molinadario.project.component.ConverterContact;
import com.molinadario.project.dto.ContactDTO;
import com.molinadario.project.entity.Contact;
import com.molinadario.project.repository.ContactRepository;
import com.molinadario.project.service.ContactService;

@Service("contactServiceImplement")
public class ContactServiceImplement implements ContactService {

	private static final Log LOG = LogFactory.getLog(ContactServiceImplement.class);

	@Autowired
	@Qualifier("contactRepository")
	private ContactRepository contactRepository;

	@Autowired
	@Qualifier("converterContact")
	private ConverterContact converteContact;

	@Override
	public ContactDTO newContact(ContactDTO contactDto) {

		LOG.info("METHOD newContact --PARAMETER: " + contactDto.toString());

		Contact contact = contactRepository.save(converteContact.contactDtoToContact(contactDto));

		return converteContact.contactToContactDto(contact);
	}

	@Override
	public List<ContactDTO> showAllContact() {

		LOG.info("METHOD showAllContact");

		List<Contact> listContact = contactRepository.findAll();

		Collections.sort(listContact);

		List<ContactDTO> listContactDTO = new ArrayList<>();

		for (Contact contact : listContact) {

			listContactDTO.add(converteContact.contactToContactDto(contact));
		}

		return listContactDTO;
	}

	@Override
	public ContactDTO findContactId(long idContact) {

		LOG.info("METHOD findContactId _--PARAM: " + idContact);

		Contact contact = contactRepository.findById(idContact);

		return converteContact.contactToContactDto(contact);

	}

	@Override
	public ContactDTO deleteContact(long idContact) {

		LOG.info("METHOD deleteContact --PARAM: " + idContact);

		Contact contact = contactRepository.findById(idContact);

		ContactDTO contactDto = converteContact.contactToContactDto(contact);

		if (contact != null) {
			contactRepository.delete(contact);
		}

		return contactDto;
	}

}
