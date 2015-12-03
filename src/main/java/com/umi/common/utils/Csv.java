package com.umi.common.utils;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import au.com.bytecode.opencsv.CSVReader;

public class Csv {
	List <String[]>  content =  new ArrayList<String[]>();
	URL url = null;
	public static final String CLOUD_DATA_STORAGE="http://storage.googleapis.com/oztees-au.appspot.com/";
	
	public Csv(URL url) {
		super();
		this.url = url;
	}
	
	public Csv() {
		
	}
	
	public List <String[]>  fetchContent() throws UnsupportedEncodingException, IOException {
		
		if(url!=null){
			
			InputStreamReader input =  new InputStreamReader(url.openStream(),"UTF-8");
			CSVReader csvReader = new CSVReader(input);
			content = csvReader.readAll();
			csvReader.close();
			
		}
		 
	return content;
	}
	public URL getUrl() {
		return url;
	}
	public void setUrl(String filename) {
		try {
			this.url = new URL(filename) ;
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
} 