<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="n_header.jsp"%>
<div class="container">
	<c:if test="${!empty items}">
	 <c:forEach items="${items}" var="item">
		 <div class="thumbnail">
			<a href="/recipe/e/${item.slug}"> 
			 <img width="300" height="225" itemprop="image" src="${item.thumbnailUrl}">
			</a>
			<div class="caption">
				<h3 itemprop="name">
					<a href="/recipe/e/${item.slug}" itemprop="url">${item.name}</a>
				</h3>
			</div>
    	</div>
    </c:forEach>
	</c:if>
</div>
<%@include file="bottom.jsp"%>