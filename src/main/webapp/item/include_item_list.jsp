<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${!empty items}">
		<section itemscope="" itemtype="http://schema.org/ItemList">
			<meta content=true itemprop="mainContentOfPage">

			<h2 itemprop="name">Полезные рецепты для гурманов</h2>
			<meta content="Descending" itemprop="itemListOrder">
			
			<!-- row of columns -->
			<div class="row">
				<c:forEach items="${items}" var="item" begin="0" end="3">
					<div itemprop="itemListElement" class="col-sm-6 col-lg-3">
						<div class="thumbnail">
							<c:if test="${!empty item.thumbnailUrl}">
							 <a href="/recipe/${item.slug}"> <img alt="${item.alt}"
								width="300" height="225" itemprop="image"
								src="${item.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3 itemprop="name">
									<a href="/recipe/${item.slug}" title="${item.link_title}"
										itemprop="url">${item.name}</a>
								</h3>
								
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--/ row of columns -->
			
			<!-- row of columns -->
			<div class="row">
				<c:forEach items="${items}" var="item"  begin="4" end="7">
					<div itemprop="itemListElement" class="col-sm-6 col-lg-3">
						<div class="thumbnail">
							<c:if test="${!empty item.thumbnailUrl}">
							 <a href="/recipe/${item.slug}"> <img alt="${item.alt}"
								width="300" height="225" itemprop="image"
								src="${item.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3 itemprop="name">
									<a href="/recipe/${item.slug}" title="${item.link_title}"
										itemprop="url">${item.name}</a>
								</h3>
							
							</div>
						</div>
					</div>
				</c:forEach>
			</div>
			<!--/ row of columns -->
		</section>
	</c:if>