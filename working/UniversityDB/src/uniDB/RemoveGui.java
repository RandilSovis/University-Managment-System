package uniDB;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.*;

public class RemoveGui extends JPanel {
	private JLabel mainL;
	private JTextField index;
	private JButton searchB;
	private Student stu;

	public RemoveGui() {
		
		//setting the layout
		setLayout(null);
		
		//creating the components
		mainL = new JLabel();
		searchB = new JButton("Remove");
		index = new JTextField();
		mainL = new JLabel("Enter Index");
		// add(p2);

		//setting the positions
		mainL.setBounds(200, 50, 100, 25);
		index.setBounds(280, 50, 120, 25);
		searchB.setBounds(415, 50, 100, 25);
		add(mainL);
		add(index);
		add(searchB);
		
		//The search for student and remove button event
		searchB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				removeStudnet();
			}
		});

		// setSize(700,700);
		setSize(700, 700);

	}

	//Metgos that searches for the student and removes him/her
	public void removeStudnet() {
		int ind = 0;
		//to check if there is a student
		boolean valid=false;
		try {
			ind = Integer.parseInt(index.getText());
		} catch (NumberFormatException e) {
			index.setText("Enter a Number");
			// setVisible(false);
			// revalidate();
		}
		try {

			//checking if there is a student in the DB
			String qs = "SELECT indexNo FROM Student WHERE indexNo=" + ind;
			

			//creating the connection to the DB
			Connection con1 = UOC.connect();
			Statement s1 = con1.createStatement();
			
			ResultSet rs = s1.executeQuery(qs);
			// If there is a next then yes
			
			if (rs.next()) {
				valid = true;
				/*Student s = new Student(rs.getInt("indexNo"), rs.getInt("regNo"), rs.getString("studentName"),
						rs.getInt("year"), rs.getInt("marksEntered"),rs.getInt("credits"),rs.getDouble("gpa"));
				s.setGpa(rs.getDouble("gpa"));
				this.stu = s;*/
				//Not needed
				ind=rs.getInt("indexNo");
				// System.out.println(s.getEntered());

			}
			// Close connections
			s1.close();
			con1.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
		//int indexNo = UOC.getUni().findStudent(ind);
		//If there is a student 
		if (valid) {
			
			//Asking for confirmation
			int response = JOptionPane.showConfirmDialog(null, "Do you want to Remove the Student?", "Confirm",JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
			//If confirmed proceed 
			if (response == JOptionPane.YES_OPTION) {
				
				try{
					//create a connection
					Connection con = UOC.connect();
					
					//query to delete student modules for the student
					//THIS MUST BE PERFORMED FIRST OTHERWISE FOREIGN CONTRAINT ISSUE
					  String qsm = "DELETE FROM StudentModule WHERE indexNo = ?";
				      PreparedStatement psm = con.prepareStatement(qsm);
				      psm.setInt(1, ind);
				      psm.execute();
				      
				      //Then delete the Student form the student table
				      String qs = "DELETE FROM Student WHERE indexNo = ?";
				      PreparedStatement ps = con.prepareStatement(qs);
				      ps.setInt(1, ind);
				      ps.execute();
				     
				      //Close connections
				      ps.close();
				      psm.close();
				      con.close();
				      
				}catch(SQLException e){
					System.err.println(e.getMessage());
				}
				
				//UOC.getUni().getStudents()[indexNo].unregisterStudent(UOC.getUni());
				//index.setText("Student Removed");
				JOptionPane.showMessageDialog(null, "Student Successfully Removed");
			}
		}
		//If no students found
		else {
			//index.setText("No Student Found");
			JOptionPane.showMessageDialog(null, "No Student Found");
		}
	}

}
