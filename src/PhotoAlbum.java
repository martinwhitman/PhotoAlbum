/*Martin Whitman Lean Techniques Technical Showcase 2/20/19 Option 1 Photo Album
martin.whitman.1776@gmail.com
"Create a console application that displays photo ids and titles in an album. The photos are available in this online web service (https://jsonplaceholder.typicode.com/photos)."
*/

//Standard imports
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

//HTTPS connection import
import javax.net.ssl.HttpsURLConnection;

//JSON imports, see pom.xml for Maven dependency used
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * @author Martin Whitman
 *
 */
public class PhotoAlbum {

	/**Main method
	 * @param args
	 */
	public static void main(String[] args) {
		//Open scanner for input
		Scanner in = new Scanner(System.in);
		//Welcome and instruction
		System.out.println("Welcome to the Photo Retrieval Program!");
		System.out.println(
				"From which photo album do you wish to retrieve photo titles and their corresponding IDs?");
		//integer for input
		int number;
		//Do-While loop for input validation of positive numbers for album id
		do {
		    System.out.println("Enter a positive whole number.");
		    while (!in.hasNextInt()) {
		        System.out.println("That's not a number.\nEnter a positive whole number");
		        in.next();
		    }
		    number = in.nextInt();
		} while (number <= 0);
		//convert number to string idInput for appending to URL
		String idInput =Integer.toString(number);
		//ArrayList of photo objects to hold return from getAlbumInfo method
		ArrayList<Photo> result = getAlbumInfo(idInput);
		//step through arraylist to write output to console
		for (int i = 0; i < result.size(); i++) {
			System.out.println(result.get(i).toString());
		}
		//close scanner to avoid resource leaks
		in.close();
	}

	/**
	 * Method to get the photo info via HTTP Get request
	 * @param albumID
	 * @return ArrayList of Photo objects
	 */
	public static ArrayList<Photo> getAlbumInfo(String albumID) {
		//arraylist of photo objects to return
		ArrayList<Photo> photos = new ArrayList<Photo>();
		//try catch block to handle IO exceptions
		try {
			//concatenates desired album ID to the URL string
			URL url = new URL("https://jsonplaceholder.typicode.com/photos?albumId=" + albumID);
			//open HTTPS connection
			HttpsURLConnection hUC = (HttpsURLConnection) url.openConnection();
			//indicates HTTP GET request since we're just retrieving information
			hUC.setRequestMethod("GET");
			//Reader to process response input
			BufferedReader in = new BufferedReader(new InputStreamReader(hUC.getInputStream()));
			//StringBuilder,line string to append input
			StringBuilder content = new StringBuilder();
			String line;
			//appends response to StringBuilder object
			while (null != (line = in.readLine())) {
				content.append(line);
			}
			//Creates JSONArray out of string from StringBuilder object
			JSONArray responseJSONArray = new JSONArray(content.toString());
			//Steps through JSONArray to get individual JSON objects, creates Photo objects with JSON object data, adds to photos ArrayList
			for (int i = 0; i < responseJSONArray.length(); i++) {
				JSONObject photo = responseJSONArray.getJSONObject(i);
				Photo p = new Photo((int) photo.get("id"), photo.get("title").toString());
				photos.add(p);
			}

		} catch (IOException e) {
			// catch IO Exceptions
			e.printStackTrace();
		}
		//return ArrayList of Photos
		return photos;
	}
}
