package com.example.demo.mappers;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;
import com.example.demo.entities.Review;;

public class ReviewRowMapper implements RowMapper<Review> {
  @Override
  public Review mapRow(ResultSet row, int rowNum) throws SQLException {
    Review review = new Review();
    review.setPropertyId(row.getInt("property_id"));
    review.setId(row.getInt("id"));
    review.setRating(row.getInt("rating"));
    review.setRemark(row.getString("remark"));
    return review;
  }
}