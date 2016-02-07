


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Controller {
	final String STATUSOK = "ok";
	final String STATUSARREARS = "arrears";
	final String DATABASEONE = "companies.txt";
	Screen model;
	View view;
	Client c;

	LinkedList<Client> clients;

	/* create controller */
	public Controller(LinkedList<Client> clients) {
		this.clients = clients;

		/* compare inputed value with onscreen value */
	}

	public void actionPerformed(java.awt.event.ActionEvent e) {
		// uncomment to see what action happened at view
		/*
		 * System.out.println ("Controller: The " + e.getActionCommand() +
		 * " button is clicked at " + new java.util.Date(e.getWhen()) +
		 * " with e.paramString " + e.paramString() );
		 */
		System.out.println("Controller: acting on Model");

	}

	public void addModel(Screen m) {
		model = m;
	}

	public void addView(View v) {
		view = v;

	}

	public void setClient(Client c) {
		this.c = c;
	}

	public Client getClient() {
		return c;
	}

	/* update view */
	/**
	 * 
	 * Post: return ok = 1 arrears= -1 not found = 0
	 * */
	public int validation(String s, String p) {
		for (int i = 0; i < clients.size(); i++) {
			Client cur = clients.get(i);

			if (s.equals(cur.getStudentNum()) && p.equals(cur.getPin())) {
				this.setClient(cur);
				if (cur.getStatus().equals(STATUSOK)) {
					return 1;
				} else {
					return -1;
				}
			}
		}
		return 0;

	}

	public String[] getInsuranceCompany() throws IOException {
		List<String> company = new ArrayList<String>();
		// Open the file
		FileInputStream fstream;
		fstream = new FileInputStream(DATABASEONE);

		BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

		String strLine;
		int counter = 0;
		// Read File Line By Line
		while ((strLine = br.readLine()) != null) {
			strLine.replaceAll("[\\s]*[\\n]*", "");
			if(!(strLine.isEmpty() || strLine.trim().equals("") || strLine.trim().equals("\n")))company.add(strLine);
		}
		br.close();
		String s [] = company.toArray(new String[0]);
		return s;
		
	}

}
