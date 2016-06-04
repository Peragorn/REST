package com.rsi.rest.business;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rsi.rest.database.HibernateUtil;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.User;


public class RentBusiness {

  public List<Rent> getAllRents() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    List<Rent> allRentList = new ArrayList<Rent>();
    allRentList = session.createQuery("from Rent").list();

    session.close();
    return allRentList;
  }

  public void addRent(Rent rent) {
    CarBusiness cb = new CarBusiness();
    TruckBusiness tb = new TruckBusiness();

    if (rent.getCarId() != null) {
      cb.changeStatus(false, rent.getCarId().getId());
    }
    if (rent.getTruckId() != null) {
      tb.changeStatus(false, rent.getTruckId().getId());
    }
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    session.save(rent);
    tx.commit();
  }

  public List<Rent> getRentedCarByUser(User user) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Rent.class);

    List<Rent> rentedCar = new ArrayList<Rent>();
    rentedCar = criteria.add(Restrictions.eq("userId", user)).list();

    session.close();
    return rentedCar;
  }

  public void removeUserRent(Rent rent) {
    CarBusiness cb = new CarBusiness();
    TruckBusiness tb = new TruckBusiness();
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    session.delete(rent);
    tx.commit();

    if (rent.getCarId() != null) {
      cb.changeStatus(true, rent.getCarId().getId());
    }
    if (rent.getTruckId() != null) {
      tb.changeStatus(true, rent.getTruckId().getId());
    }

  }

  public Rent findRentByData(Rent r) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Rent.class);
    Rent rent = new Rent();

    if (r.getUserId() != null) {
      criteria.add(Restrictions.eq("userId", r.getUserId()));
    }
    if (r.getCarId() != null) {
      criteria.add(Restrictions.eq("carId", r.getCarId()));
    }
    if (r.getTruckId() != null) {
      criteria.add(Restrictions.eq("truckId", r.getTruckId()));
    }
    List<Rent> rentList = new ArrayList<Rent>();
    rentList = criteria.list();

    rent = rentList.get(0);
    session.close();
    return rent;
  }
}
