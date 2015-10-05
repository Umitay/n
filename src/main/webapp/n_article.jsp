<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="n_header.jsp"%>
<div class="container">
	<c:if test="${!empty articles}">
	<!-- row of columns -->
	<div class="row">
	 <c:forEach items="${articles}" var="article">
	 <div class="col-sm-6 col-lg-3">
		 <div class="thumbnail" style="${empty article.datePublished ? 'background-color:#e15d35;':''}">
		
			<div class="caption">
				<h3 itemprop="name">
					<a href="/article/e/${article.slug}" itemprop="url">${article.name}</a>
				</h3>
				<p> Status: ${empty article.datePublished ? 'Still unpublished': 'published' }, ${ article.active ?'active': 'Still inactive' }
			</div>
    	</div>
    	</div>
    </c:forEach>
    </div>
	</c:if>
</div>
<%@include file="bottom.jsp"%>