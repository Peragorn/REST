package com.rsi.rest.model;

import java.io.Serializable;

public class Car implements Serializable {

  private static final long serialVersionUID = -1159640016730196606L;

  private int id;

  private String name;

  private String nameplates;

  private int spots;

  private boolean state;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

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

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

}
