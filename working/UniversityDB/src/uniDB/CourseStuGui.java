package uniDB;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class CourseStuGui extends JPanel{
	
	private JComboBox<String> courseCombo;
	private JButton selectB;
	private JLabel[] name;
	private JLabel[] index;
	private JLabel[] reg;
	private JPanel p1;
	private JPanel p2;
	private JTable table;
	
	public CourseStuGui(){
		setLayout(new GridLayout(2,1));
		courseCombo = new JComboBox<String>();
		selectB = new JButton("Select");
		
		for(int i=0;i<UOC.getUni().getNoOfCourses();i++){
			courseCombo.addItem(UOC.getUni().getCourses()[i].getCourseName());
		}
		/*courseCombo.addItem("Physical Science");
		courseCombo.addItem("Bio Science");
		courseCombo.addItem("Industrial Statistics");*/
		p1 = new JPanel();
		p2 = new JPanel();
		courseCombo.setBounds(200, 50, 100, 25);
		selectB.setBounds(280, 50, 120, 25);
		add(p1);
		p1.add(courseCombo);
		p1.add(selectB);
		
		selectB.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				showCourseStudents();
			}
		});
		
	}
	public void showCourseStudents(){
		//int indexNo=UOC.getUni().findCourse(courseCombo.getSelectedItem().toString());
		
		boolean valid = false;
		Student[] students=null;
		//int indexNo = UOC.getUni().findCourse(courseCombo.getSelectedItem().toString());
		try {
			//System.out.println("1");

			//THIS THING DIDN'T WORK ONCE FOR SOME REASON 
			
			/*String s =courseCombo.getSelectedItem().toString();
			int year = yearCombo.getSelectedIndex()+1;*/
			
			Connection conn = UOC.connect();
			
			 String query = "SELECT * FROM Student WHERE courseId = (SELECT courseId from Course where courseName = ?)";
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      //String s = "Physical Science";
		      preparedStmt.setString(1, courseCombo.getSelectedItem().toString());
		     
		      
		      ResultSet rs = preparedStmt.executeQuery();
		      
		      String query1 = "SELECT COUNT(*) FROM Student WHERE courseId = (SELECT courseId from Course where courseName = ?)";
		      PreparedStatement preparedStmt1 = conn.prepareStatement(query1);
		      //String s = "Physical Science";
		      preparedStmt1.setString(1, courseCombo.getSelectedItem().toString());
		      
		      ResultSet rs1 = preparedStmt1.executeQuery();
			
		      rs1.next();
		      //System.out.println(rs1.getInt(1));
		      students = new Student[rs1.getInt(1)];
		      
		      int i =0;
		      while(rs.next()){
		    	  //System.out.println("2");
		    	  valid =true;
		    	  Student s = new Student(rs.getInt("indexNo"),rs.getInt("regNo"),rs.getString("studentName"));
		    	  students[i++] = s;
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
		if (valid) {
			System.out.println("3");
			remove(p2);
			revalidate();
			//int numS = UOC.getUni().getCourses()[indexNo].getNoOfStudents();
			
			table = new JTable();
			DefaultTableModel model = (DefaultTableModel) table.getModel();
			model.setColumnIdentifiers(new Object[]{"Name","Index","Reg Number" });
			
			p2 = new JPanel();
			p2.setLayout(null);
			name=new JLabel[students.length];
			index=new JLabel[students.length];
			reg=new JLabel[students.length];
			
			for(int i=0;i<students.length;i++){
				/*name[i] = new JLabel(UOC.uni.getCourses()[indexNo].getCourseStudents()[i].getStudentName());
				index[i] = new JLabel(
						Integer.toString(UOC.uni.getCourses()[indexNo].getCourseStudents()[i].getIndexNo()));
				reg[i] = new JLabel(
						Integer.toString(UOC.uni.getCourses()[indexNo].getCourseStudents()[i].getRegNo()));
				
				name[i].setBounds(50, 100 + i * 25, 250, 25);
				index[i].setBounds(300, 100 + i * 25, 100, 25);
				reg[i].setBounds(400, 100 + i * 25, 200, 25);
				
				p2.add(name[i]);
				p2.add(index[i]);
				p2.add(reg[i]);*/
				
				model.addRow(new Object[]{students[i].getStudentName(),
						Integer.toString(students[i].getIndexNo()),
						Integer.toString(students[i].getRegNo())});
				
			}
			table.setEnabled(false);
			JScrollPane jsp = new JScrollPane(table);
			jsp.setBounds(50,100,500,100+10*students.length);
			p2.add(jsp);
			add(p2);
			p2.setVisible(true);
			revalidate();
		}
		else{
			JOptionPane.showMessageDialog(null, "No Student Found");
			p2.setVisible(false);
			remove(p2);
			revalidate();
		}
		
	}
	

}
