package uniDB;


import java.util.Scanner;

public class Student {

	private int indexNo;
	private int regNo;
	private String studentName;
	private int courseId;
	private Course studentCourse;
	private int credits = 0;
	private int noOfModules;
	// Might need to change this to
	// private Module[] studentModules=new Module[noOfModules];
	private StudentModule[] studentModules;
	private double gpa;
	private int year;
	private int entered;
	
	private boolean[] marksEntered = new boolean[4];
	Scanner sc = new Scanner(System.in);
	Scanner in = new Scanner(System.in);

	public Student() {

	}

	public Student(int indexNo, int regNo, String studentName, Course studentCourse) {

		this.indexNo = indexNo;
		this.regNo = regNo;
		this.studentName = studentName;
		this.studentCourse = studentCourse;
	}

	public Student(int indexNo, int regNo, String studentName, int year, int entered,int credits,double gpa) {

		this.indexNo = indexNo;
		this.regNo = regNo;
		this.studentName = studentName;
		this.year=year;
		this.entered=entered;
		this.credits=credits;
		this.gpa = gpa;

	}

	public Student(int indexNo, int regNo, String studentName) {

		this.indexNo = indexNo;
		this.regNo = regNo;
		this.studentName = studentName;
		

	}
	
	

	public int getEntered() {
		return entered;
	}

	public void setEntered(int entered) {
		this.entered = entered;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public boolean[] isMarksEntered() {
		return marksEntered;
	}

	public boolean isMarksEntered(int year) {
		return entered>=year?true:false;
	}

	public void setMarksEntered(boolean[] marksEntered) {
		this.marksEntered = marksEntered;
	}

	public void setMarkEnteredYear(int year) {
		marksEntered[year - 1] = true;
	}

	public Course getStudentCourse() {
		return studentCourse;
	}

	public void setStudentCourse(Course studentCourse) {
		this.studentCourse = studentCourse;
		this.courseId=studentCourse.getCourseId();
	}

	public void setCredits(int credits) {
		this.credits = credits;
	}

	// to get the gpa, first must calculate it
	public double getGpa() {
		//calculateGpa();
		return gpa;
	}

	public double getGpa(int year, boolean isTill) {
		calculateGpa(year, isTill);
		return gpa;
	}

	public void setGpa(double gpa) {
		this.gpa = gpa;
	}

	public int getIndexNo() {
		return indexNo;
	}

	public void setIndexNo(int indexNo) {
		this.indexNo = indexNo;
	}

	public int getRegNo() {
		return regNo;
	}

	public void setRegNo(int regNo) {
		this.regNo = regNo;
	}

	public String getStudentName() {
		return studentName;
	}

	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}

	public int getCourseId() {
		return courseId;
	}

	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	public int getNoOfModules() {
		return noOfModules;
	}

	public void setNoOfModules(int noOfModules) {
		this.noOfModules = noOfModules;
	}

	public StudentModule[] getStudentModules() {
		return studentModules;
	}

	// Must set the noOFModules here as well
	public void setStudentModules(StudentModule[] studentModules) {
		
		this.studentModules = new StudentModule[studentModules.length];
		this.studentModules=studentModules;
		/*for(int i=0;i<studentModules.length;i++){
			this.studentModules[i]=new StudentModule(studentModules[i]);
		}*/
		
		//System.arraycopy(studentModules,0,this.studentModules,0,studentModules.length);;
		
		this.noOfModules = studentModules.length;
	}
	
/*public void setStudentModules(StudentModule[] studentModules) {
		
		this.studentModules = new StudentModule[studentModules.length];
		this.noOfModules = studentModules.length;
	}*/

	
	// Calculating the gpa
	//The one used
	public double calculateGpa() {
		double total = 0;

		for (int i = 0; i < noOfModules; i++) {
			total += studentModules[i].getGpv() * studentModules[i].getCredits();

		}
		//total+=gpa*credits;
		return gpa = total / (calculateCredits());
	}

	//NOT USED 
	public double calculateGpa(int year, boolean isTill) {
		double total = 0;

		//GPA till the year 
		if (isTill) {
			for (int i = 0; i < noOfModules; i++) {
				if (studentModules[i].getYear() <= year) {
					total += studentModules[i].getGpv() * studentModules[i].getCredits();
				}

			}
		} 
		//GPA for only that year
		else {
			for (int i = 0; i < noOfModules; i++) {

				if (studentModules[i].getYear() == year) {
					total += studentModules[i].getGpv() * studentModules[i].getCredits();
				}

			}
		}
		//calculateCredits(year, isTill);
		return gpa = total / calculateCredits(year, isTill);
	}

	//NOT USED
	public int calculateCredits(int year, boolean isTill) {
		int credits = 0;
		//Credits earned till that year
		if(isTill){
		for (int i = 0; i < noOfModules; i++) {
				if (studentModules[i].getYear() <= year) {
					credits += studentModules[i].getCredits();
				}
			
		}
		}
		//Credits for that year
		else{
			for (int i = 0; i < noOfModules; i++) {
				if (studentModules[i].getYear() == year) {
					credits += studentModules[i].getCredits();
				}
			
		}
		}
		//this.credits = credits;
		return credits;
	}

	public int getCredits() {
		return credits;
	}

	// Calculating the credits needed to find the gpa
	//THE ONE USED
	public int calculateCredits() {
		int credits = 0;
		for (int i = 0; i < noOfModules; i++) {
			credits += studentModules[i].getCredits();

		}
		//this.credits = credits;
		return credits;
	}

	// Showing details of the student.. If marks entered then the grade gpv and
	// gpa as well
	//NOT USED
	public void showStudentDetails() {
		System.out
				.println("Name:" + studentName + "	index:" + indexNo + "	Course:" + studentCourse.getCourseName());
		// if(this.marksEntered){
		System.out.println("Module Code	Module Name	Grade	GPV");
		System.out.println("-----------------------------------");
		for (int i = 0; i < noOfModules; i++) {
			System.out.println(studentModules[i].getModuleCode() + "	" + studentModules[i].getModuleName() + "	"
					+ studentModules[i].getGrade() + "	" + studentModules[i].getGpv());
		}
		System.out.println("-----------------------------------");
		System.out.println("GPA:" + getGpa());
		// }
	}

	// Getting and setting the marks for the modules
	//NOT USED
	public void setModuleMarks() {
		for (int i = 0; i < noOfModules; i++) {
			boolean error = false;
			// If the marks are over or under the boundary
			do {
				System.out.println("Enter marks for " + studentModules[i].getModuleName());
				int mark = sc.nextInt();
				if (mark >= 0 && mark <= 100) {
					studentModules[i].setModuleMarks(mark);
					error = false;

				} else {
					System.out.println("Marks must be between 0 and 100");
					System.out.print("Re");
					error = true;
				}
			} while (error);
		}
		// marksEntered=true;
		System.out.println("Marks Entered");
	}

	// Adding the student to the modules he/she follows
	//EDIT THIS
	//NOT USED
	public void addStudentToModules(University uni) {
		for (int i = 0; i < noOfModules; i++) {
			studentCourse.getCourseModules()[i].setModuleStudent(this);
		}
	}

	// Deleting student from modules
	//NOT USED
	public void deleteStudentModules() {
		for (int i = 0; i < noOfModules; i++) {
			studentModules[i].deleteStudent(this.indexNo);
		}
	}

	// Unregister Student
	//NOT USED
	public void unregisterStudent(University uni) {
		deleteStudentModules();
		this.studentCourse.deleteStudent(this.indexNo);
		uni.deleteStudent(this.indexNo);
		System.out.println("Student Unregistered From University");
	}

	// Getting Student information and registering the student
	//NOT USED
	public void registerStudent(University uni) {
		// Student s = new Student();
		System.out.println("Enter Student index Number");
		this.indexNo = sc.nextInt();
		System.out.println("Enter Student Registration Number");
		this.regNo = sc.nextInt();
		System.out.println("Enter Student Name");
		this.studentName = in.nextLine();
		uni.showCourses();
		System.out.println("Select Course");
		int result;
		// Checking if the entered course is in the university
		do {
			int id = sc.nextInt();
			result = uni.findCourse(id);
			if (result != -1) {

				this.studentCourse = uni.getCourses()[result];
				this.noOfModules = uni.getCourses()[result].getNoOfModules();
			} else {
				System.out.println("Invalid course id: Please reenter");
			}
			// s.setCourseId();
		} while (result == -1);
		// setting the student modules
		//studentModules = studentCourse.getCourseModules();
		// Adding the student to the respective modules
		addStudentToModules(uni);
		uni.getCourses()[result].setCourseStudent(this);
		// Add student array
		uni.addStudent(this);

	}
	//finding how many modules the student has to resit
	public int resitModuleNumber(int year,boolean isTill){
		int count = 0;
		/*int[] arr = findYearModules(year,isTill);
		
		for(int a:arr){
			if (!studentModules[a].isPass()) {
				count++;
			}
		}*/
		for(int i = 0 ;i<noOfModules;i++){
			if (!studentModules[i].isPass()) {
				count++;
			}
		}
		/*for (int i = 0; i < noOfModules; i++) {
			if (!studentModules[i].isPass()) {
				count++;
			}
		}*/
		
		return count;
	}

	//Find the resit modules
	public int[] findResitModules(int year,boolean isTill) {

		int[] arr = new int[resitModuleNumber(year,isTill)];
		int[] modules = findYearModules(year,isTill);
		int a = 0;
		//if(isTill){
		for(int i:modules){
			if (!studentModules[i].isPass() ) {
				arr[a] = i;
				a++;
			}
		}
		/*for (int i = 0; i < noOfModules; i++) {
			if (!studentModules[i].isPass() ) {
				arr[a] = i;
				a++;
			}
		}*/
		//}
		/*else{
			for (int i = 0; i < noOfModules; i++) {
				if (studentModules[i].getYear() == year) {
					arr[a] = i;
					a++;
				}
			}
		}*/
		return arr;
	}
	

	//Finding the year modules
	//Not needed I think
	public int[] findYearModules(int year,boolean isTill) {

		int[] arr = new int[yearModuleNumber(year,isTill)];
		int a = 0;
		if(isTill){
		for (int i = 0; i < noOfModules; i++) {
			if (studentModules[i].getYear() <= year) {
				arr[a] = i;
				a++;
			}
		}
		}
		else{
			for (int i = 0; i < noOfModules; i++) {
				if (studentModules[i].getYear() == year) {
					arr[a] = i;
					a++;
				}
			}
		}
		return arr;
	}

	
	public int yearModuleNumber(int year,boolean isTill) {
		int count = 0;
		if(isTill){
		for (int i = 0; i < noOfModules; i++) {
			if (studentModules[i].getYear() <= year) {
				count++;
			}
		}
		}
		else{
			for (int i = 0; i < noOfModules; i++) {
				if (studentModules[i].getYear() == year) {
					count++;
				}
			}
		}
		return count;
	}

	/*public boolean isMarksEnteredTill(int year) {
		boolean r = true;
		for (int i = 0; i < year ; i++) {
			if (marksEntered[i] == false)
				r = false;
		}
		return r;
	}*/
	//Checking if the marks entered till the year
	public boolean isMarksEnteredTill(int year) {
		if(entered>=year){
			return true;
		}
		return false;
	}
	

}
