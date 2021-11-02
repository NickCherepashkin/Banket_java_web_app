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

	<div id="overlay"></div>
	<div id="container">

		<div id="up_line">
			<h2 align="center">КЛИЕНТЫ</h2>
		</div>

		<div id="menu">
			<menu>
				<a href="<%=request.getContextPath()%>" id="menu_orders"><li>Журнал
						заказов</li></a>
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
				<a href="<%=request.getContextPath()%>/clients/new_client/">
					<li class="main_menu_up" id="add_client_li">Добавить клиента</li>
				</a>
			</div>

			<div id="main_field_clients">
				<table class="table">
					<thead>
						<tr>
							<th>Номер</th>
							<th>ФИО Клиента</th>
							<th>Дата рождения</th>
							<th>Телефон Клиента</th>
							<th>E-mail клиента</th>
							<th>Действия</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="client" items="${listClient}" varStatus="counter">
							<tr>
								<td align="center"><c:out value="${counter.count}" /></td>
								<td><c:out value="${client.fio}" /></td>
								<td nowrap><c:out value="${client.birthday}" /></td>
								<td nowrap><c:out value="${client.mobile}" /></td>
								<td><c:out value="${client.email}" /></td>
								<td><a
									href="<%=request.getContextPath()%>/clients/edit_client?id=<c:out value='${client.id}' />">Редактировать</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="<%=request.getContextPath()%>/clients/delete_client?id=<c:out value='${client.id}' />">Удалить</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>

</BODY>

</HTML>