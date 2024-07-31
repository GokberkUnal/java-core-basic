import java.sql.SQLException;
import java.util.Arrays;

import model.Classes;
import model.Student;
import model.Teacher;
import service.ClassesService;
import service.StudentService;
import service.TeacherService;

public class main {
	public static void main(String[] args) throws ClassNotFoundException, SQLException{
		ClassesService classesService = new ClassesService();
        StudentService studentsService = new StudentService();
        TeacherService teachersService = new TeacherService();
        
        
//        System.out.println(classesService.getAllClasses());
//        System.out.println(studentsService.getAllStudents());
//        System.out.println(teachersService.getAllTeachers());
        
        System.out.println(studentsService.getStudentsUniqClasses((long) 1));
        
        
        
        
   

        
//        Classes programingClass= new Classes(null,"Programming","Teaching programing");
//        classesService.addClass(programingClass);
//        System.out.println(classesService.getAllClasses());
        
//        Student veli = new Student(null, "Veli Meli");
//        studentsService.addStudent(veli);
//        System.out.println(studentsService.getAllStudents());
        

      /*  Classes newClass = new Classes(null, "Math", "Mathematics Class");
        classesService.addClass(newClass);
        System.out.println("All Classes: " + classesService.getAllClasses());


        Student newStudent = new Student(null, "John Doe");
        newStudent.setClassesList(Arrays.asList(newClass)); 
        studentsService.addStudent(newStudent);
        System.out.println("All Students: " + studentsService.getAllStudents());

      
        Teacher newTeacher = new Teacher(null, "Jane Smith", newClass.getId());
        teachersService.addTeacher(newTeacher);
        System.out.println("All Teachers: " + teachersService.getAllTeachers());*/	
	
	}
}
