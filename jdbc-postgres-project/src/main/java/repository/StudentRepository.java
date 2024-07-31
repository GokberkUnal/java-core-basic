package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import config.DatabaseConnector;
import model.Classes;
import model.Student;

public class StudentRepository implements RepositoryCommens<Student, Long> {

	

	@Override
	public void addToTable(Student student) {
		String sql = "INSERT INTO students (name) VALUES (?)";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

			statement.setString(1, student.getName());
			statement.executeUpdate();

			try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					student.setId(generatedKeys.getLong(1));
				}
			}

			for (Classes classes : student.getClassesList()) {
				addStudentClass(student.getId(), classes.getId());
			}

		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void addStudentClass(Long studentId, Long classId) {
		String sql = "INSERT INTO student_classes (student_id, class_id) VALUES (?, ?)";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, studentId);
			statement.setLong(2, classId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	

	private List<Classes> getClassesByStudentId(Long studentId) {
		List<Classes> classesSet = new ArrayList<>();
		String sql = "SELECT c.* FROM classes c INNER JOIN student_classes sc ON c.id = sc.class_id WHERE sc.student_id = ?";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, studentId);
			try (ResultSet resultSet = statement.executeQuery()) {
				while (resultSet.next()) {
					Classes classes = new Classes();
					classes.setId(resultSet.getLong("id"));
					classes.setName(resultSet.getString("name"));
					classes.setDescription(resultSet.getString("description"));
					classesSet.add(classes);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return classesSet;
	}

	@Override
	public Student getById(Long id) {
		

		Student student = null;
		String sql = "SELECT * FROM students WHERE id = ?";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, id);
			try (ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					student = new Student();
					student.setId(resultSet.getLong("id"));
					student.setName(resultSet.getString("name"));
					student.setClassesList(getClassesByStudentId(student.getId()));
				
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return student;
	}

	@Override
	public void update(Student student) {
		String sql = "UPDATE students SET name = ? WHERE id = ?";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setString(1, student.getName());
			statement.setLong(2, student.getId());
			statement.executeUpdate();

			deleteStudentClasses(student.getId());

			for (Classes classes : student.getClassesList()) {
				addStudentClass(student.getId(), classes.getId());
			}

	

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void deleteStudentClasses(Long studentId) {
		String sql = "DELETE FROM student_classes WHERE student_id = ?";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, studentId);
			statement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void delete(Long id) {
		deleteStudentClasses(id);

		String sql = "DELETE FROM students WHERE id = ?";

		try (Connection connection = DatabaseConnector.getConnection();
				PreparedStatement statement = connection.prepareStatement(sql)) {

			statement.setLong(1, id);
			statement.executeUpdate();
		

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	@Override
	public List<Student> getAllTable() {
		List<Student> studentsList = new ArrayList<>();
		String sql = "SELECT * FROM students";

		try (Connection connection = DatabaseConnector.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery(sql)) {

			while (resultSet.next()) {
				Student student = new Student();
				student.setId(resultSet.getLong("id"));
				student.setName(resultSet.getString("name"));
				student.setClassesList(getClassesByStudentId(student.getId()));
				studentsList.add(student);
							}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return studentsList;
	}
	

	 
	


	
}
