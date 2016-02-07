

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.Robot;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class Keyboard extends JPanel {

	public JPanel keyBoard;
	private JButton kbutton;
	private Robot robot = new Robot();//imitates human interaction with a keyboard
	Font buttonFont = new Font("Georgia", Font.PLAIN, 15);

	/*keyboard maker constructor*/
	public Keyboard() throws Exception {
		keyBoard = makeKeyboard();
	}

	/* 
	 * Keys to be loaded into the keyboard
	 */
	
	private static final String[] key = { "", "1", "2", "3", "4", "5", "6",
			"7", "8", "9", "0", "", "tab", "q", "w", "e", "r", "t", "y", "u",
			"i", "o", "p", "tab", "", "caps lock", "a", "s", "d", "f", "g",
			"h", "j", "k", "l", "_", "", "z", "x", "c", "v", "b", "n", "m",
			".", "@", "-", "delete" };

	private JButton keyboard[] = new JButton[key.length]; // array that stores all the keyboard buttons
	private JToggleButton caps;

	/*
	 * Method to create the keyboards layout and functionality
	 */
	public JPanel makeKeyboard() {
		JPanel keyPanel = new JPanel(); // make keyboard pane
		keyPanel.setLayout(new GridBagLayout()); 
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.FIRST_LINE_START;
		
		int counter = 0;// keeps place of where the button is due to null buttons
		/*Adds buttons onto keyboard from array*/
		for (int i = 0; i < key.length; i++) {
			final String keyface = key[i];
			if (keyface == "DELETE") {
				kbutton = new JButton(keyface);
				kbutton.setContentAreaFilled(false);
				kbutton.setOpaque(true);
				kbutton.setBackground(Color.red.darker());
				kbutton.setForeground(Color.white);
				gbc.gridx = i % 12;
				gbc.gridwidth = 1;
				gbc.gridy = (key.length % 12) + i - i % 12;
				kbutton.setFocusable(false);
				keyPanel.add(kbutton, gbc);
				keyboard[counter] = kbutton;
				counter++;
			} else if (keyface == "") {
			} else if (keyface == "caps lock") {
				caps = new JToggleButton(keyface);
				caps.setBorder(BorderFactory.createRaisedBevelBorder());
				caps.setPreferredSize(new Dimension(50, 40));
				caps.setForeground(Color.white);
				caps.setBackground(Color.red.darker());
				caps.setContentAreaFilled(false);
				caps.setOpaque(true);
				gbc.gridx = 0;
				gbc.gridwidth = 2;
				gbc.gridheight = 1;
				gbc.gridy = (key.length % 12) + i - i % 12;
				gbc.fill = GridBagConstraints.HORIZONTAL;
				caps.setFocusable(false);
				keyPanel.add(caps, gbc);
			} else {
				kbutton = new JButton(keyface);
				gbc.gridx = i % 12;
				gbc.gridwidth = 1;
				gbc.gridy = (key.length % 12) + i - i % 12;
				kbutton.setBorder(BorderFactory.createRaisedBevelBorder());
				kbutton.setBackground(Color.red.darker());
				kbutton.setForeground(Color.white);
				kbutton.setContentAreaFilled(false);
				kbutton.setOpaque(true);
				kbutton.setPreferredSize(new Dimension(60, 40));
				kbutton.setFont(buttonFont);
				gbc.insets = new Insets(5, 5, 5, 5);
				kbutton.setFocusable(false);
				
				/*add a listener to every button created*/
				kbutton.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						/*special listeners for special characters*/
						if (keyface == "delete") {
							robot.keyPress(KeyEvent.VK_BACK_SPACE);
							robot.keyRelease(KeyEvent.VK_BACK_SPACE);
						} else if (keyface == "tab") {
							robot.keyPress(KeyEvent.VK_TAB);
							robot.keyRelease(KeyEvent.VK_TAB);
						} else if (keyface == "space") {

						} else if (keyface == "@") {
							robot.keyPress(KeyEvent.VK_SHIFT);
							robot.keyPress(KeyEvent.VK_2);
							robot.keyRelease(KeyEvent.VK_2);
							robot.keyRelease(KeyEvent.VK_SHIFT);
						} else if (keyface == "_") {
							robot.keyPress(KeyEvent.VK_SHIFT);
							robot.keyPress(KeyEvent.VK_MINUS);
							robot.keyRelease(KeyEvent.VK_MINUS);
							robot.keyRelease(KeyEvent.VK_SHIFT);
						} else { 
							//not a special character
							int keycode = KeyEvent
									.getExtendedKeyCodeForChar(keyface
											.charAt(0));
							System.out.println(keycode);
							robot.keyPress(keycode);
							robot.keyRelease(keycode);
						}
					}
				});
				keyPanel.add(kbutton, gbc);
				keyboard[counter] = kbutton;
				counter++;
			}
		}
		/* space bar button creation*/
		JPanel spacePanel = new JPanel();
		JButton space = new JButton("Space");
		space.setPreferredSize(new Dimension(700, 50));
		space.setFocusable(false);
		space.setBorder(BorderFactory.createRaisedBevelBorder());
		spacePanel.add(space);

		spacePanel.setBackground((Color.white));
		space.setContentAreaFilled(false);
		space.setOpaque(true);
		space.setBackground(Color.red.darker());
		space.setForeground(Color.white);

		/*add all keyboard components onto the keyboard panel*/
		JPanel keyboardPanel = new JPanel();
		keyboardPanel.setLayout(new BoxLayout(keyboardPanel, BoxLayout.Y_AXIS));
		keyboardPanel.add(keyPanel);
		keyboardPanel.add(spacePanel);
		keyboardPanel.setBackground(Color.white);
		keyPanel.setBackground(Color.white);

		JPanel contentPane = new JPanel();
		contentPane.setLayout(new BorderLayout());
		contentPane.setBackground(Color.white);

		contentPane.add(keyboardPanel, BorderLayout.SOUTH);
		
		/*listening for uppercasing on the keyboard via caps button press*/
		caps.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (caps.isSelected())
					caps.setBackground(Color.blue.darker());
				else
					caps.setBackground(Color.red.darker());
				/*replace keyboard facse for uppercasing*/
				for (int i = 0; i < keyboard.length; i++) {
					/*defensive against the empty keyboard buttons*/
					if (!(keyboard[i] == null)) {
						//uppercase face when caps is toggled
						if (caps.isSelected()
								&& !(keyboard[i].getText() == "delete" || keyboard[i]
										.getText() == "tab")) {

							keyboard[i].setText(keyboard[i].getText()
									.toUpperCase());
						} else {
							//lower case when caps is not toggled
							keyboard[i].setText(keyboard[i].getText()
									.toLowerCase());

						}
						
						final String text = keyboard[i].getText();
						ActionListener temp[] = keyboard[i]
								.getActionListeners();
						keyboard[i].removeActionListener(temp[0]);//remove the old listener
						keyboard[i].addActionListener(new ActionListener() {
							
							/*add the actionlisteners for whether it is upper/lower case*/
							@Override
							public void actionPerformed(ActionEvent e) {
								/*special cases for special characters and their functionality*/
								if (text == "delete") {
									robot.keyPress(KeyEvent.VK_BACK_SPACE);
									robot.keyRelease(KeyEvent.VK_BACK_SPACE);
								} else if (text == "tab") {
									robot.keyPress(KeyEvent.VK_TAB);
									robot.keyRelease(KeyEvent.VK_TAB);
								} else if (text == "space") {

								} else if (text == ".") {
									robot.keyPress(KeyEvent.VK_PERIOD);
									robot.keyRelease(KeyEvent.VK_PERIOD);

								} else if (text == "@") {
									robot.keyPress(KeyEvent.VK_SHIFT);
									robot.keyPress(KeyEvent.VK_2);
									robot.keyRelease(KeyEvent.VK_2);
									robot.keyRelease(KeyEvent.VK_SHIFT);
								} else if (text == "_") {
									robot.keyPress(KeyEvent.VK_SHIFT);
									robot.keyPress(KeyEvent.VK_MINUS);
									robot.keyRelease(KeyEvent.VK_MINUS);
									robot.keyRelease(KeyEvent.VK_SHIFT);
								} else if (text == "-") {

									robot.keyPress(KeyEvent.VK_MINUS);
									robot.keyRelease(KeyEvent.VK_MINUS);
								} else {
									/*alphabetic character*/
									int keycode = KeyEvent
											.getExtendedKeyCodeForChar(text
													.charAt(0));
									/* caps to not apply to numeric buttons*/
									if (caps.isSelected()&&!(text.matches("[0-9]*"))) {
										caps.setContentAreaFilled(false);
										caps.setOpaque(true);
										caps.setBorder(BorderFactory
												.createLoweredBevelBorder());

										robot.keyPress(KeyEvent.VK_SHIFT);
										robot.keyPress(keycode);
										robot.keyRelease(keycode);
										robot.keyRelease(KeyEvent.VK_SHIFT);

									} else {
										robot.keyPress(keycode);
										robot.keyRelease(keycode);
									}
								}
							}
						});
					}
				}
			}
		});

		/* action for spacebar*/
		space.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				robot.keyPress(KeyEvent.VK_SPACE);
				robot.keyRelease(KeyEvent.VK_SPACE);

			}

		});
		return contentPane;
	}

	//get the newly created keyboard*/
	public JPanel getKeyboard() {
		return keyBoard;
	}
}
