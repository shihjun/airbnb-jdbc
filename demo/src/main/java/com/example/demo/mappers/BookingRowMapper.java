package com.example.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.demo.entities.Booking;

public class BookingRowMapper implements RowMapper<Booking> {
  @Override
  public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
    Booking booking = new Booking();
    booking.setUserId(row.getInt("user_id"));
    booking.setPropertyId(row.getInt("property_id"));
    booking.setId(row.getInt("id"));
    booking.setCheckInDate(row.getString("checkInDate"));
    booking.setCheckOutDate(row.getString("checkOutDate"));
    booking.setTotalPrice(row.getInt("totalPrice"));
    booking.setRemarks(row.getString("remarks"));
    booking.setNumGuest(row.getInt("numGuest"));
    return booking;
  }
}