package com.rsi.rest.bean;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.ws.rs.core.MediaType;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@ManagedBean(eager = true)
public class UserLoginView {

  private String username;

  private String password;

  private String name;

  private String surname;

  public boolean authentication = false;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getSurname() {
    return surname;
  }

  public void setSurname(String surname) {
    this.surname = surname;
  }

  public boolean isAuthentication() {
    return authentication;
  }

  public void setAuthentication(boolean authentication) {
    this.authentication = authentication;
  }

  @PostConstruct
  public void initialize() {

  }

  public void getFreeCar() {

    try {

      Client client = Client.create();
      WebResource webResource2 = client.resource("http://localhost:8080/server/rest/freeCar");
      ClientResponse response2 =
          webResource2.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response2.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
      }

      String output2 = response2.getEntity(String.class);
      System.out.println(output2);

    } catch (Exception e) {
      e.printStackTrace();
    }


  }
}
