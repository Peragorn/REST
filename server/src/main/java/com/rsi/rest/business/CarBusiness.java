package com.rsi.rest.business;

import java.util.ArrayList;
import java.util.List;
import org.hibernate.Transaction;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import com.rsi.rest.database.HibernateUtil;
import com.rsi.rest.model.Car;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.User;

public class CarBusiness {

  public List<Car> getAllCar() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    List<Car> allCarlist = new ArrayList<Car>();

    allCarlist = session.createQuery("from Car").list();
    session.close();
    return allCarlist;
  }

  public List<Car> getFreeCar() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Car.class);

    List<Car> freeCarList = new ArrayList<Car>();

    freeCarList = criteria.add(Restrictions.eq("state", true)).list();

    session.close();
    return freeCarList;
  }

  public void changeStatus(boolean status, int id) {

    Car car = new Car();
    car = getCarById(id);
    car.setState(status);

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    session.update(car);
    tx.commit();

  }

  public Car getCarById(int id) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    Car car = new Car();
    car = session.get(Car.class, id);

    session.close();
    return car;
  }

  public void addCar(Car car) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    car.setState(true);
    session.save(car);
    tx.commit();
  }

  public List<Car> getCarRentByUser(User user) {
    RentBusiness rentBusoness = new RentBusiness();
    List<Car> rentedCarList = new ArrayList<Car>();

    for (Rent r : rentBusoness.getRentedCarByUser(user)) {
      if (r.getCarId() != null) {
        rentedCarList.add(r.getCarId());
      }
    }
    return rentedCarList;
  }
}
