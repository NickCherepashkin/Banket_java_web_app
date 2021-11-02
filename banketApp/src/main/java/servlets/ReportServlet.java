package servlets;

import java.io.IOException;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.ReportDAO;
import entities.Report;

@WebServlet("/report/*")
public class ReportServlet extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	private ReportDAO reportDAO;
	
	@Override
	public void init() {
		reportDAO = new ReportDAO();
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/event/":
				eventStatistic(request, response);
				break;
			case "/place/":
				placeStatistic(request, response);
				break;
			case "/profit/":
				getProfit(request, response);
				break;
//			case "/edit_place":
//				showEditPlaceForm(request, response);
//				break;
//			case "/update_place/":
//				updatePlace(request, response);
//				break;
			default:
				showReportPage(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void showReportPage(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/profit.jsp");
		dispatcher.forward(request, response);
	}
	
	private void getProfit(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		int profit = reportDAO.getProfit(request.getParameter("dateAfter"), request.getParameter("dateBefore")); 
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");	
		Date dateBefore, dateAfter;
		try {
			dateBefore = formatter.parse(request.getParameter("dateBefore"));
			dateAfter = formatter.parse(request.getParameter("dateAfter"));
			
			DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
			String dateBeforeStr = df.format(dateBefore);
			String dateAfterStr = df.format(dateAfter);
			
			request.setAttribute("profit", profit);
			request.setAttribute("dateAfter", dateAfterStr);
			request.setAttribute("dateBefore", dateBeforeStr);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/profit.jsp");
			dispatcher.forward(request, response);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	private void eventStatistic(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Report> events = reportDAO.getEventsStatistic();
		request.setAttribute("listStatistic", events);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/event_statistic.jsp");
		dispatcher.forward(request, response);
	}
	
	private void placeStatistic(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Report> places = reportDAO.getPlacesStatistic();
		request.setAttribute("listStatistic", places);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/place_statistic.jsp");
		dispatcher.forward(request, response);
	}
}
