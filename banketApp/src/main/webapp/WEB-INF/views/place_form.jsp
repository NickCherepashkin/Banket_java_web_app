<%@ page language='java' contentType='text/html; charset=UTF-8'
	pageEncoding='UTF-8'%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<HTML>
<HEAD>
<meta http-equiv='Content-Type' content='text/html; charset=UTF-8' />
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
					<c:if test="${place != null}">
            			Редактирование данных о месте
            		</c:if>
					<c:if test="${place == null}">
            			Добавление нового места
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
			<c:if test="${place != null}">
				<form action="<%=request.getContextPath()%>/places/update_place/"
					method="post">
			</c:if>
			<c:if test="${place == null}">
				<form action="<%=request.getContextPath()%>/places/insert_place/"
					method="post">
			</c:if>
			<c:if test="${place != null}">
				<input type="hidden" name="id" value="<c:out value='${place.id}' />" />
			</c:if>
			<div class="form_info" id="form_info_place">
				<b>Место:</b>
				<p>
					<label>Название:</label> <input type="text"
						value="<c:out value='${place.place}' />" name="place" required>
				</p>
				<p>
					<label>Тип заведения:</label> <select name="selPlaceId">
						<c:forEach items="${listKinds}" var="kind">
							<c:if test="${place != null}">
								<c:if test="${place.idType == kind.id}">
									<option selected value="${kind.id}">${kind.kind}</option>
								</c:if>
								<c:if test="${place.idType != kind.id}">
									<option value="${kind.id}">${kind.kind}</option>
								</c:if>
							</c:if>
							<c:if test="${place == null}">
								<option value="${kind.id}">${kind.kind}</option>
							</c:if>
						</c:forEach>
					</select>
				</p>
				<p>
					<label>Адрес:</label> <input type="text"
						value="<c:out value='${place.address}' />" name="address" required>
				</p>
				<p>
					<label>Контакты:</label> <input type="text"
						value="<c:out value='${place.contacts}' />" name="contacts"
						required>
				</p>
			</div>
			<c:if test="${place != null}">
				<input id="add_button" type="submit" value="Сохранить изменения">
			</c:if>
			<c:if test="${place == null}">
				<input id="add_button" type="submit" value="Добавить место">
			</c:if>
			</form>
		</div>
	</div>

</BODY>

</HTML>