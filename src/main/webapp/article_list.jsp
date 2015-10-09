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
			<section itemscope="" itemtype="http://schema.org/ItemList" style="margin-top: 20px;">
				<meta content=true itemprop="mainContentOfPage">
				<meta content="Descending" itemprop="itemListOrder">
				
				<c:forEach items="${articles}" var="article">
					<div itemprop="itemListElement" class="list-article-border pull-left" onclick="open_article('${article.slug}')">
						<c:if test="${!empty article.thumbnailUrl}">
					    <div class="pull-left">
					      <a href="/article/${article.slug}">
					        <img class="media-object" itemprop="image" src="${article.thumbnailUrl}" >
					      </a>
					    </div>
					    </c:if>
					    <div class="pull-left">
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