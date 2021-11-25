package uniDB;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UOC {

	private static University uni;

	public static void main(String[] args) {
		uni = new University(3, 100);
		
		//Better to get it from the DB straight
		Course[] ca = new Course[uni.getNoOfCourses()];
		
		// Adding the courses to the university
		Connection conn = UOC.connect();
		try {
			//First get all the courses info
			String qu = "SELECT * FROM Course";
			

			Statement st = conn.createStatement();

			ResultSet r = st.executeQuery(qu);
			int i = 0;
			
			//Then for each of the courses
			while (r.next()) {
				int id = r.getInt("courseId");
				String name = r.getString("CourseName");
				
				//Getting the number of Modules for a particular course from Module Table
				String qu2 = "SELECT COUNT(*) FROM Module WHERE courseId="+id;
				Statement st2 = conn.createStatement();
				ResultSet r2 = st2.executeQuery(qu2);
				int num=0;
				r2.next();
				
				//Creating the course
				Course c = new Course(id,name,r2.getInt(1));
				System.out.println(c.getCourseId() + " " + c.getCourseName());
				ca[i++] = c;
				

			}
			//Closing the statement and connection
			st.close();
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Add modules to coursE
		uni.setCourses(ca);
		HomeGui home = new HomeGui();
		

	}

	public static University getUni() {
		return uni;
	}

	//Method to create a connection to the DB
	public static Connection connect() {
		try {
			// create our mysql database connection
			String driver = "org.gjt.mm.mysql.Driver";
			String url = "jdbc:mysql://localhost/university";
			Class.forName(driver);
			Connection con = DriverManager.getConnection(url, "root", "");
			return con;

		} catch (Exception e) {
			System.err.println("Could not connect " + e.getMessage());
		}
		return null;
	}

}
