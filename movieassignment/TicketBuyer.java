package movieassignment;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.Serializable;
/**
 * This class creates ticket buyer to store detail of the movie goer
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class TicketBuyer implements Serializable{
		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//private static final long serialVersionUID = -2658405035770636518L;
		//instance variables 
		private String name; 
		private int mobilenumber; 
		private String emailaddress; 
		private int age; 
		private BookingHistory history; 
		//booking history 
		
		/**
		 * This constructor initialise the name , mobile number , email address and the age of the movie goer
		 * @param name String of name of movie goer
		 * @param mobilenumber int of movie goer
		 * @param emailaddress string of movie goer
		 * @param age int of movie goer
	     * @throws FileNotFoundException if binary file cannot be created or edited
	     * @throws EOFException if binary file is empty
		 */
		public TicketBuyer(String name, int mobilenumber, String emailaddress, int age) throws Exception {
			this.name = name; 
			this.mobilenumber = mobilenumber; 
			this.emailaddress = emailaddress;  
			this.age = age; 
			//create a booking for the ticket buyer 
			this.history = new BookingHistory();
		}
		
		/**
		 * This method gets name of the movie goer
		 * @return String name of movie goer
		 */ 
		public String getName () { return this.name; } 
		/**
		 * This method gets mobile number of movie goer
		 * @return int mobile number of movie goer
		 */
		public int getMobileNumber() { return this.mobilenumber; } 
		/**
		 * This method gets email address of movie goer
		 * @return String email of movie goer
		 */
		public String getEmailAddress() { return this.emailaddress; } 
		/**
		 * This method gets age of movie goer
		 * @return int age
		 */
		public int getAge() {return this.age;} 
		/**
		 * This method returns the bookinghistory object of the movie goer
		 * @return BookingHistory of movie goer
		 */
		public BookingHistory getBookingHistory() {return this.history;}
		 
		/**
		 * This method sets name of movie goer
		 * @param name string of movie goer to set to
		 */
		public void setName (String name) {this.name = name;} 
		/**
		 * This method sets mobile number of movie goer
		 * @param mobilenumber int of movie goer to set to
		 */
		public void setMobileNumber(int mobilenumber) {this.mobilenumber = mobilenumber; } 
		/**
		 * This method sets email address of movie goer
		 * @param emailaddress string of movie goer to set to
		 */
		public void setEmailAddress(String emailaddress) { this.emailaddress = emailaddress;}
		/**
		 * This method sets age of movie goer
		 * @param age int of movie goer to set to
		 */
		public void setAge(int age) {this.age = age;} 
		//public void setBookingHistory(BookingHistory history) {this.history = history;}

	
}
