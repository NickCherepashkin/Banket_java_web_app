package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ClientDAO;
import dao.EventDAO;
import dao.McDAO;
import dao.OrderDAO;
import dao.PlaceDAO;
import entities.Client;
import entities.Event;
import entities.Mc;
import entities.Order;
import entities.Place;

@WebServlet("/")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private OrderDAO orderDAO;
	private PlaceDAO placeDAO;
	private McDAO mcDAO;
	private EventDAO eventsDAO;
	private ClientDAO clientDAO;
	
	@Override
	public void init() {
		orderDAO = new OrderDAO();
		placeDAO = new PlaceDAO();
		mcDAO = new McDAO();
		eventsDAO = new EventDAO();
		clientDAO = new ClientDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getServletPath();
		try {
			switch (action) {
			case "/new_order":
				showNewOrderForm(request, response);
				break;
			case "/insert_order":
				insertOrder(request, response);
				break;
			case "/delete_order":
				deleteOrder(request, response);
				break;
			case "/edit":
				showEditOrderForm(request, response);
				break;
			case "/update_order":
				updateOrder(request, response);
				break;

			default:
				listOrder(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listOrder(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Order> listOrder = orderDAO.selectAllOrders();
		request.setAttribute("listOrder", listOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/order.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewOrderForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		List<Client> listClient = clientDAO.selectAllClients();
		request.setAttribute("listClient", listClient);
		List<Place> listPlace = placeDAO.selectAllPlaces();
		request.setAttribute("listPlace", listPlace);
		List<Mc> listMc = mcDAO.selectAllMc();
		request.setAttribute("listMc", listMc);
		List<Event> listEvent = eventsDAO.selectAllEvents();
		request.setAttribute("listEvent", listEvent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/order_form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		Order newOrder = new Order();
		newOrder.setIdPlace(Integer.valueOf(request.getParameter("selPlaceId")));
		newOrder.setIdClient(Integer.valueOf(request.getParameter("selClientId")));
		newOrder.setDate(request.getParameter("date"));
		newOrder.setIdMc(Integer.valueOf(request.getParameter("selMcId")));
		newOrder.setCountGuests(Integer.valueOf(request.getParameter("count")));
		newOrder.setBudget(Integer.valueOf(request.getParameter("budget")));
		newOrder.setIdEventType(Integer.valueOf(request.getParameter("selTypeEventId")));
		
		boolean result = orderDAO.insertOrder(newOrder);
		if (result) {
			response.sendRedirect("/banketApp/");
		} else {
			request.setAttribute("error","Заказ уже добавлен в базу данных!");
			List<Client> listClient = clientDAO.selectAllClients();
			request.setAttribute("listClient", listClient);
			List<Place> listPlace = placeDAO.selectAllPlaces();
			request.setAttribute("listPlace", listPlace);
			List<Mc> listMc = mcDAO.selectAllMc();
			request.setAttribute("listMc", listMc);
			List<Event> listEvent = eventsDAO.selectAllEvents();
			request.setAttribute("listEvent", listEvent);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/order_form.jsp"); 
			dispatcher.forward(request, response);
		}
		
	}
	
	private void deleteOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		orderDAO.deleteOrder(id);
		response.sendRedirect("/banketApp/");

	}
			
	private void showEditOrderForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		
		List<Client> listClient = clientDAO.selectAllClients();
		request.setAttribute("listClient", listClient);
		List<Place> listPlace = placeDAO.selectAllPlaces();
		request.setAttribute("listPlace", listPlace);
		List<Mc> listMc = mcDAO.selectAllMc();
		request.setAttribute("listMc", listMc);
		List<Event> listEvent = eventsDAO.selectAllEvents();
		request.setAttribute("listEvent", listEvent);
		
		Order existingOrder = orderDAO.selectOrder(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/order_form.jsp");
		request.setAttribute("order", existingOrder);
		dispatcher.forward(request, response);		
	}
	
	private void updateOrder(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		Order order = new Order();
		order.setId(id);
		order.setIdPlace(Integer.valueOf(request.getParameter("selPlaceId")));
		order.setIdClient(Integer.valueOf(request.getParameter("selClientId")));
		order.setDate(request.getParameter("date"));
		order.setIdMc(Integer.valueOf(request.getParameter("selMcId")));
		order.setCountGuests(Integer.valueOf(request.getParameter("count")));
		order.setBudget(Integer.valueOf(request.getParameter("budget")));
		order.setIdEventType(Integer.valueOf(request.getParameter("selTypeEventId")));
		
		orderDAO.updateOrder(order);
		response.sendRedirect("/banketApp/");
	}
}
