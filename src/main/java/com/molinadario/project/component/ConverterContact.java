package com.molinadario.project.component;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;
import com.molinadario.project.dto.ContactDTO;
import com.molinadario.project.entity.Contact;

@Component("converterContact")
public class ConverterContact {

	private static final Log LOG = LogFactory.getLog(ConverterContact.class);

	public Contact contactDtoToContact(ContactDTO contactDto) {

		LOG.info("METHOD contactDtoToContact --- PARAMETER= " + contactDto.toString());

		Contact contact = null;

		if (contactDto != null) {
			
			contact = new Contact();
            
			contact.setId(contactDto.getId());
			contact.setFirstName(contactDto.getFirstName());
			contact.setLastName(contactDto.getLastName());
			contact.setTelephone(contactDto.getTelephone());
			contact.setCity(contactDto.getCity());

			return contact;
		}

		return contact;
	}

	public ContactDTO contactToContactDto(Contact contact) {
		
		
		LOG.info("METHOD contactToContactDto --- PARAMETER= " + contact.toString());

		ContactDTO contactDto = new ContactDTO();

		if (contact != null) {
             
			contactDto.setId(contact.getId());
			contactDto.setFirstName(contact.getFirstName());
			contactDto.setLastName(contact.getLastName());
			contactDto.setTelephone(contact.getTelephone());
			contactDto.setCity(contact.getCity());

			return contactDto;
		}
		return null;
	}

}
