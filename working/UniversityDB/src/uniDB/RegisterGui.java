package uniDB;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.plaf.DimensionUIResource;

public class RegisterGui extends JPanel {

	private javax.swing.JComboBox<String> courseCombo;
	private javax.swing.JLabel courseL;
	private javax.swing.JLabel indexL;
	private javax.swing.JTextField indexT;
	private javax.swing.JLabel jLabel2;
	private javax.swing.JLabel nameL;
	private javax.swing.JTextField nameT;
	private javax.swing.JLabel regL;
	private javax.swing.JTextField regT;
	private javax.swing.JButton registerButton;

	public RegisterGui() {

		initComponents();
	}

	private void initComponents() {

		// Creating the variables
		nameL = new JLabel("Name");
		indexL = new JLabel("Index Number");
		regL = new JLabel("Registration Number");
		courseL = new JLabel("Course");
		courseCombo = new JComboBox<>();
		nameT = new JTextField();
		indexT = new JTextField();
		regT = new JTextField();
		jLabel2 = new JLabel();
		registerButton = new JButton();

		setLayout(null);

		// Setting the comboBox information
		for (int i = 0; i < UOC.getUni().getNoOfCourses(); i++) {
			courseCombo.addItem(UOC.getUni().getCourses()[i].getCourseName());
		}

		// Changing the font for Label2
		jLabel2.setFont(new Font("Tahoma", Font.BOLD, 14));

		jLabel2.setText("Register Student");

		jLabel2.setBounds(275, 50, 200, 100);

		// Adding the register button actionListner
		registerButton.setText("Register");
		registerButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				register();
			}
		});

		// Setting the labels and bounds
		nameL.setBounds(75, 150, 200, 20);
		nameT.setBounds(250, 150, 200, 20);

		indexL.setBounds(75, 175, 200, 20);
		indexT.setBounds(250, 175, 200, 20);

		regL.setBounds(75, 200, 200, 20);
		regT.setBounds(250, 200, 200, 20);

		courseL.setBounds(75, 250, 200, 20);
		courseCombo.setBounds(250, 250, 200, 20);

		registerButton.setBounds(250, 300, 100, 20);

		// Adding the components to JFrame
		add(jLabel2);

		add(nameL);

		add(nameT);

		add(indexL);

		add(indexT);

		add(regL);

		add(regT);

		add(courseL);

		add(courseCombo);

		add(registerButton);

		setSize(700, 700);
	}

	// Method to register the Student
	public void register() {
		int index = 0;
		// Checking if the entered information is valid
		boolean valid = true;
		//When changing to Stirng change these
		try {
			index = Integer.parseInt(indexT.getText());
		} catch (NumberFormatException e) {
			indexT.setText("Enter a Integer");
			valid = false;
		}
		int reg = 0;
		try {
			reg = Integer.parseInt(regT.getText());
		} catch (NumberFormatException e) {
			regT.setText("Enter a Integer");
			valid = false;
		}

		// Checking if the name textField is empty
		if (nameT.getText().equals("")) {
			nameT.setText("Enter a Name");
			valid = false;

		}

		// If information is valid continue
		if (valid) {

			Student s = new Student(index, reg, nameT.getText());

			// Getting the courseId
			int result = UOC.getUni().findCourse(courseCombo.getSelectedItem().toString());

			// Setting the student course info
			s.setStudentCourse(UOC.getUni().getCourses()[result]);
			s.setNoOfModules(UOC.getUni().getCourses()[result].getNoOfModules());

			// Creating a Connection to the DB
			Connection conn = UOC.connect();
			try {
				// Check if Student is already registered

				String qS = "SELECT COUNT(*) FROM Student WHERE indexNo=" + s.getIndexNo();

				Statement stS = conn.createStatement();

				ResultSet rS = stS.executeQuery(qS);

				rS.next();
				// If count if greater than 0 then there is a row entered
				if (rS.getInt(1) > 0) {
					JOptionPane.showMessageDialog(null, "Student Already Enrolled");
				}

				// If student is a new one add them to Student Table
				else {

					// Adding Student to the Student table
					String qu = "INSERT INTO Student (indexNo, regNo, studentName, courseId, year) VALUES(?,?,?,?,?)";

					PreparedStatement ps = conn.prepareStatement(qu);
					ps.setInt(1, s.getIndexNo());
					ps.setInt(2, s.getRegNo());
					ps.setString(3, s.getStudentName());
					ps.setInt(4, s.getCourseId());
					ps.setInt(5, 1);

					ps.execute();
					ps.close();

					// Adding the studentModules to DB

					// First get the moduleCodes for the modules in the Course
					String qmods = "SELECT moduleCode FROM Module WHERE courseId=" + s.getCourseId();

					Statement stmods = conn.createStatement();

					ResultSet r = stmods.executeQuery(qmods);

					// Then for all of them create a new StudentModule row in DB
					while (r.next()) {

						// Creating a new StudentModule
						String quSM = "INSERT INTO StudentModule (indexNo, moduleCode) VALUES(?,?)";

						PreparedStatement psSM = conn.prepareStatement(quSM);
						psSM.setInt(1, s.getIndexNo());
						psSM.setInt(2, r.getInt("moduleCode"));

						psSM.execute();
						psSM.close();

					}

					// Close connectionS
					stS.close();
					stmods.close();
					conn.close();
					jLabel2.setText("Registered");
					JOptionPane.showMessageDialog(null, "Registration Successful");
					registerButton.setEnabled(false);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

		}
		// If data is invalid
		else {
			JOptionPane.showMessageDialog(null, "Enter Valid Data");
			jLabel2.setText("Invalid");

		}
	}

}
