<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml"%>
<!DOCTYPE html>
<HTML>
<HEAD>
<TITLE></TITLE>
	<style>
		<%@ include file = "/WEB-INF/css/style.css" %>
	</style>
</HEAD>
<BODY>

	<div id="overlay"></div>

	<div id="container">

		<div id="up_line">
			<h2 align="center">ЗАКАЗЫ</h2>
		</div>

		<div id="menu">
			<menu>
				<a href="<%=request.getContextPath()%>"><li>Журнал заказов</li></a>
				<li>Данные</li>
				<menu>
					<a href="<%=request.getContextPath()%>/clients/"><li class="second">Клиенты</li></a>
					<a href="<%=request.getContextPath()%>/places/"><li class="second">Места</li></a>
					<a href="<%=request.getContextPath()%>/events/"><li class="second">Типы мероприятия</li></a>
					<a href="<%=request.getContextPath()%>/mc/"><li class="second">Ведущие</li></a>
				</menu>
				<li>Отчеты</li>
				<menu>
					<a href="<%=request.getContextPath()%>/report/"><li class="second">Прибыль</li></a>
					<a href="<%=request.getContextPath()%>/report/event/"><li class="second">Статистика по мероприятиям</li></a>
					<a href="<%=request.getContextPath()%>/report/place/"><li class="second">Статистика по местам</li></a>
				</menu>
			</menu>
		</div>

		<div id="main">

			<div id="main_menu">
				<a href="<%=request.getContextPath()%>/new_order">
					<li class="main_menu_up" id="add_order_li">Добавить заказ</li>
				</a>
			</div>

			<div id="main_field_orders">
				<table class="table">
					<thead>
						<tr>
							<th>Номер</th>
							<th>Место</th>
							<th>ФИО Клиента</th>
							<th>Телефон Клиента</th>
							<th>Дата</th>
							<th>Ведущий</th>
							<th>Количество гостей</th>
							<th>Бюджет</th>
							<th>Тип мероприятия</th>
							<th>Действия</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach items="${listOrder}" var="order" varStatus="сounter">

							<tr>
								<td align="center"><c:out value="${сounter.count}" /></td>
								<td><c:out value="${order.place}" /></td>
								<td><c:out value="${order.fio}" /></td>
								<td nowrap><c:out value="${order.mobile}" /></td>
								<td nowrap><c:out value="${order.date}" /></td>
								<td><c:out value="${order.mc}" /></td>
								<td align="center"><c:out value="${order.countGuests}" /></td>
								<td align="center"><c:out value="${order.budget}" /></td>
								<td><c:out value="${order.eventType}" /></td>
								<td><a 
									href="<%=request.getContextPath()%>/edit?id=<c:out value='${order.id}' />">Редактировать</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="<%=request.getContextPath()%>/delete_order?id=<c:out value='${order.id}' />">Удалить</a></td>

							</tr>

						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>

</BODY>

</HTML>