package org.sky.webseed.controller;


import org.sky.webcrawler.BaseThreadManager;
import org.sky.webcrawler.util.AppContext;
import org.sky.webcrawler.stockCrawler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

@Controller
@RequestMapping("/simple")
public class SimpleController {

	@RequestMapping("/hello")
	public String simple(Model model, WebRequest request) {
		String page = request.getParameter("pageNum");
		String size = request.getParameter("pageSize");
		int pageNum = 1;
		int pageSize = 10;
		try {
			if(page != null){
				pageNum = Integer.parseInt(page);
			}
			if (size != null){
				pageSize = Integer.parseInt(size);
			}
		}
		catch (Exception e){
			e.printStackTrace();
		}
		//check pageNum pageSize
		int offset = pageSize* (pageNum-1);

		Set<Map.Entry<String, Map<String, String>>> info = AppContext.getInstance().getInfoMap().entrySet();
		Map<String,Map<String,String>> result = new HashMap<>();
		Iterator<Map.Entry<String, Map<String, String>>> iterator = info.iterator();
		for (int j = 0; j < offset && j< info.size()-pageSize; j++){
			iterator.next();
		}
		for (int i = offset; i < pageNum* pageSize; i++) {
			Map.Entry<String, Map<String, String>>entry = iterator.next();
			String key = entry.getKey();
			Map value = entry.getValue();
			result.put(key, value);
		}
		model.addAttribute("records", result);
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
