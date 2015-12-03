<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="header.jsp" %>
<div class="container">
	<c:if test="${!empty items}">
		<c:forEach items="${items}" var="item">
			<div class="col-sm-6 col-lg-3">
				<div class="thumbnail">
					<c:if test="${!empty item.thumbnailUrl}">
					 <a href="/n/item/e/${item.slug}"> 
					  <img alt="${item.alt}"
						width="300" height="225"  
						src="${item.thumbnailUrl}">
					 </a>
					 </c:if>
					<div class="caption">
						<h3>
							<a href="/n/item/e/${item.slug}">${item.name}</a>
						</h3>
						<p>Status: ${empty item.datePublished ? 'Still unpublished': 'Published' }, ${ item.active ?'Active': 'Still inactive' }</p>
					</div>
				</div>
			</div>
		</c:forEach>
	</c:if>
</div>	<!--/.container -->
<%@include file="bottom.jsp" %>