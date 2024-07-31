package repository;

import java.sql.Connection;

import model.Classes;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import config.DatabaseConnector;
public class ClassesRepository implements RepositoryCommens<Classes, Long> {
	
	
	
	 @Override
	    public void addToTable(Classes classes) {
	        String sql = "INSERT INTO classes (name, description) VALUES (?, ?)";

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setString(1, classes.getName());
	            statement.setString(2, classes.getDescription());
	            statement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public List<Classes> getAllTable() {
	        List<Classes> classesList = new ArrayList<>();
	        String sql = "SELECT * FROM classes";

	        try (Connection connection = DatabaseConnector.getConnection();
	             Statement statement = connection.createStatement();
	             ResultSet resultSet = statement.executeQuery(sql)) {

	            while (resultSet.next()) {
	                Classes classes = new Classes();
	                classes.setId(resultSet.getLong("id"));
	                classes.setName(resultSet.getString("name"));
	                classes.setDescription(resultSet.getString("description"));
	                classesList.add(classes);
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return classesList;
	    }

	    @Override
	    public Classes getById(Long id) {
	        Classes classes = null;
	        String sql = "SELECT * FROM classes WHERE id = ?";

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setLong(1, id);
	            ResultSet resultSet = statement.executeQuery();

	            if (resultSet.next()) {
	                classes = new Classes();
	                classes.setId(resultSet.getLong("id"));
	                classes.setName(resultSet.getString("name"));
	                classes.setDescription(resultSet.getString("description"));
	            }

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }

	        return classes;
	    }

	    @Override
	    public void update(Classes classes) {
	        String sql = "UPDATE classes SET name = ?, description = ? WHERE id = ?";

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setString(1, classes.getName());
	            statement.setString(2, classes.getDescription());
	            statement.setLong(3, classes.getId());
	            statement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

	    @Override
	    public void delete(Long id) {
	        String sql = "DELETE FROM classes WHERE id = ?";

	        try (Connection connection = DatabaseConnector.getConnection();
	             PreparedStatement statement = connection.prepareStatement(sql)) {

	            statement.setLong(1, id);
	            statement.executeUpdate();

	        } catch (SQLException e) {
	            e.printStackTrace();
	        }
	    }

		

}
