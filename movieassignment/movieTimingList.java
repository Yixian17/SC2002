package movieassignment;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Scanner;
/**
 * This class creates ArrayList of movietiming and contains the methods to process and use it.
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class movieTimingList {
	private static ArrayList<MovieTiming> movieTiming = new ArrayList<MovieTiming>();
	private static ArrayList<String> HolidayList = new ArrayList<String>(); 

	private int num = 0;
	/**
	 * This default constructor reads from the binary file and add to the array list
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 * @throws EOFException if binary file is empty
	 */	
	public static void listMovieTiming() throws Exception{
		FileInputStream fis = new FileInputStream("MovieTiming");
		ObjectInputStream ois= new ObjectInputStream(fis);
		int num=ois.readInt();
		movieTiming.clear();
		System.out.println("---------------------");
		if ( num == 0) {
			System.out.println("No cineplex to show");
		}
		else {
			for (int i = 0; i < num; i++) 
			{
				movieTiming.add((MovieTiming) ois.readObject());
				System.out.println("Movie Timing (" + (i + 1) + "): \n" + movieTiming.get(i).toString());
			}
		}
		ois.close();
	}
	/**
	*This method is used to create a new movietiming and add it to the binary file
	*Use this if the binary file is not empty
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void createMovieTiming() throws Exception {		
		FileInputStream fis = new FileInputStream("MovieTiming");
		ObjectInputStream ois = new ObjectInputStream(fis);

		int num = ois.readInt();
		num++;
		ArrayList<MovieTiming> mList = new ArrayList<MovieTiming>();
		for (int i = 0; i < (num-1); i++) {
			mList.add((MovieTiming) ois.readObject());
		}
		ois.close();

		Scanner input = new Scanner(System.in);
		System.out.println("---------------------");
		System.out.println("Select Cineplex");
		CineplexList.listCineplexLocation();
		System.out.println("---------------------");
		int cineplexNum = input.nextInt();
		
		Cineplex cineplex = CineplexList.chooseCineplexOnly(cineplexNum);
		Cinema cinema = CineplexList.chooseCinemaOnly(cineplexNum);

		MovieTiming newMovieTiming = new MovieTiming(MovieListing2.chooseMovie(), cineplex, cinema);
		mList.add(newMovieTiming);

		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("MovieTiming"));
		oos.writeInt(num);
		for (MovieTiming i : mList) {
			oos.writeObject(i);
		}
		oos.close();
	}
	/**
	*This method is used to create a new movietiming to empty binary file
	*Use this if the binary file is empty
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void createFirstMovieTiming() throws Exception{
		Scanner input = new Scanner(System.in);
		FileOutputStream fos2 = new FileOutputStream("MovieTiming");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		int num = 1; //Indicating Number of MovieTiming
		oos2.writeInt(num); //Writing it at the top of the file
		System.out.println("---------------------");
		System.out.println("Select Cineplex");
		CineplexList.listCineplexLocation();
		System.out.println("---------------------");
		int cineplexNum = input.nextInt();
		Cineplex cineplex = CineplexList.chooseCineplexOnly(cineplexNum);
		Cinema cinema = CineplexList.chooseCinemaOnly(cineplexNum);

		MovieTiming newMovieTiming = new MovieTiming(MovieListing2.chooseMovie(), cineplex, cinema); //Creating first cineplex
		oos2.writeObject(newMovieTiming);
		oos2.close();
	}
	/**
	*This method is used to edit a movietiming and update changes to the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void editMovieTiming() throws Exception{
		try {
			Scanner input = new Scanner(System.in);
			FileInputStream fis = new FileInputStream("MovieTiming");
			ObjectInputStream ois= new ObjectInputStream(fis);
			
			int num=ois.readInt();
			MovieTiming[] mTList = new MovieTiming[num];
			
			System.out.println("---------------------");
			System.out.println("Select movie timing to edit:");
			movieTiming.clear();
			
			if (num == 0) {
				System.out.println("No Movie Timing to show");
			}
			else {
				for (int i = 0; i < num; i++) 
				{
					movieTiming.add((MovieTiming) ois.readObject());
					System.out.println("Movie Timing (" +(i+1)+"): \n" + movieTiming.get(i).toString());
		
				}
			}
			System.out.println("---------------------");
			
			FileInputStream fis4 = new FileInputStream("MovieTiming");
			ObjectInputStream ois4 = new ObjectInputStream(fis4);
			ois4.readInt();
			for (int j = 0; j < num; j++) {
				mTList[j] = (MovieTiming) ois4.readObject();
			}
			
			ois.close();
			ois4.close();
			
			int movieTimingChoice = input.nextInt();
			System.out.println("---------------------");
			if (movieTimingChoice>num) 
			{
				System.out.println("Try again, Please select one movie timing from above.");
				return;
			}
			System.out.println("Select information would you like to edit:");
			movieTiming.get(movieTimingChoice-1).printMovieTimingInfo();
			System.out.println("---------------------");
			int informationChoice = input.nextInt();
			System.out.println("---------------------");
			input.nextLine();
			switch (informationChoice)
			{
			 case 1 :movieTiming.get(movieTimingChoice-1).setDate();
	    		break;
			 case 2 :movieTiming.get(movieTimingChoice-1).setTime();
				break;
			 case 3 :movieTiming.get(movieTimingChoice-1).setMovieShowingStatus();
	    		break;
			}

			FileOutputStream fos3 = new FileOutputStream("MovieTiming");
			ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
			oos3.writeInt(num);
			for (int j = 0; j < num; j++) {
				if (!(movieTimingChoice == j + 1)) {
					oos3.writeObject(mTList[j]);
				}else
					oos3.writeObject(movieTiming.get(movieTimingChoice-1));
			}

			oos3.close();
		
		}
		catch(Exception e) {
			System.out.println("---------------------");
			System.out.println("No Movie Timing to show");
		}
	}
	/**
	*This method is used to remove a movietiming from the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void removeMovieTiming() throws Exception {
		Scanner input = new Scanner(System.in);
		int sel, i;
		FileInputStream fis2 = new FileInputStream("MovieTiming");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);

		int num = ois2.readInt();
		MovieTiming[] mTList = new MovieTiming[num];
		System.out.println("---------------------");
		System.out.println("Select movie timing to remove:");
		for (i = 0; i < num; i++) {
			mTList[i] = (MovieTiming) ois2.readObject();
			System.out.println("Movie Timing (" + (i + 1) + "): " + mTList[i].toString());

		}
		System.out.println("---------------------");
		ois2.close();
		sel = input.nextInt();
		if (sel > num)
		{
			System.out.println("---------------------");
			System.out.println("Try again, Please select one movie timing from above.");
			return;
		}
		input.nextLine();

		// update number of movie
		num--;

		FileOutputStream fos2 = new FileOutputStream("MovieTiming");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		oos2.writeInt(num);
		for (i = 0; i < num + 1; i++) {
			if (!(sel == i + 1)) {
				oos2.writeObject(mTList[i]);
			}
		}

		oos2.close();
	}
	/**
	*This method prompt the user to key in holiday dates and update to the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void createHoliday() throws Exception{
		Scanner input = new Scanner(System.in);
		FileOutputStream fos1 = new FileOutputStream("Holiday");
		ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
		System.out.println("---------------------");
		System.out.print("Enter the number of holidays: ");
		int num = input.nextInt();
		input.nextLine();
		oos1.writeInt(num);
		int i =0;
		while (i<num)
		{
			System.out.print("Enter holiday date (YYYYMMDD) "+(i+1)+": ");
			String holiday = input.nextLine();
			oos1.writeUTF(holiday);
			i++;
		}
		oos1.close();
	}
	/**
	*This method reads the binary file returns wether date can be found
	* @param Date String in YYYYMMDD format to match binary file
	* @return boolean if date matches data in binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static boolean readHoliday(String Date) throws Exception{
		FileInputStream fis = new FileInputStream("Holiday");
		ObjectInputStream ois= new ObjectInputStream(fis);
		int num=ois.readInt();
		HolidayList.clear();
		if (num == 0) {
			System.out.println("---------------------");
			System.out.println("No holiday to show");
		}
		else {
			for (int i = 0; i < num; i++) 
			{
				HolidayList.add(ois.readUTF());
			}
		}
		ois.close();
		for (int j=0; j<num;j++)
		{
			if(HolidayList.get(j).equals(Date)) return true;
		}
		return false;
	}
	
	/**
	*This method returns arraylist of movie timing
	* @return movieTiming arraylist of movie timing
	*/
	public static ArrayList<MovieTiming> getMovieTiming() {
		return movieTiming;
	}

	/**
	*This method return the first movie timing
	* @return movieTiming first movie timing
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static MovieTiming chooseMovieTiming() throws Exception{
		try {FileInputStream fis = new FileInputStream("MovieTiming");
		ObjectInputStream ois= new ObjectInputStream(fis);
		int num=ois.readInt();
		movieTiming.clear();
		if ( num == 0) {
			System.out.println("---------------------");
			System.out.println("No Movietiming to show");
		}
		else {
			for (int i = 0; i < num; i++) 
			{
				movieTiming.add((MovieTiming) ois.readObject());
			}
		}
		ois.close();
		return movieTiming.get(0);}
		catch(Exception e) {
			System.out.println("---------------------");
			System.out.println("No Movietiming to show");
			return null;
		}
		
	}
	/**
	*This method prompts the user to choose movie timing to select and returns it
	* @param movie to select the movie timing from
	* @return movieTiming selected by user
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static MovieTiming chooseMovieTiming(Movie2 movie) throws Exception{
		Scanner sc = new Scanner(System.in);
		int j = 0;
		ArrayList<MovieTiming> movieTimingSelected = new ArrayList<MovieTiming>();
		try {FileInputStream fis = new FileInputStream("MovieTiming");
		ObjectInputStream ois= new ObjectInputStream(fis);
		int num=ois.readInt();
		movieTiming.clear();

			for (int i = 0; i < num; i++) 
			{
				movieTiming.add((MovieTiming) ois.readObject());
			}
		
		System.out.print("Select Movie Timing: \n");
		for (int i=0; i<movieTiming.size();i++) {
			if((movieTiming.get(i).getMovie().getTitle()).equals(movie.getTitle()) && movieTiming.get(i).getMovie().getShowingStatus()!=4) {
				movieTimingSelected.add(movieTiming.get(i));
				System.out.println("Movie Timing (" + (j + 1) + "):\n" + movieTimingSelected.get(j).toString());
				j++;
			}
		}
		if(j==0) 
		{
			
			System.out.println("No Movietiming to show");
			movieTimingSelected=null;
			ois.close();
			return null;
		}
		System.out.println("---------------------");
		
		int userChoice = sc.nextInt();
		if(userChoice<0 || userChoice>j)
			{ois.close();
			System.out.println("Wrong Input Please Try Again.");
			System.out.println("---------------------");
			return null;}
		ois.close();
		return movieTimingSelected.get(userChoice -1);}
		
		catch(Exception e)
		{
			System.out.println("---------------------");
			System.out.println("No Movietiming to show");
			movieTimingSelected=null;
			return null;
		}
		
	}
	/**
	*This method saves all movietiming of the movietiming arraylist to the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/ 
	public static void saveMovieTiming() throws Exception{
		FileOutputStream fos3 = new FileOutputStream("MovieTiming");
		ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
		oos3.writeInt(movieTiming.size());
		for (int j = 0; j < movieTiming.size(); j++) {
			oos3.writeObject(movieTiming.get(j));
		}
		oos3.close();
	}
	
	
}

