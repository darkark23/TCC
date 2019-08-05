package br.net.iesb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MainController {

	
	@RequestMapping("/home")
	public String home() {
		return "index";
	}


}
