package org.sky.webcrawler;

import java.util.Map;
import java.util.concurrent.Callable;


public abstract class CrawlerThread implements Callable<String>{
	
	//task info
	Map<String,Object> taskInfo;
	
	//the handle of thread
	String handle;


	public final String call() {
		try{
			cralwer();
		}
		catch(Exception e){
			//getTaskService().threadFinish(handle, "Exception");//
			clean();
		}
		finish();
		return "OK";
	}

	public Map<String, Object> getTaskInfo() {
		return taskInfo;
	}

	public void setTaskInfo(Map<String, Object> taskInfo) {
		this.taskInfo = taskInfo;
	}


	public void setHandle(String handle) {
		this.handle = handle;
	}

	
	private void finish() {
		//getTaskService().threadFinish(handle, "success");
	}
	
	public void cleanUp(){
		clean();
	}


	/**
	 * 用于用户中断线程时，需要进行的一些清理工作, 在管理器终止该线程前，会执行该方法， 如果没有需要清理的内容，可以留空
	 */
	protected abstract void clean();

	protected abstract void cralwer();
	
	
}
