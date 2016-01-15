<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<c:if test="${!empty items}">
		<section itemscope="" itemtype="http://schema.org/ItemList">
			<meta content="false" itemprop="mainContentOfPage">

			<h2 itemprop="name">Все рецепты</h2>
			<meta content="Descending" itemprop="itemListOrder">
			
				<c:forEach items="${items}"  varStatus="loop" var="item">
					<c:if test="${empty unvisible  && loop.index == 1} ">
						<c:choose>
						    <c:when test="${empty category.ads_horizont1}">
						    	<%@include file="/common/ad_horizont.jsp"%>
						    </c:when>
						    <c:otherwise>
						     ${category.ads_horizont1}
						    </c:otherwise>
						</c:choose>
				    </c:if>
					<div itemprop="itemListElement" class="item_list_element  col-xs-12 col-sm-6 col-md-6 col-lg-3">
						<div class="thumbnail">
							<c:if test="${!empty item.thumbnailUrl}">
							 <a href="${domain_url}recipe/${item.slug}"> <img alt="${item.alt}"
								width="300" height="225" itemprop="image"
								src="${item.thumbnailUrl}">
							 </a>
							 </c:if>
							<div class="caption">
								<h3 itemprop="name">
									<a href="${domain_url}recipe/${item.slug}" title="${item.link_title}"
										itemprop="url">${item.name}</a>
								</h3>
								<p itemprop="description">${item.about}</p>
							</div>
						</div>
					</div>
				</c:forEach>
		</section>
	</c:if>