package com.rsi.rest.bean;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

import org.primefaces.event.SelectEvent;
import org.primefaces.json.JSONArray;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.rsi.rest.model.Car;
import com.rsi.rest.model.Rent;
import com.rsi.rest.model.ResponseList;
import com.rsi.rest.model.Truck;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

@ManagedBean
public class ContentBean {
  private String name;

  private String nameplates;

  private int spots;

  private float copacity;

  private List<Car> freeCarList;

  private Car selectedCarToRent;

  private List<Truck> freeTruckList;

  private Truck selectedTruckToRent;

  private List<Car> rentedCarList;

  private Car selectedCarToRemove;

  private List<Truck> rentedTruckList;

  private Truck selectedTruckToRemove;

  private UserLoginView userLoginView;

  private Rent rentByUser;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getNameplates() {
    return nameplates;
  }

  public void setNameplates(String nameplates) {
    this.nameplates = nameplates;
  }

  public int getSpots() {
    return spots;
  }

  public void setSpots(int spots) {
    this.spots = spots;
  }

  public float getCopacity() {
    return copacity;
  }

  public void setCopacity(float copacity) {
    this.copacity = copacity;
  }

  public List<Car> getFreeCarList() {
    return freeCarList;
  }

  public void setFreeCarList(List<Car> freeCarList) {
    this.freeCarList = freeCarList;
  }

  public Car getSelectedCarToRent() {
    return selectedCarToRent;
  }

  public void setSelectedCarToRent(Car selectedCarToRent) {
    this.selectedCarToRent = selectedCarToRent;
  }

  public List<Truck> getFreeTruckList() {
    return freeTruckList;
  }

  public void setFreeTruckList(List<Truck> freeTruckList) {
    this.freeTruckList = freeTruckList;
  }

  public Truck getSelectedTruckToRent() {
    return selectedTruckToRent;
  }

  public void setSelectedTruckToRent(Truck selectedTruckToRent) {
    this.selectedTruckToRent = selectedTruckToRent;
  }

  public List<Car> getRentedCarList() {
    return rentedCarList;
  }

  public void setRentedCarList(List<Car> rentedCarList) {
    this.rentedCarList = rentedCarList;
  }

  public Car getSelectedCarToRemove() {
    return selectedCarToRemove;
  }

  public void setSelectedCarToRemove(Car selectedCarToRemove) {
    this.selectedCarToRemove = selectedCarToRemove;
  }

  public List<Truck> getRentedTruckList() {
    return rentedTruckList;
  }

  public void setRentedTruckList(List<Truck> rentedTruckList) {
    this.rentedTruckList = rentedTruckList;
  }

  public Truck getSelectedTruckToRemove() {
    return selectedTruckToRemove;
  }

  public void setSelectedTruckToRemove(Truck selectedTruckToRemove) {
    this.selectedTruckToRemove = selectedTruckToRemove;
  }

  public UserLoginView getUserLoginView() {
    return userLoginView;
  }

  public void setUserLoginView(UserLoginView userLoginView) {
    this.userLoginView = userLoginView;
  }

  public Rent getRentByUser() {
    return rentByUser;
  }

  public void setRentByUser(Rent rentByUser) {
    this.rentByUser = rentByUser;
  }

  private ResponseList loadData(String urlString) {
    try {
      Client client = Client.create();
      WebResource webResource2 = client.resource(urlString);
      ClientResponse response2 =
          webResource2.accept(MediaType.APPLICATION_XML).get(ClientResponse.class);
      if (response2.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
      }

      InputStream is = webResource2.accept(MediaType.APPLICATION_XML).get(ClientResponse.class)
          .getEntityInputStream();
      JAXBContext jaxb = JAXBContext.newInstance(ResponseList.class);
      Unmarshaller jaxbUnmarshaller = jaxb.createUnmarshaller();
      ResponseList xml = (ResponseList) jaxbUnmarshaller.unmarshal(is);

      return xml;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  private ResponseList loadDataPostWithResponse(String urlString) {
    Client client = Client.create();
    WebResource wr = client.resource(urlString);
    ResponseList resp = new ResponseList();
    resp.setUser(userLoginView.getUserLoged());
    return wr.accept(MediaType.APPLICATION_XML).post(ResponseList.class, resp);
  }

  private ResponseList loadDataPostWithResponse(String urlString, ResponseList response) {
    Client client = Client.create();
    WebResource wr = client.resource(urlString);
    return wr.accept(MediaType.APPLICATION_XML).post(ResponseList.class, response);
  }

  private void addDataPost(String urlString, ResponseList response) {
    Client client = Client.create();
    WebResource wr = client.resource(urlString);
    wr.accept(MediaType.TEXT_XML).post(response);
  }

  @PostConstruct
  public void initialize() {

    setFreeCarList(loadData("http://localhost:8080/server/rest/freeCar").getCarList());

    setFreeTruckList(getFreeTruck());

    userLoginView = new UserLoginView();


    setRentedCarList(
        loadDataPostWithResponse("http://localhost:8080/server/rest/getRentedCarByUser")
            .getCarList());

    setRentedTruckList(
        loadDataPostWithResponse("http://localhost:8080/server/rest/getRentedTruckByUser")
            .getTruckList());


    rentByUser = new Rent();
  }

  public void addNewCar() {
    FacesMessage message = null;
    Car car = new Car();
    if (name != null && nameplates != null && spots > 1) {
      car.setName(name);
      car.setNameplates(nameplates);
      car.setSpots(spots);
      ResponseList resp = new ResponseList();
      resp.setCar(car);
      addDataPost("http://localhost:8080/server/rest/addCar", resp);
      freeCarList.add(car);
      message = new FacesMessage("Dodano samochód osobowy");
    }
    clearData();
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  public void addNewTruck() {
    FacesMessage message = null;
    Truck truck = new Truck();
    if (name != null && nameplates != null && copacity > 1) {
      truck.setName(name);
      truck.setNameplates(nameplates);
      truck.setCopacity(copacity);
      ResponseList resp = new ResponseList();
      resp.setTruck(truck);
      addDataPost("http://localhost:8080/server/rest/addTruck", resp);
      freeTruckList.add(truck);
      message = new FacesMessage("Dodano samochód ciężarowy");
    }
    clearData();
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  private void clearData() {
    setName(null);
    setNameplates(null);
    setCopacity(0);
    setSpots(0);
  }

  public ArrayList<Truck> getFreeTruck() {

    ArrayList<Truck> truckList = new ArrayList<Truck>();
    try {

      Client client = Client.create();
      WebResource webResource2 = client.resource("http://localhost:8080/server/rest/freeTruck");
      ClientResponse response2 =
          webResource2.accept(MediaType.APPLICATION_JSON).get(ClientResponse.class);
      if (response2.getStatus() != 200) {
        throw new RuntimeException("Failed : HTTP error code : " + response2.getStatus());
      }

      String output2 = response2.getEntity(String.class);

      JSONArray jsonArray = new JSONArray(output2);

      for (int i = 0; i < jsonArray.length(); i++) {
        Truck truck = new Truck();
        truck.setId((Integer) (jsonArray.getJSONObject(i).get("id")));
        truck.setName((String) (jsonArray.getJSONObject(i).get("name")));
        truck.setNameplates((String) (jsonArray.getJSONObject(i).get("nameplates")));
        truck.setState((Boolean) (jsonArray.getJSONObject(i).get("state")));
        truck.setCopacity(Float.valueOf(jsonArray.getJSONObject(i).get("copacity").toString()));

        truckList.add(truck);

      }


    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return truckList;

  }

  public void onRowSelect(SelectEvent event) {
    FacesMessage msg =
        new FacesMessage("Wybrany samochód osobowy", ((Car) event.getObject()).getName());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void onRowSelectTruck(SelectEvent event) {
    FacesMessage msg =
        new FacesMessage("Wybrany samochód cięzarowy", ((Truck) event.getObject()).getName());
    FacesContext.getCurrentInstance().addMessage(null, msg);
  }

  public void rentCar() {
    if (selectedCarToRent != null) {
      Rent r = new Rent();
      r.setUserId(getUserLoginView().getUserLoged());
      r.setCarId(selectedCarToRent);
      ResponseList resp = new ResponseList();
      resp.setRent(r);
      addDataPost("http://localhost:8080/server/rest/addRent", resp);
      freeCarList.remove(selectedCarToRent);
    }
  }

  public void rentTruck() {
    if (selectedTruckToRent != null) {
      Rent r = new Rent();
      r.setUserId(userLoginView.getUserLoged());
      r.setTruckId(selectedTruckToRent);
      ResponseList resp = new ResponseList();
      resp.setRent(r);
      addDataPost("http://localhost:8080/server/rest/addRent", resp);
      freeTruckList.remove(selectedTruckToRent);
    }
  }

  public void removeCar() {
    if (selectedCarToRemove != null) {
      Rent r = new Rent();
      r.setUserId(getUserLoginView().getUserLoged());
      r.setCarId(selectedCarToRemove);
      ResponseList resp1 = new ResponseList();
      resp1.setRent(r);
      setRentByUser(
          loadDataPostWithResponse("http://localhost:8080/server/rest/findRentByData", resp1)
              .getRent());

      ResponseList resp = new ResponseList();
      resp.setRent(rentByUser);
      addDataPost("http://localhost:8080/server/rest/removeRent", resp);
      rentedCarList.remove(selectedCarToRemove);
    }
  }

  public void removeTruck() {
    if (selectedTruckToRemove != null) {
      Rent r = new Rent();
      r.setUserId(userLoginView.getUserLoged());
      r.setTruckId(selectedTruckToRemove);
      ResponseList resp1 = new ResponseList();
      resp1.setRent(r);
      setRentByUser(
          loadDataPostWithResponse("http://localhost:8080/server/rest/findRentByData", resp1)
              .getRent());

      ResponseList resp = new ResponseList();
      resp.setRent(rentByUser);
      addDataPost("http://localhost:8080/server/rest/removeRent", resp);
      rentedTruckList.remove(selectedTruckToRemove);
    }
  }

  public void editUserProfile() {
    FacesMessage message = null;
    ResponseList resp = new ResponseList();
    resp.setUser(getUserLoginView().getUserLoged());

    Client client = Client.create();
    WebResource wr = client.resource("http://localhost:8080/server/rest/editUserProfile");
    wr.accept(MediaType.APPLICATION_XML).post(resp);

    message = new FacesMessage("Zmieniono dane");
    FacesContext.getCurrentInstance().addMessage(null, message);
  }

  public void generatePdf() {
    FacesMessage message = null;
    Client client = Client.create();
    WebResource wr = client.resource("http://localhost:8080/server/rest/generatePdf");

    String str;
    String folder = "C:/wygenerowanyPDF.pdf";
    ResponseList resp = new ResponseList();
    resp.setUser(userLoginView.getUserLoged());
    str = wr.post(String.class, resp);

    Document doc = new Document(PageSize.A4);
    try {
      PdfWriter.getInstance(doc, new FileOutputStream(folder));
      doc.open();
      doc.add(new Paragraph(str));
      doc.close();

      message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Sukces",
          "Wygenerowano plik PDF znajduje się on w katalogu: " + folder);
      FacesContext.getCurrentInstance().addMessage(null, message);

    } catch (FileNotFoundException e) {
      e.printStackTrace();
      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd:", "Błąd" + e);
      FacesContext.getCurrentInstance().addMessage(null, message);
    } catch (DocumentException e) {
      message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Błąd:", "Błąd" + e);
      FacesContext.getCurrentInstance().addMessage(null, message);
      e.printStackTrace();
    }

  }
}
