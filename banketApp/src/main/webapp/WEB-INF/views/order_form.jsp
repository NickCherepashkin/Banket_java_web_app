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
					<c:if test="${order != null}">
            			Редактирование заказа
            		</c:if>
					<c:if test="${order == null}">
            			Добавление нового заказа
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
			<c:if test="${order != null}">
				<form action="update_order" method="post">
			</c:if>
			<c:if test="${order == null}">
				<form action="insert_order" method="post">
			</c:if>
			<c:if test="${order != null}">
				<input type="hidden" name="id"
					value="<c:out value='${order.id}' />" />
			</c:if>
			<div class="form_info" id="form_info_order">
				<b>Информация о заказе:</b>
				<p>
					<label>Место:</label> <select name="selPlaceId">
						<c:forEach items="${listPlace}" var="place">
							<c:if test="${order != null}">
								<c:if test="${order.idPlace == place.id}">
									<option selected value="${place.id}">${place.place}</option>
								</c:if>
								<c:if test="${order.idPlace != place.id}">
									<option value="${place.id}">${place.place}</option>
								</c:if>
							</c:if>
							<c:if test="${order == null}">
								<option value="${place.id}">${place.place}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>Клиент:</label> <select name="selClientId">
						<c:forEach items="${listClient}" var="client">
							<c:if test="${order != null}">
								<c:if test="${order.idClient == client.id}">
									<option selected value="${client.id}">${client.fio}</option>
								</c:if>
								<c:if test="${order.idClient != client.id}">
									<option value="${client.id}">${client.fio}</option>
								</c:if>
							</c:if>
							<c:if test="${order == null}">
								<option value="${client.id}">${client.fio}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>Дата банкета:</label> <input type="date" name="date" required>
				</p>
				<p>
					<label>Ведущий:</label> <select name="selMcId">						
						<c:forEach items="${listMc}" var="mc">
							<c:if test="${order != null}">
								<c:if test="${order.idMc == mc.id}">
									<option selected value="${mc.id}">${mc.fio}</option>
								</c:if>
								<c:if test="${order.idMc != mc.id}">
									<option value="${mc.id}">${mc.fio}</option>
								</c:if>
							</c:if>
							<c:if test="${order == null}">
								<option value="${mc.id}">${mc.fio}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>Количество гостей:</label> <input type="number"
						value="<c:out value='${order.countGuests}' />" name="count"
						required>
				</p>
				<p>
					<label>Бюджет:</label> <input type="number"
						value="<c:out value='${order.budget}' />" name="budget" required>
				</p>
				<p>
					<label>Тип мероприятия</label> <select name="selTypeEventId">
						<c:forEach items="${listEvent}" var="event">
							<c:if test="${order != null}">
								<c:if test="${order.idEventType == event.id}">
									<option selected value="${event.id}">${event.type}</option>
								</c:if>
								<c:if test="${order.idEventType != event.id}">
									<option value="${event.id}">${event.type}</option>
								</c:if>
							</c:if>
							<c:if test="${order == null}">
								<option value="${event.id}">${event.type}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
			</div>
			<c:if test="${order != null}">
				<input id="add_button" type="submit" value="Сохранить изменения">
			</c:if>
			<c:if test="${order == null}">
				<input id="add_button" type="submit" value="Добавить заказ">
			</c:if>
			</form>
		</div>
	</div>

</BODY>

</HTML>