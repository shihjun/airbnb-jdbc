package com.example.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.demo.entities.Payment;;

public class PaymentRowMapper implements RowMapper<Payment> {
  @Override
  public Payment mapRow(ResultSet row, int rowNum) throws SQLException {
    Payment payment = new Payment();
    payment.setBookingId(row.getInt("bookingId"));
    payment.setId(row.getInt("id"));
    payment.setAmount(row.getInt("amount"));
    payment.setStatus(row.getString("status"));
    payment.setPaymentMethod(row.getString("paymentMethod"));
    return payment;
  }
}