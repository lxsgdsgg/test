package com.bashiju.quartz.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpClientUtil {
	public static String getResult(String url,String charset) throws Exception {
        try (CloseableHttpClient httpClient = HttpClientBuilder.create().build();
                CloseableHttpResponse response = httpClient.execute(new HttpGetConfig(url))) {
            String result = EntityUtils.toString(response.getEntity(),charset);
            return result;
        } catch (Exception e) {
            System.out.println("获取失败:"+e.getMessage());
            return null;
        }
    }

	 public String postForm(String url, Map<String, String> content, String charset) throws IOException {
	        HttpPost post = new HttpPost(url);
	        List<BasicNameValuePair> values = new ArrayList<>();
	        for (Map.Entry<String, String> entry : content.entrySet()) {
	            values.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
	        }
	        HttpEntity entity= new UrlEncodedFormEntity(values, "utf-8");
//	        StringEntity entity = new StringEntity(content, ContentType.create("application/json",charset));
	        post.setEntity(entity);
	        post.setHeader("Connection","close");
	        CookieStore cookieStore = new BasicCookieStore();
	        try (CloseableHttpClient httpClient = HttpClientBuilder.create().setDefaultCookieStore(cookieStore).build();
	                CloseableHttpResponse response = httpClient.execute(post)) {
	        	
	            String result = EntityUtils.toString(response.getEntity(),charset);
	            List<Cookie> cookies = cookieStore.getCookies();
	            for (int i = 0; i < cookies.size(); i++) {
	            	System.out.println("name:"+cookies.get(i).getName()+";value:"+cookies.get(i).getValue());
	            }
	            return result;
	        } catch (Exception e) {
	            System.out.println("获取失败");
	            return null;
	        }
	    }
	 public static void main(String[] args) throws Exception {
		 String url="https://jxjump.58.com/service?target=FCADV8oV3os7xtAhI2suhvPnTEAKOf2uvijIFVDSn9cin86PZpk1zEffDjpc6uQhhfOQnEtf3gzeNV-hxIuaoznpUe4T88HWWf28Ux_7k2_EqtKoHF0kI75_JOB1CiheMag7mpM3b6VbDKBcdf371QuxZsUopG7U9Axjnr-QuiAazy31scVtc3ttHtxtnpsMu8dyIj5MLSZyLT1jEWLqFbOOLVymq4vSLM-9VmKVx650sLQ7xRN2R4nm8iH3x85li7Vlr&pubid=53352426&apptype=0&psid=151539578202511131829533630&entinfo=36452934299799_0&cookie=%7C%7C%7C&fzbref=1&key=&params=busitime^desc&cookie=%7C%7C%7C&fzbref=1&key=";
		String param=url.substring(url.indexOf("?")+1);
		param=URLEncoder.encode(param, "UTF-8");
		url=url.substring(0,url.indexOf("?")+1);
		url=url+param;
		//url=url.replaceAll(" ", "%20");
		//url=url.replaceAll("^", "%5e");
		//PoolingHttpClient poolingHttpClient=new PoolingHttpClient();
		 //String html=poolingHttpClient.get(url);
		 String content=HttpClientUtil.getResult(url, "utf-8");
		System.out.println(content);
	 }
}

/**
 * 内部类，继承HttpGet，为了设置请求超时的参数
 * 
 * @author tom
 *
 */
class HttpGetConfig extends HttpGet{
    public HttpGetConfig(String url) {
        super(url);
        setDefaulConfig();
    }

    private void setDefaulConfig() {
        this.setConfig(RequestConfig.custom()
                .setConnectionRequestTimeout(10000)
                .setConnectTimeout(10000)
                .setSocketTimeout(10000).build());
        this.setHeader("User-Agent", "spider");
    }
}
