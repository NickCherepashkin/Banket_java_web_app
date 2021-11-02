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

import dao.McDAO;
import entities.Mc;

@WebServlet("/mc/*")
public class McServlet extends HttpServlet {
	private static final long serialVersionUID = 2L;

	McDAO mcDAO;

	public McServlet() {
		mcDAO = new McDAO();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getPathInfo();

		try {
			switch (action) {
			case "/new_mc/":
				showNewMcForm(request, response);
				break;
			case "/insert_mc/":
				insertMc(request, response);
				break;
			case "/delete_mc":
				deleteMc(request, response);
			break;
			case "/edit_mc":
				showEditMcForm(request, response);
				break;
			case "/update_mc/":
				updateMc(request, response);
				break;
			default:
				listMc(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listMc(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Mc> listMc = mcDAO.selectAllMc();
		request.setAttribute("listMc", listMc);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/mc.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewMcForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/mc_form.jsp");
		dispatcher.forward(request, response);
	}

	private void insertMc(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		Mc newMc = new Mc();
		newMc.setFio(new String(request.getParameter("fio").getBytes("ISO-8859-1"),"UTF-8"));
		newMc.setBirthday(request.getParameter("birthday"));
		newMc.setMobile(request.getParameter("mobile"));
		newMc.setEmail(request.getParameter("email"));
		
		boolean result = mcDAO.insertMc(newMc);
		
		if (result) {
			response.sendRedirect("/banketApp/mc/");
		} else {
			request.setAttribute("error","Ведущий с такими данными уже добавлен в базу данных!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/mc_form.jsp"); 
			dispatcher.forward(request, response);
		}
		
	}
	
	private void deleteMc(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		mcDAO.deleteMc(id);
		response.sendRedirect("/banketApp/mc/");
	}
	
	private void showEditMcForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Mc existingMc = mcDAO.selectMc(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/mc_form.jsp");
		request.setAttribute("mc", existingMc);
		dispatcher.forward(request, response);		
	}
	
	private void updateMc(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Mc mc = new Mc();
		mc.setId(id);
		mc.setFio(new String(request.getParameter("fio").getBytes("ISO-8859-1"),"UTF-8"));
		mc.setBirthday(request.getParameter("birthday"));
		mc.setMobile(request.getParameter("mobile"));
		mc.setEmail(request.getParameter("email"));
		
		mcDAO.updateMc(mc);
		response.sendRedirect("/banketApp/mc/");
	}

}
