package org.sky.webcrawler.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class HttpRequestUtil {
	
	public static String httpRequest(String url, String cookie, String hostname) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(url);

		if(!"".equals(hostname)){
			HttpHost proxy = new HttpHost(hostname, 80, "http"); 
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
			httpGet.setConfig(config);
		}
		
		if(!"".equals(cookie)){
			httpGet.addHeader("Cookie",cookie);
		}
		
		httpGet.addHeader("max-age",String.valueOf(Integer.MAX_VALUE));
		httpGet.addHeader("Accept", "text/html,application/xhtml+xm	l,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,es;q=0.4");
		httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
		try {			
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity,"UTF-8");							
				return content;
			}
		} catch (IOException e) {
					
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	public static String httpRequest(String url, String cookie, String hostname, int port) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(url);

		if(!"".equals(hostname)){
			HttpHost proxy = new HttpHost(hostname, port, "http"); 
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
			httpGet.setConfig(config);
		}
		
		if(!"".equals(cookie)){
			httpGet.addHeader("Cookie",cookie);
		}
		
		httpGet.addHeader("max-age",String.valueOf(Integer.MAX_VALUE));
		httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,es;q=0.4");
		httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
		try {			
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity,"UTF-8");							
				return content;
			}
		} catch (IOException e) {
					
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	public static String httpRequestDeCode(String url, String cookie,String hostname,String code) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(url);

		if(!"".equals(hostname)){
			HttpHost proxy = new HttpHost(hostname, 80, "http"); 
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
			httpGet.setConfig(config);
		}
		
		if(!"".equals(cookie)){
			httpGet.addHeader("Cookie",cookie);
		}
		
		httpGet.addHeader("max-age",String.valueOf(Integer.MAX_VALUE));
		httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,es;q=0.4");
		httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
		try {			
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity,code);							
				return content;
			}
		} catch (IOException e) {
					
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	public static String httpRequest(String url, String cookie,String hostname,String port) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpGet httpGet = new HttpGet(url);
		;
		if(!"".equals(hostname)){
			HttpHost proxy = new HttpHost(hostname, Integer.parseInt(port), "http"); 
			
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).setConnectTimeout(10000).setConnectionRequestTimeout(10000).setSocketTimeout(10000).build(); 
//	        config = RequestConfig.custom()  
//	        	    .setConnectionRequestTimeout(3000).setConnectTimeout(3000)  
//	        	    .setSocketTimeout(3000).build();  
			httpGet.setConfig(config);
			
		}
		
		if(!"".equals(cookie)){
			httpGet.addHeader("Cookie",cookie);
		}
		
		httpGet.addHeader("max-age",String.valueOf(Integer.MAX_VALUE));
		httpGet.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpGet.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpGet.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,es;q=0.4");
		httpGet.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
		try {			
			HttpResponse httpResponse = closeableHttpClient.execute(httpGet);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity,"UTF-8");							
				return content;
			}
		} catch (IOException e) {
					
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
	
	
	public static String httpRequestPost(String url, String cookie,String hostname,Map<String,String> param) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(url);

		List <NameValuePair> params = new ArrayList<NameValuePair>();  
		for (Map.Entry<String ,String> entry : param.entrySet()) {
			   String key = entry.getKey().toString();
			   String value = entry.getValue().toString();
//			   System.out.println("key=" + key + " value=" + value);
			   params.add(new BasicNameValuePair(key, value));
		}
		
		if(!"".equals(hostname)){
			HttpHost proxy = new HttpHost(hostname, 80, "http"); 
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
	        httpPost.setConfig(config);
		}
//		
		if(!"".equals(cookie)){
			httpPost.addHeader("Cookie",cookie);
		}
		httpPost.addHeader("max-age",String.valueOf(Integer.MAX_VALUE));
		httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.addHeader("Accept-Encoding", "gzip,deflate,sdch");
		httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,es;q=0.4");
		httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
		try {			
			httpPost.setEntity(new UrlEncodedFormEntity(params,"UTF-8"));  
			
			HttpResponse httpResponse = closeableHttpClient.execute(httpPost);
			HttpEntity entity = httpResponse.getEntity();
			if (entity != null) {
				String content = EntityUtils.toString(entity,"UTF-8");							
				return content;
			}
		} catch (IOException e) {
					
		} finally {
			try {
				closeableHttpClient.close();
			} catch (IOException e) {
			}
		}
		return null;
	}
	
//	public static String getCookie(String url,String hostip ,String port){
////	    org.apache.commons.logging.impl.Jdk14Logger logger = (Jdk14Logger) LogFactory.getLog("com.gargoylesoftware.htmlunit");
////		logger.getLogger().setLevel(Level.OFF);
//		String cookie = "";
//		WebClient webClient = null;
//		if(!"".equals(hostip)){
//			webClient = new WebClient(BrowserVersion.CHROME,hostip,Integer.parseInt(port));
//		}else{
//			webClient = new WebClient(BrowserVersion.CHROME);
//		}
//		webClient.getOptions().setTimeout(80000);
//		webClient.getOptions().setThrowExceptionOnScriptError(false);
//		webClient.getOptions().setJavaScriptEnabled(true);
//	    webClient.getOptions().setCssEnabled(true);
//	    webClient.getOptions().setActiveXNative(true);
//	    webClient.setAjaxController(new NicelyResynchronizingAjaxController());
//	
//	    try {
//			HtmlPage htmlPage = webClient.getPage(url);
//		} catch (FailingHttpStatusCodeException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (MalformedURLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	    Set<Cookie> c = webClient.getCookieManager().getCookies();
//	       Iterator<Cookie> it=c.iterator();
//			while (it.hasNext()) {
//				Cookie st = it.next();				
//				cookie += st.getName() + "=" + st.getValue() + ";";
//			}
//				
//		return cookie;
//	}
}
