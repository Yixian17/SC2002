package movieassignment;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

/**
*This class creates CineplexList which is an arraylist of cineplexes and contains the methods to process and use it.
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
*/
public class CineplexList {
	private static ArrayList<Cineplex> cineplexes = new ArrayList<Cineplex>();
	//private static ArrayList<Cinema> cinema = new ArrayList<Cinema>();
	//print cineplex details
	
	/**
	*This method list out all the cineplex and the cinemas it contains based on the binary file
	* @throws EOFException if binary file is empty
	*/
	public static void listCineplexWithCinema() throws Exception{
		FileInputStream fis = new FileInputStream("Cineplex6");
		ObjectInputStream ois= new ObjectInputStream(fis);
		int num=ois.readInt();
		cineplexes.clear();
		if ( num == 0) {
			System.out.println("---------------------");
			System.out.println("No cineplex to show");
		}
		else {
			for (int i = 0; i < num; i++) 
			{
				System.out.println("Cineplex " + (i + 1) + ":");
				cineplexes.add((Cineplex) ois.readObject());
				cineplexes.get(i).printCineplexInfo();
				for (int j=0;j<cineplexes.get(i).getCinemas().size();j++) {
					System.out.println("-- Cinema("+ (j+1) +") "+cineplexes.get(i).getCinemas().get(j).getCinemaDetails());
				}
				System.out.println("=====================");
				System.out.println();
			}
		}
		ois.close();
	}
	
	
	/**
	*This method list out all the cineplex based on the binary file
	* @throws EOFException if binary file is empty
	*/
	public static void listCineplexLocation() throws Exception{
		FileInputStream fis = new FileInputStream("Cineplex6");
		ObjectInputStream ois= new ObjectInputStream(fis);
		int num=ois.readInt();
		cineplexes.clear();
		if ( num == 0) {
			System.out.println("---------------------");
			System.out.println("No cineplex to show");
		}
		else {
			for (int i = 0; i < num; i++) 
			{
				cineplexes.add((Cineplex) ois.readObject());
				System.out.println("Cineplex (" + (i + 1) + "): " + cineplexes.get(i).getLocation());
			}
		}
		ois.close();
	}
	/**
	*This method returns the chosen Cineplex object
	*@param cineNum index of cineplex object
	*@return Cineplex object selected
	* @throws EOFException if binary file is empty
	*/
	 public static Cineplex chooseCineplexOnly(int cineNum) throws Exception{
		 	
			Scanner input = new Scanner(System.in);
			FileInputStream fis2 = new FileInputStream("Cineplex6");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			int num = ois2.readInt();
			Cineplex[] cList = new Cineplex[num];
			for (int i = 0; i < num; i++) {
				cList[i] = (Cineplex) ois2.readObject();
			}
			ois2.close();	
			return cList[cineNum-1];
	 }
	 
	 /**
		*This method returns the chosen Cinema object
		*@param cineNum index of cinema object
		*@return cinema object selected
		* @throws EOFException if binary file is empty
		*/
	 public static Cinema chooseCinemaOnly(int cineNum) throws Exception{
		 	Scanner input = new Scanner(System.in);
			FileInputStream fis3 = new FileInputStream("Cineplex6");
			ObjectInputStream ois3 = new ObjectInputStream(fis3);
			int num2 = ois3.readInt();
			Cineplex[] cList2 = new Cineplex [num2];
			for (int i = 0; i < num2; i++) {
				cList2[i] = (Cineplex) ois3.readObject();
			}
			System.out.println("---------------------");
			System.out.println("Select cinema:");
		
			for (int j=0;j<cList2[cineNum-1].getCinemaNum();j++) {
				System.out.println("Cinema("+ (j+1) +") ");
				System.out.println("-- Hall: "+cList2[cineNum-1].getCinemas().get(j).getHallNumber());
				System.out.println("-- Code: "+cList2[cineNum-1].getCinemas().get(j).getCinemaCode());
			}
			System.out.println("---------------------");
			ois3.close();
				
			int selCinema = input.nextInt();
			
			return cList2[cineNum-1].getCinemas().get(selCinema-1);		
			}		
	 /**
		*This method is used to create a new cineplex to empty binary file
		*Use this if the binary file is empty
		* @throws EOFException if binary file is empty
		* @throws FileNotFoundException if binary file cannot be created or edited
		*/
	public static void createFirstCineplex() throws Exception {
		FileOutputStream fos2 = new FileOutputStream("Cineplex6");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		int num = 1; //Indicating Number of Cineplex
		oos2.writeInt(num); //Writing it at the top of the file
		Scanner input = new Scanner(System.in);
		System.out.println("---------------------");
		System.out.print("Enter cineplex location: ");
		String newCineplexName = input.nextLine();
		System.out.print("Enter location Abbreviation (XXX): ");
		String locationAbb = input.nextLine();
		Cineplex firstCineplex = new Cineplex(newCineplexName,locationAbb); //Creating first cineplex
		oos2.writeObject(firstCineplex);
		oos2.close();
	}
	/**
	*This method is used to create a new cineplex and add it to the binary file
	*Use this if the binary file is not empty
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void createCineplex() throws Exception {		
		FileInputStream fis = new FileInputStream("Cineplex6");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		int num = ois.readInt();
		num++;
		ArrayList<Cineplex> cList = new ArrayList<Cineplex>();
		for (int i = 0; i < num-1; i++) {
			cList.add((Cineplex) ois.readObject());
		}
		ois.close();
		
		Scanner input = new Scanner(System.in);
		System.out.println("---------------------");
		System.out.print("Enter cineplex location: ");
		String newCineplexName = input.nextLine();
		System.out.print("Enter location abbreviation (XXX): ");
		String locationAbb = input.nextLine();
		Cineplex newCineplex = new Cineplex(newCineplexName, locationAbb);

		cList.add(newCineplex);
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Cineplex6"));
		oos.writeInt(num);
		for (Cineplex i : cList) {
			oos.writeObject(i);
		}
		oos.close();
	}
	/**
	*This method is used to remove a cineplex object from the binary file
	*Use this if the binary file is not empty
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void removeCineplex() throws Exception {
		Scanner input = new Scanner(System.in);
		int sel, i;
		FileInputStream fis2 = new FileInputStream("Cineplex6");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);

		int num = ois2.readInt();
		Cineplex[] cList = new Cineplex[num];
		System.out.println("---------------------");
		System.out.println("Select cineplex to remove:");
		for (i = 0; i < num; i++) {
			cList[i] = (Cineplex) ois2.readObject();
			System.out.println("Cineplex (" + (i + 1) + "): "+cList[i].getLocation());
		}
		System.out.println("---------------------");
		ois2.close();
		sel = input.nextInt();
		if (sel > num) {	
			do {
				System.out.println("Try again, choose a cineplex from above");
				System.out.println("---------------------");
				sel = input.nextInt();
			} while (sel>num);
		}
		input.nextLine();

		// update number of movie
		num--;

		FileOutputStream fos2 = new FileOutputStream("Cineplex6");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		oos2.writeInt(num);
		for (i = 0; i < num + 1; i++) {
			if (!(sel == i + 1)) {
				oos2.writeObject(cList[i]);
			}
		}
		

		oos2.close();
	}

	/**
	*This method is used to add a cinema object to a cinplex object and update the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	 public static void addCinema() throws Exception{
		Scanner input = new Scanner(System.in);
		FileInputStream fis2 = new FileInputStream("Cineplex6");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		int num = ois2.readInt();
		Cineplex[] cList = new Cineplex[num];
		System.out.println("---------------------");
		System.out.println("Select cineplex to add cinema:");
		for (int i = 0; i < num; i++) {
			cList[i] = (Cineplex) ois2.readObject();
			System.out.println("Cineplex (" + (i + 1) + "): "+cList[i].getLocation());
		}
		System.out.println("---------------------");
		ois2.close();
		int sel = input.nextInt();
		input.nextLine();
		
		cList[sel-1].addCinema();
		
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("Cineplex6"));
		oos.writeInt(num);
		for (Cineplex i : cList) {
			oos.writeObject(i);
		}
		oos.close();
		
		
		}
		
	 /**
		*This method is used to select a cineplex and the cinema based on the binary file
		*@return Cinema object that is selected
		* @throws EOFException if binary file is empty
		* @throws FileNotFoundException if binary file cannot be created or edited
		*/
	 public static Cinema chooseCineplexCinema() throws Exception{
		Scanner input = new Scanner(System.in);
		FileInputStream fis2 = new FileInputStream("Cineplex6");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		int num = ois2.readInt();
		Cineplex[] cList = new Cineplex[num];
		System.out.println("---------------------");
		System.out.println("Select cineplex:");
		for (int i = 0; i < num; i++) {
			cList[i] = (Cineplex) ois2.readObject();
			System.out.println("Cineplex " + (i + 1) + ": "+cList[i].getLocation());
		}
		System.out.println("---------------------");
		ois2.close();
		int sel = input.nextInt();
		input.nextLine();

		
		FileInputStream fis3 = new FileInputStream("Cineplex6");
		ObjectInputStream ois3 = new ObjectInputStream(fis3);
		int num2 = ois3.readInt();
		Cineplex[] cList2 = new Cineplex [num];
		for (int i = 0; i < num2; i++) {
			cList2[i] = (Cineplex) ois3.readObject();
		}
		System.out.println("---------------------");
		System.out.println("Select cinema:");
	
		for (int j=0;j<cList2[sel-1].getCinemaNum();j++) {
			System.out.println("-- Cinema("+ (j+1) +") "+cList2[sel-1].getCinemas().get(j).getCinemaCode());
		}
		System.out.println("---------------------");
		System.out.println();
		ois3.close();
			
		int selCinema = input.nextInt();
		
		return cList2[sel-1].getCinemas().get(selCinema-1);		
		}
	 /**
		*This method is used to select a cineplex to change the Top 5 to display for the moviegoer
		* @throws EOFException if binary file is empty
		* @throws FileNotFoundException if binary file cannot be created or edited
		*/
	 public static void chooseCineplexToChangeTop5() throws Exception{
		 
		 	Scanner input = new Scanner(System.in);
			System.out.println("---------------------"); 
			System.out.println("Choose Cineplex to change Top 5 Showing");
			listCineplexLocation();
			System.out.println("---------------------"); 
			int cineplexChoice = input.nextInt();
			
			//Scanner input = new Scanner(System.in);
			FileInputStream fis2 = new FileInputStream("Cineplex6");
			ObjectInputStream ois2 = new ObjectInputStream(fis2);
			int num = ois2.readInt();
			Cineplex[] cList = new Cineplex[num];
			for (int i = 0; i < num; i++) {
				cList[i] = (Cineplex) ois2.readObject();
			}
			ois2.close();	
			//return cList[cineNum-1];
			
			//Cineplex cineplex = CineplexList.chooseCineplexOnly(cineplexChoice);
		 	
			if(cList[cineplexChoice-1].getTop5showing()==0)
			{
				cList[cineplexChoice-1].setTop5showing(1);
				System.out.println(cList[cineplexChoice-1].getLocation()+" Will Now Show Top Review Movies");
			}
			else
			{
				cList[cineplexChoice-1].setTop5showing(0);
				System.out.println(cList[cineplexChoice-1].getLocation()+" Will Now Show Top Sales Movies");
			}
			
			FileOutputStream fos2 = new FileOutputStream("Cineplex6");
			ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
			oos2.writeInt(num);
			for (int i = 0; i < num ; i++) {			
					oos2.writeObject(cList[i]);
					//System.out.println(cList[i].getTop5showing()+" cList[i].getTop5showing()");
					
			}
			oos2.close();
			
			
	
			//return cList[cineNum-1];
	 }
	 
	 
	
	 	
//	 public static void listBookingHistory() throws Exception {
//		 BookingHistory bookingHistory = new BookingHistory();
//		 bookingHistory.listBookingHistory();
//	 }
//	 	
		
}
	

//	public static void editCinema() throws Exception {
//		
//		Scanner input = new Scanner(System.in);
//		int cineplexChoice, i, informationChoice;
//		FileInputStream fis3 = new FileInputStream("Cineplex5.txt");
//		ObjectInputStream ois3 = new ObjectInputStream(fis3);
//		
//		int num = ois3.readInt();
//		Cineplex[] c2List = new Cineplex[num];
//		
//		System.out.println("Choose which cineplex you would like to add cinema:");
//		cineplexes.clear();
//		for (i = 0; i < num; i++) {
//			cineplexes.add((Cineplex) ois3.readObject());
//			System.out.println("Movie " + (i + 1) + " : " + cineplexes.get(i).getLocation());
//		}
//		
//		FileInputStream fis4 = new FileInputStream("Cineplex5.txt");
//		ObjectInputStream ois4 = new ObjectInputStream(fis4);
//		ois4.readInt();
//		for (int j = 0; j < num; j++) {
//			c2List[j] = (Cineplex) ois4.readObject();
//		}
//		
//		ois3.close();
//		ois4.close();
//		
//		cineplexChoice = input.nextInt();
//		cineplexes.get(cineplexChoice-1).printCineplexInfo();
//		
//		System.out.println("Which information would you like to edit:");
//		informationChoice = input.nextInt();
//		input.nextLine();
//		switch (informationChoice)
//		{
//		    case 1 :cineplexes.get(cineplexChoice-1).setCineplexLocation();
//		    		break;
//		    case 2 :movies.get(movieChoice-1).setShowingStatus();
//					break;
//		    case 3 :movies.get(movieChoice-1).setMovieType();
//		    		break;
//		    case 4 :movies.get(movieChoice-1).setSynopsis();
//		    		break;
//		    case 5 :movies.get(movieChoice-1).setDirector();
//		    		break;
//		    case 6 :movies.get(movieChoice-1).setCast();
//    				break;
//		}
//
//				
//		FileOutputStream fos3 = new FileOutputStream("MovieDetails5.txt");
//		ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
//		oos3.writeInt(num);
//		for (int j = 0; j < num; j++) {
//			if (!(movieChoice == j + 1)) {
//				oos3.writeObject(m2List[j]);
//			}else
//				oos3.writeObject(movies.get(movieChoice-1));
//		}
//
//		oos3.close();
		
		
	


    
//
//    /**
//     * Displays the showtimes of the cinemas in all the cineplexes.
//     * @param i Index
//     */
//    public static void showShowtimes(int i) {
//        if (cineplexes != null) {
//            cineplexes.get(i).showShowtimes();
//        }
//    }
//
//


//

