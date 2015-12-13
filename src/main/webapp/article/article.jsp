<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@include file="article_header.jsp"%>
<div class="container">
	<div class="row">
		<div class="col-xs-12 col-sm-8 col-md-8">
		<div itemscope itemtype="http://schema.org/Article">
		<meta itemprop="dateModified" content="${article_dateModified}">
		<h1 itemprop="name">${article.name} </h1>
		<div itemprop="author" itemscope=""  itemtype="http://schema.org/Person">
			 Автор: <a href="https://plus.google.com/+Urrecipe1/posts"><span itemprop="name">Умитай Турдыкулова</span></a> 
		 </div>
		<div> Опубликовано: <time datetime="${article_datePublished}" itemprop="datePublished">${article_datePublished}</time></div>
		<c:if test="${empty article.thumbnailUrl}">
		<div class="thumbnail" id="output_field" >
			<img  src="${article.thumbnailUrl}"  itemprop="image"/>
		</div>
		</c:if>
		<div itemprop="description">${article.about}</div>
		
		<c:set var="share_url" value="http://www.ur-recipe.com/article/${article.slug}" scope="request"/>
		<%@include file="/common/horizont_sosial_buttons.jsp"%>
		
		<div class="bg-warning ads">
				<c:if test="${empty unvisible}">
				<c:choose>
				 	<c:when test="${empty article.ads_horizont1}">
				    	<%@include file="/common/ad_horizont.jsp"%>
				    </c:when>
				    <c:otherwise>
				        ${article.ads_horizont1}
				    </c:otherwise>
				</c:choose>
			    </c:if>
			    <br>
		</div>
		
		<div itemprop="articleBody"> ${article.description}</div>
		
		</div>
		
		<div class="bg-warning ads">
				<c:if test="${empty unvisible}">
				<c:choose>
				 	<c:when test="${empty article.ads_horizont2}">
				    	<%@include file="/common/ad_horizont.jsp"%>
				    </c:when>
				    <c:otherwise>
				        ${article.ads_horizont2}
				    </c:otherwise>
				    </c:choose>
			    </c:if>
		</div>
		<!-- Put this div tag to the place, where the Comments block will be -->
<div id="vk_comments"></div>
<script type="text/javascript">
VK.Widgets.Comments("vk_comments", {limit: 5, width: "750", attach: "*"});
</script>
		</div>
		<!-- /.col-xs-12 col-sm-8 col-md-8 -->
		<div class="col-sm-4 col-md-4">
		
		<c:if test="${empty unvisible}">
				<c:choose>
				    <c:when test="${empty article.ads_side1}">
				    	<%@include file="/common/ad_side.jsp"%>
				    </c:when>
				    <c:otherwise>
				        ${article.ads_side1}
				    </c:otherwise>
				</c:choose>
			</c:if>
			
		 <%@include file="/item/item_side_list.jsp"%>
			
		<!-- /.col-sm-4 col-md-4 -->
		</div>
	</div>

<%@include file="/common/bottom.jsp"%>