package movieassignment;

import java.util.*;
import java.io.*;  // Import the File class
import java.time.*;
import java.text.*;

/**
 * This class creates booking for the movie goer ticket
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class Booking implements Serializable {

	private static final long serialVersionUID = 1L;
	//instance variables 
	private Cinema cinema; 
	private MovieTiming timing; 
	private TicketBuyer buyer; 
	private String transactionID;
	private int row; 
	private int col; 
	private Prices price; 
	private Cineplex cineplex;
	private double finalprice;
	

	/**
	 * This constructor creates a booking
	 * @param cinema cinema of the booking
	 * @param timing movietiming of the booking
	 * @param buyer Ticketbuyer object contain buyer details
	 * @param row row of the seat
	 * @param col column of the seat
	 * @param cineplex cineplex location
	 * @throws EOFException if binary file is empty
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 */	
	public Booking (Cinema cinema, MovieTiming timing, TicketBuyer buyer, int row, int col, Cineplex cineplex) throws Exception { 
		this.cinema = cinema; 
		this.timing = timing; 
		this.buyer = buyer; 
		this.row = row;
		this.col = col; 
		this.cineplex = cineplex;
		price = new Prices(timing.getMovie(), cinema, buyer, timing, timing.getAllSeats().getSeats(this.row, this.col)); 
	}
	
	/**
	 * This method will get cinema number of the booking
	 * @return Cinema cinema object
	 */
	public Cinema getCinema() { return this.cinema; } 
	/**
	 * This method will get the movie timing 
	 * @return Movietiming MovingTiming object
	 */
	public MovieTiming getMovieTiming() { return this.timing; } 
	/**
	 * This method will get the TicketBuyer 
	 * @return TicketBuyer TicketBuyer object
	 */
	public TicketBuyer getBuyer() { return this.buyer; }
	/**
	 * This method will get the Price of the booking
	 * @return Prices Prices object
	 */
	public Prices getPrice() {return this.price; } 
	/**
	 * This method will get cineplex of the booking
	 * @return Cineplex cineplex object
	 */
	public Cineplex getCineplex() {
		return cineplex;
	}	
	/**
	 * This method will get the transaction ID of the booking
	 * @return String transaction ID of the booking
	 */
	public String getTransactionID() {
		return transactionID;
	}
	/**
	 * This method will set the transaction ID of the booking
	 * @param transactionID transactionID to set to
	 */
	public void setTransactionID(String transactionID) {
		this.transactionID = transactionID;
	}

	
	//functions
	//generate TID 
	/**
	 * This method generates the transaction ID and sets it
	 * @param cinema of the booking
	 */
	public void generateTID (Cinema cinema) { 
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMddHHmm");
    	//Gets the current date and time
    	Date datetime = new Date();
		System.out.println("Transaction ID: " + cinema.getCinemaCode() + format1.format(datetime)); 
		transactionID="Transaction ID: " + cinema.getCinemaCode() + format1.format(datetime);
	}
	
	/**
	 * This method list the booking details after purchasing ticket
	 */
	public void displayBookingTicket() {
		System.out.println("---------------------");
		System.out.println("Your Booking Has been Confirmed! ");
		System.out.println("Date: "+timing.getDate()+"  "+"Show Time: " + timing.getTime());
		System.out.println("Name: " + this.buyer.getName());
		System.out.println("Mobile Number: " + this.buyer.getMobileNumber());
		System.out.println("Email Address: " + this.buyer.getEmailAddress());
		System.out.println("Seat Number: R" + (this.row+1) + ", C" + (this.col+1));
		System.out.println("Price: " + this.price.calculatePrice());
		finalprice=this.price.calculatePrice();
		generateTID(this.cinema); 
	}
	
	//for listing in booking history 
	/**
	 * This method list the booking details in booking history
	 */
	public void displayBooking() {
		System.out.println("Name: " + this.buyer.getName());
		System.out.println("Mobile Number: " + this.buyer.getMobileNumber());
		System.out.println("Email Address: " + this.buyer.getEmailAddress());
		System.out.println(this.cinema.getCinemaDetails());
		System.out.println("Show Time: " + timing.getTime() +"	"+ "Date: "+timing.getDate());
		//System.out.println("Cinema Class: " + this.cinema.getCinemaClass());
		
		System.out.println("Seat Number: " + (this.row+1) + "," + (this.col+1));
		System.out.println(getTransactionID());
		//System.out.println("Price: " + this.price.calculatePrice());
		//generateTID(this.cinema); 
	}
	/**
	 * This method gets the price of the booking after calculation for movie goer
	 * @return double final price of the booking
	 */
	public double getFinalprice() {
		return finalprice;
	}

	
}
