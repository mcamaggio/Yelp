package it.polito.tdp.Yelp.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import it.polito.tdp.Yelp.model.Business;
import it.polito.tdp.Yelp.model.Review;
import it.polito.tdp.Yelp.model.User;

public class YelpDAO {
	
	public List<Business> readAllBusinesses() {
		String sql = "SELECT * FROM Business" ;
		
		List<Business> result = new ArrayList<>() ;
		
		Connection conn = DBConnect.getConnection() ;
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				result.add(new Business(
						res.getString("business_id"),
						res.getString("full_address"),
						res.getString("active"),
						res.getString("categories"),
						res.getString("city"),
						res.getInt("review_count"),
						res.getString("business_name"),
						res.getString("neighborhoods"),
						res.getDouble("latitude"),
						res.getDouble("longitude"),
						res.getString("state"),
						res.getDouble("stars")
						));
			}
			conn.close();
			return result ;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	public List<Review> getAllReviews(){
		String sql = "SELECT * FROM Reviews";
		List<Review> result = new ArrayList<Review>();
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;		
			ResultSet res = st.executeQuery();
			
			while(res.next()) {
				Review review = new Review(res.getString("review_id"),
						res.getString("business_id"),
						res.getString("user_id"),
						res.getDouble("stars"),
						res.getDate("review_date").toLocalDate(),
						res.getInt("votes_funny"),
						res.getInt("votes_useful"),
						res.getInt("votes_cool"),
						res.getString("review_text"));
				result.add(review);
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				
	}
	
	public List<User> getAllUsers() {
		String sql = "SELECT * FROM Users";
		List<User> result = new ArrayList<User>() ;
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			ResultSet res = st.executeQuery() ;
			while (res.next()) {
				User user = new User(res.getString("user id") ,
				res.getInt("votes_funny"),
				res. getInt ("votes useful"),
				res. getInt ("votes cool"),
				res.getString("name"),
				res. getDouble("average stars"),
				res.getInt ("review count")) ;
				result.add(user);
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				
	}
	
	public List<User> getUsersWithReviews(int minReviews) {
		String sql = "SELECT users.*, COUNT(reviews.review_id) AS numero "
				+ "FROM users, reviews "
				+ "WHERE reviews.user_id = users.user_id "
				+ "GROUP BY users.user_id "
				+ "HAVING COUNT(reviews.review_id) >= ?";
		List<User> result = new ArrayList<User>() ;
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setInt(1, minReviews);
			ResultSet res = st.executeQuery() ;
			while (res.next()) {
				User user = new User(res.getString("user_id"),
				res.getInt("votes_funny"),
				res.getInt ("votes_useful"),
				res.getInt ("votes_cool"),
				res.getString("name"),
				res.getDouble("average_stars"),
				res.getInt ("review_count"));
				result.add(user);
			}
			
			conn.close();
			return result;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
				
	}
	
	public int calcolaSimilarita(User u1, User u2, int anno) {
		
		String sql = "SELECT COUNT(*) AS similarita "
				+ "FROM reviews r1, reviews r2 "
				+ "WHERE r1.business_id = r2.business_id "
				+ "AND r1.user_id = ? "
				+ "AND r2.user_id = ? "
				+ "AND YEAR(r1.review_date) = ? "
				+ "AND YEAR(r2.review_date) = ? "
				+ "";
		
		Connection conn = DBConnect.getConnection();
		
		try {
			PreparedStatement st = conn.prepareStatement(sql) ;
			st.setString(1, u1.getUserId());
			st.setString(2, u2.getUserId());
			st.setInt(3, anno);
			st.setInt(4, anno);
			ResultSet res = st.executeQuery() ;
			
			res.first();
			int similarita = res.getInt("similarita");
			
			conn.close();
			return similarita;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return -1;
		}
	}
	
	public double averageStars(Business business) {
		String sql = "SELECT AVG(stars) AS avg_stars, COUNT(*) AS number "
				+ "FROM reviews "
				+ "WHERE business_id = ?";
		Connection conn = DBConnect.getConnection();
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, business.getBusinessId());
			ResultSet res = st.executeQuery();
			res.first();
			double stars = res.getDouble("avg_stars") ;
			conn.close();
			return stars ;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0 ;
		}
	}

}
