package movieassignment;

import java.util.*;
import java.io.*;  // Import the File class

/**
 * This class creates Review objects for the movie
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class Review implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String review;	//Text of the review
	private int rating;	//rating of the review
	
	/**
	 * This default constructor creates a new Review object without initialising anything
	 */
	public Review() {};
	
	/**
	 * This constructor creates a new Review object with initialisation
	 * @param r review rating out of 1 to 5
	 * @param rev String of the actual review text
	 */
	Review(int r, String rev){
		this.review = rev; 
		this.rating = r; 
	}
	/**
	 * This method gets the review in string
	 * @return String of the review text
	 */
	public String getReview() {
		return this.review; 
	}
	/**
	 * This method gets rating of the review out of 1 to 5
	 * @return int rating of the review 
	 */
	public int getRating() {
		return this.rating; 
	}
	/**
	 * This method sets the review rating
	 * @param r int of the rating to set review to
	 */
	public void setRating(int r)
	{
		this.rating = r;
	}
	/**
	 * This method sets the review rating
	 * @param review string of text of the review
	 */
	public void setReview(String review)
	{
		this.review = review;
	}

}
