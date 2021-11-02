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
			<h2 align="center">ТИПЫ МЕРОПРИЯТИЙ</h2>
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
				<a href="<%=request.getContextPath()%>/events/new_event/">
					<li class="main_menu_up" id="add_kindevent_li">Добавить тип
						мероприятия</li>
				</a>
			</div>

			<div id="main_field_kind_events">
				<table class="table">
					<thead>
						<tr>
							<th>Номер</th>
							<th>Тип мероприятия</th>
							<th>Действия</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="event" items="${listEvent}" varStatus="counter">
							<tr>
								<td align="center"><c:out value="${counter.count}" /></td>
								<td><c:out value="${event.type}" /></td>
								<td><a
									href="<%=request.getContextPath()%>/events/edit_event?id=<c:out value='${event.id}' />">Редактировать</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="<%=request.getContextPath()%>/events/delete_event?id=<c:out value='${event.id}' />">Удалить</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>

</BODY>

</HTML>