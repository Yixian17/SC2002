package movieassignment;

import java.io.EOFException;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Scanner;

/**
 * This class creates Pricesconfigure object which will store the prices and method to change the prices
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */

public class PriceConfigure implements Serializable {
	//private static final long serialVersionUID = 1L;
	private static final long serialVersionUID =  -2658405035770636518L;
	private double basePrice; 
	private double seniorsDiscount; 
	private double studentDiscount; 
	private double student3DAdjust; 
	private double normTeF;
	private double normlFSSH;
	private double blockMTW; 
	private double blockTeF; 
	private double blocklFSSH; 
	private double threedMTWT;
	private double threedFSSH;
	private double nSeatMultiplier;
	private double cSeatMultiplier;
	private double eSeatMultiplier; 
	private double uSeatMultiplier;
	private double classic;
	private double platinum;
	private double gold; 

    /**
	 * This constructor initialise the default value of all the prices and their adjustment
	 */
	public PriceConfigure() {
		this.basePrice = 8.50;
		//DEFAULT PRICING (before any staff edits on ticket pricing) 
		//AGE-RELATED DISCOUNTS 
		//Senior Citizen Discount = $4.50
		this.seniorsDiscount = 4.50;
		//Student Discount (Normal Movies) = $1.50
		this.studentDiscount = 1.50;
		//Student,3D Movies (Price Adjuster) = $2.00 //+$2.00 from Discounted Student Price for Normal Movies 
		this.student3DAdjust = 2.00; 
		
		//FOR NORMAL ADULTS: (by movie type and day of week) 
		//Normal Movie, Thurday and Early Friday = $1.00
		this.normTeF = 1.00; 
		//Normal Movie, Late Friday, Saturday, Sunday and Holidays = $2.50 
		this.normlFSSH = 2.50;
		//Blockbuster Movie, Monday, Tuesday, Wednesday = $1.00 
		this.blockMTW = 1.00;
		//Blockbuster Movie, Thursday and Early Friday = $2.00 
		this.blockTeF = 2.00; 
		//Blockbuster Movie, Late Friday, Saturday, Sunday and Holidays = $3.50
		this.blocklFSSH  = 3.50;
		//3D Movie, Monday, Tuesday, Wednesday, Thursday = $2.50
		this.threedMTWT = 2.50; 
		//3D Movie, Friday, Saturday, Sunday and Holidays = $6.50
		this.threedFSSH = 6.50;  
		
		//BY SEAT TYPE (multiplier) 
		//Normal = 1
		this.nSeatMultiplier = 1.00;
		//Couple = 2
		this.cSeatMultiplier = 2.00;
		//Elite = 2.5
		this.eSeatMultiplier = 2.50;
		//Ultima = 3
		this.uSeatMultiplier = 3.00; 

		//BY CINEMA CLASS 
		//Classic Cinema = $0
		this.classic = 0.00;
		//Platinum Class = $1.50 
		this.platinum = 1.50;
		//Gold Class = $3.00
		this.gold = 3.00;
	}

	   /**
		 * This method will return the base price
		 * @return double base price
		 */
	public double getBasePrice() {return this.basePrice;}
	   /**
		 * This method will return the senior discount 
		 * @return double seniorsDiscount
		 */
	public double getSeniorsDiscount(){return this.seniorsDiscount;}
	   /**
		 * This method will return the student discount
		 * @return double studentDiscount
		 */
	public double getStudentDiscount() {return this.studentDiscount;}
	   /**
		 * This method will return the student3DAdjust
		 * @return double student3DAdjust
		 */
	public double getStudent3DAdjust() {return this.student3DAdjust;}
	   /**
		 * This method will return the normal movie Thursday to early Friday price
		 * @return double normal movie Thursday to early Friday price
		 */
	public double getNormTeF() {return this.normTeF; }
	   /**
		 * This method will return the normal movie Friday,Saturday,Sunday or Holiday price
		 * @return double normal movie Friday,Saturday,Sunday or Holiday price
		 */
	public double getNormlFSSH() {return this.normlFSSH;}
	   /**
		 * This method will return the blockbuster movie Monday,Tuesday,Wednesday price
		 * @return double blockbuster movie Monday,Tuesday,Wednesday price
		 */
	public double getBlockMTW() {return this.blockMTW;}
	   /**
		 * This method will return the blockbuster movie Thursday to early Friday price
		 * @return double blockbuster movie Thursday to early Friday price
		 */
	public double getBlockTeF() {return this.blockTeF;} 
	   /**
		 * This method will return the blockbuster movie Friday,Saturday,Sunday or Holiday price
		 * @return double blockbuster movie Friday,Saturday,Sunday or Holiday price
		 */
	public double getBlockFSSH() {return this.blocklFSSH;}
	   /**
		 * This method will return the 3D movie Monday,Tuesday,Wednesday,Thursday price
		 * @return double 3D movie Monday,Tuesday,Wednesday,Thursday price
		 */
	public double getThreedMTWT() {return this.threedMTWT;}
	   /**
		 * This method will return the 3D movie Friday,Saturday,Sunday or Holiday price
		 * @return double 3D movie Friday,Saturday,Sunday or Holiday price
		 */
	public double getThreedFSSH() {return this.threedFSSH;}
	   /**
		 * This method will return the normal seat multiplier 
		 * @return double nSeatMultiplier
		 */
	public double getNSeatMultiplier() {return this.nSeatMultiplier;}
	   /**
		 * This method will return the Couple seat multiplier 
		 * @return double cSeatMultiplier
		 */
	public double getCSeatMultiplier() {return this.cSeatMultiplier;}
	   /**
		 * This method will return the Elite seat multiplier 
		 * @return double eSeatMultiplier
		 */
	public double getESeatMultiplier() {return this.eSeatMultiplier;}
	   /**
		 * This method will return the Ultima seat multiplier 
		 * @return double uSeatMultiplier
		 */
	public double getUSeatMultiplier() {return this.uSeatMultiplier;}
	   /**
		 * This method will return the classic cinema class price
		 * @return double classic cinema class price
		 */
	public double getClassic() {return this.classic;}
	   /**
		 * This method will return the platinum class price
		 * @return double platinum
		 */
	public double getPlatinum() {return this.platinum;}
	   /**
		 * This method will return the Gold cinema class price
		 * @return double gold
		 */
	public double getGold() {return this.gold;}
	
	/**
	 * This method will set the base price
	 * @param basePrice double base price
	 */
	public void setBasePrice(double basePrice) {this.basePrice = basePrice;} 
	/**
	 * This method will set the senior discount 
	 * @param seniorsDiscount double  adjustment
	 */
	public void setSeniorsDiscount(double seniorsDiscount){this.seniorsDiscount = seniorsDiscount;} 
	/**
	 * This method will set the student discount
	 * @param studentDiscount double adjustment
	 */
	public void setStudentDiscount(double studentDiscount) {this.studentDiscount = studentDiscount;}  
	/**
	 * This method will set the student3DAdjust
	 * @param student3DAdjust double adjustment 
	 */
	public void setStudent3DAdjust(double student3DAdjust) {this.student3DAdjust = student3DAdjust;} 
	/**
	 * This method will set the normal movie Thursday to early Friday price
	 * @param normTeF normal movie Thursday to early Friday price
	 */
	public void setNormTeF(double normTeF) {this.normTeF = normTeF;}
	/**
	 * This method will set the normal movie Friday,Saturday,Sunday or Holiday price
	 * @param normlFSSH normal movie Friday,Saturday,Sunday or Holiday price
	 */
	public void setNormlFSSH(double normlFSSH) {this.normlFSSH = normlFSSH;} 
	/**
	 * This method will set the blockbuster movie Monday,Tuesday,Wednesday price
	 * @param blockMTW blockbuster movie Monday,Tuesday,Wednesday price
	 */
	public void setBlockMTW(double blockMTW) {this.blockMTW = blockMTW; }
	/**
	 * This method will set the blockbuster movie Thursday to early Friday price
	 * @param blockTeF blockbuster movie Thursday to early Friday price
	 */
	public void setBlockTeF(double blockTeF) {this.blockTeF = blockTeF;}
	/**
	 * This method will set the blockbuster movie Friday,Saturday,Sunday or Holiday price
	 * @param blocklFSSH blockbuster movie Friday,Saturday,Sunday or Holiday price
	 */
	public void setBlockFSSH(double blocklFSSH) {this.blocklFSSH = blocklFSSH;}
	/**
	 * This method will set the 3D movie Monday,Tuesday,Wednesday,Thursday price
	 * @param threedMTWT 3D movie Monday,Tuesday,Wednesday,Thursday price
	 */
	public void setThreedMTWT(double threedMTWT) {this.threedMTWT = threedMTWT;}
	/**
	 * This method will set the 3D movie Friday,Saturday,Sunday or Holiday price
	 * @param threedFSSH 3D movie Friday,Saturday,Sunday or Holiday price
	 */
	public void setThreedFSSH(double threedFSSH) {this.threedFSSH = threedFSSH;}
	/**
	 * This method will set the normal seat multiplier 
	 * @param nSeatMultiplier double multiplier 
	 */
	public void setNSeatMultiplier(double nSeatMultiplier) {this.nSeatMultiplier = nSeatMultiplier;}
	/**
	 * This method will set the Couple seat multiplier 
	 * @param cSeatMultiplier double multiplier 
	 */
	public void setCSeatMultiplier(double cSeatMultiplier) {this.cSeatMultiplier = cSeatMultiplier;}
	/**
	 * This method will set the Elite seat multiplier 
	 * @param eSeatMultiplier double multiplier 
	 */
	public void setESeatMultiplier(double eSeatMultiplier) {this.eSeatMultiplier = eSeatMultiplier;}
	/**
	 * This method will set the Ultima seat multiplier 
	 * @param uSeatMultiplier double multiplier 
	 */
	public void setUSeatMultiplier(double uSeatMultiplier) {this.uSeatMultiplier = uSeatMultiplier;}
	/**
	 * This method will set the classic cinema class price
	 * @param classic classic cinema class price
	 */
	public void setClassic(double classic) {this.classic = classic;}
	/**
	 * This method will set the platinum class price
	 * @param platinum platinum cinema class price
	 */
	public void setPlatinum(double platinum) {this.platinum = platinum;}
	/**
	 * This method will set the Gold cinema class price
	 * @param gold gold cinema class price
	 */
	public void setGold(double gold) {this.gold = gold;}
}

