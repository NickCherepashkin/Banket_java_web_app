
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConfigConnection;
import entities.Place;

public class PlaceDAO {

	public List<Place> selectAllPlaces() {
		List<Place> placesList = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getAllPlaces());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Place place = new Place();
				place.setId(rs.getInt("id"));
				place.setPlace(rs.getString("place"));
				place.setType(rs.getString("type"));				
				place.setAddress(rs.getString("address"));
				place.setContacts(rs.getString("contacts"));
			
				placesList.add(place);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return placesList;
	}
	
	public boolean insertPlace(Place place) throws SQLException {
		ConfigConnection config = new ConfigConnection();
		try {
			if (checkPlace(place)) {
				Connection connection = config.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(config.insertPlace());
				preparedStatement.setString(1, place.getPlace());
				preparedStatement.setString(2, place.getAddress());
				preparedStatement.setString(3, place.getContacts());
				preparedStatement.setInt(4, place.getIdType());
				
				preparedStatement.executeUpdate();
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean checkPlace(Place place) {
		
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.checkPlace());
			preparedStatement.setString(1, place.getPlace());
			preparedStatement.setString(2, place.getAddress());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean deletePlace(int id) throws SQLException {
		boolean rowDeleted;
		ConfigConnection config = new ConfigConnection();
		try (Connection connection = config.getConnection();
				PreparedStatement statement = connection.prepareStatement(config.deletePlace());) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Place selectPlace(int id) {
		
		ConfigConnection config = new ConfigConnection();
		Place place = new Place();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.getPlaceById());
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				place.setId(rs.getInt("id"));
				place.setPlace(rs.getString("place"));
				place.setAddress(rs.getString("address"));
				place.setContacts(rs.getString("contacts"));
				place.setIdType(rs.getInt("idType"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return place;
	}
	
	public boolean updatePlace(Place place) throws SQLException {
		boolean rowUpdated = false;
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
		
			PreparedStatement statement = connection.prepareStatement(config.updatePlace());
			
			statement.setString(1, place.getPlace());
			statement.setString(2, place.getAddress());
			statement.setString(3, place.getContacts());
			statement.setInt(4, place.getIdType());
			statement.setInt(5, place.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	} 
}
