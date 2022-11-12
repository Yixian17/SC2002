package movieassignment;
import java.util.*;
import java.io.*;  // Import the File class
/**
*This class creates Cineplexes
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
*/
public class Cineplex implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String location;
	private String locationAbb;
	private int cinemaNum;
	private ArrayList<Cinema> cinema = new ArrayList<Cinema>();
	private BookingHistory bookingHistory;
	private int Top5showing=0; //0=Sales ; 1=rating
	
	


	/**
	*The constructor creates a new cineplex with 3 new cinema's 
	*@param newCineplexName Full name of the new cineplex to be created
	*@param newlocationAbb The 3 character abbreviation of the new cineplex to be created
	* @throws FileNotFoundException if binary file cannot be created or edited
	* @throws EOFException if binary file is empty
	*/
	public Cineplex(String newCineplexName, String newlocationAbb) throws Exception
	{
		location=newCineplexName; //Cineplex name = location name 
		locationAbb=newlocationAbb;	
		threeNewCinema();
		bookingHistory = new BookingHistory();
	}
	
	/**
	*The method gets the bookingHistory object
	*@return BookingHistory object
	*/
	public BookingHistory getBookingHistory() {
		return bookingHistory;
	}

	/**
	*The method gets the location of the cineplex
	*@return string of cineplex location 
	*/
	public String getLocation() {
		return this.location;
	}
	
	/**
	*The method prompts the user to enter New Cineplex name sets the location of the Cineplex
	*/
	public void setCineplexLocation() {
		Scanner input = new Scanner(System.in);
		System.out.println("Enter New Cineplex name");
		String newLocation = input.nextLine();
		this.location = newLocation; 
	}
	/**
	*The method gets the 3 character abbreviation of the Cineplex
	*@return string of cineplex 3 letter abbreviation
	*/
	public String getLocationAbb() {
		return this.locationAbb;
	}
	
	/**
	*The method sets the 3 character abbreviation of the Cineplex
	*@param locationAbb Cineplex 3 letter abbreviation in string
	*/
	public void setLocationAbb(String locationAbb) {
		this.locationAbb = locationAbb; 
	}
	/**
	*The method gets the number of cinema's in the Cineplex
	*@return int of the number cineplex 3 letter abbreviation
	*/
	public int getCinemaNum() {
		return this.cinemaNum;
	}
	/**
	*The method sets the number of cinema's in the Cineplex
	*@param cinemaNum The number of cinema's in cineplex to set to
	*/
	public void setCinemaNum(int cinemaNum) {
		this.cinemaNum = cinemaNum; 
	}
	/**
	*The method gets the Arraylist of cinema objects
	*@return ArrayList of cinema objects
	*/
	public ArrayList<Cinema> getCinemas(){
	        return cinema;
	}
	/**
	*The method will print all the cinema's of the cineplex
	*/
	public void showCinema() {
	for(int i=0;i<this.getCinemas().size();i++)
		
		System.out.println("-- Cinema("+ (i+1) +") "+getCinemas().get(i).getCinemaCode());
		
	}
	/**
	*The method create a new cinema object and add it to the Arraylist of cinema
	* @throws FileNotFoundException if binary file cannot be created or edited
	* @throws EOFException if binary file is empty
	*/
	public void addCinema() throws Exception {
		Cinema newCinema = new Cinema (locationAbb);
		cinema.add(newCinema);
		cinemaNum++;
	}

	/**
	*The method removes cinema object from the Arraylist of cinema
	*@param index index location of the cinema to be removed
	*/
	public void removeCinema(int index) {
		cinema.remove(index);
	}

	/**
	*The method creates 3 new cinema object and add it to the Arraylist of cinema
	*@throws FileNotFoundException if binary file cannot be created or edited
	* @throws EOFException if binary file is empty
	*/
	public void threeNewCinema() throws Exception{ 
        //ArrayList <Cinema> cine = new ArrayList<Cinema>();
        System.out.println("---------------------");
        System.out.println("Create 3 cinemas upon creating a new cineplex:");
        System.out.println("---------------------");
        for (int i = 0;i<3;i++) {
            Cinema newCinema = new Cinema(locationAbb);
            cinema.add(newCinema);
            cinemaNum++;
        }
    }

	/**
	*The method prints all the information of the cineplex including the location name, location Abbreviation, the number of cinema's with the relevant cinema details
	*/
	public void printCineplexInfo() {

		System.out.println("=====INFORMATION=====");
		System.out.println("(1) Location: " + getLocation());
		System.out.println("(2) Location Abbreviation: " + getLocationAbb());
		System.out.println("(3) Number of Cinema: " + getCinemaNum());
		
	}
	/**
	*The method gets the top 5 showing setting of the cineplex by default will show top 5 based on ticket sales
	*@return int configured int of the Top 5 setting 0=Sales 1=rating
	*/
	public int getTop5showing() {
		return Top5showing;
	}

	/**
	*The method sets the top 5 showing setting of the cineplex
	*@param top5showing either 0 or 1 to show based on top 5 based on ticket sales or show top 5 based on movie rating
	*/
	public void setTop5showing(int top5showing) {
		Top5showing = top5showing;
	}

}

