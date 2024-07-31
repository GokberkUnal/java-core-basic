package service;

import java.util.List;

import model.Classes;
import repository.RepositoryCommens;
import repository.ClassesRepository;

public class ClassesService {
	  private RepositoryCommens<Classes, Long> classesRepository = new ClassesRepository();

	    public void addClass(Classes classes) {
	        classesRepository.addToTable(classes);
	    }

	    public List<Classes> getAllClasses() {
	        return classesRepository.getAllTable();
	    }

	    public Classes getClassById(Long id) {
	        return classesRepository.getById(id);
	    }

	    public void updateClass(Classes classes) {
	        classesRepository.update(classes);
	    }

	    public void deleteClass(Long id) {
	        classesRepository.delete(id);
	    }
	    
}
