package uniDB;

public class StudentModule extends Module {
	private boolean pass;
	private int moduleMarks;
	private char grade;
	private double gpv;
	
	
	public StudentModule(int moduleCode, String moduleName, int credits, Course relatedCourse, int maxStudents,int year) {
		super(moduleCode, moduleName, credits, relatedCourse, maxStudents, year);
		// TODO Auto-generated constructor stub
	}
	public StudentModule(int moduleCode, String moduleName, int credits,int year,int moduelMarks) {
		super(moduleCode, moduleName, credits,  year);
		this.moduleMarks=moduelMarks;
		// TODO Auto-generated constructor stub
	}
	public StudentModule(int moduleCode, String moduleName, int credits,int year,int moduelMarks,char grade,double gpv) {
		super(moduleCode, moduleName, credits,  year);
		this.moduleMarks=moduelMarks;
		this.grade=grade;
		this.gpv=gpv;
		// TODO Auto-generated constructor stub
	}
	public StudentModule(Module m) {
		super(m.moduleCode, m.moduleName, m.credits, m.relatedCourse, m.maxStudents, m.year);
		// TODO Auto-generated constructor stub
	}
	
	//Checking if the modules is passed
	public boolean isPass() {
		setPass();
		return pass;
	}


	public void setPass() {
		if(moduleMarks>=passMark)
			pass = true;
		else
			pass=false;
	}
	
	public int getModuleMarks() {
		return moduleMarks;
	}
	//Setting module marks.. This validation is not  as it is already done 
	public int setModuleMarks(int moduleMarks) {
		if(moduleMarks>=0&&moduleMarks<=100){
			this.moduleMarks = moduleMarks;
			return 0;
		}
		else{
			return -1;
		}
		
	}
	public double getGpv() {
		calculateGpv();
		return gpv;
	}
	
	public char getGrade() {
		calculateGrade();
		return grade;
	}
	//Calculating the grade
	public void calculateGrade() {
		if(moduleMarks>80){
			this.grade='A';
		}
		else if(moduleMarks>60){
			this.grade='B';
		}
		else if(moduleMarks>50){
			this.grade='C';
		}
		else if(moduleMarks>=40){
			this.grade='D';
		}
		else{
			this.grade='F';
		}
	}
	//Calculating the gpv
	public void calculateGpv() {
		calculateGrade();
		switch(this.grade){
		case 'A':
			this.gpv=4.00;
			break;
		case 'B':
			this.gpv=3.00;
			break;
		case 'C':
			this.gpv=2.00;
			break;
		case 'D':
			this.gpv=1.00;
			break;
		default:
			this.gpv=0.00;
		}
	}

}
