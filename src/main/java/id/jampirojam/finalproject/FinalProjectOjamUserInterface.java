package id.jampirojam.finalproject;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class FinalProjectOjamUserInterface {
	@RequestMapping("/")
	public String index() {
		return "redirect:swagger-ui.html";
	}

}