package com.example.demo.controllers;

import com.example.demo.entities.Booking;
import com.example.demo.repositories.BookingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class BookingController {

  @Autowired
  BookingRepository bookingRepository;

  @GetMapping(value = "/allbookings", produces = "application/json")
  public List<Booking> displayBookings() {
    return bookingRepository.getAllBookings();
  }

  @GetMapping(value = "/properties/{id}/bookings", produces = "application/json")
  public List<Booking> displayBookingsByProperty(@PathVariable("id") int id) {
    return bookingRepository.getBookingsByProperty(id);
  }

  @PostMapping(value = "/bookings/{id}", produces = "application/json")
  public Booking updateBooking(@RequestBody Booking booking, @PathVariable("id") int id) {
    bookingRepository.updateBooking(id, booking);
    return booking;
  }

}