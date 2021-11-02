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
import entities.Client;

@WebServlet("/clients/*")
public class ClientServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ClientDAO clientDAO;

	@Override
	public void init() {
		clientDAO = new ClientDAO();
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
			case "/new_client/":
				showNewClientForm(request, response);
				break;
			case "/insert_client/":
				insertClient(request, response);
				break;
			case "/delete_client":
				deleteClient(request, response);
				break;
			case "/edit_client":
				showEditClientForm(request, response);
				break;
			case "/update_client/":
				updateClient(request, response);
				break;

			default:
				listClient(request, response);
				break;
			}
		} catch (Exception ex) {
			throw new ServletException(ex);
		}
	}
	
	private void listClient(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<Client> listClient = clientDAO.selectAllClients();
		request.setAttribute("listClient", listClient);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/clients.jsp");
		dispatcher.forward(request, response);
	}
	
	private void showNewClientForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/client_form.jsp");
		dispatcher.forward(request, response);
	}
	
	private void insertClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException, ServletException {
		
		Client newClient = new Client();
		newClient.setFio(new String(request.getParameter("fio").getBytes("ISO-8859-1"),"UTF-8"));
		newClient.setBirthday(request.getParameter("birthday"));
		newClient.setMobile(request.getParameter("mobile"));
		newClient.setEmail(request.getParameter("email"));
		
		boolean result = clientDAO.insertClient(newClient);
		
		if (result) {
			response.sendRedirect("/banketApp/clients/");
		} else {
			request.setAttribute("error","Клиент с такими данными уже добавлен в базу данных!");
			RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/client_form.jsp"); 
			dispatcher.forward(request, response);
		}
		
	}
	
	private void deleteClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		clientDAO.deleteClient(id);
		response.sendRedirect("/banketApp/clients/");
	}
	
	private void showEditClientForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Client existingClient = clientDAO.selectClient(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/views/client_form.jsp");
		request.setAttribute("client", existingClient);
		dispatcher.forward(request, response);		
	}
	
	private void updateClient(HttpServletRequest request, HttpServletResponse response) 
			throws SQLException, IOException {
		
		int id = Integer.parseInt(request.getParameter("id"));
		
		Client client = new Client();
		client.setId(id);
		client.setFio(new String(request.getParameter("fio").getBytes("ISO-8859-1"),"UTF-8"));
		client.setBirthday(request.getParameter("birthday"));
		client.setMobile(request.getParameter("mobile"));
		client.setEmail(request.getParameter("email"));
		
		clientDAO.updateClient(client);
		response.sendRedirect("/banketApp/clients/");
	}

}
