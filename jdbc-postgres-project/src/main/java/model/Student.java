package model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Student extends Person {
	
	private List<Classes> classesList = new ArrayList<>();
	
	 public Student() {}
	 public Student(Long id, String name) {
	        super(id, name);
	    }
	public List<Classes> getClassesList() {
		return classesList;
	}
	public void setClassesList(List<Classes> classesList) {
		this.classesList = classesList;
	}
	
	@Override
    public String toString() {
        return "Student [classesList=" + classesList + ", " + super.toString() + "]";
    }
	

}
