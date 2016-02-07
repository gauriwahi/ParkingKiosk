

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentListener;
import java.awt.event.*;
import java.io.FileOutputStream;
import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.text.SimpleDateFormat;
import java.util.Observable;

/*
 * View Class
 */
public class View extends JFrame implements ActionListener {

	/*
	 * The following avoids a "warning" with Java 1.5.0 complier.
	 */
	static final long serialVersionUID = 42L;
	
	/*
	 * Fields for users to Log in.
	 */
	private JTextField studentNo;
	private JPasswordField studentPIN;
	private JButton login;

	
	/*
	 * Fields for 2nd page where users add info.
	 */
	private JTextField messageInput;
	private JTextField messageDisplay;
	private JTextField carModel;
	private JTextField carMake;
	private JTextField plateNo;
	private JTextField carColour;
	private JTextField policy;

	private JComboBox insuranceCombo;
	private JComboBox dayCombo;
	private JComboBox monthCombo;
	private JComboBox yearCombo;
	
	private JLabel permitLabel;
	private JLabel textDate;
	
	private double PRICE = 3.50;
	private double amount = 0;
	final int MESSAGE_SIZE = 20;

	private JButton submit;
	
	/*
	 * Fonts for whole program.
	 */
	Font headerFont = new Font("serif", Font.PLAIN, 30);
	Font textFont = new Font("serif", Font.PLAIN, 20);
	Font TitledBorderFont = new Font("serif", Font.BOLD, 20);
	Font buttonFont = new Font("serif", Font.PLAIN, 20); 
	
	String carColourString;
	
	private Keyboard keyboard = new Keyboard();
	private JPasswordField password;
	
	/*
	 * Fields for page 5. 
	 */
	private JPanel permit = new JPanel();
	private JTextField emailField;
	private JLabel emailLabel;
	private JCheckBox emailConsent;
	private JLabel congrats;
	
	/*
	 * Databases for Dates.
	 */
	final String[] days = { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10",
			"11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
			"22", "23", "24", "25", "26", "27", "28", "29", "30", "31" };
	final String[] months = { "January", "February", "March", "April", "May",
			"June", "July", "August", "September", "October", "November",
			"December" };
	final String[] years = { "2015", "2016", "2017", "2018", "2019", "2020",
			"2021", "2022" };

	
	int permitDays = 1;
	final int SIZE = 8;
		
	/*
	 * Array and vars used to get data from text field and print in final permit pop up.  
	 */
	JLabel arrayInfo[] = new JLabel[10];
	JLabel crMdl;
	JLabel crClr;
	JLabel pltN;
	JLabel crmk;

	/*
	 * Get Insurance Companies Names
	 */
	String insurance[];

	/*
	 * Frame declaration and all panels that will be switched between like pages.
	 */
	JFrame frame = new JFrame("YorkU Parking Permit");
	JPanel panelCont = new JPanel();
	JPanel panelFirst = new JPanel();
	JPanel panelSecond = new JPanel();
	JPanel panelThird = new JPanel();
	JPanel panelFourth = new JPanel();
	JPanel panelArrear = new JPanel();
	JPanel panelPermit = new JPanel();

	CardLayout cl = new CardLayout();

	Controller c;
	
/*******
 * View*
 *******/
	public View(Controller c) throws Exception {
		this.addController(c);
		panelCont.setLayout(cl);
		panelCont.setBackground(Color.WHITE);
		panelFirst.setBackground(Color.WHITE);
		panelSecond.setBackground(Color.WHITE);
		panelArrear.setBackground(getBackground());
		setUpOne();
		panelCont.add(panelFirst, "1");
		insurance = c.getInsuranceCompany();
		setUpTwo();
		panelCont.add(panelSecond, "2");

		cl.show(panelCont, "1");

		frame.add(panelCont);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.pack();
		frame.setVisible(true);
	}


	/*
	 * Construct and configure the components and create views. 
	 */
	
	/* 
	 *  Splash screen with starting graphic.
	 */
	private void setUpOne() {

		/* Display giant button for start menu. */
		JButton startTap = new JButton();
		startTap.setIcon(new ImageIcon("car.jpg"));
		panelFirst.add(startTap);
		startTap.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(panelCont, "2");
			}
		});
	}

	/********************************************************
	 * Login page where users put in student number and pin.*
	 ********************************************************/
	private void setUpTwo() {
		setExtendedState(java.awt.Frame.MAXIMIZED_BOTH);
	
		/*
		 * Fields to collect data.
		 */
		studentNo = new JTextField("", SIZE);
		studentNo.requestFocus(); // sets this box to be the first targeted box
		carModel = new JTextField("", SIZE);
		carMake = new JTextField("", SIZE);
		plateNo = new JTextField("", SIZE);
		carColour = new JTextField("", SIZE);
		policy = new JTextField("", SIZE);
		String word = "Ok";
		studentPIN = new JPasswordField(10);

		insuranceCombo = new JComboBox(insurance);

		Calendar cd = Calendar.getInstance();
		dayCombo = new JComboBox(days);
		((JLabel) dayCombo.getRenderer()) .setHorizontalAlignment(SwingConstants.CENTER);
		((JTextField) dayCombo.getEditor().getEditorComponent()) .setHorizontalAlignment(JTextField.CENTER);
		dayCombo.setSelectedIndex(cd.get(Calendar.DAY_OF_MONTH)-1);

		monthCombo = new JComboBox(months);
		((JLabel) monthCombo.getRenderer()).setHorizontalAlignment(SwingConstants.LEFT);
		((JTextField) monthCombo.getEditor().getEditorComponent()).setHorizontalAlignment(JTextField.LEFT);
		monthCombo.setSelectedIndex(cd.get(Calendar.MONTH));

		yearCombo = new JComboBox(years);
		((JLabel) yearCombo.getRenderer()).setHorizontalAlignment(SwingConstants.CENTER);
		((JTextField) yearCombo.getEditor().getEditorComponent()).setHorizontalAlignment(JTextField.CENTER);
		yearCombo.setSelectedIndex(cd.get(Calendar.YEAR)-2015);

		dayCombo.addActionListener(this);
		monthCombo.addActionListener(this);
		yearCombo.addActionListener(this);

		/*
		 * Login Button.
		 */
		login = new JButton("LOGIN");
		login.setBackground(Color.red.darker());
		login.setForeground(Color.white);
		login.setContentAreaFilled(false);
		login.setOpaque(true);
		login.setBorder(BorderFactory.createRaisedBevelBorder());
		login.setPreferredSize(new Dimension(60, 40));

		/*
		 * Change Spacing.
		 */
		studentNo.setMargin(new Insets(0, 3, 0, 0));
		studentPIN.setMargin(new Insets(0, 3, 0, 0));
		carModel.setMargin(new Insets(0, 3, 0, 0));
		carMake.setMargin(new Insets(0, 3, 0, 0));
		plateNo.setMargin(new Insets(0, 3, 0, 0));
		carModel.setMargin(new Insets(0, 3, 0, 0));
		carColour.setMargin(new Insets(0, 3, 0, 0));
		policy.setMargin(new Insets(0, 3, 0, 0));

		/*
		 * Add Listeners 
		 */
		login.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String p = new String(studentPIN.getPassword());
				String s = studentNo.getText();

				System.out.println("TEST submit:" + s);
				System.out.println("TEST submit:" + p);
				if (s == null || !s.matches("[0-9]*")) {
					s = " ";
				}
				if (p == null || !p.matches("[0-9]*")) {
					p = " ";
				}
				int result = c.validation(s, p);

				if (result == 1) {
					try {
						setUpPermit();
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					panelCont.add(panelPermit, "permit");
					cl.show(panelCont, "permit");
				} else if (result == -1) {
					setUpArrears();
					panelCont.add(panelArrear, "arrears");
					cl.show(panelCont, "arrears");
					// in arrears
				} else {
					JOptionPane.showMessageDialog(null,
							"Invalid Student Number or PIN");
					panelSecond.setBackground(Color.red);

				}
			}

		});
		

		/*
		 * Add components to panels.
		 */
		JPanel paymentInfo = new JPanel();

		JPanel space = new JPanel();
		space.add(Box.createVerticalStrut(100));
		space.setBackground(Color.white);
		JPanel f1 = new JPanel();
		f1.setLayout(new GridLayout(0, 1));
		f1.add(new JLabel("Student Number", SwingConstants.RIGHT));
		f1.add(new JLabel("PIN ", SwingConstants.RIGHT));
		f1.setBackground(Color.white);

		JPanel f2 = new JPanel();
		f2.setLayout(new GridLayout(0, 1));
		f2.add(studentNo);
		f2.add(studentPIN);
		f2.setBackground(Color.white);

		JPanel studentInfo = new JPanel();
		studentInfo.setLayout(new BorderLayout());
		studentInfo.add(f1, "West");
		studentInfo.add(f2, "East");
		studentInfo.setBackground(Color.white);
		TitledBorder studentLabel = new TitledBorder(new EtchedBorder(), "Student Information");
		studentLabel.setTitleFont(TitledBorderFont);
		studentLabel.setTitleJustification(TitledBorder.CENTER);

		studentInfo.setBorder(studentLabel);

		JPanel loginPanel = new JPanel();
		loginPanel.add(login);
		loginPanel.setBackground(Color.white);

		JPanel colourPanel = new JPanel();
		colourPanel.add(studentInfo);
		colourPanel.setBackground(Color.white);

		/*
		 * Arrange panels.
		 */
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		top.add(paymentInfo, BorderLayout.NORTH);
		top.add(space);
		top.add(colourPanel, BorderLayout.CENTER);
		top.setBackground(Color.white);

		JPanel bottom = new JPanel();
		bottom.setLayout(new BoxLayout(bottom, BoxLayout.Y_AXIS));

		bottom.add(loginPanel);
		
		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.add(top, "North");
		contentPane.add(bottom, "Center");
		contentPane.setBackground(Color.white);

		panelSecond.setLayout(new BorderLayout());
		panelSecond.add(contentPane);
		panelSecond.add(keyboard.getKeyboard(), BorderLayout.SOUTH);
		panelSecond.setBorder(BorderFactory.createLineBorder(Color.red.darker()));
	}
	
	
	/********************************************
	 * Confirmation page for user to review it. *
	 ********************************************/
	private void setUpThree() {
		panelThird = new JPanel();
		panelThird.setLayout(new GridBagLayout());
		panelThird.setBackground(Color.white);
		GridBagConstraints c = new GridBagConstraints();
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 0;
		c.gridwidth = 2;
		JLabel chkinfo = new JLabel("Please check the information below: ", SwingConstants.CENTER);
		chkinfo.setFont(headerFont);
		panelThird.add(chkinfo, c);

		JPanel receiptPanel = new JPanel();
		receiptPanel.setLayout(new BoxLayout(receiptPanel, BoxLayout.Y_AXIS));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 0.5;
		c.gridx = 0;
		c.gridy = 1;
		c.gridwidth = 2;
		panelThird.add(receiptPanel, c);
		panelThird.setBorder(BorderFactory.createLineBorder(Color.RED.darker()));

		TitledBorder infoConfirm = new TitledBorder(new EtchedBorder(), "Information");
		infoConfirm.setBorder(BorderFactory.createLineBorder(Color.red.darker(), 4));
		receiptPanel.setBackground(Color.white);
		infoConfirm.setTitleFont(TitledBorderFont);
		receiptPanel.setBorder(infoConfirm);

		crMdl = new JLabel("Vehicle's Model: " + carModel.getText(), SwingConstants.LEFT);
		arrayInfo[0] = crMdl;
		crMdl.setFont(textFont);
		receiptPanel.add(crMdl);

		crmk = new JLabel("Vehicle's Make: " + carMake.getText(), SwingConstants.LEFT);
		arrayInfo[1] = crmk;
		crmk.setFont(textFont);
		receiptPanel.add(crmk);

		pltN = new JLabel("Plate Number: " + plateNo.getText(), SwingConstants.LEFT);
		arrayInfo[2] = pltN;
		System.out.println(pltN.getText());
		pltN.setFont(textFont);
		receiptPanel.add(pltN);

		crClr = new JLabel("Vehicle's Colour: " + carColour.getText(), SwingConstants.LEFT);
		arrayInfo[3] = crClr;
		crClr.setFont(textFont);
		receiptPanel.add(crClr);

		JLabel insrncCmbo = new JLabel("Insurance Company: " + insuranceCombo.getSelectedItem(), SwingConstants.LEFT);
		insrncCmbo.setFont(textFont);
		receiptPanel.add(insrncCmbo);

		JLabel dt = new JLabel("Permit Expiry Date: "
				+ (monthCombo.getSelectedIndex() + 1) + "-"
				+ (dayCombo.getSelectedItem()) + "-"
				+ (yearCombo.getSelectedItem()), SwingConstants.LEFT);
		dt.setFont(textFont);
		receiptPanel.add(dt);

		JButton edit = new JButton("Edit"); // info is incorrect and user is editing 
		edit.setFont(buttonFont);
		edit.setOpaque(false);
		edit.setBackground(Color.red.darker());
		edit.setBorder(BorderFactory.createLineBorder(Color.red.darker()));
		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1;// where extra space should go
		c.gridx = 0;// which cell column
		c.gridy = 2;// which cell row
		c.gridwidth = 1;// how many cells button should span
		c.ipady = 20;// height of button
		c.insets = new Insets(10, 100, 0, 100);
		panelThird.add(edit, c);

		edit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cl.show(panelCont, "permit"); // go back one page
			}
		});

		JButton confirm = new JButton("Confirm"); // info is good and correct
		confirm.setFont(buttonFont);
		confirm.setBackground(Color.red.darker());
		confirm.setBorder(BorderFactory.createRaisedBevelBorder());
		confirm.setContentAreaFilled(false);
		confirm.setOpaque(true);

		c.fill = GridBagConstraints.HORIZONTAL;
		c.weightx = 1; 
		c.gridx = 1; 
		c.gridy = 2; 
		c.gridwidth = 1; 
		c.ipady = 20; 
		c.insets = new Insets(10, 100, 0, 100); // top padding
		panelThird.add(confirm, c);

		confirm.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					setUpFour();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				panelCont.add(panelFourth, "4");
				cl.show(panelCont, "4"); // go to next page
			}
		});
	}

	/*****************************************************
	 * Final Page where user can view and email receipt. * 
	 ****************************************************/
	private void setUpFour() throws Exception {
		
		/*
		 * Build Components
		 */
		panelFourth.setLayout(new BoxLayout(panelFourth, BoxLayout.Y_AXIS));
		panelFourth.setBackground(Color.white);

		congrats = new JLabel("Congratulations, you have been approved for a Parking Permit!");
		congrats.setFont(TitledBorderFont);
		
		emailConsent = new JCheckBox("Yes, I would like my Permit E-Mailed to me.");
		
		JButton recieptButton = new JButton("View Permit");
		
		emailLabel = new JLabel("Email: ");
		emailField = new JTextField("", 15);
		emailField.setEditable(false);
		
		JButton send = new JButton("Send");
		
		permit.setBackground(Color.white);
		permit.setLayout(new BoxLayout(permit, BoxLayout.Y_AXIS));
		permit.add(new JLabel("YorkU Parking Permit"));
		permit.add(new JLabel ("----------------------------------"));
		permit.add(new JLabel (crmk.getText()));
		permit.add(new JLabel (crMdl.getText()));
		permit.add(new JLabel (crClr.getText()));
		permit.add(new JLabel (pltN.getText()));
		
		Keyboard k3 = new Keyboard();
				
		
		/*
		 * Add listeners. 
		 */
		recieptButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JOptionPane.showMessageDialog(null, permit, "Permit", JOptionPane.PLAIN_MESSAGE); 
			}
		});
		

		emailConsent.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(emailConsent.isSelected())
					emailField.setEditable(true);
				else{
					emailField.setEditable(false);
				}
			}
		});
		
		/*
		 * Add and arrange components.
		 */
		congrats.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelFourth.add(congrats, BorderLayout.CENTER);
		panelFourth.add(Box.createVerticalStrut(60));
		recieptButton.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelFourth.add(recieptButton, BorderLayout.CENTER);
		panelFourth.add(Box.createVerticalStrut(10));
		emailConsent.setAlignmentX(Component.CENTER_ALIGNMENT);
		panelFourth.add(emailConsent, BorderLayout.CENTER);
		JPanel emailPanel = new JPanel();
		emailPanel.setBackground(Color.white);

		emailPanel.add(emailLabel);
		emailPanel.add(emailField);
		emailPanel.add(send);
		panelFourth.add(emailPanel);
		panelFourth.add(k3.getKeyboard(), "SOUTH");		
	}
	
	/****************************
	 * User is in arrears page. *
	 ***************************/
	public void setUpArrears() {
		panelArrear.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		gbc.gridwidth = gbc.HORIZONTAL;
		gbc.gridy = 0;
		System.out.println("wat");
		JLabel apology = new JLabel("We're Sorry " + c.getClient().getFName() + " " + c.getClient().getLName());

		panelArrear.add(apology, gbc);
		JLabel instruct = new JLabel();
		instruct.setIcon(new ImageIcon("transportation.jpg"));
		gbc.gridy = 1;
		panelArrear.add(instruct, gbc);
		JButton exit = new JButton("LOGOUT");
		gbc.gridy = 2;
		gbc.gridx = gbc.CENTER;
		
		exit.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				studentNo.setText("");
				studentPIN.setText("");
				panelArrear.removeAll();

				cl.show(panelCont, "1");
			}

		});
		panelArrear.add(exit, gbc);
	}

	/*******************************************
	 * Third page where user enters their info.*
	 *******************************************/
	public void setUpPermit() throws Exception {
		/*
		 * Build all components.
		 */
		// this will be located north
		JLabel welcomeSlogan = new JLabel("Welcome " + c.getClient().getFName()	+ " " + c.getClient().getLName());
		welcomeSlogan.setFont(TitledBorderFont);

		welcomeSlogan.setForeground((Color.white));
		JPanel welcome = new JPanel();
		welcome.setBorder(new TitledBorder(new EtchedBorder()));
		welcome.add(welcomeSlogan);
		welcomeSlogan.setBackground((Color.white));
		welcome.setBackground((Color.red).darker());
		JPanel ensure = new JPanel();
		JLabel ensureLabel = new JLabel("Please fill in the following information and ensure it is correct: ");
		ensureLabel.setFont(textFont);
		ensure.add(ensureLabel);
		ensure.add(Box.createVerticalStrut(70));
		ensure.setBackground((Color.white));

		JPanel b1 = new JPanel();
		b1.setLayout(new GridLayout(0, 1));
		b1.add(new JLabel("Vehicle's Model", SwingConstants.RIGHT));
		b1.add(new JLabel("Vehicle's Make ", SwingConstants.RIGHT));
		b1.add(new JLabel("Plate Number ", SwingConstants.RIGHT));
		b1.add(new JLabel("Vehicle's Colour", SwingConstants.RIGHT));
		b1.setBackground((Color.white));

		JPanel b2 = new JPanel();
		b2.setLayout(new GridLayout(0, 1));
		b2.add(carModel);
		b2.add(carMake);
		b2.add(plateNo);
		b2.add(carColour);
		b2.setBackground((Color.white));

		JPanel vehicleInfo = new JPanel();
		vehicleInfo.setLayout(new BorderLayout());
		vehicleInfo.add(b1, "West");
		vehicleInfo.add(b2, "East");
		TitledBorder vehicleLabel = new TitledBorder(new EtchedBorder(), "Vehicle Information");
		vehicleLabel.setTitleFont(textFont);
		vehicleInfo.setBorder(vehicleLabel);
		vehicleInfo.setBackground((Color.white));

		// insurance information should be placed on right + date
		JPanel insurancePanel = new JPanel();
		insurancePanel.setLayout(new BoxLayout(insurancePanel, BoxLayout.X_AXIS));
		insurancePanel.add(new JLabel("Insurance Company: "));
		insurancePanel.add(insuranceCombo);
		insurancePanel.setBackground((Color.white));

		JPanel datePanel = new JPanel();
		datePanel.setLayout(new GridLayout(1, 3));
		datePanel.setSize(50, 30);
		datePanel.add(dayCombo);
		datePanel.add(monthCombo);
		datePanel.add(yearCombo);
		datePanel.setBorder(new TitledBorder(new EtchedBorder(), "Permit is valid from " + new SimpleDateFormat("dd  MMMMMMMM  yyyy").format(new Date()) + " until "));
		datePanel.setBackground(Color.white);

		JPanel dateInfo = new JPanel();
		dateInfo.add(datePanel);
		dateInfo.setBackground((Color.white));

		double amount = 3.50;

		String permitLabel = "Your total amount is $" + amount + " for a permit of " + permitDays + " day(s)";
		textDate = new JLabel(permitLabel, SwingConstants.CENTER);
		dateInfo.add(textDate);
		JPanel policyInfo = new JPanel();

		policyInfo.add(new JLabel("Policy Number"));
		policyInfo.add(policy);
		policyInfo.setBackground((Color.white));

		JPanel amountInfo = new JPanel();
		TitledBorder amountLabel = new TitledBorder(new EtchedBorder(),"Permit Information");
		amountLabel.setTitleFont(textFont);
		amountInfo.setBorder(amountLabel);
		amountInfo.add(dateInfo);
		amountInfo.setBackground((Color.white));

		JPanel studentInfo = new JPanel();
		studentInfo.setLayout(new BoxLayout(studentInfo, BoxLayout.Y_AXIS));
		studentInfo.add(insurancePanel);

		studentInfo.add(policyInfo);
		studentInfo.add(amountInfo);

		TitledBorder titleAmount = new TitledBorder(new EtchedBorder(),	"Insurance Information");
		titleAmount.setTitleFont(textFont);

		studentInfo.setBorder(titleAmount);
		studentInfo.setBackground((Color.white));

		JPanel panel1 = new JPanel();
		panel1.add(vehicleInfo);
		panel1.add(studentInfo);

		JPanel side = new JPanel();
		side.setLayout(new BoxLayout(side, BoxLayout.X_AXIS));
		side.add(vehicleInfo);
		side.add(studentInfo);
		side.setBackground((Color.white));

		JPanel submitPanel = new JPanel();
		submit = new JButton("Submit");
		submit.setContentAreaFilled(false);
		submit.setOpaque(true);
		submit.setForeground(Color.white);
		submit.setBackground(Color.red.darker());
		submit.setPreferredSize(new Dimension(200, 50));
		submit.setBorder(BorderFactory.createRaisedBevelBorder());
		submit.setAlignmentX(Component.CENTER_ALIGNMENT);
		submitPanel.add(Box.createVerticalStrut(20));
		
		/*
		 * Add listeners. 
		 */
		submit.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				int day = Integer.parseInt(dayCombo.getSelectedItem()
						.toString());
				int month = (monthCombo.getSelectedIndex());
				int year = Integer.parseInt(yearCombo.getSelectedItem()
						.toString());
				Calendar c2 = new GregorianCalendar();
				c2.set(year, month, day);

				Calendar c1 = new GregorianCalendar();

				//supposed to check for invalid entry
				if (c2.getTimeInMillis() >= c1.getTimeInMillis()
						&& !(carModel.getText() == ""
								|| carMake.getText() == ""
								|| plateNo.getText() == ""
								|| carColour.getText() == "" || policy
								.getText() == "")) {
					setUpThree();
					panelCont.add(panelThird, "3");
					cl.show(panelCont, "3"); // go to confirmation page
				} else {
					Toolkit.getDefaultToolkit().beep();
			}
			}
		});
		
		/*
		 * Add and arrange components. 
		 */
		submitPanel.add(submit, SwingConstants.CENTER);// center button
		// arrange them
		JPanel top = new JPanel();
		top.setLayout(new BoxLayout(top, BoxLayout.Y_AXIS));
		top.add(welcome);
		top.add(ensure);
		top.add(side);
		top.add(amountInfo);
		top.add(submit, BorderLayout.CENTER);
		top.add(Box.createVerticalStrut(5));
		top.setBackground((Color.white));
		top.setBorder(BorderFactory.createLineBorder((Color.red).darker(), 4));

		// panelPermit.setLayout(new BorderLayout());
		panelPermit.add(top, "NORTH");

		Keyboard k2 = new Keyboard();

		panelPermit.add(k2.getKeyboard(), "SOUTH");
		panelPermit.add(submitPanel);
		panelPermit.setBackground((Color.white));
		panelPermit.setBorder(BorderFactory.createLineBorder((Color.red).darker()));
	}

	/*
	 * Set relationship with controller. 
	 */
	public void addController(Controller controller) {
		System.out.println("View      : adding controller");
		c = controller; 
	}

	
	/*
	 * Dynamically calculates the amount owed. 
	 */
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == monthCombo || e.getSource() == dayCombo
				|| e.getSource() == yearCombo) {
			int day = Integer.parseInt(dayCombo.getSelectedItem().toString());
			int month = (monthCombo.getSelectedIndex());
			int year = Integer.parseInt(yearCombo.getSelectedItem().toString());
			Calendar c2 = new GregorianCalendar();
			c2.set(year, month, day);

			Calendar c1 = new GregorianCalendar();
			if (c2.getTimeInMillis() >= c1.getTimeInMillis()) {
				permitDays = (int) Math.round(((c2.getTimeInMillis() - c1
						.getTimeInMillis()) / (1000 * 60 * 60 * 24)) * 1000) / 1000 + 1;
				amount = PRICE * permitDays;
				textDate.setText("Your total amount is $" + amount + "0 "
						+ " for a permit of " + permitDays + " day(s)");
			} else {
				textDate.setText("Invalid Permit Period");

			}
		}
	}
}
