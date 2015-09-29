package com.umi.healthy.utils;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.google.common.collect.Lists;

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
}
