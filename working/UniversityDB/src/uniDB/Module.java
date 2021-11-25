package uniDB;



public class Module {
	
	protected int moduleCode;
	protected String moduleName;
	protected int credits;
	
	
	protected int year;
	
	protected Course relatedCourse;
	protected int noOfStudents=0;
	protected int maxStudents;
	protected Student[] moduleStudents;
	
	protected  int passMark;
	
	
	
	public Module(int moduleCode, String moduleName, int credits, Course relatedCourse, int maxStudents, int year) {
		
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.credits = credits;
		this.relatedCourse = relatedCourse;
		this.maxStudents=maxStudents;
		this.year =year;
		passMark=40;
		moduleStudents=new Student[maxStudents];
	}
	public Module(int moduleCode, String moduleName, int credits,int year) {
		
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.credits = credits;
		this.year =year;
		passMark=40;
	}
	
	public Module(int moduleCode, String moduleName, int credits) {
		
		this.moduleCode = moduleCode;
		this.moduleName = moduleName;
		this.credits = credits;
		//this.year =year;
		passMark=40;
	}
	
	
	


	public int getYear() {
		return year;
	}


	public void setYear(int year) {
		this.year = year;
	}


	public int getCredits() {
		return credits;
	}
	public void setCredits(int credits) {
		this.credits = credits;
	}
	
	public int getModuleCode() {
		return moduleCode;
	}
	public void setModuleCode(int moduleCode) {
		this.moduleCode = moduleCode;
	}
	public String getModuleName() {
		return moduleName;
	}
	public void setModuleName(String moduleName) {
		this.moduleName = moduleName;
	}
	
	
	/*public int getCourseId() {
		return courseId;
	}
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}*/
	public int getNoOfStudents() {
		return noOfStudents;
	}
	public void setNoOfStudents(int noOfStudents) {
		this.noOfStudents = noOfStudents;
	}
	public Student[] getModuleStudents() {
		return moduleStudents;
	}
	public void setModuleStudents(Student[] moduleStudents) {
		this.moduleStudents = moduleStudents;
	}
	public void setModuleStudent(Student s) {
		this.moduleStudents[noOfStudents] = s;
		noOfStudents++;
	}
	//Must calculate grade before giving a grade
	
	//HAVE TO CHANGE WHEN USING DATABASE
	//MOT NEEDED
	public void deleteStudent(int index){
		int no = findStudent(index);
		if(no==-1){
			//System.out.println("Student not found");
		}
		else{
			for(int i=no;i<noOfStudents-no;i++){
				moduleStudents[i]=moduleStudents[i+1];
			}
			noOfStudents--;
		}
	}
	public int findStudent(int index){
		for(int i=0;i<noOfStudents;i++){
			if(moduleStudents[i].getIndexNo()==index){
				return i;
			}
		}
		return -1;
	}
	
	
	

}
