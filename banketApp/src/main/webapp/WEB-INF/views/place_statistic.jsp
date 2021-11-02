<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="x" uri="http://java.sun.com/jsp/jstl/xml" %>
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
			<h2 align="center">СТАТИСТИКА ПО МЕСТАМ</h2>
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

			
			<div id="report">
				<table class="table">
					<thead>
						<tr>
							<th>Номер п/п</th>
							<th>Место</th>
							<th>Прибыль</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="place" items="${listStatistic}" varStatus="counter">
							<tr>
								<td align="center"><c:out value="${counter.count}" /></td>
								<td><c:out value="${place.titleStatistic}" /></td>
								<td nowrap><c:out value="${place.profit}" /></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
	</div>
</BODY>

</HTML>