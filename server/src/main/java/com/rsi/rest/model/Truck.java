package com.rsi.rest.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Truck", schema = "rest")
public class Truck implements Serializable {

  private static final long serialVersionUID = -5892601389707361232L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "truck_id_seq")
  @SequenceGenerator(name = "truck_id_seq", sequenceName = "truck_id_seq", allocationSize = 1)
  @Column(name = "Id", unique = true, nullable = false)
  private int id;

  @Column(name = "Name")
  private String name;

  @Column(name = "Nameplates")
  private String nameplates;

  @Column(name = "Copacity")
  private float copacity;

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
