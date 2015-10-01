<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="n_header.jsp" %>
<div class="container">
		   <c:forEach items="${articles}" var="article">
				<div class="thumbnail">
					<a href="/article/e/${article.slug}"> 
					 <img width="300" height="225" src="${article.thumbnailUrl}">
					</a>
					<div class="caption">
						<h3 >
							<a href="/article/e/${article.slug}" >${article.name}</a>
						</h3>
					</div>
	    		</div>
			</c:forEach>
</div>	<!--/.container -->
<%@include file="bottom.jsp" %>