package com.umi.common.utils;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Map;
import java.util.Map.Entry;

import lombok.extern.java.Log;
@Log
public class PlistUtil
{
	public enum PlistWrapper
	{
		NONE,TEMPLATE,DICTIONARY
	}
    public static String MapToPlist(Map<String,String> data) {
        return MapToPlist(data, false, true);
    }
    public static String MapToPlist(Map<String,String> data, boolean wrapWithTemplate) {
        return MapToPlist(data, wrapWithTemplate?PlistWrapper.TEMPLATE:PlistWrapper.NONE, true);
    }
    
    public static String MapToPlist(Map<String,String> data, boolean wrapWithTemplate, boolean useUrlEncode)
    {
    	return MapToPlist(data, wrapWithTemplate?PlistWrapper.TEMPLATE:PlistWrapper.NONE, useUrlEncode);
    }
    
	public static String MapToPlist(Map<String,String> data, PlistWrapper wrapper, boolean useUrlEncode)
	{
		String result = "";
		for(Entry<String, String> entry:data.entrySet())
		{
			String value = entry.getValue();
			String key = entry.getKey();
			if(value != null)
			{
                if (useUrlEncode) {
                    try {
                        key = URLEncoder.encode(key, "UTF-8");
                        value = URLEncoder.encode(value, "UTF-8");
                    } catch (UnsupportedEncodingException e) {
                        log.severe(e.getMessage());
                    }
                }
                result += "<key>" + key + "</key><string>" + value + "</string>";
			}
		}

		switch(wrapper)
		{
		case TEMPLATE:
			 String template = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
	                    "<!DOCTYPE plist PUBLIC \"-//Apple//DTD PLIST 1.0//EN\" \"http://www.apple.com/DTDs/PropertyList-1.0.dtd\">\n" +
	                    "<plist version=\"1.0\">\n" +
	                    "<dict>" +
	                    "%s" +
	                    "</dict>";
	            result = String.format(template, result);
			break;
		case DICTIONARY:
			result = String.format("<plist><dict>%s</dict></plist>", result);
			break;
		default:
			break;	
		}

		return result;
	}
}
