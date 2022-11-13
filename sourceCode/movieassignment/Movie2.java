package movieassignment;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;

/**
 *  The available Movie Ratings
 */
enum AllMovieRatings{
	G,
	PG,
	NC16,
	M18,
	R21
}

/**
 * This class creates Movie objects
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class Movie2 implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String movieTitle;
	private String movieType;
	private int showingStatus;
	// coming soon = 1 , preview = 2, now showing = 3, end of showing = 4;
	private String synopsis;
	private String director;
	private int castNum;
	private ArrayList<String> cast = new ArrayList<String>();
	//private double rating;
	//private ArrayList<Review> reviews = new ArrayList<Review>();
	private double rating;
	private int numberOfReviews = 0;
	//private int ticketSales;
	private ArrayList<Review> reviews;
	private String movieRating;
	private String allMovieTypes[]=new String[] {"Blockbuster","3D","Normal"};

	//private String movieRatingArr[]=new String[] {"G","PG","NC16","M18","R21"};
	//movieRating
	


	private static final DecimalFormat df = new DecimalFormat("0.0");
	
	/**
	 * This constructor creates a new movie object
	 * It will prompt user to enter all details required to create new movie object
	 */
	public Movie2(){
		Scanner input = new Scanner(System.in);
		System.out.print("Please input movie title: ");
		this.movieTitle = input.nextLine();
		System.out.println("Please input showing status:");
		System.out.println("1. coming soon");
		System.out.println("2. preview");
		System.out.println("3. now showing");
		this.showingStatus = Integer.parseInt(input.nextLine());
		//input.nextLine();
		System.out.println("Please input movie type: ");
		System.out.println("1. Blockbuster");
		System.out.println("2. 3D");
		System.out.println("3. Normal");
		this.movieType = allMovieTypes[Integer.parseInt(input.nextLine())-1];
		System.out.println("Please input movie Rating: ");
		System.out.println("1. G");
		System.out.println("2. PG");
		System.out.println("3. NC16");
		System.out.println("4. M18");
		System.out.println("5. R21");
		this.movieRating = AllMovieRatings.values()[Integer.parseInt(input.nextLine())-1].toString();
		//input.nextLine();
		System.out.print("Please input synopsis: ");
		this.synopsis = input.nextLine();
		System.out.print("Please input director: ");
		this.director = input.nextLine();
		System.out.print("Please input number of casts: ");
		castNum = input.nextInt();
		input.nextLine();
		for (int i = 0; i < castNum; i++) {
			System.out.print("Please input cast " + (i + 1) + " : ");
			cast.add(input.nextLine());
		}
		
		this.reviews = new ArrayList<Review>();
	}
	
	/**
	 * This method adds a new review to the movie
	 * The rating will be adjusted based on the review score
	 * @param review object to be added to the reviews arraylist
	 */
	public void addReview(Review review) {
		reviews.add(review);
		rating = (rating * numberOfReviews + review.getRating()) / (numberOfReviews+1);
		numberOfReviews += 1;
	}
	/**
	 * This method will print out all the review and the rating of all the review for the movie
	 */
	public void getReview() {
		for(Review r : reviews)
		{
			System.out.println("Review: " + r.getRating());
			System.out.println("Review: " + r.getReview());
		}
	}
	
	/**
	 * This method will get the rating of the movie in double format
	 * @return double rating value
	 */
	
	public double getRating() {
		if (numberOfReviews>1)
		return this.rating;
		else return 0;
	}
	/**
	 * This method will get the title of the movie
	 * @return String title of movie
	 */
	public String getTitle() {
		return this.movieTitle; 
	}
	/**
	 * This method will get the suggested viewer rating of the movie
	 * example G, PG, M18 
	 * @return String suggested viewer rating
	 */
	public String getMovieRating() {
		return movieRating;
	}
	/**
	 * This method will get the movie type based on index of choice from all the available movie types 
	 * example "Blockbuster","3D","Normal"
	 * @param choice index of Movie type to return
	 * @return String of chosen Movie type
	 */
	public String getAllMovieTypes(int choice) {
		return allMovieTypes[choice];
	}
	/**
	 * This method will set all the movie type
	 * @param allMovieTypes String array of all movie type to be set
	 * * example "Blockbuster","3D","Normal"
	 */
	public void setAllMovieTypes(String[] allMovieTypes) {
		this.allMovieTypes = allMovieTypes;
	}
	/**
	 * This method will prompt the user to enter new movie rating and sets it
	 * example G, PG, M18 
	 */
	public void setMovieRating() {
		Scanner input = new Scanner(System.in);
		System.out.println("Please input movie Rating: ");
		System.out.println("1. G");
		System.out.println("2. PG");
		System.out.println("3. NC16");
		System.out.println("4. M18");
		System.out.println("5. R21");
		this.movieRating =AllMovieRatings.values()[Integer.parseInt(input.nextLine())-1].toString();
		
		//this.movieRating = movieRating;
	}
	/**
	 * This method will prompt the user to enter new movie title and sets it
	 * example G, PG, M18 
	 */
	public void setTitle()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter movie title: ");
		String movietitle = input.nextLine();
		this.movieTitle=movietitle;
	}
	/**
	 * This method will get the movie showing status in int representation
	 * coming soon = 1 , preview = 2, now showing = 3, end of showing = 4;
	 * @return int showing status of the movie
	 */
	public int getShowingStatus() {
		return this.showingStatus;
	}
	
	/**
	 * This method will prompt the user to select new showing status and sets it
	 * coming soon = 1 , preview = 2, now showing = 3, end of showing = 4;
	 */
	public void setShowingStatus()
	{
		
		Scanner input = new Scanner(System.in);
		
		int newshowingstatus=5;
		do {
		System.out.println("Enter Showing Status: ");
	    System.out.println("1. coming soon");
		System.out.println("2. preview");
		System.out.println("3. now showing");
		System.out.println("4. end of showing");
		System.out.println("---------------------");
		newshowingstatus=input.nextInt();
		if (newshowingstatus <5 && newshowingstatus >0)
		{
			this.showingStatus = newshowingstatus;
		}else {
			System.out.print("Please enter number 1 , 2 , 3 or 4 ");
			newshowingstatus = input.nextInt();	
		}		
		}while(newshowingstatus >4);

	}
	/**
	 * This method convert int representation of the showing status of movie and returns it as a String
	 * coming soon = 1 , preview = 2, now showing = 3, end of showing = 4;
	 * @return String of the showing status
	 */
	public String printshowingStatus() {
		switch (getShowingStatus()) {
		case 1:
			return "Coming Soon";
		case 2:
			return "Preview";
		case 3:
			return "Now Showing";
		case 4:
			return "End Of Showing";
		default:
			return "N.A.";
		} 
	}	
	/**
	 * This method gets the String of the movie type of the movie
	 * example "Blockbuster","3D","Normal"
	 * @return String movie type
	 */
	public String getType() {
		return this.movieType; 
	}
	/**
	 * This method will prompt the user to select new movie type and sets it
	 * example "Blockbuster","3D","Normal"
	 */
	public void setMovieType()
	{
		Scanner input = new Scanner(System.in);
		System.out.println("Enter Movie Type: ");
	    System.out.println("1. Blockbuster");
		System.out.println("2. 3D");
		System.out.println("3. Normal");
		this.movieType = allMovieTypes[Integer.parseInt(input.nextLine())-1];
	}
	/**
	 * This method will return the Synopsis of the movie
	 * @return String of the Synopsis of the movie
	 */
	public String getSynopsis() {
		return this.synopsis;
	}
	/**
	 * This method will prompt the user to enter new Synopsis and sets it
	 */
	public void setSynopsis()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Synopsis: ");
	    String synopsis = input.nextLine();
		this.synopsis=synopsis;
	}
	/**
	 * This method will return the Director of the movie
	 * @return String of the Director of the movie
	 */
	public String getDirector() {
		return this.director;
	}
	/**
	 * This method will prompt the user to enter new Director and sets it
	 */	
	public void setDirector()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter Director: ");
	    String Director = input.nextLine();
		this.director=Director;
	}
	/**
	 * This method will return the number of cast of the movie
	 * @return int number of cast of the movie
	 */
	public int getCastNum() {
		return this.getCastNum();
	}
	
	/**
	 * This method will prompt the user to enter new number of cast and sets them
	 */		
	public void setCast() 
	{
		Scanner input = new Scanner(System.in);
		cast.clear();
		System.out.print("Please input number of casts:");
		int castNum = input.nextInt();
		input.nextLine();
		for (int i = 0; i < castNum; i++) {
			System.out.print("Enter cast " + (i + 1) + " :");
			this.cast.add(input.nextLine());	
		}
	}
	
	/**
	 * This method will return the arraylist of all the cast of the movie
	 * @return String ArrayList all the cast of the movie
	 */
	public ArrayList<String> getCasts() {
		return cast;
	}
	/**
	 * This method will print all the relevant information for the movie
	 */
	public void printInfo() {

		System.out.println("=====INFORMATION=====");
		System.out.println("(1) Title: " + getTitle());
		System.out.println("(2) Showing status: " + printshowingStatus());
		System.out.println("(3) Movie type: " + getType());
		System.out.println("(4) Movie rating: " + getMovieRating());
		System.out.println("(5) Synopsis: ");
		StringTokenizer st = new StringTokenizer(synopsis,".");
		while (st.hasMoreTokens()) 
		{
			System.out.println("-- "+st.nextToken());
		}	
		System.out.println("(6) Director: " + director);
		System.out.println("(7) Cast information:");
		for (int i = 0; i < cast.size(); i++) {
			System.out.println("-- " + cast.get(i));
		}
		if(reviews.size() > 1) {
			System.out.println("(8) Overall rating: " + df.format(rating));
		}
		else {
			System.out.println("(8) Overall rating: NA");
		}
		
		System.out.println("(9) Past reviews:");
		if(reviews.size() > 1) {
			for(Review review : reviews)
			{
				System.out.print("-- " +review.getRating() + "/5: ");
				System.out.println(review.getReview());
			}
		}
		else
		{
			System.out.println("-- NA");
		}
		
		System.out.println("=====================");
	}
}