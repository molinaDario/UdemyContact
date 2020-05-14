package com.molinadario.project.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.molinadario.project.model.UserCredential;
import com.molinadario.project.view.ManagerView;

@Controller
public class LogginController {

	private static final Log LOG = LogFactory.getLog(LogginController.class);

	@GetMapping(path = { "/loggin", "/" })
	public String showLogginForm(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout, Model model) {

		LOG.info("METHOD: showLogginForm Parameter: Error: " + error + " , Logout: " + logout);

		model.addAttribute("error", error);
		model.addAttribute("logout", logout);

		return ManagerView.LOGIN;
	}

	@PostMapping(path = "/menu")
	public String logginCheck() {

		LOG.info("METHOD: logginCheck()");
		return "redirect:/contact/showAllContacts";
	}
}
