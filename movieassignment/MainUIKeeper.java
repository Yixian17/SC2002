package movieassignment;

import java.io.EOFException;
import java.io.FileNotFoundException;

/**
*This class creates staffUI and userUI objects to display to the user
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
*/

public class MainUIKeeper {
	private MainUIInterface staff; 
	private MainUIInterface goer; 

	/**
	*This default construct creates new staff and goer objects
	*/
	public MainUIKeeper() {
		staff = new StaffUI(); 
		goer = new UserUI(); 
	}
	/**
	*This method will display the staff interface
   * @throws FileNotFoundException if binary file cannot be created or edited
   * @throws EOFException if binary file is empty
	*/
	public void displayStaffInterface() throws Exception { 
		staff.displayInterface(); 
	}
	/**
	*This method will display the user interface
   * @throws FileNotFoundException if binary file cannot be created or edited
   * @throws EOFException if binary file is empty
	*/
	public void displayGoerInterface() throws Exception {
		goer.displayInterface(); 
	}
}
