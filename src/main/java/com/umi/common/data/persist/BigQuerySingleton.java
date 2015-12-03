package com.umi.common.data.persist;

import java.util.Arrays;

import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.extensions.appengine.auth.oauth2.AppIdentityCredential;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.bigquery.Bigquery;

public enum BigQuerySingleton
{
	INSTANCE;
	
	private final String SCOPE = "https://www.googleapis.com/auth/bigquery";
	private final HttpTransport TRANSPORT = new UrlFetchTransport();
	private final JsonFactory JSOFACTORY = new JacksonFactory();
	
	private Bigquery bigquery = new Bigquery.Builder(TRANSPORT, JSOFACTORY, getRequestInitializer()).setApplicationName(EnvironmentConfig.getInstance().getApplicationID()).build();
	
	private HttpRequestInitializer getRequestInitializer() {
		
	    if (System.getProperty("OAUTH_ACCESS_TOKEN") != null) {
	      // Good for testing and localhost environments, where no AppIdentity is available.
	      return new GoogleCredential().setAccessToken(System.getenv("OAUTH_ACCESS_TOKEN"));
	    }
	    return new AppIdentityCredential(Arrays.asList(new String[] {SCOPE}));
	}
	
	public Bigquery get()
	{
		return bigquery;
	}
}
