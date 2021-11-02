<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE></TITLE>
	<style>
		<%@ include file = "/WEB-INF/css/style.css" %>
	</style>
</HEAD>
<BODY>

	<div id="container">

		<div id="up_line" align="center">
			<caption>
				<h2>
					<c:if test="${client != null}">
            			Редактирование данных о клиенте
            		</c:if>
					<c:if test="${client == null}">
            			Добавление нового клиента
            		</c:if>
				</h2>
			</caption>
		</div>

		<div id="main">
			<%
				String error_msg=(String)request.getAttribute("error");  
				if(error_msg!=null)
				out.println("<font color=red size=6px>"+error_msg+"</font>");
			%>
			<c:if test="${client != null}">
				<form action="<%=request.getContextPath()%>/clients/update_client/"
					method="post">
			</c:if>
			<c:if test="${client == null}">
				<form action="<%=request.getContextPath()%>/clients/insert_client/"
					method="post">
			</c:if>
			<c:if test="${client != null}">
				<input type="hidden" name="id"
					value="<c:out value='${client.id}' />" />
			</c:if>
			<div class="form_info" id="form_info_client">
				<b>Клиент:</b>
				<p>
					<label>ФИО:</label> <input type="text"
						value="<c:out value='${client.fio}' />" name="fio" required>
				</p>
				<p>
					<label>Дата рождения:</label> <input type="date"
						value="<c:out value='${client.birthday}' />" name="birthday"
						required>
				</p>
				<p>
					<label>Контакты:</label> <input type="text"
						value="<c:out value='${client.mobile}' />" name="mobile"
						pattern="[+0-9]{13}" title="Пример: +375291234567" required>
				</p>
				<p>
					<label>E-mail:</label> <input type="text"
						value="<c:out value='${client.email}' />" name="email" required>
				</p>
			</div>
			<c:if test="${client != null}">
				<input id="add_button" type="submit" value="Сохранить изменения">
			</c:if>
			<c:if test="${client == null}">
				<input id="add_button" type="submit" value="Добавить клиента">
			</c:if>
			</form>
		</div>
	</div>

</BODY>

</HTML>