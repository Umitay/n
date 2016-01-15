<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="/common/header.jsp"%>
<div class="container">
   <c:if test="${!empty category.description}">
	<div class="jumbotron">
		<h1>${category.name}</h1>
		<c:if test="${empty unvisible}">
			<%@include file="/common/ad_horizont.jsp"%>
	    </c:if>
		${category.description}
	</div>
	 </c:if>
	<c:if test="${!empty articles}">
			<section itemscope="" itemtype="http://schema.org/ItemList" style="margin-top: 20px; float:left;" class="item-list">
				<meta content=true itemprop="mainContentOfPage">
				<meta content="Descending" itemprop="itemListOrder">
				
				<c:forEach items="${articles}" var="article">
					<div itemprop="itemListElement" class="article thumbnail col-xs-12 col-sm-6 col-md-6 col-lg-5-5">
					   <c:if test="${!empty article.thumbnailUrl}">
					    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4" >
					      <a href="${domain_url}article/${article.slug}" title="Интересно и Полезно - ${article.name}">
					        <img itemprop="image" class="img-responsive" src="${article.thumbnailUrl}" >
					      </a>
					    </div>
					    </c:if>
					    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8" >
					     <h4  itemprop="name"> <a href="${domain_url}article/${article.slug}" itemprop="url" title="Полезный совет ${article.name}">${article.name}</a></h4>
					     <div id="description" itemprop="description">${article.about}</div>
					    </div>
					 </div>
	  			</c:forEach>
	  		</section>
	</c:if>
</div>

<script type="text/javascript">
//<![CDATA[
function opearticle(slug){
	location.href="/article/"+slug;
}
//]]>
</script> 
<%@include file="/common/bottom.jsp"%>