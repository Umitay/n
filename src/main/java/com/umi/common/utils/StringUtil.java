package com.umi.common.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.extern.java.Log;

import com.google.common.collect.Lists;
@Log
public class StringUtil
{
	public static URL parseURL(String val, URL defaultVal)
	{
		URL ret = defaultVal;
		if (val != null)
		{
			try
			{
				ret = new URL(val);
			}
			catch (MalformedURLException malformedURLException)
			{
				ret = defaultVal;
			}
		}
		return ret;
	}

	public static String parseString(String val, String defaultVal)
	{
		String ret = defaultVal;
		if (val != null && val.length() > 0)
		{
			ret = val;
		}
		return ret;
	}

	public static Long parseLong(String val, Long defaultVal)
	{
		Long ret = defaultVal;
		if (val != null && val.length() > 0)
		{
			try
			{
				ret = Long.parseLong(val);
			}
			catch (NumberFormatException e)
			{
			}
		}
		return ret;
	}

	public static long parseLong(String val, long defaultVal)
	{
		long ret = defaultVal;
		if (val != null && val.length() > 0)
		{
			try
			{
				ret = Long.parseLong(val);
			}
			catch (NumberFormatException e)
			{
			}
		}
		return ret;
	}

	public static Boolean parseBool(String val, Boolean defaultVal)
	{
		Boolean ret = defaultVal;
		if (val != null && val.length() > 0)
		{
			ret = Boolean.parseBoolean(val);
		}
		return ret;
	}

	public static Integer parseInt(String val, Integer defaultVal)
	{
		Integer ret = defaultVal;
		if (val != null && val.length() > 0)
		{
			try
			{
				ret = Integer.parseInt(val);
			}
			catch (NumberFormatException e)
			{
			}
		}
		return ret;
	}

	public static int parseInt(String val, int defaultVal)
	{
		int ret = defaultVal;
		if (val != null && val.length() > 0)
		{
			try
			{
				ret = Integer.parseInt(val);
			}
			catch (NumberFormatException e)
			{
			}
		}
		return ret;
	}

	public static Double parseDouble(String val, Double defaultVal)
	{
		Double ret = defaultVal;
		if (val != null && val.length() > 0)
		{
			try
			{
				ret = Double.parseDouble(val);
			}
			catch (NumberFormatException e)
			{
			}
		}
		return ret;
	}

	public static ArrayList<String> ConvertStringsToStringList(String str, String delimiter)
	{
		ArrayList<String> ret = new ArrayList<String>();
		if (str != null && str.length() > 0)
		{
			ret = Lists.newArrayList(str.split(delimiter));
		}
		return ret;
	}

	public static final boolean equalsWithNulls(Object a, Object b)
	{
		if (a == b)
			return true;
		if ((a == null) || (b == null))
			return false;
		return a.equals(b);
	}

	public static List<String> parseList(String str)
	{
		List<String> result = Lists.newArrayList();;
		if(str!=null){
			result = Lists.newArrayList(str.split(",",-1));
		}
		return result;
	}
	public static Long ipToLong(String ipAddress) {
	    Long result = 0L;
	    String[] atoms = ipAddress.split("\\.");

	    if(atoms.length!=4)
	    {
	    	return null;
	    }
	    for (int i = 3; i >= 0; i--) {
	        result |= (Long.parseLong(atoms[3 - i]) << (i * 8));
	    }

	    return result & 0xFFFFFFFF;
	}

	public static String longToIp(long i) {
		return ((i >> 24) & 0xFF) + 
                   "." + ((i >> 16) & 0xFF) + 
                   "." + ((i >> 8) & 0xFF) + 
                   "." + (i & 0xFF);
	}
	public static <E> String exceptionFormat(E exception){
		
		Exception e =  (Exception) exception;
		Integer line = null;
		String className = null;
		String methodName = null;
		
		if(e.getStackTrace() != null && e.getStackTrace().length > 2 ){
			line = e.getStackTrace()[2].getLineNumber();
			className = e.getStackTrace()[2].getClassName();
			methodName = e.getStackTrace()[2].getMethodName();
		}
		if(line == null ){
			line = Thread.currentThread().getStackTrace()[2].getLineNumber();
		}
		if(className == null ){
			className = Thread.currentThread().getStackTrace()[2].getClassName();
		}
		if(methodName == null ){
			methodName = Thread.currentThread().getStackTrace()[2].getMethodName();
		}
		
		return String.format("The msg: %s,"
					+ "The Class: %s,"
					+ "The Method: %s,"
					+ "The Line: %d,",
					e.getMessage(),
					className,
					methodName,
					line ) ;
		
	}
	public static String generateSlug(String str)
	{	
		String slug = StringUtil.rus2lat(str.toLowerCase());
		slug = slug.trim();
		slug = slug.replace(",", "");
		slug = slug.replace(".", "");
		slug = slug.replace(" ", "-");
		slug = slug.replace("--", "-");
		slug = slug.replace("?", "");
		slug = slug.replace("«", "");
		slug = slug.replace("»", "");
		return slug;
	}
	
	public static String rus2lat(String str){
	
	
		Map<Character,String> map = new HashMap<Character,String>();
		map.put('\u0410', "A");
		map.put('\u0412', "B");
		map.put('\u0421', "C");
		map.put('\u0415', "E");
		map.put('\u041D', "H");
		map.put('\u041A', "K");
		map.put('\u041C', "M");
		map.put('\u041E', "O");
		map.put('\u0420', "P");
		map.put('\u0422', "T");
		map.put('\u0423', "Y");
		map.put('\u0425', "X");
		map.put('\u0430', "a");
		map.put('\u0431', "b");
		map.put('\u0432', "v");
		map.put('\u0433', "g");
		map.put('\u0434', "d");
		map.put('\u0435', "e");
		map.put('\u0436', "zh");
		map.put('\u0437', "z");
		map.put('\u0438', "i");
		map.put('\u0439', "i");
		map.put('\u043A', "k");
		map.put('\u043B', "l");
		map.put('\u043C', "m");
		map.put('\u043D', "n");
		map.put('\u043E', "o");
		map.put('\u043F', "p");
		map.put('\u0440', "r");
		map.put('\u0441', "s");
		map.put('\u0442', "t");
		map.put('\u0443', "u");
		map.put('\u0444', "f");
		map.put('\u0445', "h");
		map.put('\u0446', "c");
		map.put('\u0447', "ch");
		map.put('\u0448', "sh");
		map.put('\u0449', "shch");
		map.put('\u044A', "");
		map.put('\u044B', "y");
		map.put('\u044C', "");
		map.put('\u044D', "e");
		map.put('\u044E', "yu");
		map.put('\u044F', "ya");
		map.put(' ', " ");
		
		StringBuilder sb = new StringBuilder(str.length());
		for (Character c : str.toCharArray()) {
		    if (map.containsKey(c)) {
		       log.info("add latin: "+map.get(c)+" for cyrillic: "+ c);
		        sb.append(map.get(c));
		    } else {
		    	log.info("add unknown letter:  " + c);
		        sb.append(c);
		    }
		}
		
		
		return  sb.toString();
	}
	public static Boolean is_rus(String str){
		
		Boolean flag = false;
		Map<Character,String> map = new HashMap<Character,String>();
		map.put('\u0410', "A");
		map.put('\u0412', "B");
		map.put('\u0421', "C");
		map.put('\u0415', "E");
		map.put('\u041D', "H");
		map.put('\u041A', "K");
		map.put('\u041C', "M");
		map.put('\u041E', "O");
		map.put('\u0420', "P");
		map.put('\u0422', "T");
		map.put('\u0423', "Y");
		map.put('\u0425', "X");
		map.put('\u0430', "a");
		map.put('\u0431', "b");
		map.put('\u0432', "v");
		map.put('\u0433', "g");
		map.put('\u0434', "d");
		map.put('\u0435', "e");
		map.put('\u0436', "zh");
		map.put('\u0437', "z");
		map.put('\u0438', "i");
		map.put('\u0439', "i");
		map.put('\u043A', "k");
		map.put('\u043B', "l");
		map.put('\u043C', "m");
		map.put('\u043D', "n");
		map.put('\u043E', "o");
		map.put('\u043F', "p");
		map.put('\u0440', "r");
		map.put('\u0441', "s");
		map.put('\u0442', "t");
		map.put('\u0443', "u");
		map.put('\u0444', "f");
		map.put('\u0445', "h");
		map.put('\u0446', "c");
		map.put('\u0447', "ch");
		map.put('\u0448', "sh");
		map.put('\u0449', "shch");
		map.put('\u044A', "");
		map.put('\u044B', "y");
		map.put('\u044C', "");
		map.put('\u044D', "e");
		map.put('\u044E', "yu");
		map.put('\u044F', "ya");
		map.put(' ', " ");
		
	
		for (Character c : str.toCharArray()) {
		    if (map.containsKey(c)) {
		       log.info("add latin: "+map.get(c)+" for cyrillic: "+ c);
		       flag = true;
		    }
		}
		
		
		return flag;
	}
	
}
