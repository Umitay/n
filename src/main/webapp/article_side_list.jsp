<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
	 <c:if test="${empty unvisible && loop.index==1}">
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
	    			
	<c:if test="${!empty articles}">
			<section itemscope="" itemtype="http://schema.org/ItemList" style="margin-top: 20px; float:left;" class="item-list">
				<meta content=true itemprop="mainContentOfPage">
				<meta content="Descending" itemprop="itemListOrder">
				
				<c:forEach items="${articles}" var="article" varStatus="loop" begin="0" end="2">
					<div itemprop="itemListElement" class="thumbnail" onclick="open_article('${article.slug}')">
					   <c:if test="${!empty article.thumbnailUrl}">
					      <a href="/article/${article.slug}">
					        <img itemprop="image" class="img-responsive" src="${article.thumbnailUrl}" >
					      </a>
					    </c:if>
					    <div>
					     <h4  itemprop="name"> <a href="/article/${article.slug}" itemprop="url">${article.name}</a></h4>
					     <div id="description" itemprop="description">${article.about}</div>
					    </div>
					 </div>
	  			</c:forEach>
	  		</section>
	</c:if>

<script type="text/javascript">
//<![CDATA[
function open_article(slug){
	location.href="/article/"+slug;
}
//]]>
</script> 