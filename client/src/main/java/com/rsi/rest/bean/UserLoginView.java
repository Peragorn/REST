package com.rsi.rest.bean;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.primefaces.context.RequestContext;
import org.primefaces.json.JSONArray;

import com.rsi.rest.model.Car;
import com.rsi.rest.model.ResponseList;
import com.rsi.rest.model.Truck;
import com.rsi.rest.model.User;
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

  public User userLoged;

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

  public ArrayList<Car> getFreeCar() {

    try {

      Client client = Client.create();
      WebResource webResource2 = client.resource("http://localhost:8080/server/rest/freeCar");
      ClientResponse response2 =
          webResource2.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response2.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
      }

      // Zmienia XML na ArrayList
      InputStream is = webResource2.accept(MediaType.APPLICATION_XML).get(ClientResponse.class)
          .getEntityInputStream();
      JAXBContext jaxb = JAXBContext.newInstance(ResponseList.class);
      Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
      ResponseList xml = (ResponseList) jaxbUnmarshaller.unmarshal(is);


      ArrayList<Car> carList = new ArrayList<Car>();
      carList.addAll(xml.getCarList());


      return carList;

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }

  }

  public ArrayList<Truck> getFreeTruck() {

    ArrayList<Truck> truckList = new ArrayList<Truck>();
    try {

      Client client = Client.create();
      WebResource webResource2 = client.resource("http://localhost:8080/server/rest/freeTruckk");
      ClientResponse response2 =
          webResource2.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
      if (response2.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
      }

      String output2 = response2.getEntity(String.class);

      JSONArray jsonArray = new JSONArray(output2);

      for (int i = 0; i < jsonArray.length(); i++) {
        Truck truck = new Truck();
        truck.setId((Integer) (jsonArray.getJSONObject(i).get("id")));
        truck.setName((String) (jsonArray.getJSONObject(i).get("name")));
        truck.setNameplates((String) (jsonArray.getJSONObject(i).get("nameplates")));
        truck.setState((Boolean) (jsonArray.getJSONObject(i).get("state")));
        truck.setCopacity(Float.valueOf(jsonArray.getJSONObject(i).get("copacity").toString()));

        truckList.add(truck);

      }


    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return truckList;

  }

  public void login(ActionEvent event) {

    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage message = null;
    boolean loggedIn = false;
    User user = new User();

    if (username != null && password != null) {
      user = convertToUser(username, password);

    }

    if (user != null) {
      try {
        loggedIn = true;
        setUserLoged(user);

        context.addCallbackParam("loggedIn", loggedIn);

        FacesContext.getCurrentInstance().getExternalContext().redirect("/client/rent/main.xhtml");
        message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Witaj",
            user.getName() + " " + user.getLastname());
      } catch (IOException ex) {
        Logger.getLogger(UserLoginView.class.getName()).log(Level.SEVERE, null, ex);
      }
    } else {
      message =
          new FacesMessage(FacesMessage.SEVERITY_WARN, "Błąd przy logowaniu", "Niepoprawne dane");
    }

    FacesContext.getCurrentInstance().addMessage(null, message);

  }

  public User getUserLoged() {
    if (userLoged == null) {
      userLoged = new User();
    }
    return userLoged;
  }

  public void setUserLoged(User userLoged) {
    this.userLoged = userLoged;
  }

  private User convertToUser(String username, String password) {
    User user = new User();

    try {
      Client client = Client.create();
      WebResource webResource2 = client.resource(
          "http://localhost:8080/server/rest/isPasswordCorrect/" + username + "/" + password);
      ClientResponse response2 =
          webResource2.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response2.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
      }

      InputStream is = webResource2.accept(MediaType.APPLICATION_XML).get(ClientResponse.class)
          .getEntityInputStream();
      JAXBContext jaxb = JAXBContext.newInstance(ResponseList.class);
      Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
      ResponseList xml = (ResponseList) jaxbUnmarshaller.unmarshal(is);

      user = xml.getUser();

    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return user;
  }

  public void register(ActionEvent event) {

    Client client = Client.create();
    WebResource wr = client.resource("http://localhost:8080/server/rest/addUser");

    RequestContext context = RequestContext.getCurrentInstance();
    FacesMessage message = null;
    boolean registered = false;
    User user = new User();

    if (username != null && password != null && name != null && surname != null) {
      user.setLogin(username);
      user.setPassword(password);
      user.setName(name);
      user.setLastname(surname);
      ResponseList resp = new ResponseList();
      resp.setUser(user);
      wr.accept(MediaType.TEXT_XML).post(resp);
    }
    if (user != null) {
      registered = true;
      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Zarejestrowano",
          user.getName() + " " + user.getLastname());

    }
    FacesContext.getCurrentInstance().addMessage(null, message);
    context.addCallbackParam("registered", registered);
  }

}
