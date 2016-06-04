package com.rsi.rest.database;


import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rsi.rest.model.Car;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.Truck;
import com.rsi.rest.model.User;

public class InitDB {

  public void init() {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    User user = new User();
    user.setLastname("Śliwowski");
    user.setLogin("damian");
    user.setPassword("damian");
    user.setName("Damian");
    session.save(user);

    User user1 = new User();
    user1.setLastname("Mioduszewski");
    user1.setLogin("michal");
    user1.setPassword("michal");
    user1.setName("Michał");
    session.save(user1);

    Car car = new Car();
    car.setName("Opel Astra");
    car.setNameplates("BI 1234");
    car.setSpots(4);
    car.setState(false);
    session.save(car);

    Car car1 = new Car();
    car1.setName("Audi R8");
    car1.setNameplates("BZA 1234");
    car1.setSpots(2);
    car1.setState(true);
    session.save(car1);

    Car car2 = new Car();
    car2.setName("Ford Mondeo");
    car2.setNameplates("BWM 1234");
    car2.setSpots(5);
    car2.setState(true);
    session.save(car2);

    Car car3 = new Car();
    car3.setName("Ford Fiesta");
    car3.setNameplates("SK 1234");
    car3.setSpots(4);
    car3.setState(true);
    session.save(car3);

    Truck truck = new Truck();
    truck.setName("Start");
    truck.setNameplates("BWM 1231");
    truck.setState(true);
    truck.setCopacity((float) 1000.12);
    session.save(truck);

    Truck truck1 = new Truck();
    truck1.setName("DAF XF");
    truck1.setNameplates("DSA 5643");
    truck1.setState(true);
    truck1.setCopacity((float) 1000.25);
    session.save(truck1);

    Rent rent = new Rent();
    rent.setCarId(car);
    rent.setUserId(user);
    session.save(rent);

    System.out.println("DB data initialized");
    tx.commit();
  }
}
