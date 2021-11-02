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
					<c:if test="${event != null}">
            			Редактирование типа мероприятия
            		</c:if>
					<c:if test="${event == null}">
            			Добавление нового типа мероприятия
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
		
			<c:if test="${event != null}">
				<form action="<%=request.getContextPath()%>/events/update_event/"
					method="post">
			</c:if>
			<c:if test="${event == null}">
				<form action="<%=request.getContextPath()%>/events/insert_event/"
					method="post">
			</c:if>

			<c:if test="${event != null}">
				<input type="hidden" name="id" value="<c:out value='${event.id}' />" />
			</c:if>
			<div class="form_info" id="form_info_kindevents">
				<b>Тип мероприятия:</b>
				<p>
					<label>Название:</label> <input type="text"
						value="<c:out value='${event.type}' />" name="type" required>
				</p>
			</div>
			<c:if test="${event != null}">
				<input id="add_button" type="submit" value="Сохранить изменения">
			</c:if>
			<c:if test="${event == null}">
				<input id="add_button" type="submit"
					value="Добавить тип мероприятия">
			</c:if>
			</form>
		</div>
	</div>

</BODY>

</HTML>