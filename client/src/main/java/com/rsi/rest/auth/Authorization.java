package com.rsi.rest.auth;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.client.filter.HTTPBasicAuthFilter;
import com.sun.jersey.client.urlconnection.HTTPSProperties;

public class Authorization {

  public void auth() {
    DefaultClientConfig defaultClientConfig = new DefaultClientConfig();
    try {
      SSLContext sslContext = SSLContext.getInstance("SSL");
      ServerTrustManager serverTrustManager = new ServerTrustManager();
      sslContext.init(null, new TrustManager[] {serverTrustManager}, null);
      defaultClientConfig.getProperties().put(HTTPSProperties.PROPERTY_HTTPS_PROPERTIES,
          new HTTPSProperties(null));
    } catch (NoSuchAlgorithmException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (KeyManagementException e) {
      // TODO: handle exception
      e.printStackTrace();
    }

    Client client = Client.create(defaultClientConfig);
    client.addFilter(new HTTPBasicAuthFilter("admin", "admin"));
    WebResource webResource = client.resource("http://localhost:8080/server/");
    ClientResponse clientResponse = webResource.get(ClientResponse.class);
    String output = clientResponse.getEntity(String.class);
    System.out.println("output " + output);

  }
}
