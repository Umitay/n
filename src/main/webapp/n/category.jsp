<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>
<div class="container">
	<c:if test="${!empty items}">
	<!-- row of columns -->
	<div class="row">
	 <c:forEach items="${items}" var="item">
	 <div class="col-sm-6 col-lg-3">
		 <div class="thumbnail" style="${empty item.datePublished ? 'background-color:#e15d35;':''}">
			<a href="/n/item/e/${item.slug}"> 
			 <img width="300" height="225" itemprop="image" src="${item.thumbnailUrl}">
			</a>
			<div class="caption">
				<h3 itemprop="name">
					<a href="/n/item/e/${item.slug}" itemprop="url">${item.name}</a>
				</h3>
				<p> Status: ${empty item.datePublished ? 'Still unpublished': 'published' }, ${ item.active ?'active': 'Still inactive' }
			</div>
    	</div>
    	</div>
    </c:forEach>
    </div>
	</c:if>
</div>
<%@include file="bottom.jsp"%>