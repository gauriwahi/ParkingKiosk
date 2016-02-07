


import java.awt.CardLayout;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

import javax.swing.*;

public class Screen {

	private final static String DATABASEONE = "students.txt";
	

	public static void main(String args[]) throws Exception {
		/* Get Clients from database */
		LinkedList<Client> clients = loadClients();
		
		Screen model = new Screen();
		Controller controller = new Controller(clients);
		View view = new View(controller);
		controller.addModel(model);
		controller.addView(view);
		view.addController(controller);
		
		/* Instantiate welcome Screen View */

		/* Go to welcome Screen View */
		
		
		/* touch interaction and update model object */

		/* Go to verify details View */
	}

	/* parse through database and load into list */
	private static LinkedList<Client> loadClients() throws IOException {
		/* for every line in database */
		LinkedList<Client> c = new LinkedList();
		/* create new client */
		// Open the file
		FileInputStream fstream;
		fstream = new FileInputStream(DATABASEONE);

		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;

		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			// Print the content on the console
			//System.out.println(strLine);
			strLine.replaceAll("[\\s]*", "");
			String temp[] = strLine.split(",[\\s]*");
			Client new_client = new Client((temp[0]),(temp[1]), temp[2], temp[3], temp[4]);
			System.out.println((temp[0])+(temp[1])+ temp[2]+ temp[3]+ temp[4]);
			c.add(new_client);
		}

		// Close the input stream
		br.close();
		return c;
	}
}
