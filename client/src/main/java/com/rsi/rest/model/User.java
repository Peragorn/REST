package com.rsi.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class User implements Serializable {

  private static final long serialVersionUID = 9145652009703462898L;

  private int id;

  private String login;

  private String password;

  private String name;

  private String lastname;

  private List<Rent> rents = new ArrayList<Rent>();

  public int getId() {
    return id;
  }

  public void setId(int Id) {
    this.id = Id;
  }

  public String getLogin() {
    return login;
  }

  public void setLogin(String login) {
    this.login = login;
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

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public List<Rent> getRents() {
    return rents;
  }

  public void setRents(List<Rent> rents) {
    this.rents = rents;
  }
}
