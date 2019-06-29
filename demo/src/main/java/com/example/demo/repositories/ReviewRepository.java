package com.example.demo.repositories;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.mappers.ReviewRowMapper;
import com.example.demo.entities.Review;;;

@Transactional
@Repository
public class ReviewRepository {
  private final JdbcTemplate jdbcTemplate;

  @Autowired
  public ReviewRepository(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  public List<Review> getAllReviews() {
    String sql = "SELECT * FROM review";
    RowMapper<Review> rowMapper = new ReviewRowMapper();
    return this.jdbcTemplate.query(sql, rowMapper);
  }

  public List<Review> getReviewsByProperty(int id) {
    String sql = "SELECT * FROM review where property_id =?";
    RowMapper<Review> rowMapper = new ReviewRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { id }, rowMapper);
  }

  public List<Review> getReviewsByRating(int rating) {
    String sql = "SELECT * FROM review where rating > ?";
    RowMapper<Review> rowMapper = new ReviewRowMapper();
    return this.jdbcTemplate.query(sql, new Object[] { rating }, rowMapper);
  }

  public void createReview(int id, Review review) {
    String sql = "INSERT INTO review VALUES(?, ?, ?)";
    int rating = review.getRating();
    String remark = review.getRemark();
    this.jdbcTemplate.update(sql, rating, remark, id);

    String select = "SELECT TOP 1 id FROM review ORDER BY id DESC";
    int reviewId = this.jdbcTemplate.queryForObject(select, Integer.class);

    review.setId(reviewId);
    review.setPropertyId(id);
  }
}