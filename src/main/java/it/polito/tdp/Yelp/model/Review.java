package it.polito.tdp.Yelp.model;

import java.time.LocalDate;

public class Review {
	
	private String reviewId;
	private String business;  // private Business businessId
	private String user;	// private User userId
	private double stars;
	private LocalDate reviewDate;
	private int votesFunny;
	private int votesUseful;
	private int votesCool;
	private String reviewText;
	
	public Review(String reviewId, String business, String user, double stars, LocalDate reviewDate, int votesFunny,
			int votesUseful, int votesCool, String reviewText) {
		super();
		this.reviewId = reviewId;
		this.business = business;
		this.user = user;
		this.stars = stars;
		this.reviewDate = reviewDate;
		this.votesFunny = votesFunny;
		this.votesUseful = votesUseful;
		this.votesCool = votesCool;
		this.reviewText = reviewText;
	}

	public String getReviewId() {
		return reviewId;
	}

	public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public double getStars() {
		return stars;
	}

	public void setStars(double stars) {
		this.stars = stars;
	}

	public LocalDate getReviewDate() {
		return reviewDate;
	}

	public void setReviewDate(LocalDate reviewDate) {
		this.reviewDate = reviewDate;
	}

	public int getVotesFunny() {
		return votesFunny;
	}

	public void setVotesFunny(int votesFunny) {
		this.votesFunny = votesFunny;
	}

	public int getVotesUseful() {
		return votesUseful;
	}

	public void setVotesUseful(int votesUseful) {
		this.votesUseful = votesUseful;
	}

	public int getVotesCool() {
		return votesCool;
	}

	public void setVotesCool(int votesCool) {
		this.votesCool = votesCool;
	}

	public String getReviewText() {
		return reviewText;
	}

	public void setReviewText(String reviewText) {
		this.reviewText = reviewText;
	}
	
	
}
