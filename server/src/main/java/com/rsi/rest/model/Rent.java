package com.rsi.rest.model;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "Rent", schema = "rest")
public class Rent implements Serializable {

  private static final long serialVersionUID = -667717010681684391L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO, generator = "rent_id_seq")
  @SequenceGenerator(name = "rent_id_seq", sequenceName = "rent_id_seq", allocationSize = 1)
  @Column(name = "Id", unique = true, nullable = false)
  private int id;


  @OneToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Car carId;

  @OneToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
  private Truck truckId;


  @ManyToOne(
      cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH},
      fetch = FetchType.EAGER)
  @JoinColumn(name = "UserId")
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
