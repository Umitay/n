<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	<c:if test="${!empty articles}">
			<section itemscope="" itemtype="http://schema.org/ItemList" style="margin-top: 20px;">
				<meta content=true itemprop="mainContentOfPage">
				<meta content="Descending" itemprop="itemListOrder">
				
				<c:forEach items="${articles}" var="article" varStatus="loop" begin="0" end="2">
					 <a href="/article/${article.slug}">
						<div itemprop="itemListElement" class="thumbnail">
						   	<c:if test="${!empty article.thumbnailUrl}">
						        <img itemprop="image" class="img-responsive" src="${article.thumbnailUrl}" >
						    </c:if>
						    <div>
						     <h4  itemprop="name"> <a href="/article/${article.slug}" itemprop="url">${article.name}</a></h4>
						     <div id="description" itemprop="description">${article.about}</div>
						    </div>	
					 	</div>
					 </a>
	  			</c:forEach>
	  		</section>
	</c:if>