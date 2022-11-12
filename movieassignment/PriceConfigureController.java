package movieassignment;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

/**
 * This class creates PricesconfigureController object which have methods to change the prices that the user will user
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */

public class PriceConfigureController {
	/**
	 * This method write the binary data file with the new prices data
	 * @throws EOFException if binary file is empty
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 */	
	public static void createPriceConfigureObject() throws Exception{
		Scanner input = new Scanner(System.in);
		FileOutputStream fos1 = new FileOutputStream("PriceConfiguration");
		ObjectOutputStream oos1 = new ObjectOutputStream(fos1);
		PriceConfigure configure = new PriceConfigure();
		oos1.writeObject(configure);
		oos1.close();
	}
	/**
	 * This method reads the binary data file for the previous saved values
	 * @return PriceConfigure object after reading from file
	 * @throws EOFException if binary file is empty
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 */		
	public static PriceConfigure readPriceConfigureObject() throws Exception{
		Scanner sc = new Scanner(System.in);
		FileInputStream fis = new FileInputStream("PriceConfiguration");
		ObjectInputStream ois= new ObjectInputStream(fis);
		PriceConfigure readOnlyPriceConfigure = (PriceConfigure) ois.readObject();
		ois.close();
		return readOnlyPriceConfigure;

	}

	//DEFAULT PRICING (before any staff edits on ticket pricing) 
	//AGE-RELATED DISCOUNTS 
	//Senior Citizen Discount = $4.50
	//Student Discount (Normal Movies) = $1.50
	//Student,3D Movies (Price Adjuster) = $2.00 //+$2.00 from Discounted Student Price for Normal Movies 

	//FOR NORMAL ADULTS: (by movie type and day of week) 
	//ASSUMPTION: NORMAL MOVIE MON-WED FOLLOWS BASE PRICE 
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
	 * This method will prompt the user to adjust the price of the ticket and save it to the binary file
	 * @throws EOFException if binary file is empty
	 * @throws FileNotFoundException if binary file cannot be created or edited
	 */	
	public static void editPriceConfigureObject() throws Exception{
		Scanner sc = new Scanner(System.in);
		FileInputStream fis = new FileInputStream("PriceConfiguration");
		ObjectInputStream ois= new ObjectInputStream(fis);
		System.out.println("---------------------");
		System.out.println("Select pricing to edit: ");
		System.out.println("(1) Base Price");
		System.out.println("(The following are adjusted with respect to base price ($8.50)");
		System.out.println("(2) Senior Citizen");
		System.out.println("(3) Student (Normal)");
		System.out.println("(4) Student (3D) ");
		System.out.println("(5) Normal Movie (Thurs & Fri before 6pm)");
		System.out.println("(6) Normal Movie (Fri after 6pm, Sat, Sun & Holidays");
		System.out.println("(7) Blockbuster Movie (Mon-Wed)");
		System.out.println("(8) Blockbuster Movie (Thurs & Fri before 6pm)");
		System.out.println("(9) Blockbuster Movie (Fri after 6pm, Sat, Sun & Holidays)");
		System.out.println("(10) 3D Movie (Mon-Thurs)");
		System.out.println("(11) 3D Movie (Fri-Sun & Holidays)");
		System.out.println("(12) Normal Seat Type");
		System.out.println("(13) Couple Seat Type");
		System.out.println("(14) Elite Seat Type");
		System.out.println("(15) Ultima Seat Type");
		System.out.println("(16) Classic Class Cinema");
		System.out.println("(17) Platinum Class Cinema");
		System.out.println("(18) Gold Class Cinema");
		System.out.println("---------------------");
		int configureChoice = sc.nextInt(); 

		PriceConfigure configureNew = new PriceConfigure();
		configureNew = (PriceConfigure) ois.readObject();

		switch(configureChoice) {
		case 1: 
			System.out.println("Enter the new BASE PRICE: ");
			double base = sc.nextDouble(); 
			configureNew.setBasePrice(base);
			break; 

		case 2: 
			System.out.println("Enter new DISCOUNT for Senior Citizens: ");
			System.out.println("(Note: Senior Citizen Price = base price - DISCOUNT))");
			double seniorsD = sc.nextDouble(); 
			configureNew.setSeniorsDiscount(seniorsD);
			break;

		case 3: 
			System.out.println("Enter new DISCOUNT for Student (Normal Movies): ");
			System.out.println("(Note: Student Price (Normal Movies) = base price - DISCOUNT))");
			double studentD = sc.nextDouble(); 
			configureNew.setStudentDiscount(studentD);
			break;

		case 4: 
			System.out.println("Enter new PRICE ADJUSTMENT for Student (3D Movies): ");
			System.out.println("(Note: Student Price (3D) = Student Price (Normal Movies) + PRICE ADJUSTMENT)");
			double student3D = sc.nextDouble(); 
			configureNew.setStudent3DAdjust(student3D);
			break; 

		case 5: 
			System.out.println("Enter new PRICE ADJUSTMENT for Normal Movies (Thurs & Fri before 6pm): ");
			System.out.println("(Note: Normal Movie Price (Thurs & Fri before 6pm) = base price + PRICE ADJUSTMENT)");
			double normalTeF = sc.nextDouble(); 
			configureNew.setNormTeF(normalTeF); 
			break;

		case 6: 
			System.out.println("Enter new PRICE ADJUSTMENT for Normal Movies (Fri after 6pm, Sat, Sun & Holidays): ");
			System.out.println("(Note: Normal Movie Price (Fri after 6pm, Sat, Sun & Holidays) = base price + PRICE ADJUSTMENT)");
			double normallFSSH = sc.nextDouble();
			configureNew.setNormlFSSH(normallFSSH); 
			break; 

		case 7: 
			System.out.println("Enter new PRICE ADJUSTMENT for Blockbuster Movies (Mon-Wed): ");
			System.out.println("(Note: Blockbuster Movie Price (Mon-Wed) = Base Price + PRICE ADJUSTMENT)");
			double blockbusterMTW = sc.nextDouble(); 
			configureNew.setBlockMTW(blockbusterMTW); 
			break;

		case 8:
			System.out.println("Enter new PRICE ADJUSTMENT for Blockbuster Movies (Thurs & Fri before 6pm): ");
			System.out.println("(Note: Blockbuster Movie Price (Thurs & Fri before 6pm) = Base Price + PRICE ADJUSTMENT)");
			double blockbusterTeF = sc.nextDouble(); 
			configureNew.setBlockTeF(blockbusterTeF); 
			break;

		case 9: 
			System.out.println("Enter new PRICE ADJUSTMENT for Blockbuster Movies (Fri after 6pm, Sat, Sun & Holidays):");
			System.out.println("(Note: Blockbuster Movie Price (Fri after 6pm, Sat, Sun & Holidays) = Base Price + PRICE ADJUSTMENT)");
			double blockbusterlFSSH = sc.nextDouble(); 
			configureNew.setBlockFSSH(blockbusterlFSSH); 
			break; 

		case 10: 
			System.out.println("Enter new PRICE ADJUSTMENT for 3D Movies (Mon-Thurs): ");
			System.out.println("(Note: 3D Movie Price (Mon-Thurs) = Base Price + PRICE ADJUSTMENT)");
			double tdMTWT = sc.nextDouble(); 
			configureNew.setThreedMTWT(tdMTWT);
			break; 

		case 11: 
			System.out.println("Enter new PRICE ADJUSTMENT for 3D Movies (Fri-Sun & Holidays)");
			System.out.println("(Note: 3D Movie Price (Fri-Sun & Holidays) = Base Price + PRICE ADJUSTMENT)");
			double tdFSSH = sc.nextDouble(); 
			configureNew.setThreedFSSH(tdFSSH); 
			break; 

		case 12: 
			System.out.println("Enter new PRICE MULTIPLIER for Price of Normal Seats: ");
			System.out.println("(Note: Price of Normal Seats = Derived Price * PRICE MULTIPLIER)");
			System.out.println("(Note: Derived Price is Price adjusted by Age-Related Discounts, Movie Type, Day of Week & Holidays)");
			double normal = sc.nextDouble(); 
			configureNew.setNSeatMultiplier(normal); 
			break; 

		case 13: 
			System.out.println("Enter new PRICE MULTIPLIER for Price of Couple Seats: ");
			System.out.println("(Note: Price of Couple Seats = Derived Price * PRICE MULTIPLIER)");
			System.out.println("(Note: Derived Price is Price adjusted by Age-Related Discounts, Movie Type, Day of Week & Holidays)");
			double couple = sc.nextDouble(); 
			configureNew.setCSeatMultiplier(couple); 
			break; 

		case 14: 
			System.out.println("Enter new PRICE MULTIPLIER for Price of Elite Seats: ");
			System.out.println("(Note: Price of Elite Seats = Derived Price * PRICE MULTIPLIER)");
			System.out.println("(Note: Derived Price is Price adjusted by Age-Related Discounts, Movie Type, Day of Week & Holidays)");
			double elite = sc.nextDouble(); 
			configureNew.setESeatMultiplier(elite); 
			break; 

		case 15: 
			System.out.println("Enter new PRICE MULTIPLIER for Price of Ultima Seats: ");
			System.out.println("(Note: Price of Ultima Seats = Derived Price * PRICE MULTIPLIER)");
			System.out.println("(Note: Derived Price is Price adjusted by Age-Related Discounts, Movie Type, Day of Week & Holidays)");
			double ultima = sc.nextDouble(); 
			configureNew.setNSeatMultiplier(ultima); 
			break; 

		case 16: 
			System.out.println("Enter new PRICE ADJUSTER for Price of Classic Class Cinema: ");
			System.out.println("(Note: Price of Classic Class Cinema = Current Price + PRICE ADJUSTER)");
			System.out.println("(Note: Current Price is Price adjusted by Seat Type, Age-Related Discounts, Movie Type, Day of Week & Holidays)");
			double classic = sc.nextDouble(); 
			configureNew.setClassic(classic); 
			break; 

		case 17: 
			System.out.println("Enter new PRICE ADJUSTER for Price of Platinum Class Cinema: ");
			System.out.println("(Note: Price of Platinum Class Cinema = Current Price + PRICE ADJUSTER)");
			System.out.println("(Note: Current Price is Price adjusted by Seat Type, Age-Related Discounts, Movie Type, Day of Week & Holidays)");
			double platinum = sc.nextDouble(); 
			configureNew.setPlatinum(platinum); 
			break; 

		case 18: 
			System.out.println("Enter new PRICE ADJUSTER for Price of Gold Class Cinema: ");
			System.out.println("(Note: Price of Gold Class Cinema = Current Price + PRICE ADJUSTER)");
			System.out.println("(Note: Current Price is Price adjusted by Seat Type, Age-Related Discounts, Movie Type, Day of Week & Holidays)");
			double gold = sc.nextDouble(); 
			configureNew.setGold(gold); 
			break; 
		}

		ois.close();

		FileOutputStream fos3 = new FileOutputStream("PriceConfiguration");
		ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
		oos3.writeObject(configureNew);

		oos3.close();

	}

	
}
