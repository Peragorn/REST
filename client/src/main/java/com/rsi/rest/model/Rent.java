package com.rsi.rest.model;

import java.io.Serializable;

public class Rent implements Serializable {

  private static final long serialVersionUID = 7710980707122221234L;

  private int id;

  private Car carId;

  private Truck truckId;

  private User userId;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Car getCarId() {
    return carId;
  }

  public void setCarId(Car carId) {
    this.carId = carId;
  }

  public Truck getTruckId() {
    return truckId;
  }

  public void setTruckId(Truck truckId) {
    this.truckId = truckId;
  }

  public User getUserId() {
    return userId;
  }

  public void setUserId(User userId) {
    this.userId = userId;
  }


}
