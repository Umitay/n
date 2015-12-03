package com.umi.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import lombok.extern.java.Log;
@Log
public class EncodingUtil
{
	public static final String UTF_8 = "UTF-8";
	public static final String FILE_ENCODING = UTF_8;
	public static final Charset UTF_8_CHARSET = Charset.forName(UTF_8);
	public static final String US_ASCII = "US-ASCII";
	public static final String ISO_8859_1 = "ISO-8859-1";
	

	private static Pattern s_xmlEntityPattern = Pattern.compile("&.*?;");
	private static Map<String, String> s_xmlEntities;
	
	static
	{
		s_xmlEntities = new HashMap<String, String>();
		s_xmlEntities.put("&amp;", "&");
		s_xmlEntities.put("&quot;", "\\");
		s_xmlEntities.put("&lt;", "<");
		s_xmlEntities.put("&gt;", ">");
		s_xmlEntities.put("&nbsp;", " ");
	}

	public static Charset getEncoding(byte[] htmlData)
	{
		Charset charset = null;
		String charsetName = getEncodingName(htmlData);
		if (charsetName != null)
		{
			try
			{
				charset = Charset.forName(charsetName);
			}
			catch (Exception e)
			{
				System.err.println("Charset encoding " + charsetName + " not found");
				charset = null;
			}
		}
		
		return charset;
	}

	public static String getEncodingName(byte[] htmlData)
	{
		String charsetName = null;
		String htmlStr = new String(htmlData);
		int startIdx = htmlStr.indexOf("charset=");
		if (startIdx >= 0)
		{
			startIdx += "charset=".length();
			int endIdx = startIdx;
			while (!Character.isWhitespace(htmlStr.charAt(endIdx)) &&
					htmlStr.charAt(endIdx) != '\"' &&
					htmlStr.charAt(endIdx) != ';' &&
					htmlStr.charAt(endIdx) != '\'' &&
					endIdx < htmlStr.length())
			{
				endIdx++;
			}
			charsetName = htmlStr.substring(startIdx, endIdx);

		}

		return charsetName;
	}
	
	
	
	/**
	 * Unescapes *ONLY* the following XML entities: &amp; &quot; &lt; &gt; &nbsp;
	 * @param str
	 * @return
	 */
	public static String unescapeXmlEntities(String str)
	{
		if (str == null || str.length() == 0)
		{
			return str;
		}

		Matcher m = s_xmlEntityPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (m.find()) {
			String val = s_xmlEntities.get(m.group());
			if (val == null)
			{
				val = m.group();
			}
			m.appendReplacement(sb, val);
		}
		m.appendTail(sb);
		
		return sb.toString();
	}
	
	
	public static String escapeXmlEntities(String str)
	{		
		StringBuilder newUrl = new StringBuilder();
		for(int i = 0; i < str.length(); i++)
		{
			char c = str.charAt(i);
			switch(c)
	        {
	          case '<': newUrl.append("&lt;"); break;
	          case '>': newUrl.append("&gt;"); break;
	          case '\"': newUrl.append("&guot;"); break;
	          case '&': newUrl.append("&amp;"); break;
	          case '\'': newUrl.append("&apos;"); break;
	          default:
	             if(c>0x7e)
	             {
	                newUrl.append("&#"+((int)c)+";");
	             }
	             else
	             {
	            	 newUrl.append(c);
	             }	                
	        }
		}		       
		 return newUrl.toString();
	}
	
	public static String encodeUrlParam(String value)
	{
		try
        {
	        return URLEncoder.encode(value, UTF_8);
        }
        catch (UnsupportedEncodingException e)
        {
        	//this should never happen
        	return "";
        }
	}

	public static String decodeUrlParam(String value)
	{
		try
        {
	        return URLDecoder.decode(value, UTF_8);
        }
        catch (UnsupportedEncodingException e)
        {
        	//this should never happen
        	return "";
        }
	}

	public static String MD5(String md5)
	{
		try
		{
			java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
			byte[] array = md.digest(md5.getBytes());
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < array.length; ++i)
			{
				sb.append(Integer.toHexString((array[i] & 0xFF) | 0x100).substring(1, 3));
			}
			return sb.toString();
		}
		catch (java.security.NoSuchAlgorithmException e)
		{
		}
		return null;
	}
	
	public static byte[] decrypt(byte[] data) 
	{
		log.info("START decrypt");
		byte[] keyData = null;
		try {
			keyData = "anabel".getBytes( "UTF8" );
		} catch (UnsupportedEncodingException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		for (int keyIndex = 0, j = 0; j < data.length; j++)
		{
			try {
				data[j] = (byte) (data[j] ^ keyData[keyIndex]);	
			} catch (ArrayIndexOutOfBoundsException e) {
				log.info( "LOG\t" + e.getMessage() + "\nj:" + j + "\ti:" + keyIndex 
						+ "\ndata:" + data.length + "\tkey:" + keyData.length);
			}
			
			if( ++keyIndex == keyData.length )
			{
				keyIndex = 0;
			}
			
		}
		
		
		log.info("FINISH encrypt\t" + data 
				+ "\n" + data.toString() 
				+ "\n" + new String(data));
		return data;
	}
}
