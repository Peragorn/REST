package com.rsi.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.rsi.rest.business.CarBusiness;
import com.rsi.rest.business.TruckBusiness;
import com.rsi.rest.model.Car;
import com.rsi.rest.model.ResposeList;
import com.rsi.rest.model.Truck;

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

  @Path("/freeTruck")
  @GET
  @Produces(MediaType.APPLICATION_XML)
  public ResposeList getFreeTruck() {
    List<Truck> truckList = new ArrayList<Truck>();
    TruckBusiness tb = new TruckBusiness();
    truckList = tb.getFreeTrack();

    ResposeList freeTruckList = new ResposeList();
    freeTruckList.setTruckList(truckList);

    return freeTruckList;
  }

}
