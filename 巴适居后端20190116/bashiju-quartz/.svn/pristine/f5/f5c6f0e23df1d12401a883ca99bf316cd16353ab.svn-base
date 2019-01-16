package com.bashiju.quartz.service.mq;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.zip.GZIPInputStream;

import org.apache.commons.lang3.StringUtils;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.net.HttpRequest;
import cn.edu.hfut.dmic.webcollector.net.HttpResponse;

/**
 * Created by YNBSJ-002 on 2018/7/14.
 */
public class CustomHttpRequest extends HttpRequest {

    public CustomHttpRequest(String url) throws Exception {
        super(url);
    }

    public CustomHttpRequest(CrawlDatum crawlDatum) throws Exception {
        super(crawlDatum);
    }
    @Override
    public HttpResponse response() throws Exception {

        URL url = new URL(crawlDatum.url());
        if(userAgent!=null){
            setUserAgent(userAgent);
        }
        HttpResponse response = new HttpResponse(url);
        int code = -1;
        int maxRedirect = Math.max(0, MAX_REDIRECT);
        HttpURLConnection con = null;
        InputStream is = null;
        try {
            String firstCookie=null;
            for (int redirect = 0; redirect <= maxRedirect; redirect++) {
                con = (HttpURLConnection) url.openConnection();
                con.setInstanceFollowRedirects(true);
                con.setConnectTimeout(5000);
                con.setRequestMethod("GET");
                con.setDoOutput(true);
                con.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                if (StringUtils.isNotEmpty(firstCookie)){
                    con.setRequestProperty("Cookie", firstCookie);
                }
                config(con);

                if(outputData!=null){
                    OutputStream os=con.getOutputStream();
                    os.write(outputData);
                    os.close();
                }

                code = con.getResponseCode();
                /*只记录第一次返回的code*/
                if (redirect == 0) {
                    response.code(code);
                }

                if(code==HttpURLConnection.HTTP_NOT_FOUND){
                    response.setNotFound(true);
                    return response;
                }

                boolean needBreak = false;
                switch (code) {

                    case HttpURLConnection.HTTP_MOVED_PERM:
                    case HttpURLConnection.HTTP_MOVED_TEMP:
                        response.setRedirect(true);
                        if (redirect == MAX_REDIRECT) {
                            throw new Exception("redirect to much time");
                        }
                        String location = con.getHeaderField("Location");
                        if (location == null) {
                            throw new Exception("redirect with no location");
                        }
                        String originUrl = url.toString();
                        //url = new URL(url, location);
                        //获取cookie
                        Map<String,List<String>> map=con.getHeaderFields();
                        Set<String> set=map.keySet();
                        for (Iterator iterator = set.iterator(); iterator.hasNext();) {
                            String key = (String) iterator.next();
                            if (key!=null&&key.equals("Set-Cookie")) {
                                System.out.println("key=" + key + ",开始获取cookie");
                                List<String> list = map.get(key);
                                StringBuilder builder = new StringBuilder();
                                for (String str : list) {
                                    builder.append(str).toString();
                                    builder.append(";");
                                }
                                firstCookie = builder.toString();
                                System.out.println("第一次得到的cookie=" + firstCookie);
                            }
                        }
                            response.setRealUrl(url);
                        LOG.info("redirect from " + originUrl + " to " + url.toString());
                        continue;
                    default:
                        needBreak = true;
                        break;
                }
                if (needBreak) {
                    break;
                }

            }

            is = con.getInputStream();
            String contentEncoding = con.getContentEncoding();
            if (contentEncoding != null && contentEncoding.equals("gzip")) {
                is = new GZIPInputStream(is);
            }

            byte[] buf = new byte[2048];
            int read;
            int sum = 0;
            int maxsize = MAX_RECEIVE_SIZE;
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            while ((read = is.read(buf)) != -1) {
                if (maxsize > 0) {
                    sum = sum + read;

                    if (maxsize > 0 && sum > maxsize) {
                        read = maxsize - (sum - read);
                        bos.write(buf, 0, read);
                        break;
                    }
                }
                bos.write(buf, 0, read);
            }

            response.content(bos.toByteArray());
            response.headers(con.getHeaderFields());
            bos.close();

            return response;
        } catch (Exception ex) {
            throw ex;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }
}
