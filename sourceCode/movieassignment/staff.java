package movieassignment;
import java.util.*;
import java.io.*;  // Import the File class
import java.io.Serializable;
/**
*This class creates the staff which will store the login details
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
*/
public class staff implements Serializable{
	private static final long serialVersionUID = 1L;
	private String loginID;
	private String Password;
	
	
	/**
	*This is the constructor for staff, prompts user to enter login ID and password for new staff account
	*/
	public staff() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter new login ID: ");
		this.loginID = input.nextLine();
		System.out.print("Enter password: ");
		this.Password = input.nextLine();
	}
	/**
	*This method gets the Login ID of the current staff object
	*@return String String of staff loginID
	*/
	public String getLoginID() {return this.loginID; }
	/**
	*This method gets the password of the current staff object
	*@return String String of staff password
	*/
	public String getPassword() {return this.Password;}
	
	/**
	*This method prompts the user to enter a new password and sets it
	*/
	public void setNewPassword() {
		Scanner input = new Scanner(System.in);
		System.out.print("Enter new password: ");
		this.Password = input.nextLine();
	}
}
