package com.umi.common.utils;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import java.util.Map.Entry;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response.Status;

import com.umi.common.data.persist.EnvironmentConfig;
import com.umi.common.utils.EncodingUtil;

import lombok.extern.java.Log;

import org.apache.commons.io.IOUtils;
@Log
public class NetworkUtils {
	private static final Long HASH_EXPERATIOTIME = 60000L; // 1 minute
	public static final String SECRET_KEY = "aC=l745$key";
	public static final String USER_AGENT = "agentIM";
	public static Boolean sendMail(String email,String description) throws UnsupportedEncodingException{
		Boolean flag = true;
		 Properties props = new Properties();
	        Session session = Session.getDefaultInstance(props, null);

	        try {
	            Message msg = new MimeMessage(session);
	            msg.setFrom(new InternetAddress(EnvironmentConfig.getInstance().getEmail(), "Nutrition"));
	            msg.addRecipient(Message.RecipientType.TO,
	            		new InternetAddress(EnvironmentConfig.getInstance().getEmail(), "Nutrition"));
	            
 	           /* msg.addRecipient(Message.RecipientType.TO,
	                             new InternetAddress(email, email));*/
	           
	            msg.setSubject(" Order (this email come from oztees that hosted on google)");
	            msg.setText(description);
	            Transport.send(msg);

	        } catch (AddressException e) {
	        	throw new CustomException(Status.INTERNAL_SERVER_ERROR, "AddressException: "+e.getMessage());
	        } catch (MessagingException e) {
	        	throw new CustomException(Status.INTERNAL_SERVER_ERROR, "MessagingException: "+e.getMessage());
	        }
		
		return true;
	}
	public static String addHashQuery(String query) {
		String result = "";
		if (query != null) {
			result = String.valueOf(query);
		}
		if (result.length() > 0) {
			result += "&";
		}
		result += "timestamp=" + System.currentTimeMillis();
		result += "&hash=" + EncodingUtil.MD5(result + SECRET_KEY);
		return result;
	}

	public static URL addHashQuery(URL url) {
		String query = url.getQuery();
		query = addHashQuery(query);
		try {
			url = new URL(url.getProtocol() + "://" + url.getAuthority()
					+ url.getPath() + "?" + query);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return url;
	}
	public static String getFullURL(HttpServletRequest req) {

	    String scheme = req.getScheme();             // http
	    String serverName = req.getServerName();     // hostname.com
	    int serverPort = req.getServerPort();        // 80
	    String contextPath = req.getContextPath();   // /mywebapp
	    String servletPath = req.getServletPath();   // /servlet/MyServlet
	    String pathInfo = req.getPathInfo();         // /a/b;c=123
	    String queryString = req.getQueryString();          // d=789

	    // Reconstruct original requesting URL
	    StringBuffer url =  new StringBuffer();
	    url.append(scheme).append("://").append(serverName);

	    if ((serverPort != 80) && (serverPort != 443)) {
	        url.append(":").append(serverPort);
	    }

	    url.append(contextPath).append(servletPath);

	    if (pathInfo != null) {
	        url.append(pathInfo);
	    }
	    if (queryString != null) {
	        url.append("?").append(queryString);
	    }
	    return url.toString();
	}
	public static String validateHashQuery(String hashQuery) {
		if (hashQuery != null) {
			int timestampIndex = hashQuery.indexOf("timestamp=");
			int index = hashQuery.indexOf("&hash");
			if (index > -1 && timestampIndex > -1) {
				Long timeStamp = Long.parseLong(hashQuery.substring(
						timestampIndex + 10, index));
				String hash = hashQuery.substring(index + 6);
				String query = hashQuery.substring(0, index);
				if (EncodingUtil.MD5(query + SECRET_KEY).equals(hash)) {
					if (System.currentTimeMillis() - timeStamp < HASH_EXPERATIOTIME) {
						return null;
					}
					return "expired!";
				} else {
					return "wrong hash!";
				}
			}
		}
		return "hash or timestamp not found!";
	}

	private static <T> String addParam(String query, String key, T value) {
		if (query == null) {
			query = "";
		}
		if (query.length() > 0) {
			query += "&";
		}
		try {
			query += key + "="
					+ URLEncoder.encode(String.valueOf(value), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		;
		return query;
	}

	private static <T> String getQueryString(Map<String, T> params) {
		Iterator<Entry<String, T>> entries = params.entrySet().iterator();
		String param = "";
		if (entries != null) {
			while (entries.hasNext()) {
				Map.Entry<String, T> entry = entries.next();
				String key = entry.getKey();
				T value = entry.getValue();

				if (value instanceof Iterable) {
					Iterator<?> iterator = ((Iterable<?>) value).iterator();
					while (iterator.hasNext()) {
						param = addParam(param, key, iterator.next());
					}
				} else {
					param = addParam(param, key, value);
				}
			}
		}
		return param;
	}

	public static <T> String crossApplicationRequestPost(URL url,
			Map<String, T> params) {
		String result = null;

		String param = getQueryString(params);
		param = addHashQuery(param);

		/* End preparing params */
		byte[] postData = param.getBytes(Charset.forName("UTF-8"));
		int postDataLength = postData.length;
		
		try {
			
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setDoOutput(true);
			connection.setDoInput(true);
			connection.setInstanceFollowRedirects(false);
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
			connection.setRequestProperty("charset", "utf-8"); 
			connection.setRequestProperty("Content-Length",
					Integer.toString(postDataLength));
			
			connection.setUseCaches(false);
			try (DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream())) {
				wr.write(postData);
			} 
			
			log.info(" crossApplicationRequestPost :"+connection.getResponseCode());
			log.info(" crossApplicationRequestPost :"+connection.getInputStream());
			
			if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
				InputStream inputStream = connection.getInputStream();
				result = IOUtils.toString(inputStream, "UTF-8");
				inputStream.close();
			} else {
				log.severe("try fails. Connect to:" + url.toString());
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}

	public static String fetchCountry(HttpServletRequest req) {
		String result = null;
		result = req.getHeader("CloudFront-Viewer-Country");
		if (result == null) {
			result = req.getHeader("X-AppEngine-Country");
		}

		return result;
	}

	public static String fetchIp(HttpServletRequest req) {
		String result = null;
		result = req.getHeader("X-Forwarded-For");
		if (result == null) {
			result = req.getRemoteAddr();
		}

		return result;
	}
	
	
	public static Map<String,String> parseUrlParams(String urlStr)
	{
		URL url = null;
		if(urlStr!=null)
		{
			try {
				url = new URL(urlStr);
			} catch (MalformedURLException e) {
				log.severe("error parsing url:"+url+".exception:"+StringUtil.exceptionFormat(e));
			}
		}
		return parseUrlParams(url);
	}
	
	public static String mapToQuery(Map<String,String> queryParams)
	{
		String query = "";
		String separator = "";
		for(Entry<String,String> entry:queryParams.entrySet())
		{
			String name = entry.getKey();
			if(name!=null && !name.isEmpty())
			{
				String value = entry.getValue();
				if(value!=null)
				{
					try {
						value = URLEncoder.encode(value, "UTF-8");
					} catch (UnsupportedEncodingException e) {
						log.severe(StringUtil.exceptionFormat(e));
					}
				}
				else
				{
					value = "";
				}
				query += separator + name + "=" + value;
				separator = "&";
			}
		}
		return query;
	}
	
	public static Map<String,String> parseUrlParams(URL url)
	{
		String urlParamsStr = null;
		String[] split_param;
		Map<String, String> urlParamsMap = new HashMap<String, String>();
		
		String name = null;
		String value = null;

		if (url != null)
		{
			try
			{
				urlParamsStr = url.getQuery();
				if (urlParamsStr != null
						&& !urlParamsStr.isEmpty())
				{
					String[] params = urlParamsStr.split("&");
					if (params.length > 0)
					{
						for (String param : params)
						{
							if(param == null)
							{
								continue;
							}
							split_param = param.split("=");
							name = null;
							value = null;
							
							if(split_param.length>0 && split_param[0]!=null && !split_param[0].isEmpty())
							{
								name = split_param[0];
								
								if(split_param.length==2 && split_param[1]!=null)
								{
									value = URLDecoder.decode(split_param[1], "UTF-8");
								}
							}
							
							if(name != null)
							{
								urlParamsMap.put(name, value);
							}
						}
						
					}
				}

			}
			catch (UnsupportedEncodingException e)
			{
				log.severe("error parsing url:"+url+".exception:"+StringUtil.exceptionFormat(e));
			}
		}
		else
		{
			log.info("url is empty");
		}
		return urlParamsMap;
	}


	public static void removeCookie(String string, HttpServletResponse response) {
	
	}

	public static String readCookieValue(String key, HttpServletRequest request) {
		String value = null;
		Cookie[] cookies = request.getCookies();
		if(cookies != null ){
			for(Cookie cookie:cookies){
				if(cookie.getName().equals(key)){
					value = cookie.getValue();
				}
			}
		 
		}
		return value;
	}

	public static void writeCookie(HttpServletResponse response,
			String key, String value) {
		String tmp = EncodingUtil.MD5( value + EnvironmentConfig.SECRET_KEY);
		Cookie myCookie = new Cookie(key, tmp);
		  myCookie.setMaxAge(60 * 60);
		  myCookie.setPath("/");
		response.addCookie(myCookie );
	}

}
