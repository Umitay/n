<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="article_header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-md-8">
		<div itemscope itemtype="http://schema.org/Article">
		<h1 itemprop="name">${article.name} <small> Автор: <a href="https://plus.google.com/+Urrecipe1/posts"><span
								itemprop="name">Умитай Турдыкулова</span></a></small></h1>
						

		<div class="row">
	<div class="col-xs-3 col-md-3">
		<!-- Your like button code -->
		<div class="fb-like"
			data-href="http://www.ur-recipe.com/article/${article.slug}"
			data-layout="button" data-action="like" data-show-faces="false">
		</div>
	</div>
	<div class="col-xs-3 col-md-3">

		<!-- Your share button code -->
		<div class="fb-share-button"
			data-href="http://www.ur-recipe.com/article/${article.slug}"
			data-layout="button"></div>
	</div>
	<div class="col-xs-3 col-md-3">
		<!-- Your send button code -->
		<div class="fb-send"
			data-href="http://www.ur-recipe.com/article/${article.slug}"
			data-layout="button"></div>
	</div>
</div>
		<div itemprop="description">${article.about}</div>
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
	    
		<div itemprop="articleBody"> ${article.description}</div>
		</div>
		</div>
		<!-- /.col-xs-12 col-sm-8 col-md-8 -->
		<div class="col-sm-4 col-md-4">
		<div class="fb-page" data-href="https://www.facebook.com/tut.recipe"
				data-small-header="false" data-adapt-container-width="true"
				data-hide-cover="false" data-show-facepile="false"
				data-show-posts="true">
				<div class="fb-xfbml-parse-ignore">
					<blockquote cite="https://www.facebook.com/tut.recipe">
						<a href="https://www.facebook.com/tut.recipe">Любимые рецепты</a>
					</blockquote>
				</div>
			</div>
			</div>
		<!-- /.col-sm-4 col-md-4 -->
		</div>
	</div>

<%@include file="bottom.jsp"%>