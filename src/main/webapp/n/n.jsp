<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="n_header.jsp" %>
<div class="container">
	
	<c:if test="${!empty items}">
			<h2>Рецепты</h2>
			
			<!-- row of columns -->
			<div class="row">
				<c:forEach items="${items}" var="item" begin="0" end="3">
					<div class="col-sm-6 col-lg-3">
						<div class="thumbnail">
							<c:if test="${!empty item.thumbnailUrl}">
							 <a href="/recipe/e/${item.slug}"> 
							  <img alt="${item.alt}"
								width="300" height="225"  
								src="${item.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3>
									<a href="/recipe/e/${item.slug}">${item.name}</a>
								</h3>
								<p>Status: ${empty item.datePublished ? 'Still unpublished': 'Published' }, ${ item.active ?'Active': 'Still inactive' }</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--/ row of columns -->
			
			<!-- row of columns -->
			<div class="row">
				<c:forEach items="${items}" var="item"  begin="4" end="7">
					<div class="col-sm-6 col-lg-3">
						<div class="thumbnail">
							<c:if test="${!empty item.thumbnailUrl}">
							 <a href="/recipe/e/${item.slug}"> 
							  <img alt="${item.alt}"
								width="300" height="225"  
								src="${item.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3>
									<a href="/recipe/e/${item.slug}">${item.name}</a>
								</h3>
								<p>Status: ${empty item.datePublished ? 'Still unpublished': 'Published' }, ${ item.active ?'Active': 'Still inactive' }</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--/ row of columns -->
			<!-- row of columns -->
			<div class="row">
				<c:forEach items="${items}" var="item"  begin="8" end="11">
					<div class="col-sm-6 col-lg-3">
						<div class="thumbnail">
							<c:if test="${!empty item.thumbnailUrl}">
							 <a href="/recipe/e/${item.slug}"> 
							  <img alt="${item.alt}"
								width="300" height="225"  
								src="${item.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3>
									<a href="/recipe/e/${item.slug}">${item.name}</a>
								</h3>
								<p>Status: ${empty item.datePublished ? 'Still unpublished': 'Published' }, ${ item.active ?'Active': 'Still inactive' }</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--/ row of columns -->
			<!-- row of columns -->
			<div class="row">
				<c:forEach items="${items}" var="item" begin="12" end="15">
					<div class="col-sm-6 col-lg-3">
						<div class="thumbnail">
							<c:if test="${!empty item.thumbnailUrl}">
							 <a href="/recipe/e/${item.slug}"> 
							  <img alt="${item.alt}"
								width="300" height="225"  
								src="${item.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3>
									<a href="/recipe/e/${item.slug}">${item.name}</a>
								</h3>
								<p>Status: ${empty item.datePublished ? 'Still unpublished': 'Published' }, ${ item.active ?'Active': 'Still inactive' }</p>
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--/ row of columns -->
	</c:if>
</div>	<!--/.container -->
<%@include file="n_bottom.jsp" %>