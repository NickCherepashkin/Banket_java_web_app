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

import dao.KindOfPlacesDAO;
import dao.PlaceDAO;
import entities.KindOfPlaces;
import entities.Place;

@WebServlet("/places/*")
public class PlaceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private PlaceDAO placeDAO;
	
	@Override
	public void init() {
		placeDAO = new PlaceDAO();
	}

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/new_place/":
				showNewPlaceForm(request, response);
				break;
			case "/insert_place/":
				insertPlace(request, response);
				break;
			case "/delete_place":
				deletePlace(request, response);
				break;
			case "/edit_place":
				showEditPlaceForm(request, response);
				break;
			case "/update_place/":
				updatePlace(request, response);
				break;
			default:
				listPlace(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listPlace(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Place> listPlace = placeDAO.selectAllPlaces();
		request.setAttribute("listPlace", listPlace);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/places.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewPlaceForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		KindOfPlacesDAO kindsDAO = new KindOfPlacesDAO();
		List<KindOfPlaces> listKinds = kindsDAO.selectAllKinds();
		request.setAttribute("listKinds", listKinds);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/place_form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertPlace(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		Place newPlace = new Place();
		newPlace.setPlace(new String(request.getParameter("place").getBytes("ISO-8859-1"),"UTF-8"));
		newPlace.setAddress(new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8"));
		newPlace.setContacts(request.getParameter("contacts"));
		newPlace.setIdType(Integer.valueOf(request.getParameter("selPlaceId")));
		
		boolean result = placeDAO.insertPlace(newPlace);
		if (result) {
			response.sendRedirect("/banketApp/places/");
		} else {
			request.setAttribute("error","Введенное Вами место уже добавлено в базу данных!");
			KindOfPlacesDAO kindsDAO = new KindOfPlacesDAO();
			List<KindOfPlaces> listKinds = kindsDAO.selectAllKinds();
			request.setAttribute("listKinds", listKinds);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/place_form.jsp"); 
			dispatcher.forward(request, response);
		}
		
	}
	
	private void deletePlace(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		placeDAO.deletePlace(id);
		response.sendRedirect("/banketApp/places/");
	}
	
	private void showEditPlaceForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		KindOfPlacesDAO kindsDAO = new KindOfPlacesDAO();
		Place existingPlace = placeDAO.selectPlace(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/place_form.jsp");
		List<KindOfPlaces> listKinds = kindsDAO.selectAllKinds();
		request.setAttribute("listKinds", listKinds);
		request.setAttribute("place", existingPlace);
		request.setAttribute("selPlaceId", existingPlace.getIdType());
		dispatcher.forward(request, response);		
	}
	
	private void updatePlace(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.valueOf(request.getParameter("id"));
		
		Place place = new Place();
		place.setId(id);
		place.setPlace(new String(request.getParameter("place").getBytes("ISO-8859-1"),"UTF-8"));
		place.setAddress(new String(request.getParameter("address").getBytes("ISO-8859-1"),"UTF-8"));
		place.setContacts(request.getParameter("contacts"));
		place.setIdType(Integer.valueOf(request.getParameter("selPlaceId")));
		
		placeDAO.updatePlace(place);
		response.sendRedirect("/banketApp/places/");
	}
}
