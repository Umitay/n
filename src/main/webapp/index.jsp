<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="header.jsp"%>
<div class="container">
	<div class="jumbotron">
		<h1>${category.name}</h1>
		 <c:if test="${empty unvisible}">
			<div class="panel">
				<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- recipes -->
				<ins class="adsbygoogle"
				     style="display:block"
				     data-ad-client="ca-pub-2604632423420713"
				     data-ad-slot="6041146216"
				     data-ad-format="auto"></ins>
				<script>
				(adsbygoogle = window.adsbygoogle || []).push({});
				</script>
			</div>
		</c:if>
		${category.description}
	</div>
	<%@include file="item_list.jsp"%>
</div>
<%@include file="bottom.jsp"%>