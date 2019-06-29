package com.example.demo.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mappers.UserRowMapper;
import com.example.demo.entities.User;

@Transactional
@Repository
public class UserRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public UserRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<User> getAllUsers() {
    String sql = "SELECT * FROM \"user\"";
    RowMapper<User> rowMapper = new UserRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<User> getUsersByEmail(String email) {
    String sql = "SELECT * FROM \"user\" WHERE email = ?";
    RowMapper<User> rowMapper = new UserRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { email }, rowMapper);
  }

  // public void addUser(String firstName, String lastName, String email, String
  // phone) {
  // String sql = "INSERT INTO \"user\" VALUES (?, ?, ?, ?)";
  // this.jdbcTemplate.update(sql, firstName, lastName, email, phone);
  // }

  // public void updateUser(String oldFirstName, String newFirstName) {
  // String sql = "UPDATE \"user\" SET firstName = ? WHERE firstName = ?";
  // this.jdbcTemplate.update(sql, newFirstName, oldFirstName);
  // }

  // public void deleteUser(int id) {
  // String sql = "DELETE FROM \"user\" WHERE id = ?";
  // this.jdbcTemplate.update(sql, id);
  // }

  public void createUser(User user) {
    String sql = "INSERT INTO \"user\" VALUES(?, ?, ?, ?)";
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String email = user.getEmail();
    String phone = user.getPhone();
    this.jdbcTemplate.update(sql, firstName, lastName, email, phone);

    String select = "SELECT TOP 1 id FROM \"user\" ORDER BY id DESC";
    int userId = this.jdbcTemplate.queryForObject(select, Integer.class);

    user.setId(userId);
  }

  public void updateUser(int id, User user) {
    String sql = "UPDATE \"user\" SET firstName=?, lastName=?, email=?, phone=? WHERE id = ?";
    String firstName = user.getFirstName();
    String lastName = user.getLastName();
    String email = user.getEmail();
    String phone = user.getPhone();
    this.jdbcTemplate.update(sql, firstName, lastName, email, phone, id);

    user.setId(id);
  }
}
