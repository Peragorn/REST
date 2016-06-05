package com.rsi.rest.model;

import java.io.Serializable;

public class Truck implements Serializable {

  private static final long serialVersionUID = -5892601389707361232L;

  private int id;

  private String name;

  private String nameplates;

  private float copacity;

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

  public float getCopacity() {
    return copacity;
  }

  public void setCopacity(float copacity) {
    this.copacity = copacity;
  }

  public boolean isState() {
    return state;
  }

  public void setState(boolean state) {
    this.state = state;
  }

}
