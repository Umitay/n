<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="item_header.jsp"%>

<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-12 col-md-8">
			<div id="item" itemtype="http://schema.org/Recipe" itemscope="">
				<meta itemprop="dateModified" content="${item_dateModified}">
				<div class="page-header">
					<h1 itemprop="name">${item.name}</h1>
				</div>

				<!-- row -->
				<div class="row">
					<div class="col-xs-12 col-md-4">
						<div class="thumbnail" id="output_field"
							style="height: 180px; background-color: #F1FCEC;" >
							<img width="230" height="230" src="${item.thumbnailUrl}"  itemprop="image"/>
						</div>
					</div>
					<div class="col-xs-12 col-md-8">
						<div itemprop="description"  style="margin-bottom: 10px;">
							${item.about}
						</div>
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
				<c:set var="share_url"
					value="http://www.ur-recipe.com/recipe/${item.slug}"
					scope="request" />
				<%@include file="/common/horizont_sosial_buttons.jsp"%>

				<div class="bg-warning ads">
					<c:if test="${empty unvisible }">
						<c:choose>
							<c:when test="${empty item.ads_horizont1}">
								<%@include file="/common/ad_horizont.jsp"%>
							</c:when>
							<c:otherwise>
						        ${item.ads_horizont1}
						    </c:otherwise>
						</c:choose>
					</c:if>
					<br>
				</div>
				<!--  row -->
				<div class="row">
					<div class="col-xs-12 col-md-8">
						<div>
							<h2>Как приготовить</h2>
							<div itemprop="recipeInstructions">${item.description}</div>
						</div>
					</div>
					<div class="col-xs-12 col-md-4">
						<div itemprop="ingredients">
							<h2>Ингредиенты</h2>
							${item.ingredients}
						</div>
					</div>
				</div>
				<!-- / row -->
				<hr>
				<div class="fb-comments" data-href="http://www.ur-recipe.com/recipe/${item.slug}" data-numposts="5"></div>
				
				<%@include file="include_item_list.jsp"%>

				<div class="bg-warning ads">
					<c:if test="${empty unvisible }">
						<c:choose>
							<c:when test="${empty item.ads_horizont2}">
								<%@include file="/common/ad_horizont.jsp"%>
							</c:when>
							<c:otherwise>
						        ${item.ads_horizont2}
						    </c:otherwise>
						</c:choose>
					</c:if>
				</div>
			</div>
			<!-- /itemscope  -->
		</div>
		<!-- /.col-xs-12 col-sm-8 col-md-8 -->
		<div class="col-xs-12 col-sm-12 col-md-4">
			<c:if test="${empty unvisible}">
				<c:choose>
					<c:when test="${empty item.ads_side1}">
						<%@include file="/common/ad_side.jsp"%>
					</c:when>
					<c:otherwise>
						        ${item.ads_side1}
						    </c:otherwise>
				</c:choose>
			</c:if>

			<%@include file="/article/article_side_list.jsp"%>

		</div>
		<!-- /.col-sm-4 col-md-4 -->
	</div>
	<!-- /.row -->
</div>
<!-- /.container -->
<%@include file="item_bottom.jsp"%>
