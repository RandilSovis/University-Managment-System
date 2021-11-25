package uniDB;

import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class HomeGui extends JFrame {

	private JMenuBar menuBar;
	private JMenu menuStudent;
	private JMenu menuCourse;
	private JMenuItem[] menuItemsStudent;
	private JMenuItem[] menuItemsCourse;
	private JLabel mainLable;
	private JLabel subLable;
	private JPanel homeP;
	private JPanel current;

	public HomeGui() {

		//Creating the layout
		setLayout(new GridLayout(1, 1));
		GridBagConstraints gbc = new GridBagConstraints();

		//Menu bar
		menuBar = new JMenuBar();

		//Creating the menus
		menuStudent = new JMenu("Student");
		menuCourse = new JMenu("Course");

		//adding the menus to the menubar
		setJMenuBar(menuBar);
		menuBar.add(menuStudent);
		menuBar.add(menuCourse);

		//Creating the menueItems
		menuItemsStudent = new JMenuItem[4];
		menuItemsCourse = new JMenuItem[2];
		String[] stMsg = { "Register", "Marks", "Show", "Remove" };
		String[] courseMsg = { "Students", "Modules" };

		//Setting up the menu items for the student
		for (int i = 0; i < menuItemsStudent.length; i++) {
			menuItemsStudent[i] = new JMenuItem(stMsg[i]);
			menuStudent.add(menuItemsStudent[i]);
		}
		//Setting up the menu items for the course
		for (int i = 0; i < menuItemsCourse.length; i++) {
			menuItemsCourse[i] = new JMenuItem(courseMsg[i]);
			menuCourse.add(menuItemsCourse[i]);
		}
		
		//Setting the labels
		homeP = new JPanel();
		homeP.setLayout(new GridBagLayout());
		mainLable = new JLabel("University Management System");
		subLable = new JLabel("University Of Colombo");

		//Setting the positions for the components
		gbc.fill = GridBagConstraints.CENTER;
		gbc.gridx = 0;
		gbc.gridy = 0;
		mainLable.setFont(new Font("", Font.BOLD, 30));
		homeP.add(mainLable, gbc);
		gbc.fill = GridBagConstraints.CENTER;

		gbc.gridx = 0;
		gbc.gridy = 1;
		subLable.setFont(new Font("", Font.PLAIN, 20));
		homeP.add(subLable, gbc);
		current = homeP;
		add(current);
		
		//Adding the action events to the menu items
		menuItemsStudent[0].addActionListener(new StuRegEvent());

		menuItemsStudent[1].addActionListener(new StuMarksEvent());

		menuItemsStudent[2].addActionListener(new StuShowEvent());

		menuItemsStudent[3].addActionListener(new StuRemoveEvent());

		menuItemsCourse[0].addActionListener(new CourseStuEvent());
		menuItemsCourse[1].addActionListener(new CourseModEvent());

		
		//setting the sizes
		setSize(700, 700);
		setTitle("Home");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

	}

	//the event to change to the student registration page
	public class StuRegEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			//remove the current panel
			remove(current);
			//Make the register panel the current one
			current = new RegisterGui();
			//add it to the JFrame
			add(current);
			setTitle("Registeration");
			setVisible(true);

		}
	}

	//The event to change to the student show page
	public class StuShowEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(current);
			current = new ShowGui();
			add(current);
			setTitle("Show Details");
			setVisible(true);

		}
	}

	//The event to change to the marks entering page
	public class StuMarksEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(current);
			current = new MarksGui();
			add(current);
			setTitle("Mark Entering");
			setVisible(true);

		}
	}

	//The event to change to the student removal page
	public class StuRemoveEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(current);
			current = new RemoveGui();
			add(current);
			setTitle("Delete Student");
			setVisible(true);

		}
	}

	//the event to change to the course students page
	public class CourseStuEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(current);
			current = new CourseStuGui();
			add(current);
			setTitle("Course Students");
			setVisible(true);

		}
	}

	//the event to change to the course module page
	public class CourseModEvent implements ActionListener {
		public void actionPerformed(ActionEvent e) {
			remove(current);
			current = new CourseModGui();
			add(current);
			setTitle("Course Modules");
			setVisible(true);

		}
	}

}
