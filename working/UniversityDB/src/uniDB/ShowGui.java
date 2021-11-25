package uniDB;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class ShowGui extends JPanel {

	private JLabel mainL;
	private JLabel subL;
	private JLabel nameL;
	private JLabel indexL;
	private JLabel courseL;
	private JLabel regL;
	private JLabel nameLL;
	private JLabel indexLL;
	private JLabel courseLL;
	private JLabel regLL;
	private JLabel gpa;
	private JLabel totalGpa;
	private JLabel[] labels;
	private JLabel[] marks;
	private JLabel[] grade;
	private JLabel[] gpv;
	private JTextField index;
	private JButton searchB;
	private JPanel p1;
	private JPanel p2;
	private JTable table;
	private Student stu;
	
	private JComboBox<String> yearCombo;

	private int indexNo;

	public ShowGui() {
		//Adding the layout
		setLayout(new GridLayout(2, 1));
		
		//Creating the components 
		mainL = new JLabel();
		searchB = new JButton("Select");
		index = new JTextField();
		mainL = new JLabel("Enter Index");
		nameL = new JLabel();
		indexL = new JLabel();
		courseL = new JLabel();
		regL = new JLabel();
		
		nameLL = new JLabel();
		indexLL = new JLabel();
		courseLL = new JLabel();
		regLL = new JLabel();
		p1 = new JPanel();
		p2 = new JPanel();
		add(p1);
		
		p1.setLayout(null);
		
		//Adding the year combo box information
		yearCombo = new JComboBox<String>();

		yearCombo.addItem("Year 1");
		yearCombo.addItem("Year 2");
		yearCombo.addItem("Year 3");
		yearCombo.addItem("Year 4");

		//setting the positions
		yearCombo.setBounds(280, 75, 100, 25);
		mainL.setBounds(200, 50, 100, 25);
		index.setBounds(280, 50, 100, 25);
		searchB.setBounds(380, 75, 100, 25);
		// add(p2);
		
		/*mainL.setBounds(200, 50, 100, 25);
		index.setBounds(280, 50, 120, 25);
		searchB.setBounds(415, 50, 100, 25);*/
		
		//adding the components
		p1.add(mainL);
		p1.add(index);
		p1.add(searchB);
		
		p1.add(yearCombo);
		
		//the select button event
		searchB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				showStudent();
			}
		});

		// setSize(700,700);
		setSize(700, 700);

	}

	//Method that shows the student information
	public void showStudent() {
		int ind = 0;
			
		//First remove the labels 
		//Just in case no student were found
		//
		//THERE MIGHT BE A BETTER WAY TO DO THIS
		remove(p2);
		p1.remove(nameL);
		p1.remove(indexL);
		p1.remove(courseL);
		p1.remove(regL);
		p1.remove(nameLL);
		p1.remove(indexLL);
		p1.remove(courseLL);
		p1.remove(regLL);
		revalidate();
		p2.setVisible(false);
		
		repaint();
		
		try {
			ind = Integer.parseInt(index.getText());
		} catch (NumberFormatException e) {
			index.setText("Enter a Number");
			/*p2.setVisible(false);
			remove(p2);
			revalidate();*/
		}
		
		
		//int indexNo = UOC.getUni().findStudent(ind);
		//getting the year
		boolean valid =false;
		int year = yearCombo.getSelectedIndex() + 1;
		String course="";
		
		// Checking if a student exists in the DB
				try {

					String qs = "SELECT* FROM Student WHERE indexNo=" + ind;
					//Query to get  the course name 
					String qc = "SELECT courseName FROM Course WHERE courseId = (SELECT courseId FROM Student WHERE indexNo=" + ind+");";

					//Create connection
					Connection con1 = UOC.connect();
					Statement s1 = con1.createStatement();
					Statement sc = con1.createStatement();
					ResultSet rs = s1.executeQuery(qs);
					ResultSet rc = sc.executeQuery(qc);

					//get the courseName
					rc.next();
					course=rc.getString("courseName");
					// If there is a next then yes
					
					//Setting the student information
					//and checking If there is a Student
					if (rs.next()) {
						valid = true;
						Student s = new Student(rs.getInt("indexNo"), rs.getInt("regNo"), rs.getString("studentName"),
								rs.getInt("year"), rs.getInt("marksEntered"),rs.getInt("credits"),rs.getDouble("gpa"));
						s.setGpa(rs.getDouble("gpa"));
						this.stu = s;
						// System.out.println(s.getEntered());

					}
					// Close connections
					s1.close();
					sc.close();
					con1.close();

				} catch (SQLException e) {
					System.err.println(e.getMessage());
				}

				//If ther is a student
		if (valid) {
			// Remove it or multiple is possible so not good
			/*remove(p2);
			p1.remove(nameL);
			p1.remove(indexL);
			p1.remove(courseL);
			p1.remove(regL);
			
			p1.remove(nameLL);
			p1.remove(indexLL);
			p1.remove(courseLL);
			p1.remove(regLL);
			//p1.setVisible(false);
			revalidate();*/
			//p1.repaint();
			// Set the students index number
			/*nameL.setText("Name	:" + UOC.uni.getStudents()[indexNo].getStudentName());
			indexL.setText("Index	:" + Integer.toString(UOC.uni.getStudents()[indexNo].getIndexNo()));
			regL.setText("Reg	:" + Integer.toString(UOC.uni.getStudents()[indexNo].getRegNo()));
			courseL.setText("Course	:" + UOC.uni.getStudents()[indexNo].getStudentCourse().getCourseName());
			*/
			
			//Adding the Student details
			nameL = new JLabel(stu.getStudentName());
			indexL = new JLabel(Integer.toString(stu.getIndexNo()));
			regL = new JLabel(Integer.toString(stu.getRegNo()));
			courseL = new JLabel(course);
			
			nameLL = new JLabel("Name");
			indexLL = new JLabel("Index");
			regLL = new JLabel("Reg Number");
			courseLL = new JLabel("Course");
			
			nameLL.setBounds(50, 150, 175, 25);
			indexLL.setBounds(250, 150, 125, 25);
			regLL.setBounds(350, 150, 125, 25);
			courseLL.setBounds(475, 150, 200, 25);
			
			nameL.setBounds(50, 200, 200, 25);
			indexL.setBounds(250, 200, 125, 25);
			regL.setBounds(350, 200, 125, 25);
			courseL.setBounds(475, 200, 200, 25);
			
			//Adding  the components
			p1.add(nameL);
			p1.add(indexL);
			p1.add(regL);
			p1.add(courseL);
			
			p1.add(nameLL);
			p1.add(indexLL);
			p1.add(regLL);
			p1.add(courseLL);
			
			add(p1);
			
			p1.setVisible(true);
			revalidate();
			p1.repaint();
			
			//Creating the JTable info
			//this.indexNo = indexNo;
			subL = new JLabel("Marks Year"+year);
			//int numM = UOC.uni.getStudents()[indexNo].getNoOfModules();
			/*int numM = UOC.getUni().getStudents()[indexNo].yearModuleNumber(year,false);
			int[] moduleIndexes = UOC.getUni().getStudents()[indexNo].findYearModules(year,false);*/
			
			try {

				
				Connection conn = UOC.connect();

				// Getting the Module details from the StudentModules that the student is doing
				String qu = "SELECT *,StudentModule.moduleMarks  FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + stu.getIndexNo() + ";";
				String q1 = "SELECT COUNT(*) FROM Module INNER JOIN StudentModule ON Module.moduleCode = StudentModule.moduleCode WHERE year ="
						+ year + " AND StudentModule.indexNo=" + stu.getIndexNo() + ";";

				Statement st = conn.createStatement();
				Statement s1 = conn.createStatement();

				ResultSet r1 = s1.executeQuery(q1);
				r1.next();
				// Getting the number of modules and setting the array 
				// Could have used an ArrayList
				System.out.println(r1.getInt(1));
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
					StudentModule sm = new StudentModule(r.getInt("moduleCode"), r.getString("moduleName"),
							r.getInt("credits"), r.getInt("year"), r.getInt("moduleMarks"),r.getString("grade").charAt(0),r.getDouble("gpv"));

					sms[i++] = sm;
					// st2.close();

				}

				// Set the studentModules
				stu.setStudentModules(sms);
				// Closing the statements and connections

				s1.close();
				st.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			
			table = new JTable();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			
			//Setting the tables headers
			model.setColumnIdentifiers(new Object[]{"Module Name","Marks","Grade","GPV" });
			
			p2 = new JPanel();
			p2.setLayout(null);
			
			/*marks = new JLabel[stu.getNoOfModules()];
			labels = new JLabel[stu.getNoOfModules()];
			grade = new JLabel[stu.getNoOfModules()];
			gpv = new JLabel[stu.getNoOfModules()];*/
			subL.setBounds(300, 75, 250, 25);
			p2.add(subL);
			
			gpa=new JLabel();
			
			//If marks entered then show the marks, grade, gpv
			if (stu.isMarksEntered(year)) {
				
				
				
				// Setting up the labels and textfields
				/*
				for (int i = 0; i < numM; i++) {
					labels[i] = new JLabel(UOC.uni.getStudents()[indexNo].getStudentModules()[i].getModuleName());
					marks[i] = new JLabel(
							Integer.toString(UOC.uni.getStudents()[indexNo].getStudentModules()[i].getModuleMarks()));
					grade[i]= new JLabel(""+UOC.uni.getStudents()[indexNo].getStudentModules()[i].getGrade());
					gpv[i]= new JLabel(""+UOC.uni.getStudents()[indexNo].getStudentModules()[i].getGpv());
					labels[i].setBounds(50, 100 + i * 25, 250, 25);
					marks[i].setBounds(300, 100 + i * 25, 100, 25);
					grade[i].setBounds(400, 100 + i * 25, 50, 25);
					gpv[i].setBounds(500, 100 + i * 25, 100, 25);
					p2.add(labels[i]);
					p2.add(marks[i]);
					p2.add(grade[i]);
					p2.add(gpv[i]);

				}*/
				//int i=0;
				
				//Show the results in tablerow by row
				for(int j=0;j<stu.getNoOfModules();j++){
					/*labels[i] = new JLabel(UOC.uni.getStudents()[indexNo].getStudentModules()[a].getModuleName());
					marks[i] = new JLabel(
							Integer.toString(UOC.uni.getStudents()[indexNo].getStudentModules()[a].getModuleMarks()));
					grade[i]= new JLabel(""+UOC.uni.getStudents()[indexNo].getStudentModules()[a].getGrade());
					gpv[i]= new JLabel(""+UOC.uni.getStudents()[indexNo].getStudentModules()[a].getGpv());
					labels[i].setBounds(50, 100 + i * 25, 250, 25);
					marks[i].setBounds(300, 100 + i * 25, 100, 25);
					grade[i].setBounds(400, 100 + i * 25, 50, 25);
					gpv[i].setBounds(500, 100 + i * 25, 100, 25);
					p2.add(labels[i]);
					p2.add(marks[i]);
					p2.add(grade[i]);
					p2.add(gpv[i]);*/
					
					model.addRow(new Object[]{stu.getStudentModules()[j].getModuleName(),
							Integer.toString(stu.getStudentModules()[j].getModuleMarks()),
							""+stu.getStudentModules()[j].getGrade(),""+stu.getStudentModules()[j].getGpv()});
					
					
					
					//i++;
				}
				
				
				//Show the gpa
				//totalGpa=new JLabel("Total GPA :"+stu.getGpa());
				gpa.setText("GPA :"+stu.getGpa());
				gpa.setBounds(300, 100 + stu.getNoOfModules() * 25, 150, 25);
				//totalGpa.setBounds(300, 125 + stu.getNoOfModules() * 25, 150, 25);
				
				
				p2.add(gpa);
				//p2.add(totalGpa);
				// Adding the submit button and event
				/*add(p2);
				p2.setVisible(true);
				revalidate();*/
				
				//These should be here Otherwise J plane is null or something
				
				//repaint();
			}
			//If marks not entered yet then no marks, grade, gpv and gpa
			else{
				/*p2.setVisible(false);
				remove(p2);
				revalidate();
				repaint();*/
				
				//Remove the gpa 
				
				
				
				
				p2.remove(gpa);
				//p2.remove(totalGpa);
				
				for(int j=0;j<stu.getNoOfModules();j++){
					/*labels[i] = new JLabel(UOC.uni.getStudents()[indexNo].getStudentModules()[a].getModuleName());
					marks[i] = new JLabel(
							Integer.toString(UOC.uni.getStudents()[indexNo].getStudentModules()[a].getModuleMarks()));
					grade[i]= new JLabel(""+UOC.uni.getStudents()[indexNo].getStudentModules()[a].getGrade());
					gpv[i]= new JLabel(""+UOC.uni.getStudents()[indexNo].getStudentModules()[a].getGpv());
					labels[i].setBounds(50, 100 + i * 25, 250, 25);
					marks[i].setBounds(300, 100 + i * 25, 100, 25);
					grade[i].setBounds(400, 100 + i * 25, 50, 25);
					gpv[i].setBounds(500, 100 + i * 25, 100, 25);
					p2.add(labels[i]);
					p2.add(marks[i]);
					p2.add(grade[i]);
					p2.add(gpv[i]);*/
					
					//Adding column info accordingly
					String msg="N/A";
					
					model.addRow(new Object[]{stu.getStudentModules()[j].getModuleName(),
							msg,
							""+msg,""+msg});
					
					
					
					//i++;
				}

				//JOptionPane.showMessageDialog(null, "Marks for "+year+" is not Entered");
			}
			//Both adding the table to panel 2
			JScrollPane jsp = new JScrollPane(table);
			table.setEnabled(false);
			
			jsp.setBounds(50, 100, 600, 135);
			
			table.setPreferredScrollableViewportSize(new Dimension(500,500));
			//table.setFillsViewportHeight(true);
			
			//table.setBounds(50, 100, 500, 500);
			//p2.add(jsp);
			//p2.add(table);
			p2.add(jsp);
			add(p2);
			p2.setVisible(true);
			revalidate();
			repaint();
			
		} 
		//If no student found
		else {
			//remove(p2);
			
			
			//index.setText("No Student Found");
			p2.setVisible(false);
			//remove(p2);
			//revalidate();
			//repaint();
			JOptionPane.showMessageDialog(null, "No Student Found");
		}
	}

}
