package com.rsi.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "responseList")
public class ResponseList {

  private List<Object> list;

  private List<Car> carList;

  private List<Truck> truckList;

  private List<User> userList;

  private List<Rent> rentList;

  public List<Object> getList() {
    return list;
  }

  public void setList(List<Object> list) {
    this.list = list;
  }

  public List<Car> getCarList() {
    return carList;
  }

  public void setCarList(List<Car> carList) {
    this.carList = carList;
  }

  public List<Truck> getTruckList() {
    return truckList;
  }

  public void setTruckList(List<Truck> truckList) {
    this.truckList = truckList;
  }

  public List<User> getUserList() {
    return userList;
  }

  public void setUserList(List<User> userList) {
    this.userList = userList;
  }

  public List<Rent> getRentList() {
    return rentList;
  }

  public void setRentList(List<Rent> rentList) {
    this.rentList = rentList;
  }

}
