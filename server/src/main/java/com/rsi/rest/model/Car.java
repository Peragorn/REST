package com.rsi.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name = "Car", schema = "rest")
public class Car implements Serializable {

  private static final long serialVersionUID = 8143176535198253075L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "car_id_seq")
  @SequenceGenerator(name = "car_id_seq", sequenceName = "car_id_seq", allocationSize = 1)
  @Column(name = "Id", unique = true, nullable = false)
  private int id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Nameplates")
  private String nameplates;

  @Column(name = "Spots")
  private int spots;

  @Column(name = "State", columnDefinition = "boolean default true")
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
