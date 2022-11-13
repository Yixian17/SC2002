package movieassignment;
import java.util.Scanner;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * This class creates BookingHistory objects
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class BookingHistory implements Serializable{

	private static final long serialVersionUID = 1L;
	//	public static final String SEPARATOR = "|";
	private ArrayList<Booking> bookingHistory = new ArrayList<Booking>();
	private int num;
	


	/**
	 * This constructor reads from the binary file and populates the bookingHistory arraylist
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */
	public BookingHistory() throws Exception {
		try {
			FileInputStream fis = new FileInputStream("BookingHistory.txt");
		ObjectInputStream ois= new ObjectInputStream(fis);
		num = ois.readInt();
		bookingHistory.clear();
		for (int i = 0; i < num; i++) {
			bookingHistory.add((Booking) ois.readObject());
		} 
		ois.close();}
		catch (Exception e)
		{
			System.out.println("error reading booking history");
			num=0;
		}
		
	}

	
	/**
	 * This method reads from the binary file and populates the bookingHistory arraylist and prints it
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */
	public void listBookingHistory() throws Exception {
		int j =0;
		Scanner sc = new Scanner (System.in);
		System.out.println("---------------------");
		System.out.println("Enter Username: ");
		String usernameinput=sc.nextLine();
		try {FileInputStream fis = new FileInputStream("BookingHistory.txt");
		ObjectInputStream ois= new ObjectInputStream(fis);
		num = ois.readInt();
		//System.out.println("THis is num  "+num);

		System.out.println("---------------------");
		System.out.println("Displaying Booking History: ");
		bookingHistory.clear();
		for (int i = 0; i < num; i++) {
			bookingHistory.add((Booking) ois.readObject());
			if(bookingHistory.get(i).getBuyer().getName().equals(usernameinput))
				{bookingHistory.get(i).displayBooking();
				j++;
				}
		} 
		if(j==0)
		{
			System.out.println("Booking History is Empty: ");
		}
		ois.close();}
		
		catch(Exception e)
		{System.out.println("Booking History is Empty: ");}
		
	}

	
	/**
	 * This method gets the number of booking in the binary file
	 * @return int number of bookings
	 */
	public int getNum() {
		return num;
	}

	/**
	 * This method sets the number of booking in the binary file
	 * @param num number of bookings to set to
	 */
	public void setNum(int num) {
		this.num = num;
	}
	/**
	*This method is used to create a new booking to empty binary file
	*Use this if the binary file is empty
	* @param booking booking object to be added
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public void createFirstBookingInput(Booking booking) throws Exception {
		FileOutputStream fos2 = new FileOutputStream("BookingHistory.txt");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		num = 1; //Indicating Number of Booking 
		oos2.writeInt(num); //Writing it at the top of the file
		oos2.writeObject(booking);
		oos2.close();
	}

	/**
	*This method is used to create a new booking and add it to the binary file
	*Use this if the binary file is not empty
	* @param booking booking object to be added
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public void createBookingInput(Booking booking) throws Exception {		
		FileInputStream fis = new FileInputStream("BookingHistory.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		num = ois.readInt();
		num++;
		bookingHistory.clear();
		//ArrayList<Booking> bookingHistory = new ArrayList<Booking>();
		//read all Movie objects file
		for (int i = 0; i < num - 1; i++) {
			bookingHistory.add((Booking) ois.readObject());
		}
		ois.close();

		System.out.println("---------------------");
		//append Movie
		bookingHistory.add(booking);

		// rewrite database
		FileOutputStream fos2 = new FileOutputStream("BookingHistory.txt");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);		
		oos2.writeInt(num);
		for (Booking i : bookingHistory) {
			oos2.writeObject(i);
		}
		oos2.close();
	}
	/**
	 * This method prints the booking history of a specific movie goer
	 * @param booking booking to query
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */	
	public void showBookingHistory(Booking booking) throws Exception{
		FileInputStream fis = new FileInputStream("BookingHistory.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		num = ois.readInt();
		num++;
		bookingHistory.clear();
		//ArrayList<Booking> bookingHistory = new ArrayList<Booking>();
		//read all Movie objects file
		for (int i = 0; i < num - 1; i++) {
			bookingHistory.add((Booking) ois.readObject());
		}
		ois.close();
		for (int j=0; j<bookingHistory.size(); j++) {
//			bookingHistory.get(j);
			System.out.println("_________________________________________");
			System.out.println("_________________________________________");
			System.out.println(bookingHistory.get(j).getBuyer());
			System.out.println(bookingHistory.get(j).getCinema());
			System.out.println(bookingHistory.get(j).getMovieTiming());
			System.out.println(bookingHistory.get(j).getPrice());
			bookingHistory.get(j).displayBookingTicket();
			System.out.println("_________________________________________");
			System.out.println("_________________________________________");

		}
		
	}
	/**
	 * This method prints the booking history of all bookings
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */	
	public void listAllBookingHistory() throws Exception {
		try {FileInputStream fis = new FileInputStream("BookingHistory.txt");
		ObjectInputStream ois= new ObjectInputStream(fis);
		num = ois.readInt();

		System.out.println("---------------------");
		System.out.println("Displaying Booking History: ");
		bookingHistory.clear();
		for (int i = 0; i < num; i++) {
			bookingHistory.add((Booking) ois.readObject());
			bookingHistory.get(i).displayBooking();
		} 

		ois.close();}
		
		catch(Exception e)
		{System.out.println("Booking History is Empty: ");}
		
	}
	/**
	 * This method prints top sales movie based on revenue earn from ticket sales
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */	
	public void getTopSales() throws Exception {
		  try {FileInputStream fis = new FileInputStream("BookingHistory.txt");
		  ObjectInputStream ois= new ObjectInputStream(fis);
		  num = ois.readInt();
		  
		  //ArrayList<ArrayList<Double> > allMovieList = new ArrayList<ArrayList<Double>>();
		  
		  System.out.println("---------------------");
		  System.out.println("Top Sales is: ");
		  bookingHistory.clear();
		  for (int i = 0; i < num; i++) {
		   bookingHistory.add((Booking) ois.readObject());
		   //bookingHistory.get(i).displayBooking();
		  } 
		  double temp=0;
		  ArrayList<String> allMovies = MovieListing2.ReturnAllMovie();
		  String [] names=new String[allMovies.size()];
		  double [] allprices=new double[allMovies.size()];
		  double [] sortedallprices=new double[allMovies.size()];
		  for (int i = 0; i < allMovies.size(); i++) {
		   names[i]=allMovies.get(i);
		  }
		  for (int i = 0; i < num; i++) {
		   temp=bookingHistory.get(i).getFinalprice();
		   String tempstr=bookingHistory.get(i).getMovieTiming().getMovie().getTitle();
		   
		   for(int j=0;j<allMovies.size();j++)
		   {
		    if(tempstr.equals(names[j]))
		    {
		     allprices[j]+=temp;
		     sortedallprices[j]=allprices[j];
		    }
		   }
		   //allMovieList.add(null)
		   //bookingHistory.get(i).displayBooking();
		  }
		  Arrays.sort(sortedallprices);
		  
		  int allsize=allMovies.size();
		  int arrayvisit [] = new int[allsize];
		  
		  
		  for (int i = allsize-1; i >-1; i--) {
		   //System.out.println("Top Sales is: ");
		   for (int j = 0; j <allMovies.size(); j++) {
		    if(sortedallprices[i]==allprices[j] && arrayvisit[j]==0)
		     {System.out.println(allMovies.get(j)+" : Sold: $"+sortedallprices[i]);
		     //allMovies.remove(j);
		     arrayvisit[j]=1;
		     break;}
		   }
		   
		  }

		  
		  ois.close();}
		  
		  catch(Exception e)
		  {System.out.println("Booking History is Empty: ");}
		  
		 }

}
