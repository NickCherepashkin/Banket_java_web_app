package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import config.ConfigConnection;
import entities.Client;

public class ClientDAO {
	
	public List<Client> selectAllClients() {
		List<Client> clientsList = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getAllClients());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Client client = new Client();
				client.setId(rs.getInt("id"));
				client.setFio(rs.getString("fio"));
				
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String date = df.format(rs.getDate("birthday"));
				
				client.setBirthday(date);
				client.setMobile(rs.getString("mobile"));
				client.setEmail(rs.getString("email"));
			
				clientsList.add(client);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return clientsList;
	}
	
	public boolean insertClient(Client client) throws SQLException {
		ConfigConnection config = new ConfigConnection();
		try {
			if (checkClient(client)) {
				Connection connection = config.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(config.insertClient());
				preparedStatement.setString(1, client.getFio());
				preparedStatement.setString(2, client.getBirthday());
				preparedStatement.setString(3, client.getMobile());
				preparedStatement.setString(4, client.getEmail());
				preparedStatement.executeUpdate();
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean deleteClient(int id) throws SQLException {
		boolean rowDeleted;
		ConfigConnection config = new ConfigConnection();
		try (Connection connection = config.getConnection();
				PreparedStatement statement = connection.prepareStatement(config.deleteClient());) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Client selectClient(int id) {
		
		ConfigConnection config = new ConfigConnection();
		Client client = new Client();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.getClientById());
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				client.setId(rs.getInt("id"));
				client.setFio(rs.getString("fio"));
				client.setBirthday(rs.getString("birthday"));
				client.setMobile(rs.getString("mobile"));
				client.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return client;
	}
	
	public boolean checkClient(Client client) {
		
		ConfigConnection config = new ConfigConnection();
		boolean result = true;
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.checkClient());
			preparedStatement.setString(1, client.getFio());
			preparedStatement.setString(2, client.getBirthday());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				result = false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public boolean updateClient(Client client) throws SQLException {
		boolean rowUpdated = false;
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
		
			PreparedStatement statement = connection.prepareStatement(config.updateClient());
			statement.setString(1, client.getFio());
			statement.setString(2, client.getBirthday());
			statement.setString(3, client.getMobile());
			statement.setString(4, client.getEmail());
			statement.setInt(5, client.getId());

			rowUpdated = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}
}
