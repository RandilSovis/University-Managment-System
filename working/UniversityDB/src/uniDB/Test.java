package uniDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//HomeGui hgui=new HomeGui();
		
		try {

			//int name=1;
			Connection conn = UOC.connect();
			 String query = "SELECT * FROM Module WHERE courseId = (SELECT courseId from Course where courseName = ?)";
		      PreparedStatement preparedStmt = conn.prepareStatement(query);
		      String s = "Physical Science";
		      preparedStmt.setString(1, s);
		      
		      ResultSet rs = preparedStmt.executeQuery();
			
			/*Connection con1 = UOC.connect();

			PreparedStatement p = con1.prepareStatement(q);
	p.setInt(1, 1);
			ResultSet r = p.executeQuery(q);
			
			r.next();
			int id = r.getInt("courseId");*/
		      rs.next();
			System.out.println(rs.getString("moduleName"));
		

			// Close connection
			
			//con1.close();

		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}

	}

}
