package repository;

import java.util.List;
import java.util.Set;

public interface RepositoryCommens<T, ID>  {
	 void addToTable(T entity);
	    List<T> getAllTable();
	    T getById(ID id);
	    void update(T entity);
	    void delete(ID id);
	 

}
