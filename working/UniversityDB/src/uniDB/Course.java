package uniDB;


public class Course {
	
	private int courseId;
	private String courseName;
	private int maxStudents;
	//private int maxModules;
	private int noOfModules;
	private int noOfStudents=0;
	private Module[] courseModules;
	private Student[] courseStudents;
	
	
	
	public Course(int courseId, String courseName, int noOfModules, int maxStudents  ) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.noOfModules = noOfModules;
		this.maxStudents=maxStudents;
		courseModules=new Module[noOfModules];
		courseStudents=new Student[maxStudents];
	}
	
	public Course(int courseId, String courseName, int noOfModules) {
		this.courseId = courseId;
		this.courseName = courseName;
		this.noOfModules = noOfModules;
		
	}
	public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public int getNoOfModules() {
		return noOfModules;
	}
	public void setNoOfModules(int noOfModules) {
		this.noOfModules = noOfModules;
	}
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public Module[] getCourseModules() {
		return courseModules;
	}
	public void setCourseModules(Module[] courseModules) {
		this.courseModules = courseModules;
		this.noOfModules=courseModules.length;
	}
	public Student[] getCourseStudents() {
		return courseStudents;
	}
	public void setCourseStudents(Student[] courseStudents) {
		this.courseStudents = courseStudents;
	}
	public void setCourseStudent(Student courseStudent) {
		this.courseStudents[noOfStudents] = courseStudent;
		this.noOfStudents++;
	}
	//Not needed
	public void showCourseModules(){
		if(noOfModules==0){
			System.out.println("No Modules For "+this.courseName);
		}
		else{
			System.out.println("Module Code	Module Name	Credits");
			System.out.println("--------------------------------");
			for(int i=0;i<noOfModules;i++){
				System.out.println(courseModules[i].getModuleCode()+"	"+courseModules[i].getModuleName()+"	"+courseModules[i].getCredits());
			}
		}
	}
	//Not needed
	public void showCourseStudents(){
		if(noOfStudents==0){
			System.out.println("No Students For "+this.courseName);
		}
		else{
			System.out.println("Index	Registration	Student Name");
			System.out.println("---------------------");
			for(int i=0;i<noOfStudents;i++){
				System.out.println(courseStudents[i].getIndexNo()+"	"+courseStudents[i].getRegNo()+"	"+courseStudents[i].getStudentName());
			}
		}
	}
	//Not needed
	public int findStudent(int index){
		for(int i=0;i<noOfStudents;i++){
			if(courseStudents[i].getIndexNo()==index){
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
				courseStudents[i]=courseStudents[i+1];
			}
			noOfStudents--;
		}
	}
	//not needed
public int[] findYearModules(int year){
		
		int[] arr = new int[yearModuleNumber(year)];
		int a=0;
		for(int i=0;i<noOfModules;i++){
			if(courseModules[i].getYear()==year){
				arr[a]=i;
				a++;
			}
		}
		return arr;
	}
//not needed
	public int yearModuleNumber(int year){
		int count=0;
		for(int i=0;i<noOfModules;i++){
			if(courseModules[i].getYear()==year){
				count++;
			}
		}
		return count;
	}
	
	
	

}
