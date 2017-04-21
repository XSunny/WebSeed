package org.sky.webseed.controller;


import org.sky.webcrawler.BaseThreadManager;
import org.sky.webcrawler.util.AppContext;
import org.sky.webcrawler.stockCrawler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

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
		List<FutureTask> results = new ArrayList<>();
		for (int i = 600000; i < 600999; i++){
			stockCrawler xx = new stockCrawler();
			xx.id = i+"";
			FutureTask id = crawler.runCrawler(xx, null);
			results.add(id);

		}
		for (FutureTask future: results) {
			try {
				future.get();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} catch (ExecutionException e) {
				e.printStackTrace();
			}
		}
		return "OK";
	}

}
