package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConfigConnection;
import entities.Report;

public class ReportDAO {
	
	public int getProfit(String dateAfter, String dateBefore) {
		int profit = 0;
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getProfit(dateAfter, dateBefore));
			ResultSet rs = preparedStatement.executeQuery();
			
			if (rs.next()) {
				profit = rs.getInt(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return profit;
	}
	
	public List<Report> getEventsStatistic() {
		List<Report> events = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getEventsStatistic());
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Report event = new Report();
				event.setTitleStatistic(rs.getString("event"));
				event.setProfit(rs.getInt("profit"));
				
				events.add(event);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return events;
	}
	
	public List<Report> getPlacesStatistic() {
		List<Report> places = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getPlacesStatistic());
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				Report place = new Report();
				place.setTitleStatistic(rs.getString("place"));
				place.setProfit(rs.getInt("profit"));
				
				places.add(place);
			}
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		
		return places;
	}
}
