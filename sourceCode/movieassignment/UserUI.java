package movieassignment;

import java.util.Scanner;
import java.text.*;
import java.time.*;
import java.util.*;
import java.io.*;

/**
*This class creates the UserUI for the Moviegoer to interface with
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
*/

public class UserUI implements MainUIInterface {

  /**
   * This method will implement displayInterface() to display UI for user
   * @throws FileNotFoundException if binary file cannot be created or edited
   * @throws EOFException if binary file is empty
   */
	public void displayInterface() throws Exception
	{
	Scanner sc = new Scanner (System.in);
	Cineplex cineplex;
	System.out.println("---------------------"); 
	System.out.println("Select a cineplex to begin: ");
	CineplexList.listCineplexLocation();
	System.out.println("---------------------"); 
	int cineplexChoice = sc.nextInt();
	cineplex = CineplexList.chooseCineplexOnly(cineplexChoice);
	
	
       
      int userActionOption; 
      do { 
    	System.out.println("---------------------"); 
        System.out.println("Select the following action: "); 
        System.out.println("(1) Search / List Movies ");
        System.out.println("(2) View Movie Details ");
        System.out.println("(3) Review Movie ");
        System.out.println("(4) Check Seat Availability and Selection of Seats ");
        System.out.println("(5) Book and Purchase Ticket ");
        System.out.println("(6) View Booking History ");
        System.out.println("(7) List Top 5 ");
        System.out.println("(8) Back");
        System.out.println("---------------------");   

        userActionOption = sc.nextInt();
        
        //private createPriceConfigureObject();
        switch(userActionOption) {
        	case 1: MovieListing2.listMovie();
        			break;
        	case 2: Movie2 movie3 = MovieListing2.chooseMovie();
        			MovieListing2.displaySingleMovieDetails(movie3);
        			break;
        	case 3: Movie2 movie2 = MovieListing2.chooseMovie();
        			MovieListing2.addReview(movie2);
    		  		break;
        	case 4: Movie2 movie4 = MovieListing2.chooseMovie();
        			MovieTiming movietiming3 = movieTimingList.chooseMovieTiming(movie4);
        			if(movietiming3==null)break;
        			movietiming3.getAllSeats().showSeats();
        			break;
        	case 5: Movie2 movie = MovieListing2.chooseMovie();
            		if(movie.getShowingStatus()==1){
					    System.out.println("Movie Coming Soon. No Booking Allowed");
					    break;
					}
          		    MovieTiming movietiming = movieTimingList.chooseMovieTiming(movie);
          		    if (movietiming==null)break;  
          		    movietiming.getAllSeats().choseSeat(movietiming.getCinema(), movietiming,cineplex);
          		    movieTimingList.saveMovieTiming();
          		    movietiming.getAllSeats().showSeats();
          		    break;
          		    

        	case 6: cineplex.getBookingHistory().listBookingHistory();
        			break;
        	case 7: if(cineplex.getTop5showing()==0)
	        	{
	        		cineplex.getBookingHistory().getTopSales();//this will show total sales
        			//MovieListing2.printTop5Sale();
	        	}
	        	else
	        	{
	        		MovieListing2.printTop5Rating();// this show top 5 based on rating
	        	}
        	  		
        	  		break;
        }
     }while (userActionOption<8); 
  }

}