package movieassignment;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
/**
 * This class creates ArrayList of movies and contains the methods to process and use it.
 * @author Lin Da wei
 * @author Jarel Tan Zhi Wen 
 * @author Lui Shi Ying 
 * @author Solomon Duke Tneo Yruan Rui
 * @author Wong Yi Xian 
 */
public class MovieListing2 {
	// public static final String SEPARATOR = "|";
	private static ArrayList<Movie2> movies = new ArrayList<Movie2>();
	private static ArrayList<Movie2> usermovies = new ArrayList<Movie2>();

	
	
	/**
	 * This method reads from the binary file and list out all the movies
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */
	public static void listMovie() throws Exception {
		FileInputStream fis = new FileInputStream("MovieDetails6.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(fis);
			int num = ois.readInt();
			System.out.println("---------------------");
			System.out.println("List of movie:");
			movies.clear();
			for (int i = 0; i < num; i++) {
				movies.add((Movie2) ois.readObject());
				System.out.println("Movie " + (i + 1) + " : " + movies.get(i).getTitle());
			}
			ois.close();
		} catch (Exception EOFException) {
			return;
		}

	}

	/**
	 * This method reads from the binary file and list out all the movies
     * @throws FileNotFoundException if binary file cannot be created or edited
     * @throws EOFException if binary file is empty
	 */
	public static void displayMovieDetails() throws Exception {
		FileInputStream fis2 = new FileInputStream("MovieDetails6.txt");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);
		int num = ois2.readInt();
		Movie2[] mList = new Movie2[num];
		System.out.println("---------------------");
		System.out.println("List of Movies and their details:");
		for (int i = 0; i < num; i++) {
			mList[i] = (Movie2) ois2.readObject();
			System.out.println("Movie " + (i + 1) + ": " + mList[i].getTitle());
			mList[i].printInfo();
			System.out.println();
		}
		ois2.close();

	}

	/**
	*This method is used to create a new movie to empty binary file
	*Use this if the binary file is empty
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void createFirstMovie() throws Exception {
		FileOutputStream fos2 = new FileOutputStream("MovieDetails6.txt");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		int num = 1; // Indicating Number of Movie
		oos2.writeInt(num); // Writing it at the top of the file
		Movie2 firstMovie = new Movie2(); // Creating first movie
		oos2.writeObject(firstMovie);
		oos2.close();
	}

	/**
	*This method is used to create a new movie and add it to the binary file
	*Use this if the binary file is not empty
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void createMovie() throws Exception {
		FileInputStream fis = new FileInputStream("MovieDetails6.txt");
		ObjectInputStream ois = new ObjectInputStream(fis);

		int num = ois.readInt();
		num++;
		ArrayList<Movie2> mList = new ArrayList<Movie2>();
		// read all Movie objects file
		for (int i = 0; i < num - 1; i++) {
			mList.add((Movie2) ois.readObject());
		}
		ois.close();

		System.out.println("---------------------");
		// append Movie
		mList.add(new Movie2());

		// rewrite database
		FileOutputStream fos2 = new FileOutputStream("MovieDetails6.txt");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		oos2.writeInt(num);
		for (Movie2 i : mList) {
			oos2.writeObject(i);
		}
		oos2.close();
	}
	/**
	*This method is used to edit a movie and update changes to the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void editMovie() throws Exception {

		Scanner input = new Scanner(System.in);
		int movieChoice, i, informationChoice;
		FileInputStream fis3 = new FileInputStream("MovieDetails6.txt");
		ObjectInputStream ois3 = new ObjectInputStream(fis3);

		int num = ois3.readInt();
		Movie2[] m2List = new Movie2[num];

		System.out.println("---------------------");
		System.out.println("Select movie to edit");
		movies.clear();
		for (i = 0; i < num; i++) {
			movies.add((Movie2) ois3.readObject());
			System.out.println("Movie " + (i + 1) + " : " + movies.get(i).getTitle());
		}
		System.out.println("---------------------");

		FileInputStream fis4 = new FileInputStream("MovieDetails6.txt");
		ObjectInputStream ois4 = new ObjectInputStream(fis4);
		ois4.readInt();
		for (int j = 0; j < num; j++) {
			m2List[j] = (Movie2) ois4.readObject();
		}

		ois3.close();
		ois4.close();

		movieChoice = input.nextInt();
		if (movieChoice > num) {
			System.out.println("---------------------");
			System.out.println("Try again, Please select one movie from above.");
			return;
		}
		System.out.println("---------------------");
		System.out.println("Select information to edit:");
		movies.get(movieChoice - 1).printInfo();
		informationChoice = input.nextInt();
		if (informationChoice > 7) {
			System.out.println("---------------------");
			System.out.println("Try again, Please choose a number between 1 and 7");
			return;
		}
		System.out.println("---------------------");
		input.nextLine();
		switch (informationChoice) {
		case 1:
			movies.get(movieChoice - 1).setTitle();
			break;
		case 2:
			movies.get(movieChoice - 1).setShowingStatus();
			break;
		case 3:
			movies.get(movieChoice - 1).setMovieType();
			break;
		case 4:
			movies.get(movieChoice - 1).setMovieRating();
			break;
		case 5:
			movies.get(movieChoice - 1).setSynopsis();
			break;
		case 6:
			movies.get(movieChoice - 1).setDirector();
			break;
		case 7:
			movies.get(movieChoice - 1).setCast();
			break;
		}

		FileOutputStream fos3 = new FileOutputStream("MovieDetails6.txt");
		ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
		oos3.writeInt(num);
		for (int j = 0; j < num; j++) {
			if (!(movieChoice == j + 1)) {
				oos3.writeObject(m2List[j]);
			} else
				oos3.writeObject(movies.get(movieChoice - 1));
		}

		oos3.close();

	}

	/**
	*This method is used to remove a movie from the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void removeMovie() throws Exception {
		Scanner input = new Scanner(System.in);
		int sel, i;
		FileInputStream fis2 = new FileInputStream("MovieDetails6.txt");
		ObjectInputStream ois2 = new ObjectInputStream(fis2);

		int num = ois2.readInt();
		Movie2[] mList = new Movie2[num];
		System.out.println("---------------------");
		System.out.println("Select movie to remove:");
		for (i = 0; i < num; i++) {
			mList[i] = (Movie2) ois2.readObject();
			System.out.println("Movie (" + (i + 1) + "): " + mList[i].getTitle());

		}
		System.out.println("---------------------");
		ois2.close();
		sel = input.nextInt();
		if (sel > num) {
			do {
				System.out.println("---------------------");
				System.out.println("Try again, Please select one movie from above.");
				sel = input.nextInt();
			} while (sel > num);
		}
		input.nextLine();

		// update number of movie
		num--;

		FileOutputStream fos2 = new FileOutputStream("MovieDetails6.txt");
		ObjectOutputStream oos2 = new ObjectOutputStream(fos2);
		oos2.writeInt(num);
		for (i = 0; i < num + 1; i++) {
			if (!(sel == i + 1)) {
				oos2.writeObject(mList[i]);
			}
		}

		oos2.close();
	}
	/**
	*This method is used to list from the binary file filtered for the user
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/ 
	public static void listUserMovie() throws Exception {
		FileInputStream fis = new FileInputStream("MovieDetails6.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(fis);
			int num = ois.readInt();
			int j = 0;
			System.out.println("---------------------");
			System.out.println("Select a movie:");
			movies.clear();
			usermovies.clear();
			for (int i = 0; i < num; i++) {
				movies.add((Movie2) ois.readObject());
				if (movies.get(i).getShowingStatus() != 4) {
					System.out.println("Movie (" + (j + 1) + "): " + movies.get(i).getTitle());
					usermovies.add(movies.get(i));
					j++;
				}
			}
			ois.close();
		} catch (Exception EOFException) {
			return;
		}

	}
	/**
	*This method is used to return all movie titles in a arraylist
	*@return ArrayList String of all movie titles
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/ 	
	public static ArrayList<String> ReturnAllMovie() throws Exception {
		ArrayList<String> allMovie = new ArrayList<String>();
		FileInputStream fis = new FileInputStream("MovieDetails6.txt");
		try {
			ObjectInputStream ois = new ObjectInputStream(fis);
			int num = ois.readInt();
			int j = 0;
			movies.clear();
			// usermovies.clear();
			for (int i = 0; i < num; i++) {
				movies.add((Movie2) ois.readObject());
				allMovie.add(movies.get(i).getTitle());
				// usermovies.add(movies.get(i));
				j++;

			}
			ois.close();
			return allMovie;
		} catch (Exception EOFException) {
			System.out.println("No Movies:");
			return null;
		}

	}
	/**
	*This method is used to return a movie object
	*@return Movie2 Movie object
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/ 
	public static Movie2 chooseMovie() throws Exception {
		Scanner input = new Scanner(System.in);
		listUserMovie();
		System.out.println("---------------------");
		int userinput = input.nextInt();
		if (userinput > usermovies.size()) {
			do {
				System.out.println("---------------------");
				System.out.println("Try Again! Select a movie from above");
				System.out.println("---------------------");
				userinput = input.nextInt();
			} while (userinput > usermovies.size());
		}
		System.out.println("---------------------");
		Movie2 choice = usermovies.get(userinput - 1);
		input.nextLine();

		return choice;
	}
	/**
	*This method list out movie details of a single movie
	*@param movie a Movie2 object to display movie details
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/ 
	public static void displaySingleMovieDetails(Movie2 movie) throws Exception {
		System.out.println("---------------------");
		// System.out.println("List of Movies and their details:");
		System.out.println(movie.getTitle() + " Movie Details");
		// System.out.println("Movie " + (i + 1) + ":");
		movie.printInfo();
		System.out.println();

	}
	/**
	*This method adds a review to the selected movie
	*@param movie a Movie2 object to add review to
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/ 	
	public static void addReview(Movie2 movie) throws Exception {

		Movie2 m = null;
		Movie2 m2 = null;
		int rate = 0;
		String comments = " ";
		String title;
		Scanner sc = new Scanner(System.in);
		System.out.println("What is your score (out of 5) for the movie: " + movie.getTitle() + " ?");
		rate = Integer.parseInt(sc.nextLine());
		System.out.println("What are your comments on the movie? ");
		comments = sc.nextLine();
		Review r = new Review();
		r.setRating(rate);
		r.setReview(comments);
		movie.addReview(r);
		saveMovie();

		// }
		// }

	}

	/**
	*This method prints top 5 movies based on moviegoer review rating
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/
	public static void printTop5Rating() throws Exception {
		Movie2 m = null;
		// int number = 0;
		FileInputStream fis3 = new FileInputStream("MovieDetails6.txt");
		ObjectInputStream ois3 = new ObjectInputStream(fis3);
		int num = ois3.readInt();

		// Movie2[] ml = new Movie2[num];
		List<Movie2> ml = new ArrayList<Movie2>();
		// Movie2[] top5 = new Movie2[5];
		List<Movie2> top5 = new ArrayList<Movie2>();

		for (int i = 0; i < num; i++) {
			m = (Movie2) ois3.readObject();
			// ml[i] = m;
			ml.add(m);
		}

		for (int i = 0; i < num; i++) {
			int maxIndex = 0;
			// for(int j = 1; j < ml.length;j++)
			for (int j = 1; j < ml.size(); j++) {
				// if(ml[j].getRating() > ml[maxIndex].getRating())
				if (ml.get(j).getRating() > ml.get(maxIndex).getRating()) {
					maxIndex = j;
					// System.out.println("Movie:" + ml[j].getTitle());
				}
			}

			top5.add(ml.remove(maxIndex));

		}
		System.out.println("====Top 5 Movies Based on Rating====");
		int num1 = 0;
		for (Movie2 movie : top5) {
			if(movie.getRating()!=0)
			{System.out.print(num1 + 1 + ". " + movie.getTitle() + ": ");
			System.out.printf("%.1f ", +movie.getRating());
			System.out.println();
			num1++;}
			else {
				System.out.print(num1 + 1 + ". " + movie.getTitle() + ": ");
				System.out.printf("NA");
				System.out.println();
				num1++;
			}
		}
	}
	/**
	*This method saves all movie of the movie arraylist to the binary file
	* @throws EOFException if binary file is empty
	* @throws FileNotFoundException if binary file cannot be created or edited
	*/ 
	public static void saveMovie() throws Exception {
		FileOutputStream fos3 = new FileOutputStream("MovieDetails6.txt");
		ObjectOutputStream oos3 = new ObjectOutputStream(fos3);
		oos3.writeInt(movies.size());
		for (int j = 0; j < movies.size(); j++) {
			oos3.writeObject(movies.get(j));
		}
		oos3.close();
	}

}
