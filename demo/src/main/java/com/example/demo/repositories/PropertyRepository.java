package com.example.demo.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mappers.PropertyRowMapper;
import com.example.demo.entities.Property;;

@Transactional
@Repository
public class PropertyRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public PropertyRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Property> getAllProperties() {
    String sql = "SELECT * FROM property";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<Property> getProperties(String address, int numRooms, int price) {
    String sql = "SELECT * FROM property WHERE address LIKE ? OR numrooms = ? OR price = ? ";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    String arg = "%" + address + "%";
    return this.jdbcTemplate.query(sql, new Object[] { arg, numRooms, price }, rowMapper);
  }

  public List<Property> getBookedProperties() {
    String sql = "SELECT distinct property.* FROM property join booking on property.id=booking.property_id ";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<Property> getReviewedProperties() {
    String sql = "SELECT distinct property.* FROM property join review on property.id=review.property_id ";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<Property> getOrderedPropertiess() {
    String sql = "SELECT * FROM property order by price";
    RowMapper<Property> rowMapper = new PropertyRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  // public void addProperty(Property property) {
  // String sql = "INSERT INTO property VALUES (?, ?, ?, ?,?)";
  // this.jdbcTemplate.update(sql, property.getAddress(), property.getNumRooms(),
  // property.getPrice(),
  // property.getAllowSmoking(), property.getMaxGuestNum());
  // sql = "SELECT id FROM property WHERE address = ?";
  // int id = jdbcTemplate.queryForObject(sql, Integer.class,
  // property.getAddress());

  // property.setId(id);
  // }

  // public void updateProperty(String oldAddress, String newAddress) {
  // String sql = "UPDATE property SET address = ? WHERE address = ?";
  // this.jdbcTemplate.update(sql, newAddress, oldAddress);
  // }

  // public void deleteProperty(int id) {
  // String sql = "DELETE FROM property WHERE id = ?";
  // this.jdbcTemplate.update(sql, id);
  // }

  public void createProperty(Property property) {
    String sql = "INSERT INTO property VALUES(?, ?, ?, ?, ?)";
    String address = property.getAddress();
    int numRooms = property.getNumRooms();
    int price = property.getPrice();
    boolean allowSmoking = property.getAllowSmoking();
    int maxGuestNum = property.getMaxGuestNum();
    this.jdbcTemplate.update(sql, address, numRooms, price, allowSmoking, maxGuestNum);

    String select = "SELECT TOP 1 id FROM property ORDER BY id DESC";
    int propertyId = this.jdbcTemplate.queryForObject(select, Integer.class);

    property.setId(propertyId);
  }

}