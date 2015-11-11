<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>
<div class="container">
		<h1> categories </h1>
		<c:if test="${empty unvisible}">
			<div class="panel">
				<script
					src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"
					async=""></script>
				<!-- jumbotron-recipe-hp-h1 -->
				<ins data-ad-format="auto" data-ad-slot="9173826618"
					data-ad-client="ca-pub-2604632423420713" style="display: block"
					class="adsbygoogle" data-adsbygoogle-status="done"></ins>
				<script>
					(adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
	    </c:if>
	    
	    <c:forEach items="${categories}" var="category" >
					<div itemprop="itemListElement" class="col-sm-6 col-lg-3" style="width:15%">
						<div class="thumbnail">
							<c:if test="${!empty category.thumbnailUrl}">
							 <a href="/category/${category.slug}"> <img alt="${category.alt}"
								width="300" height="225" itemprop="image"
								src="${category.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3 itemprop="name">
									<a href="/category/${category.slug}" title="${category.link_title}"
										itemprop="url">${category.name}</a>
								</h3>
								<p itemprop="description">${category.about}</p>
							</div>
						</div>
					</div>
				</c:forEach>
</div>
<%@include file="bottom.jsp"%>