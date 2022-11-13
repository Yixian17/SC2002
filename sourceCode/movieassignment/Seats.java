package movieassignment;
import java.util.*;
import java.io.*;  // Import the File class
/**
 * This class creates individual seat objects
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class Seats implements Serializable {
	

	private static final long serialVersionUID = 1L;
	private int row;	//row number of the seat
	private int col;	//column number of the seat
	private String typeOfSeat;	//String the type of seat example, couple, elite
	private boolean assigned;	//boolean of wether the seat has been assigned
	

	/**
	 * This constructor initialise the row and column of the seat and the type of seat based on the layout of the cinema hall
	 * @param row int row number where the seat is located
	 * @param col int column number where the seat is located
	 */
	public Seats(int row, int col) {
		//Seat Selection 
		this.row = row; 
		this.col = col; 
		//Seat type 
		if (row < 3)
			this.typeOfSeat = "Normal"; 
		if (row == 3) 
			this.typeOfSeat = "Couple"; 
		if (row == 4)
			this.typeOfSeat = "Elite"; 
		if (row == 5)
			this.typeOfSeat = "Ultima"; 
	}
	
	/**
	 * This method gets row number of the seat
	 * @return int row of seat
	 */
	public int getRow() {return this.row; } 
	/**
	 * This method gets column number of the seat
	 * @return int column of seat
	 */
	public int getCol() {return this.col; } 
	/**
	 * This method gets type of seat
	 * @return String type of seat
	 */
	public String getSeatType() { return this.typeOfSeat;}
	/**
	 * This method return wether the seat is sold and assigned to another user
	 * @return boolean of the seat if its assigned or not
	 */
	public boolean isAssigned(){return this.assigned;}

	/**
	 * This method sets row number of the seat
	 * @param row int to assign the seat to
	 */
	public void setRow(int row) {this.row = row;}
	/**
	 * This method sets column number of the seat
	 * @param col int to assign the seat to
	 */
	public void setCol(int col) {this.col = col;} 

	/**
	 * This method assign the seat and create a new booking upon assigning 
	 * @param row int of the seat
	 * @param col int of the seat
	 * @param cinema the cinema where the seat is in
	 * @param timing the movie timing of the movie
	 * @param cineplex the cineplex location
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 * @throws EOFException if binary file is empty
	 */
	public void assignSeat(int row, int col, Cinema cinema, MovieTiming timing, Cineplex cineplex) throws Exception { //change to text file (?) 
		this.row = row; 
		this.col = col;
		this.assigned = true; 
		//ask for ticket buyer info 
		Scanner sc = new Scanner (System.in); 
		System.out.println("Please enter your name: ");
		String name = sc.next(); 
		System.out.println("Please enter your phone number: ");
		int num= sc.nextInt(); 
		System.out.println("Please enter your email address: ");
		String email= sc.next(); 
		System.out.println("Please enter your age: ");
		int age = sc.nextInt(); 
		TicketBuyer buyer = new TicketBuyer(name, num, email, age); 
		Booking booking = new Booking(cinema, timing, buyer, this.row, this.col, cineplex);//cineplex choose cinema
		booking.displayBookingTicket();
		//Transaction obj = new Transaction(booking.getMovieTiming().getMovie().getTitle(),booking.getFinalprice());
		//need to write to the booking history file 
//		System.out.println("total num of bookinggg  "+ cineplex.getBookingHistory().getNum());
		try {
		cineplex.getBookingHistory().createBookingInput(booking);
		}
		catch(Exception e) {cineplex.getBookingHistory().createFirstBookingInput(booking);}	
//		cineplex.getBookingHistory().showBookingHistory(booking);
//		buyer.getBookingHistory().createFirstBookingInput(booking); 
	}
	
//	//functions 
//	//produce/display a details of seat selected 
//	public void displaySeatDetails() {
//		System.out.println("Displaying Seat Details: ");
//		System.out.println("Seat Number: " + this.getRow() + this.getCol());
//		System.out.println("Seat Type: " + this.getSeatType());
//		//System.out.println("Price: " + this.getPrice() );
//	}
//	
}
