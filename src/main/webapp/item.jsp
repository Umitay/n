<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="item_header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-md-8">
			<div id="item" itemtype="http://schema.org/Recipe" itemscope="">
				<meta itemprop="dateModified" content="${item_dateModified}">
				<div class="page-header">
					<h1 itemprop="name">${item.name}</h1>
				</div>

				<!-- row -->
				<div class="row">
					<div class="col-xs-6 col-md-4">
						<div class="thumbnail" id="output_field"
							style="height: 180px; background-color: #F1FCEC;"
							itemprop="image">
							<img width="230" height="230" src="${item.thumbnailUrl}" />
						</div>
					</div>
					<div class="col-xs-6 col-md-8">
						<div itemprop="description">${item.about}</div>
						<div>
							Время приготовления:
							<time content="PT${item.totalTime} min" itemprop="totalTime">${item.totalTime}
								мин</time>
						</div>
						<c:if test="${!empty item.recipeYield}">
							<div>
								Количество порций: <span itemprop="recipeYield">${item.recipeYield}
									порции</span>
							</div>
						</c:if>
						<c:if test="${!empty item.nutrition}">
							<div itemprop="nutrition" itemscope
								itemtype="http://schema.org/NutritionInformation">
								Пищевая ценность: <span itemprop="calories">${item.nutrition}
									калорий</span>
							</div>
						</c:if>
						<c:if test="${!empty item_categories}">
							<div>
								Категорий:
								<c:forEach items="${item_categories}" var="category">
									<a href="http://www.ur-recipe.com/category/${category.slug}"
										itemprop="recipeCategory">${category.name}</a>&nbsp;
					</c:forEach>
							</div>
						</c:if>
						<div>
							Опубликовано:
							<time datetime="${item_datePublished}" itemprop="datePublished">${item_datePublished}</time>
						</div>
						<div itemprop="author" itemscope=""
							itemtype="http://schema.org/Person">
							Автор: <a href="https://plus.google.com/+Urrecipe1/posts"><span
								itemprop="name">Умитай Турдыкулова</span></a>
						</div>
					</div>
				</div>
				<!-- / row -->
				<%@include file="horizont_sosial_buttons.jsp"%>
				<div class="bg-warning ads"></div>
				<!--  row -->
				<div class="row">
					<div class="col-xs-6 col-md-8">
						<div>
							<h2>Как приготовить</h2>
							<div itemprop="recipeInstructions">${item.description}</div>
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
			<div class="fb-page" data-href="https://www.facebook.com/tut.recipe"
				data-small-header="false" data-adapt-container-width="true"
				data-hide-cover="false" data-show-facepile="false"
				data-show-posts="false">
				<div class="fb-xfbml-parse-ignore">
					<blockquote cite="https://www.facebook.com/tut.recipe">
						<a href="https://www.facebook.com/tut.recipe">Любимые рецепты</a>
					</blockquote>
				</div>
			</div>
			<!-- Поместите этот тег туда, где должна отображаться виджет. 
			<div class="g-page"
				data-href="//plus.google.com/u/0/102501925985129905212"
				data-rel="publisher"></div>-->
				
			<c:if test="${empty !unvisible}">
				<script async
					src="//pagead2.googlesyndication.com/pagead/js/adsbygoogle.js"></script>
				<!-- recipe-side -->
				<ins class="adsbygoogle" style="display: block"
					data-ad-client="ca-pub-2604632423420713" data-ad-slot="6080759413"
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
<%@include file="bottom.jsp"%>