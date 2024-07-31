package service;
import java.util.List;

import model.Teacher;
import repository.RepositoryCommens;
import repository.TeacherRepository;
public class TeacherService {
	  private RepositoryCommens<Teacher, Long> teachersRepository = new TeacherRepository();

	    public void addTeacher(Teacher teacher) {
	        teachersRepository.addToTable(teacher);
	    }

	    public List<Teacher> getAllTeachers() {
	        return teachersRepository.getAllTable();
	    }

	    public Teacher getTeacherById(Long id) {
	        return teachersRepository.getById(id);
	    }

	    public void updateTeacher(Teacher teacher) {
	        teachersRepository.update(teacher);
	    }

	    public void deleteTeacher(Long id) {
	        teachersRepository.delete(id);
	    }
}
