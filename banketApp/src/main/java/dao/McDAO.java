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
import entities.Mc;

public class McDAO {

	public List<Mc> selectAllMc() {
		List<Mc> mcList = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getAllMc());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Mc mc = new Mc();
				mc.setId(rs.getInt("id"));
				mc.setFio(rs.getString("fio"));
				
				DateFormat df = new SimpleDateFormat("dd-MM-yyyy");
				String date = df.format(rs.getDate("birthday"));
				
				mc.setBirthday(date);
				mc.setMobile(rs.getString("mobile"));
				mc.setEmail(rs.getString("email"));
			
				mcList.add(mc);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return mcList;
	}
	
	public boolean checkMc(Mc mc) {
		
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.checkMc());
			preparedStatement.setString(1, mc.getFio());
			preparedStatement.setString(2, mc.getBirthday());
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	public boolean insertMc(Mc mc) throws SQLException {
		ConfigConnection config = new ConfigConnection();
		try {
			if (checkMc(mc)) {
				Connection connection = config.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(config.insertMc());
				preparedStatement.setString(1, mc.getFio());
				preparedStatement.setString(2, mc.getBirthday());
				preparedStatement.setString(3, mc.getMobile());
				preparedStatement.setString(4, mc.getEmail());
				preparedStatement.executeUpdate();
			} else {
				return false;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return true;
	}
	
	public boolean deleteMc(int id) throws SQLException {
		boolean rowDeleted;
		ConfigConnection config = new ConfigConnection();
		try (Connection connection = config.getConnection();
				PreparedStatement statement = connection.prepareStatement(config.deleteMc());) {
			statement.setInt(1, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public Mc selectMc(int id) {
		
		ConfigConnection config = new ConfigConnection();
		Mc mc = new Mc();
		try {
			Connection connection = config.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(config.getMcById());
			preparedStatement.setInt(1, id);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				mc.setId(rs.getInt("id"));
				mc.setFio(rs.getString("fio"));
				mc.setBirthday(rs.getString("birthday"));
				mc.setMobile(rs.getString("mobile"));
				mc.setEmail(rs.getString("email"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return mc;
	}
	
	public boolean updateMc(Mc mc) throws SQLException {
		boolean rowUpdated = false;
		ConfigConnection config = new ConfigConnection();
		try {
			Connection connection = config.getConnection();
		
			PreparedStatement statement = connection.prepareStatement(config.updateMc());
			statement.setString(1, mc.getFio());
			statement.setString(2, mc.getBirthday());
			statement.setString(3, mc.getMobile());
			statement.setString(4, mc.getEmail());
			statement.setInt(5, mc.getId());
			
			rowUpdated = statement.executeUpdate() > 0;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return rowUpdated;
	} 
}
