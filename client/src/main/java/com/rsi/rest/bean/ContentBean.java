package com.rsi.rest.bean;

import java.io.InputStream;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import com.rsi.rest.model.Car;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.ResponseList;
import com.rsi.rest.model.Truck;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class ContentBean {
  private String name;

  private String nameplates;

  private int spots;

  private float copacity;

  private List<Car> freeCarList;

  private Car selectedCarToRent;

  private List<Truck> freeTruckList;

  private Truck selectedTruckToRent;

  private List<Car> rentedCarList;

  private Car selectedCarToRemove;

  private List<Truck> rentedTruckList;

  private Truck selectedTruckToRemove;

  private UserLoginView userLoginView;

  private Rent rentByUser;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameplates() {
    return nameplates;
  }

  public void setNameplates(String nameplates) {
    this.nameplates = nameplates;
  }

  public int getSpots() {
    return spots;
  }

  public void setSpots(int spots) {
    this.spots = spots;
  }

  public float getCopacity() {
    return copacity;
  }

  public void setCopacity(float copacity) {
    this.copacity = copacity;
  }

  public List<Car> getFreeCarList() {
    return freeCarList;
  }

  public void setFreeCarList(List<Car> freeCarList) {
    this.freeCarList = freeCarList;
  }

  public Car getSelectedCarToRent() {
    return selectedCarToRent;
  }

  public void setSelectedCarToRent(Car selectedCarToRent) {
    this.selectedCarToRent = selectedCarToRent;
  }

  public List<Truck> getFreeTruckList() {
    return freeTruckList;
  }

  public void setFreeTruckList(List<Truck> freeTruckList) {
    this.freeTruckList = freeTruckList;
  }

  public Truck getSelectedTruckToRent() {
    return selectedTruckToRent;
  }

  public void setSelectedTruckToRent(Truck selectedTruckToRent) {
    this.selectedTruckToRent = selectedTruckToRent;
  }

  public List<Car> getRentedCarList() {
    return rentedCarList;
  }

  public void setRentedCarList(List<Car> rentedCarList) {
    this.rentedCarList = rentedCarList;
  }

  public Car getSelectedCarToRemove() {
    return selectedCarToRemove;
  }

  public void setSelectedCarToRemove(Car selectedCarToRemove) {
    this.selectedCarToRemove = selectedCarToRemove;
  }

  public List<Truck> getRentedTruckList() {
    return rentedTruckList;
  }

  public void setRentedTruckList(List<Truck> rentedTruckList) {
    this.rentedTruckList = rentedTruckList;
  }

  public Truck getSelectedTruckToRemove() {
    return selectedTruckToRemove;
  }

  public void setSelectedTruckToRemove(Truck selectedTruckToRemove) {
    this.selectedTruckToRemove = selectedTruckToRemove;
  }

  public UserLoginView getUserLoginView() {
    return userLoginView;
  }

  public void setUserLoginView(UserLoginView userLoginView) {
    this.userLoginView = userLoginView;
  }

  public Rent getRentByUser() {
    return rentByUser;
  }

  public void setRentByUser(Rent rentByUser) {
    this.rentByUser = rentByUser;
  }

  private ResponseList loadData(String urlString) {
    try {
      Client client = Client.create();
      WebResource webResource2 = client.resource(urlString);
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

      return xml;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @PostConstruct
  public void initialize() {

    setFreeCarList(loadData("http://localhost:8080/server/rest/freeCar").getCarList());

    setFreeTruckList(loadData("http://localhost:8080/server/rest/freeTruck").getTruckList());

    userLoginView = new UserLoginView();

    setRentedCarList(loadData("http://localhost:8080/server/rest/getRentedCarByUser").getCarList());

    setRentedTruckList(
        loadData("http://localhost:8080/server/rest/getRentedtruckByUser").getTruckList());


    rentByUser = new Rent();
  }

  public void addNewCar() {
    FacesMessage message = null;
    Car car = new Car();
    if (name != null && nameplates != null && spots > 1) {
      car.setName(name);
      car.setNameplates(nameplates);
      car.setSpots(spots);
      // addCar(car);
      freeCarList.add(car);
      message = new FacesMessage("Dodano samochód osobowy");
    }
    clearData();
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  public void addNewTruck() {
    FacesMessage message = null;
    Truck truck = new Truck();
    if (name != null && nameplates != null && copacity > 1) {
      truck.setName(name);
      truck.setNameplates(nameplates);
      truck.setCopacity(copacity);
      // addTruck(truck);
      freeTruckList.add(truck);
      message = new FacesMessage("Dodano samochód ciężarowy");
    }
    clearData();
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  private void clearData() {
    setName(null);
    setNameplates(null);
    setCopacity(0);
    setSpots(0);
  }

}
