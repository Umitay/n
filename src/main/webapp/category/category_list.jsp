<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/common/header.jsp"%>
<div class="container">
		<h1> categories </h1>
		<c:if test="${empty unvisible}">
			<div class="panel">
			<c:if test="${empty unvisible}">
				${category.ads_jumbotron}
			</c:if>
			</div>
	    </c:if>
	    
	    <c:forEach items="${categories}" var="category" >
					<div itemprop="itemListElement" class="col-sm-6 col-lg-3" style="width:15%">
						<div class="thumbnail">
							<c:if test="${!empty category.thumbnailUrl}">
							 <a href="${domain_url}category/${category.slug}"> <img alt="${category.alt}"
								width="300" height="225" itemprop="image"
								src="${category.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3 itemprop="name">
									<a href="${domain_url}category/${category.slug}" title="${category.link_title}"
										itemprop="url">${category.name}</a>
								</h3>
								<p itemprop="description">${category.about}</p>
							</div>
						</div>
					</div>
				</c:forEach>
</div>
<%@include file="/common/bottom.jsp"%>