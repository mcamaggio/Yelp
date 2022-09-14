package it.polito.tdp.Yelp.db;

import java.util.List;
import it.polito.tdp.Yelp.model.User;

public class TestDAO {

	public static void main(String[] args) {
		YelpDAO dao = new YelpDAO();
		
		List<User> utenti = dao.getUsersWithReviews(200);
		System.out.println(utenti);
		System.out.println(utenti.size());

	}

}
