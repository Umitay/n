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
<ul class="media-list">
 <c:forEach items="${articles}" var="article">
  <li class="media">
    <div class="media-left">
      <a href="/article/${article.slug}">
        <img class="media-object" src="${article.thumbnailUrl}" >
      </a>
    </div>
    <div class="media-body">
      <h4 class="media-heading">${article.name}</h4>
     <p itemprop="summary">${article.about}</p>
    </div>
  </li>
  </c:forEach>
</ul>
</c:if>
</div>
<%@include file="bottom.jsp"%>