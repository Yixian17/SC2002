package movieassignment;
import java.util.*;
import java.io.*;  // Import the File class

/**
*This class creates the StaffUI for the Staff to interface with
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
*/


public class StaffUI implements MainUIInterface {
	  /**
	   * This method will implement displayInterface() to display UI for staff
	   * @throws FileNotFoundException if binary file cannot be created or edited
	   * @throws EOFException if binary file is empty
	   */
	public void displayInterface() throws Exception
	{
		do {
		Scanner sc = new Scanner (System.in); 
		System.out.println("---------------------"); 
		System.out.println("Select the following action: ");
		System.out.println("(1) Create/Update/Remove Movie Listing");
		System.out.println("(2) Create/Update/Remove Cinema Showtimes and Movies ");
		System.out.println("(3) Configure System Setting");
		
//		System.out.println("(1) Update Movie Listing");
//		System.out.println("(2) Update Movie Timing"); 
//		System.out.println("(3) Create New Cineplex ");
//		System.out.println("(4) Show Cineplex ");
//		System.out.println("(5) Remove Cineplex ");
//		System.out.println("(6) Add Cinema ");
		System.out.println("---------------------"); 
		int staffActionOption = sc.nextInt();
		switch(staffActionOption) 
		{
		case 1: // Update Movie Listing
				int staffUpdateMovieOption=0;
				do {
					System.out.println("---------------------"); 
			        System.out.println("Select the following action: "); 
			        System.out.println("(1) Display Movie Listing");
			        System.out.println("(2) Display Movie Information");
			        System.out.println("(3) Create New Movie");
			        System.out.println("(4) Update Movie");
			        System.out.println("(5) Remove Movie");
			        System.out.println("(6) Back");
			        System.out.println("---------------------");
			        //sc.nextLine();
			        staffUpdateMovieOption = sc.nextInt();
			        sc.nextLine();
			        switch (staffUpdateMovieOption)
			        {
			        	case 1:	try {MovieListing2.listMovie();}
			        			catch (Exception EOFException) {System.out.println("No movie found");}
			        			break;
			        	case 2:	try {MovieListing2.displayMovieDetails();}
		        				catch (Exception EOFException) {System.out.println("No movie found");}
			        			break;
			        	case 3: try {MovieListing2.createMovie();}
		        				catch (Exception EOFException) {MovieListing2.createFirstMovie();}
			        			break;
			        	case 4: try {MovieListing2.editMovie();}
			        			catch (Exception EOFException) {System.out.println("No movie found.");}
			        			break;
			        	case 5: try {MovieListing2.removeMovie();}
			        			catch (Exception EOFException) {System.out.println("No movie found.");}
			        			break;
			        } 
			        }while (staffUpdateMovieOption <6);   
				break;
				
		case 2: // Update Movie Timing
				int staffUpdateMovieTimingOption = 0;
				do {
					System.out.println("---------------------"); 
			        System.out.println("Select the following action: "); 
			        System.out.println("(1) Display List of Movie Timing");
			        System.out.println("(2) Create Cinema Showtime");
			        System.out.println("(3) Update Movie Timing");
			        System.out.println("(4) Remove Movie Timing"); 
			        System.out.println("(5) Back");
			        System.out.println("---------------------");
			        staffUpdateMovieTimingOption = sc.nextInt();
			        sc.nextLine();
			        switch (staffUpdateMovieTimingOption)
			        {
			        	case 1: try {movieTimingList.listMovieTiming();}
		        				catch (Exception EOFException) {System.out.println("No Movie Timing.");}
			        			break;
			        	case 2: try {movieTimingList.createMovieTiming();}
			        			catch (Exception EOFException) {movieTimingList.createFirstMovieTiming();}
			        			break;
			        	case 3:	try {movieTimingList.editMovieTiming();}       			
			        			catch (Exception EOFException) {System.out.println("No Movie Timing.");}
	        					break;
			        	case 4: try {movieTimingList.removeMovieTiming();}       			
	        					catch (Exception EOFException) {System.out.println("No Movie Timing.");}
    							break;
			        } 
				}while (staffUpdateMovieTimingOption <5);
				break;
				
		case 3: //Configure system setting
			int staffConfigureSystemSettingOption = 0;
			do {
				System.out.println("---------------------"); 
		        System.out.println("Select the following action: "); 
		        System.out.println("(1) Display List of Cineplex");
		        System.out.println("(2) Create New Cineplex");
		        System.out.println("(3) Remove Cineplex");
		        System.out.println("(4) Add New Cinema"); 
		        System.out.println("(5) Create Holiday");
		        System.out.println("(6) Update Pricing");
		        System.out.println("(7) Toggle Top 5 for user");
		        System.out.println("(8) Back");
		        System.out.println("---------------------");
		        staffConfigureSystemSettingOption = sc.nextInt();
		        sc.nextLine();
		        switch (staffConfigureSystemSettingOption)
		        {
		        	case 1: System.out.println("---------------------");
							System.out.println("List of Cineplex:");
							CineplexList.listCineplexWithCinema();
							break;
		        	case 2: try {CineplexList.createCineplex();} 
		        			catch(IOException e){CineplexList.createFirstCineplex();}
							break;
		        	case 3: try {CineplexList.removeCineplex();}
		        			catch (Exception EOFException) {System.out.println("No Cineplex.");}
		        			break;
		        	case 4:	CineplexList.addCinema();
							break;
		        	case 5: movieTimingList.createHoliday();
		        			break;
		        	case 6: PriceConfigureController.editPriceConfigureObject();
		        			break;
		        	case 7: CineplexList.chooseCineplexToChangeTop5();
        			break;
		        } 
			}while (staffConfigureSystemSettingOption <8);
		}
	}while (true);
}
}
