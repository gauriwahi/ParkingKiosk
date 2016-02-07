# ParkingKiosk
Parking Kiosk application Project for EECS3461



---------------------
Special Requirements: (optional)
---------------------
It is assuming a database connections exists, and is loaded onto the server to recognize if the students are in the system.



-------
Design:
-------

(Describe the motivation behind your software design. Why did you use
the JFC/Swing components that you did, and why did you organise them
that way? Briefly justify your design.)

The first panel is the welcome panel, where we used a JButton field, along with a background picture allowing to give the user the look and feel of a touch screen interactive system.

Each panel is designed for the user to go through the form filling of the Permit smoothly, and in an organized manner. We used TitledBorders, and EtchedBorders to signify what information is under what category. Such as the policy number, and Insurance Company Name is under Insurance Information. We also use JPanel to help us achieve an organized look.
The user-friendly keyboard is designed for a touchscreen system, with rounded keyboard buttons to help the user have an easier way to type, with flow and ease. This is done by correct spacing, along with JPanel to give a qwerty keyboard look, along with JButton, and Robot, which imitates the keyboard presses. The red and white buttons are designed to go with the YorkU logo.
It takes control over the keyboard, which allows the user to do any operation related to the keyboard. SwingConstants were used to align the Labels used beside the text fields, along with the alignment of the combo boxes.
The client class is designed to take information from the database, so the information can be validated through ActionListeners. ActionListeners were also used to help submit the information into the next panel and onto the final receipt. 

We used CardLayout to setup a way to swap between the JPanels that we originally designed through a flow diagram. Each panel is identified through a name assignment and the method show is used to display each panel in a storyboard like model.
 
To help set the background of the buttons different from the panel, we used Swing components for the JButton, such as setOpaque(), and setContentAreaFilled().
Swing components were also used to help set the margins of the buttons, the text fields, and panels as well. 
GridBagLayout was used for aligning the buttons, and panels. GridLayout was also used for this purpose.


--------------------
Additional Features: (optional)
--------------------
- Using a password field to make sure their PIN is secure and private
- If a user adds an incorrect variables or incorrect length in Student Number field, or Student PIN field, then it asks the user to refocus, along with highlighting the box red.
- The main intro page with a logo for the Parking Space
- Switched to Serif font to increase readability
- High contrast white on black so it’s readable both indoors and outdoors

---------------
Communications:
---------------

October 1st, 2015 at 10:29 am, Robin made a private Facebook group named
“3461 WE GOT THIS”. Our initial planning stage began this day. Where we discussed what is required, and to split the tasks fairly. 

On October 1st, 2015 at 1:46 pm we posted a flow layout about our idea of how our panels will look like and how they will be connected. 

October 4th, 2015 at 9:32 PM there was an update to our main page where we combined a lot of the panels together. (Again posted on the Facebook group.

Another means of communication we used is a Whatsapp group called “EECS 3461”. We used this to communicate with one another on what task an individual was taking over, also another means to contact people and ask for there whereabouts to meet up. 

Our meetings usually took place before our EECS 3461 class, where we showed the update and discussed what else we should do to make the program more user friendly. 

On October 13th, we discussed the order of the panels once again, and made the first page while choosing the picture we should use. 

On October 15th, 2015 we began to post the files and began to combine them together. We posted the zip files on our Facebook group.

October 16th, 11:30 am we finalized our components, and beautified the layout. 

Screenshots of work has been placed in the folder “Snapshots”.


-----------------
Responsibilities:
-----------------

Gary:
Creating the combo-boxes for Insurance, and Date, while also working on the on-screen keyboard, as well as the parking GUI, and intro page.

Robin:
Validating the information, Switching between panels, and making an 
on screen keyboard and making sure of its functionality. Also linking the database.

Gauri:
Making the main login GUI where the student would enter their information, along with the vehicle information GUI, and documentation.





