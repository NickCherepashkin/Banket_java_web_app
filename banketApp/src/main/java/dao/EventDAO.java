package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConfigConnection;
import entities.Event;

public class EventDAO {
	
	public List<Event> selectAllEvents() {
		List<Event> eventsList = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getAllEvents());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Event event = new Event();
				event.setId(rs.getInt("id"));
				event.setType(rs.getString("type"));
			
				eventsList.add(event);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return eventsList;
	}
	
	public boolean insertEvent(Event event) throws SQLException {
		ConfigConnection config = new ConfigConnection();
		try {
			if (checkEvent(event)) {
				Connection connection = config.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(config.insertEvent());
				preparedStatement.setString(1, event.getType());
				preparedStatement.executeUpdate();
			} else {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean deleteEvent(int id) throws SQLException {
		boolean rowDeleted;
		ConfigConnection config = new ConfigConnection();
		try (Connection connection = config.getConnection();
				PreparedStatement statement = connection.prepareStatement(config.deleteEvent());) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Event selectEvent(int id) {
		
		ConfigConnection config = new ConfigConnection();
		Event event = new Event();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.getEventById());
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				event.setId(rs.getInt("id"));
				event.setType(rs.getString("type"));
			}
			
			rs.close();
			preparedStatement.close();
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return event;
	}
	
	public boolean checkEvent(Event event) {
		
		ConfigConnection config = new ConfigConnection();
		boolean result = true;
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.getOneEvent());
			preparedStatement.setString(1, event.getType());

			ResultSet rs = preparedStatement.executeQuery();
		
			if (rs.next()) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateEvent(Event event) throws SQLException {
		boolean rowUpdated = false;
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
		
			PreparedStatement statement = connection.prepareStatement(config.updateEvent());
			statement.setString(1, event.getType());
			statement.setInt(2, event.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	} 
}
