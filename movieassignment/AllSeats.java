package movieassignment;

import java.util.*;
import java.io.*;  // Import the File class
/**
 * This class creates AllSeats and contains the function to display them
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class AllSeats implements Serializable {
	  /**
	 * 
	 */
	private static final long serialVersionUID = 5556778042068794028L;
	private Seats [][] Seats;
	/**
	 * This constructor creates a new Allseat object and initialises it with new seat objects
	 */	  
	  public AllSeats() {
		  Seats = new Seats [6][12];
		  
		  for (int i = 0; i<6; i++) {
		      for (int j = 0; j<12; j++) {
		        Seats seat = new Seats(i, j);
		        Seats[i][j] = seat;
		      }
		   }
	  }
	  
	  /**
		 * This method will get specific seat object
		 * @param row row number of seat
		 * @param col column number of seat
		 * @return Seats seat object 
		 */
	  public Seats getSeats(int row, int col) {return this.Seats[row][col];}
	  
	  
	  /**
			 * This method will display seats layout for user to select seat 
			 */
	  public void showSeats () {
		    char[] numToABC = new char[6];
		    numToABC[0] = 'A';
		    numToABC[1] = 'B';
		    numToABC[2] = 'C';
		    numToABC[3] = 'D';
		    numToABC[4] = 'E';
		    numToABC[5] = 'F';
		    
		    char[] seatChar = new char[3];
		    seatChar[0] = '`';
		    seatChar[1] = '~';
		    seatChar[2] = '*';

		    System.out.println("---------------------");
		    System.out.println("Type of Seats:");
		    System.out.println("Normal Seats(Single): [  ]");
		    System.out.println("Couple Seats(Double): [``````]");
		    System.out.println("Elite Seats(Double): [~~~~~~]");
		    System.out.println("Ultima Seats(Double): [******]");
		    System.out.println("");
		    
		    System.out.println("------------------------------------------------------");
		    System.out.println("                       --------                       ");
		    System.out.println("                       |SCREEN|                       ");
		    System.out.println("                       --------                       ");
//		    System.out.println("                       --------                       ");
		    System.out.println("    1   2   3   4   5   6      7   8   9   10  11  12 ");
//		    System.out.println("[A1][A2][A3][A4][A5][A6]   [A7][A8][A9][A10][A11][A12]");



		      for (int i = 0; i<6; i++) {
		        if(i<3) {
		          System.out.print(" "+(i+1)+" ");
		          for (int j = 0; j<13; j++) {
		              if(j<6) {
		                if (this.Seats[i][j].isAssigned()) {
		                      System.out.print("[XX]");
		                    } else {System.out.print("[  ]");}
		              } else if(j==6) {
		                System.out.print("   ");
		              } else {
		                if (this.Seats[i][j-1].isAssigned()) {
		                      System.out.print("[XX]");
		                    } else {System.out.print("[  ]");}
		              }

		            } System.out.println("");
		        }
		        else {
		          System.out.print(" "+(i+1)+" ");
		          for (int j = 0; j<13; j++) {
		                if(j<6 && j%2==0) {
		                  if (this.Seats[i][j].isAssigned()) {
		                        System.out.print("[XXXXXX]");
		                      } else {System.out.print("["+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+"]");}
		                } else if(j==6) {
		                  System.out.print("   ");
		                } else if(j>6 && j%2==1){
		                  if (this.Seats[i][j-1].isAssigned()) {
		                        System.out.print("[XXXXXX]");
		                      } else {System.out.print("["+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+seatChar[i-3]+"]");}
		                }
		              } System.out.println("");
		        }
		        
		      }System.out.println("------------------------------------------------------");
		      System.out.println("");
		    }
	 
	  /**
		 * This method will prompt the user to select a seat
		 * @param cinema where the chosen seat is located in
		 * @param timing movie timing of the movie slot
		 * @param cineplex where the chosen seat is located in
		 * @throws EOFException if binary file is empty
		 * @throws FileNotFoundException if binary file cannot be created or edited
		 */
	  public void choseSeat(Cinema cinema, MovieTiming timing, Cineplex cineplex) throws Exception { 
		  Scanner sc = new Scanner(System.in); 
		  timing.getAllSeats().showSeats();
		  System.out.print("Enter row number to book: ");
		  int row = sc.nextInt(); 
		  System.out.print("Enter column number to book: ");
		  int col = sc.nextInt();
		  
		  while((row>3 && col%2==0) || row<0 || row>6 || col<0 || col>12 || this.Seats[row-1][col-1].isAssigned()) {
			  timing.getAllSeats().showSeats();
			  System.out.println("Please Enter the Valid Seat");
			  System.out.print("Enter row number to book: ");
			  row = sc.nextInt(); 
			  System.out.print("Enter column number to book: ");
			  col = sc.nextInt();
			  
		  }
		  
		  this.Seats[row-1][col-1].assignSeat(row-1, col-1,cinema,timing,cineplex);
	  }
}