package movieassignment;

import java.util.*;
import java.io.*;  // Import the File class
import java.io.Serializable;
/**
 * This class creates Cinema objects
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class Cinema implements Serializable {
	private static final long serialVersionUID = 1L;
	private String cinemaLocationAbb;
	private int hallNumber; //cinemaCode
	private int cinemaClass; // 1 classic, 2 gold, 3 platinum
	private ArrayList<MovieTiming> MovieTiming;
	/**
	 * This constructor creates a new cinema object
	 * @param cinemaLocationAbb 3 character abbreviation based on where the Cinema is located
	 */
	public Cinema(String cinemaLocationAbb)
	{
		Scanner input = new Scanner(System.in);
		this.cinemaLocationAbb = cinemaLocationAbb;
		System.out.print("Please enter the hall number: ");
		hallNumber=input.nextInt();
		System.out.println("Please enter the cinema class: ");
		System.out.println("1. Classic");
		System.out.println("2. Gold");
		System.out.println("3. Platinum");
		System.out.println("---------------------");
		cinemaClass=input.nextInt();
		input.nextLine();
		System.out.println("---------------------");
		System.out.println("Your cinema code is : "+ getCinemaCode());
	}
	/**
	 * This method gets the hall number of the cinema
	 * @return int hall number of the cinema
	 */
	public int getHallNumber() {
		return this.hallNumber;
	}
	/**
	 * This method sets the hall number of the cinema
	 * @param hallNumber hall int number to set to 
	 */
	public void setHallNumber(int hallNumber) {
		this.hallNumber = hallNumber;
	}
	/**
	 * This method gets the class of the cinema
	 * 1 classic, 2 gold, 3 platinum
	 * @return int class of the cinema 
	 */
	public int getCinemaClass(){
		return this.cinemaClass;
	}
	/**
	 * This method sets the cinema class
	 * 1 classic, 2 gold, 3 platinum
	 * @param cinemaType int number to set to 
	 */
	public void setCinemaClass(int cinemaType) {
		this.cinemaClass = cinemaType;
	}
	
	/**
	 * This method gets the string combining cinema hall number and cinema class
	 * example "AMKH1C1" meaning(AMK, Hall 1 , Classic)
	 * @return String combining cinema location, cinema hall number and cinema class
	 */
	public String getCinemaCode() { 
		return cinemaLocationAbb+ "HALL"+ hallNumber + "C" + cinemaClass;
	}
	/**
	 * This method gets the hall number and cinema class in a string
	 * converting 1 to classic, 2 to gold, 3 to platinum
	 * @return String combining cinema hall number and cinema class
	 */
	public String getCinemaDetails() { 
		String cinemaclassout="";
		switch (getCinemaClass())
		{
		case 1:cinemaclassout="Classic";
		break;
		case 2:cinemaclassout="Gold";
		break;
		case 3:cinemaclassout="Platinum";
		break;
		//case 1:
		}
		
			
		return 	"HALL: "+ hallNumber + " "+
				"Class: " + cinemaclassout;
	}
	/**
	 * This method adds a new movietiming to a cineplex
	 * @param cineplex the cineplex to add movietiming to
	 * @throws EOFException if binary file is empty
     * @throws FileNotFoundException if binary file cannot be created or edited
	 */
	public void addMovieTiming(Cineplex cineplex) throws Exception {
		MovieTiming.add(new MovieTiming(MovieListing2.chooseMovie(),cineplex,this));

	}
	/**
	 * This method removes movietiming based on the index
	 * @param index index int number to remove
	 */
	public void removeShowingTime(int index) {
		MovieTiming.remove(index);
	}
	/**
	 * This method list all movietiming 
	 */
	public void listMovieTiming() {
		System.out.println("Movie list:");
		for (int i = 0; i < MovieTiming.size(); i++) {
			System.out.println((i + 1) + ". " + MovieTiming.get(i).toString());
		}
	}
}
