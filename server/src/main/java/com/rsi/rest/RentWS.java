package com.rsi.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.JSONArray;

import com.rsi.rest.business.CarBusiness;
import com.rsi.rest.business.RentBusiness;
import com.rsi.rest.business.TruckBusiness;
import com.rsi.rest.business.UserBusiness;
import com.rsi.rest.model.Car;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.ResponseList;
import com.rsi.rest.model.Truck;
import com.rsi.rest.model.User;

@Path("/")
public class RentWS {

  @GET
  @Produces(MediaType.TEXT_HTML)
  public Response getStartingPage() {
    String output = "<h1>Hello World!<h1>" + "<p>RESTful Service is running ... <br>Ping @ "
        + new Date().toString() + "</p<br>";
    return Response.status(200).entity(output).build();
  }

  @Path("/freeCar")
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public ResponseList getFreeCar() {
    List<Car> carList = new ArrayList<Car>();
    CarBusiness cb = new CarBusiness();
    carList = cb.getFreeCar();

    ResponseList freeCarList = new ResponseList();
    freeCarList.setCarList(carList);

    return freeCarList;
  }

  // @Path("/freeTruck")
  // @GET
  // @Produces(MediaType.APPLICATION_XML)
  // public ResposeList getFreeTruck() {
  // List<Truck> truckList = new ArrayList<Truck>();
  // TruckBusiness tb = new TruckBusiness();
  // truckList = tb.getFreeTrack();
  //
  // ResposeList freeTruckList = new ResposeList();
  // freeTruckList.setTruckList(truckList);
  //
  // return freeTruckList;
  // }

  @Path("/freeTruck")
  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getFreeTruck() {
    List<Truck> truckList = new ArrayList<Truck>();
    TruckBusiness tb = new TruckBusiness();
    truckList = tb.getFreeTrack();

    JSONArray jsArray = new JSONArray(truckList);

    String result = jsArray.toString();
    return Response.status(200).entity(result).build();
  }

  @GET
  @Path("/isPasswordCorrect/{login}/{password}")
  @Produces(MediaType.APPLICATION_XML)
  public ResponseList isPasswordCorect(@PathParam("login") String login,
      @PathParam("password") String password) {
    UserBusiness user = new UserBusiness();
    User u = new User();

    ResponseList userList = new ResponseList();

    u = user.getUserByLogin(login);
    if (u.getPassword().equals(password)) {
      userList.setUser(u);
      return userList;
    } else {
      userList.setUser(null);
      return userList;
    }
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/addUser")
  public Response addUser(ResponseList user) {
    UserBusiness userBusiness = new UserBusiness();
    userBusiness.addUser(user.getUser());
    return Response.status(200).entity("OK").build();
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/addCar")
  public Response addCar(ResponseList car) {
    CarBusiness carBusiness = new CarBusiness();
    carBusiness.addCar(car.getCar());
    return Response.status(200).entity("OK").build();
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/addTruck")
  public Response addTruck(ResponseList truck) {
    TruckBusiness truckBusiness = new TruckBusiness();
    truckBusiness.addTruck(truck.getTruck());
    return Response.status(200).entity("OK").build();
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/addRent")
  public Response addRent(ResponseList rent) {
    RentBusiness rentBusiness = new RentBusiness();
    rentBusiness.addRent(rent.getRent());
    return Response.status(200).entity("OK").build();
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/removeRent")
  public Response removeRent(ResponseList rent) {
    RentBusiness rb = new RentBusiness();
    rb.removeUserRent(rent.getRent());
    return Response.status(200).entity("OK").build();
  }

  @Path("/findRentByData")
  @POST
  @Produces(MediaType.APPLICATION_XML)
  public ResponseList findRentByData(ResponseList rent) {
    RentBusiness rb = new RentBusiness();
    Rent rentID = new Rent();
    rentID = rb.findRentByData(rent.getRent());

    ResponseList findRent = new ResponseList();
    findRent.setRent(rentID);

    return findRent;
  }

  @Path("/getCarRentByMe")
  @POST
  @Produces(MediaType.APPLICATION_XML)
  public ResponseList getCarRentByMe(ResponseList user) {
    List<Rent> rentCarByMeList = new ArrayList<Rent>();
    RentBusiness rentBusiness = new RentBusiness();
    rentCarByMeList = rentBusiness.getRentedCarByUser(user.getUser());

    ResponseList rentByMe = new ResponseList();
    rentByMe.setRentList(rentCarByMeList);

    return rentByMe;
  }

  @Path("/getRentedTruckByUser")
  @POST
  @Produces(MediaType.APPLICATION_XML)
  public ResponseList getRentedTruckByUser(ResponseList user) {
    List<Truck> rentedTruckList = new ArrayList<Truck>();
    TruckBusiness tb = new TruckBusiness();
    rentedTruckList = tb.getTruckRentByUser(user.getUser());

    ResponseList rentByMe = new ResponseList();
    rentByMe.setTruckList(rentedTruckList);

    return rentByMe;
  }

  @Path("/getRentedCarByUser")
  @POST
  @Produces(MediaType.APPLICATION_XML)
  public ResponseList getRentedCarByUser(ResponseList user) {
    List<Car> rentedCarList = new ArrayList<Car>();
    CarBusiness cb = new CarBusiness();
    rentedCarList = cb.getCarRentByUser(user.getUser());

    ResponseList rentByMe = new ResponseList();
    rentByMe.setCarList(rentedCarList);

    return rentByMe;
  }

  // TODO: metoda do generowania PDF

  // TODO: metoda do edycji profilu uzytkownika
}
