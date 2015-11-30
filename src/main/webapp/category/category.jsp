<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/common/header.jsp"%>
<div class="container">
	<div class="jumbotron">
		<h1>${category.name}</h1>
		<c:if test="${empty unvisible}">
				${category.ads_jumbotron}
	    </c:if>
		${category.description}
	</div>
    
	<%@include file="/item/item_list.jsp"%>
	
	<c:if test="${empty unvisible}">
			<c:choose>
		    <c:when test="${empty category.ads_horizont2}">
		    	<%@include file="/common/ad_horizont.jsp"%>
		    </c:when>
		    <c:otherwise>
		     ${category.ads_horizont2}
		    </c:otherwise>
		</c:choose>
    </c:if>
    
</div>
<%@include file="/common/bottom.jsp"%>