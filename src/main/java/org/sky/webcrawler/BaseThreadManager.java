package org.sky.webcrawler;

import java.util.*;


/*
 * 管理thread的运行，负责启动和管理各thread的开启，关闭
 * 
 * 知道CrawlerThread，并且知道Thread和class的对应关系
 * 
 * 知道 Task 但不关心其中细节，只是负责将Task 内 Thread 关系的字段传递给Thread，
 * 启动Thread之后，其他操作由Thread自己处理
 * 
 */

public class BaseThreadManager {

	Map<String, Thread> threadQueue = new Hashtable<String, Thread>();
	Map<String, CrawlerThread> crawlerQueue = new Hashtable<String, CrawlerThread>();
	
	/** 
	 * 代理执行 线程，并将加入到管理队列中
	 * @param target
	 * @return
	 */
	public String runCrawler(CrawlerThread target){
		return runCrawler(target, null);
	}
	
	
	/** 
	 * 代理执行 线程，并将加入到管理队列中
	 * @param target
	 * @param name
	 * @return
	 */
	public String runCrawler(CrawlerThread target ,String name){
		String threadName = name;
		if(threadName == null){
			//set default name
			threadName = getDefaultName(target);
		}
		Thread t = new Thread(target);
		String id = UUID.randomUUID().toString();
		t.start();
		threadQueue.put(id, t);
		crawlerQueue.put(id, target);
		target.setHandle(id);
		return id;
	}
	
	/**
	 * 
	 * 获得指定线程状态
	 * @param threadId
	 * @return
	 */
	public Map<String, String> getCrawlerStatus(String threadId){
		Map<String, String> status = new HashMap<String, String>();
		Thread t = threadQueue.get(threadId);
		status.put("name", t.getName());
		status.put("status", t.getState().toString());
		status.put("id", t.getId()+"");
		status.put("isAlive", t.isAlive()+"");
		return status;
	}
	
	/**
	 * 获取正在运行中的线程数
	 */
	public int getRunningThread(){
		clean();
		return threadQueue.size();
	}

	/**
	 * 停止指定 threadId 的线程
	 * @param threadId
	 */
	public void stopCrawler(String threadId){
		Thread t = threadQueue.get(threadId);
		if(t == null){
			return;
		}
		if(t.isAlive()){
			t.interrupt();
			crawlerQueue.get(threadId).cleanUp();
			t.stop();
		}
	}
	
	/**
	 * 清理线程相关资源，方便jvm回收死亡进程的资源
	 */
	public void clean(){
		List<String> keys = new ArrayList<String>();
		for(String key : threadQueue.keySet()){
			Thread t = threadQueue.get(key);
			if(!t.isAlive()){
				keys.add(key);
			}
		}
		for(String id : keys){
			threadQueue.remove(id);
			crawlerQueue.remove(id);
		}
	}
	
	
	/**
	 * 得到指定的thread 引用
	 * @param id
	 * @return
	 */
	protected Thread getThread(String id){
		return threadQueue.get(id);
	}

	/**
	 * 返回默认的线程名称
	 * @param target
	 * @return
	 */
	private String getDefaultName(Runnable target) {
		return target.toString();
	}

	
	public static void main(String[] args){
		
		System.out.println("main start");
		BaseThreadManager crawler = new BaseThreadManager();
		XXXThread xx = new XXXThread();
		String id = crawler.runCrawler(xx, null);	
		System.out.println(crawler.getCrawlerStatus(id));
		try {
			
			crawler.getThread(id).join(3000);
			System.out.println("now I want to stop the thread!");
			crawler.stopCrawler(id);	
			System.out.println(crawler.getCrawlerStatus(id));
			crawler.getThread(id).join();
			System.out.println(crawler.getCrawlerStatus(id));
			System.out.println("main end");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
}

class XXXThread extends CrawlerThread{

	@Override
	protected void cralwer() {
		for(int i = 0; i < 5; i++){
			try {
				Thread.sleep(1000);
				System.out.println( "counting ..." +i);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	protected void clean() {
		
	}



}


