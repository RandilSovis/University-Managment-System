package uniDB;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CourseModGui extends JPanel {
	private JComboBox<String> courseCombo;
	private JButton selectB;
	private JLabel[] id;
	private JLabel[] name;
	private JLabel[] gpv;
	private JPanel p1;
	private JPanel p2;
	private JTable table;

	private JComboBox<String> yearCombo;

	public CourseModGui() {
		//Setting the layout
		setLayout(new GridLayout(2, 1));
		courseCombo = new JComboBox<String>();
		selectB = new JButton("Select");

		//Adding the combo box information for course
		for (int i = 0; i < UOC.getUni().getNoOfCourses(); i++) {
			courseCombo.addItem(UOC.getUni().getCourses()[i].getCourseName());
		}
		/*
		 * courseCombo.addItem("Physical Science");
		 * courseCombo.addItem("Bio Science");
		 * courseCombo.addItem("Industrial Statistics");
		 */

		//Addint the combo box infomation for year
		yearCombo = new JComboBox<String>();

		yearCombo.addItem("Year 1");
		yearCombo.addItem("Year 2");
		yearCombo.addItem("Year 3");
		yearCombo.addItem("Year 4");

		yearCombo.setBounds(280, 75, 100, 25);

		p1 = new JPanel();
		p2 = new JPanel();
		
		//Setting the positions of the components
		courseCombo.setBounds(200, 50, 100, 25);
		yearCombo.setBounds(300, 50, 100, 25);
		selectB.setBounds(200, 75, 200, 25);

		//Adding them to the panel
		add(p1);
		p1.add(courseCombo);
		p1.add(yearCombo);
		p1.add(selectB);

		
		//Select button event
		selectB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showCourseModules();
			}
		});

	}

	//The method for the select button event that show the corresponding modules for the course
	public void showCourseModules() {
		//To check if the input is valid
		boolean valid = false;
		Module[] modules=null;
		//int indexNo = UOC.getUni().findCourse(courseCombo.getSelectedItem().toString());
		
		try {

			//THIS THING DIDN'T WORK ONCE FOR SOME REASON 
			
			/*String s =courseCombo.getSelectedItem().toString();
			int year = yearCombo.getSelectedIndex()+1;*/
			
			//Creating a connection to the DB
			Connection conn = UOC.connect();
			
			//Query to get the information about the modules for the corresponding course for that year
			 String query = "SELECT * FROM Module WHERE year = ? AND courseId = (SELECT courseId from Course where courseName = ?)";
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      //String s = "Physical Science";
		      //Setting the values
		      preparedStmt.setInt(1, (yearCombo.getSelectedIndex()+1));
		      preparedStmt.setString(2, courseCombo.getSelectedItem().toString());
		     
		      //The results gained from the query
		      ResultSet rs = preparedStmt.executeQuery();
		      
		      //The query to get the count of how many modules there are for the given situation
		      //need it to create the array
		      String query1 = "SELECT COUNT(*) FROM Module WHERE year = ? AND courseId = (SELECT courseId from Course where courseName = ?)";
		      PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
		      //String s = "Physical Science";
		      preparedStmt1.setInt(1, (yearCombo.getSelectedIndex()+1));
		      preparedStmt1.setString(2, courseCombo.getSelectedItem().toString());
		      
		      ResultSet rs1 = preparedStmt1.executeQuery();
			
		      //Creating the array to hold the necessary modules
		      rs1.next();
		      modules = new Module[rs1.getInt(1)];
		      
		      int i =0;
		      //Adding the modules to the array
		      while(rs.next()){
		    	  valid =true;
		    	  Module m = new Module(rs.getInt("moduleCode"),rs.getString("moduleName"),rs.getInt("credits"));
		    	  modules[i++] = m;
		      }
			/*Connection con1 = UOC.connect();

			PreparedStatement p = con1.prepareStatement(q);
	p.setInt(1, 1);
			ResultSet r = p.executeQuery(q);
			
			r.next();
			int id = r.getInt("courseId");*/
		      /*while(rs.next()){
		    	  
		      }*/
		     /* rs.next();
			System.out.println(rs.getString("moduleName"));*/
			
			

			/*PreparedStatement p = con1.prepareStatement(q);
			//p.setInt(1, 1);
			ResultSet r = p.executeQuery(qs);
			
			r.next();
			int id = r.getInt("courseId");*/
			//System.out.println(id);
		
			// Close connection
			/*pc.close();
			pm.close();
			con1.close();*/

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

		//If a valid response is given from the DB 
		if (valid) {
			remove(p2);
			revalidate();
			/*int year = yearCombo.getSelectedIndex() + 1;
			;
			int numM = UOC.getUni().getCourses()[indexNo].yearModuleNumber(year);

			int[] moduleIndexes = UOC.getUni().getCourses()[indexNo].findYearModules(year);
*/
		//Create the JTable to show the infomation
			table = new JTable();
			DefaultTableModel model = (DefaultTableModel) table.getModel();

			//Set the column headers
			model.setColumnIdentifiers(new Object[] { "Module Code", "Module Name", "Credits" });

			//Create the other components
			p2 = new JPanel();
			p2.setLayout(null);
			/*name = new JLabel[modules.length];
			id = new JLabel[modules.length];
			gpv = new JLabel[modules.length];*/

			/*
			 * for(int i=0;i<numM;i++){ name[i] = new
			 * JLabel(UOC.uni.getCourses()[indexNo].getCourseModules()[i].
			 * getModuleName()); id[i] = new JLabel(
			 * Integer.toString(UOC.uni.getCourses()[indexNo].getCourseModules()
			 * [i].getModuleCode())); gpv[i] = new JLabel(
			 * Integer.toString(UOC.uni.getCourses()[indexNo].getCourseModules()
			 * [i].getCredits()));
			 * 
			 * id[i].setBounds(50, 100 + i * 25, 150, 25);
			 * name[i].setBounds(300, 100 + i * 25, 250, 25);
			 * gpv[i].setBounds(550, 100 + i * 25, 50, 25);
			 * 
			 * p2.add(name[i]); p2.add(id[i]); p2.add(gpv[i]);
			 * 
			 * }
			 */
			// int i=0;
			
			//Adding the module information obtained to the rows of the Jtable 
			for (int i=0;i<modules.length;i++) {/*
											 * name[i] = new
											 * JLabel(UOC.uni.getCourses()[
											 * indexNo].getCourseModules()[a].
											 * getModuleName()); id[i] = new
											 * JLabel( Integer.toString(UOC.uni.
											 * getCourses()[indexNo].
											 * getCourseModules()[a].
											 * getModuleCode())); gpv[i] = new
											 * JLabel( Integer.toString(UOC.uni.
											 * getCourses()[indexNo].
											 * getCourseModules()[a].getCredits(
											 * )));
											 * 
											 * id[i].setBounds(50, 100 + i * 25,
											 * 150, 25); name[i].setBounds(300,
											 * 100 + i * 25, 250, 25);
											 * gpv[i].setBounds(550, 100 + i *
											 * 25, 50, 25);
											 * 
											 * p2.add(name[i]); p2.add(id[i]);
											 * p2.add(gpv[i]);
											 * 
											 * i++;
											 */
				model.addRow(new Object[] {
						Integer.toString(modules[i].getModuleCode()),
						modules[i].getModuleName(),
						Integer.toString(modules[i].getCredits()) });
				
				

			}
			
			//Make the JTable uneditable
			table.setEnabled(false);
			//Set it to a scrollpane
			JScrollPane jsp = new JScrollPane(table);
			jsp.setBounds(50, 100, 500, 100 + modules.length * 10);
			
			p2.add(jsp);
			add(p2);
			p2.setVisible(true);
			revalidate();
		} 
		//If no modules found for the course
		else {
			JOptionPane.showMessageDialog(null, "No Modules Found");
			p2.setVisible(false);
			remove(p2);
			revalidate();
		}

	}

}
