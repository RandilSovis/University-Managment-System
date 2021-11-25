package uniDB;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class MarksGui extends JPanel {

	// NOTE REPLACE THE MARKS ENTERED WITH A SUITABLE REPLACEMENT MAYBE CHECK IF
	// THE MODULE MARKS WERE ENTERED MUST ALSO HAVE TO CHANGE THE PASS
	// Add year to student
	// if all pass year++
	// entered set tries to module and if tries >0 then entered
	// if year = year and not entered
	// if year =year and entered
	// if year>year can't go without passing
	// if year<year reenter

	// Could have gotten just the ones I need through sql instead of all

	// Second method
	// Get the student modules in the sections itself instead of all at the top
	// Do it if have time

	private JLabel mainL;
	private JLabel subL;
	private JLabel[] labels;
	private JTextField[] fields;
	private JTextField index;
	private JButton searchB;
	private JPanel p1;
	private JPanel p2;
	private JButton submitB;
	private JComboBox<String> yearCombo;
	private int indexNo;
	private JTable table;
	private DefaultTableModel model;
	private Student stu;
	boolean reenter;

	public MarksGui() {

		// Creating the variables
		
		stu = new Student();

		//Setting the layout
		setLayout(new GridLayout(2, 1));
		mainL = new JLabel();
		searchB = new JButton("Select");
		index = new JTextField();
		mainL = new JLabel("Enter Index");
		yearCombo = new JComboBox<String>();

		// Making the first two columns of the JTable non editable
		model = new DefaultTableModel() {

			public boolean isCellEditable(int row, int col) {
				if (col == 0 || col == 1) {
					return false;
				} else {
					return true;
				}
			}

		};

		table = new JTable(model);

		// Setting the columns
		model.setColumnIdentifiers(new Object[] { "Module Code", "Module Name", "Mark" });

		//Creating the combo box for the year
		yearCombo.addItem("Year 1");
		yearCombo.addItem("Year 2");
		yearCombo.addItem("Year 3");
		yearCombo.addItem("Year 4");

		// The positions of the components
		yearCombo.setBounds(280, 75, 100, 25);
		mainL.setBounds(200, 50, 100, 25);
		index.setBounds(280, 50, 100, 25);
		searchB.setBounds(380, 75, 100, 25);

		p1 = new JPanel();
		p2 = new JPanel();

		// Adding the components
		add(p1);
		// add(p2);
		p1.setLayout(null);

		p1.add(mainL);
		p1.add(index);
		p1.add(searchB);

		p1.add(yearCombo);

		// The Student select button event
		searchB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				selectStudent();

			}
		});

		// setSize(700,700);
		setSize(700, 700);

	}

	// Method to select the student and show the modules the student takes for the particular year and allow to enter marks
	public void selectStudent() {
		int ind = 0;
		reenter=false;
		// Checking if the index is int
		try {
			ind = Integer.parseInt(index.getText());
			this.indexNo = ind;
		} catch (NumberFormatException e) {
			index.setText("Enter a Number");
			p2.setVisible(false);
			remove(p2);
			revalidate();
		}

		// Getting the year
		int year = yearCombo.getSelectedIndex() + 1;

		boolean valid = false;
		// Checking if a student exists in the DB
		try {

			//The query to get the Student information
			
			String qs = "SELECT* FROM Student WHERE indexNo=" + indexNo;

			//Creating a connection to the DB
			Connection con1 = UOC.connect();
			//Making the the query a statement 
			Statement s1 = con1.createStatement();
			ResultSet rs = s1.executeQuery(qs);

			// If there is a Student then yes
			if (rs.next()) {
				valid = true;
				Student s = new Student(rs.getInt("indexNo"), rs.getInt("regNo"), rs.getString("studentName"),
						rs.getInt("year"), rs.getInt("marksEntered"),rs.getInt("credits"),rs.getDouble("gpa"));
				this.stu = s;
				// System.out.println(s.getEntered());

			}
			// Close connection
			s1.close();
			con1.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		
		// If There is a valid student
		if (valid) {
			
			remove(p2);
			revalidate();
			repaint();
			
			p2 = new JPanel();
			p2.setLayout(null);
			int column = 2;

			//set the JTable to the scrollpane
			JScrollPane jsp = new JScrollPane(table);
			model.setRowCount(0);
			boolean show = true;
			

			// If normal marks entered till and all passed
			// If marks not entered for the current year and all the previous
			// ones are entered and no resits
			String qu = "";
			String q1 = "";
			
			//ADD THE PREPARED STATEMENT HERE AS WELL NOT CONCAT THING
			
			if (stu.getYear() == year && !stu.isMarksEnteredTill(year)) {
				System.out.println("Normal");
				subL = new JLabel("Enter Marks");
				// Module.moduleCode,Module.moduleName,Module.credits,Module.year
				//Get the module information and the module marks from the studentModules for a join is used
				
				qu = "SELECT *,StudentModule.moduleMarks   FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + indexNo + ";";
				
				//Getting the count of how many modules there are for the student for that year
				
				q1 = "SELECT COUNT(*) FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + indexNo + ";";
				/*
				 * // Set the module number for the year int numM =
				 * stu.yearModuleNumber(year, false); // Find the modules the
				 * student must take int[] moduleIndexes =
				 * stu.findYearModules(year, false);
				 */

			}

			// If has resits for that year
			// If the student has entered the marks for the year and has resists
			// that year
			else if (stu.getYear() == year && stu.isMarksEnteredTill(year)) {
				System.out.println("Resits");

				//Query to get the resits similar to the previous one but pass is 0 because mysql saves it as 0,1
				qu = "SELECT *,StudentModule.moduleMarks  FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + indexNo + " AND StudentModule.pass=" + 0 + ";";
				q1 = "SELECT COUNT(*) FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + indexNo + " AND StudentModule.pass=" + 0 + ";";

				subL = new JLabel("Enter Resit Marks");
				// Set the resit module number
			}
			// If marks till the current year is not entered
			/*
			 * else if (!stu.isMarksEnteredTill(year - 1)) {
			 * p2.setVisible(false); remove(p2); revalidate(); String m =
			 * "Please Enter"; for (int k = 1; k < year; k++) { if
			 * (!stu.isMarksEntered(k)) { m += " Year " + (k); } } m +=
			 * " Marks"; JOptionPane.showMessageDialog(null, m);
			 * 
			 * }
			 */
			// going to a year ahead without passing
			else if (stu.getYear() < year) {
				//the table is not shown 
				show = false;
				p2.setVisible(false);
				remove(p2);
				revalidate();
				JOptionPane.showMessageDialog(null, "Please Pass previous modules");
			}

			else {
				// reenter marks
				reenter=true;
				subL = new JLabel("ReEnter Marks");
				//Same as when normal
				qu = "SELECT *,StudentModule.moduleMarks  FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + indexNo + ";";
				q1 = "SELECT COUNT(*) FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + indexNo + ";";

			}
			//If there are modules to show
			if (show) {
				try {

					//create the connection to the DB
					Connection conn = UOC.connect();

					// Getting the Module details from the StudentModules
					Statement st = conn.createStatement();
					Statement s1 = conn.createStatement();

					//Using the queries from the above selections
					ResultSet r1 = s1.executeQuery(q1);
					r1.next();
					// Getting the number of modules and setting the array Could
					// have used an ArrayList
					//System.out.println(r1.getInt(1));
					StudentModule[] sms = new StudentModule[r1.getInt(1)];

					ResultSet r = st.executeQuery(qu);
					int i = 0;

					// Then for each of the StudentModules
					while (r.next()) {
						/*
						 * int id = r.getInt("moduleCode"); // String name =
						 * r.getString("CourseName");
						 * 
						 * // Getting the number of Modules for a particular //
						 * StudentModule // from Module Table String qu2 =
						 * "SELECT * FROM Module WHERE moduleCode=" + id;
						 * Statement st2 = conn.createStatement(); ResultSet r2
						 * = st2.executeQuery(qu2); r2.next();
						 */
						// System.out.println(r.getInt("moduleCode")+" "+
						// r.getString("moduleName")+" "+r.getInt("credits")+"
						// "+ r.getInt("year")+" "+r.getInt("moduleMarks"));
						
						//Create a new StudentModule
						StudentModule sm = new StudentModule(r.getInt("moduleCode"), r.getString("moduleName"),
								r.getInt("credits"), r.getInt("year"), r.getInt("moduleMarks"));

						//add it to the studentModules array
						sms[i++] = sm;
						// st2.close();

					}

					// Set the studentModules for the student
					stu.setStudentModules(sms);
					// Closing the statements and connections

					s1.close();
					st.close();
					conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				
				/*fields = new JTextField[stu.getNoOfModules()];
				labels = new JLabel[stu.getNoOfModules()];*/
				// Remove it or multiple is possible so not good

				// int numM = UOC.uni.getStudents()[indexNo].getNoOfModules();

				//setting the component positions
				subL.setBounds(200, 75, 250, 25);
				p2.add(subL);
				
				// Adding the submit button and event
				submitB = new JButton("Submit");
				submitB.setBounds(200, 110 + stu.getNoOfModules() * 25, 100, 50);
				submitB.addActionListener(new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent e) {
						enterMarks();
					}
				});
				p2.add(submitB);
				add(p2);

				//Adding the module information to the Jtable
				for (int i = 0; i < stu.getNoOfModules(); i++) {

					model.addRow(new Object[] { stu.getStudentModules()[i].getModuleCode(),
							stu.getStudentModules()[i].getModuleName() });
				}
				// table.setEnabled(false);
				// JScrollPane jsp = new JScrollPane(table);
				jsp.setBounds(50, 100, 500, 100 + stu.getNoOfModules() * 10);

				p2.add(jsp);
				p2.setVisible(true);
				revalidate();
			} else {

			}

			repaint();

			// Setting up the labels and textfields

			// Showing the fields for the modules for that year
		}
		// If no student
		else {
			// index.setText("No Student Found");
			p2.setVisible(false);
			remove(p2);
			revalidate();
			JOptionPane.showMessageDialog(null, "No Student Found");
		}
	}

	
	public boolean validateMarks() {
		// Need this otherwise the last value may not be taken from the JTable
		if (table.getCellEditor() != null) {
			table.getCellEditor().stopCellEditing();
		}

		boolean isValid = true;
		// if (indexNo != -1) {
		int year = yearCombo.getSelectedIndex() + 1;

		//Getting the module number can just use noOfModules I think
		
		//CAN JUST ADD NOOFMODULES FOR ALL HERE NO NEED FOR IF 
		
		int numM = stu.yearModuleNumber(year, false);

		// If has resits
		if (stu.getYear() == year && stu.isMarksEnteredTill(year)) {
			numM = stu.resitModuleNumber(year, false);

		}
		// No resits
		else {
			numM = stu.yearModuleNumber(year, false);
		}

		// Get the marks(2nd Column from table and check if valid
		for (int i = 0; i < numM; i++) {
			// If it is a number
			try {
				// int mark = Integer.parseInt(fields[i].getText());

				int mark = Integer.valueOf((String) table.getModel().getValueAt(i, 2));
				// If atleast one not right then not valid
				if (mark < 0 || mark > 100) {
					isValid = false;
					table.getModel().setValueAt("Number between 0-100", i, 2);
				}
			}
			// If not a number
			catch (NumberFormatException e) {
				isValid = false;
				table.getModel().setValueAt("Number between 0-100", i, 2);
			}

		}
		// }
		return isValid;
	}

	// Method that enters the data to the DB
	public void enterMarks() {
		// First validate marks
		boolean b = validateMarks();
		// If valid then
		if (b) {
			//get the year
			int year = yearCombo.getSelectedIndex() + 1;
			
			// Setting the module marks for the year
			try {
				//create connection to the Db
				Connection conn = UOC.connect();

				//Getting the JTables row information one by one
				for (int i = 0; i < stu.getNoOfModules(); i++) {
					stu.getStudentModules()[i]
							.setModuleMarks(Integer.valueOf((String) table.getModel().getValueAt(i, 2)));
					
					//Query to update one module
					String q = "UPDATE StudentModule SET moduleMarks = ?, grade = ?, gpv = ?, pass = ? WHERE indexNo = ? AND moduleCode = ?";
					
					//Preparing the query 
					PreparedStatement preparedStmt = conn.prepareStatement(q);
					preparedStmt.setInt(1, stu.getStudentModules()[i].getModuleMarks());
					preparedStmt.setString(2, String.valueOf(stu.getStudentModules()[i].getGrade()));
					preparedStmt.setDouble(3, stu.getStudentModules()[i].getGpv());
			
					//If true then 1 false then 0
					preparedStmt.setInt(4, stu.getStudentModules()[i].isPass() ? 1 : 0);
					preparedStmt.setInt(5, stu.getIndexNo());
					preparedStmt.setInt(6, stu.getStudentModules()[i].getModuleCode());
					//executing the query
					preparedStmt.executeUpdate();
					//closing the statement
					preparedStmt.close();
					//conn.close();

				}
				//If there are no resits for the current year and it is not a reenter then student is moved to the next year
				if (stu.resitModuleNumber(year, false) == 0 &&!reenter) {
					stu.setYear(stu.getYear() + 1);
				}
				//If this is the first time the marks are entered for the year ie not a resit or ree nter
				if (!stu.isMarksEnteredTill(year)) {
					stu.setEntered(stu.getEntered() + 1);
				}
				
				//This is used to calculate the gpa need all the student module information of the student to properly calculate it without errors
				//No need for this if re enter is removed
				Connection con = UOC.connect();
				// Getting the Module details from the StudentModules

				String qu = "SELECT *,StudentModule.moduleMarks   FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year <="
						+ year + " AND StudentModule.indexNo=" + indexNo + ";";
				String q1 = "SELECT COUNT(*) FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year <="
						+ year + " AND StudentModule.indexNo=" + indexNo + ";";
				Statement st = con.createStatement();
				Statement s1 = con.createStatement();

				ResultSet r1 = s1.executeQuery(q1);
				r1.next();
				// Getting the number of modules and setting the array Could
				// have used an ArrayList
				//System.out.println(r1.getInt(1));
				StudentModule[] sms = new StudentModule[r1.getInt(1)];

				ResultSet r = st.executeQuery(qu);
				int i = 0;

				// Then for each of the StudentModules
				while (r.next()) {
					StudentModule sm = new StudentModule(r.getInt("moduleCode"), r.getString("moduleName"),
							r.getInt("credits"), r.getInt("year"), r.getInt("moduleMarks"));

					sms[i++] = sm;
					

				}

				// Set the studentModules
				stu.setStudentModules(sms);
				// Closing the statements and connections

				s1.close();
				st.close();
				con.close();
			

				//Updating the students information in the DB
				String q = "UPDATE Student SET year = ?, marksEntered = ?, gpa = ? , credits = ? WHERE indexNo = ?";
				PreparedStatement preparedS = conn.prepareStatement(q);
				preparedS.setInt(1, stu.getYear());
				preparedS.setInt(2, stu.getEntered());
				//Calculating the gpa and credits
				preparedS.setDouble(3, stu.calculateGpa());
				preparedS.setDouble(4, stu.calculateCredits());
				preparedS.setInt(5, stu.getIndexNo());
				preparedS.executeUpdate();
				
				

				subL.setText("Marks Entered");
				JOptionPane.showMessageDialog(null, "Marks Entered Successfully");
				submitB.setEnabled(false);
				stu.setMarkEnteredYear(year);
				
				preparedS.close();
				conn.close();
			} catch (SQLException e) {
				System.out.println(e.getMessage());
			}

		}
	}

}
