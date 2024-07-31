package service;

import repository.StudentRepository;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import model.Classes;
import model.Student;
import repository.RepositoryCommens;

public class StudentService {
	private RepositoryCommens<Student, Long> studentsRepository = new StudentRepository();
	public Map<Long, Student> studentCache = new HashMap<>();

	public void addStudent(Student student) {
		studentsRepository.addToTable(student);
	}

	public List<Student> getAllStudents() {
		return studentsRepository.getAllTable();
	}

	public void writeStudentsToCache() {

		if (!studentCache.isEmpty()) {
			studentCache.clear();
		}

		List<Student> allStudents = getAllStudents();
		for (Student student : allStudents) {
			studentCache.put(student.getId(), student);
		}

	}
	
	

	public Student getStudentById(Long id) {
		return studentsRepository.getById(id);
	}

	public void updateStudent(Student student) {
		studentsRepository.update(student);
	}

	public void deleteStudent(Long id) {
		studentsRepository.delete(id);
	}

	public Set<Classes> getStudentsUniqClasses(Long id) {
		Student indexStudent=getStudentById(id);
		List<Classes> indexStudentClasses =indexStudent.getClassesList();
		Set<Classes> uniqClasses = new HashSet<>(indexStudentClasses);
		return uniqClasses;
		

	}

}
