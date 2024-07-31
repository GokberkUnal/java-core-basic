package repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import config.DatabaseConnector;
import model.Teacher;

public class TeacherRepository implements RepositoryCommens<Teacher,Long> {

	@Override
	public void addToTable(Teacher teacher) {
		// TODO Auto-generated method stub
		 String sql = "INSERT INTO teachers (name, class_id) VALUES (?, ?)";

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setString(1, teacher.getName());
	            statement.setLong(2, teacher.getClassId());
	            statement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	}

	@Override
	public List<Teacher> getAllTable() {
		 List<Teacher> teachersList = new ArrayList<>();
	        String sql = "SELECT * FROM teachers";

	        try (Connection connection = DatabaseConnector.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {

	            while (resultSet.next()) {
	                Teacher teacher = new Teacher();
	                teacher.setId(resultSet.getLong("id"));
	                teacher.setName(resultSet.getString("name"));
	                teacher.setClassId(resultSet.getLong("class_id"));
	                teachersList.add(teacher);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return teachersList;
	}

	@Override
	public Teacher getById(Long id) {
		// TODO Auto-generated method stub
		   Teacher teacher = null;
	        String sql = "SELECT * FROM teachers WHERE id = ?";

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setLong(1, id);
	            try (ResultSet resultSet = statement.executeQuery()) {
	                if (resultSet.next()) {
	                    teacher = new Teacher();
	                    teacher.setId(resultSet.getLong("id"));
	                    teacher.setName(resultSet.getString("name"));
	                    teacher.setClassId(resultSet.getLong("class_id"));
	                }
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return teacher;
	
	}

	@Override
	public void update(Teacher teacher) {
		// TODO Auto-generated method stub
		String sql = "UPDATE teachers SET name = ?, class_id = ? WHERE id = ?";

        try (Connection connection = DatabaseConnector.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, teacher.getName());
            statement.setLong(2, teacher.getClassId());
            statement.setLong(3, teacher.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		  String sql = "DELETE FROM teachers WHERE id = ?";

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setLong(1, id);
	            statement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	
		
}
