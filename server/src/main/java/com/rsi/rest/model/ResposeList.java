package com.rsi.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement(name = "responseList")
public class ResposeList {

  private List<Object> list;

  private List<Car> carList;

  private List<Truck> truckList;

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

}
