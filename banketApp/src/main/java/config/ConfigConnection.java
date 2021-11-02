package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConfigConnection {
	public String HOST = "https://localhost/";

	private String DB_PATH_CONNECTION = "jdbc:mysql://localhost:3306/";
	private String DB_NAME = "banket";
	private String DB_LOGIN = "root";
	private String DB_PASS = "root";
	
	public Connection getConnection() {
		Connection connection = null;
		
		try {
			connection = DriverManager.getConnection(DB_PATH_CONNECTION + DB_NAME, DB_LOGIN, DB_PASS);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return connection;
	}
	
	public String getProfit(String dateAfter, String dateBefore) {
		String SQL_PROFIT = "select sum(budget) from banket.order where date >= '" + dateAfter + "' AND date <= '" + dateBefore + "';"; 
		return SQL_PROFIT;
	} 
	
	public String getEventsStatistic() {
		String SQL_EVENTS_STATISTIC = "select sum(banket.order.budget) AS 'profit', kind_of_event.kind_of_event AS 'event' from banket.order, "
				+ "kind_of_event where order.kind_of_event = kind_of_event.id_kind_of_event Group By banket.order.kind_of_event ORDER BY profit DESC;"; 
		return SQL_EVENTS_STATISTIC;
	}
	
	public String getPlacesStatistic() {
		String SQL_PLACES_STATISTIC = "select sum(banket.order.budget) AS 'profit', place.place AS 'place' from banket.order, "
				+ "place where order.place = place.id_place Group By banket.order.place ORDER BY profit DESC;"; 
		return SQL_PLACES_STATISTIC;
	}
	
	public String getAllEvents() {
		String SELECT_ALL_EVENTS = "SELECT id_kind_of_event AS 'id', kind_of_event AS 'type' FROM kind_of_event;";
		return SELECT_ALL_EVENTS;
	}
	
	public String insertEvent() {
		String INSERT_EVENT_SQL = "INSERT INTO kind_of_event (kind_of_event) VALUES (?);";
		return INSERT_EVENT_SQL;
	}
	
	public String deleteEvent() {
		String DELETE_EVENT_SQL = "delete from kind_of_event where id_kind_of_event = ?;";
		return DELETE_EVENT_SQL;
	}
	
	public String getEventById() {
		String SELECT_EVENT_BY_ID = "SELECT id_kind_of_event AS 'id', kind_of_event AS 'type' FROM kind_of_event where id_kind_of_event = ?";
		return SELECT_EVENT_BY_ID;
	}
	
	public String updateEvent() {
		String UPDATE_EVENT_SQL = "update kind_of_event set kind_of_event = ? where id_kind_of_event = ?;";
		return UPDATE_EVENT_SQL;
	}
	
	public String getOneEvent() {
		String SELECT_ONE_EVENT = "SELECT kind_of_event AS 'type' FROM kind_of_event where kind_of_event=?";
		return SELECT_ONE_EVENT;
	}
	
	public String getAllClients() {
		String SELECT_ALL_CLIENTS = "SELECT id_client AS 'id', fio, birthday, mobile, email FROM client;";
		return SELECT_ALL_CLIENTS;
	}
	
	public String insertClient() {
		String INSERT_CLIENT_SQL = "INSERT INTO client (fio, birthday, mobile, email) VALUES (?, ?, ?, ?);";
		return INSERT_CLIENT_SQL;
	}
	
	public String deleteClient() {
		String DELETE_CLIENT_SQL = "delete from client where id_client = ?;";
		return DELETE_CLIENT_SQL;
	}
	
	public String getClientById() {
		String SELECT_CLIENT_BY_ID = "SELECT id_client AS 'id', fio, birthday, mobile, email FROM client where id_client =?";
		return SELECT_CLIENT_BY_ID;
	}
	
	public String checkClient() {
		String CHECK_CLIENT = "SELECT fio, birthday FROM client where fio = ? AND birthday = ?";
		return CHECK_CLIENT;
	}
	
	public String updateClient() {
		String UPDATE_CLIENT_SQL = "update client set fio = ?, birthday= ?, mobile =?, email =?  where id_client = ?;";
		return UPDATE_CLIENT_SQL;
	}
	
	public String getAllMc() {
		String SELECT_ALL_MC = "SELECT id_mc AS 'id', fio, birthday, mobile, email FROM mc;";
		return SELECT_ALL_MC;
	}
	
	public String insertMc() {
		String INSERT_MC_SQL = "INSERT INTO mc (fio, birthday, mobile, email) VALUES (?, ?, ?, ?);";
		return INSERT_MC_SQL;
	}
	
	public String deleteMc() {
		String DELETE_MC_SQL = "delete from mc where id_mc = ?;";
		return DELETE_MC_SQL;
	}
	
	public String getMcById() {
		String SELECT_MC_BY_ID = "SELECT id_mc AS 'id', fio, birthday, mobile, email FROM mc where id_mc =?";
		return SELECT_MC_BY_ID;
	}
	
	public String checkMc() {
		String CHECK_MC = "SELECT fio, birthday FROM mc where fio = ? AND birthday = ?";
		return CHECK_MC;
	}
	
	public String updateMc() {
		String UPDATE_MC_SQL = "update mc set fio = ?, birthday= ?, mobile =?, email =?  where id_mc = ?;";
		return UPDATE_MC_SQL;
	}

	public String getAllOrder() {
		String SELECT_ALL_ORDER = "select order.id_order AS 'id', place.place AS 'place', client.fio, client.mobile, order.date, mc.fio AS 'mc', order.guests AS 'countGuests', order.budget, "
				+ "kind_of_event.kind_of_event from banket.order, banket.client, place, mc, kind_of_event where place.id_place=order.place AND client.id_client=order.client AND "
				+ "mc.id_mc=order.mc AND kind_of_event.id_kind_of_event=order.kind_of_event;";
		return SELECT_ALL_ORDER;
	}
	
	public String insertOrder() {
		String INSERT_ORDER_SQL = "INSERT INTO banket.order (place, client, mc, date, guests, budget, kind_of_event) VALUES (?, ?, ?, ?, ?, ?, ?);";
		return INSERT_ORDER_SQL;
	}
	
	public String deleteOrder() {
		String DELETE_ORDER_SQL = "delete from banket.order where id_order = ?;";
		return DELETE_ORDER_SQL;
	}
	
	public String getOrderById() {
		String SELECT_ORDER_BY_ID = "select order.id_order AS 'id', place, client, mc, order.date, order.guests AS 'countGuests', order.budget, "
				+ "order.kind_of_event from banket.order where order.id_order =?";
		return SELECT_ORDER_BY_ID;
	}
	
	public String checkOrder() {
		String CHECK_ORDER = "select place, client, order.date, "
				+ "order.kind_of_event from banket.order where client = ? AND place = ? AND date = ? AND kind_of_event = ?";
		return CHECK_ORDER;
	}
	
	public String updateOrder() {
		String UPDATE_ORDER_SQL = "update banket.order set place = ?, client= ?, mc = ?, date = ?, guests = ?, budget = ?, kind_of_event = ? where id_order = ?;";
		return UPDATE_ORDER_SQL;
	}
	
	public String getAllPlaces() {
		String SELECT_ALL_PLACES = "SELECT id_place AS 'id', place, kind_of_place.kind_of_place AS 'type', address, contacts FROM place, kind_of_place "
				+ "WHERE place.kind_of_place=kind_of_place.id_kind_of_place;";
		return SELECT_ALL_PLACES;
	}
	
	public String insertPlace() {
		String INSERT_PLACE_SQL = "INSERT INTO place (place, address, contacts, kind_of_place) VALUES (?, ?, ?, ?);";
		return INSERT_PLACE_SQL;
	}
	
	public String deletePlace() {
		String DELETE_PLACE_SQL = "delete from place where id_place = ?;";
		return DELETE_PLACE_SQL;
	}
	
	public String getPlaceById() {
		String SELECT_PLACE_BY_ID = "SELECT id_place AS 'id', place, address, contacts, kind_of_place as 'idType' FROM place where id_place =?;";
		return SELECT_PLACE_BY_ID;
	}
	
	public String checkPlace() {
		String CHECK_PLACE = "SELECT place, address FROM place where place = ? AND address = ?;";
		return CHECK_PLACE;
	}
	
	public String updatePlace() {
		String UPDATE_PLACE_SQL = "update place set place = ?, address= ?, contacts =?, kind_of_place =?  where id_place = ?;";
		return UPDATE_PLACE_SQL;
	}
	
	public String getAllKinds() {
		String SELECT_ALL_KINDS = "SELECT id_kind_of_place AS 'id', kind_of_place AS 'kind' FROM kind_of_place;";
		return SELECT_ALL_KINDS;
	}
}