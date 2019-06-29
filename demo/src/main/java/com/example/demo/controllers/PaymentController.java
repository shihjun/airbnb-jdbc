package com.example.demo.controllers;

import com.example.demo.entities.Payment;
import com.example.demo.repositories.PaymentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class PaymentController {

  @Autowired
  PaymentRepository paymentRepository;

  @GetMapping(value = "/allpayments", produces = "application/json")
  public List<Payment> displayPayments() {
    return paymentRepository.getAllPayments();
  }

  @GetMapping(value = "/bookings/{id}/payments", produces = "application/json")
  public List<Payment> displayPaymentsForBooking(@PathVariable("id") int id) {
    return paymentRepository.getPaymentsForBooking(id);
  }
}