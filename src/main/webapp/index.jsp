<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>
<div class="container">
	<div class="jumbotron">
		<h1>${category.name}</h1>
		 <c:if test="${empty unvisible}">
			<div class="panel">
			${category.ads_jumbotron}
			</div>
		</c:if>
		${category.description}
	</div>
	<%@include file="item_list.jsp"%>
	
	<c:if test="${empty unvisible}">
		<div class="panel">
			${category.ads_horizont1}
		</div>
	 </c:if>
</div>
<%@include file="bottom.jsp"%>