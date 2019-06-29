package com.example.demo.controllers;

import com.example.demo.entities.Property;
import com.example.demo.repositories.PropertyRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PropertyController {

  @Autowired
  PropertyRepository propertyRepository;

  @GetMapping(value = "/allproperties", produces = "application/json")
  public List<Property> displayProperties() {
    return propertyRepository.getAllProperties();
  }

  @GetMapping(value = "/properties", produces = "application/json")
  public List<Property> displayProperties(@RequestParam("address") String address,
      @RequestParam("numrooms") int numRooms, @RequestParam("price") int price) {
    return propertyRepository.getProperties(address, numRooms, price);
  }
  // http://localhost:8080/api/properties?address=88&numrooms=2&price=35000

  @GetMapping(value = "/bookedProperties", produces = "application/json")
  public List<Property> displayBookedProperties() {
    return propertyRepository.getBookedProperties();
  }

  @GetMapping(value = "/reviewedProperties", produces = "application/json")
  public List<Property> displayReviewedProperties() {
    return propertyRepository.getReviewedProperties();
  }

  @GetMapping(value = "/orderedProperties", produces = "application/json")
  public List<Property> displayOrderedProperties() {
    return propertyRepository.getOrderedPropertiess();
  }

  // @GetMapping(value = "/create_property")
  // public void addProperty(@RequestParam("address") String address,
  // @RequestParam("numRooms") int numRooms,
  // @RequestParam("price") int price, @RequestParam("allowSmoking") boolean
  // allowSmoking,
  // @RequestParam("maxGuestNum") int maxGuestNum) {
  // Property property = new Property();
  // property.setAddress(address);
  // property.setAllowSmoking(allowSmoking);
  // property.setMaxGuestNum(maxGuestNum);
  // property.setNumRooms(numRooms);
  // property.setPrice(price);
  // propertyRepository.addProperty(property);
  // }

  // http://localhost:8080/api/create_property?address=Test
  // address&numRooms=100&price=1000000&allowSmoking=true&maxGuestNum=100

  // @GetMapping(value = "/update_property")
  // public void updateProperty() {
  // propertyRepository.updateProperty("test property", "new property");
  // }

  // @GetMapping(value = "/delete_property")
  // public void deleteProperty() {
  // propertyRepository.deleteProperty(5);
  // }

  @PostMapping(value = "/properties", produces = "application/json")
  public Property createProperty(@RequestBody Property property) {
    propertyRepository.createProperty(property);
    return property;
  }

}