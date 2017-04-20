package org.sky.webcrawler.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;

public class HttpRequestPostUtil {
	public static String httpRequest(String url, String cookie,String hostname, List<NameValuePair > parms) {
		HttpClientBuilder httpClientBuilder = HttpClientBuilder.create();
		CloseableHttpClient closeableHttpClient = httpClientBuilder.build();
		HttpPost httpPost = new HttpPost(url);

		if(!"".equals(hostname)){
			HttpHost proxy = new HttpHost(hostname, 80, "http"); 
	        RequestConfig config = RequestConfig.custom().setProxy(proxy).build();  
	        httpPost.setConfig(config);
		}
		
		if(!"".equals(cookie)){
			httpPost.addHeader("Cookie",cookie);
		}
		
		if(parms != null){
			try {
				httpPost.setEntity(new UrlEncodedFormEntity(parms, HTTP.UTF_8));
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
		}
		httpPost.addHeader("max-age",String.valueOf(Integer.MAX_VALUE));
		httpPost.addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,image/webp,*/*;q=0.8");
		httpPost.addHeader("Accept-Encoding", "gzip,deflate");
		httpPost.addHeader("Accept-Language", "zh-CN,zh;q=0.8,en;q=0.6,es;q=0.4");
		httpPost.addHeader("User-Agent","Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/35.0.1916.153 Safari/537.36");
		try {			
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
}
