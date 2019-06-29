package com.example.demo.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mappers.BookingRowMapper;
import com.example.demo.entities.Booking;

@Transactional
@Repository
public class BookingRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public BookingRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Booking> getAllBookings() {
    String sql = "SELECT * FROM booking";
    RowMapper<Booking> rowMapper = new BookingRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<Booking> getBookingsByProperty(int id) {
    String sql = "SELECT * FROM booking where property_id =?";
    RowMapper<Booking> rowMapper = new BookingRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { id }, rowMapper);
  }

  public void updateBooking(int id, Booking booking) {
    String sql = "UPDATE booking SET totalPrice=? WHERE id = ?";
    int totalPrice = booking.getTotalPrice();
    this.jdbcTemplate.update(sql, totalPrice, id);

    String selectCheckInDate = "SELECT checkInDate FROM booking  WHERE id = ?";
    String checkInDate = this.jdbcTemplate.queryForObject(selectCheckInDate, new Object[] { id }, String.class);
    String selectCheckOutDate = "SELECT checkOutDate FROM booking  WHERE id = ?";
    String checkOutDate = this.jdbcTemplate.queryForObject(selectCheckOutDate, new Object[] { id }, String.class);
    String selectRemarks = "SELECT remarks FROM booking  WHERE id = ?";
    String remarks = this.jdbcTemplate.queryForObject(selectRemarks, new Object[] { id }, String.class);
    String selectNumGuest = "SELECT numGuest FROM booking  WHERE id = ?";
    int numGuest = this.jdbcTemplate.queryForObject(selectNumGuest, new Object[] { id }, Integer.class);
    String selectUserId = "SELECT user_id FROM booking  WHERE id = ?";
    int userId = this.jdbcTemplate.queryForObject(selectUserId, new Object[] { id }, Integer.class);
    String selectPropertyId = "SELECT property_id FROM booking  WHERE id = ?";
    int propertyId = this.jdbcTemplate.queryForObject(selectPropertyId, new Object[] { id }, Integer.class);

    booking.setId(id);
    booking.setCheckInDate(checkInDate);
    booking.setCheckOutDate(checkOutDate);
    booking.setRemarks(remarks);
    booking.setNumGuest(numGuest);
    booking.setUserId(userId);
    booking.setPropertyId(propertyId);
  }

}