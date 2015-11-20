<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>
<div class="container">
   <c:if test="${!empty category.description}">
	<div class="jumbotron">
		<h1>${category.name}</h1>
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
		${category.description}
	</div>
	 </c:if>
	<c:if test="${!empty articles}">
			<section itemscope="" itemtype="http://schema.org/ItemList" style="margin-top: 20px; float:left;" class="item-list">
				<meta content=true itemprop="mainContentOfPage">
				<meta content="Descending" itemprop="itemListOrder">
				
				<c:forEach items="${articles}" var="article">
					<div itemprop="itemListElement" class="article thumbnail col-xs-12 col-sm-6 col-md-6 col-lg-5-5" onclick="open_article('${article.slug}')">
					   <c:if test="${!empty article.thumbnailUrl}">
					    <div class="col-xs-12 col-sm-4 col-md-4 col-lg-4" >
					      <a href="/article/${article.slug}">
					        <img itemprop="image" class="img-responsive" src="${article.thumbnailUrl}" >
					      </a>
					    </div>
					    </c:if>
					    <div class="col-xs-12 col-sm-8 col-md-8 col-lg-8" >
					     <h4  itemprop="name"> <a href="/article/${article.slug}" itemprop="url">${article.name}</a></h4>
					     <div id="description" itemprop="description">${article.about}</div>
					    </div>
					 </div>
	  			</c:forEach>
	  		</section>
	</c:if>
</div>

<script type="text/javascript">
//<![CDATA[
function open_article(slug){
	location.href="/article/"+slug;
}
//]]>
</script> 
<%@include file="bottom.jsp"%>