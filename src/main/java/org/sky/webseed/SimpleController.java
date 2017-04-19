package org.sky.webseed;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/simple")
public class SimpleController {

	@RequestMapping("/hello")
	public @ResponseBody String simple() {
		return "Hello world!";
	}

}
