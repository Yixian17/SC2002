package movieassignment;

import java.io.*;
import java.text.*;
import java.time.*;
import java.util.*;
/**
 * This class creates Prices objects and is used to calculate ticket price
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class Prices implements Serializable{

	private static final long serialVersionUID = 1L;
	private Movie2 movie; 
	private Cinema cinema; 
	private TicketBuyer buyer; 
	private MovieTiming timing;  
	private Seats seat; 
	private int time;
	private PriceConfigure configure;


    /**
	 * This constructor creates a new prices object
	 * @param movie movie of the ticket that is going to be purchased
	 * @param cinema cinema where the movie is going to be shown
	 * @param buyer the buyer of the ticket
	 * @param timing movietiming of the movie
	 * @param seat seat that is to be purchased
	 * @throws EOFException if binary file is empty
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 */
	public Prices(Movie2 movie, Cinema cinema, TicketBuyer buyer, MovieTiming timing, Seats seat) throws Exception {
		this.movie = movie; 
		this.cinema = cinema; 
		this.buyer = buyer; 
		this.timing = timing; 
		this.seat = seat; 
		time = Integer.parseInt(this.timing.getTime());
		//CREATE A PRICECONFIGURE OBJECT***
		try{configure = PriceConfigureController.readPriceConfigureObject();}
		catch (Exception e)
		{
			PriceConfigureController.createPriceConfigureObject();
			configure = PriceConfigureController.readPriceConfigureObject();
		}
		
	}


    /**
	 * This method will get the movie
	 * @return Movie2 movie
	 */
	public Movie2 getMovie() {return this.movie;}
    /**
	 * This method will get the cinema
	 * @return Cinema cinema
	 */
	public Cinema getCinema() {return this.cinema;}
    /**
	 * This method will get the buyer
	 * @return buyer ticket buyer of the movie
	 */
	public TicketBuyer getBuyer() {return this.buyer;} 
    /**
	 * This method will get the movie timing
	 * @return Movietiming movie timing of the movie
	 */
	public MovieTiming getMovieTiming() {return this.timing;} 
    /**
	 * This method will get the seat
	 * @return Seats seat that the buyer purchased
	 */
	public Seats getSeat() {return this.seat;} 
    /**
	 * This method will get the price configure object
	 * @return PriceConfigure PriceConfigure object
	 */
	public PriceConfigure getPriceConfigure() {return this.configure;}

	/**
	 * This method will set the movie
	 * @param movie Movie2
	 */
	public void setMovie(Movie2 movie) {this.movie = movie;} 
	/**
	 * This method will set the cinema
	 * @param cinema cinema
	 */
	public void setCinema(Cinema cinema) {this.cinema = cinema;} 
	/**
	 * This method will set the buyer
	 * @param buyer ticket buyer of the movie
	 */
	public void setBuyer(TicketBuyer buyer) {this.buyer = buyer;} 
	/**
	 * This method will set the movie timing
	 * @param timing movietiming of the movie
	 */
	public void setTiming(MovieTiming timing) {this.timing = timing;} 
	/**
	 * This method will set the seat
	 * @param seat seat that the buyer purchased
	 */
	public void setSeat(Seats seat) {this.seat = seat;} 
	/**
	 * This method will set the price configure object
	 * @param configure PriceConfigure object
	 */
	public void setPriceConfigure(PriceConfigure configure) {this.configure = configure;}


	//functions 
	//calculate the price of a movie ticket based on: 
	//1. the cinema class (classic, platinum, gold)
	//2. ticket buyer age (senior is <55, student is <18, adult is 18<=age<=55
	//3. seat type (normal, couple, elite, ultima)
	//4. day of week (monday, tuesday, wednesday, thursday, friday
	//5. whether or not it is a holiday (true, false) 
	//6. time (before or after 6pm -- matters on fridays) 
	//7. movie type (normal, blockbuster, 3D) 
	//ASSUMPTION: for student and seniors, blockbuster and normal movies are priced the same

	//DEFAULT PRICING (before any staff edits on ticket pricing) 
	//AGE-RELATED DISCOUNTS 
	//Senior Citizen Discount = $4.50
	//Student Discount (Normal Movies) = $1.50
	//Student,3D Movies (Price Adjuster) = $2.00 //+$2.00 from Discounted Student Price for Normal Movies 

	//FOR NORMAL ADULTS: (by movie type and day of week) 
	//Normal Movie, Thurday and Early Friday = $1.00
	//Normal Movie, Late Friday, Saturday, Sunday and Holidays = $2.50 
	//Blockbuster Movie, Monday, Tuesday, Wednesday = $1.00 
	//Blockbuster Movie, Thursday and Early Friday = $2.00 
	//Blockbuster Movie, Late Friday, Saturday, Sunday and Holidays = $3.50
	//3D Movie, Monday, Tuesday, Wednesday, Thursday = $2.50
	//3D Movie, Friday, Saturday, Sunday and Holidays = $6.50

	//BY SEAT TYPE (multiplier) 
	//Normal = 0 
	//Couple = 2
	//Elite = 2.5
	//Ultima = 3

	//BY CINEMA CLASS 
	//Classic Cinema = $0
	//Platinum Class = $1.50 
	//Gold Class = $3.00

	/**
	 * This method will calculate the price of the price object based the the parameters that will affect price
	 * @return double value of the ticket price
	 */
	public double calculatePrice() {

		
		double price = 0; 
		price += configure.getBasePrice(); 
		try {
			this.timing.setHoliday();
		} catch (Exception e) {
			System.out.println("No holidays loaded");
		}
		
		//NON-HOLIDAY PRICING 
		if (this.timing.getHoliday()==false) {
			//SENIORS AND STUDENT PRICING
			//day switch case for discounts 
			switch(this.timing.getDay()) {

			case "Monday": 
			case "Tuesday": 
			case "Wednesday": 
				//senior 
				if (this.buyer.getAge() > 55) {
					//price -= 4.50; 
					price -= configure.getSeniorsDiscount();
					//System.out.println("Seniors on MonTuesWedThurs: " + price);
				}

				//student
				else if (this.buyer.getAge() < 18) {
					//price -= 1.50; 
					price -= configure.getStudentDiscount();
					//System.out.println("Students on MonTuesWedThurs: " + price);

					if (this.movie.getType().compareTo("3D") == 0){
						//price += 2.00; 
						price += configure.getStudent3DAdjust();
						//System.out.println("Student 3D on MonTuesWedThurs: " + price);
					}
				}

				//adults
				else {
					if (this.movie.getType().compareTo("Blockbuster") == 0) {
						//price += 1.00;
						price += configure.getBlockMTW();
						//System.out.println("Adult Blockbuster on MonTuesWed: " + price);
					}
					else if (this.movie.getType().compareTo("3D") == 0) {
						//price += 2.50; 
						price += configure.getThreedMTWT();
						//System.out.println("Adult 3D on MonTuesWed: " + price);
					}
				}
				break; 
			case "Thursday":
				//senior 
				if (this.buyer.getAge() > 55) {
					//price -= 4.50; 
					price -= configure.getSeniorsDiscount();
					//System.out.println("Seniors on Thurs: " + price);
				}

				//student
				else if (this.buyer.getAge() < 18) {
					//price -= 1.50; 
					price -= configure.getStudentDiscount();
					//System.out.println("Students on Thurs: " + price);

					if (this.movie.getType().compareTo("3D") == 0) {
						//price += 2.00; 
						price -= configure.getStudent3DAdjust();
						//System.out.println("Student 3D on Thurs: " + price);
					}
				}

				//adults
				else {
					if (this.movie.getType().compareTo("Normal") == 0) {
						//price += 1.00;
						price += configure.getNormTeF();
						//System.out.println("Adult Normal on Thursday: " + price);
					}
					else if (this.movie.getType().compareTo("Blockbuster") == 0) {
						//price += 2.00;
						price += configure.getBlockTeF();
						//System.out.println("Adult Blockbuster on Thursday: " + price);
					}
					else if (this.movie.getType().compareTo("3D") == 0) {
						//price += 2.50; 
						price += configure.getThreedMTWT();
						//System.out.println("Adult 3D on Thursday: " + price);
					}
				}
				break;
			case "Friday": 
				if (time < 1800) {
					//senior 
					if (this.buyer.getAge() > 55) {
						//price -= 4.50; 
						price -= configure.getSeniorsDiscount();
						//System.out.println("Seniors on Early Friday: " + price);
					}

					//student
					else if (this.buyer.getAge() < 18) {
						//price -= 1.50; 
						price -= configure.getStudentDiscount();
						//System.out.println("Students on Early Friday: " + price);

						if (this.movie.getType().compareTo("3D") == 0) {
							//price += 2.00; 
							price += configure.getStudent3DAdjust();
							//System.out.println("Student 3D on Early Friday: " + price);
						}
					}
					//adults
					else {
						if (this.movie.getType().compareTo("Normal") == 0) {
							//price += 1.00;
							price += configure.getNormTeF();
							//System.out.println("Adult Normal on Early Friday: " + price);
						}
						else if (this.movie.getType().compareTo("Blockbuster") == 0) {
							//price += 2.00;
							price += configure.getBlockTeF(); 
							//System.out.println("Adult Blockbuster on Early Friday: " + price);
						}
						else if (this.movie.getType().compareTo("3D") == 0) {
							//price += 6.50; 
							price += configure.getThreedFSSH();
							//System.out.println("Adult 3D on Early Friday: " + price);
						}
					}
				}

				//Friday after 6pm
				else {
					if (this.movie.getType().compareTo("Normal") == 0) {
						//price += 2.50;
						price += configure.getNormlFSSH();
					}

					else if (this.movie.getType().compareTo("Blockbuster") == 0) {
						//price += 3.50;
						price += configure.getBlockFSSH();
					}

					else if (this.movie.getType().compareTo("3D") == 0) {
						//price += 6.50;
						price += configure.getThreedFSSH();
					}
				}
				break; 
			case "Saturday": 
			case "Sunday": 
				if (this.movie.getType().compareTo("Normal") == 0) {
					//price += 2.50;
					price += configure.getNormlFSSH();
				}

				else if (this.movie.getType().compareTo("Blockbuster") == 0) {
					//price += 3.50;
					price += configure.getBlockFSSH();
				}

				else if (this.movie.getType().compareTo("3D") == 0) {
					//price += 6.50;
					price += configure.getThreedFSSH();
				}
			}
		}

		//HOLIDAY PRICING 
		else {
			if (this.movie.getType().compareTo("Normal") == 0) {
				//price += 2.50;
				price += configure.getNormlFSSH();
			}

			else if (this.movie.getType().compareTo("Blockbuster") == 0) {
				//price += 3.50;
				price += configure.getBlockFSSH();
			}

			else if (this.movie.getType().compareTo("3D") == 0) {
				//price += 6.50;
				price += configure.getThreedFSSH();
			}
		}

		//CHECK SEAT TYPE 
		switch(this.seat.getSeatType()) {
		//Normal Seat + $0
		case "Normal": 
			//price *= 1 
			price *= configure.getNSeatMultiplier();
			//System.out.println("You have seleted a Normal Seat + 0: " + price);
			break; 

			//Couple Seat *2 whatever price 
		case "Couple": 
			//price *= 2;
			price *= configure.getCSeatMultiplier();
			//System.out.println("You have seleted Couple Seat *2 Price: " + price);
			break; 

			//Elite Seat *2.5 whatever price 
		case "Elite": 
			//price *= 2.5; 
			price *= configure.getESeatMultiplier();
			//System.out.println("You have seleted Elite Seat *2.5 Price: " + price);
			break; 

			//Ultima Seat *3 whatever price 
		case "Ultima": 
			//price *= 3; 
			price *= configure.getUSeatMultiplier();
			//System.out.println("You have seleted Ultima Seat *3 Price: " + price);
			break;
		}

		//CHECK CINEMA CLASS 
		switch (this.cinema.getCinemaClass()) {
		//Classic + $0 
		case 1: 
			//price += 0
			price += configure.getClassic();
			//System.out.println("You have seleted Classic Cinema class + $0: " + price);
			break; 
			//Platinum + $1.50 
		case 2:
			//price += 1.50 ;
			price += configure.getPlatinum();
			//System.out.println("You have seleted Platinum Cinema class + $1.50: " + price);
			break;
			//Gold + $3.00 
		case 3: 
			//price += 3.00; 
			price += configure.getGold();
			//System.out.println("You have seleted Gold Cinema class + $3.00: " + price);
			break; 
		}
		//System.out.println("Final Price: " + price);
		return price; 

	}
}
