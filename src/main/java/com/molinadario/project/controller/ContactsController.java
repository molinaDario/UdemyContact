package com.molinadario.project.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import com.molinadario.project.dto.ContactDTO;
import com.molinadario.project.service.ContactService;
import com.molinadario.project.view.ManagerView;

@Controller
@RequestMapping(path = "/contact")
public class ContactsController {

	private static final Log LOG = LogFactory.getLog(ContactsController.class);

	@Autowired
	@Qualifier("contactServiceImplement")
	private ContactService contactService;

	@GetMapping(path = "/addForm")
	public String addForm(@RequestParam(name = "id", required = false) Long id, Model model) {

		LOG.info("METHOD: addForm");

		long idContact = 0;

		if (id != null) {
			idContact = id.longValue();

			model.addAttribute("newContact", contactService.findContactId(idContact));

		} else {

			model.addAttribute("newContact", new ContactDTO());
		}

		return ManagerView.CONTACT_FORM;
	}

	@GetMapping(path = "/cancel")
	public String buttonCancel() {

		LOG.info("METHOD: buttonCancel");

		return "redirect:/contact/showAllContacts";
	}

	@PostMapping(path = "/addContact")
	public String addContact(@ModelAttribute(name = "newContact") ContactDTO contactDto, Model model) {

		LOG.info("METHOD: addContact PARAMETER: " + contactDto.toString());

		if (contactService.newContact(contactDto) != null) {
			
			model.addAttribute("result", 1);
			
			return "redirect:/contact/showAllContacts";
			
		} else {
			
			model.addAttribute("result", 0);
			
			return ManagerView.CONTACT_FORM;
		}

	}

	@GetMapping(path = "/showAllContacts")
	public ModelAndView showAllContacts() {

		LOG.info("METHOD showAllContacts");

		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("allContact", contactService.showAllContact());

		modelAndView.setViewName(ManagerView.CONTACTS);

		return modelAndView;
	}

	@GetMapping(path = "/deleteContact")
	public ModelAndView deleteContact(@RequestParam(name = "id", required = true) long id) {

		LOG.info("METHOD deleteContact -- PARAM:" + id);

		ModelAndView modelAndView = new ModelAndView();

		ContactDTO contactDto = contactService.deleteContact(id);

		modelAndView.setViewName("redirect:/contact/showAllContacts");

		LOG.info("contactDto " + contactDto.toString());

		return modelAndView;
	}

}
