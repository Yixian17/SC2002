package movieassignment;
import java.util.*;
import java.io.*;  // Import the File class

/**
*This class creates the MainUI for the user of the application to interface with
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
*/

public class MainUI {
	private static ArrayList<staff> staffList = new ArrayList<staff>();
	/**
	*This main method for the application
	*@param args string argument for main function
	*@throws FileNotFoundException if "StaffDetails2" cannot be created or edited
	*/
	public static void main(String[] args) throws Exception  
	{	
		MainUIKeeper keeper = new MainUIKeeper(); 
		Scanner sc = new Scanner (System.in); 
		int useroption; 
		System.out.println("Welcome to Moblima"); 
		do {
		System.out.println("---------------------"); 	
		System.out.println("Select Option: "); 
		System.out.println("(1) Staff ");
		System.out.println("(2) Customer"); 
		System.out.println("(3) Create New Staff Account"); 
		System.out.println("---------------------"); 
		useroption = sc.nextInt(); 
		
			switch(useroption) {
				case 1: //enter staff UI
					int count = 3;
					do {
						System.out.println("---------------------"); 
						System.out.print("Enter your Login ID: "); 
						String loginID1 = sc.next();
						System.out.print("Enter your Password: "); 
						String password1 = sc.next();
						try {
						if(readStaffDetails(loginID1,password1)==true) {
								keeper.displayStaffInterface();
						} else {
							count--;
							System.out.println("Invalid User/pass. You have " + count + " tries left");
						}
						}
						catch (Exception e){
							System.out.println("An Error has occured, Please restart");		
							break;
						}
					} while (count>0);
					break;
					
					
				case 2: //enter customer UI 
					try {
						keeper.displayGoerInterface();
					} 
					catch (Exception e) {
						e.printStackTrace();
					}
						break;
						
				case 3: try {createAccount();}
        				catch (Exception e) {createFirstAccount();}
						break;
					
			}
		}while (useroption<4);
	}
	
	/**
	*This method writes to a new binary file "StaffDetails2" that will store the staff objects with login detail
	*@throws FileNotFoundException if "StaffDetails2" cannot be created or edited
	*/
	public static void createFirstAccount() throws Exception {
		FileOutputStream fos = new FileOutputStream("StaffDetails2");
		ObjectOutputStream oos = new ObjectOutputStream(fos);
		int num = 1; //Indicating Number of Staff
		oos.writeInt(num); //Writing it at the top of the file
		staff firstStaff = new staff(); //Creating first staff
		oos.writeObject(firstStaff);
		oos.close();
	}
	/**
	*This method add a new staff object to the binary file "StaffDetails2" that will store the login details
	*@throws EOFException if "StaffDetails2" is empty
	*/
	public static void createAccount() throws Exception {		
		FileInputStream fis = new FileInputStream("StaffDetails2");
		ObjectInputStream ois = new ObjectInputStream(fis);
		
		int num = ois.readInt();
		num++;
		ArrayList<staff> sList = new ArrayList<staff>();
		//read all Movie objects file
		for (int i = 0; i < num - 1; i++) {
			sList.add((staff) ois.readObject());
		}
		ois.close();
		
		System.out.println("---------------------");
		//append Movie
		sList.add(new staff());
		
		// rewrite database
		FileOutputStream fos2 = new FileOutputStream("StaffDetails2");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);		
		oos2.writeInt(num);
		for (staff i : sList) {
			oos2.writeObject(i);
		}
		oos2.close();
	}
	
	/**
	*This method verifies that the user trying to log in matches with the data stored in the binary file
	*@param LoginID login ID to be matched 
	*@param Password login password to be matched
	*@return A boolean expression of the weather login in successful
	*@throws EOFException if "StaffDetails2" is empty
	*/
	public static boolean readStaffDetails(String LoginID, String Password) throws Exception {
		FileInputStream fis = new FileInputStream("StaffDetails2");
		try {
			ObjectInputStream ois= new ObjectInputStream(fis);
			int num = ois.readInt();
			staffList.clear();
			for (int i = 0; i < num; i++) {
				staffList.add((staff) ois.readObject());
				if ((staffList.get(i).getLoginID()).compareTo(LoginID)==0 && (staffList.get(i).getPassword()).compareTo(Password)==0){
				return true;
				}
			}			
			ois.close();
			return false;
			}	
			catch(Exception EOFException) {return false;}
		}
}
