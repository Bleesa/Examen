package es.salesianos.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class BaseController {

	@GetMapping("/index")
	private String goToIndex() {
		return "index";
	}

	@GetMapping("/error")
	private String getErrorPage() {
		return "error";
	}
}