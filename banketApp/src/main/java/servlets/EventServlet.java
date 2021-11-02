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

import dao.EventDAO;
import entities.Event;

@WebServlet("/events/*")
public class EventServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private EventDAO eventDAO;

	@Override
	public void init() {
		eventDAO = new EventDAO();
	}  
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();
		try {
			switch (action) {
			case "/new_event/":
				showNewEventForm(request, response);
				break;
			case "/insert_event/":
				insertEvent(request, response);
				break;
			case "/delete_event":
				deleteEvent(request, response);
				break;
			case "/edit_event":
			showEditEventForm(request, response);
				break;
			case "/update_event/":
				updateEvent(request, response);
				break;
			default:
				listEvent(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listEvent(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Event> listEvent = eventDAO.selectAllEvents();
		request.setAttribute("listEvent", listEvent);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/events.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewEventForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/event_form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertEvent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		Event newEvent = new Event();
		newEvent.setType(new String(request.getParameter("type").getBytes("ISO-8859-1"),"UTF-8"));
		boolean result = eventDAO.insertEvent(newEvent);
		
		if (result) {
				response.sendRedirect("/banketApp/events/");
			} else {
				request.setAttribute("error","Мероприятие с таким названием уже добавлено в базу данных!");
				RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/event_form.jsp"); 
				dispatcher.forward(request, response);
			}
	}
	
	private void deleteEvent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		eventDAO.deleteEvent(id);
		response.sendRedirect("/banketApp/events/");
	}
	
	private void showEditEventForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Event existingEvent = eventDAO.selectEvent(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/event_form.jsp");
		request.setAttribute("event", existingEvent);
		dispatcher.forward(request, response);		
	}
	
	private void updateEvent(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Event event = new Event();
		event.setId(id);
		event.setType(new String(request.getParameter("type").getBytes("ISO-8859-1"),"UTF-8"));
		
		eventDAO.updateEvent(event);
		response.sendRedirect("/banketApp/events/");
	}

}
