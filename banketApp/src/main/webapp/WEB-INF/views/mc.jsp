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
			<h2 align="center">ВЕДУЩИЕ</h2>
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
				<a href="<%=request.getContextPath()%>/mc/new_mc/"><li
					class="main_menu_up" id="add_MC_li">Добавить ведущего</li></a>
			</div>

			<div id="main_field_mc">
				<table class="table">
					<thead>
						<tr>
							<th>Номер</th>
							<th>Фио</th>
							<th>Дата рождения</th>
							<th>Контакты</th>
							<th>E-mail</th>
							<th>Действия</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="mc" items="${listMc}" varStatus="counter">

							<tr>
								<td align="center"><c:out value="${counter.count}" /></td>
								<td><c:out value="${mc.fio}" /></td>
								<td><c:out value="${mc.birthday}" /></td>
								<td><c:out value="${mc.mobile}" /></td>
								<td><c:out value="${mc.email}" /></td>
								<td><a
									href="<%=request.getContextPath()%>/mc/edit_mc?id=<c:out value='${mc.id}' />">Редактировать</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a
									href="<%=request.getContextPath()%>/mc/delete_mc?id=<c:out value='${mc.id}' />">Удалить</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>

		</div>
	</div>
</BODY>

</HTML>