<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/common/header.jsp"%>
<div class="container">
	<div class="jumbotron">
		<h1 itemprop="name">${category.name}</h1>
		<%-- <c:if test="${empty unvisible}">
			${category.ads_jumbotron}
		</c:if>--%>
		<div itemprop="description">${category.description}</div>  
	</div>
	<%@include file="/item/item_list.jsp"%>
	<%--
	<c:if test="${empty unvisible}">
			${category.ads_horizont1}
	 </c:if>--%>
</div>
<%@include file="/common/bottom.jsp"%>