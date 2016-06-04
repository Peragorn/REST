package com.rsi.rest;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.rsi.rest.database.HibernateUtil;
import com.rsi.rest.database.InitDB;
import com.rsi.rest.model.Car;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.User;

public class Test {
  public static void main(String[] args) {
    Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    Transaction tx = session.beginTransaction();

    User user = new User();
    user.setId(1);
    user.setLastname("Śliwowski");
    user.setLogin("damian");
    user.setPassword("damian");
    user.setName("Damian");
    ////
    Car car = new Car();
    car.setId(6);
    car.setName("OpelTEST");
    car.setNameplates("BI 1234");
    car.setSpots(4);
    car.setState(true);

    // Truck truck = new Truck();
    // truck.setName("StartTEST");
    // truck.setNameplates("BWM 1231");
    // truck.setState(true);
    // truck.setCopacity((float) 1000.12);

    Rent rent = new Rent();
    rent.setId(5);
    rent.setCarId(car);
    rent.setUserId(user);
    // rent1.setTruckId(truck);
    //
    // System.out.println("Przed zapisem");

    // session.save(user);
    // session.save(car);
    // session.save(rent);
    // session.save(rent1);

    session.close();
    // List<User> userList = session.createQuery("from User").list();
    // for(User u : userList){
    // System.out.println(u.getName());
    // if(u.getRents() != null){
    //// System.err.println("TEST "+u.getRents().get(1).getCarId().getName() + " " + u.getId());
    // }
    // }

    // List<Car> carList = session.createQuery("from Car").list();
    // for(Car c : carList){
    // System.out.println(c.getName());
    // if(c.getId()==2){
    // Rent r = new Rent();
    // r.setCarId(c);
    // session.save(r);
    // }
    // }
    // List<Rent> rentList = session.createQuery("from Rent").list();
    // for(Rent u : rentList) {
    // if(u.getCarId() != null){
    // System.out.println(u.getId() + " " + u.getCarId().getName());
    // }
    // if(u.getUserId() != null){
    // System.out.println(u.getUserId().getLastname());
    // }
    // }

    // tx.commit();
    // System.out.println("END"+userList.size());
    // session.close();
    //
    // InitDB initDb = new InitDB();
    // initDb.init();
    // SPRAWDZAN UserBusiness
    // UserBusiness userBussines = new UserBusiness();
    // List<User> usersList = new VirtualFlow.ArrayLinkedList<User>();
    // usersList = userBussines.getAllUsers();
    // for(User u : usersList){
    // System.out.println(u.getName());
    // if(u.getRents() != null){
    // u.getRents().size();
    // }
    // }
    // User usr = new User();
    // usr = userBussines.getUserById(1);
    // System.out.println(usr.getName()+" "+usr.getLastname() + " " + usr.getRents());
    // System.err.println(userBussines.getUserByLogin("damian"));

    // List<Rent> rent = new ArrayList<Rent>();
    // rent = userBussines.getUserRents(1);
    // for(Rent r : rent){
    // System.out.println(r.getId() + " " + r.getCarId().getName());
    // }

    // SPRAWDZAN CarBusiness
    // CarBusiness cb = new CarBusiness();
    // List<Car> carList = new ArrayList<Car>();
    // carList = cb.getAllCar();
    // for(Car c : carList){
    // System.out.println(c.getName()+" "+c.getId());
    // }
    //
    // System.out.println("CAR BY ID "+cb.getCarById(4).getName());

    // cb.changeStatus(false, 3);
    //
    // carList = cb.getFreeCar();
    // for(Car c : carList){
    // System.out.println(c.getName()+" "+c.getId());
    // }
    //
    // User user = new User();
    // user.setId(1);
    // user.setLastname("Śliwowski");
    // user.setLogin("damian");
    // user.setPassword("damian");
    // user.setName("Damian");
    // cb.getCarRentByUser(user);

    // TEST RentBusiness
    // Rent rent = new Rent();
    // rent.setUserId(userBussines.getUserById(1));
    // rent.setCarId(cb.getCarById(4));
    // RentBusiness rentBusiness = new RentBusiness();
    // rentBusiness.addRent(rent);

    // rentBusiness.getRentedCarByUser(user);


    // TEST RentBusiness
    // TruckBusiness tb = new TruckBusiness();
    // tb.getTruckRentByUser(user);


    // rentBusiness.addRent(rent);
    // rentBusiness.removeUserRent(rent);
    // ArrayList<Car> lista;
    // lista = (ArrayList<Car>) cb.getFreeCar();
    // for(Car c :lista){
    // System.out.println(c.getId() +" " +c.getName());
    // }
    System.exit(0);
  }
}
