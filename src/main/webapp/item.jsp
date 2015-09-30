<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@include file="header.jsp" %>
<div class="container">
	<div class="row">	
	<div class="col-xs-12 col-sm-8 col-md-8">
	 <div id="item" itemtype="http://schema.org/Recipe" itemscope="">
		<meta itemprop="recipeCategory" content="Овощи и гарниры">
		<meta itemprop="description" content="Легкое,но сытное блюдо из доступных продуктов.">
		<meta itemprop="dateModified" content="2015-07-1">
		<div class="page-header"><h1 itemprop="name">${item.name}</h1></div>
		
		<!-- row -->		 
		<div class="row">
		  <div class="col-xs-6 col-md-4">
		  <div class="thumbnail" id="output_field" style="height: 180px; background-color:#F1FCEC;" itemprop="image"> 
		     <img width="230" height="230" src="${item.thumbnailUrl}" />	</div>
		<script src="https://apis.google.com/js/plusone.js"></script>
		<g:plus action="share"></g:plus>
		  </div>
		  <div class="col-xs-6 col-md-8">
		 	<div>${item.about}</div>
		 	<c:if test="${!empty item_categories}">
			 	<div>Категорий: 
				 	<c:forEach items="${item_categories}" var="category">
				 		<a href="http://ur-recipe.com/category/${category.slug}">${category.name}</a>&nbsp;
					</c:forEach>
				</div>
			</c:if>
			<div>Опубликовано:  <time datetime="${item.datePublished}" itemprop="datePublished">${item.datePublished}</time></div>
		 	<div itemprop="author" itemscope="" itemtype="http://schema.org/Person">
		 	Автор: <a href="//plus.google.com/u/0/110204310495545191121"><span itemprop="name">Умитай Турдыкулова</span></a>
     		</div>
		 </div>
		</div>
		<!-- / row -->
		<div class="bg-warning ads"></div>
		<!--  row -->
		<div class="row">
				<div class="col-xs-6 col-md-8">
					<div>
						<h2>Как приготовить</h2>
						<div itemprop="recipeInstructions">
							${item.description}
						</div>
					</div>
				</div>
				  <div class="col-xs-6 col-md-4">
					<div itemprop="ingredients">
						<h2>Ингредиенты</h2>
						 ${item.ingredients}							     
					</div>
				</div>
		</div>
		<!-- / row -->
	</div>
	<!-- /itemscope  -->
	</div>
	<!-- /.col-xs-12 col-sm-8 col-md-8 -->
	<div class="col-sm-4 col-md-4">
	<c:if test="${empty unvisible}">
		<!-- Вставьте этот тег в заголовке страницы или непосредственно перед закрывающим тегом основной части. -->
	<script src="https://apis.google.com/js/platform.js" async defer>
	  {lang: 'ru'}
	</script>
	
	<!-- Поместите этот тег туда, где должна отображаться виджет. -->
	<div class="g-page" data-href="//plus.google.com/u/0/102501925985129905212" data-rel="publisher"></div>
	
	<script async src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
	<!-- recipe-side -->
	<ins class="adsbygoogle"
	     style="display:block"
	     data-ad-client="ca-pub-2604632423420713"
	     data-ad-slot="6080759413"
	     data-ad-format="auto"></ins>
	<script>
	(adsbygoogle = window.adsbygoogle || []).push({});
	</script>	
	 </c:if>
	</div>
	<!-- /.col-sm-4 col-md-4 -->
</div>	
<!-- /.row -->
</div>	
<!-- /.container -->
<%@include file="bottom.jsp" %>