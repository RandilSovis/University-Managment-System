package uniDB;

import java.util.Scanner;


public class University {
	int max =100;
	private int noOfCourses;
	private int maxStudentNo;
	private int noOfStudents=0;
	private Course[] courses;
	private Student[] students;
	Scanner sc = new Scanner(System.in);
	Scanner in = new Scanner(System.in);

	/*public static void main(String[] args) {
		//LATER ADD THESE INTO SWITCH CASE AND IMPLEMENT SHOW COURSE DETAILS, SHOW MODULE DETAILS ETC
		//LET IT JUST SET UP STUDENTS THEN SAVE THEM IN AN ARRAYLIST... THEN BY USING THE INDEX YOU CAN ENTER THE MARKS, SHOW THE DETAILS N SUCH
		//ADD A SWITCH CASE INSIDE ANOTHER SWITHCASE
		University uni = new University();
		uni.initializingCourses();
		//Getting student information
		Student student = new Student();
		student=uni.setStudent();
		
		//Getting marks
		uni.getMarks(student);
		student.showStudentDetails();
			

	}*/

	public University(int noOfCourses, int maxStudentNo) {
		//super();
		this.noOfCourses = noOfCourses;
		this.maxStudentNo = maxStudentNo;
		courses = new Course[noOfCourses];
		students = new Student[maxStudentNo];
	}
	

	public int getNoOfCourses() {
		return noOfCourses;
	}



	public void setNoOfCourses(int noOfCourses) {
		this.noOfCourses = noOfCourses;
	}


	public int getMaxStudentNo() {
		return maxStudentNo;
	}


	public void setMaxStudentNo(int maxStudentNo) {
		this.maxStudentNo = maxStudentNo;
	}


	public int getNoOfStudents(){
		return this.noOfStudents;
	}


	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}


	public Course[] getCourses() {
		return courses;
	}


	public void setCourses(Course[] courses) {
		this.courses = courses;
	}


	public Student[] getStudents() {
		return students;
	}


	public void setStudents(Student[] students) {
		this.students = students;
	}
	//Adding a student to the university must increment the number of students 
	public void addStudent(Student student) {
		this.students[noOfStudents]=student;
		noOfStudents++;
		
	}



	//NOT NEEDED
	public  void initializingCourses() {
		// First course
		Course c1 = new Course(1, "Physical Science", 6,max);
		Module c1m1 = new Module(11001, "Modern Physics", 2, c1,max,1);
		Module c1m2 = new Module(11002, "Thermodynamics", 1, c1,max,1);
		Module c1m3 = new Module(11003, "Differental Equations", 2, c1,max,1);
		Module c1m4 = new Module(11004, "Statistical Computing", 2, c1,max,1);
		Module c1m5 = new Module(11005, "Organic Chemistry", 3, c1,max,1);
		Module c1m6 = new Module(11006, "Calculas", 2, c1,max,1);
		
		Module c1m7 = new Module(12001, "Physical Laboratory ", 2, c1,max,2);
		Module c1m8 = new Module(12002, "Electromagnetic Theory", 2, c1,max,2);
		Module c1m9 = new Module(12003, "Differental Equations 2", 2, c1,max,2);
		Module c1m10 = new Module(12004, "Linear Programming", 2, c1,max,2);
		Module c1m11= new Module(12005, "Bio Chemistry", 3, c1,max,2);
		Module c1m12 = new Module(12006, "Software Engineering", 3, c1,max,2);
		
		Module c1m13 = new Module(13001, "Quantum Mechanics", 3, c1,max,3);
		Module c1m14 = new Module(13002, "Nuclear Physics", 3, c1,max,3);
		Module c1m15 = new Module(13003, "Mathematical Methods", 2, c1,max,3);
		Module c1m16 = new Module(13004, "Regression Analysis", 2, c1,max,3);
		Module c1m17 = new Module(13005, "Algebra", 3, c1,max,3);
		Module c1m18 = new Module(13006, "Visual Programming", 3, c1,max,3);
		
		Module c1m19 = new Module(14001, "Computaional Chemistry", 2, c1,max,4);
		Module c1m20 = new Module(14002, "Complex Analysis", 3, c1,max,4);
		Module c1m21 = new Module(14003, "Database Systems", 2, c1,max,4);
		Module c1m22 = new Module(14004, "Sampling Techniques", 1, c1,max,4);
		Module c1m23 = new Module(14005, "Financial Mathematics", 3, c1,max,4);
		Module c1m24 = new Module(14006, "Industrial Chemistry", 2, c1,max,4);
		
		Module[] c1m = { c1m1, c1m2, c1m3, c1m4, c1m5, c1m6,c1m7, c1m8, c1m9, c1m10, c1m11, c1m12,c1m13, c1m14, c1m15, c1m16, c1m17, c1m18,c1m19, c1m20, c1m21, c1m22, c1m23, c1m24 };
		c1.setCourseModules(c1m);

		// Second course
		Course c2 = new Course(2, "Industrial Statistics", 5,max);
		Module c2m1 = new Module(21001, "Probability", 3, c2,max,1);
		Module c2m2 = new Module(21002, "Statistical Computing", 2, c2,max,1);
		Module c2m3 = new Module(21003, "Linear Programming", 3, c2,max,1);
		Module c2m4 = new Module(21004, "Survey", 2, c2,max,1);
		Module c2m5 = new Module(21005, "Calculas", 3, c2,max,1);
		
		Module c2m6 = new Module(22001, "Statistical Inference", 3, c2,max,2);
		Module c2m7 = new Module(22002, "Matrices", 2, c2,max,2);
		Module c2m8 = new Module(22003, "Actuarial Mathematics", 2, c2,max,2);
		Module c2m9 = new Module(22004, "Logical Analysis", 2, c2,max,2);
		Module c2m10 = new Module(22005, "Quality Control", 1, c2,max,2);
		Module c2m11= new Module(22006, "Software Engineering", 3, c2,max,2);
		Module c2m12 = new Module(22007, "Calculas 2", 3, c2,max,2);
		
		Module c2m13 = new Module(23001, "Regression Analysis", 3, c2,max,3);
		Module c2m14 = new Module(23002, "Calculas 3", 2, c2,max,3);
		Module c2m15 = new Module(23003, "Numerical Methods", 2, c2,max,3);
		Module c2m16 = new Module(23004, "Sampling Techniques", 1, c2,max,3);
		Module c2m17 = new Module(23005, "Visual Programming", 3, c2,max,3);
		
		Module c2m18 = new Module(24001, "Game Theory", 3, c2,max,4);
		Module c2m19 = new Module(24002, "Time Series", 2, c2,max,4);
		Module c2m20 = new Module(24003, "Operational Research", 3, c2,max,4);
		Module c2m21 = new Module(24004, "Multivariate Methods", 2, c2,max,4);
		
		
		Module[] c2m = { c2m1, c2m2, c2m3, c2m4, c2m5,c2m6, c2m7, c2m8, c2m9, c2m10,c2m11, c2m12, c2m13, c2m14, c2m15
				,c2m16, c2m17, c2m18, c2m19, c2m20,c2m21};
		
		c2.setCourseModules(c2m);
		// Third course
		Course c3 = new Course(3, "Biological Science", 6,max);
		
		Module c3m1 = new Module(31001, "Plant Resources", 1, c3,max,1);
		Module c3m2 = new Module(31002, "Flora", 1, c3,max,1);
		Module c3m3 = new Module(31003, "Organic Chemistry", 3, c3,max,1);
		Module c3m4 = new Module(31004, "Cell Biology", 2, c3,max,1);
		Module c3m5 = new Module(31005, "Calculation Chemistry", 2, c3,max,1);
		Module c3m6 = new Module(31006, "Animal Behaviour", 2, c3,max,1);
		
		Module c3m7 = new Module(32001, "Miocro Biology", 1, c3,max,2);
		Module c3m8 = new Module(32002, "Biostatistics", 2, c3,max,2);
		Module c3m9 = new Module(32003, "Bio Chemistry", 3, c3,max,2);
		Module c3m10 = new Module(32004, "Systems", 2, c3,max,2);
		Module c3m11= new Module(32005, "Animal Form", 2, c3,max,2);
		Module c3m12 = new Module(32006, "Plant Development", 3, c3,max,2);
		Module c3m13 = new Module(32007, "Software Engineering", 3, c3,max,2);
		
		Module c3m14 = new Module(33001, "Molecular Biology", 2, c3,max,3);
		Module c3m15 = new Module(33002, "Quality Managemnet", 2, c3,max,3);
		Module c3m16 = new Module(33003, "Economic Zoology", 2, c3,max,3);
		Module c3m17 = new Module(33004, "Mammalian Biology", 2, c3,max,3);
		Module c3m18 = new Module(33005, "Chemical Technology", 1, c3,max,3);
		Module c3m19 = new Module(33006, "Pharmaceutucal Chemistry", 3, c3,max,3);
		
		Module c3m20 = new Module(34001, "Plant Pathology", 3, c3,max,4);
		Module c3m21 = new Module(34002, "Anthoropology", 3, c3,max,4);
		Module c3m22 = new Module(34003, "Horiculture", 3, c3,max,4);
		Module c3m23 = new Module(34004, "Industrual Chemistry", 2, c3,max,4);
		
		Module[] c3m = { c3m1, c3m2, c3m3, c3m4, c3m5, c3m6,c3m7, c3m8, c3m9, c3m10, c3m11, c3m12
				,c3m13, c3m14, c3m15, c3m16, c3m17, c3m18,c3m19, c3m20, c3m21, c3m22, c3m23};
		c3.setCourseModules(c3m);
		Course[] c = {c1,c2,c3};
		this.courses=c;
	}
	/*public Student setStudent(){
		Student s = new Student();
		System.out.println("Enter Student index Number");
		s.setIndexNo(sc.nextInt());
		System.out.println("Enter Student Registration Number");
		s.setRegNo(sc.nextInt());
		System.out.println("Enter Student Name");
		s.setStudentName(in.nextLine());
		showCourses();
		System.out.println("Select Course");
		int result;
		do{
			int id=sc.nextInt();
			result=findCourse(id);
			if(result!=-1){
				s.setCourseId(id);
				s.setStudentCourse(courses[result]);
			}
			else{
				System.out.println("Invalid course id: Please reenter");
			}
		//s.setCourseId();
		}while(result==-1);
		s.setStudentModules(s.getStudentCourse().getCourseModules());
		addStudentToModules(s);
		s.getStudentCourse().setCourseStudent(s);
		students[studentNo]=s;
		return s;
		
	}*/
	//Showing the courses available at the university
	//Not needed
	public void showCourses(){
		System.out.println("Course Id	Course Name");
		System.out.println("------------------------");
		for(int i=0;i<noOfCourses;i++){
			System.out.println(courses[i].getCourseId()+"	"+courses[i].getCourseName());
		}
	}
	//Finding and returning the index of a course
	//not needed
	public int findCourse(int id){
		//int num=-1;
		for(int i=0;i<noOfCourses;i++){
			if(courses[i].getCourseId()==id){
				//num=i;
				return i;
			}
			
		}
		return -1;
		
	}
	//not needed double check
	public int findCourse(String name){
		//int num=-1;
		for(int i=0;i<noOfCourses;i++){
			if(courses[i].getCourseName().equalsIgnoreCase(name)){
				//num=i;
				return i;
			}
			
		}
		return -1;
		
	}
	/*public void getMarks(Student s){
		for(int i=0;i<s.getNoOfModules();i++){
			boolean error=false;
			do{
			System.out.println("Enter marks for "+s.getStudentModules()[i].getModuleName());
			int mark =sc.nextInt();
			if(mark>=0&&mark<=100){
				s.getStudentModules()[i].setModuleMarks(mark);
				
			}
			else{
				System.out.println("Marks must be between 0 and 100");
				System.out.print("Re");
				error=true;
			}
			}while(error);
		}
	}*/
	/*public void addStudentToModules(Student s){
		for(int i=0;i<s.getNoOfModules();i++){
			s.getStudentModules()[i].setModuleStudent(s);
		}
	}*/
	//Finding and returning the index of a student
	//not needed
	public int findStudent(int index){
		for(int i=0;i<noOfStudents;i++){
			if(students[i].getIndexNo()==index){
				return i;
			}
		}
		return -1;
	}
	//not needed
	public void deleteStudent(int index){
		int no = findStudent(index);
		if(no==-1){
			System.out.println("Student not found");
		}
		else{
			for(int i=no;i<noOfStudents-no;i++){
				students[i]=students[i+1];
			}
			noOfStudents--;
		}
	}

}
