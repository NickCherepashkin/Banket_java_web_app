<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
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
					<c:if test="${mc != null}">
            			Редактирование данных о ведущем
            		</c:if>
					<c:if test="${mc == null}">
            			Добавление нового ведущего
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
			<c:if test="${mc != null}">
				<form action="<%=request.getContextPath()%>/mc/update_mc/"
					method="post">
			</c:if>
			<c:if test="${mc == null}">
				<form action="<%=request.getContextPath()%>/mc/insert_mc/"
					method="post">
			</c:if>

			<c:if test="${mc != null}">
				<input type="hidden" name="id" value="<c:out value='${mc.id}' />" />
			</c:if>
			<div class="form_info" id="form_info_MC">
				<b>Ведущий:</b>
				<p>
					<label>ФИО:</label> <input type="text"
						value="<c:out value='${mc.fio}' />" name="fio" required>
				</p>
				<p>
					<label>Дата рождения:</label> <input type="date"
						value="<c:out value='${mc.birthday}' />" name="birthday" required>
				</p>
				<p>
					<label>Контакты:</label> <input type="text"
						value="<c:out value='${mc.mobile}' />" name="mobile"
						pattern="[+0-9]{13}" title="Пример: +375291234567" required>
				</p>
				<p>
					<label>E-mail:</label> <input type="text"
						value="<c:out value='${mc.email}' />" name="email" required>
				</p>
			</div>
			<c:if test="${mc != null}">
				<input id="add_button" type="submit" value="Сохранить изменения">
			</c:if>
			<c:if test="${mc == null}">
				<input id="add_button" type="submit" value="Добавить ведущего">
			</c:if>

			</form>
		</div>
	</div>

</BODY>

</HTML>