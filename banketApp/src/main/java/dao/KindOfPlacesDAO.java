package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import config.ConfigConnection;
import entities.KindOfPlaces;

public class KindOfPlacesDAO {
	
	public List<KindOfPlaces> selectAllKinds() {
		List<KindOfPlaces> kindsList = new ArrayList<>();
		ConfigConnection config = new ConfigConnection();
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection connection = config.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(config.getAllKinds());
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				KindOfPlaces kind = new KindOfPlaces();
				kind.setId(rs.getInt("id"));
				kind.setKind(rs.getString("kind"));
			
				kindsList.add(kind);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return kindsList;
	}
}
