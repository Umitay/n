<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<script	src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.1/jquery.min.js"></script>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"	href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<link rel="stylesheet"	href="/static/styles.css">
<!-- Latest compiled and minified JavaScript -->
<script	src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<script src="//tinymce.cachefly.net/4.2/tinymce.min.js"></script>
</head>
<body>
<div class="container">
	<c:if test="${!empty articles}">
	<!-- row of columns -->
	<div class="row">
	 <c:forEach items="${articles}" var="article">
	 	<div><a href="/article/${article.slug}" itemprop="url"  class="${ article.active ?'text-success': '' }" >${article.name}</a></div>
    </c:forEach>
    </div>
	</c:if>
	<c:if test="${!empty items}">
	<!-- row of columns -->
	<div class="row">
	 <c:forEach items="${items}" var="item">
	 	<div>
	 		<p class="${ item.active ?'text-success': '' }">${item.name}</p>
		 	<p> FB: ${item.fb_share }</p>
		 	<p> VK: ${item.vk_share }</p>
		 	<p> LJ: ${item.lj_share }</p>
		 	<p> Twitter: ${item.twitter_share }</p>
	 	</div>
    </c:forEach>
    </div>
	</c:if>
</div>
<%@include file="bottom.jsp"%>