<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>
<div class="container">
	
		<h1>${article.name}</h1>
		${article.about}
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
	    
		${article.description}
	</div>

<%@include file="bottom.jsp"%>