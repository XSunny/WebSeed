package org.sky.webseed.controller;


import org.sky.webcrawler.BaseThreadManager;
import org.sky.webcrawler.util.AppContext;
import org.sky.webseed.webcrawler.stockCrawler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

@Controller
@RequestMapping("/simple")
public class SimpleController {

	@RequestMapping("/hello")
	public String simple(Model model) {
		model.addAttribute("records", AppContext.getInstance().getInfoMap().entrySet());
		return "form";
	}

	@RequestMapping("/refresh")
	public  @ResponseBody String refresh(Model model) {
		BaseThreadManager crawler = new BaseThreadManager();
		for (int i = 600000; i < 600999; i++){
			stockCrawler xx = new stockCrawler();
			xx.id = i+"";
			String id = crawler.runCrawler(xx, null);
		}
		return "OK";
	}

}
