package movieassignment;

import java.io.EOFException;
import java.io.FileNotFoundException;

/**
 * This interface for displaying UI for the user
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */

public interface MainUIInterface {
	/**
	 * This abstract method for displayInterface function which will display a UI for the user
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */
	public void displayInterface() throws Exception; 
}
