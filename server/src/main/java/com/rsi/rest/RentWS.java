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
import com.rsi.rest.model.ResposeList;
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
  public ResposeList getFreeCar() {
    List<Car> carList = new ArrayList<Car>();
    CarBusiness cb = new CarBusiness();
    carList = cb.getFreeCar();

    ResposeList freeCarList = new ResposeList();
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
  public Response getFreeTruckk() {
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
  public ResposeList isPasswordCorect(@PathParam("login") String login,
      @PathParam("password") String password) {
    UserBusiness user = new UserBusiness();
    User u = new User();

    ResposeList userList = new ResposeList();

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
  public Response addUser(ResposeList user) {
    UserBusiness userBusiness = new UserBusiness();
    userBusiness.addUser(user.getUser());
    return Response.status(200).entity("OK").build();
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/addCar")
  public Response addCar(ResposeList car) {
    CarBusiness carBusiness = new CarBusiness();
    carBusiness.addCar(car.getCar());
    return Response.status(200).entity("OK").build();
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/addTruck")
  public Response addTruck(ResposeList truck) {
    TruckBusiness truckBusiness = new TruckBusiness();
    truckBusiness.addTruck(truck.getTruck());
    return Response.status(200).entity("OK").build();
  }

  @POST
  @Produces(MediaType.TEXT_XML)
  @Path("/addRent")
  public Response addRent(ResposeList rent) {
    RentBusiness rentBusiness = new RentBusiness();
    rentBusiness.addRent(rent.getRent());
    return Response.status(200).entity("OK").build();
  }

}
