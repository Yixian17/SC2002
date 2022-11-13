package movieassignment;

import java.util.*;
import java.time.*;
import java.text.*;
import java.io.*;  // Import the File class
/**
 * This class creates movietiming objects
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class MovieTiming implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Movie2 movie;   
    private Cineplex cineplex;
    private Cinema cinema;
    private String cinemaCode;
    private String date;
    private String day; 
    private String time;
    private boolean Holiday;
    private AllSeats layout;
    
    /**
	 * This constructor creates a new movietiming object
	 * @param movie movie of the movietiming
	 * @param cineplex cineplex where the movie is going to be shown
	 * @param cinema cinema where the movie is going to be shown
	 * @throws EOFException if binary file is empty
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 */
    public MovieTiming(Movie2 movie, Cineplex cineplex, Cinema cinema) throws Exception {
    	Scanner input = new Scanner(System.in);
    	this.movie=movie;
    	this.cineplex=cineplex;
    	this.cinema=cinema;
    	this.cinemaCode = cinema.getCinemaCode();
    	System.out.print("Enter Date(YYYYMMDD):");
    	this.date = input.nextLine();
    	System.out.print("Enter Time(HHMM):");
    	this.time = input.nextLine();
		//day of the week -- automatically generate the day of week
    	String inputDate = date;
    	SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
    	Date dt1 = format1.parse(inputDate);
    	DateFormat format2 = new SimpleDateFormat("EEEE"); 
    	String finalDay = format2.format(dt1);
    	this.day = finalDay; 
    	//READ THE HOLIDAY TEXT FILE TO SEE IF TODAY IS A HOLIDAY 
    	//create new AllSeats object 
    	this.layout = new AllSeats(); 
    	this.Holiday = movieTimingList.readHoliday(this.date);
    }
    
   
    /**
	 * This method will get movie
	 * @return Movie2 movie
	 */
    public Movie2 getMovie() {return this.movie;}
    /**
	 * This method will get the Cinema hall number in int value
	 * @return int hall number integer
	 */
    public int getCinemaHall() {return this.cinema.getHallNumber();}
    /**
	 * This method will get Cinema code
	 * @return String Cinema Code
	 */
    public String getCinemaCode() {return this.cinemaCode;}
    /**
	 * This method will get the date in string
	 * @return String date YYYYMMDD
	 */
    public String getDate() {return this.date;}
    /**
	 * This method will get time in string
	 * @return String time in 24hr HHMM
	 */
    public String getTime() {return this.time;}
    /**
	 * This method will get the day of the movie
	 * example Monday,Tuesday
	 * @return String Day of the movie
	 */
    public String getDay() {return this.day;}
    /**
	 * This method will get whether the movietiming is on a holiday
	 * @return boolean whether movietiming is on a holiday
	 */
    public boolean getHoliday() {return this.Holiday;}
    /**
	 * This method will get Allseat object
	 * @return AllSeats Allseat with the seat arrangement
	 */
    public AllSeats getAllSeats() {return this.layout;}

    /**
   	 * This method will prompt the user to set date of the movietiming
   	 * @throws ParseException when wrong date format is encountered
   	 */
    public void setDate() throws ParseException {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter Date (YYYYMMDD):");
    	this.date = input.nextLine();
    	
    	String inputDate = date;
    	SimpleDateFormat format1 = new SimpleDateFormat("YYYYMMDD");
    	Date dt1 = format1.parse(inputDate);
    	DateFormat format2 = new SimpleDateFormat("EEEE"); 
    	String finalDay = format2.format(dt1);
    	this.day = finalDay;
    }
    /**
   	 * This method will prompt the user to set the time of the movietiming
   	 */
    public void setTime() {
    	Scanner input = new Scanner(System.in);
    	System.out.print("Enter Time(HHMM):");
    	this.time = input.nextLine();
    }
    /**
   	 * This method read holiday binary file and set boolean holiday to that value
	 * @throws EOFException if binary file is empty
	 * @throws FileNotFoundException if binary file cannot be created or edited
   	 */
    public void setHoliday() throws Exception{
    	this.Holiday = movieTimingList.readHoliday(this.date);
    }
    /**
   	 * This method will prompt the user to set the showing status of the movietiming
   	 */
    public void setMovieShowingStatus() {
    	this.getMovie().setShowingStatus();
    }
    
    /**
	 * This method will get the cineplex 
	 * @return cineplex cineplex location
	 */
    public Cineplex getCineplex() {
		return cineplex;
	}
	
	
	/**
	 * This method will get the Cinema
	 * @return Cinema Cinema
	 */
	public Cinema getCinema() {
		return cinema;
	}

	/**
	 * This method return information of the movie timing in String
	 * @return String movietiming information
	 */
	public String toString() {
      return "Cineplex: "+ getCineplex().getLocation() + "\n" +
    		  "Cinema Hall: "+ getCinemaHall()+ "\n" +
    		  "Movie: " + movie.getTitle() + "\n" +
    		  "Date: " + getDate() + "\n" +
    		  "Time: " + getTime()+ "\n";
    }
	
	/**
	 * This method will get the rating of the movie in double format
	 * @return double rating value
	 */
	public String getMovieShowingStatus(){
		return this.movie.printshowingStatus();
	}
	
	/**
	 * This method will print out all movie timing information
	 */
	public void printMovieTimingInfo() {
		System.out.println("Movie: " + getMovie().getTitle());
		System.out.println("Cineplex: " +getCineplex().getLocation());
		System.out.println("Cinema Hall Number: " + getCinema().getHallNumber());
		System.out.println("Cinema Hall Type: " + getCinema().getCinemaClass());
		System.out.println("(1) Date: " + getDate());
		System.out.println("(2) Time: " + getTime());
		System.out.println("(3) Movie Showing Status: "+getMovieShowingStatus());
		
	}
}


