package org.sky.webcrawler;

import org.sky.webcrawler.BaseThreadManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/*
 * 位于Task任务管理的顶层，
 * 
 * Has a ThreadManager ，并将Thread 管理托管给 该Manager
 * 
 * 了解Task， 用Task 来标示和管理当前系统内的任务列表
 * 
 * 了解 DataSourceList 获得合法的数据源名称和标示
 */
public class TaskManager {
	
	Map<String, List<String>> taskDetail;  //key TaskId, value ThreadIds;
	Map<String, String> runningQueue;         // key TaskId, value status;
	Map<String, Map<String,String>> waitQueue;         // key TaskId, value task;
	BaseThreadManager threadmanager;
	
	int maxTaskCount;

	//外部向Manager提交任务
	public String submitTask(Map<String, String> task, List<String> dataSources){
		if(runningQueue.size() < maxTaskCount){
			runTask(task, dataSources);
			runningQueue.put(task.get("id"), "runing");
		}else{
			waitQueue.put(task.get("id"), task);
		}
		return null;
	}
	
	
	//向CrawlerManager提交任务 设置需要的属性
	private String runTask(Map<String, String> task, List<String> dataSources){
		for(String dataSource : dataSources){
			//
			distribute(dataSource, task);
		}
		return task.get("id");
	}

	private void distribute(String source, Map<String, String> task){
//		String id = null;
//		switch(source){
//		case DataSource.WEIBO:
//			id = threadmanager.runCrawler(new WeiboContent(task), null);
//			register(task.get("id"), id);
//			break;
//		case DataSource.TIEBA:
//			id = threadmanager.runCrawler(new WeiboContent(task), null);
//			register(task.get("id"), id);
//			break;
//		case DataSource.TIANYA:
//			id = threadmanager.runCrawler(new WeiboContent(task), null);
//			register(task.get("id"), id);
//			break;
//		case DataSource.PCAUTO:
//			id = threadmanager.runCrawler(new WeiboContent(task), null);
//			register(task.get("id"), id);
//			break;
//			
//		}
	}
	
	
	private void register(String taskId, String threadId){
		List<String> threads = taskDetail.get(taskId);
		if(threads == null){
			threads = new ArrayList<String>();
			threads.add(threadId);
			taskDetail.put(taskId, threads);
		}
		else{
			threads.add(threadId);
		}
	}
	
	//管理任务， 获取任务状态
	public String getTaskStates(String taskId){
		if(runningQueue.get(taskId) != null){
			return "Running";
		}
		if(waitQueue.get(taskId) != null){
			return "Waiting";
		}
		return "Not Found";
	}
	
	//取消 或者 结束任务
	public void cancelTask(String taskId){
		List<String> threadIds = taskDetail.get(taskId);
		for(String id : threadIds){
			threadmanager.stopCrawler(id);
		}
	}
}
