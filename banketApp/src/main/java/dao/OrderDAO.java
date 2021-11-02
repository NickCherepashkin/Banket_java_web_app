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
import entities.Order;

public class OrderDAO {
	
	public List<Order> selectAllOrders() {
		List<Order> ordersList = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getAllOrder());
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.setId(rs.getInt("id"));
				order.setPlace(rs.getString("place"));
				order.setFio(rs.getString("fio"));
				order.setMobile(rs.getString("mobile"));
				
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String date = df.format(rs.getDate("date"));
				
				order.setDate(date);
				order.setMc(rs.getString("mc"));
				order.setCountGuests(rs.getInt("countGuests"));
				order.setBudget(rs.getInt("budget"));
				order.setEventType(rs.getString("kind_of_event"));
				
				ordersList.add(order);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return ordersList;
	}
	
	public boolean insertOrder(Order order) throws SQLException {
		ConfigConnection config = new ConfigConnection();
		try {
			if (checkOrder(order)) {
				Connection connection = config.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(config.insertOrder());
				preparedStatement.setInt(1, order.getIdPlace());
				preparedStatement.setInt(2, order.getIdClient());
				preparedStatement.setInt(3, order.getIdMc());
				preparedStatement.setString(4, order.getDate());
				preparedStatement.setInt(5, order.getCountGuests());
				preparedStatement.setInt(6, order.getBudget());
				preparedStatement.setInt(7, order.getIdEventType());
				preparedStatement.executeUpdate();
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean checkOrder(Order order) {
		
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.checkOrder());
			preparedStatement.setInt(1, order.getIdClient());
			preparedStatement.setInt(2, order.getIdPlace());
			preparedStatement.setString(3, order.getDate());
			preparedStatement.setInt(4, order.getIdEventType());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean deleteOrder(int id) throws SQLException {
		boolean rowDeleted;
		ConfigConnection config = new ConfigConnection();
		try (Connection connection = config.getConnection();
				PreparedStatement statement = connection.prepareStatement(config.deleteOrder());) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Order selectOrder(int id) {
		
		ConfigConnection config = new ConfigConnection();
		Order order = new Order();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.getOrderById());
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				order.setId(rs.getInt("id"));
				order.setIdPlace(rs.getInt("place"));
				order.setIdClient(rs.getInt("client"));
				order.setIdMc(rs.getInt("mc"));
				
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String date = df.format(rs.getDate("date"));
				
				order.setDate(date);
				order.setCountGuests(rs.getInt("countGuests"));
				order.setBudget(rs.getInt("budget"));
				order.setIdEventType(rs.getInt("kind_of_event"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}
	
	public boolean updateOrder(Order order) throws SQLException {
		boolean rowUpdated = false;
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
		
			PreparedStatement statement = connection.prepareStatement(config.updateOrder());
			
			statement.setInt(1, order.getIdPlace());
			statement.setInt(2, order.getIdClient());
			statement.setInt(3, order.getIdMc());
			statement.setString(4, order.getDate());
			statement.setInt(5, order.getCountGuests());
			statement.setInt(6, order.getBudget());
			statement.setInt(7, order.getIdEventType());
			statement.setInt(8, order.getId());
			
			rowUpdated = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	}

}
