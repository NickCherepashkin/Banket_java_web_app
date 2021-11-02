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
			<h2 align="center">ОТЧЕТ О ПРИБЫЛИ</h2>
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
			<form action="<%=request.getContextPath()%>/report/profit/" method="post">
				<div id="report">
					<label>Посчитать прибыль за период:</label> <label>c:</label> <input
						type="date" name="dateAfter" required> <label>по:</label> <input type="date" name="dateBefore" required>
						
					<input id="count_button" type="submit" value="Посчитать"><br/><br/>
					<label>С&nbsp;&nbsp;</label> <label><c:out value="${dateAfter}" /></label>&nbsp;&nbsp; 
					<label>по&nbsp;&nbsp;</label> <label><c:out value="${dateBefore}" /></label>&nbsp;&nbsp;
					<label>прибыль состаавляет:&nbsp;&nbsp;&nbsp;&nbsp;</label> <label><c:out value="${profit}" /></label>
					
				</div>
			</form>
		</div>
	</div>
</BODY>

</HTML>