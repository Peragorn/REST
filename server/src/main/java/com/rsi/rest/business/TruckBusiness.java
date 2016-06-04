package com.rsi.rest.business;


import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import com.rsi.rest.database.HibernateUtil;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.Truck;
import com.rsi.rest.model.User;

public class TruckBusiness {

  public List<Truck> getAllTrucks() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    List<Truck> allTruckList = new ArrayList<Truck>();
    allTruckList = session.createQuery("from Truck").list();

    session.close();
    return allTruckList;
  }

  public List<Truck> getFreeTrack() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    Criteria criteria = session.createCriteria(Truck.class);

    List<Truck> freeTruckList = new ArrayList<Truck>();

    freeTruckList = criteria.add(Restrictions.eq("state", true)).list();

    session.close();
    return freeTruckList;
  }

  public void changeStatus(boolean status, int id) {

    Truck truck = new Truck();
    truck = getTruckById(id);
    truck.setState(status);

    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    session.update(truck);
    tx.commit();

  }

  public Truck getTruckById(int id) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    Truck truck = new Truck();
    truck = session.get(Truck.class, id);

    session.close();
    return truck;
  }

  public void addTruck(Truck truck) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();
    truck.setState(true);
    session.save(truck);
    tx.commit();
  }

  public List<Truck> getTruckRentByUser(User user) {
    RentBusiness rentBusoness = new RentBusiness();
    List<Truck> rentedTruckList = new ArrayList<Truck>();

    for (Rent r : rentBusoness.getRentedCarByUser(user)) {
      if (r.getTruckId() != null) {
        rentedTruckList.add(r.getTruckId());
      }
    }

    return rentedTruckList;
  }
}
