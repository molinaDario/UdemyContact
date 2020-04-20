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

	@GetMapping(path = "/")
	public String redirectToLoggin() {
		LOG.info("METHOD: redirectToLoggin");

		return "redirect:/loggin";
	}

	@GetMapping(path = "/loggin")
	public String showLogginForm(@RequestParam(name = "error", required = false) String error,
			@RequestParam(name = "logout", required = false) String logout, Model model) {

		LOG.info("METHOD: showLogginForm Parameter: Error: " + error + " , Logout: " + logout);

		model.addAttribute("userCredential", new UserCredential());
		model.addAttribute("error", error);
		model.addAttribute("logout", logout);

		return ManagerView.LOGIN;
	}

	@PostMapping(path = "/logginCheck")
	public String logginCheck(@ModelAttribute(name = "userCredential") UserCredential userCredential) {

		LOG.info("METHOD: logginCheck Paremeter: userCredential " + userCredential);

		if (userCredential.getUser().equalsIgnoreCase("root") && userCredential.getPass().equalsIgnoreCase("root")) {
			return "redirect:/contact/showAllContacts";
		} else {
			return "redirect:/loggin?error";
		}
	}
}
