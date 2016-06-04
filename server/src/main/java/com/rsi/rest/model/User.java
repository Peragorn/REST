package com.rsi.rest.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "User", schema = "rest")
public class User implements Serializable {

  private static final long serialVersionUID = 9145652009703462898L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "user_id_seq")
  @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
  @Column(name = "Id", unique = true, nullable = false)
  private int id;

  @Column(name = "Login")
  private String login;

  @Column(name = "Password")
  private String password;

  @Column(name = "Name")
  private String name;

  @Column(name = "Surname")
  private String lastname;

  @Transient
  @OneToMany(mappedBy = "userId", fetch = FetchType.EAGER)
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
